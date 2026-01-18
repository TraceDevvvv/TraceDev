```java
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Represents a Cultural Heritage object with its unique properties.
 * The 'name' field is considered the primary unique identifier for duplicate checking.
 */
class CulturalHeritage {
    private String id; // System-generated or user-provided unique ID
    private String name;
    private String description;
    private String location;
    private int yearOfOrigin;

    /**
     * Constructs a new CulturalHeritage object.
     *
     * @param id The unique identifier for the cultural heritage.
     * @param name The name of the cultural heritage.
     * @param description A brief description.
     * @param location The geographical location.
     * @param yearOfOrigin The year of origin.
     */
    public CulturalHeritage(String id, String name, String description, String location, int yearOfOrigin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.yearOfOrigin = yearOfOrigin;
    }

    // Getters for all fields
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public int getYearOfOrigin() {
        return yearOfOrigin;
    }

    /**
     * Determines if two CulturalHeritage objects are equal.
     * Equality is based solely on the 'name' field, as per the quality requirement
     * to not accept duplicate cultural heritage (implying unique names).
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CulturalHeritage that = (CulturalHeritage) o;
        return name.equalsIgnoreCase(that.name); // Case-insensitive comparison for name uniqueness
    }

    /**
     * Generates a hash code for the CulturalHeritage object.
     * The hash code is based solely on the 'name' field to be consistent with equals().
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase()); // Consistent with equals()
    }

    /**
     * Returns a string representation of the CulturalHeritage object.
     *
     * @return A formatted string containing the object's details.
     */
    @Override
    public String toString() {
        return "CulturalHeritage{" +
               "id='" + (id != null && !id.isEmpty() ? id : "N/A") + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", location='" + location + '\'' +
               ", yearOfOrigin=" + yearOfOrigin +
               '}';
    }
}

/**
 * Manages the storage and retrieval of CulturalHeritage objects.
 * Acts as a simple in-memory database for this example.
 */
class CulturalHeritageRepository {
    // Using a Set to automatically enforce uniqueness based on CulturalHeritage's equals/hashCode
    private final Set<CulturalHeritage> culturalHeritages;

    /**
     * Constructs a new CulturalHeritageRepository.
     */
    public CulturalHeritageRepository() {
        this.culturalHeritages = new HashSet<>();
        // Pre-populate with some data for testing duplicate checks
        culturalHeritages.add(new CulturalHeritage("CH001", "Eiffel Tower", "Iron lattice tower in Paris", "Paris, France", 1889));
        culturalHeritages.add(new CulturalHeritage("CH002", "Great Wall of China", "Series of fortifications", "Northern China", -700));
    }

    /**
     * Adds a new cultural heritage object to the repository.
     *
     * @param heritage The CulturalHeritage object to add.
     * @return true if the heritage was added successfully (not a duplicate), false otherwise.
     */
    public boolean addCulturalHeritage(CulturalHeritage heritage) {
        // Set.add() uses equals() and hashCode() to check for duplicates
        return culturalHeritages.add(heritage);
    }

    /**
     * Checks if a cultural heritage object with the same unique identifier (name) already exists.
     *
     * @param heritage The CulturalHeritage object to check.
     * @return true if a duplicate exists, false otherwise.
     */
    public boolean isDuplicate(CulturalHeritage heritage) {
        return culturalHeritages.contains(heritage);
    }

    /**
     * Retrieves all cultural heritage objects currently stored.
     *
     * @return A Set of all CulturalHeritage objects.
     */
    public Set<CulturalHeritage> getAllCulturalHeritages() {
        return new HashSet<>(culturalHeritages); // Return a copy to prevent external modification
    }
}

/**
 * Represents the possible outcomes of an insertion operation.
 */
enum InsertResult {
    SUCCESS,
    DUPLICATE_ERROR,
    VALIDATION_ERROR,
    CANCELED,
    SERVER_ERROR // For simulating connection interruption
}

/**
 * Provides business logic for managing Cultural Heritage objects,
 * including validation and interaction with the repository.
 */
class CulturalHeritageService {
    private final CulturalHeritageRepository repository;

    /**
     * Constructs a new CulturalHeritageService.
     *
     * @param repository The repository to use for data storage.
     */
    public CulturalHeritageService(CulturalHeritageRepository repository) {
        this.repository = repository;
    }

    /**
     * Validates the data of a CulturalHeritage object.
     *
     * @param heritage The CulturalHeritage object to validate.
     * @return true if the data is valid, false otherwise.
     */
    public boolean isValid(CulturalHeritage heritage) {
        // Basic validation: name, description, location should not be empty
        if (heritage.getName() == null || heritage.getName().trim().isEmpty()) {
            System.err.println("Validation Error: Cultural heritage name cannot be empty.");
            return false;
        }
        if (heritage.getDescription() == null || heritage.getDescription().trim().isEmpty()) {
            System.err.println("Validation Error: Cultural heritage description cannot be empty.");
            return false;
        }
        if (heritage.getLocation() == null || heritage.getLocation().trim().isEmpty()) {
            System.err.println("Validation Error: Cultural heritage location cannot be empty.");
            return false;
        }
        // Year of origin should be a reasonable number (e.g., not 0, not excessively large future year)
        // Assuming cultural heritage is generally from the past or present.
        if (heritage.getYearOfOrigin() == 0 || heritage.getYearOfOrigin() > 2100 || heritage.getYearOfOrigin() < -5000) { // Arbitrary bounds
            System.err.println("Validation Error: Year of origin is invalid. Must be a non-zero value, not in the far future, and within a reasonable historical range.");
            return false;
        }
        // ID can be null or empty if system generates it, but if user provides, it should be non-empty
        // For this simulation, we allow empty ID, and it's treated as "N/A" in toString.
        // If a strict ID format is required, more validation would be here.
        return true;
    }

    /**
     * Inserts a new cultural heritage object into the system.
     * This method encapsulates the core business logic for insertion.
     *
     * @param heritage The CulturalHeritage object to insert.
     * @return An InsertResult indicating the outcome of the operation.
     */
    public InsertResult insertCulturalHeritage(CulturalHeritage heritage) {
        // 4. Verify the data entered
        if (!isValid(heritage)) {
            // System activates the use case Errored.
            return InsertResult.VALIDATION_ERROR;
        }

        // Check for duplicates before adding
        if (repository.isDuplicate(heritage)) {
            System.err.println("Error: A cultural heritage with the name '" + heritage.getName() + "' already exists.");
            return InsertResult.DUPLICATE_ERROR;
        }

        // Simulate potential server/connection issues (Exit condition: Interruption of the connection to the server ETOUR)
        // For a real system, this would involve network calls and proper exception handling.
        // Here, we'll simulate it with a random chance.
        if (Math.random() < 0.05) { // 5% chance of server error
            System.err.println("Simulated Server Error: Connection to ETOUR server interrupted.");
            return InsertResult.SERVER_ERROR;
        }

        // 6. Memorize your new cultural good.
        if (repository.addCulturalHeritage(heritage)) {
            return InsertResult.SUCCESS;
        } else {
            // This case should ideally not be reached if isDuplicate() is checked first,
            // but serves as a fallback for repository-level duplicate checks.
            return InsertResult.DUPLICATE_ERROR;
        }
    }
}

/**
 * Simulates the console-based user interface for an Agency Operator.
 * Handles user input and displays messages.
 */
class AgencyOperatorConsoleUI {
    private final Scanner scanner;
    private final CulturalHeritageService service;
    private boolean loggedIn = false; // Simulates login status

    /**
     * Constructs a new AgencyOperatorConsoleUI.
     *
     * @param service The CulturalHeritageService to interact with.
     */
    public AgencyOperatorConsoleUI(CulturalHeritageService service) {
        this.scanner = new Scanner(System.in);
        this.service = service;
    }

    /**
     * Simulates the login process for an Agency Operator.
     * For this use case, it's a simple placeholder.
     *
     * @return true if login is successful, false otherwise.
     */
    public boolean simulateLogin() {
        System.out.println("--- Agency Operator Login ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Simple hardcoded login for demonstration
        if ("agency".equals(username) && "password".equals(password)) {
            System.out.println("Login successful. Welcome, Agency Operator!");
            loggedIn = true;
            return true;
        } else {
            System.out.println("Login failed. Invalid credentials.");
            loggedIn = false;
            return false;
        }
    }

    /**
     * Displays the form for cultural heritage data and collects user input.
     * Allows the operator to cancel the operation at any point.
     *
     * @return A CulturalHeritage object with collected data, or null if the operation is canceled.
     */
    private CulturalHeritage getCulturalHeritageData() {
        System.out.println("\n--- Insert New Cultural Heritage ---");
        System.out.println("Enter 'cancel' at any prompt to abort the operation.");

        String id = "";
        String name = "";
        String description = "";
        String location = "";
        int yearOfOrigin = 0;

        // Prompt for ID (optional)
        System.out.print("Enter ID (optional, press Enter to skip): ");
        id = scanner.nextLine().trim();
        if (id.equalsIgnoreCase("cancel")) return null;

        // Prompt for Name
        while (name.isEmpty()) {
            System.out.print("Enter Name (required): ");
            name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("cancel")) return null;
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please try again.");
            }
        }

        // Prompt for Description
        while (description.isEmpty()) {
            System.out.print("Enter Description (required): ");
            description = scanner.nextLine().trim();
            if (description.equalsIgnoreCase("cancel")) return null;
            if (description.isEmpty()) {
                System.out.println("Description cannot be empty. Please try again.");
            }
        }

        // Prompt for Location
        while (location.isEmpty()) {
            System.out.print("Enter Location (required): ");
            location = scanner.nextLine().trim();
            if (location.equalsIgnoreCase("cancel")) return null;
            if (location.isEmpty()) {
                System.out.println("Location cannot be empty. Please try again.");
            }
        }

        // Prompt for Year of Origin
        boolean yearValid = false;
        while (!yearValid) {
            System.out.print("Enter Year of Origin (e.g., 1889, -700 for BC): ");
            String yearInput = scanner.nextLine().trim();
            if (yearInput.equalsIgnoreCase("cancel")) return null;
            try {
                yearOfOrigin = Integer.parseInt(yearInput);
                yearValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid year format. Please enter a valid integer.");
            }
        }

        return new CulturalHeritage(id, name, description, location, yearOfOrigin);
    }

    /**
     * Displays the entered cultural heritage data and asks for operator confirmation.
     *
     * @param heritage The CulturalHeritage object to confirm.
     