/**
 * Student.java
 * 学生类，包含学生的基本信息和相关操作
 */
import java.util.Objects;

public class Student {
    private int id;              // 学生ID
    private String name;         // 学生姓名
    private int classId;         // 所在班级ID
    private int age;             // 年龄
    private String email;        // 邮箱（可选）
    private String address;      // 地址（可选）

    /**
     * 默认构造函数
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
     * 带参数的构造函数
     * @param id 学生ID
     * @param name 学生姓名
     * @param classId 班级ID
     * @param age 年龄
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
     * 完整的构造函数
     * @param id 学生ID
     * @param name 学生姓名
     * @param classId 班级ID
     * @param age 年龄
     * @param email 邮箱
     * @param address 地址
     */
    public Student(int id, String name, int classId, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    // Getter 和 Setter 方法

    /**
     * 获取学生ID
     * @return 学生ID
     */
    public int getId() {
        return id;
    }

    /**
     * 设置学生ID
     * @param id 学生ID
     */
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("学生ID不能为负数");
        }
        this.id = id;
    }

    /**
     * 获取学生姓名
     * @return 学生姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置学生姓名
     * @param name 学生姓名
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("学生姓名不能为空");
        }
        this.name = name.trim();
    }

    /**
     * 获取班级ID
     * @return 班级ID
     */
    public int getClassId() {
        return classId;
    }

    /**
     * 设置班级ID
     * @param classId 班级ID
     */
    public void setClassId(int classId) {
        if (classId < 0) {
            throw new IllegalArgumentException("班级ID不能为负数");
        }
        this.classId = classId;
    }

    /**
     * 获取学生年龄
     * @return 年龄
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置学生年龄
     * @param age 年龄
     */
    public void setAge(int age) {
        if (age < 5 || age > 25) {
            throw new IllegalArgumentException("年龄必须在5-25岁之间");
        }
        this.age = age;
    }

    /**
     * 获取学生邮箱
     * @return 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置学生邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email != null ? email.trim() : "";
    }

    /**
     * 获取学生地址
     * @return 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置学生地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address != null ? address.trim() : "";
    }

    /**
     * 显示学生信息
     */
    public void displayInfo() {
        System.out.println("学生ID: " + id);
        System.out.println("姓名: " + name);
        System.out.println("班级ID: " + classId);
        System.out.println("年龄: " + age);
        if (!email.isEmpty()) {
            System.out.println("邮箱: " + email);
        }
        if (!address.isEmpty()) {
            System.out.println("地址: " + address);
        }
    }

    /**
     * 获取简洁的学生信息
     * @return 格式化字符串
     */
    public String getSimpleInfo() {
        return String.format("ID:%d 姓名:%s (班级ID:%d, 年龄:%d)", id, name, classId, age);
    }

    /**
     * 判断学生是否属于指定班级
     * @param targetClassId 目标班级ID
     * @return 如果属于该班级则返回true，否则返回false
     */
    public boolean belongsToClass(int targetClassId) {
        return this.classId == targetClassId;
    }

    /**
     * 重写equals方法，基于ID比较学生对象
     * @param obj 要比较的对象
     * @return 如果ID相同则返回true
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id;
    }

    /**
     * 重写hashCode方法，基于ID生成哈希值
     * @return 哈希值
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * 重写toString方法，提供学生信息的字符串表示
     * @return 学生信息字符串
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
     * 验证学生信息是否完整
     * @return 如果ID和姓名不为空，则返回true
     */
    public boolean isValid() {
        return id > 0 && name != null && !name.trim().isEmpty();
    }
}