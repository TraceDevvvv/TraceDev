package com.example.entitysearch;

import java.util.ArrayList;
import java.util.List;

/**
 *      
 *          ，    、  、       
 *       ，                
 */
public class MockData {
    
    /**
     *         
     *             ，        
     * 
     * @return       
     */
    public List<ClassEntity> getAllClassEntities() {
        List<ClassEntity> classes = new ArrayList<>();
        
        //         
        classes.add(new ClassEntity(
            "CLASS-001",
            "     2023 1 ",
            "       2023 1 ，       ",
            "   ",
            45,
            "2023",
            "          "
        ));
        
        classes.add(new ClassEntity(
            "CLASS-002",
            "    2022 2 ",
            "      2022 2 ，           ",
            "   ",
            38,
            "2022",
            "    "
        ));
        
        classes.add(new ClassEntity(
            "CLASS-003",
            "    2023    ",
            "      2023    ，           ",
            "   ",
            30,
            "2023",
            "      "
        ));
        
        classes.add(new ClassEntity(
            "CLASS-004",
            "    2021 1 ",
            "      2021 1 ，         ",
            "   ",
            42,
            "2021",
            "      "
        ));
        
        classes.add(new ClassEntity(
            "CLASS-005",
            "    2022    ",
            "      2022    ，          ",
            "   ",
            28,
            "2022",
            "      "
        ));
        
        classes.add(new ClassEntity(
            "CLASS-006",
            "    2023 1 ",
            "      2023 1 ，          ",
            "   ",
            35,
            "2023",
            "      "
        ));
        
        classes.add(new ClassEntity(
            "CLASS-007",
            "     2022 2 ",
            "       2022 2 ，             ",
            "   ",
            40,
            "2022",
            "       "
        ));
        
        classes.add(new ClassEntity(
            "CLASS-008",
            "      2023 1 ",
            "        2023 1 ，           ",
            "   ",
            32,
            "2023",
            "      "
        ));
        
        return classes;
    }
    
    /**
     *         
     *             ，        
     * 
     * @return       
     */
    public List<Teaching> getAllTeachingEntities() {
        List<Teaching> teachings = new ArrayList<>();
        
        //         
        teachings.add(new Teaching(
            "TEACH-001",
            "Java    ",
            "Java        ，     ",
            "Java    ",
            "T001",
            "   ",
            48,
            "2023    ",
            "   A101"
        ));
        
        teachings.add(new Teaching(
            "TEACH-002",
            "       ",
            "         ，           ",
            "       ",
            "T002",
            "   ",
            64,
            "2023    ",
            "   B205"
        ));
        
        teachings.add(new Teaching(
            "TEACH-003",
            "       ",
            "              ",
            "       ",
            "T003",
            "   ",
            56,
            "2023    ",
            "   C301"
        ));
        
        teachings.add(new Teaching(
            "TEACH-004",
            "     ",
            "            ",
            "     ",
            "T004",
            "   ",
            60,
            "2023    ",
            "   D102"
        ));
        
        teachings.add(new Teaching(
            "TEACH-005",
            "      ",
            "             ",
            "      ",
            "T005",
            "   ",
            48,
            "2023    ",
            "       "
        ));
        
        teachings.add(new Teaching(
            "TEACH-006",
            "      ",
            "              ",
            "      ",
            "T006",
            "   ",
            72,
            "2023    ",
            "       "
        ));
        
        teachings.add(new Teaching(
            "TEACH-007",
            "Web    ",
            "  Web        ",
            "Web    ",
            "T007",
            "   ",
            40,
            "2023    ",
            "   E201"
        ));
        
        teachings.add(new Teaching(
            "TEACH-008",
            "      ",
            "Android iOS        ",
            "      ",
            "T008",
            "   ",
            56,
            "2023    ",
            "       "
        ));
        
        return teachings;
    }
    
