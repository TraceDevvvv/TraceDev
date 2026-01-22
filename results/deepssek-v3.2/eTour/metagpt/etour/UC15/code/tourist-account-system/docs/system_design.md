## Implementation approach

           ，  Java Spring Boot      RESTful API  ，            。          ：

1. **Spring Boot 3.x** -   Java    ，        
2. **Spring Data JPA** -       ，      
3. **Hibernate Validator** -       ，          
4. **Lombok** -       ，       
5. **SLF4J + Logback** -     ，       
6. **Spring Security** -     ，                 
7. **Connection Pool (HikariCP)** -       ，    
8. **Java 17+** - LTS  ，        

      ：
-   DTO    API       
-                
-              ETOUR        
-           ，         
-       ，          

## File list

            ：

```
tourist-account-system/
├── pom.xml
├── src/main/java/com/etour/
│   ├── TouristAccountSystemApplication.java
│   ├── config/
│   │   ├── SecurityConfig.java
│   │   ├── AuditConfig.java
│   │   └── ETOURConnectionConfig.java
│   ├── dto/
│   │   ├── TouristDTO.java
│   │   ├── TouristUpdateRequest.java
│   │   ├── TouristResponse.java
│   │   └── ValidationErrorDTO.java
│   ├── entity/
│   │   ├── Tourist.java
│   │   ├── AgencyOperator.java
│   │   ├── AuditLog.java
│   │   └── Address.java
│   ├── repository/
│   │   ├── TouristRepository.java
│   │   ├── AgencyRepository.java
│   │   └── AuditLogRepository.java
│   ├── service/
│   │   ├── TouristService.java
│   │   ├── TouristServiceImpl.java
│   │   ├── ValidationService.java
│   │   ├── ValidationServiceImpl.java
│   │   ├── AuditService.java
│   │   └── ETOURConnectionService.java
│   ├── controller/
│   │   ├── TouristController.java
│   │   ├── ErrorController.java
│   │   └── AgencyController.java
│   ├── validation/
│   │   ├── TouristValidator.java
│   │   ├── EmailValidator.java
│   │   ├── PhoneValidator.java
│   │   └── PasswordValidator.java
│   ├── exception/
│   │   ├── GlobalExceptionHandler.java
│   │   ├── ETourConnectionException.java
│   │   ├── TouristNotFoundException.java
│   │   ├── ValidationException.java
│   │   └── OperationNotPermittedException.java
│   └── util/
│       ├── DateUtils.java
│       ├── StringUtils.java
│       └── RetryUtil.java
├── src/main/resources/
│   ├── application.yml
│   ├── application-dev.yml
│   ├── application-prod.yml
│   └── logback-spring.xml
└── src/test/java/com/etour/
    ├── controller/TouristControllerTest.java
    ├── service/TouristServiceTest.java
    └── validation/TouristValidatorTest.java
```

## Data structures and interfaces

