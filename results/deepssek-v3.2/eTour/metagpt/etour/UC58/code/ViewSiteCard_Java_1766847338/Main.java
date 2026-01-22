// Main.java
//   ，  main        ，          。
//                    ，      。

public class Main {
    /**
     *     -      
     *             
     * 
     * @param args      （      ）
     */
    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("             - ViewSiteCard    ");
        System.out.println("================================================");
        System.out.println("    : ViewSiteCard");
        System.out.println("  :            ");
        System.out.println("     :   ");
        System.out.println("================================================");
        System.out.println("      ...");
        
        try {
            //         
            SiteCardUI ui = new SiteCardUI();
            
            //       
            System.out.println("         。");
            System.out.println("  ：        ，       。");
            System.out.println("                  。");
            System.out.println("================================================");
            
            //       
            ui.run();
            
        } catch (Exception e) {
            //              
            System.err.println("             : " + e.getMessage());
            System.err.println("    : " + e.getClass().getName());
            System.err.println("  ：              。");
            e.printStackTrace();
            
            //     ，      
            System.exit(1);
        }
        
        System.out.println("================================================");
        System.out.println("        ");
        System.out.println("================================================");
    }
}