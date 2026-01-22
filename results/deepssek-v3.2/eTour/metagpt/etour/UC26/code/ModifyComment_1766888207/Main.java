import java.util.ArrayList;
import java.util.List;

/**
 * Main        ，  main  。
 *          、        ，        。
 *   ModifyComment       。
 */
public class Main {
    
    /**
     *        。
     * @param args      （   ）
     */
    public static void main(String[] args) {
        System.out.println("=== ModifyComment       ===");
        System.out.println("              \n");
        
        //   1：          （      ：     ）
        AgencyOperator operator = createAndLoginOperator();
        if (operator == null || !operator.isLoggedIn()) {
            System.err.println("  ：       ，    ");
            return;
        }
        
        //   2：           
        List<Site> sites = createMockSites();
        
        //   3：       
        UserInterface ui = new UserInterface(operator, sites);
        
        //   4：        
        try {
            ui.startModificationProcess();
        } finally {
            //   5：    
            ui.close();
        }
        
        System.out.println("\n===        ===");
    }
    
    /**
     *           。
     *          ，         。
     * @return          ，         null
     */
    private static AgencyOperator createAndLoginOperator() {
        System.out.println("          ...");
        
        //        
        AgencyOperator operator = new AgencyOperator(1001, "  ");
        
        //       
        System.out.println("   : " + operator.getOperatorName() + " (ID: " + operator.getOperatorId() + ")");
        
        //     （          ）
        boolean loginSuccess = operator.login("zhangsan", "password123");
        if (loginSuccess) {
            System.out.println("       ");
            return operator;
        } else {
            System.err.println("  ：       ");
            return null;
        }
    }
    
    /**
     *            。
     *    SearchSite         。
     * @return            
     */
    private static List<Site> createMockSites() {
        System.out.println("        ...");
        
        List<Site> sites = new ArrayList<>();
        
        //     1
        Site site1 = new Site(1, "    ");
        site1.addFeedback(new Feedback(101, "        ，             。", 1));
        site1.addFeedback(new Feedback(102, "        ，      。", 1));
        site1.addFeedback(new Feedback(103, "       ，         。", 1));
        sites.add(site1);
        
        //     2
        Site site2 = new Site(2, "      ");
        site2.addFeedback(new Feedback(201, "      ，       。", 2));
        site2.addFeedback(new Feedback(202, "      ，        。", 2));
        site2.addFeedback(new Feedback(203, "      ，         。", 2));
        site2.addFeedback(new Feedback(204, "    ，    ，     。", 2));
        sites.add(site2);
        
        //     3
        Site site3 = new Site(3, "      ");
        site3.addFeedback(new Feedback(301, "      ，      。", 3));
        site3.addFeedback(new Feedback(302, "              。", 3));
        sites.add(site3);
        
        System.out.println("    " + sites.size() + "    ，   " + 
                          sites.stream().mapToInt(s -> s.getFeedbacks().size()).sum() + "    ");
        
        return sites;
    }
    
    /**
     *            。
     *                  。
     */
    private static void demonstrateTestScenarios() {
        System.out.println("\n===        ===");
        
        //   1：    
        System.out.println("  1：        ");
        System.out.println("  ：       ->      ->      ->    ->   ");
        
        //   2：    
        System.out.println("  2：      ");
        System.out.println("  ：       'cancel'    ");
        
        //   3：    
        System.out.println("  3：        ");
        System.out.println("  ：    Errored  ，      ");
        
        //   4：       
        System.out.println("  4：       ");
        System.out.println("  ：         ，    ");
        
        System.out.println("\n  ：             ");
    }
}