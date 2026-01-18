package com.culturalheritage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the SearchCulturalHeritage use case.
 * This class verifies the functionality of the search system including
 * normal operations, edge cases, error handling, and performance requirements.
 */
public class SearchCulturalHeritageTest {
    
    private SearchService searchService;
    private List<CulturalObject> testData;
    
    /**
     * Sets up test data before each test.
     */
    @BeforeEach
    public void setUp() {
        testData = new ArrayList<>();
        
        // Create test cultural objects
        testData.add(new CulturalObject("TEST-001", "Test Painting", "Painting", "Renaissance", 
                "Test Museum", "A test painting", Year.of(1500), "Italy", true));
        testData.add(new CulturalObject("TEST-002", "Ancient Vase", "Artifact", "Ancient", 
                "Archaeological Site", "Ancient ceramic vase", Year.of(-500), "Greece", true));
        testData.add(new CulturalObject("TEST-003", "Modern Sculpture", "Sculpture", "Modern", 
                "Art Gallery", "Contemporary art piece", Year.of(2020), "USA", false));
        testData.add(new CulturalObject("TEST-004", "Local Artifact", "Artifact", "Medieval", 
                "Local Museum", "Local historical artifact", Year.of(1200), "France", true));
        testData.add(new CulturalObject("TEST-005", "Another Painting", "Painting", "Modern", 
                "Modern Art Museum", "Abstract painting", Year.of(1950), "Spain", false));
        
        searchService = new SearchService(testData);
    }
    
    /**
     * Cleans up resources after each test.
     */
    @AfterEach
    public void tearDown() {
        if (searchService != null) {
            searchService.shutdown();
        }
    }
    
    /**
     * Tests basic search functionality by name.
     */
    @Test
    public void testSearchByName() {
        try {
            SearchForm form = new SearchForm("Painting", null, null, null);
            List<CulturalObject> results = searchService.search(form);
            
            assertEquals(2, results.size());
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-001")));
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-005")));
        } catch (SearchService.SearchException e) {
            fail("Search should not throw exception for valid criteria: " + e.getMessage());
        }
    }
    
    /**
     * Tests search by type.
     */
    @Test
    public void testSearchByType() {
        try {
            SearchForm form = new SearchForm(null, "Artifact", null, null);
            List<CulturalObject> results = searchService.search(form);
            
            assertEquals(2, results.size());
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-002")));
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-004")));
        } catch (SearchService.SearchException e) {
            fail("Search should not throw exception for valid criteria: " + e.getMessage());
        }
    }
    
    /**
     * Tests search by era.
     */
    @Test
    public void testSearchByEra() {
        try {
            SearchForm form = new SearchForm(null, null, "Modern", null);
            List<CulturalObject> results = searchService.search(form);
            
            assertEquals(2, results.size());
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-003")));
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-005")));
        } catch (SearchService.SearchException e) {
            fail("Search should not throw exception for valid criteria: " + e.getMessage());
        }
    }
    
    /**
     * Tests search by location.
     */
    @Test
    public void testSearchByLocation() {
        try {
            SearchForm form = new SearchForm(null, null, null, "Museum");
            List<CulturalObject> results = searchService.search(form);
            
            assertEquals(3, results.size());
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-001")));
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-004")));
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-005")));
        } catch (SearchService.SearchException e) {
            fail("Search should not throw exception for valid criteria: " + e.getMessage());
        }
    }
    
    /**
     * Tests case-insensitive search.
     */
    @Test
    public void testCaseInsensitiveSearch() {
        try {
            SearchForm form = new SearchForm("painting", null, null, null);
            List<CulturalObject> results = searchService.search(form);
            
            assertEquals(2, results.size());
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-001")));
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-005")));
        } catch (SearchService.SearchException e) {
            fail("Search should not throw exception for valid criteria: " + e.getMessage());
        }
    }
    
    /**
     * Tests partial name search.
     */
    @Test
    public void testPartialNameSearch() {
        try {
            SearchForm form = new SearchForm("Ancient", null, null, null);
            List<CulturalObject> results = searchService.search(form);
            
            assertEquals(1, results.size());
            assertEquals("TEST-002", results.get(0).getId());
        } catch (SearchService.SearchException e) {
            fail("Search should not throw exception for valid criteria: " + e.getMessage());
        }
    }
    
    /**
     * Tests search with multiple criteria.
     */
    @Test
    public void testSearchWithMultipleCriteria() {
        try {
            SearchForm form = new SearchForm(null, "Painting", "Modern", null);
            List<CulturalObject> results = searchService.search(form);
            
            assertEquals(1, results.size());
            assertEquals("TEST-005", results.get(0).getId());
        } catch (SearchService.SearchException e) {
            fail("Search should not throw exception for valid criteria: " + e.getMessage());
        }
    }
    
    /**
     * Tests search by year range.
     */
    @Test
    public void testSearchByYearRange() {
        try {
            SearchForm form = new SearchForm();
            form.setMinYear(Year.of(1000));
            form.setMaxYear(Year.of(2000));
            List<CulturalObject> results = searchService.search(form);
            
            assertEquals(2, results.size());
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-001")));
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-004")));
        } catch (SearchService.SearchException e) {
            fail("Search should not throw exception for valid criteria: " + e.getMessage());
        }
    }
    
