# System Design: Cultural Heritage Management System

## Implementation approach

We will implement a Java-based system for managing cultural heritage objects with the following approach:

1. **Framework Selection**: Use plain Java with JDBC for database persistence (could be extended with Spring Boot later). For simplicity in this design, we'll use an in-memory H2 database for demonstration.

2. **Architecture Pattern**: MVC (Model-View-Controller) pattern to separate concerns:
   - Model: CulturalHeritage entity class
   - Controller: CulturalHeritageController for business logic
   - Service: CulturalHeritageService for validation and duplicate checking
   - Repository: CulturalHeritageRepository for data persistence
   - View: Console-based UI for simplicity (could be replaced with GUI later)

3. **Key Components**:
   - Data validation using Java Bean Validation (JSR-380) annotations
   - Duplicate checking based on unique identifier or combination of attributes
   - Transaction confirmation mechanism
   - Error handling for invalid data and server interruptions

4. **Database**: Use H2 in-memory database for simplicity with JDBC. The system can be easily extended to use PostgreSQL, MySQL, etc.

5. **Build Tool**: Maven for dependency management and build automation.

## File list

- src/main/java/com/culturalheritage/Main.java
- src/main/java/com/culturalheritage/model/CulturalHeritage.java
- src/main/java/com/culturalheritage/controller/CulturalHeritageController.java
- src/main/java/com/culturalheritage/service/CulturalHeritageService.java
- src/main/java/com/culturalheritage/repository/CulturalHeritageRepository.java
- src/main/java/com/culturalheritage/exception/DuplicateHeritageException.java
- src/main/java/com/culturalheritage/exception/ValidationException.java
- src/main/java/com/culturalheritage/database/DatabaseConnection.java
- src/main/java/com/culturalheritage/util/ValidationUtil.java
- src/main/java/com/culturalheritage/view/ConsoleUI.java
- src/test/java/com/culturalheritage/service/CulturalHeritageServiceTest.java
- src/test/java/com/culturalheritage/repository/CulturalHeritageRepositoryTest.java
- pom.xml
- README.md
- docs/system_design.md
- docs/system_design-sequence-diagram.mermaid
- docs/system_design-class-diagram.mermaid

## Data structures and interfaces

```mermaid
classDiagram
    class CulturalHeritage {
        -String id
        -String name
        -String type
        -String location
        -String era
        -String description
        -String agencyCode
        -Date registrationDate
        +CulturalHeritage()
        +CulturalHeritage(String name, String type, String location, String era, String description, String agencyCode)
        +getId() String
        +setId(String id) void
        +getName() String
        +setName(String name) void
        +getType() String
        +setType(String type) void
        +getLocation() String
        +setLocation(String location) void
        +getEra() String
        +setEra(String era) void
        +getDescription() String
        +setDescription(String description) void
        +getAgencyCode() String
        +setAgencyCode(String agencyCode) void
        +getRegistrationDate() Date
        +setRegistrationDate(Date registrationDate) void
        +toString() String
        +equals(Object obj) boolean
        +hashCode() int
    }
    
    class CulturalHeritageService {
        -CulturalHeritageRepository repository
        +CulturalHeritageService(CulturalHeritageRepository repository)
        +insertCulturalHeritage(CulturalHeritage heritage) CulturalHeritage
        +validateCulturalHeritage(CulturalHeritage heritage) void
        +checkForDuplicates(CulturalHeritage heritage) boolean
        +confirmTransaction(CulturalHeritage heritage) boolean
    }
    
    class CulturalHeritageRepository {
        -Connection connection
        +CulturalHeritageRepository(Connection connection)
        +save(CulturalHeritage heritage) CulturalHeritage
        +findById(String id) CulturalHeritage
        +findByNameAndLocation(String name, String location) CulturalHeritage
        +findAll() List~CulturalHeritage~
        +delete(String id) boolean
        +createTable() void
    }
    
    class CulturalHeritageController {
        -CulturalHeritageService service
        +CulturalHeritageController(CulturalHeritageService service)
        +insertCulturalHeritage(CulturalHeritage heritage) CulturalHeritage
        +handleInsertionForm() CulturalHeritage
        +displayForm() CulturalHeritage
        +verifyData(CulturalHeritage heritage) boolean
        +memorizeCulturalHeritage(CulturalHeritage heritage) CulturalHeritage
        +notifyInclusion(CulturalHeritage heritage) void
    }
    
    class ConsoleUI {
        -CulturalHeritageController controller
        -Scanner scanner
        +ConsoleUI(CulturalHeritageController controller)
        +start() void
        +displayMenu() void
        +activateInsertionFeature() void
        +displayForm() CulturalHeritage
        +fillForm() CulturalHeritage
        +askForConfirmation(CulturalHeritage heritage) boolean
        +handleError(String message) void
        +displaySuccess(CulturalHeritage heritage) void
    }
    
    class DatabaseConnection {
        +getConnection() Connection
        +closeConnection() void
    }
    
    class ValidationUtil {
        +validateString(String value, String fieldName, int maxLength) void
        +validateRequiredFields(CulturalHeritage heritage) void
        +isValidEra(String era) boolean
    }
    
    class DuplicateHeritageException {
        -String message
        +DuplicateHeritageException(String message)
    }
    
    class ValidationException {
        -String message
        +ValidationException(String message)
    }
    
    CulturalHeritageController --> CulturalHeritageService : uses
    CulturalHeritageService --> CulturalHeritageRepository : uses
    CulturalHeritageService --> ValidationUtil : uses
    ConsoleUI --> CulturalHeritageController : uses
    CulturalHeritageRepository --> DatabaseConnection : uses
    CulturalHeritageService ..> DuplicateHeritageException : throws
    CulturalHeritageService ..> ValidationException : throws
```

