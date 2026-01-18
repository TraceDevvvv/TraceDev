# ViewVisitedSites 用例分析

## 1. 用例概述

**用例名称**: ViewVisitedSites
**描述**: 查看游客已发布反馈的站点列表
**参与者**: 已成功认证的游客
**入口条件**: 游客已成功认证到系统
**出口条件**: 系统显示访问过的站点列表
**中断条件**: 到ETOUR服务器的连接中断

## 2. 事件流程分析

### 2.1 用户系统交互流程
1. **用户操作**: 游客选择显示个人访问站点列表的功能
2. **系统响应**: 上传游客已发布反馈的站点列表
3. **最终状态**: 系统显示访问过的站点列表

### 2.2 技术流程分析
1. 用户认证验证
2. 查询用户反馈记录
3. 提取反馈相关的站点信息
4. 展示站点列表

## 3. 功能需求分析

### 3.1 核心功能
- 用户认证状态验证
- 查询用户历史反馈记录
- 关联反馈与站点信息
- 展示站点列表（包含站点名称、访问时间、反馈内容等）

### 3.2 数据需求
- 用户信息表
- 反馈记录表
- 站点信息表
- 用户-站点关联表

## 4. 非功能需求分析

### 4.1 性能需求
- 响应时间：列表加载应在3秒内完成
- 并发支持：支持多用户同时访问
- 数据一致性：确保显示的数据是最新的

### 4.2 安全需求
- 用户认证验证
- 数据访问权限控制
- 防止SQL注入等安全漏洞

### 4.3 可用性需求
- 界面友好，易于操作
- 清晰的错误提示信息
- 支持分页显示（如果数据量大）

## 5. 技术栈分析

### 5.1 后端技术
- **语言**: Java 11+（根据要求）
- **框架**: Spring Boot 2.7+（推荐）
- **数据库**: MySQL 8.0+ 或 PostgreSQL 14+
- **ORM**: JPA/Hibernate
- **API**: RESTful API

### 5.2 前端技术
- **框架**: React/Vue.js（可选，根据项目需求）
- **UI库**: Ant Design/Material-UI
- **状态管理**: Redux/Vuex

### 5.3 架构模式
- 分层架构：Controller-Service-Repository
- 依赖注入：Spring IOC容器
- 事务管理：Spring声明式事务

## 6. 数据模型设计

### 6.1 用户表 (User)
```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### 6.2 站点表 (Site)
```sql
CREATE TABLE sites (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    location VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 6.3 反馈表 (Feedback)
```sql
CREATE TABLE feedbacks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    site_id BIGINT NOT NULL,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (site_id) REFERENCES sites(id)
);
```

## 7. 接口设计

### 7.1 REST API 端点
- `GET /api/users/{userId}/visited-sites` - 获取用户访问过的站点列表
- 请求头：Authorization Bearer Token
- 响应格式：JSON

### 7.2 响应示例
```json
{
    "success": true,
    "data": [
        {
            "siteId": 1,
            "siteName": "埃菲尔铁塔",
            "visitedDate": "2024-01-15",
            "feedbackRating": 5,
            "feedbackComment": "非常棒的体验！"
        },
        {
            "siteId": 2,
            "siteName": "卢浮宫",
            "visitedDate": "2024-01-16",
            "feedbackRating": 4,
            "feedbackComment": "艺术品很丰富"
        }
    ],
    "total": 2
}
```

## 8. 错误处理

### 8.1 常见错误场景
1. 用户未认证
2. 用户不存在
3. 数据库连接失败
4. 服务器内部错误

### 8.2 错误响应格式
```json
{
    "success": false,
    "error": {
        "code": "UNAUTHORIZED",
        "message": "用户未认证或认证已过期",
        "details": "请重新登录"
    }
}
```

## 9. 测试策略

### 9.1 单元测试
- 服务层逻辑测试
- 数据访问层测试
- 业务规则验证

### 9.2 集成测试
- API端点测试
- 数据库集成测试
- 认证授权测试

### 9.3 性能测试
- 并发用户测试
- 响应时间测试
- 数据库查询优化

## 10. 部署考虑

### 10.1 环境要求
- Java Runtime Environment 11+
- 数据库服务器
- Web服务器（Tomcat/Undertow）

### 10.2 配置管理
- 环境变量配置
- 数据库连接配置
- 安全配置

## 11. 风险评估

### 11.1 技术风险
- 数据库性能瓶颈
- 并发访问处理
- 安全漏洞

### 11.2 缓解措施
- 数据库索引优化
- 缓存机制引入
- 定期安全审计

## 12. 后续扩展

### 12.1 功能扩展
- 站点搜索功能
- 站点分类筛选
- 导出功能（CSV/PDF）

### 12.2 技术扩展
- 微服务架构迁移
- 缓存层添加（Redis）
- 消息队列集成

---

**分析完成时间**: 2025-12-27
**分析人员**: Alice (产品经理)
**下一步**: 基于此分析创建完整的PRD文档
