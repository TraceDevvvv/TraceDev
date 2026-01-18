package com.example.entitysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟数据类
 * 为系统提供测试数据，包括班级、教学、地址和用户实体
 * 在实际系统中，这些数据通常来自数据库或外部服务
 */
public class MockData {
    
    /**
     * 获取所有班级实体
     * 返回预定义的班级实体列表，用于测试搜索功能
     * 
     * @return 班级实体列表
     */
    public List<ClassEntity> getAllClassEntities() {
        List<ClassEntity> classes = new ArrayList<>();
        
        // 添加班级实体数据
        classes.add(new ClassEntity(
            "CLASS-001",
            "计算机科学2023级1班",
            "计算机科学专业2023级1班，主修编程和算法",
            "张老师",
            45,
            "2023",
            "计算机科学与技术学院"
        ));
        
        classes.add(new ClassEntity(
            "CLASS-002",
            "软件工程2022级2班",
            "软件工程专业2022级2班，主修软件开发和项目管理",
            "李老师",
            38,
            "2022",
            "软件学院"
        ));
        
        classes.add(new ClassEntity(
            "CLASS-003",
            "人工智能2023级实验班",
            "人工智能专业2023级实验班，主修机器学习和深度学习",
            "王教授",
            30,
            "2023",
            "人工智能学院"
        ));
        
        classes.add(new ClassEntity(
            "CLASS-004",
            "网络工程2021级1班",
            "网络工程专业2021级1班，主修网络协议和安全",
            "赵老师",
            42,
            "2021",
            "网络工程学院"
        ));
        
        classes.add(new ClassEntity(
            "CLASS-005",
            "数据科学2022级精英班",
            "数据科学专业2022级精英班，主修数据分析和可视化",
            "刘教授",
            28,
            "2022",
            "数据科学学院"
        ));
        
        classes.add(new ClassEntity(
            "CLASS-006",
            "信息安全2023级1班",
            "信息安全专业2023级1班，主修密码学和网络安全",
            "陈老师",
            35,
            "2023",
            "信息安全学院"
        ));
        
        classes.add(new ClassEntity(
            "CLASS-007",
            "物联网工程2022级2班",
            "物联网工程专业2022级2班，主修嵌入式系统和传感器技术",
            "孙老师",
            40,
            "2022",
            "物联网工程学院"
        ));
        
        classes.add(new ClassEntity(
            "CLASS-008",
            "数字媒体技术2023级1班",
            "数字媒体技术专业2023级1班，主修图形设计和动画制作",
            "周老师",
            32,
            "2023",
            "数字媒体学院"
        ));
        
        return classes;
    }
    
    /**
     * 获取所有教学实体
     * 返回预定义的教学实体列表，用于测试搜索功能
     * 
     * @return 教学实体列表
     */
    public List<Teaching> getAllTeachingEntities() {
        List<Teaching> teachings = new ArrayList<>();
        
        // 添加教学实体数据
        teachings.add(new Teaching(
            "TEACH-001",
            "Java编程基础",
            "Java编程语言基础课程，面向初学者",
            "Java编程基础",
            "T001",
            "张老师",
            48,
            "2023秋季学期",
            "教学楼A101"
        ));
        
        teachings.add(new Teaching(
            "TEACH-002",
            "数据结构与算法",
            "计算机科学核心课程，讲解常用数据结构和算法",
            "数据结构与算法",
            "T002",
            "李教授",
            64,
            "2023秋季学期",
            "教学楼B205"
        ));
        
        teachings.add(new Teaching(
            "TEACH-003",
            "数据库系统原理",
            "数据库系统设计与实现原理课程",
            "数据库系统原理",
            "T003",
            "王老师",
            56,
            "2023春季学期",
            "实验楼C301"
        ));
        
        teachings.add(new Teaching(
            "TEACH-004",
            "计算机网络",
            "计算机网络协议和架构课程",
            "计算机网络",
            "T004",
            "赵教授",
            60,
            "2023秋季学期",
            "教学楼D102"
        ));
        
        teachings.add(new Teaching(
            "TEACH-005",
            "人工智能导论",
            "人工智能基础理论和应用课程",
            "人工智能导论",
            "T005",
            "刘老师",
            48,
            "2023秋季学期",
            "人工智能实验室"
        ));
        
        teachings.add(new Teaching(
            "TEACH-006",
            "软件工程实践",
            "软件工程方法论和实践项目课程",
            "软件工程实践",
            "T006",
            "陈教授",
            72,
            "2023春季学期",
            "软件工程实验室"
        ));
        
        teachings.add(new Teaching(
            "TEACH-007",
            "Web前端开发",
            "现代Web前端开发技术课程",
            "Web前端开发",
            "T007",
            "孙老师",
            40,
            "2023秋季学期",
            "教学楼E201"
        ));
        
        teachings.add(new Teaching(
            "TEACH-008",
            "移动应用开发",
            "Android和iOS移动应用开发课程",
            "移动应用开发",
            "T008",
            "周老师",
            56,
            "2023春季学期",
            "移动开发实验室"
        ));
        
        return teachings;
    }
    
