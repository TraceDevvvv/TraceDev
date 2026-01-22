// SiteCardUI.java
//      ，                     ，           。
//                 ：      ，      。

import java.util.InputMismatchException;
import java.util.Scanner;

public class SiteCardUI {
    private SiteCardService siteCardService;  //        
    private Scanner scanner;                  //         
    
    //     
    public SiteCardUI() {
        this.siteCardService = new SiteCardService();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     *      
     */
    public void showMainMenu() {
        System.out.println("\n==================================");
        System.out.println("                ");
        System.out.println("==================================");
        System.out.println("    ：ViewSiteCard");
        System.out.println("  ：           ");
        System.out.println("==================================");
        System.out.println("    : " + siteCardService.getCurrentStatus());
        System.out.println("==================================");
        System.out.println("     ：");
        System.out.println("1.       ");
        System.out.println("2.       ");
        System.out.println("3.         ");
        System.out.println("4.         ");
        System.out.println("5.     ");
        System.out.print("      (1-5): ");
    }
    
    /**
     *       
     */
    public void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                handleAuthentication();
                break;
            case 2:
                handleAreaSelection();
                break;
            case 3:
                handleShowAvailableSites();
                break;
            case 4:
                handleViewSiteCard();
                break;
            case 5:
                System.out.println("              ，  ！");
                break;
            default:
                System.out.println("  ：     ，   1-5     。");
        }
    }
    
    /**
     *       
     */
    private void handleAuthentication() {
        System.out.println("\n---      ---");
        System.out.println("    ，     '          '");
        System.out.println("        ...");
        
        //       
        siteCardService.authenticateTourist();
        
        System.out.println("    。");
        System.out.println("    : " + siteCardService.getCurrentStatus());
    }
    
    /**
     *       
     */
    private void handleAreaSelection() {
        System.out.println("\n---        ---");
        System.out.println("    ，            ：");
        System.out.println("1. Research Results (    )");
        System.out.println("2. Visited Sites (       )");
        System.out.println("3. Favorites List (    )");
        System.out.print("      (1-3): ");
        
        try {
            int areaChoice = scanner.nextInt();
            scanner.nextLine(); //      
            
            String area;
            switch (areaChoice) {
                case 1:
                    area = "Research Results";
                    break;
                case 2:
                    area = "Visited Sites";
                    break;
                case 3:
                    area = "Favorites List";
                    break;
                default:
                    System.out.println("  ：     ，      。");
                    return;
            }
            
            siteCardService.setCurrentArea(area);
            
        } catch (InputMismatchException e) {
            System.out.println("  ：         (1-3)。");
            scanner.nextLine(); //       
        }
    }
    
    /**
     *         
     */
    private void handleShowAvailableSites() {
        System.out.println("\n---        ---");
        System.out.println("            ...");
        
        int[] siteIds = siteCardService.getAvailableSiteIds();
        
        if (siteIds == null || siteIds.length == 0) {
            System.out.println("  ：              。");
            return;
        }
        
        System.out.println("        ID:");
        for (int i = 0; i < siteIds.length; i++) {
            System.out.print(siteIds[i]);
            if (i < siteIds.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n  ：         ID      。");
    }
    
    /**
     *         
     */
    private void handleViewSiteCard() {
        System.out.println("\n---          ---");
        System.out.println("    ：               ");
        
        //                  
        System.out.println("      ...");
        
        //       ID，      
        int[] siteIds = siteCardService.getAvailableSiteIds();
        if (siteIds == null || siteIds.length == 0) {
            System.out.println("  ：        ，    。");
            return;
        }
        
        System.out.print("    ID: ");
        for (int i = 0; i < siteIds.length; i++) {
            System.out.print(siteIds[i]);
            if (i < siteIds.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        
        //          ID
        System.out.print("         ID: ");
        
        try {
            int siteId = scanner.nextInt();
            scanner.nextLine(); //      
            
            //     ID    
            if (!siteCardService.validateSiteId(siteId)) {
                System.out.println("  ：  ID " + siteId + "   。");
                System.out.println("      ID   。");
                return;
            }
            
            //            
            System.out.println("      ID  " + siteId + "      ...");
            siteCardService.viewSiteCardWithValidation(siteId);
            
        } catch (InputMismatchException e) {
            System.out.println("  ：            ID。");
            scanner.nextLine(); //       
        }
    }
    
    /**
     *          
     */
    public void run() {
        System.out.println("              ！");
        System.out.println("       ：ViewSiteCard -            ");
        System.out.println("   ：        ，       。");
        
        boolean running = true;
        
        while (running) {
            showMainMenu();
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); //      
                
                if (choice == 5) {
                    running = false;
                }
                
                handleUserChoice(choice);
                
            } catch (InputMismatchException e) {
                System.out.println("  ：         (1-5)。");
                scanner.nextLine(); //       
            } catch (Exception e) {
                System.out.println("      : " + e.getMessage());
                System.out.println("  ：      。");
                running = false;
            }
        }
        
        //      
        scanner.close();
        System.out.println("       。");
    }
    
    /**
     *    ，      UI
     */
    public static void main(String[] args) {
        SiteCardUI ui = new SiteCardUI();
        ui.run();
    }
}