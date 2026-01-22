package com.example.entitysearch;

/**
 *      
 *           ，   Entity  
 *             ，   、  、   
 */
public class Teaching extends Entity {
    private String courseName;       //     
    private String teacherId;        //   ID
    private String teacherName;      //     
    private int creditHours;         //   
    private String semester;         //   
    private String classroom;        //   
    
    /**
     *     
     * 
     * @param id       
     * @param name     （         ）
     * @param description     
     * @param courseName     
     * @param teacherId   ID
     * @param teacherName     
     * @param creditHours   
     * @param semester   
     * @param classroom   
     */
    public Teaching(String id, String name, String description, 
                   String courseName, String teacherId, String teacherName,
                   int creditHours, String semester, String classroom) {
        super(id, name, description);
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.creditHours = creditHours;
        this.semester = semester;
        this.classroom = classroom;
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
        if (courseName != null && courseName.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //     ID       
        if (teacherId != null && teacherId.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //              
        if (teacherName != null && teacherName.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //            
        if (semester != null && semester.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //            
        if (classroom != null && classroom.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //     （        ）
        try {
            int keywordNumber = Integer.parseInt(keyword);
            if (creditHours == keywordNumber) {
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
        return courseName + " -   : " + teacherName + 
               ",   : " + semester + 
               ",   : " + classroom + 
               ",   : " + creditHours + "  ";
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
               ",     : " + name + 
               ",   : " + description + 
               ",     : " + courseName + 
               ",   ID: " + teacherId + 
               ",     : " + teacherName + 
               ",   : " + creditHours + 
               ",   : " + semester + 
               ",   : " + classroom;
    }
    
    // Getter Setter  
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getTeacherId() {
        return teacherId;
    }
    
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    
    public String getTeacherName() {
        return teacherName;
    }
    
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    
    public int getCreditHours() {
        return creditHours;
    }
    
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
    
    public String getSemester() {
        return semester;
    }
    
    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    public String getClassroom() {
        return classroom;
    }
    
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}