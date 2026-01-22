import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 *        -                   
 * 
 *           ：
 * 1.                 
 * 2.  SearchSite        
 * 3.                  
 * 4.             
 * 5.       Java  
 */

/**
 *       -          
 */
class Feedback {
    private int id;
    private String comment;
    private String author;
    private LocalDateTime timestamp;
    private int rating; // 1-5   
    
    public Feedback(int id, String comment, String author, LocalDateTime timestamp, int rating) {
        this.id = id;
        this.comment = comment;
        this.author = author;
        this.timestamp = timestamp;
        this.rating = rating;
    }
    
    public int getId() { return id; }
    public String getComment() { return comment; }
    public String getAuthor() { return author; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getRating() { return rating; }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String stars = "★".repeat(rating) + "☆".repeat(5 - rating);
        return String.format("[%s] %s\n  : %s\n  : %s\n  : %s\n",
                stars, comment, author, timestamp.format(formatter), stars);
    }
}

/**
 *       -              
 */
class Site {
    private int id;
    private String name;
    private String description;
    private List<Feedback> feedbacks;
    
    public Site(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.feedbacks = new ArrayList<>();
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Feedback> getFeedbacks() { return feedbacks; }
    
    //        
    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }
    
    //          
    public List<Feedback> getAllFeedbacks() {
        return new ArrayList<>(feedbacks);
    }
    
    @Override
    public String toString() {
        return String.format("  ID: %d\n  : %s\n  : %s\n    : %d\n",
                id, name, description, feedbacks.size());
    }
}

/**
 *        -           ，      
 */
class ServerSimulator {
    /**
     *              -      SearchSite  
     *       ，    HTTP      API
     * 
     * @return     
     * @throws IOException             
     */
    public static List<Site> getSiteListFromServer() throws IOException {
        //          
        if (!isServerAvailable()) {
            throw new IOException("       ，        ");
        }
        
        List<Site> sites = new ArrayList<>();
        
        //         
        sites.add(new Site(1, "     ", "            "));
        sites.add(new Site(2, "     ", "             "));
        sites.add(new Site(3, "     ", "            "));
        sites.add(new Site(4, "     ", "            "));
        sites.add(new Site(5, "     ", "            "));
        
        //              
        addDummyFeedbacks(sites);
        
        return sites;
    }
    
    /**
     *            
     * 
     * @param siteId   ID
     * @return          
     * @throws IOException             
     */
    public static List<Feedback> getFeedbackForSite(int siteId) throws IOException {
        //          
        if (!isServerAvailable()) {
            throw new IOException("       ，        ");
        }
        
        List<Site> allSites = new ArrayList<>();
        addDummyFeedbacks(allSites);
        
        //       ，          API  
        for (Site site : allSites) {
            if (site.getId() == siteId) {
                return site.getAllFeedbacks();
            }
        }
        
        return new ArrayList<>(); //         ，     
    }
    
    /**
     *          
     * 
     * @return true       ，false     
     */
    public static boolean isServerAvailable() {
        //   10%             
        return Math.random() > 0.1;
    }
    
    /**
     *            
     */
    private static void addDummyFeedbacks(List<Site> sites) {
        if (sites.isEmpty()) {
            //       ，     
            sites.add(new Site(1, "     ", "            "));
            sites.add(new Site(2, "     ", "             "));
        }
        
        for (Site site : sites) {
            //        3     
            site.addFeedback(new Feedback(1, 
                site.getName() + "       ，    。", 
                "   ", 
                LocalDateTime.now().minusDays(3), 
                5));
            
            site.addFeedback(new Feedback(2, 
                "      ，        。", 
                "   ", 
                LocalDateTime.now().minusDays(7), 
                3));
            
            site.addFeedback(new Feedback(3, 
                "      ，      。", 
                "   ", 
                LocalDateTime.now().minusDays(1), 
                4));
        }
    }
}

/**
 *        -         
 */
class UserSession {
    private boolean isLoggedIn = false;
    private String username;
    
    /**
     *     
     * 
     * @param username    
     * @param password   
     * @return true      ，false    
     */
    public boolean login(String username, String password) {
        //          
        if ("agency_operator".equals(username) && "password123".equals(password)) {
            this.isLoggedIn = true;
            this.username = username;
            System.out.println("    ！   " + username);
            return true;
        } else {
            System.out.println("    ：         ");
            return false;
        }
    }
    
    /**
     *     
     */
    public void logout() {
        this.isLoggedIn = false;
        this.username = null;
        System.out.println("     ");
    }
    
    /**
     *          
     * 
     * @return true       
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     *           
     * 
     * @return    ，       null
     */
    public String getUsername() {
        return username;
    }
}

/**
 *         -   ViewFeedback       
 */
public class FeedbackViewer {
    private UserSession userSession;
    private Scanner scanner;
    
