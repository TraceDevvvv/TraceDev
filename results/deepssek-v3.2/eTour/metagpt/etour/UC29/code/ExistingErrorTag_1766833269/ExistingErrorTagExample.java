import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * ExistingErrorTagExample -   ExistingErrorTag   Java  
 * 
 *     ：                   ，        ，
 *        ，         。
 */
public class ExistingErrorTagExample {
    
    //      ，           
    private static class SystemState {
        private Set<String> existingTags;
        private int inputCount;
        private String lastValidInput;
        
        public SystemState(Set<String> existingTags, int inputCount, String lastValidInput) {
            this.existingTags = new HashSet<>(existingTags);
            this.inputCount = inputCount;
            this.lastValidInput = lastValidInput;
        }
        
        public Set<String> getExistingTags() {
            return new HashSet<>(existingTags);
        }
        
        public int getInputCount() {
            return inputCount;
        }
        
        public String getLastValidInput() {
            return lastValidInput;
        }
        
        @Override
        public String toString() {
            return String.format("SystemState{   =%d,     =%d,       ='%s'}",
                    existingTags.size(), inputCount, lastValidInput);
        }
    }
    
    //       
    private static class TagManager {
        private Set<String> existingTags;
        private SystemState previousState;
        
        public TagManager() {
            this.existingTags = new HashSet<>();
            this.previousState = null;
            //          
            initializeDefaultTags();
        }
        
        private void initializeDefaultTags() {
            existingTags.add("java");
            existingTags.add("programming");
            existingTags.add("design");
            existingTags.add("system");
        }
        
        /**
         *        
         * @param tag       
         * @return          false，      true
         */
        public boolean tryAddTag(String tag) {
            if (tag == null || tag.trim().isEmpty()) {
                return false; //           
            }
            
            String normalizedTag = tag.trim().toLowerCase();
            
            //           
            saveCurrentState();
            
            if (existingTags.contains(normalizedTag)) {
                return false; //      
            }
            
            existingTags.add(normalizedTag);
            return true;
        }
        
        /**
         *         
         */
        private void saveCurrentState() {
            String lastValidInput = existingTags.isEmpty() ? null : 
                existingTags.stream().skip(existingTags.size() - 1).findFirst().orElse(null);
            previousState = new SystemState(existingTags, existingTags.size(), lastValidInput);
        }
        
        /**
         *         
         */
        public void restorePreviousState() {
            if (previousState != null) {
                this.existingTags = previousState.getExistingTags();
            }
        }
        
        /**
         *          
         */
        public boolean tagExists(String tag) {
            if (tag == null || tag.trim().isEmpty()) {
                return false;
            }
            return existingTags.contains(tag.trim().toLowerCase());
        }
        
        /**
         *         
         */
        public Set<String> getAllTags() {
            return new HashSet<>(existingTags);
        }
        
        /**
         *         
         */
        public String getStatus() {
            return String.format("      ：   %d    ，    ：%s",
                    existingTags.size(), String.join(", ", existingTags));
        }
    }
    
    /**
     *        
     */
    private static class UserInteractionHandler {
        private Scanner scanner;
        private TagManager tagManager;
        
        public UserInteractionHandler(TagManager tagManager) {
            this.scanner = new Scanner(System.in);
            this.tagManager = tagManager;
        }
        
        /**
         *                
         * @param tag       
         * @return         
         */
        public boolean showErrorMessageAndConfirm(String tag) {
            System.out.println("\n==========      ==========");
            System.out.printf("  ：   '%s'         ！\n", tag);
            System.out.println("==============================");
            System.out.print("            （   'y'   ，      ）：");
            
            String confirmation = scanner.nextLine().trim().toLowerCase();
            return confirmation.equals("y") || confirmation.equals("yes");
        }
        
        /**
         *          
         */
        public String getUserInput() {
            System.out.print("\n         （   'exit'     ）：");
            return scanner.nextLine().trim();
        }
        
        /**
         *            
         */
        public void showWelcome() {
            System.out.println("===        ===");
            System.out.println("   ExistingErrorTag   ");
            System.out.println("      ，      ：");
            System.out.println(tagManager.getAllTags());
            System.out.println("=====================");
        }
        
        /**
         *       
         */
        public void showExitMessage() {
            System.out.println("\n          ！");
            System.out.println("      ：");
            System.out.println(tagManager.getStatus());
        }
        
        /**
         *     
         */
        public void close() {
            scanner.close();
        }
    }
    
    /**
     *         
     */
    private static class ApplicationController {
        private TagManager tagManager;
        private UserInteractionHandler uiHandler;
        
        public ApplicationController() {
            this.tagManager = new TagManager();
            this.uiHandler = new UserInteractionHandler(tagManager);
        }
        
        /**
         *      
         */
        public void run() {
            uiHandler.showWelcome();
            
            boolean running = true;
            while (running) {
                //   1：      
                String userInput = uiHandler.getUserInput();
                
                //       
                if (userInput.equalsIgnoreCase("exit")) {
                    running = false;
                    continue;
                }
                
                //         
                if (userInput.isEmpty()) {
                    System.out.println("  ：      ！");
                    continue;
                }
                
                //          
                if (tagManager.tagExists(userInput)) {
                    //   1：             
                    boolean confirmed = uiHandler.showErrorMessageAndConfirm(userInput);
                    
                    if (confirmed) {
                        //   2：         
                        System.out.println("         。");
                        
                        //   3：       
                        tagManager.restorePreviousState();
                        System.out.println("       。");
                        System.out.println(tagManager.getStatus());
                    } else {
                        System.out.println("    ，         。");
                    }
                } else {
                    //      ，    
                    boolean added = tagManager.tryAddTag(userInput);
                    if (added) {
                        System.out.printf("       '%s'    。\n", userInput);
                        System.out.println(tagManager.getStatus());
                    } else {
                        System.out.println("      （       ）。");
                    }
                }
            }
            
            uiHandler.showExitMessage();
            uiHandler.close();
        }
    }
    
    /**
     *     -      
     */
    public static void main(String[] args) {
        try {
            ApplicationController app = new ApplicationController();
            app.run();
        } catch (Exception e) {
            System.err.println("         ：" + e.getMessage());
            e.printStackTrace();
        }
    }
}