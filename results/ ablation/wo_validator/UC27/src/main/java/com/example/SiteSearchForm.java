package com.example;

/**
 * Represents the UI form for site search.
 * Simulates user interaction as per sequence diagram.
 */
public class SiteSearchForm {

    public void displayForm() {
        System.out.println("Displaying site search form...");
    }

    /**
     * Submits the search form by creating a request and calling controller.
     * @param searchData the search request data
     */
    public void submitForm(SiteSearchRequest searchData) {
        System.out.println("Submitting search form...");
        SiteSearchController controller = new SiteSearchController();
        SiteSearchResponse response = controller.searchSite(searchData);
        displayResults(response);
    }

    public void displayResults(SiteSearchResponse response) {
        System.out.println("Displaying search results...");
        System.out.println("Status: " + response.getStatus());
        System.out.println("Message: " + response.getMessage());
        if (response.getSites() != null && !response.getSites().isEmpty()) {
            System.out.println("Sites found: " + response.getSites().size());
            for (Site site : response.getSites()) {
                System.out.println(" - " + site.getName() + " at " + site.getLocation());
            }
        } else {
            System.out.println("No sites to display.");
        }
    }

    /**
     * Main method to simulate the sequence diagram flow.
     */
    public static void main(String[] args) {
        SiteSearchForm form = new SiteSearchForm();

        // Step 1 & 2: User activates search functionality, form displays
        form.displayForm();

        // Step 3 & 4: User fills and submits form
        SiteSearchRequest request = new SiteSearchRequest();
        request.setSearchTerm("Office");
        SearchCriteria criteria = new SearchCriteria();
        criteria.setSearchTerm("Office");
        criteria.addFilter("type", "Commercial");
        request.setSearchCriteria(criteria);

        form.submitForm(request);
    }
}