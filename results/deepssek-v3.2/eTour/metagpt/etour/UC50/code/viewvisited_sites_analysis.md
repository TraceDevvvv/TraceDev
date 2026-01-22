# ViewVisitedSites     

## 1.     

**    **: ViewVisitedSites
**  **:               
**   **:         
**    **:           
**    **:             
**    **:  ETOUR        

## 2.       

### 2.1         
1. **    **:                  
2. **    **:               
3. **    **:             

### 2.2       
1.       
2.         
3.            
4.       

## 3.       

### 3.1     
-         
-           
-          
-       （      、    、     ）

### 3.2     
-      
-      
-      
-   -     

## 4.        

### 4.1     
-     ：      3    
-     ：         
-      ：           

### 4.2     
-       
-         
-   SQL       

### 4.3      
-     ，    
-          
-       （      ）

## 5.      

### 5.1     
- **  **: Java 11+（    ）
- **  **: Spring Boot 2.7+（  ）
- **   **: MySQL 8.0+   PostgreSQL 14+
- **ORM**: JPA/Hibernate
- **API**: RESTful API

### 5.2     
- **  **: React/Vue.js（  ，      ）
- **UI **: Ant Design/Material-UI
- **    **: Redux/Vuex

### 5.3     
-     ：Controller-Service-Repository
-     ：Spring IOC  
-     ：Spring     

## 6.       

### 6.1     (User)
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

### 6.2     (Site)
```sql
CREATE TABLE sites (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    location VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 6.3     (Feedback)
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

## 7.     

### 7.1 REST API   
- `GET /api/users/{userId}/visited-sites` -             
-    ：Authorization Bearer Token
-     ：JSON

### 7.2     
```json
{
    "success": true,
    "data": [
        {
            "siteId": 1,
            "siteName": "     ",
            "visitedDate": "2024-01-15",
            "feedbackRating": 5,
            "feedbackComment": "      ！"
        },
        {
            "siteId": 2,
            "siteName": "   ",
            "visitedDate": "2024-01-16",
            "feedbackRating": 4,
            "feedbackComment": "      "
        }
    ],
    "total": 2
}
```

## 8.     

### 8.1       
1.      
2.      
3.        
4.        

### 8.2       
```json
{
    "success": false,
    "error": {
        "code": "UNAUTHORIZED",
        "message": "           ",
        "details": "     "
    }
}
```

## 9.     

### 9.1     
-        
-        
-       

### 9.2     
- API    
-        
-       

### 9.3     
-       
-       
-        

## 10.     

### 10.1     
- Java Runtime Environment 11+
-       
- Web   （Tomcat/Undertow）

### 10.2     
-       
-        
-     

## 11.     

### 11.1     
-        
-       
-     

### 11.2     
-        
-       
-       

## 12.     

### 12.1     
-       
-       
-     （CSV/PDF）

### 12.2     
-        
-      （Redis）
-       

---

**      **: 2025-12-27
**    **: Alice (    )
**   **:           PRD  