    /**
     *         
     *             ，        
     * 
     * @return       
     */
    public List<Address> getAllAddressEntities() {
        List<Address> addresses = new ArrayList<>();
        
        //         
        addresses.add(new Address(
            "ADDR-001",
            "      ",
            "        ，    ，      ",
            "   123 ",
            "   ",
            "   ",
            "100000",
            "  ",
            "     "
        ));
        
        addresses.add(new Address(
            "ADDR-002",
            "       ",
            "            ，        ",
            "   456 ",
            "   ",
            "   ",
            "100001",
            "  ",
            "       "
        ));
        
        addresses.add(new Address(
            "ADDR-003",
            "    1  ",
            "      ，      ",
            "   789 ",
            "   ",
            "   ",
            "100002",
            "  ",
            "    1  "
        ));
        
        addresses.add(new Address(
            "ADDR-004",
            "     ",
            "        ，        ",
            "   101 ",
            "   ",
            "   ",
            "100003",
            "  ",
            "     "
        ));
        
        addresses.add(new Address(
            "ADDR-005",
            "    ",
            "      ，         ",
            "   202 ",
            "   ",
            "   ",
            "100004",
            "  ",
            "    "
        ));
        
        addresses.add(new Address(
            "ADDR-006",
            "   A ",
            "       ，         ",
            "   303 ",
            "   ",
            "   ",
            "100005",
            "  ",
            "   A "
        ));
        
        addresses.add(new Address(
            "ADDR-007",
            "      ",
            "             ",
            "   404 ",
            "   ",
            "   ",
            "100006",
            "  ",
            "      "
        ));
        
        addresses.add(new Address(
            "ADDR-008",
            "      ",
            "             ",
            "   505 ",
            "   ",
            "   ",
            "100007",
            "  ",
            "      "
        ));
        
        return addresses;
    }
    
    /**
     *         
     *             ，        
     * 
     * @return       
     */
    public List<User> getAllUserEntities() {
        List<User> users = new ArrayList<>();
        
        //         
        users.add(new User(
            "USER-001",
            "  ",
            "     ，      ",
            "zhangsan",
            "zhangsan@university.edu",
            "   ",
            "13800138001",
            true,
            "     "
        ));
        
        users.add(new User(
            "USER-002",
            "  ",
            "       ，    ：    ",
            "lisi",
            "lisi@university.edu",
            "  ",
            "13800138002",
            true,
            "          "
        ));
        
        users.add(new User(
            "USER-003",
            "  ",
            "      ，  Java  ",
            "wangwu",
            "wangwu@university.edu",
            "  ",
            "13800138003",
            true,
            "    "
        ));
        
        users.add(new User(
            "USER-004",
            "  ",
            "  ，       2023 ",
            "zhaoliu",
            "zhaoliu@student.university.edu",
            "  ",
            "13800138004",
            true,
            "          "
        ));
        
        users.add(new User(
            "USER-005",
            "  ",
            "      ，      ",
            "liuqi",
            "liuqi@university.edu",
            "     ",
            "13800138005",
            true,
            "   "
        ));
        
        users.add(new User(
            "USER-006",
            "  ",
            "       ，      ",
            "chenba",
            "chenba@university.edu",
            "    ",
            "13800138006",
            true,
            "   "
        ));
        
        users.add(new User(
            "USER-007",
            "  ",
            "     ，        ",
            "sunjiu",
            "sunjiu@university.edu",
            "   ",
            "13800138007",
            true,
            "    "
        ));
        
        users.add(new User(
            "USER-008",
            "  ",
            "    ，    ",
            "zhoushi",
            "zhoushi@student.university.edu",
            "    ",
            "13800138008",
            true,
            "    "
        ));
        
        users.add(new User(
            "USER-009",
            "   ",
            "     ，     ",
            "wushiyi",
            "wushiyi@former.university.edu",
            "   ",
            "13800138009",
            false,
            " "
        ));
        
        users.add(new User(
            "USER-010",
            "   ",
            "  ，      ",
            "zhengshier",
            "zhengshier@new.university.edu",
            "  ",
            "13800138010",
            false,
            "   "
        ));
        
        return users;
    }
    
    /**
     *       （    ）
     *              
     * 
     * @return          
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
     *         
     *               
     * 
     * @return             
     */
    public String getEntityStatistics() {
        int classCount = getAllClassEntities().size();
        int teachingCount = getAllTeachingEntities().size();
        int addressCount = getAllAddressEntities().size();
        int userCount = getAllUserEntities().size();
        int totalCount = classCount + teachingCount + addressCount + userCount;
        
        return String.format(
            "      ：  =%d,   =%d,   =%d,   =%d,   =%d",
            classCount, teachingCount, addressCount, userCount, totalCount
        );
    }
    
    /**
     *        
     *           
     */
    public void initializeMockData() {
        System.out.println("         ！");
        System.out.println(getEntityStatistics());
        System.out.println("         ，       。");
    }
    
    /**
     *   ID      
     *           ID   
     * 
     * @param entityId   ID
     * @return      ，        null
     */
    public Entity findEntityById(String entityId) {
        if (entityId == null || entityId.isEmpty()) {
            return null;
        }
        
        //         
        for (Entity entity : getAllEntities()) {
            if (entityId.equals(entity.getId())) {
                return entity;
            }
        }
        
        return null;
    }
    
    /**
     *         
     *                  
     * 
     * @param name     （     ）
     * @return        
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