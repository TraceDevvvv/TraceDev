import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * ViewNoteDetails类 - 模拟显示笔记详情的功能
 * 根据用例要求，只有管理员可以查看笔记详情
 * 该类处理管理员权限验证、笔记数据获取和显示详情
 */
public class ViewNoteDetails {
    
    // 模拟笔记数据库（内存存储）
    private static final Map<String, Note> NOTE_DATABASE = new HashMap<>();
    
    // 静态初始化块：初始化一些示例笔记
    static {
        try {
            // 初始化一些示例笔记
            NOTE_DATABASE.put("N001", new Note("N001", "张三", "数学课堂笔记：微积分基础", "李老师", "2024-01-15 10:30:00"));
            NOTE_DATABASE.put("N002", new Note("N002", "李四", "物理实验报告：牛顿第二定律验证", "王老师", "2024-01-16 14:20:00"));
            NOTE_DATABASE.put("N003", new Note("N003", "王五", "英语作文：我的假期计划", "张老师", "2024-01-17 09:15:00"));
            NOTE_DATABASE.put("N004", new Note("N004", "赵六", "化学笔记：化学反应方程式", "刘老师", "2024-01-18 11:45:00"));
            NOTE_DATABASE.put("N005", new Note("N005", "钱七", "历史复习资料：二战重要事件", "陈老师", "2024-01-19 16:30:00"));
        } catch (Exception e) {
            System.err.println("初始化笔记数据库时发生错误: " + e.getMessage());
        }
    }
    
    /**
     * 验证用户是否具有查看笔记详情的权限
     * 根据用例要求，只有管理员可以查看笔记详情
     * 
     * @param user 用户对象
     * @return 如果用户是管理员且已登录，返回true；否则返回false
     */
    public static boolean validateUserPermission(User user) {
        if (user == null) {
            System.out.println("错误：用户对象为空！");
            return false;
        }
        
        if (!user.isLoggedIn()) {
            System.out.println("错误：用户未登录！");
            return false;
        }
        
        if (!user.isAdmin()) {
            System.out.println("错误：只有管理员可以查看笔记详情！当前用户角色: " + user.getRole());
            return false;
        }
        
        System.out.println("权限验证通过：" + user.getUsername() + " 是管理员");
        return true;
    }
    
    /**
     * 根据笔记ID获取笔记详情
     * 
     * @param noteId 笔记ID
     * @return 笔记对象，如果不存在返回null
     */
    public static Note getNoteById(String noteId) {
        if (noteId == null || noteId.trim().isEmpty()) {
            System.out.println("错误：笔记ID不能为空！");
            return null;
        }
        
        Note note = NOTE_DATABASE.get(noteId.trim());
        if (note == null) {
            System.out.println("错误：找不到ID为 '" + noteId + "' 的笔记");
            return null;
        }
        
        return note;
    }
    
    /**
     * 获取所有笔记的ID（用于模拟列表显示）
     * 
     * @return 笔记ID列表
     */
    public static List<String> getAllNoteIds() {
        return new ArrayList<>(NOTE_DATABASE.keySet());
    }
    
    /**
     * 显示笔记详情 - 核心方法
     * 根据用例要求，显示学生、描述、老师、日期等信息
     * 
     * @param note 笔记对象
     * @return 显示是否成功
     */
    public static boolean displayNoteDetails(Note note) {
        if (note == null) {
            System.out.println("错误：笔记对象为空，无法显示详情！");
            return false;
        }
        
        // 检查笔记是否有效
        if (!note.isValid()) {
            System.out.println("警告：笔记数据不完整，某些字段可能为空");
        }
        
        // 显示笔记详情 - 按照用例要求的格式：学生、描述、老师、日期
        System.out.println("\n" + "=".repeat(50));
        System.out.println("            笔记详情");
        System.out.println("=".repeat(50));
        
        // 使用getDetails方法获取格式化详情
        System.out.println(note.getDetails());
        
        // 额外信息
        System.out.println("\n附加信息：");
        System.out.println("笔记有效性检查: " + (note.isValid() ? "通过" : "不通过"));
        System.out.println("笔记创建时间: " + note.getFormattedDate());
        
        System.out.println("=".repeat(50));
        
        return true;
    }
    
    /**
     * 显示笔记详情（简化版本，直接显示）
     * 
     * @param noteId 笔记ID
     * @return 显示是否成功
     */
    public static boolean displayNoteDetailsById(String noteId) {
        Note note = getNoteById(noteId);
        if (note == null) {
            return false;
        }
        return displayNoteDetails(note);
    }
    
