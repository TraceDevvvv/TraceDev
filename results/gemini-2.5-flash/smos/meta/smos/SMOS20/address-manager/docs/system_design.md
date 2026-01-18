## Implementation approach

We will design a backend system using **Spring Boot** for rapid development, **Spring Data JPA** for efficient database interaction, and a **RESTful API** for communication with the frontend. This approach ensures a clear separation of concerns, maintainability, and scalability.

**Key considerations:**
*   **Layered Architecture:** We will implement a standard layered architecture (Controller, Service, Repository, Domain) to organize the codebase logically.
*   **Business Logic Enforcement:** The core business rule "Check if the address has associated classes; If so, shows an error message..." will be strictly enforced in the Service layer.
*   **Transactional Integrity:** Database operations will be managed transactionally to ensure data consistency.
*   **Error Handling:** Custom exceptions will be used to provide meaningful error messages to the client, mapped to appropriate HTTP status codes.
*   **External System Integration:** A dedicated utility or service will handle the interaction with the "SMOS server" to ensure its disconnection post-deletion.

## File list

- `address-manager/pom.xml`
- `address-manager/src/main/java/com/example/addressmanager/AddressManagerApplication.java`
- `address-manager/src/main/java/com/example/addressmanager/domain/Address.java`
- `address-manager/src/main/java/com/example/addressmanager/domain/AssociatedClass.java`
- `address-manager/src/main/java/com/example/addressmanager/repository/AddressRepository.java`
- `address-manager/src/main/java/com/example/addressmanager/repository/AssociatedClassRepository.java`
- `address-manager/src/main/java/com/example/addressmanager/service/AddressService.java`
- `address-manager/src/main/java/com/example/addressmanager/controller/AddressController.java`
- `address-manager/src/main/java/com/example/addressmanager/exception/AddressNotFoundException.java`
- `address-manager/src/main/java/com/example/addressmanager/exception/AddressDeletionForbiddenException.java`
- `address-manager/src/main/java/com/example/addressmanager/util/SmosConnectionManager.java`
- `address-manager/src/main/resources/application.properties`
- `address-manager/docs/system_design.md`
- `address-manager/docs/system_design-class-diagram.mermaid`
- `address-manager/docs/system_design-sequence-diagram.mermaid`

## Data structures and interfaces:

```mermaid
classDiagram
    class AddressManagerApplication {
        +main(args: String[]): void
    }

    class Address {
        +id: Long
        +street: String
        +city: String
        +zipCode: String
        +country: String
        +getId(): Long
        +setStreet(street: String): void
        // ... other getters/setters
    }

    class AssociatedClass {
        +id: Long
        +name: String
        +addressId: Long
        +getId(): Long
        +getAddressId(): Long
        // ... other getters/setters
    }

    interface AddressRepository {
        +findById(id: Long): Optional<Address>
        +deleteById(id: Long): void
        +existsById(id: Long): boolean
    }

    interface AssociatedClassRepository {
        +countByAddressId(addressId: Long): long
    }

    class AddressService {
        -addressRepository: AddressRepository
        -associatedClassRepository: AssociatedClassRepository
        -smosConnectionManager: SmosConnectionManager
        +AddressService(addressRepo: AddressRepository, assocClassRepo: AssociatedClassRepository, smosMgr: SmosConnectionManager)
        +deleteAddress(addressId: Long): void
    }

    class AddressController {
        -addressService: AddressService
        +AddressController(addressService: AddressService)
        +deleteAddress(addressId: Long): ResponseEntity<String>
    }

    class AddressNotFoundException {
        +AddressNotFoundException(message: String)
    }

    class AddressDeletionForbiddenException {
        +AddressDeletionForbiddenException(message: String)
    }

    interface SmosConnectionManager {
        +disconnect(): void
        +isConnected(): boolean
    }

    AddressManagerApplication --> AddressController
    AddressController --> AddressService
    AddressService --> AddressRepository
    AddressService --> AssociatedClassRepository
    AddressService --> SmosConnectionManager
    AddressRepository ..|> JpaRepository
    AssociatedClassRepository ..|> JpaRepository
    Address "1" -- "*" AssociatedClass : has
```