    /**
     * 获取所有地址实体
     * 返回预定义的地址实体列表，用于测试搜索功能
     * 
     * @return 地址实体列表
     */
    public List<Address> getAllAddressEntities() {
        List<Address> addresses = new ArrayList<>();
        
        // 添加地址实体数据
        addresses.add(new Address(
            "ADDR-001",
            "主校区图书馆",
            "学校主校区图书馆，藏书丰富，提供自习空间",
            "大学路123号",
            "北京市",
            "北京市",
            "100000",
            "中国",
            "图书馆大楼"
        ));
        
        addresses.add(new Address(
            "ADDR-002",
            "计算机学院大楼",
            "计算机科学与技术学院主楼，包含教室和实验室",
            "科技路456号",
            "北京市",
            "北京市",
            "100001",
            "中国",
            "计算机学院大楼"
        ));
        
        addresses.add(new Address(
            "ADDR-003",
            "学生宿舍1号楼",
            "本科生宿舍楼，提供学生住宿",
            "学生路789号",
            "北京市",
            "北京市",
            "100002",
            "中国",
            "学生宿舍1号楼"
        ));
        
        addresses.add(new Address(
            "ADDR-004",
            "行政办公楼",
            "学校行政办公大楼，包含各部门办公室",
            "行政路101号",
            "北京市",
            "北京市",
            "100003",
            "中国",
            "行政办公楼"
        ));
        
        addresses.add(new Address(
            "ADDR-005",
            "体育中心",
            "学校体育中心，包含体育馆和运动场",
            "体育路202号",
            "北京市",
            "北京市",
            "100004",
            "中国",
            "体育中心"
        ));
        
        addresses.add(new Address(
            "ADDR-006",
            "实验楼A座",
            "主要实验教学楼，包含多个专业实验室",
            "实验路303号",
            "北京市",
            "北京市",
            "100005",
            "中国",
            "实验楼A座"
        ));
        
        addresses.add(new Address(
            "ADDR-007",
            "国际交流中心",
            "国际学生和交流学者活动中心",
            "国际路404号",
            "北京市",
            "北京市",
            "100006",
            "中国",
            "国际交流中心"
        ));
        
        addresses.add(new Address(
            "ADDR-008",
            "学生活动中心",
            "学生社团活动和课外活动场所",
            "活动路505号",
            "北京市",
            "北京市",
            "100007",
            "中国",
            "学生活动中心"
        ));
        
        return addresses;
    }
    