    public FeedbackViewer() {
        this.userSession = new UserSession();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     *       
     */
    public void start() {
        System.out.println("===          ===\n");
        
        //   1：    
        if (!performLogin()) {
            System.out.println("    ，    。");
            return;
        }
        
        boolean continueRunning = true;
        
        while (continueRunning && userSession.isLoggedIn()) {
            try {
                //   2：     
                displayMainMenu();
                
                int choice = getMenuChoice(1, 3);
                
                switch (choice) {
                    case 1:
                        viewSiteFeedbacks();
                        break;
                    case 2:
                        searchAndViewSite();
                        break;
                    case 3:
                        userSession.logout();
                        continueRunning = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("    : " + e.getMessage());
                System.out.println("     ...\n");
            }
        }
        
        System.out.println("            ！");
        scanner.close();
    }
    
    /**
     *         
     * 
     * @return true      
     */
    private boolean performLogin() {
        System.out.println("      ");
        System.out.print("   : ");
        String username = scanner.nextLine();
        System.out.print("  : ");
        String password = scanner.nextLine();
        
        return userSession.login(username, password);
    }
    
    /**
     *      
     */
    private void displayMainMenu() {
        System.out.println("\n=====     =====");
        System.out.println("1.       ");
        System.out.println("2.          ");
        System.out.println("3.     ");
        System.out.print("      (1-3): ");
    }
    
    /**
     *            
     * 
     * @param min       
     * @param max       
     * @return      
     */
    private int getMenuChoice(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input);
                
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("         (" + min + "-" + max + "): ");
                }
            } catch (NumberFormatException e) {
                System.out.print("      (" + min + "-" + max + "): ");
            }
        }
    }
    
    /**
     *        -       
     *       ：            
     */
    private void viewSiteFeedbacks() {
        System.out.println("\n=====        =====");
        
        try {
            //           （  SearchSite  ）
            System.out.println("            ...");
            List<Site> sites = ServerSimulator.getSiteListFromServer();
            
            //            
            displaySiteList(sites);
            
            if (sites.isEmpty()) {
                System.out.println("        。");
                return;
            }
            
            System.out.print("              (0  ): ");
            int siteChoice = getMenuChoice(0, sites.size());
            
            if (siteChoice == 0) {
                return; //       
            }
            
            Site selectedSite = sites.get(siteChoice - 1);
            System.out.println("\n=== " + selectedSite.getName() + "     ===");
            System.out.println(selectedSite);
            
            //              
            displayFeedbackForSite(selectedSite.getId());
            
        } catch (IOException e) {
            //             
            System.out.println("  : " + e.getMessage());
            System.out.println("          。");
        }
    }
    
    /**
     *           -     
     */
    private void searchAndViewSite() {
        System.out.println("\n=====           =====");
        
        try {
            System.out.print("               : ");
            String searchTerm = scanner.nextLine().toLowerCase();
            
            List<Site> allSites = ServerSimulator.getSiteListFromServer();
            List<Site> filteredSites = new ArrayList<>();
            
            //          
            for (Site site : allSites) {
                if (site.getName().toLowerCase().contains(searchTerm) ||
                    site.getDescription().toLowerCase().contains(searchTerm)) {
                    filteredSites.add(site);
                }
            }
            
            if (filteredSites.isEmpty()) {
                System.out.println("         。");
                return;
            }
            
            displaySiteList(filteredSites);
            
            System.out.print("              (0  ): ");
            int siteChoice = getMenuChoice(0, filteredSites.size());
            
            if (siteChoice == 0) {
                return;
            }
            
            Site selectedSite = filteredSites.get(siteChoice - 1);
            System.out.println("\n=== " + selectedSite.getName() + "     ===");
            System.out.println(selectedSite);
            
            displayFeedbackForSite(selectedSite.getId());
            
        } catch (IOException e) {
            System.out.println("  : " + e.getMessage());
            System.out.println("          。");
        }
    }
    
    /**
     *       
     * 
     * @param sites         
     */
    private void displaySiteList(List<Site> sites) {
        System.out.println("\n       :");
        System.out.println("=========================================");
        
        for (int i = 0; i < sites.size(); i++) {
            Site site = sites.get(i);
            System.out.printf("%d. %s - %s (   : %d)%n",
                    i + 1,
                    site.getName(),
                    site.getDescription(),
                    site.getFeedbacks().size());
        }
        
        System.out.println("=========================================");
    }
    
    /**
     *            
     * 
     * @param siteId   ID
     * @throws IOException          
     */
    private void displayFeedbackForSite(int siteId) throws IOException {
        List<Feedback> feedbacks = ServerSimulator.getFeedbackForSite(siteId);
        
        if (feedbacks.isEmpty()) {
            System.out.println("           。");
            return;
        }
        
        System.out.println("    :");
        System.out.println("=========================================");
        
        for (int i = 0; i < feedbacks.size(); i++) {
            System.out.println("   #" + (i + 1));
            System.out.println(feedbacks.get(i));
            System.out.println("-----------------------------------------");
        }
        
        //       
        displayFeedbackStatistics(feedbacks);
    }
    
    /**
     *         
     * 
     * @param feedbacks     
     */
    private void displayFeedbackStatistics(List<Feedback> feedbacks) {
        if (feedbacks.isEmpty()) {
            return;
        }
        
        int totalRating = 0;
        for (Feedback feedback : feedbacks) {
            totalRating += feedback.getRating();
        }
        
        double averageRating = (double) totalRating / feedbacks.size();
        
        System.out.println("    :");
        System.out.println("    : " + feedbacks.size());
        System.out.printf("    : %.1f/5.0%n", averageRating);
        
        //     
        int[] ratingCount = new int[6]; //   0   ，1-5    
        for (Feedback feedback : feedbacks) {
            ratingCount[feedback.getRating()]++;
        }
        
        System.out.println("    :");
        for (int i = 5; i >= 1; i--) {
            System.out.printf("%d : %d   %n", i, ratingCount[i]);
        }
        
        System.out.println("=========================================\n");
        
        //         
        System.out.println("    :");
        System.out.println("1.      ");
        System.out.println("2.       ");
        System.out.print("   : ");
        
        int choice = getMenuChoice(1, 2);
        
        if (choice == 2) {
            viewSiteFeedbacks();
        }
    }
    
    /**
     *         
     * 
     * @param args      
     */
    public static void main(String[] args) {
        FeedbackViewer feedbackViewer = new FeedbackViewer();
        feedbackViewer.start();
    }
}