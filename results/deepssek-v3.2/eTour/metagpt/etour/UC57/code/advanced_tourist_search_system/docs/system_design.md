##     

  PRD    ，               ：

###     ：
1. **    **：Spring Boot 3.x -      Java Web    ，  RESTful API、    、AOP 
2. **     **：
   - PostgreSQL + PostGIS -     ，      （      ）
   - Redis -    ，           
   - Elasticsearch -     ，           
3. **    **：Spring Security + JWT -          
4. **API  **：OpenAPI 3.0 (SpringDoc) -     API  
5. **    **：Maven -          
6. **    **：JUnit 5 + Mockito + Testcontainers -          

###       ：
1. **     **：          、    、          
2. **    **：  CompletableFuture Spring WebFlux        
3. **    **：    （     + Redis     ）      
4. **    **：  Resilience4j    、     
5. **    **：  PostGIS      ，           

###     ：
1. **    **：     （B-tree, GiST, GIN）、    、   
2. **  IO**：     IO       
3. **   **：HikariCP      ，      
4. **    **：        ，       

##     

###   Java   （src/main/java/com/tourist/search/）:
- **config/** -    
  - SecurityConfig.java - Spring Security  
  - RedisConfig.java - Redis  
  - ElasticsearchConfig.java - Elasticsearch  
  - DatabaseConfig.java -      
  - OpenApiConfig.java - OpenAPI  

- **controller/** -     
  - AuthController.java -       API
  - SearchController.java -     API
  - LocationController.java -     API
  - UserController.java -       API

- **service/** -    
  - impl/ -     
    - AuthServiceImpl.java -       
    - SearchServiceImpl.java -       
    - LocationServiceImpl.java -       
    - UserServiceImpl.java -       
  - interface/ -     
    - AuthService.java
    - SearchService.java
    - LocationService.java
    - UserService.java

- **repository/** -      
  - UserRepository.java -       
  - AttractionRepository.java -       
  - SearchHistoryRepository.java -       
  - LocationRepository.java -       

- **model/** -     
  - dto/ -       
    - UserDTO.java -     DTO
    - SearchRequestDTO.java -     DTO
    - SearchResultDTO.java -     DTO
    - LocationDTO.java -     DTO
  - entity/ -    
    - User.java -     
    - Attraction.java -     
    - SearchHistory.java -       
    - Location.java -     
  - request/ -     
    - LoginRequest.java -     
    - AdvancedSearchRequest.java -       
  - response/ -     
    - ApiResponse.java -   API  
    - SearchResponse.java -     

- **security/** -     
  - JwtAuthenticationFilter.java - JWT     
  - JwtTokenProvider.java - JWT     
  - UserPrincipal.java -       
  - CustomUserDetailsService.java -          

- **util/** -    
  - GeoUtils.java -        
  - DateTimeUtils.java -        
  - ValidationUtils.java -      
  - CacheUtils.java -      

- **exception/** -     
  - GlobalExceptionHandler.java -        
  - CustomException.java -      
  - ErrorCode.java -      

- **component/** -   
  - EtourClient.java - ETOUR      （      ）
  - LocationProcessor.java -      
  - SearchEngine.java -       

- **scheduler/** -     
  - CacheWarmupScheduler.java -       
  - DataSyncScheduler.java -       

- **AdvancedSearchApplication.java** -     

###     （src/main/resources/）:
- application.yml -      
- application-dev.yml -       
- application-prod.yml -       
- logback-spring.xml -     
- sql/     
  - schema.sql -       
  - data.sql -     

###     （src/test/java/）:
- controller/ -      
- service/ -      
- integration/ -     
- util/ -      

##        

       ...

##       

       ...

##        

1. **ETOUR     **：    ETOUR      API         
2. **     **：        GPS、IP        
3. **      **：                  
4. **      **：     ETOUR            
5. **      **：            
6. **    **：GDPR              
7. **      **：             
8. **    **：             

 ：       PRD     ，     、  、    、         。          ，          、         。