    /**
     * Tests search by protected status.
     */
    @Test
    public void testSearchByProtectedStatus() {
        try {
            SearchForm form = new SearchForm();
            form.setIsProtected(true);
            List<CulturalObject> results = searchService.search(form);
            
            assertEquals(3, results.size());
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-001")));
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-002")));
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-004")));
            
            form.setIsProtected(false);
            results = searchService.search(form);
            
            assertEquals(2, results.size());
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-003")));
            assertTrue(results.stream().anyMatch(obj -> obj.getId().equals("TEST-005")));
        } catch (SearchService.SearchException e) {
            fail("Search should not throw exception for valid criteria: " + e.getMessage());
        }
    }
    
    /**
     * Tests empty search (should return all objects).
     */
    @Test
    public void testEmptySearch() {
        try {
            SearchForm form = new SearchForm();
            List<CulturalObject> results = searchService.search(form);
            
            assertEquals(5, results.size());
        } catch (SearchService.SearchException e) {
            fail("Empty search should not throw exception: " + e.getMessage());
        }
    }
    
    /**
     * Tests search with no matching results.
     */
    @Test
    public void testSearchWithNoResults() {
        try {
            SearchForm form = new SearchForm("Nonexistent", null, null, null);
            List<CulturalObject> results = searchService.search(form);
            
            assertTrue(results.isEmpty());
        } catch (SearchService.SearchException e) {
            fail("Search should not throw exception for no results: " + e.getMessage());
        }
    }
    
    /**
     * Tests search form validation with invalid year range.
     */
    @Test
    public void testInvalidYearRange() {
        SearchForm form = new SearchForm();
        form.setMinYear(Year.of(2000));
        form.setMaxYear(Year.of(1000));
        
        assertFalse(form.isValid());
    }
    
    /**
     * Tests search with invalid form (should throw exception).
     */
    @Test
    public void testSearchWithInvalidForm() {
        SearchForm form = new SearchForm();
        form.setMinYear(Year.of(2000));
        form.setMaxYear(Year.of(1000));
        
        SearchService.SearchException exception = assertThrows(
            SearchService.SearchException.class,
            () -> searchService.search(form)
        );
        
        assertTrue(exception.getMessage().contains("Invalid search criteria"));
    }
    
    /**
     * Tests that search completes within timeout (quality requirement).
     */
    @Test
    @Timeout(value = 6, unit = TimeUnit.SECONDS)
    public void testSearchWithinTimeout() {
        try {
            SearchForm form = new SearchForm();
            List<CulturalObject> results = searchService.search(form, 1000); // 1 second timeout
            
            assertEquals(5, results.size());
        } catch (SearchService.SearchException e) {
            fail("Search should complete within timeout: " + e.getMessage());
        }
    }
    