```mermaid
classDiagram
    %%    
    class Tourist {
        +Long id
        +String firstName
        +String lastName
        +String email
        +String phoneNumber
        +LocalDate dateOfBirth
        +Address address
        +String nationality
        +String passportNumber
        +LocalDateTime createdAt
        +LocalDateTime updatedAt
        +Integer version
        +Boolean active
        +getFullName() String
        +updateFrom(TouristUpdateRequest request) void
        +validate() boolean
    }
    
    class Address {
        +String street
        +String city
        +String state
        +String postalCode
        +String country
        +getFullAddress() String
    }
    
    class AgencyOperator {
        +Long id
        +String username
        +String agencyName
        +String email
        +String phone
        +Boolean loggedIn
        +Boolean isActive() boolean
        +login() void
        +logout() void
    }
    
    class AuditLog {
        +Long id
        +String actionType
        +String entityType
        +Long entityId
        +String oldValue
        +String newValue
        +String operatorId
        +LocalDateTime timestamp
        +String ipAddress
        +createLog(String action, Object entity, Object oldValue, Object newValue, String operatorId) AuditLog
    }
    
    %% DTO 
    class TouristDTO {
        +Long id
        +String fullName
        +String email
        +String phoneNumber
        +String nationality
        +String passportNumber
        +LocalDate dateOfBirth
        +String address
        +Boolean active
        +static fromEntity(Tourist tourist) TouristDTO
    }
    
    class TouristUpdateRequest {
        +String firstName
        +String lastName
        +String email
        +String phoneNumber
        +LocalDate dateOfBirth
        +Address address
        +String nationality
        +String passportNumber
        +validate() void
    }
    
    class TouristResponse {
        +Boolean success
        +String message
        +TouristDTO data
        +LocalDateTime timestamp
        +static success(TouristDTO data) TouristResponse
        +static error(String message) TouristResponse
    }
    
    %%     
    interface TouristService {
        +List~TouristDTO~ searchTourists(String searchTerm) List~TouristDTO~
        +TouristDTO getTouristById(Long id) TouristDTO
        +TouristResponse updateTourist(Long id, TouristUpdateRequest request, String operatorId) TouristResponse
        +TouristResponse confirmUpdate(Long touristId, Long auditLogId, String operatorId) TouristResponse
        +Boolean deleteTourist(Long id, String operatorId) boolean
    }
    
    class TouristServiceImpl {
        -TouristRepository touristRepository
        -ValidationService validationService
        -AuditService auditService
        -ETOURConnectionService etourConnectionService
        +searchTourists(String searchTerm) List~TouristDTO~
        +getTouristById(Long id) TouristDTO
        +updateTourist(Long id, TouristUpdateRequest request, String operatorId) TouristResponse
        +confirmUpdate(Long touristId, Long auditLogId, String operatorId) TouristResponse
        +deleteTourist(Long id, String operatorId) boolean
    }
    
    interface ValidationService {
        +ValidationResult validateTouristData(TouristUpdateRequest request) ValidationResult
        +Boolean isEmailValid(String email) boolean
        +Boolean isPhoneValid(String phone) boolean
        +Boolean isPassportValid(String passport) boolean
    }
    
    class ValidationServiceImpl {
        -TouristValidator touristValidator
        -EmailValidator emailValidator
        -PhoneValidator phoneValidator
        -PasswordValidator passwordValidator
        +validateTouristData(TouristUpdateRequest request) ValidationResult
        +isEmailValid(String email) boolean
        +isPhoneValid(String phone) boolean
        +isPassportValid(String passport) boolean
    }
    
    interface ETOURConnectionService {
        +Boolean connectToETOUR() boolean
        +Boolean isConnectionAlive() boolean
        +sendDataToETOUR(Object data) boolean
        +retryConnection(Integer maxRetries) boolean
    }
    
    %%     
    interface TouristRepository {
        +List~Tourist~ findBySearchTerm(String searchTerm) List~Tourist~
        +Optional~Tourist~ findById(Long id) Optional~Tourist~
        +Tourist save(Tourist tourist) Tourist
        +void deleteById(Long id) void
        +Long countActiveTourists() Long
    }
    
    interface AgencyRepository {
        +Optional~AgencyOperator~ findById(Long id) Optional~AgencyOperator~
        +Optional~AgencyOperator~ findByUsername(String username) Optional~AgencyOperator~
        +AgencyOperator save(AgencyOperator operator) AgencyOperator
        +Boolean isLoggedIn(Long operatorId) boolean
    }
    
    %%    
    class TouristController {
        -TouristService touristService
        +ResponseEntity~List~TouristDTO~~ searchTourists(@RequestParam String searchTerm) ResponseEntity~List~TouristDTO~~
        +ResponseEntity~TouristDTO~ getTourist(@PathVariable Long id) ResponseEntity~TouristDTO~
        +ResponseEntity~TouristResponse~ updateTourist(@PathVariable Long id, @Valid @RequestBody TouristUpdateRequest request, @RequestHeader String operatorId) ResponseEntity~TouristResponse~
        +ResponseEntity~TouristResponse~ confirmUpdate(@PathVariable Long touristId, @PathVariable Long auditLogId, @RequestHeader String operatorId) ResponseEntity~TouristResponse~
    }
    
    class ErrorController {
        +ResponseEntity~Map~String, Object~~ handleValidationException(ValidationException ex) ResponseEntity~Map~String, Object~~
        +ResponseEntity~Map~String, Object~~ handleTouristNotFoundException(TouristNotFoundException ex) ResponseEntity~Map~String, Object~~
        +ResponseEntity~Map~String, Object~~ handleETourConnectionException(ETourConnectionException ex) ResponseEntity~Map~String, Object~~
    }
    
    %%    
    class TouristValidator {
        -ValidationService validationService
        +ValidationResult validate(TouristUpdateRequest request) ValidationResult
        +Boolean validateRequiredFields(TouristUpdateRequest request) boolean
        +Boolean validateBusinessRules(TouristUpdateRequest request) boolean
    }
    
    class ValidationResult {
        +Boolean isValid
        +List~String~ errors
        +Boolean hasErrors() boolean
        +void addError(String error) void
        +String getErrorMessage() String
    }
    
    %%    
    class ETourConnectionException {
        +String connectionUrl
        +Integer statusCode
        +String errorMessage
        +LocalDateTime timestamp
        +getConnectionStatus() String
    }
    
    class TouristNotFoundException {
        +Long touristId
        +String searchCriteria
        +LocalDateTime timestamp
    }
    
    class ValidationException {
        +List~String~ validationErrors
        +LocalDateTime timestamp
        +String fieldName
    }
    
    %%    
    class ETOURConnectionConfig {
        +String serverUrl
        +Integer timeoutSeconds
        +Integer maxRetries
        +Integer retryDelayMillis
        +String apiKey
        +Boolean isProduction
    }
    
    %%   
    Tourist "1" -- "1" Address
    TouristServiceImpl --> TouristRepository
    TouristServiceImpl --> ValidationService
    TouristServiceImpl --> AuditService
    TouristServiceImpl --> ETOURConnectionService
    TouristController --> TouristService
    ValidationServiceImpl --> TouristValidator
    ValidationServiceImpl --> EmailValidator
    TouristValidator --> ValidationService
    Tourist "1" -- "*" AuditLog : has
    AgencyOperator "1" -- "*" AuditLog : performed by
    TouristUpdateRequest ..> Tourist : converts to
    TouristDTO <.. Tourist : converts from
```

