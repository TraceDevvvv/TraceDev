'''
Represents a school class entity with all relevant attributes.
'''
public class Class {
    private int id;
    private String name;
    private String description;
    private String teacher;
    private String room;
    private int maxStudents;
    public Class(String name, String description, String teacher, String room, int maxStudents) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.room = room;
        this.maxStudents = maxStudents;
    }
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getTeacher() { return teacher; }
    public void setTeacher(String teacher) { this.teacher = teacher; }
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }
    @Override
    public String toString() {
        return String.format("Class{id=%d, name='%s', teacher='%s', room='%s'}", 
            id, name, teacher, room);
    }
}