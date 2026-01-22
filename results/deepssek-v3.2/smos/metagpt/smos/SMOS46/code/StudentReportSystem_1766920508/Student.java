/**
 * Student.java
 *    ，              
 */
import java.util.Objects;

public class Student {
    private int id;              //   ID
    private String name;         //     
    private int classId;         //     ID
    private int age;             //   
    private String email;        //   （  ）
    private String address;      //   （  ）

    /**
     *       
     */
    public Student() {
        this.id = 0;
        this.name = "";
        this.classId = 0;
        this.age = 0;
        this.email = "";
        this.address = "";
    }

    /**
     *         
     * @param id   ID
     * @param name     
     * @param classId   ID
     * @param age   
     */
    public Student(int id, String name, int classId, int age) {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.age = age;
        this.email = "";
        this.address = "";
    }

    /**
     *        
     * @param id   ID
     * @param name     
     * @param classId   ID
     * @param age   
     * @param email   
     * @param address   
     */
    public Student(int id, String name, int classId, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    // Getter   Setter   

    /**
     *     ID
     * @return   ID
     */
    public int getId() {
        return id;
    }

    /**
     *     ID
     * @param id   ID
     */
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("  ID     ");
        }
        this.id = id;
    }

    /**
     *       
     * @return     
     */
    public String getName() {
        return name;
    }

    /**
     *       
     * @param name     
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("        ");
        }
        this.name = name.trim();
    }

    /**
     *     ID
     * @return   ID
     */
    public int getClassId() {
        return classId;
    }

    /**
     *     ID
     * @param classId   ID
     */
    public void setClassId(int classId) {
        if (classId < 0) {
            throw new IllegalArgumentException("  ID     ");
        }
        this.classId = classId;
    }

    /**
     *       
     * @return   
     */
    public int getAge() {
        return age;
    }

    /**
     *       
     * @param age   
     */
    public void setAge(int age) {
        if (age < 5 || age > 25) {
            throw new IllegalArgumentException("     5-25   ");
        }
        this.age = age;
    }

    /**
     *       
     * @return   
     */
    public String getEmail() {
        return email;
    }

    /**
     *       
     * @param email   
     */
    public void setEmail(String email) {
        this.email = email != null ? email.trim() : "";
    }

    /**
     *       
     * @return   
     */
    public String getAddress() {
        return address;
    }

    /**
     *       
     * @param address   
     */
    public void setAddress(String address) {
        this.address = address != null ? address.trim() : "";
    }

    /**
     *       
     */
    public void displayInfo() {
        System.out.println("  ID: " + id);
        System.out.println("  : " + name);
        System.out.println("  ID: " + classId);
        System.out.println("  : " + age);
        if (!email.isEmpty()) {
            System.out.println("  : " + email);
        }
        if (!address.isEmpty()) {
            System.out.println("  : " + address);
        }
    }

    /**
     *          
     * @return       
     */
    public String getSimpleInfo() {
        return String.format("ID:%d   :%s (  ID:%d,   :%d)", id, name, classId, age);
    }

    /**
     *             
     * @param targetClassId     ID
     * @return           true，    false
     */
    public boolean belongsToClass(int targetClassId) {
        return this.classId == targetClassId;
    }

    /**
     *   equals  ，  ID      
     * @param obj       
     * @return   ID     true
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id;
    }

    /**
     *   hashCode  ，  ID     
     * @return    
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     *   toString  ，            
     * @return        
     */
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classId=" + classId +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    /**
     *           
     * @return   ID      ，   true
     */
    public boolean isValid() {
        return id > 0 && name != null && !name.trim().isEmpty();
    }
}