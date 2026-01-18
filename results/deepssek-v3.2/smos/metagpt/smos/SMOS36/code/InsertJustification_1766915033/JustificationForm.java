import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * JustificationForm - 请假理由表单处理类
 * 负责显示请假理由表单、收集用户输入、验证输入数据，并创建Justification对象。
 * 这个类处理表单的显示逻辑和用户交互。
 */
public class JustificationForm {
    
    // 日期格式化器，用于用户输入和显示
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    // 表单字段
    private String absenceRecordId;  // 缺勤记录ID（从用户点击的红色缺勤记录获取）
    private LocalDate justificationDate; // 请假日期
    private String reason;           // 请假理由
    private String adminId;          // 管理员ID
    
    /**
     * 默认构造函数
     * 初始化表单字段为默认值
     */
    public JustificationForm() {
        // 在实际应用中，absenceRecordId会从用户点击的红色缺勤记录获取
        // 这里我们使用一个模拟的缺勤记录ID
        this.absenceRecordId = "ABS_001"; // 模拟缺勤记录ID
        this.adminId = "admin001";        // 模拟管理员ID
    }
    
    /**
     * 带参数的构造函数
     * @param absenceRecordId 缺勤记录ID
     * @param adminId 管理员ID
     */
    public JustificationForm(String absenceRecordId, String adminId) {
        this.absenceRecordId = absenceRecordId;
        this.adminId = adminId;
    }
    
    /**
     * 显示请假理由表单
     * 按照用例要求显示表单字段
     */
    public void displayForm() {
        System.out.println("\n=== 请假理由表单 ===");
        System.out.println("请填写以下信息：");
        System.out.println("----------------------------------------");
        System.out.println("缺勤记录ID: " + absenceRecordId + " (已从选中的缺勤记录获取)");
        System.out.println("管理员ID: " + adminId);
        System.out.println("----------------------------------------");
        System.out.println("需要填写的字段：");
        System.out.println("1. 请假日期 (格式: YYYY-MM-DD)");
        System.out.println("2. 请假理由 (最多500个字符)");
        System.out.println("----------------------------------------");
        System.out.println("填写完成后，请点击'保存'按钮。");
        System.out.println("输入'取消'可以中断操作。");
    }
    
