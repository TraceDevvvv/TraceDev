package com.tourist.feedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 旅游反馈系统主应用类
 * 作为Spring Boot应用程序的入口点，负责启动整个系统
 */
@SpringBootApplication
public class TouristFeedbackApplication {

    /**
     * 应用程序主入口方法
     * 启动Spring Boot应用并初始化所有必要的组件
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 启动Spring Boot应用程序
        // 这个方法会自动扫描当前包及其子包中的所有Spring组件
        SpringApplication.run(TouristFeedbackApplication.class, args);
    }
}