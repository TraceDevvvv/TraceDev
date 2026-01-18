package com.example;

public class SearchCulturalHeritageForm {
    private SearchCriteriaDTO searchCriteria;

    public SearchCriteriaDTO getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteriaDTO searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public void submitSearch() {
        if (searchCriteria == null) {
            System.out.println("Error: No search criteria provided");
            return;
        }

        SearchController controller = new SearchController();
        try {
            CulturalObjectDTO[] results = controller.search(searchCriteria);
            displayResults(results);
        } catch (RuntimeException e) {
            System.out.println("Error during search: " + e.getMessage());
        }
    }

    public void displayResults(CulturalObjectDTO[] results) {
        if (results == null || results.length == 0) {
            System.out.println("No results found.");
            return;
        }
        System.out.println("Search Results:");
        for (CulturalObjectDTO result : results) {
            System.out.println(" - " + result);
        }
    }
}