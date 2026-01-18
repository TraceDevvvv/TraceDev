package com.example.entitysearch;

/**
 * 地址实体类
 * 表示系统中的地址实体，继承自Entity基类
 * 包含地址相关的属性和方法，如街道、城市、邮编等
 */
public class Address extends Entity {
    private String street;           // 街道地址
    private String city;             // 城市
    private String state;            // 州/省份
    private String postalCode;       // 邮政编码
    private String country;          // 国家
    private String buildingName;     // 建筑物名称
    
    /**
     * 构造函数
     * 
     * @param id 地址唯一标识
     * @param name 地址名称
     * @param description 地址描述
     * @param street 街道地址
     * @param city 城市
     * @param state 州/省份
     * @param postalCode 邮政编码
     * @param country 国家
     * @param buildingName 建筑物名称
     */
    public Address(String id, String name, String description,
                  String street, String city, String state, 
                  String postalCode, String country, String buildingName) {
        super(id, name, description);
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.buildingName = buildingName;
    }
    
    /**
     * 获取实体类型
     * 实现父类的抽象方法
     * 
     * @return 实体类型字符串 "地址"
     */
    @Override
    public String getType() {
        return "地址";
    }
    
    /**
     * 检查地址实体是否包含指定的关键词
     * 覆盖父类方法，添加地址特有字段的搜索
     * 
     * @param keyword 要搜索的关键词
     * @return 如果地址包含关键词返回true，否则返回false
     */
    @Override
    public boolean containsKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        
        String lowerKeyword = keyword.toLowerCase().trim();
        
        // 首先调用父类的搜索逻辑
        if (super.containsKeyword(keyword)) {
            return true;
        }
        
        // 检查街道地址是否包含关键词
        if (street != null && street.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查城市是否包含关键词
        if (city != null && city.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查州/省份是否包含关键词
        if (state != null && state.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查邮政编码是否包含关键词
        if (postalCode != null && postalCode.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查国家是否包含关键词
        if (country != null && country.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查建筑物名称是否包含关键词
        if (buildingName != null && buildingName.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 获取用于显示的地址信息
     * 覆盖父类方法，提供更详细的地址信息
     * 
     * @return 格式化的地址显示信息
     */
    @Override
    public String getDisplayInfo() {
        StringBuilder display = new StringBuilder();
        display.append(name);
        
        if (buildingName != null && !buildingName.isEmpty()) {
            display.append(" (").append(buildingName).append(")");
        }
        
        display.append(" - ");
        
        // 构建地址字符串
        if (street != null && !street.isEmpty()) {
            display.append(street).append(", ");
        }
        
        if (city != null && !city.isEmpty()) {
            display.append(city).append(", ");
        }
        
        if (state != null && !state.isEmpty()) {
            display.append(state).append(", ");
        }
        
        if (postalCode != null && !postalCode.isEmpty()) {
            display.append(postalCode).append(", ");
        }
        
        if (country != null && !country.isEmpty()) {
            display.append(country);
        }
        
        // 移除末尾多余的逗号和空格
        String result = display.toString().trim();
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1);
        }
        
        return result;
    }
    
    /**
     * 获取详细的地址信息
     * 覆盖父类方法，提供完整的地址信息
     * 
     * @return 详细的地址信息字符串
     */
    @Override
    public String getDetailedInfo() {
        return getType() + " ID: " + id + 
               ", 地址名称: " + name + 
               ", 描述: " + description + 
               ", 街道: " + street + 
               ", 城市: " + city + 
               ", 州/省份: " + state + 
               ", 邮政编码: " + postalCode + 
               ", 国家: " + country + 
               ", 建筑物: " + buildingName;
    }
    
    /**
     * 获取完整的格式化地址
     * 
     * @return 格式化的完整地址字符串
     */
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder();
        
        if (street != null && !street.isEmpty()) {
            fullAddress.append(street).append(", ");
        }
        
        if (city != null && !city.isEmpty()) {
            fullAddress.append(city).append(", ");
        }
        
        if (state != null && !state.isEmpty()) {
            fullAddress.append(state).append(", ");
        }
        
        if (postalCode != null && !postalCode.isEmpty()) {
            fullAddress.append(postalCode).append(", ");
        }
        
        if (country != null && !country.isEmpty()) {
            fullAddress.append(country);
        }
        
        String result = fullAddress.toString().trim();
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1);
        }
        
        return result;
    }
    
    // Getter和Setter方法
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getBuildingName() {
        return buildingName;
    }
    
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}