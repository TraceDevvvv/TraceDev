import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Provides the core logic for searching for "sites" on the computer.
 * A "site" in this context is interpreted as a file or directory path
 * that matches the search query.
 * This class simulates file system traversal and includes a simulated
 * network interruption (ETOUR error) for demonstration.
 */
public class SiteSearcher {

    private static final double ETOUR_ERROR_PROBABILITY = 0.05; // 5% chance of simulated ETOUR error
    private final Random random = new Random();

    /**
     * Performs a search based on the provided SearchForm.
     *
     * @param form The SearchForm containing the query and optional search path.
     * @return A list of SearchResult objects.
     * @throws RuntimeException if a simulated ETOUR error occurs or other search-related issues.
     */
    public List<SearchResult> search(SearchForm form) {
        // Simulate network interruption (ETOUR error)
        if (random.nextDouble() < ETOUR_ERROR_PROBABILITY) {
            throw new RuntimeException("Simulated ETOUR error: Connection to server interrupted.");
        }

        String query = form.getQuery().toLowerCase();
        String searchPath = form.getSearchPath();
        List<SearchResult> results = new ArrayList<>();

        // Determine the root path for the search
        Path rootPath;
        if (searchPath != null && !searchPath.trim().isEmpty()) {
            rootPath = Paths.get(searchPath);
            if (!Files.exists(rootPath) || !Files.isDirectory(rootPath)) {
                System.err.println("Warning: Specified search path does not exist or is not a directory. Searching default paths.");
                rootPath = null; // Fallback to default search paths
            }
        } else {
            rootPath = null; // Use default search paths
        }

        // Define default search paths if no valid path is provided by the user
        List<Path> pathsToSearch = new ArrayList<>();
        if (rootPath != null) {
            pathsToSearch.add(rootPath);
        } else {
            // Add common system paths for demonstration.
            // In a real application, this would be more sophisticated or user-configurable.
            pathsToSearch.add(Paths.get(System.getProperty("user.home"))); // User's home directory
            // On Windows, you might add C:\, Program Files, etc.
            // On Linux/macOS, you might add /usr/local, /var, etc.
            // For simplicity, we'll just use user.home and potentially the root of the file system.
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                // Example for Windows:
                File[] roots = File.listRoots();
                if (roots.length > 0) {
                    pathsToSearch.add(roots[0].toPath()); // e.g., C:\
                }
            } else {
                // Example for Unix-like systems:
                pathsToSearch.add(Paths.get("/")); // Root directory
            }
        }

        // Perform the file system traversal
        for (Path currentRoot : pathsToSearch) {
            try {
                Files.walkFileTree(currentRoot, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        // Simulate some work to allow for timeout interruption
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new IOException("Search interrupted during directory traversal.", e);
                        }

                        String dirName = dir.getFileName() != null ? dir.getFileName().toString().toLowerCase() : "";
                        if (dirName.contains(query)) {
                            results.add(new SearchResult(dir.toString(), "Directory"));
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        // Simulate some work to allow for timeout interruption
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new IOException("Search interrupted during file traversal.", e);
                        }

                        String fileName = file.getFileName() != null ? file.getFileName().toString().toLowerCase() : "";
                        if (fileName.contains(query)) {
                            results.add(new SearchResult(file.toString(), "File"));
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        // Log the error but continue the search
                        System.err.println("Access denied or other error for: " + file + " - " + exc.getMessage());
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                // If the search was interrupted or another IO error occurred for a root path
                if (e.getCause() instanceof InterruptedException) {
                    Thread.currentThread().interrupt(); // Restore interrupt status
                    throw new RuntimeException("Search was interrupted.", e);
                }
                System.err.println("Error searching path " + currentRoot + ": " + e.getMessage());
            } catch (SecurityException e) {
                System.err.println("Permission denied for path " + currentRoot + ": " + e.getMessage());
            }
        }

        // Sort results for consistent output (optional)
        Collections.sort(results, (r1, r2) -> r1.getPath().compareToIgnoreCase(r2.getPath()));

        return results;
    }
}