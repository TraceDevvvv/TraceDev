'''
MockServer.java
Simulates the ETOUR server with mock data for demonstration.
Handles connection interruptions as specified in use case.
'''
package culturalheritage.search;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class MockServer {
    private boolean connected = false;
    private Random random = new Random();
    /**
     * Simulate connection to ETOUR server
     * @return true if connection successful, false if interrupted
     * @throws InterruptedException if thread is interrupted during connection
     */
    public boolean connect() throws InterruptedException {
        // Simulate connection delay
        Thread.sleep(500);
        // 10% chance of connection interruption (simulating the use case requirement)
        if (random.nextInt(10) == 0) { // 10% chance
            connected = false;
            return false;
        }
        connected = true;
        return true;
    }
    /**
     * Disconnect from server
     */
    public void disconnect() {
        connected = false;
    }
    /**
     * Get all available cultural heritage sites (mock data)
     * @throws IllegalStateException if not connected to server
     */
    public List<Site> getAllSites() {
        if (!connected) {
            throw new IllegalStateException("Not connected to server");
        }
        List<Site> sites = new ArrayList<>();
        // Add mock cultural heritage sites
        sites.add(new Site("1", "British Museum", 
            "One of the world's largest and most comprehensive museums, housing a vast collection of world art and artifacts.",
            "Museum", "London, UK", 4.7, 6500000));
        sites.add(new Site("2", "Acropolis of Athens", 
            "An ancient citadel located on a rocky outcrop above Athens, containing several ancient buildings of great architectural significance.",
            "Archaeological Site", "Athens, Greece", 4.9, 2500000));
        sites.add(new Site("3", "Louvre Museum", 
            "World's largest art museum and historic monument in Paris, home to the Mona Lisa and Venus de Milo.",
            "Museum", "Paris, France", 4.8, 10200000));
        sites.add(new Site("4", "Machu Picchu", 
            "15th-century Inca citadel located in the Eastern Cordillera of southern Peru, often referred to as the 'Lost City of the Incas'.",
            "Archaeological Site", "Cusco Region, Peru", 4.9, 1500000));
        sites.add(new Site("5", "Colosseum", 
            "An oval amphitheatre in the centre of the city of Rome, the largest ancient amphitheatre ever built.",
            "Monument", "Rome, Italy", 4.8, 7500000));
        sites.add(new Site("6", "National Museum of China", 
            "One of the largest museums in the world, covering Chinese history from 1.7 million years ago to the end of the Qing dynasty.",
            "Museum", "Beijing, China", 4.6, 8600000));
        sites.add(new Site("7", "Taj Mahal", 
            "An ivory-white marble mausoleum on the right bank of the river Yamuna in Agra, commissioned by Mughal emperor Shah Jahan.",
            "Monument", "Agra, India", 4.9, 8000000));
        sites.add(new Site("8", "Metropolitan Museum of Art", 
            "The largest art museum in the Americas, with a collection of over 2 million works divided among 17 curatorial departments.",
            "Art Gallery", "New York, USA", 4.7, 7200000));
        sites.add(new Site("9", "Pyramids of Giza", 
            "The last of the Seven Wonders of the Ancient World still in existence, consisting of three pyramids and the Great Sphinx.",
            "Archaeological Site", "Giza, Egypt", 4.9, 14000000));
        sites.add(new Site("10", "Hermitage Museum", 
            "The second-largest art museum in the world, founded in 1764 by Catherine the Great and housing over 3 million items.",
            "Museum", "Saint Petersburg, Russia", 4.7, 4400000));
        sites.add(new Site("11", "Kyoto Imperial Palace", 
            "The former ruling palace of the Emperor of Japan, located in the Kyoto Imperial Park in Kyoto.",
            "Historic Building", "Kyoto, Japan", 4.5, 1200000));
        sites.add(new Site("12", "Uffizi Gallery", 
            "One of the most important Italian museums and the most visited, famous for its outstanding collections of Ancient sculptures and paintings.",
            "Art Gallery", "Florence, Italy", 4.8, 2300000));
        sites.add(new Site("13", "Stonehenge", 
            "A prehistoric monument consisting of a ring of standing stones, each around 13 feet high, seven feet wide, and weighing around 25 tons.",
            "Archaeological Site", "Wiltshire, England", 4.4, 1300000));
        sites.add(new Site("14", "Prado Museum", 
            "The main Spanish national art museum, housing one of the world's finest collections of European art from the 12th to early 20th centuries.",
            "Museum", "Madrid, Spain", 4.7, 3200000));
        sites.add(new Site("15", "Angkor Wat", 
            "A temple complex in Cambodia and the largest religious monument in the world, originally constructed as a Hindu temple dedicated to Vishnu.",
            "Cultural Landscape", "Siem Reap, Cambodia", 4.9, 2600000));
        return sites;
    }
}