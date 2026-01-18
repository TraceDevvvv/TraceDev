package com.example.entitysearch;

/**
 * 实体基类
 * 定义系统中所有实体的通用属性和方法
 * 这是所有具体实体类的父类
 */
public abstract class Entity {
    protected String id;          // 实体唯一标识
    protected String name;        // 实体名称
    protected String description; // 实体描述
    
    /**
     * 构造函数
     * 
     * @param id 实体唯一标识
     * @param name 实体名称
     * @param description 实体描述
     */
    public Entity(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    /**
     * 获取实体类型
     * 子类必须实现此方法以返回具体的实体类型
     * 
     * @return 实体类型字符串
     */
    public abstract String getType();
    
    /**
     * 检查实体是否包含指定的关键词
     * 子类可以实现更精确的匹配逻辑
     * 
     * @param keyword 要搜索的关键词
     * @return 如果实体包含关键词返回true，否则返回false
     */
    public boolean containsKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        
        String lowerKeyword = keyword.toLowerCase().trim();
        
        // 检查名称中是否包含关键词
        if (name != null && name.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查描述中是否包含关键词
        if (description != null && description.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 子类可以添加更多的搜索字段
        return false;
    }
    
    /**
     * 获取用于显示的实体信息
     * 子类可以覆盖此方法以提供更详细的显示信息
     * 
     * @return 格式化的显示信息
     */
    public String getDisplayInfo() {
        return name + " - " + description;
    }
    
    /**
     * 获取详细实体信息
     * 子类应该覆盖此方法以提供完整的实体信息
     * 
     * @return 详细的实体信息字符串
     */
    public String getDetailedInfo() {
        return getType() + " ID: " + id + ", 名称: " + name + ", 描述: " + description;
    }
    
    // Getter和Setter方法
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * 重写toString方法，提供友好的实体表示
     */
    @Override
    public String toString() {
        return getDetailedInfo();
    }
}