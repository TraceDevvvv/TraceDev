import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * ViewNoteDetails  -            
 *       ，             
 *            、           
 */
public class ViewNoteDetails {
    
    //        （    ）
    private static final Map<String, Note> NOTE_DATABASE = new HashMap<>();
    
    //       ：         
    static {
        try {
            //          
            NOTE_DATABASE.put("N001", new Note("N001", "  ", "      ：     ", "   ", "2024-01-15 10:30:00"));
            NOTE_DATABASE.put("N002", new Note("N002", "  ", "      ：        ", "   ", "2024-01-16 14:20:00"));
            NOTE_DATABASE.put("N003", new Note("N003", "  ", "    ：      ", "   ", "2024-01-17 09:15:00"));
            NOTE_DATABASE.put("N004", new Note("N004", "  ", "    ：       ", "   ", "2024-01-18 11:45:00"));
            NOTE_DATABASE.put("N005", new Note("N005", "  ", "      ：      ", "   ", "2024-01-19 16:30:00"));
        } catch (Exception e) {
            System.err.println("             : " + e.getMessage());
        }
    }
    
    /**
     *                  
     *       ，             
     * 
     * @param user     
     * @return             ，  true；    false
     */
    public static boolean validateUserPermission(User user) {
        if (user == null) {
            System.out.println("  ：      ！");
            return false;
        }
        
        if (!user.isLoggedIn()) {
            System.out.println("  ：     ！");
            return false;
        }
        
        if (!user.isAdmin()) {
            System.out.println("  ：             ！      : " + user.getRole());
            return false;
        }
        
        System.out.println("      ：" + user.getUsername() + "     ");
        return true;
    }
    
    /**
     *     ID      
     * 
     * @param noteId   ID
     * @return     ，       null
     */
    public static Note getNoteById(String noteId) {
        if (noteId == null || noteId.trim().isEmpty()) {
            System.out.println("  ：  ID    ！");
            return null;
        }
        
        Note note = NOTE_DATABASE.get(noteId.trim());
        if (note == null) {
            System.out.println("  ：   ID  '" + noteId + "'    ");
            return null;
        }
        
        return note;
    }
    
    /**
     *        ID（        ）
     * 
     * @return   ID  
     */
    public static List<String> getAllNoteIds() {
        return new ArrayList<>(NOTE_DATABASE.keySet());
    }
    
    /**
     *        -     
     *       ，    、  、  、     
     * 
     * @param note     
     * @return       
     */
    public static boolean displayNoteDetails(Note note) {
        if (note == null) {
            System.out.println("  ：      ，      ！");
            return false;
        }
        
        //         
        if (!note.isValid()) {
            System.out.println("  ：       ，        ");
        }
        
        //        -          ：  、  、  、  
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                ");
        System.out.println("=".repeat(50));
        
        //   getDetails         
        System.out.println(note.getDetails());
        
        //     
        System.out.println("\n    ：");
        System.out.println("       : " + (note.isValid() ? "  " : "   "));
        System.out.println("      : " + note.getFormattedDate());
        
        System.out.println("=".repeat(50));
        
        return true;
    }
    
    /**
     *       （    ，    ）
     * 
     * @param noteId   ID
     * @return       
     */
    public static boolean displayNoteDetailsById(String noteId) {
        Note note = getNoteById(noteId);
        if (note == null) {
            return false;
        }
        return displayNoteDetails(note);
    }
    
    /**
     *       （       ）
     *              
     * 
     * @param user     （      ）
     * @param noteId   ID
     * @return       
     */
    public static boolean viewNoteDetails(User user, String noteId) {
        //       
        System.out.println("\n" + "=".repeat(60));
        System.out.println("    ViewNoteDetails  ...");
        System.out.println("  : " + (user != null ? user.getUsername() : "null"));
        System.out.println("      ID: " + noteId);
        System.out.println("=".repeat(60));
        
        //   1：      （       ）
        if (!validateUserPermission(user)) {
            System.out.println("  ：        ！");
            System.out.println("      ");
            System.out.println("=".repeat(60) + "\n");
            return false;
        }
        
        //   2：      
        Note note = getNoteById(noteId);
        if (note == null) {
            System.out.println("  ：        ！");
            System.out.println("      ");
            System.out.println("=".repeat(60) + "\n");
            return false;
        }
        
        //   3：      
        boolean displaySuccess = displayNoteDetails(note);
        
        if (displaySuccess) {
            System.out.println("        ！");
            System.out.println("      ");
        } else {
            System.out.println("  ：        ！");
            System.out.println("      ");
        }
        
        System.out.println("=".repeat(60) + "\n");
        
        return displaySuccess;
    }
    