## Program call flow:

```mermaid
sequenceDiagram
    participant UI as User Interface
    participant AC as AddressController
    participant AS as AddressService
    participant AR as AddressRepository
    participant ACR as AssociatedClassRepository
    participant SCM as SmosConnectionManager
    participant DB as Database

    UI->>AC: DELETE /api/addresses/{id} (click 'Delete' button)
    activate AC
    AC->>AS: deleteAddress(addressId)
    activate AS

    AS->>AR: findById(addressId)
    activate AR
    AR->>DB: SELECT * FROM addresses WHERE id = :addressId
    activate DB
    DB-->>AR: Address object or empty
    deactivate DB
    AR-->>AS: Optional<Address>
    deactivate AR

    alt Address not found
        AS-->>AC: throws AddressNotFoundException
        deactivate AS
        AC-->>UI: HTTP 404 Not Found (Error message)
    else Address found
        AS->>ACR: countByAddressId(addressId)
        activate ACR
        ACR->>DB: SELECT COUNT(*) FROM associated_classes WHERE address_id = :addressId
        activate DB
        DB-->>ACR: count
        deactivate DB
        ACR-->>AS: long count
        deactivate ACR

        alt Associated classes exist (count > 0)
            AS-->>AC: throws AddressDeletionForbiddenException
            deactivate AS
            AC-->>UI: HTTP 409 Conflict (Error message: "Unable to delete...")
        else No associated classes (count == 0)
            AS->>AR: delete(addressObject)
            activate AR
            AR->>DB: DELETE FROM addresses WHERE id = :addressId
            activate DB
            DB-->>AR: Deletion successful
            deactivate DB
            AR-->>AS: void
            deactivate AR

            AS->>SCM: isConnected()
            activate SCM
            SCM-->>AS: boolean isConnected
            deactivate SCM

            alt SMOS is connected
                AS->>SCM: disconnect()
                activate SCM
                SCM-->>AS: void
                deactivate SCM
            end

            AS-->>AC: void (deletion successful)
            deactivate AS
            AC-->>UI: HTTP 200 OK (Success message)
            deactivate AC

            Note right of UI: User Interface then typically
            UI->>AC: GET /api/addresses (to display updated list)
            activate AC
            AC->>AS: getAllAddresses()
            activate AS
            AS->>AR: findAll()
            activate AR
            AR->>DB: SELECT * FROM addresses
            activate DB
            DB-->>AR: List<Address>
            deactivate DB
            AR-->>AS: List<Address>
            deactivate AR
            AC-->>UI: HTTP 200 OK (List of updated addresses)
            deactivate AC
        end
    end
```

## Anything UNCLEAR

1.  **SMOS Server Integration Details:** The `SmosConnectionManager` is a mock implementation. In a real-world scenario, the actual integration details (e.g., API endpoints, authentication, specific disconnection protocol) for the SMOS server would need to be provided. The current design assumes a simple `disconnect()` method is sufficient.
2.  **User Authentication and Authorization:** The preconditions state "The user is logged in to the system as an administrator." This design assumes that authentication and authorization checks (e.g., using Spring Security) are handled at a layer above the `AddressController` or within the controller itself before calling the service. The current design does not explicitly implement these security aspects.
3.  **"viewdettaglizzazione" Use Case:** The precondition "the user has taken the case of use 'viewdettaglizzazione'" implies a prior action. This design focuses solely on the 'Delete address' action and assumes the UI correctly manages the user's workflow leading up to the delete operation.
4.  **Frontend Interaction for Updated List:** After deletion, the system "Displays the list of updated addresses." The current REST API design returns a success message for the DELETE request and provides a separate `GET /api/addresses` endpoint for the client to fetch the updated list. The frontend would be responsible for making this subsequent GET request to refresh its view.