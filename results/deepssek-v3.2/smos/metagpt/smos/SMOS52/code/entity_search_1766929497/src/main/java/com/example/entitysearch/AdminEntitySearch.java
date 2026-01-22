package com.example.entitysearch;

import java.util.List;
import java.util.Scanner;

/**
 *        -          
 *                     
 *     （ClassEntity）、  （Teaching）、  （Address）   （User）
 */
public class AdminEntitySearch {
    
    /**
     *     -      
     *             
     */
    public static void main(String[] args) {
        System.out.println("===           ===\n");
        
        //          
        if (!authenticateAdmin()) {
            System.out.println("         ！    。");
            return;
        }
        
        System.out.println("       ！\n");
        
        //       
        SearchService searchService = new SearchService();
        
        //        
        Scanner scanner = new Scanner(System.in);
        
        boolean continueSearching = true;
        
        while (continueSearching) {
            System.out.println("        （   'exit'     ）:");
            String keyword = scanner.nextLine().trim();
            
            //          
            if ("exit".equalsIgnoreCase(keyword)) {
                System.out.println("      。");
                System.out.println("      SMOS   ...");
                continueSearching = false;
                break;
            }
            
            //          
            if (keyword.isEmpty()) {
                System.out.println("  ：         ！\n");
                continue;
            }
            
            //     
            System.out.println("\n       : \"" + keyword + "\"...\n");
            List<Entity> searchResults = searchService.searchAllEntities(keyword);
            
            //       
            displaySearchResults(searchResults, keyword);
            
            System.out.println("=".repeat(50) + "\n");
        }
        
        scanner.close();
        System.out.println("     。");
    }
    
    /**
     *          
     *       ，           
     * 
     * @return       true，    false
     */
    private static boolean authenticateAdmin() {
        System.out.println("           ...");
        
        //       
        try {
            Thread.sleep(800); //       
        } catch (InterruptedException e) {
            System.out.println("         : " + e.getMessage());
            return false;
        }
        
        //         -                 
        //         （             ）
        return true;
    }
    
    /**
     *       
     *           
     * 
     * @param results       
     * @param keyword      
     */
    private static void displaySearchResults(List<Entity> results, String keyword) {
        if (results == null || results.isEmpty()) {
            System.out.println("        \"" + keyword + "\"      。");
            return;
        }
        
        System.out.println("   " + results.size() + "    \"" + keyword + "\"      ：");
        System.out.println("-".repeat(50));
        
        //          
        int classCount = 0;
        int teachingCount = 0;
        int addressCount = 0;
        int userCount = 0;
        
        //           
        for (int i = 0; i < results.size(); i++) {
            Entity entity = results.get(i);
            System.out.printf("%d. [%s] %s%n", i + 1, entity.getType(), entity.getDisplayInfo());
            
            //         
            switch (entity.getType()) {
                case "  ":
                    classCount++;
                    break;
                case "  ":
                    teachingCount++;
                    break;
                case "  ":
                    addressCount++;
                    break;
                case "  ":
                    userCount++;
                    break;
            }
        }
        
        System.out.println("-".repeat(50));
        System.out.println("      ：");
        System.out.println("    : " + classCount + "  ");
        System.out.println("    : " + teachingCount + "  ");
        System.out.println("    : " + addressCount + "  ");
        System.out.println("    : " + userCount + "  ");
        
        //         ，  "    "  
        System.out.println("\n【      】:");
        System.out.println("✓               \"" + keyword + "\"          ");
        System.out.println("✓      " + results.size() + "      ");
    }
}