    /**
     * 显示笔记详情（带用户权限验证）
     * 这是符合用例要求的主要方法
     * 
     * @param user 用户对象（必须是管理员）
     * @param noteId 笔记ID
     * @return 显示是否成功
     */
    public static boolean viewNoteDetails(User user, String noteId) {
        // 记录操作开始
        System.out.println("\n" + "=".repeat(60));
        System.out.println("开始执行ViewNoteDetails用例...");
        System.out.println("用户: " + (user != null ? user.getUsername() : "null"));
        System.out.println("请求查看笔记ID: " + noteId);
        System.out.println("=".repeat(60));
        
        // 步骤1：验证用户权限（管理员且已登录）
        if (!validateUserPermission(user)) {
            System.out.println("错误：用户权限验证失败！");
            System.out.println("用例执行失败");
            System.out.println("=".repeat(60) + "\n");
            return false;
        }
        
        // 步骤2：获取笔记数据
        Note note = getNoteById(noteId);
        if (note == null) {
            System.out.println("错误：无法获取笔记数据！");
            System.out.println("用例执行失败");
            System.out.println("=".repeat(60) + "\n");
            return false;
        }
        
        // 步骤3：显示笔记详情
        boolean displaySuccess = displayNoteDetails(note);
        
        if (displaySuccess) {
            System.out.println("笔记详情显示成功！");
            System.out.println("用例执行成功");
        } else {
            System.out.println("错误：笔记详情显示失败！");
            System.out.println("用例执行失败");
        }
        
        System.out.println("=".repeat(60) + "\n");
        
        return displaySuccess;
    }
    
    /**
     * 模拟SMOS服务器连接中断（根据用例中的后置条件）
     */
    public static void handleServerConnectionIssue() {
        System.out.println("警告：SMOS服务器连接中断！");
        System.out.println("注意：这可能影响后续操作，但笔记详情已成功显示。");
    }
    
    /**
     * 获取笔记统计信息
     * 
     * @return 笔记统计字符串
     */
    public static String getNoteStatistics() {
        int totalNotes = NOTE_DATABASE.size();
        int validNotes = 0;
        
        for (Note note : NOTE_DATABASE.values()) {
            if (note.isValid()) {
                validNotes++;
            }
        }
        
        return String.format("笔记统计: 总数=%d, 有效笔记=%d, 无效笔记=%d", 
                totalNotes, validNotes, totalNotes - validNotes);
    }
    
    /**
     * 搜索包含特定关键词的笔记
     * 
     * @param keyword 搜索关键词
     * @return 匹配的笔记列表
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
     * 添加新笔记到数据库（用于测试）
     * 
     * @param note 笔记对象
     * @return 添加是否成功
     */
    public static boolean addNote(Note note) {
        if (note == null || note.getNoteId() == null) {
            System.out.println("错误：笔记或笔记ID为空！");
            return false;
        }
        
        if (NOTE_DATABASE.containsKey(note.getNoteId())) {
            System.out.println("错误：笔记ID已存在: " + note.getNoteId());
            return false;
        }
        
        NOTE_DATABASE.put(note.getNoteId(), note);
        System.out.println("成功添加笔记: " + note.getNoteId());
        return true;
    }
    
    /**
     * 删除笔记
     * 
     * @param noteId 笔记ID
     * @param user 执行操作的用户（必须是管理员）
     * @return 删除是否成功
     */
    public static boolean deleteNote(String noteId, User user) {
        if (!validateUserPermission(user)) {
            System.out.println("错误：只有管理员可以删除笔记！");
            return false;
        }
        
        if (noteId == null || !NOTE_DATABASE.containsKey(noteId)) {
            System.out.println("错误：笔记不存在: " + noteId);
            return false;
        }
        
        Note removedNote = NOTE_DATABASE.remove(noteId);
        if (removedNote != null) {
            System.out.println("成功删除笔记: " + noteId);
            return true;
        }
        
        return false;
    }
    
    /**
     * 获取数据库中的所有笔记
     * 
     * @return 笔记列表
     */
    public static List<Note> getAllNotes() {
        return new ArrayList<>(NOTE_DATABASE.values());
    }
    
    /**
     * 清除所有笔记（用于测试重置）
     * 
     * @param user 执行操作的用户（必须是管理员）
     * @return 清除是否成功
     */
    public static boolean clearAllNotes(User user) {
        if (!validateUserPermission(user)) {
            System.out.println("错误：只有管理员可以清除所有笔记！");
            return false;
        }
        
        int count = NOTE_DATABASE.size();
        NOTE_DATABASE.clear();
        System.out.println("成功清除所有笔记，共清除 " + count + " 条记录");
        return true;
    }
    
    /**
     * 生成测试报告
     */
    public static void generateTestReport() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("测试报告 - ViewNoteDetails系统");
        System.out.println("=".repeat(50));
        
        System.out.println(getNoteStatistics());
        System.out.println("可用笔记ID: " + getAllNoteIds());
        
        System.out.println("\n样品笔记详情:");
        List<Note> allNotes = getAllNotes();
        for (int i = 0; i < Math.min(3, allNotes.size()); i++) {
            Note note = allNotes.get(i);
            System.out.println((i+1) + ". " + note.getSummary());
        }
        
        System.out.println("=".repeat(50) + "\n");
    }
}