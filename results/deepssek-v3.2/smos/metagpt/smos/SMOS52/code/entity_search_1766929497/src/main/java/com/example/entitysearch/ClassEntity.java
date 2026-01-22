package com.example.entitysearch;

/**
 *      
 *           ，   Entity  
 *             
 */
public class ClassEntity extends Entity {
    private String teacherName;      //      
    private int studentCount;        //     
    private String grade;            //   
    private String department;       //     
    
    /**
     *     
     * 
     * @param id       
     * @param name     
     * @param description     
     * @param teacherName      
     * @param studentCount     
     * @param grade   
     * @param department     
     */
    public ClassEntity(String id, String name, String description, 
                      String teacherName, int studentCount, String grade, String department) {
        super(id, name, description);
        this.teacherName = teacherName;
        this.studentCount = studentCount;
        this.grade = grade;
        this.department = department;
    }
    
    /**
     *       
     *          
     * 
     * @return         "  "
     */
    @Override
    public String getType() {
        return "  ";
    }
    
    /**
     *                 
     *       ，           
     * 
     * @param keyword        
     * @return            true，    false
     */
    @Override
    public boolean containsKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        
        String lowerKeyword = keyword.toLowerCase().trim();
        
        //            
        if (super.containsKeyword(keyword)) {
            return true;
        }
        
        //               
        if (teacherName != null && teacherName.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //            
        if (grade != null && grade.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //            
        if (department != null && department.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //       （        ）
        try {
            int keywordNumber = Integer.parseInt(keyword);
            if (studentCount == keywordNumber) {
                return true;
            }
        } catch (NumberFormatException e) {
            //        ，  
        }
        
        return false;
    }
    
    /**
     *            
     *       ，          
     * 
     * @return           
     */
    @Override
    public String getDisplayInfo() {
        return name + " (" + grade + "  ) -    : " + teacherName + 
               ",   : " + studentCount + " ,   : " + department;
    }
    
    /**
     *          
     *       ，         
     * 
     * @return           
     */
    @Override
    public String getDetailedInfo() {
        return getType() + " ID: " + id + 
               ",   : " + name + 
               ",   : " + description + 
               ",    : " + teacherName + 
               ",     : " + studentCount + 
               ",   : " + grade + 
               ",   : " + department;
    }
    
    // Getter Setter  
    
    public String getTeacherName() {
        return teacherName;
    }
    
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    
    public int getStudentCount() {
        return studentCount;
    }
    
    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
}