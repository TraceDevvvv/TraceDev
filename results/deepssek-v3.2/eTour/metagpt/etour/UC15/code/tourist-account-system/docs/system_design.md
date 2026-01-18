## Implementation approach

我们将采用分层架构设计，使用Java Spring Boot框架构建一个RESTful API服务，处理旅游账户数据修改功能。系统将包含以下技术栈：

1. **Spring Boot 3.x** - 主流Java后端框架，提供快速开发能力
2. **Spring Data JPA** - 数据持久化层，与数据库交互
3. **Hibernate Validator** - 数据验证框架，确保输入数据的有效性
4. **Lombok** - 减少样板代码，提高代码可读性
5. **SLF4J + Logback** - 日志记录，便于调试和监控
6. **Spring Security** - 安全认证，确保只有登录的旅行社操作员可以访问
7. **Connection Pool (HikariCP)** - 数据库连接池，提高性能
8. **Java 17+** - LTS版本，提供现代语言特性

关键设计决策：
- 使用DTO模式分离API层和业务层数据
- 实现乐观锁机制防止并发修改冲突
- 添加连接中断重试机制处理与ETOUR服务器的通信问题
- 设计可扩展的验证框架，支持字段级验证规则
- 实现审计功能，记录所有账户修改操作

## File list

以下是项目的主要文件结构：

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
    %% 实体类
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
    
    %% DTO类
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
    
    %% 服务接口
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
    
    %% 仓库接口
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
    
    %% 控制器
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
    
    %% 验证器
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
    
    %% 异常类
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
    
    %% 配置类
    class ETOURConnectionConfig {
        +String serverUrl
        +Integer timeoutSeconds
        +Integer maxRetries
        +Integer retryDelayMillis
        +String apiKey
        +Boolean isProduction
    }
    
    %% 关系
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
    
    %% 步骤1：从SearchTourist结果中选择游客
    Note over FE,EX: 步骤1：旅行社操作员从列表选择游客
    FE->>TC: GET /api/tourists?searchTerm="..."
    TC->>TS: searchTourists(searchTerm)
    TS->>REPO: findBySearchTerm(searchTerm)
    REPO-->>TS: List~Tourist~
    TS->>DTO: TouristDTO.fromEntity(tourist)
    DTO-->>TS: TouristDTO
    TS-->>TC: List~TouristDTO~
    TC-->>FE: JSON响应（游客列表）
    
    %% 步骤2：加载选中游客数据并显示在可编辑表单
    Note over FE,EX: 步骤2：加载选中游客数据
    FE->>TC: GET /api/tourists/{id}
    TC->>TS: getTouristById(id)
    TS->>REPO: findById(id)
    alt 游客存在
        REPO-->>TS: Optional~Tourist~
        TS->>DTO: TouristDTO.fromEntity(tourist)
        DTO-->>TS: TouristDTO
        TS-->>TC: TouristDTO
        TC-->>FE: 游客详细信息
    else 游客不存在
        REPO-->>TS: Optional.empty()
        TS-->>EX: throw TouristNotFoundException
        EX-->>FE: 404错误响应
    end
    
    %% 步骤3：编辑表单字段并提交
    Note over FE,EX: 步骤3：编辑并提交修改
    FE->>TC: PUT /api/tourists/{id}
    TC->>TS: updateTourist(id, request, operatorId)
    
    %% 步骤4：验证信息（如果不完整则触发Errored用例）
    Note over FE,EX: 步骤4：验证信息
    TS->>VS: validateTouristData(request)
    VS->>TV: validate(request)
    TV->>VS: ValidationResult
    VS->>EV: isEmailValid(request.email)
    EV-->>VS: boolean
    
    alt 验证失败
        VS-->>TS: ValidationResult (invalid)
        TS-->>EX: throw ValidationException
        EX-->>FE: 400验证错误响应
        Note over FE,EX: 激活Errored用例
        FE->>TC: 显示错误信息并要求重新输入
    else 验证成功
        VS-->>TS: ValidationResult (valid)
        
        %% 检查ETOUR连接
        TS->>ECON: connectToETOUR()
        alt 连接中断
            ECON-->>TS: false (connection failed)
            TS->>ECON: retryConnection(3)
            alt 重试成功
                ECON-->>TS: true
            else 重试失败
                ECON-->>TS: false
                TS-->>EX: throw ETourConnectionException
                EX-->>FE: 503服务不可用
                Note over FE,EX: 中断连接处理
                break
            end
        else 连接正常
            ECON-->>TS: true
        end
        
        %% 数据验证和审计记录
        TS->>REPO: findById(id)
        REPO-->>TS: Optional~Tourist~
        TS->>AUDIT: createPreUpdateAuditLog(tourist, operatorId)
        AUDIT-->>TS: AuditLog
        
        %% 更新游客数据
        TS->>TS: tourist.updateFrom(request)
        TS->>REPO: save(tourist)
        REPO-->>TS: 更新的Tourist
        
        %% 步骤5：确认操作
        Note over FE,EX: 步骤5：请求操作确认
        TS->>TC: TouristResponse (needs confirmation, auditLogId)
        TC-->>FE: 响应（需要确认，包含auditLogId）
        
        %% 用户确认操作
        FE->>TC: POST /api/tourists/{id}/confirm/{auditLogId}
        TC->>TS: confirmUpdate(touristId, auditLogId, operatorId)
        
        %% 步骤6：存储修改后的数据
        Note over FE,EX: 步骤6：存储修改的数据
        TS->>AUDIT: confirmAuditLog(auditLogId)
        TS->>ECON: sendDataToETOUR(updatedTourist)
        ECON-->>TS: boolean (success)
        
        TS->>DTO: TouristDTO.fromEntity(updatedTourist)
        DTO-->>TS: TouristDTO
        TS->>TC: TouristResponse.success(touristDTO)
        TC-->>FE: 成功响应（更新后的游客数据）
        
        %% 退出条件：系统报告更新信息
        Note over FE,EX: 退出：系统报告更新后的游客账户信息
        FE->>FE: 显示成功消息和更新后的账户详情
    end
```

## Anything UNCLEAR

1. **ETOUR服务器接口规格**：不清楚ETOUR服务器API的具体格式、认证方式和数据格式要求。需要进一步明确接口规范才能实现完整的集成。

2. **搜索功能依赖**