    /**
     *   SMOS       （          ）
     */
    public static void handleServerConnectionIssue() {
        System.out.println("  ：SMOS       ！");
        System.out.println("  ：         ，          。");
    }
    
    /**
     *         
     * 
     * @return        
     */
    public static String getNoteStatistics() {
        int totalNotes = NOTE_DATABASE.size();
        int validNotes = 0;
        
        for (Note note : NOTE_DATABASE.values()) {
            if (note.isValid()) {
                validNotes++;
            }
        }
        
        return String.format("    :   =%d,     =%d,     =%d", 
                totalNotes, validNotes, totalNotes - validNotes);
    }
    
    /**
     *             
     * 
     * @param keyword      
     * @return        
     */
    public static List<Note> searchNotes(String keyword) {
        List<Note> result = new ArrayList<>();
        
        if (keyword == null || keyword.trim().isEmpty()) {
            return result;
        }
        
        String searchTerm = keyword.trim().toLowerCase();
        
        for (Note note : NOTE_DATABASE.values()) {
            String description = note.getDescription() != null ? note.getDescription().toLowerCase() : "";
            String student = note.getStudent() != null ? note.getStudent().toLowerCase() : "";
            String teacher = note.getTeacher() != null ? note.getTeacher().toLowerCase() : "";
            
            if (description.contains(searchTerm) || 
                student.contains(searchTerm) || 
                teacher.contains(searchTerm)) {
                result.add(note);
            }
        }
        
        return result;
    }
    
    /**
     *          （    ）
     * 
     * @param note     
     * @return       
     */
    public static boolean addNote(Note note) {
        if (note == null || note.getNoteId() == null) {
            System.out.println("  ：     ID  ！");
            return false;
        }
        
        if (NOTE_DATABASE.containsKey(note.getNoteId())) {
            System.out.println("  ：  ID   : " + note.getNoteId());
            return false;
        }
        
        NOTE_DATABASE.put(note.getNoteId(), note);
        System.out.println("      : " + note.getNoteId());
        return true;
    }
    
    /**
     *     
     * 
     * @param noteId   ID
     * @param user        （      ）
     * @return       
     */
    public static boolean deleteNote(String noteId, User user) {
        if (!validateUserPermission(user)) {
            System.out.println("  ：           ！");
            return false;
        }
        
        if (noteId == null || !NOTE_DATABASE.containsKey(noteId)) {
            System.out.println("  ：     : " + noteId);
            return false;
        }
        
        Note removedNote = NOTE_DATABASE.remove(noteId);
        if (removedNote != null) {
            System.out.println("      : " + noteId);
            return true;
        }
        
        return false;
    }
    
    /**
     *            
     * 
     * @return     
     */
    public static List<Note> getAllNotes() {
        return new ArrayList<>(NOTE_DATABASE.values());
    }
    
    /**
     *       （      ）
     * 
     * @param user        （      ）
     * @return       
     */
    public static boolean clearAllNotes(User user) {
        if (!validateUserPermission(user)) {
            System.out.println("  ：             ！");
            return false;
        }
        
        int count = NOTE_DATABASE.size();
        NOTE_DATABASE.clear();
        System.out.println("        ，    " + count + "    ");
        return true;
    }
    
    /**
     *       
     */
    public static void generateTestReport() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("     - ViewNoteDetails  ");
        System.out.println("=".repeat(50));
        
        System.out.println(getNoteStatistics());
        System.out.println("    ID: " + getAllNoteIds());
        
        System.out.println("\n      :");
        List<Note> allNotes = getAllNotes();
        for (int i = 0; i < Math.min(3, allNotes.size()); i++) {
            Note note = allNotes.get(i);
            System.out.println((i+1) + ". " + note.getSummary());
        }
        
        System.out.println("=".repeat(50) + "\n");
    }
}