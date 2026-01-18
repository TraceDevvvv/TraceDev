            PointService->>ETOURService: checkConnection()
            alt ETOUR连接正常
                ETOURService-->>PointService: 连接正常
                PointService->>PointRepository: save(updatedPoint)
                PointRepository->>DB: 更新数据
                DB-->>PointRepository: 更新成功
                PointRepository-->>PointService: 返回保存的对象
                PointService->>ETOURService: updatePointInETOUR(pointId, updateData)
                ETOURService-->>PointService: 同步成功
                PointService->>LogService: 记录操作成功
                PointService-->>PointController: 返回更新后的RestaurantPointDTO
            else ETOUR连接失败
                ETOURService-->>PointService: 连接失败
                PointService->>PointRepository: save(updatedPoint)
                PointRepository->>DB: 更新数据
                DB-->>PointRepository: 更新成功
                PointRepository-->>PointService: 返回保存的对象
                PointService->>LogService: 记录ETOUR连接失败警告
                PointService-->>PointController: 返回更新后的RestaurantPointDTO（带警告）
            end
        end
    end
    
    PointController-->>UI: 返回操作结果
    UI-->>Operator: 显示操作结果
    
    %% 5. 取消操作流程
    Operator->>UI: 点击取消
    UI->>PointController: POST /api/points/{pointId}/cancel
    PointController->>AuthService: 验证令牌
    AuthService-->>PointController: 验证通过
    PointController->>PointService: cancelOperation(pointId)
    PointService->>LogService: 记录操作取消
    PointService-->>PointController: 返回取消成功
    PointController-->>UI: 返回取消结果
    UI-->>Operator: 显示操作已取消
    
    %% 6. 错误处理流程
    Note over PointController,DB: 当发生异常时
    PointController->>PointController: 捕获异常
    PointController->>LogService: 记录异常
    PointController-->>UI: 返回错误响应
    UI-->>Operator: 显示错误信息
```

### 4.2 错误处理序列图

```mermaid
sequenceDiagram
    participant Operator as 操作员
    participant UI as 用户界面
    participant Controller as 控制器
    participant Service as 服务层
    participant Validator as 验证器
    participant DB as 数据库
    participant Logger as 日志服务
    
    %% 数据验证失败场景
    Operator->>UI: 提交无效数据
    UI->>Controller: POST /api/points/validate
    Controller->>Service: validateData(data)
    Service->>Validator: validate(data)
    Validator-->>Service: 返回验证错误
    Service-->>Controller: 抛出ValidationException
    
    Controller->>Logger: logError(exception)
    Controller-->>UI: 返回400 Bad Request
    UI-->>Operator: 显示验证错误信息
    
    %% 数据库连接失败场景
    Operator->>UI: 请求数据
    UI->>Controller: GET /api/points/{id}
    Controller->>Service: getPointById(id)
    Service->>DB: 查询数据
    Note over DB: 数据库连接失败
    DB-->>Service: 抛出DatabaseException
    
    Service->>Logger: logDatabaseError(exception)
    Service-->>Controller: 抛出ServiceException
    Controller->>Logger: logServiceError(exception)
    Controller-->>UI: 返回503 Service Unavailable
    UI-->>Operator: 显示系统维护信息
    
    %% 并发更新冲突场景
    Operator1->>UI1: 修改数据A
    Operator2->>UI2: 同时修改数据A
    UI1->>Controller: PUT /api/points/A
    UI2->>Controller: PUT /api/points/A
    
    Controller->>Service1: updatePoint(A, data1)
    Controller->>Service2: updatePoint(A, data2)
    
    Service1->>DB: 获取数据A (version=1)
    Service2->>DB: 获取数据A (version=1)
    DB-->>Service1: 返回数据A (version=1)
    DB-->>Service2: 返回数据A (version=1)
    
    Service1->>DB: 更新数据A (version=1->2)
    DB-->>Service1: 更新成功
    Service2->>DB: 更新数据A (version=1->2)
    Note over DB: 乐观锁检测到版本冲突
    DB-->>Service2: 抛出OptimisticLockException
    
    Service2->>Logger: logConcurrentUpdate(exception)
    Service2-->>Controller: 抛出ConcurrentUpdateException
    Controller-->>UI2: 返回409 Conflict
    UI2-->>Operator2: 显示数据已被修改，请刷新重试
```

## 5. 数据库设计

### 5.1 物理数据模型

基于PRD中的数据库设计，扩展为完整的物理模型：

```sql
-- 用户表
CREATE TABLE users (
    user_id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100)