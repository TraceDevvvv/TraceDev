/**
 * Service class for handling preference storage and retrieval.
 * Simulates interaction with a backend server/database.
 */
package modifygenericpreference;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
public class PreferenceService {
    private static final String PREFERENCES_FILE = "tourist_preferences.dat";
    private Map<String, Preferences> preferencesCache;
    public PreferenceService() {
        preferencesCache = new HashMap<>();
        loadPreferencesFromFile();
    }
    @SuppressWarnings("unchecked")
    private void loadPreferencesFromFile() {
        File file = new File(PREFERENCES_FILE);
        if (!file.exists()) {
            System.out.println("No preferences file found. Using empty cache.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                preferencesCache = (Map<String, Preferences>) obj;
                System.out.println("Preferences loaded from file: " + preferencesCache.size() + " entries");
                preferencesCache.entrySet().removeIf(entry -> 
                    entry.getKey() == null || entry.getValue() == null);
            } else {
                System.err.println("Invalid data format in preferences file. Using empty cache.");
                preferencesCache = new HashMap<>();
            }
        } catch (InvalidClassException | ClassNotFoundException e) {
            System.err.println("Serialization version mismatch: " + e.getMessage());
            preferencesCache = new HashMap<>();
        } catch (IOException e) {
            System.err.println("Error loading preferences: " + e.getMessage());
            preferencesCache = new HashMap<>();
        } catch (ClassCastException e) {
            System.err.println("Data corruption detected in preferences file: " + e.getMessage());
            preferencesCache = new HashMap<>();
        }
    }
    private void savePreferencesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PREFERENCES_FILE))) {
            oos.writeObject(preferencesCache);
            System.out.println("Preferences saved to file");
        } catch (IOException e) {
            System.err.println("Error saving preferences: " + e.getMessage());
        }
    }
    public Preferences getPreferences(String touristUsername) {
        Preferences prefs = preferencesCache.get(touristUsername);
        if (prefs == null) {
            prefs = new Preferences();
            preferencesCache.put(touristUsername, prefs);
        }
        return prefs;
    }
    public boolean updatePreferences(String touristUsername, Preferences preferences) {
        if (touristUsername == null || preferences == null) {
            return false;
        }
        try {
            System.out.println("Connecting to ETOUR server...");
            Thread.sleep(500);
            if (Math.random() < 0.05) {
                throw new IOException("Connection to server ETOUR interrupted");
            }
            preferencesCache.put(touristUsername, preferences);
            savePreferencesToFile();
            System.out.println("Preferences updated for user: " + touristUsername);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Operation interrupted: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
            return false;
        }
    }
    public boolean checkServerConnection() {
        try {
            System.out.println("Checking connection to ETOUR server...");
            Thread.sleep(200);
            return Math.random() >= 0.1;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}