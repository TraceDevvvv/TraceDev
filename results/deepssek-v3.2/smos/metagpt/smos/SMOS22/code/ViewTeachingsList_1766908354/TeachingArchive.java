import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.logging.*;

/**
 * TeachingArchive class handles reading teaching entries from a file.
 * It includes methods to load teachings from a file, handle various edge cases,
 * and simulate SMOS server connection interruption.
 */
public class TeachingArchive {
    private static final Logger logger = Logger.getLogger(TeachingArchive.class.getName());
    
    private String archiveFilePath;
    private List<Teaching> teachings;
    private boolean connectionInterrupted;
    
    /**
     * Constructor with default file path.
     */
    public TeachingArchive() {
        this("teachings_archive.txt");
    }
    
    /**
     * Constructor with custom file path.
     * 
     * @param archiveFilePath path to the teachings archive file
     */
    public TeachingArchive(String archiveFilePath) {
        this.archiveFilePath = archiveFilePath;
        this.teachings = new ArrayList<>();
        this.connectionInterrupted = false;
        setupLogger();
    }
    
    /**
     * Sets up the logger for logging errors and warnings.
     */
    private void setupLogger() {
        try {
            LogManager.getLogManager().reset();
            logger.setLevel(Level.ALL);
            
            ConsoleHandler handler = new ConsoleHandler();
            handler.setLevel(Level.WARNING);
            handler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord record) {
                    return String.format("[%s] %s: %s%n",
                            record.getLevel(),
                            record.getLoggerName(),
                            record.getMessage());
                }
            });
            logger.addHandler(handler);
        } catch (Exception e) {
            System.err.println("Failed to set up logger: " + e.getMessage());
        }
    }
    
    /**
     * Loads teachings from the archive file.
     * Handles edge cases: file not found, empty file, malformed lines, etc.
     * 
     * @return true if teachings were loaded successfully, false otherwise
     */
    public boolean loadTeachings() {
        teachings.clear();
        
        // Simulate SMOS server connection interruption with 10% probability
        if (simulateConnectionInterruption()) {
            connectionInterrupted = true;
            logger.severe("SMOS server connection interrupted. Cannot load teachings.");
            return false;
        }
        
        try {
            Path path = Paths.get(archiveFilePath);
            
            // Check if file exists
            if (!Files.exists(path)) {
                logger.severe("Archive file not found: " + archiveFilePath);
                return false;
            }
            
            // Check if file is empty
            if (Files.size(path) == 0) {
                logger.warning("Archive file is empty: " + archiveFilePath);
                return true; // Return true but with empty list
            }
            
            // Read all lines from file
            List<String> lines = Files.readAllLines(path);
            int lineNumber = 0;
            int successfullyParsed = 0;
            
            for (String line : lines) {
                lineNumber++;
                
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    logger.info("Skipping empty line at line " + lineNumber);
                    continue;
                }
                
                try {
                    Teaching teaching = parseTeachingLine(line, lineNumber);
                    if (teaching != null) {
                        teachings.add(teaching);
                        successfullyParsed++;
                    }
                } catch (Exception e) {
                    logger.warning("Failed to parse line " + lineNumber + ": " + line + 
                                 " - Error: " + e.getMessage());
                }
            }
            
            logger.info("Successfully loaded " + successfullyParsed + " teaching(s) from " + 
                       lineNumber + " line(s) in the archive.");
            
            if (successfullyParsed == 0) {
                logger.warning("No valid teaching entries found in the archive.");
            }
            
            return true;
            
        } catch (IOException e) {
            logger.severe("Error reading archive file: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Parses a single line from the archive file into a Teaching object.
     * Expected format: subject|teacher|date|description
     * 
     * @param line the line to parse
     * @param lineNumber line number for error reporting
     * @return Teaching object or null if parsing fails
     */
    private Teaching parseTeachingLine(String line, int lineNumber) {
        try {
            String[] parts = line.split("\\|", -1); // -1 to keep trailing empty strings
            
            if (parts.length != 4) {
                logger.warning("Invalid format at line " + lineNumber + 
                             ": Expected 4 fields separated by '|', got " + parts.length);
                return null;
            }
            
            String subject = parts[0].trim();
            String teacher = parts[1].trim();
            String dateStr = parts[2].trim();
            String description = parts[3].trim();
            
            // Validate required fields
            if (subject.isEmpty()) {
                logger.warning("Empty subject at line " + lineNumber);
                return null;
            }
            
            if (teacher.isEmpty()) {
                logger.warning("Empty teacher at line " + lineNumber);
                return null;
            }
            
            // Parse and validate date
            LocalDate date;
            try {
                date = LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                logger.warning("Invalid date format at line " + lineNumber + 
                             ": " + dateStr + " - Expected yyyy-MM-dd");
                return null;
            }
            
            return new Teaching(subject, teacher, date, description);
            
        } catch (Exception e) {
            logger.warning("Unexpected error parsing line " + lineNumber + ": " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Simulates SMOS server connection interruption.
     * Returns true with 10% probability to simulate random interruption.
     * 
     * @return true if connection is interrupted, false otherwise
     */
    private boolean simulateConnectionInterruption() {
        Random random = new Random();
        return random.nextDouble() < 0.1; // 10% chance of interruption
    }
    
    /**
     * Returns the list of loaded teachings.
     * 
     * @return list of Teaching objects
     */
    public List<Teaching> getTeachings() {
        return new ArrayList<>(teachings); // Return a copy to prevent external modification
    }
    
    /**
     * Returns whether the SMOS server connection was interrupted during the last load operation.
     * 
     * @return true if connection was interrupted, false otherwise
     */
    public boolean isConnectionInterrupted() {
        return connectionInterrupted;
    }
    
    /**
     * Displays all teachings in a formatted manner.
     */
    public void displayTeachings() {
        if (teachings.isEmpty()) {
            System.out.println("No teachings available in the archive.");
            return;
        }
        
        System.out.println("\n=== TEACHINGS ARCHIVE ===");
        System.out.println("Total teachings: " + teachings.size());
        System.out.println("=========================\n");
        
        for (int i = 0; i < teachings.size(); i++) {
            Teaching teaching = teachings.get(i);
            System.out.printf("%d. %s%n", i + 1, teaching.toString());
        }
        
        System.out.println("\n=========================\n");
    }
    
    /**
     * Gets the count of teachings in the archive.
     * 
     * @return number of teachings
     */
    public int getTeachingCount() {
        return teachings.size();
    }
    
    /**
     * Resets the connection interrupted flag.
     */
    public void resetConnectionStatus() {
        connectionInterrupted = false;
    }
    
    /**
     * Main method for testing the TeachingArchive class independently.
     * 
     * @param args command line arguments (optional filename)
     */
    public static void main(String[] args) {
        String filename = args.length > 0 ? args[0] : "teachings_archive.txt";
        TeachingArchive archive = new TeachingArchive(filename);
        
        System.out.println("Loading teachings from: " + filename);
        
        if (archive.loadTeachings()) {
            System.out.println("Teachings loaded successfully!");
            archive.displayTeachings();
        } else {
            System.out.println("Failed to load teachings.");
            if (archive.isConnectionInterrupted()) {
                System.out.println("Reason: SMOS server connection interrupted.");
            }
        }
    }
}