## Program call flow

```mermaid
sequenceDiagram
    participant A as AgencyOperator
    participant UI as ConsoleUI
    participant C as CulturalHeritageController
    participant S as CulturalHeritageService
    participant V as ValidationUtil
    participant R as CulturalHeritageRepository
    participant DB as Database
    
    A->>UI: start()
    UI->>A: displayMenu()
    A->>UI: activateInsertionFeature()
    UI->>C: handleInsertionForm()
    C->>UI: displayForm()
    UI->>A: displayForm()
    A->>UI: fillForm()
    UI->>C: insertCulturalHeritage(formData)
    C->>S: insertCulturalHeritage(heritage)
    S->>V: validateCulturalHeritage(heritage)
    alt Data is invalid
        V-->>S: ValidationException
        S-->>C: ValidationException
        C-->>UI: ValidationException
        UI->>A: handleError("Invalid data")
    else Data is valid
        S->>S: checkForDuplicates(heritage)
        alt Duplicate found
            S-->>C: DuplicateHeritageException
            C-->>UI: DuplicateHeritageException
            UI->>A: handleError("Duplicate heritage")
        else No duplicate
            S->>UI: askForConfirmation(heritage)
            UI->>A: askForConfirmation(heritage)
            alt User confirms
                A->>UI: confirm()
                UI->>S: confirmTransaction(heritage)
                S->>R: save(heritage)
                R->>DB: INSERT INTO cultural_heritage
                DB-->>R: success
                R-->>S: saved heritage
                S-->>C: saved heritage
                C->>C: memorizeCulturalHeritage(heritage)
                C->>UI: notifyInclusion(heritage)
                UI->>A: displaySuccess(heritage)
            else User cancels
                A->>UI: cancel()
                UI->>A: displayCancellation()
            end
        end
    end
```

## Anything UNCLEAR

1. **Server ETOUR Connection**: The requirement mentions "Interruption of the connection to the server ETOUR" but doesn't specify what ETOUR is. I assume it's an external server or database. The design includes basic connection handling but would need more details about ETOUR protocol.

2. **Agency Login**: The entry condition states "The agency has logged" but doesn't specify authentication mechanism. The design assumes the agency is already authenticated.

3. **Duplicate Criteria**: The quality requirement states "not accept duplicates cultural heritage" but doesn't define what constitutes a duplicate. I've assumed duplicates are based on name and location combination, but this might need refinement.

4. **Errored Use Case**: The flow mentions activating "use case Errored" but doesn't provide details. I've implemented basic error handling with exceptions.

5. **Persistence Details**: The requirement doesn't specify database technology. I've used H2 for simplicity but the design can be extended to other databases.

6. **Form Fields**: The exact fields for cultural heritage aren't specified. I've included common attributes like name, type, location, era, description, and agency code.

7. **Transaction Confirmation**: The flow asks for confirmation but doesn't specify UI mechanism. I've implemented a simple console confirmation prompt.