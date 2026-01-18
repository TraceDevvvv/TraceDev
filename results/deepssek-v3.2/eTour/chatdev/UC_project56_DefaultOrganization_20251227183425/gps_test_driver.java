/**
 * This class is an optional test driver to verify the GPSProvider behavior without the GUI.
 * It is not required for the main application but can be used for testing.
 */
class GPSTestDriver {
    public static void main(String[] args) {
        GPSProvider gps = new GPSProvider();
        try {
            System.out.println("Requesting GPS position...");
            GPSPosition pos = gps.calculatePosition();
            System.out.printf("Position acquired: Latitude=%.6f, Longitude=%.6f, Accuracy=%.2fm%n",
                    pos.getLatitude(), pos.getLongitude(), pos.getAccuracy());
        } catch (GPSException e) {
            System.err.println("GPS Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}