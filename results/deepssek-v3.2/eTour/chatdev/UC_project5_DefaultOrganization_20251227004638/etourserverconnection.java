"""
Simulates connection to ETOUR server with interruption handling.
Handles loading of cultural heritage data and connection errors.
"""
/**
 * Simulates connection to ETOUR server with interruption handling.
 */
class ETOURServerConnection {
    private boolean connected = true;
    /**
     * Searches for cultural heritage items on the server.
     * @return List of cultural heritage items
     * @throws ServerConnectionException if connection is interrupted
     */
    public List<CulturalHeritage> searchCulturalHeritage() throws ServerConnectionException {
        checkConnection();  // Verify server connectivity
        // Simulate server delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Return mock data for demonstration
        return createMockData();
    }
    /**
     * Checks if connection to server is active.
     * Simulates random connection interruptions.
     */
    private void checkConnection() throws ServerConnectionException {
        // Simulate random connection failure (15% chance for demonstration)
        if (Math.random() < 0.15) {
            connected = false;
            throw new ServerConnectionException("ETOUR server connection lost");
        }
        connected = true;
    }
    /**
     * Creates mock cultural heritage data for demonstration.
     */
    private List<CulturalHeritage> createMockData() {
        List<CulturalHeritage> mockData = new ArrayList<>();
        // Sample data 1
        List<String> images1 = new ArrayList<>();
        images1.add("GreatWall_1.jpg");
        images1.add("GreatWall_2.jpg");
        mockData.add(new CulturalHeritage(
            "CH001",
            "Great Wall of China",
            "Historical Monument",
            "Northern China",
            "7th century BC",
            "Protected",
            images1,
            "Series of fortifications made of stone, brick, tamped earth, wood...",
            "One of the most impressive architectural feats in history...",
            "Well preserved with ongoing conservation efforts"
        ));
        // Sample data 2
        List<String> images2 = new ArrayList<>();
        images2.add("Pyramids_1.jpg");
        mockData.add(new CulturalHeritage(
            "CH002",
            "Pyramids of Giza",
            "Ancient Monument",
            "Giza, Egypt",
            "2580–2560 BC",
            "UNESCO World Heritage",
            images2,
            "Ancient pyramid complex including the Great Pyramid...",
            "Last surviving wonder of the ancient world...",
            "Stable with tourism management"
        ));
        return mockData;
    }
}