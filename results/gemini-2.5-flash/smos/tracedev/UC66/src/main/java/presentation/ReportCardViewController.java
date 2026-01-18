package presentation;

import application.ReportCardService;
import domain.dto.AcademicYearDTO;
import domain.dto.ClassSummaryDTO;
import domain.dto.QuadrimestreDTO;
import domain.dto.ReportCardDTO;
import domain.dto.StudentSummaryDTO;

import java.util.List;

/**
 * Controller for managing the Report Card viewing workflow.
 * It acts as an intermediary between the user interface (View) and the application logic (Service).
 * It holds the state of the selections made by the user.
 */
public class ReportCardViewController {

    private final ReportCardService reportCardService;
    private final ReportCardView reportCardView;

    // State variables to keep track of user selections
    private String selectedAcademicYearId;
    private String selectedClassId;
    private String selectedStudentId;
    private String selectedQuadrimestreId;

    /**
     * Constructs a ReportCardViewController.
     * @param reportCardService The application service responsible for report card data.
     * @param reportCardView The view component responsible for displaying information.
     */
    public ReportCardViewController(ReportCardService reportCardService, ReportCardView reportCardView) {
        this.reportCardService = reportCardService;
        this.reportCardView = reportCardView;
    }

    /**
     * Initiates the process of selecting an academic year.
     * This method fetches available academic years and instructs the view to display them.
     */
    public void selectAcademicYear() {
        System.out.println("Controller: Step 1: Direction selects the academic year."); // Traces m3
        System.out.println("ReportCardController: Direction requests to select an academic year.");
        // Call the service to get academic years
        List<AcademicYearDTO> years = reportCardService.getAcademicYears();
        // Instruct the view to display them
        reportCardView.displayAcademicYears(years);
    }

    /**
     * Handles the event when an academic year is selected by the user.
     * It stores the selected year and then fetches and displays classes for that year.
     * @param yearId The ID of the selected academic year.
     */
    public void yearSelected(String yearId) {
        System.out.println("ReportCardController: Academic year " + yearId + " selected.");
        this.selectedAcademicYearId = yearId; // Store selected year
        System.out.println("Controller: Step 2: System displays classes."); // Traces m12
        // Call the service to get classes for the selected academic year
        List<ClassSummaryDTO> classes = reportCardService.getClassesByAcademicYear(yearId);
        // Instruct the view to display them
        reportCardView.displayClasses(classes);
    }

    /**
     * Handles the event when a class is selected by the user.
     * It stores the selected class and then fetches and displays students for that class.
     * @param classId The ID of the selected class.
     */
    public void classSelected(String classId) {
        System.out.println("Controller: Step 3: Direction chooses the pupil class."); // Traces m21
        System.out.println("ReportCardController: Class " + classId + " selected.");
        this.selectedClassId = classId; // Store selected class
        System.out.println("Controller: Step 4: System displays class students."); // Traces m22
        // Call the service to get students for the selected class
        List<StudentSummaryDTO> students = reportCardService.getStudentsByClass(classId);
        // Instruct the view to display them
        reportCardView.displayStudents(students);
    }

    /**
     * Handles the event when a student is selected by the user.
     * It stores the selected student and then fetches and displays available quadrimestres.
     * @param studentId The ID of the selected student.
     */
    public void studentSelected(String studentId) {
        System.out.println("Controller: Step 5: Direction selects the student."); // Traces m31
        System.out.println("ReportCardController: Student " + studentId + " selected.");
        this.selectedStudentId = studentId; // Store selected student
        System.out.println("Controller: Step 6: Direction selects quadrimestre."); // Traces m32
        // Call the service to get quadrimestres
        List<QuadrimestreDTO> quadrimestres = reportCardService.getQuadrimestres();
        // Instruct the view to display them
        reportCardView.displayQuadrimestres(quadrimestres);
    }

    /**
     * Handles the event when a quadrimestre is selected by the user.
     * It stores the selected quadrimestre. This completes the selection process required
     * before viewing a report card.
     * @param quadrimestreId The ID of the selected quadrimestre.
     */
    public void quadrimestreSelected(String quadrimestreId) {
        System.out.println("ReportCardController: Quadrimestre " + quadrimestreId + " selected.");
        this.selectedQuadrimestreId = quadrimestreId; // Store selected quadrimestre
        System.out.println("Controller: Controller stores selected student and quadrimestre."); // Traces m41
        enableViewReportCardButton(); // Explicitly call the method to trace m42
    }

    /**
     * Simulates enabling the 'View Report Card' button in the UI.
     * Traces message m42.
     */
    private void enableViewReportCardButton() {
        System.out.println("ReportCardController: enableViewReportCardButton() called. 'View Report Card' button is now enabled (simulated)."); // Traces m42
    }

    /**
     * Initiates the display of the report card based on the current selections.
     * This method is typically called when the user clicks a "View Report Card" button.
     * It uses the stored student and quadrimestre IDs to request the report card from the service.
     */
    public void viewReportCard() {
        System.out.println("Controller: Step 7: Direction clicks on the relevant key (view report button)."); // Traces m44
        System.out.println("ReportCardController: Direction requests to view the report card.");
        if (selectedStudentId == null || selectedQuadrimestreId == null) {
            System.out.println("ReportCardController: Error: Student or Quadrimestre not selected. Cannot view report card.");
            reportCardView.displayReportCard(null); // Display an error or empty report card
            return;
        }

        // Call the service to generate the report card using the stored selections
        ReportCardDTO reportCard = reportCardService.generateReportCard(selectedStudentId, selectedQuadrimestreId);
        System.out.println("Controller: Step 8: System displays the chosen student report."); // Traces m45
        // Instruct the view to display the generated report card
        reportCardView.displayReportCard(reportCard);
    }
}