## Program call flow

```mermaid
sequenceDiagram
    participant FE as Frontend/Angular UI
    participant TC as TouristController
    participant TS as TouristServiceImpl
    participant VS as ValidationServiceImpl
    participant TV as TouristValidator
    participant EV as EmailValidator
    participant REPO as TouristRepository
    participant AUDIT as AuditService
    participant ECON as ETOURConnectionService
    participant DTO as TouristDTO
    participant EX as ExceptionHandler
    
    %%   1： SearchTourist       
    Note over FE,EX:   1：             
    FE->>TC: GET /api/tourists?searchTerm="..."
    TC->>TS: searchTourists(searchTerm)
    TS->>REPO: findBySearchTerm(searchTerm)
    REPO-->>TS: List~Tourist~
    TS->>DTO: TouristDTO.fromEntity(tourist)
    DTO-->>TS: TouristDTO
    TS-->>TC: List~TouristDTO~
    TC-->>FE: JSON  （    ）
    
    %%   2：                 
    Note over FE,EX:   2：        
    FE->>TC: GET /api/tourists/{id}
    TC->>TS: getTouristById(id)
    TS->>REPO: findById(id)
    alt     
        REPO-->>TS: Optional~Tourist~
        TS->>DTO: TouristDTO.fromEntity(tourist)
        DTO-->>TS: TouristDTO
        TS-->>TC: TouristDTO
        TC-->>FE:       
    else      
        REPO-->>TS: Optional.empty()
        TS-->>EX: throw TouristNotFoundException
        EX-->>FE: 404    
    end
    
    %%   3：         
    Note over FE,EX:   3：       
    FE->>TC: PUT /api/tourists/{id}
    TC->>TS: updateTourist(id, request, operatorId)
    
    %%   4：    （        Errored  ）
    Note over FE,EX:   4：    
    TS->>VS: validateTouristData(request)
    VS->>TV: validate(request)
    TV->>VS: ValidationResult
    VS->>EV: isEmailValid(request.email)
    EV-->>VS: boolean
    
    alt     
        VS-->>TS: ValidationResult (invalid)
        TS-->>EX: throw ValidationException
        EX-->>FE: 400      
        Note over FE,EX:   Errored  
        FE->>TC:              
    else     
        VS-->>TS: ValidationResult (valid)
        
        %%   ETOUR  
        TS->>ECON: connectToETOUR()
        alt     
            ECON-->>TS: false (connection failed)
            TS->>ECON: retryConnection(3)
            alt     
                ECON-->>TS: true
            else     
                ECON-->>TS: false
                TS-->>EX: throw ETourConnectionException
                EX-->>FE: 503     
                Note over FE,EX:       
                break
            end
        else     
            ECON-->>TS: true
        end
        
        %%          
        TS->>REPO: findById(id)
        REPO-->>TS: Optional~Tourist~
        TS->>AUDIT: createPreUpdateAuditLog(tourist, operatorId)
        AUDIT-->>TS: AuditLog
        
        %%       
        TS->>TS: tourist.updateFrom(request)
        TS->>REPO: save(tourist)
        REPO-->>TS:    Tourist
        
        %%   5：    
        Note over FE,EX:   5：      
        TS->>TC: TouristResponse (needs confirmation, auditLogId)
        TC-->>FE:   （    ，  auditLogId）
        
        %%       
        FE->>TC: POST /api/tourists/{id}/confirm/{auditLogId}
        TC->>TS: confirmUpdate(touristId, auditLogId, operatorId)
        
        %%   6：        
        Note over FE,EX:   6：       
        TS->>AUDIT: confirmAuditLog(auditLogId)
        TS->>ECON: sendDataToETOUR(updatedTourist)
        ECON-->>TS: boolean (success)
        
        TS->>DTO: TouristDTO.fromEntity(updatedTourist)
        DTO-->>TS: TouristDTO
        TS->>TC: TouristResponse.success(touristDTO)
        TC-->>FE:     （        ）
        
        %%     ：        
        Note over FE,EX:   ：              
        FE->>FE:                
    end
```

## Anything UNCLEAR

1. **ETOUR       **：   ETOUR   API     、           。                    。

2. **      **