    /**
     * Tests timeout scenario by using a very short timeout.
     */
    @Test
    public void testSearchTimeout() {
        try {
            // Create a service with many objects to potentially cause delay
            List<CulturalObject> largeDataset = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                largeDataset.add(new CulturalObject("ID-" + i, "Object " + i, "Type", "Era", "Location"));
            }
            SearchService slowService = new SearchService(largeDataset);
            
            // Use very short timeout (1ms) to force timeout
            SearchForm form = new SearchForm();
            SearchService.SearchException exception = assertThrows(
                SearchService.SearchException.class,
                () -> slowService.search(form, 1)
            );
            
            assertTrue(exception.getMessage().contains("timed out"));
            
            slowService.shutdown();
        } catch (Exception e) {
            fail("Timeout test should not fail: " + e.getMessage());
        }
    }
    
    /**
     * Tests the CulturalObject matchesCriteria method.
     */
    @Test
    public void testCulturalObjectMatchesCriteria() {
        CulturalObject obj = new CulturalObject("TEST-001", "Test Painting", "Painting", "Renaissance", 
                "Test Museum", "Description", Year.of(1500), "Italy", true);
        
        assertTrue(obj.matchesCriteria("Painting", null, null, null));
        assertTrue(obj.matchesCriteria("test", null, null, null));
        assertTrue(obj.matchesCriteria(null, "Painting", null, null));
        assertTrue(obj.matchesCriteria(null, null, "Renaissance", null));
        assertTrue(obj.matchesCriteria(null, null, null, "Museum"));
        assertTrue(obj.matchesCriteria("Painting", "Painting", "Renaissance", "Museum"));
        assertFalse(obj.matchesCriteria("Sculpture", null, null, null));
        assertFalse(obj.matchesCriteria(null, "Sculpture", null, null));
        assertFalse(obj.matchesCriteria(null, null, "Modern", null));
        assertFalse(obj.matchesCriteria(null, null, null, "Gallery"));
    }
    
    /**
     * Tests SearchForm isEmpty method.
     */
    @Test
    public void testSearchFormIsEmpty() {
        SearchForm form1 = new SearchForm();
        assertTrue(form1.isEmpty());
        
        SearchForm form2 = new SearchForm("Name", null, null, null);
        assertFalse(form2.isEmpty());
        
        SearchForm form3 = new SearchForm(null, "Type", null, null);
        assertFalse(form3.isEmpty());
        
        SearchForm form4 = new SearchForm();
        form4.setMinYear(Year.of(2000));
        assertFalse(form4.isEmpty());
    }
    
    /**
     * Tests SearchForm isValid method.
     */
    @Test
    public void testSearchFormIsValid() {
        SearchForm form1 = new SearchForm();
        assertTrue(form1.isValid());
        
        SearchForm form2 = new SearchForm();
        form2.setMinYear(Year.of(1000));
        form2.setMaxYear(Year.of(2000));
        assertTrue(form2.isValid());
        
        SearchForm form3 = new SearchForm();
        form3.setMinYear(Year.of(2000));
        form3.setMaxYear(Year.of(1000));
        assertFalse(form3.isValid());
    }
    
    /**
     * Tests command line argument parsing in SearchForm.
     */
    @Test
    public void testCommandLineParsing() {
        String[] args = {
            "--name", "Mona Lisa",
            "--type", "Painting",
            "--era", "Renaissance",
            "--location", "Paris",
            "--country", "Italy",
            "--minYear", "1500",
            "--maxYear", "1600",
            "--protected", "true"
        };
        
        SearchForm form = SearchForm.fromCommandLineArgs(args);
        
        assertEquals("Mona Lisa", form.getName());
        assertEquals("Painting", form.getType());
        assertEquals("Renaissance", form.getEra());
        assertEquals("Paris", form.getLocation());
        assertEquals("Italy", form.getCountryOfOrigin());
        assertEquals(Year.of(1500), form.getMinYear());
        assertEquals(Year.of(1600), form.getMaxYear());
        assertTrue(form.getIsProtected());
    }
    
    /**
     * Tests that search service properly handles server interruption simulation.
     * This tests the ETOUR scenario indirectly.
     */
    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    public void testSearchCompletesDespitePotentialServerDelay() {
        try {
            SearchForm form = new SearchForm();
            
            // Run search multiple times to potentially trigger server delay simulation
            for (int i = 0; i < 20; i++) {
                List<CulturalObject> results = searchService.search(form);
                assertEquals(5, results.size());
            }
        } catch (SearchService.SearchException e) {
            fail("Search should complete despite potential server delays: " + e.getMessage());
        }
    }
    
    /**
     * Tests the equals and hashCode methods of CulturalObject.
     */
    @Test
    public void testCulturalObjectEqualsHashCode() {
        CulturalObject obj1 = new CulturalObject("ID-1", "Name", "Type", "Era", "Location");
        CulturalObject obj2 = new CulturalObject("ID-1", "Different Name", "Different Type", 
                "Different Era", "Different Location");
        CulturalObject obj3 = new CulturalObject("ID-2", "Name", "Type", "Era", "Location");
        
        assertEquals(obj1, obj2); // Same ID should be equal
        assertNotEquals(obj1, obj3); // Different ID should not be equal
        assertEquals(obj1.hashCode(), obj2.hashCode());
        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }
    
    /**
     * Tests toString method of CulturalObject for proper formatting.
     */
    @Test
    public void testCulturalObjectToString() {
        CulturalObject obj = new CulturalObject("TEST-001", "Test Object", "Type", "Era", "Location");
        String str = obj.toString();
        
        assertTrue(str.contains("TEST-001"));
        assertTrue(str.contains("Test Object"));
        assertTrue(str.contains("Type"));
        assertTrue(str.contains("Era"));
        assertTrue(str.contains("Location"));
    }
    
    /**
     * Tests adding new cultural objects to the service.
     */
    @Test
    public void testAddCulturalObject() {
        try {
            CulturalObject newObj = new CulturalObject("NEW-001", "New Object", "Type", "Era", "Location");
            searchService.addCulturalObject(newObj);
            
            SearchForm form = new SearchForm("New Object", null, null, null);
            List<CulturalObject> results = searchService.search(form);
            
            assertEquals(1, results.size());
            assertEquals("NEW-001", results.get(0).getId());
        } catch (SearchService.SearchException e) {
            fail("Should be able to search newly added object: " + e.getMessage());
        }
    }
    
    /**
     * Tests the getCriteriaSummary method of SearchForm.
     */
    @Test
    public void testGetCriteriaSummary() {
        SearchForm form = new SearchForm("Mona Lisa", "Painting", "Renaissance", "Paris");
        String summary = form.getCriteriaSummary();
        
        assertTrue(summary.contains("Mona Lisa"));
        assertTrue(summary.contains("Painting"));
        assertTrue(summary.contains("Renaissance"));
        assertTrue(summary.contains("Paris"));
        assertTrue(summary.startsWith("Search Criteria:"));
        
        SearchForm emptyForm = new SearchForm();
        String emptySummary = emptyForm.getCriteriaSummary();
        assertTrue(emptySummary.contains("No criteria specified"));
    }
}