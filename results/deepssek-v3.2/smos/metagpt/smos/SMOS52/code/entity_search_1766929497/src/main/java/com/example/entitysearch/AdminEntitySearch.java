package com.example.entitysearch;

import java.util.List;
import java.util.Scanner;

/**
 * 主程序入口类 - 管理员实体搜索系统
 * 模拟管理员登录后搜索系统中所有实体的功能
 * 包含班级（ClassEntity）、教学（Teaching）、地址（Address）和用户（User）
 */
public class AdminEntitySearch {
    
    /**
     * 主方法 - 程序入口点
     * 模拟管理员登录和搜索流程
     */
    public static void main(String[] args) {
        System.out.println("=== 管理员实体搜索系统 ===\n");
        
        // 模拟管理员登录验证
        if (!authenticateAdmin()) {
            System.out.println("管理员身份验证失败！程序退出。");
            return;
        }
        
        System.out.println("管理员登录成功！\n");
        
        // 创建搜索服务
        SearchService searchService = new SearchService();
        
        // 获取控制台输入
        Scanner scanner = new Scanner(System.in);
        
        boolean continueSearching = true;
        
        while (continueSearching) {
            System.out.println("请输入搜索关键词（输入 'exit' 退出程序）:");
            String keyword = scanner.nextLine().trim();
            
            // 检查用户是否要退出
            if ("exit".equalsIgnoreCase(keyword)) {
                System.out.println("用户停止操作。");
                System.out.println("连接到中断的SMOS服务器...");
                continueSearching = false;
                break;
            }
            
            // 验证关键词是否为空
            if (keyword.isEmpty()) {
                System.out.println("错误：搜索关键词不能为空！\n");
                continue;
            }
            
            // 执行搜索
            System.out.println("\n正在搜索关键词: \"" + keyword + "\"...\n");
            List<Entity> searchResults = searchService.searchAllEntities(keyword);
            
            // 显示搜索结果
            displaySearchResults(searchResults, keyword);
            
            System.out.println("=".repeat(50) + "\n");
        }
        
        scanner.close();
        System.out.println("程序已退出。");
    }
    
    /**
     * 模拟管理员身份验证
     * 在实际系统中，这里会调用身份验证服务
     * 
     * @return 验证成功返回true，失败返回false
     */
    private static boolean authenticateAdmin() {
        System.out.println("正在进行管理员身份验证...");
        
        // 模拟验证过程
        try {
            Thread.sleep(800); // 模拟网络延迟
        } catch (InterruptedException e) {
            System.out.println("认证过程中出现异常: " + e.getMessage());
            return false;
        }
        
        // 简化的模拟验证 - 实际系统中这里会有复杂的验证逻辑
        // 假设验证总是成功（因为我们模拟的是已登录状态）
        return true;
    }
    
    /**
     * 显示搜索结果
     * 根据实体类型分类显示
     * 
     * @param results 搜索结果列表
     * @param keyword 搜索关键词
     */
    private static void displaySearchResults(List<Entity> results, String keyword) {
        if (results == null || results.isEmpty()) {
            System.out.println("未找到与关键词 \"" + keyword + "\" 相关的实体。");
            return;
        }
        
        System.out.println("找到 " + results.size() + " 个与 \"" + keyword + "\" 相关的实体：");
        System.out.println("-".repeat(50));
        
        // 按实体类型分组计数
        int classCount = 0;
        int teachingCount = 0;
        int addressCount = 0;
        int userCount = 0;
        
        // 显示搜索到的所有实体
        for (int i = 0; i < results.size(); i++) {
            Entity entity = results.get(i);
            System.out.printf("%d. [%s] %s%n", i + 1, entity.getType(), entity.getDisplayInfo());
            
            // 统计各类实体数量
            switch (entity.getType()) {
                case "班级":
                    classCount++;
                    break;
                case "教学":
                    teachingCount++;
                    break;
                case "地址":
                    addressCount++;
                    break;
                case "用户":
                    userCount++;
                    break;
            }
        }
        
        System.out.println("-".repeat(50));
        System.out.println("搜索结果统计：");
        System.out.println("  班级: " + classCount + " 个");
        System.out.println("  教学: " + teachingCount + " 个");
        System.out.println("  地址: " + addressCount + " 个");
        System.out.println("  用户: " + userCount + " 个");
        
        // 根据后置条件要求，显示"活动列表"信息
        System.out.println("\n【后置条件验证】:");
        System.out.println("✓ 用户正在显示与输入的关键词 \"" + keyword + "\" 对应的实体活动列表");
        System.out.println("✓ 列表包含 " + results.size() + " 个活动实体");
    }
}