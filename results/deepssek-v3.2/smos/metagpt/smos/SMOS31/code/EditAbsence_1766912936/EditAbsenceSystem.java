import java.util.*;
import java.time.LocalDate;

/**
 * 主程序类：EditAbsenceSystem
 * 处理管理员编辑缺勤记录的主要流程
 * 根据用例要求实现完整的事件序列
 */
public class EditAbsenceSystem {
    
    /**
     * 主方法 - 程序入口点
     * 模拟管理员登录、选择日期、编辑缺勤、保存并发送通知的完整流程
     */
    public static void main(String[] args) {
        System.out.println("=== 缺勤编辑系统启动 ===");
        
        // 1. 管理员登录验证（前置条件）
        Administrator admin = loginAsAdministrator();
        if (admin == null) {
            System.out.println("错误：管理员登录失败。程序终止。");
            return;
        }
        
        System.out.println("管理员 " + admin.getName() + " 已成功登录。");
        
        // 2. 模拟从"SviewTetTingloregistration"用例选择日期
        LocalDate selectedDate = selectDateForAbsenceEdit();
        System.out.println("已选择日期：" + selectedDate);
        
        // 3. 系统根据所选日期更新屏幕（事件序列步骤1）
        updateScreenBasedOnDate(selectedDate);
        
        // 4. 管理员更改缺勤（插入或删除）并点击"保存"（事件序列步骤2）
        boolean isAbsenceAdded = changeAbsence(selectedDate);
        
        // 5. 发送修改数据到服务器（事件序列步骤3）
        boolean serverResponse = sendDataToServer(selectedDate, isAbsenceAdded);
        
        if (serverResponse) {
            // 6. 系统向学生家长发送邮件通知
            sendEmailToParent(isAbsenceAdded);
            
            // 后置条件：系统已更改缺勤记录并发送通知，停留在注册表屏幕
            System.out.println("\n=== 操作成功完成 ===");
            System.out.println("1. 缺勤记录已更新");
            System.out.println("2. 家长通知邮件已发送");
            System.out.println("3. 系统停留在注册表屏幕");
        } else {
            // 处理服务器连接中断的情况
            System.out.println("\n=== 操作中断 ===");
            System.out.println("错误：与SMOS服务器的连接中断。");
            System.out.println("管理员中断了操作。");
        }
    }
    
    /**
     * 模拟管理员登录
     * @return 登录成功返回Administrator对象，失败返回null
     */
    private static Administrator loginAsAdministrator() {
        // 在实际系统中，这里会有身份验证逻辑
        // 为简化演示，我们假设登录总是成功
        return new Administrator("admin001", "系统管理员");
    }
    
    /**
     * 模拟选择日期进行缺勤编辑
     * @return 选择的日期
     */
    private static LocalDate selectDateForAbsenceEdit() {
        // 在实际系统中，这里会有日期选择界面
        // 为简化演示，我们使用当前日期
        return LocalDate.now();
    }
    
    /**
     * 根据所选日期更新屏幕
     * @param date 选择的日期
     */
    private static void updateScreenBasedOnDate(LocalDate date) {
        System.out.println("系统：正在更新屏幕显示 " + date + " 的缺勤记录...");
        // 模拟屏幕更新逻辑
        System.out.println("屏幕已更新，显示 " + date + " 的缺勤信息。");
    }
    
    /**
     * 更改缺勤记录（插入或删除）
     * @param date 日期
     * @return true表示添加缺勤，false表示删除缺勤
     */
    private static boolean changeAbsence(LocalDate date) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n请选择操作 (1-添加缺勤, 2-删除缺勤): ");
        int choice = scanner.nextInt();
        
        boolean isAdded = (choice == 1);
        String action = isAdded ? "添加" : "删除";
        System.out.println("已" + action + " " + date + " 的缺勤记录。");
        
        return isAdded;
    }
    
    /**
     * 发送数据到服务器
     * @param date 日期
     * @param isAdded 是否添加缺勤
     * @return true表示服务器响应成功，false表示连接中断
     */
    private static boolean sendDataToServer(LocalDate date, boolean isAdded) {
        System.out.println("正在发送修改数据到SMOS服务器...");
        
        // 模拟服务器连接
        SMOSServer server = new SMOSServer();
        boolean isConnected = server.connect();
        
        if (!isConnected) {
            System.out.println("警告：服务器连接不稳定。");
            // 模拟可能的连接中断
            Random random = new Random();
            if (random.nextBoolean()) {
                System.out.println("错误：与SMOS服务器的连接中断！");
                return false;
            }
        }
        
        // 发送数据
        String data = String.format("{\"date\": \"%s\", \"action\": \"%s\"}", 
                                   date.toString(), isAdded ? "ADD" : "DELETE");
        boolean success = server.sendData(data);
        
        server.disconnect();
        
        if (success) {
            System.out.println("数据已成功发送到服务器。");
            return true;
        } else {
            System.out.println("数据发送失败。");
            return false;
        }
    }
    
    /**
     * 向家长发送邮件通知
     * @param isAdded 是否添加缺勤
     */
    private static void sendEmailToParent(boolean isAdded) {
        EmailService emailService = new EmailService();
        Student student = new Student("S001", "张三", new Parent("P001", "张父", "zhangfu@example.com"));
        
        String action = isAdded ? "添加" : "删除";
        String subject = "缺勤记录变更通知";
        String message = String.format("尊敬的家长，您的孩子 %s 的缺勤记录已被%s。请登录系统查看详情。", 
                                      student.getName(), action);
        
        boolean emailSent = emailService.sendEmail(student.getParent(), subject, message);
        
        if (emailSent) {
            System.out.println("通知邮件已成功发送给 " + student.getParent().getName() + "。");
        } else {
            System.out.println("警告：邮件发送失败，但缺勤记录已更新。");
        }
    }
}