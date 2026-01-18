package infrastructure.dao;

/**
 * Data Access Object for Report Card information.
 * Represents the structure of report card data as stored in the persistence layer.
 * Note: 'gradesData' is stored as a string, implying it might be a serialized format
 * like JSON or a custom delimited string in the database.
 */
public class ReportCardDAO {
    public String id;
    public String studentId;
    public String quadrimestreId;
    public String gradesData; // e.g., "Math:A,Science:B,History:C"
    public String comments;

    /**
     * Constructs a new ReportCardDAO.
     * @param id The unique identifier of the report card.
     * @param studentId The ID of the student.
     * @param quadrimestreId The ID of the quadrimestre.
     * @param gradesData A string representing grades (e.g., "Math:A,Science:B").
     * @param comments Any comments for the report card.
     */
    public ReportCardDAO(String id, String studentId, String quadrimestreId, String gradesData, String comments) {
        this.id = id;
        this.studentId = studentId;
        this.quadrimestreId = quadrimestreId;
        this.gradesData = gradesData;
        this.comments = comments;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getQuadrimestreId() {
        return quadrimestreId;
    }

    public String getGradesData() {
        return gradesData;
    }

    public String getComments() {
        return comments;
    }
}