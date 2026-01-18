'''
DatabaseSimulator.java
Simulates a simple in-memory database for Justification objects.
Provides thread-safe methods to retrieve, update, and list justifications.
'''
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class DatabaseSimulator {
    private static final Map<Integer, Justification> justificationDB = new ConcurrentHashMap<>();
    static {
        justificationDB.put(1, new Justification(1, LocalDate.of(2023, 10, 15), 
                               "Initial justification sample."));
        justificationDB.put(2, new Justification(2, LocalDate.of(2023, 10, 20), 
                               "Another justification entry."));
    }
    public static Justification getJustification(int id) {
        return justificationDB.get(id);
    }
    public static boolean updateJustification(Justification justification) {
        if (justification == null) {
            return false;
        }
        justificationDB.put(justification.getId(), justification);
        return true;
    }
    public static Map<Integer, Justification> getAllJustifications() {
        return new HashMap<>(justificationDB);
    }
    public static boolean deleteJustification(int id) {
        return justificationDB.remove(id) != null;
    }
    public static boolean containsJustification(int id) {
        return justificationDB.containsKey(id);
    }
}