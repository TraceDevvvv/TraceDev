import java.util.ArrayList;
import java.util.List;
/**
 * This class manages the archive of teachings (simulated database).
 * It handles insertion and retrieval of teaching records.
 */
public class DatabaseManager {
    private static List<Teaching> teachingsArchive = new ArrayList<>();
    private static boolean serverConnected = true;
    /**
     * Simulates inserting a teaching into the archive.
     * Validates the teaching and checks server connection.
     *
     * @param teaching The teaching to insert
     * @return true if insertion successful, false otherwise
     * @throws Exception if server connection is interrupted
     */
    public static boolean insertTeaching(Teaching teaching) throws Exception {
        if (!serverConnected) {
            throw new Exception("Connection to the SMOS server interrupted");
        }
        if (teaching == null || !teaching.isValid()) {
            return false;
        }
        teachingsArchive.add(teaching);
        return true;
    }
    /**
     * Retrieves all teachings from the archive.
     *
     * @return list of all teachings
     */
    public static List<Teaching> getAllTeachings() {
        return new ArrayList<>(teachingsArchive);
    }
    /**
     * Simulates server connection status.
     *
     * @param connected true if server is connected, false otherwise
     */
    public static void setServerConnected(boolean connected) {
        serverConnected = connected;
    }
    /**
     * Gets the current teachings count.
     *
     * @return number of teachings in archive
     */
    public static int getTeachingsCount() {
        return teachingsArchive.size();
    }
}