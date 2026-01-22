            PointService->>ETOURService: checkConnection()
            alt ETOUR    
                ETOURService-->>PointService:     
                PointService->>PointRepository: save(updatedPoint)
                PointRepository->>DB:     
                DB-->>PointRepository:     
                PointRepository-->>PointService:        
                PointService->>ETOURService: updatePointInETOUR(pointId, updateData)
                ETOURService-->>PointService:     
                PointService->>LogService:       
                PointService-->>PointController:       RestaurantPointDTO
            else ETOUR    
                ETOURService-->>PointService:     
                PointService->>PointRepository: save(updatedPoint)
                PointRepository->>DB:     
                DB-->>PointRepository:     
                PointRepository-->>PointService:        
                PointService->>LogService:   ETOUR      
                PointService-->>PointController:       RestaurantPointDTO（   ）
            end
        end
    end
    
    PointController-->>UI:       
    UI-->>Operator:       
    
    %% 5.       
    Operator->>UI:     
    UI->>PointController: POST /api/points/{pointId}/cancel
    PointController->>AuthService:     
    AuthService-->>PointController:     
    PointController->>PointService: cancelOperation(pointId)
    PointService->>LogService:       
    PointService-->>PointController:       
    PointController-->>UI:       
    UI-->>Operator:        
    
    %% 6.       
    Note over PointController,DB:       
    PointController->>PointController:     
    PointController->>LogService:     
    PointController-->>UI:       
    UI-->>Operator:       
```

### 4.2        

```mermaid
sequenceDiagram
    participant Operator as    
    participant UI as     
    participant Controller as    
    participant Service as    
    participant Validator as    
    participant DB as    
    participant Logger as     
    
    %%         
    Operator->>UI:       
    UI->>Controller: POST /api/points/validate
    Controller->>Service: validateData(data)
    Service->>Validator: validate(data)
    Validator-->>Service:       
    Service-->>Controller:   ValidationException
    
    Controller->>Logger: logError(exception)
    Controller-->>UI:   400 Bad Request
    UI-->>Operator:         
    
    %%          
    Operator->>UI:     
    UI->>Controller: GET /api/points/{id}
    Controller->>Service: getPointById(id)
    Service->>DB:     
    Note over DB:        
    DB-->>Service:   DatabaseException
    
    Service->>Logger: logDatabaseError(exception)
    Service-->>Controller:   ServiceException
    Controller->>Logger: logServiceError(exception)
    Controller-->>UI:   503 Service Unavailable
    UI-->>Operator:         
    
    %%         
    Operator1->>UI1:     A
    Operator2->>UI2:       A
    UI1->>Controller: PUT /api/points/A
    UI2->>Controller: PUT /api/points/A
    
    Controller->>Service1: updatePoint(A, data1)
    Controller->>Service2: updatePoint(A, data2)
    
    Service1->>DB:     A (version=1)
    Service2->>DB:     A (version=1)
    DB-->>Service1:     A (version=1)
    DB-->>Service2:     A (version=1)
    
    Service1->>DB:     A (version=1->2)
    DB-->>Service1:     
    Service2->>DB:     A (version=1->2)
    Note over DB:           
    DB-->>Service2:   OptimisticLockException
    
    Service2->>Logger: logConcurrentUpdate(exception)
    Service2-->>Controller:   ConcurrentUpdateException
    Controller-->>UI2:   409 Conflict
    UI2-->>Operator2:         ，     
```

## 5.      

### 5.1       

  PRD       ，          ：

```sql
--    
CREATE TABLE users (
    user_id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100)