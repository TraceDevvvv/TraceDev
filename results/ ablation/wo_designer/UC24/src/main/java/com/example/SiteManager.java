import java.util.*;

public class SiteManager {
    private static List<Site> sites = new ArrayList<>();
    
    // Static initializer to create sample sites
    static {
        sites.add(new Site("S001", "Site A", "Location A"));
        sites.add(new Site("S002", "Site B", "Location B"));
        sites.add(new Site("S003", "Site C", "Location C"));
    }
    
    // Simulates site search functionality
    public static List<Site> searchSites(String searchTerm) {
        List<Site> results = new ArrayList<>();
        for (Site site : sites) {
            if (site.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(site);
            }
        }
        return results;
    }
    
    public static Site getSiteById(String siteId) {
        for (Site site : sites) {
            if (site.getSiteId().equals(siteId)) {
                return site;
            }
        }
        return null;
    }
}