package com.etour.search;

/**
 * Main class to demonstrate the system functionality.
 * This class simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== ETOUR Search System Demonstration ===\n");
        
        // 1. Create serv and components
        AuthenticationService authService = new AuthenticationService();
        GeolocationService geoService = new GeolocationService();
        ETOURSiteRepository siteRepo = new ETOURSiteRepository();
        SearchService searchService = new SearchService(geoService, siteRepo);
        SearchController controller = new SearchController(searchService, authService);
        SearchForm searchForm = new SearchForm(controller);
        
        // 2. Create a guest user
        GuestUser guestUser = new GuestUser("user123", "session456");
        
        // 3. Entry condition check: user must be logged on
        System.out.println("1. Entry condition check:");
        boolean isLoggedIn = authService.isLoggedIn(guestUser);
        System.out.println("   User is logged in: " + isLoggedIn + "\n");
        
        if (!isLoggedIn) {
            System.out.println("User must be logged in to search. Exiting.");
            return;
        }
        
        // 4. Main flow: activate search
        System.out.println("2. Main search flow:");
        searchForm.activateSearch();
        
        // 5. Fill form with search criteria
        SearchCriteria criteria = new SearchCriteria();
        criteria.setKeywords("Roman ruins");
        criteria.setCategory("ARCHAEOLOGICAL");
        criteria.setRange(25.0);
        
        searchForm.fillForm(criteria);
        
        System.out.println("\n3. Alternative flow: connection interrupted");
        // Simulate connection error by temporarily disabling repository connection
        siteRepo.setConnection(null);
        
        try {
            // This should trigger connection error
            controller.processFormSubmission(criteria);
        } catch (SearchError e) {
            System.out.println("   Expected error caught: " + e.getMessage());
        }
        
        // Restore connection
        siteRepo.setConnection("ETOUR_DB_CONNECTION");
        
        System.out.println("\n4. Quality requirement: availability check");
        // 6. Availability requirement check
        boolean systemAvailable = searchForm.availabilityCheck();
        System.out.println("   Overall system availability: " + systemAvailable);
        
        System.out.println("\n=== Demonstration Complete ===");
    }
}