/**
 * Student  -     
 *                  
 */
public class Student {
    private String studentId;
    private String name;
    private int grade;
    private String className;
    private Parent parent;
    
    /**
     *     
     * @param studentId   ID
     * @param name     
     * @param parent     
     */
    public Student(String studentId, String name, Parent parent) {
        this.studentId = studentId;
        this.name = name;
        this.parent = parent;
        this.grade = 1; //     
        this.className = "A "; //     
    }
    
    /**
     *       
     * @param studentId   ID
     * @param name     
     * @param grade   
     * @param className     
     * @param parent     
     */
    public Student(String studentId, String name, int grade, String className, Parent parent) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.className = className;
        this.parent = parent;
    }
    
    /**
     *             
     * @return       
     */
    public boolean validate() {
        if (studentId == null || studentId.trim().isEmpty()) {
            System.out.println("  ：  ID    。");
            return false;
        }
        
        if (name == null || name.trim().isEmpty()) {
            System.out.println("  ：        。");
            return false;
        }
        
        if (parent == null) {
            System.out.println("  ：            。");
            return false;
        }
        
        if (!parent.validate()) {
            System.out.println("  ：      。");
            return false;
        }
        
        if (grade < 1 || grade > 12) {
            System.out.println("  ：    1-12   。");
        }
        
        return true;
    }
    
    /**
     *         
     * @return          
     */
    public String getFullInfo() {
        return String.format("  ID: %s |   : %s |   : %d |   : %s |   : %s",
                           studentId, name, grade, className, parent.getName());
    }
    
    // Getter Setter  
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getGrade() {
        return grade;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public Parent getParent() {
        return parent;
    }
    
    public void setParent(Parent parent) {
        this.parent = parent;
    }
    
    @Override
    public String toString() {
        return String.format("Student{studentId='%s', name='%s', grade=%d, className='%s', parent=%s}",
                           studentId, name, grade, className, parent);
    }
}

/**
 * Parent  -       
 *               
 */
class Parent {
    private String parentId;
    private String name;
    private String email;
    private String phone;
    
    /**
     *     
     * @param parentId   ID
     * @param name     
     * @param email     
     */
    public Parent(String parentId, String name, String email) {
        this.parentId = parentId;
        this.name = name;
        this.email = email;
        this.phone = "   ";
    }
    
    /**
     *       
     * @param parentId   ID
     * @param name     
     * @param email     
     * @param phone     
     */
    public Parent(String parentId, String name, String email, String phone) {
        this.parentId = parentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    /**
     *           
     * @return       
     */
    public boolean validate() {
        if (parentId == null || parentId.trim().isEmpty()) {
            System.out.println("  ：  ID    。");
            return false;
        }
        
        if (name == null || name.trim().isEmpty()) {
            System.out.println("  ：        。");
            return false;
        }
        
        if (email == null || email.trim().isEmpty()) {
            System.out.println("  ：        。");
            return false;
        }
        
        //          
        if (!email.contains("@")) {
            System.out.println("  ：         ：" + email);
        }
        
        return true;
    }
    
    /**
     *         
     * @return          
     */
    public String getContactInfo() {
        return String.format("  : %s |   : %s |   : %s", name, email, phone);
    }
    
    // Getter Setter  
    
    public String getParentId() {
        return parentId;
    }
    
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return String.format("Parent{parentId='%s', name='%s', email='%s', phone='%s'}",
                           parentId, name, email, phone);
    }
}