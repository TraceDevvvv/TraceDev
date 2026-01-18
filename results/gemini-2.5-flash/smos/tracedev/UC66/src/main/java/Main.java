import application.ReportCardService;
import infrastructure.repository.*;
import presentation.ReportCardView;
import presentation.ReportCardViewController;

/**
 * Main class to simulate the application execution flow as described by the sequence diagram.
 * It sets up the dependencies and simulates user interactions with the ReportCardViewController.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Application starting up...");

        // 1. Instantiate Infrastructure Layer (Repositories)
        IAcademicYearRepository academicYearRepository = new AcademicYearRepository();
        IClassRepository classRepository = new ClassRepository();
        IStudentRepository studentRepository = new StudentRepository();
        IQuadrimestreRepository quadrimestreRepository = new QuadrimestreRepository();
        IReportCardRepository reportCardRepository = new ReportCardRepository();

        // 2. Instantiate Application Layer (Service)
        ReportCardService reportCardService = new ReportCardService(
            academicYearRepository,
            classRepository,
            studentRepository,
            quadrimestreRepository,
            reportCardRepository
        );

        // 3. Instantiate Presentation Layer (View and Controller)
        ReportCardView reportCardView = new ReportCardView();
        ReportCardViewController reportCardViewController = new ReportCardViewController(
            reportCardService,
            reportCardView
        );

        System.out.println("\n--- Simulating User Interaction (Direction) ---");
        System.out.println("Note: Direction IS logged in and 'Online report cards' button IS activated (simulated).");

        // Direction -> Controller: selectAcademicYear()
        System.out.println("\nDirection (User) calls: selectAcademicYear()");
        reportCardViewController.selectAcademicYear();
        // Direction <-- View: Displays academic years list (console output)

        // Simulating user choosing "AY002" (2023-2024)
        String selectedYearId = "AY002";
        System.out.println("\nDirection (User) chooses academic year: " + selectedYearId);
        // Direction -> Controller: yearSelected(yearId)
        reportCardViewController.yearSelected(selectedYearId);
        // Direction <-- View: Displays class list (console output)

        // Simulating user choosing "C001" (10A)
        String selectedClassId = "C001";
        System.out.println("\nDirection (User) chooses class: " + selectedClassId);
        // Direction -> Controller: classSelected(classId)
        reportCardViewController.classSelected(selectedClassId);
        // Direction <-- View: Displays list of class students (console output)

        // Simulating user choosing "S001" (Alice Smith)
        String selectedStudentId = "S001";
        System.out.println("\nDirection (User) chooses student: " + selectedStudentId);
        // Direction -> Controller: studentSelected(studentId)
        reportCardViewController.studentSelected(selectedStudentId);
        // Direction <-- View: Displays available quadrimestres (console output)

        // Simulating user choosing "Q1" (First Quadrimestre)
        String selectedQuadrimestreId = "Q1";
        System.out.println("\nDirection (User) chooses quadrimestre: " + selectedQuadrimestreId);
        // Direction -> Controller: quadrimestreSelected(quadrimestreId)
        reportCardViewController.quadrimestreSelected(selectedQuadrimestreId);

        // Simulating user clicking "View Report Card"
        System.out.println("\nDirection (User) clicks: viewReportCard()");
        // Direction -> Controller: viewReportCard()
        reportCardViewController.viewReportCard();
        // Direction <-- View: A student's report IS displayed by the system (console output)

        System.out.println("\n--- End of Simulation ---");
        System.out.println("Note: The user connection to the SMOS server IS stopped.");
        System.out.println("Note: The SMOS server connection IS interrupted. (Simulated Exit Conditions)");
    }
}