    /**
     * 收集用户输入
     * 从控制台读取用户输入，验证数据，并创建Justification对象
     * @param scanner 输入扫描器
     * @return Justification对象，如果用户取消或输入无效则返回null
     */
    public Justification collectInput(Scanner scanner) {
        try {
            System.out.println("\n--- 表单填写开始 ---");
            
            // 检查用户是否要取消操作
            System.out.print("是否开始填写？(输入'是'继续，'取消'中断操作): ");
            String confirmation = scanner.nextLine().trim();
            
            if ("取消".equalsIgnoreCase(confirmation)) {
                System.out.println("操作已被用户中断。");
                throw new OperationInterruptedException("用户中断了表单填写操作。");
            }
            
            // 收集请假日期
            LocalDate date = collectDate(scanner);
            if (date == null) {
                return null; // 用户取消或输入无效
            }
            
            // 收集请假理由
            String reasonText = collectReason(scanner);
            if (reasonText == null) {
                return null; // 用户取消或输入无效
            }
            
            // 创建并返回Justification对象
            Justification justification = new Justification(
                absenceRecordId, date, reasonText, adminId
            );
            
            System.out.println("\n--- 表单填写完成 ---");
            System.out.println("已收集的信息：");
            System.out.println("请假日期: " + Justification.formatDate(date));
            System.out.println("请假理由: " + 
                (reasonText.length() > 50 ? reasonText.substring(0, 50) + "..." : reasonText));
            
            return justification;
            
        } catch (OperationInterruptedException e) {
            System.out.println("表单填写已中断: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("表单填写过程中发生错误: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 收集请假日期
     * 处理用户输入的日期，验证格式和有效性
     * @param scanner 输入扫描器
     * @return 有效的LocalDate对象，如果用户取消则返回null
     */
    private LocalDate collectDate(Scanner scanner) {
        int maxAttempts = 3; // 最大尝试次数
        int attempts = 0;
        
        while (attempts < maxAttempts) {
            try {
                System.out.print("\n请输入请假日期 (格式: YYYY-MM-DD，例如: 2024-12-28): ");
                String dateInput = scanner.nextLine().trim();
                
                // 检查用户是否要取消
                if ("取消".equalsIgnoreCase(dateInput)) {
                    System.out.println("日期输入已取消。");
                    throw new OperationInterruptedException("用户取消了日期输入。");
                }
                
                // 验证日期输入是否为空
                if (dateInput.isEmpty()) {
                    System.out.println("错误：日期不能为空。");
                    attempts++;
                    continue;
                }
                
                // 验证日期格式
                if (!isValidDateFormat(dateInput)) {
                    System.out.println("错误：日期格式不正确，请使用YYYY-MM-DD格式。");
                    attempts++;
                    continue;
                }
                
                // 尝试解析日期
                LocalDate date = LocalDate.parse(dateInput, DATE_FORMATTER);
                
                // 验证日期是否合理（不能是未来日期，也不能是太久以前的日期）
                LocalDate today = LocalDate.now();
                LocalDate minValidDate = today.minusYears(1); // 允许最多一年前的日期
                
                if (date.isAfter(today)) {
                    System.out.println("警告：请假日期不能是未来日期。");
                    System.out.print("是否继续？(输入'是'继续使用此日期，'否'重新输入): ");
                    String confirm = scanner.nextLine().trim();
                    if ("否".equalsIgnoreCase(confirm)) {
                        attempts++;
                        continue;
                    }
                } else if (date.isBefore(minValidDate)) {
                    System.out.println("警告：请假日期过于久远（超过一年）。");
                    System.out.print("是否继续？(输入'是'继续使用此日期，'否'重新输入): ");
                    String confirm = scanner.nextLine().trim();
                    if ("否".equalsIgnoreCase(confirm)) {
                        attempts++;
                        continue;
                    }
                }
                
                return date;
                
            } catch (DateTimeParseException e) {
                System.out.println("错误：无法解析日期，请检查格式是否正确。");
                attempts++;
            } catch (OperationInterruptedException e) {
                throw e; // 重新抛出中断异常
            } catch (Exception e) {
                System.out.println("错误：日期输入无效 - " + e.getMessage());
                attempts++;
            }
        }
        
        System.out.println("错误：已达到最大尝试次数，日期输入失败。");
        return null;
    }
    
    /**
     * 收集请假理由
     * 处理用户输入的请假理由，验证长度和内容
     * @param scanner 输入扫描器
     * @return 有效的请假理由字符串，如果用户取消则返回null
     */
    private String collectReason(Scanner scanner) {
        int maxAttempts = 3; // 最大尝试次数
        int attempts = 0;
        
        while (attempts < maxAttempts) {
            try {
                System.out.println("\n请输入请假理由 (最多500个字符): ");
                System.out.print("> ");
                String reasonInput = scanner.nextLine().trim();
                
                // 检查用户是否要取消
                if ("取消".equalsIgnoreCase(reasonInput)) {
                    System.out.println("请假理由输入已取消。");
                    throw new OperationInterruptedException("用户取消了请假理由输入。");
                }
                
                // 验证理由是否为空
                if (reasonInput.isEmpty()) {
                    System.out.println("错误：请假理由不能为空。");
                    attempts++;
                    continue;
                }
                
                // 验证理由长度
                if (reasonInput.length() > 500) {
                    System.out.println("警告：请假理由超过500字符，将被截断。");
                    System.out.println("原始长度: " + reasonInput.length() + " 字符");
                    System.out.print("是否继续？(输入'是'继续并截断，'否'重新输入): ");
                    String confirm = scanner.nextLine().trim();
                    
                    if ("否".equalsIgnoreCase(confirm)) {
                        attempts++;
                        continue;
                    }
                    
                    // 截断到500字符
                    reasonInput = reasonInput.substring(0, 500);
                    System.out.println("已截断为500字符。");
                }
                
                // 验证理由内容（基本检查）
                if (reasonInput.trim().length() < 5) {
                    System.out.println("警告：请假理由过短，请提供更详细的信息。");
                    System.out.print("是否继续？(输入'是'继续，'否'重新输入): ");
                    String confirm = scanner.nextLine().trim();
                    
                    if ("否".equalsIgnoreCase(confirm)) {
                        attempts++;
                        continue;
                    }
                }
                
                // 检查是否包含明显无效内容
                if (containsInvalidContent(reasonInput)) {
                    System.out.println("警告：请假理由可能包含无效内容。");
                    System.out.print("是否继续？(输入'是'继续，'否'重新输入): ");
                    String confirm = scanner.nextLine().trim();
                    
                    if ("否".equalsIgnoreCase(confirm)) {
                        attempts++;
                        continue;
                    }
                }
                
                return reasonInput;
                
            } catch (OperationInterruptedException e) {
                throw e; // 重新抛出中断异常
            } catch (Exception e) {
                System.out.println("错误：请假理由输入无效 - " + e.getMessage());
                attempts++;
            }
        }
        
        System.out.println("错误：已达到最大尝试次数，请假理由输入失败。");
        return null;
    }
    
    /**
     * 验证日期格式是否正确
     * @param dateString 日期字符串
     * @return true如果格式正确
     */
    private boolean isValidDateFormat(String dateString) {
        // 基本格式验证：YYYY-MM-DD
        if (dateString == null || dateString.length() != 10) {
            return false;
        }
        
        // 检查分隔符
        if (dateString.charAt(4) != '-' || dateString.charAt(7) != '-') {
            return false;
        }
        
        // 检查每个部分是否为数字
        try {
            String[] parts = dateString.split("-");
            if (parts.length != 3) {
                return false;
            }
            
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            
            // 基本范围检查
            if (year < 1900 || year > 2100) {
                return false;
            }
            if (month < 1 || month > 12) {
                return false;
            }
            if (day < 1 || day > 31) {
                return false;
            }
            
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * 检查是否包含明显无效内容
     * @param text 要检查的文本
     * @return true如果包含无效内容
     */
    private boolean containsInvalidContent(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        
        String lowerText = text.toLowerCase();
        
        // 检查是否只包含标点符号或空格
        if (text.trim().matches("^[\\p{P}\\s]+$")) {
            return true;
        }
        
        // 检查是否包含明显的占位符
        String[] placeholders = {"test", "测试", "例子", "example", "placeholder", "xxx"};
        for (String placeholder : placeholders) {
            if (lowerText.contains(placeholder)) {
                return true;
            }
        }
        
        // 检查是否过短或过长（已在其他方法中检查）
        return false;
    }
    
    /**
     * 获取缺勤记录ID
     * @return 缺勤记录ID
     */
    public String getAbsenceRecordId() {
        return absenceRecordId;
    }
    
    /**
     * 设置缺勤记录ID
     * @param absenceRecordId 缺勤记录ID
     */
    public void setAbsenceRecordId(String absenceRecordId) {
        this.absenceRecordId = absenceRecordId;
    }
    
    /**
     * 获取管理员ID
     * @return 管理员ID
     */
    public String getAdminId() {
        return adminId;
    }
    
    /**
     * 设置管理员ID
     * @param adminId 管理员ID
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    
    /**
     * 操作中断异常类（内部类）
     */
    public static class OperationInterruptedException extends Exception {
        public OperationInterruptedException(String message) {
            super(message);
        }
    }
}