    /**
     * 获取所有用户实体
     * 返回预定义的用户实体列表，用于测试搜索功能
     * 
     * @return 用户实体列表
     */
    public List<User> getAllUserEntities() {
        List<User> users = new ArrayList<>();
        
        // 添加用户实体数据
        users.add(new User(
            "USER-001",
            "张三",
            "系统管理员，负责系统维护",
            "zhangsan",
            "zhangsan@university.edu",
            "管理员",
            "13800138001",
            true,
            "信息技术部"
        ));
        
        users.add(new User(
            "USER-002",
            "李四",
            "计算机科学教授，研究方向：人工智能",
            "lisi",
            "lisi@university.edu",
            "教授",
            "13800138002",
            true,
            "计算机科学与技术学院"
        ));
        
        users.add(new User(
            "USER-003",
            "王五",
            "软件工程讲师，主讲Java编程",
            "wangwu",
            "wangwu@university.edu",
            "讲师",
            "13800138003",
            true,
            "软件学院"
        ));
        
        users.add(new User(
            "USER-004",
            "赵六",
            "学生，计算机科学专业2023级",
            "zhaoliu",
            "zhaoliu@student.university.edu",
            "学生",
            "13800138004",
            true,
            "计算机科学与技术学院"
        ));
        
        users.add(new User(
            "USER-005",
            "刘七",
            "图书馆管理员，负责图书管理",
            "liuqi",
            "liuqi@university.edu",
            "图书管理员",
            "13800138005",
            true,
            "图书馆"
        ));
        
        users.add(new User(
            "USER-006",
            "陈八",
            "教务处工作人员，负责课程安排",
            "chenba",
            "chenba@university.edu",
            "行政人员",
            "13800138006",
            true,
            "教务处"
        ));
        
        users.add(new User(
            "USER-007",
            "孙九",
            "网络工程师，负责校园网络维护",
            "sunjiu",
            "sunjiu@university.edu",
            "工程师",
            "13800138007",
            true,
            "网络中心"
        ));
        
        users.add(new User(
            "USER-008",
            "周十",
            "国际学生，来自美国",
            "zhoushi",
            "zhoushi@student.university.edu",
            "国际学生",
            "13800138008",
            true,
            "国际学院"
        ));
        
        users.add(new User(
            "USER-009",
            "吴十一",
            "已离职教师，账户未激活",
            "wushiyi",
            "wushiyi@former.university.edu",
            "前教师",
            "13800138009",
            false,
            "无"
        ));
        
        users.add(new User(
            "USER-010",
            "郑十二",
            "新生，尚未完成注册",
            "zhengshier",
            "zhengshier@new.university.edu",
            "新生",
            "13800138010",
            false,
            "待分配"
        ));
        
        return users;
    }
    
    /**
     * 获取所有实体（混合类型）
     * 返回所有类型实体的混合列表
     * 
     * @return 所有实体的混合列表
     */
    public List<Entity> getAllEntities() {
        List<Entity> allEntities = new ArrayList<>();
        
        allEntities.addAll(getAllClassEntities());
        allEntities.addAll(getAllTeachingEntities());
        allEntities.addAll(getAllAddressEntities());
        allEntities.addAll(getAllUserEntities());
        
        return allEntities;
    }
    
    /**
     * 获取实体统计信息
     * 返回系统中各类实体的数量统计
     * 
     * @return 包含各类实体数量的字符串
     */
    public String getEntityStatistics() {
        int classCount = getAllClassEntities().size();
        int teachingCount = getAllTeachingEntities().size();
        int addressCount = getAllAddressEntities().size();
        int userCount = getAllUserEntities().size();
        int totalCount = classCount + teachingCount + addressCount + userCount;
        
        return String.format(
            "系统实体统计：班级=%d, 教学=%d, 地址=%d, 用户=%d, 总计=%d",
            classCount, teachingCount, addressCount, userCount, totalCount
        );
    }
    
    /**
     * 初始化模拟数据
     * 打印模拟数据加载信息
     */
    public void initializeMockData() {
        System.out.println("模拟数据初始化完成！");
        System.out.println(getEntityStatistics());
        System.out.println("模拟数据已准备就绪，可用于搜索测试。");
    }
    
    /**
     * 根据ID查找特定实体
     * 在所有实体中查找指定ID的实体
     * 
     * @param entityId 实体ID
     * @return 找到的实体，如果未找到则返回null
     */
    public Entity findEntityById(String entityId) {
        if (entityId == null || entityId.isEmpty()) {
            return null;
        }
        
        // 在所有实体中搜索
        for (Entity entity : getAllEntities()) {
            if (entityId.equals(entity.getId())) {
                return entity;
            }
        }
        
        return null;
    }
    
    /**
     * 根据名称查找实体
     * 在所有实体中查找包含指定名称的实体
     * 
     * @param name 实体名称（或部分名称）
     * @return 匹配的实体列表
     */
    public List<Entity> findEntitiesByName(String name) {
        List<Entity> results = new ArrayList<>();
        
        if (name == null || name.isEmpty()) {
            return results;
        }
        
        String lowerName = name.toLowerCase();
        
        for (Entity entity : getAllEntities()) {
            if (entity.getName() != null && entity.getName().toLowerCase().contains(lowerName)) {
                results.add(entity);
            }
        }
        
        return results;
    }
}