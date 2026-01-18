import application.AcademicRecordService;
import dataaccess.AcademicRecordRepositoryImpl;
import dataaccess.DigitalArchive;
import dataaccess.IAcademicRecordRepository;
import presentation.DigitalRegisterController;
import presentation.DigitalRegisterView;

/**
 * Main application class to demonstrate the interaction between components
 * based on the provided Class and Sequence Diagrams.
 * This acts as the entry point and orchestrator for the simulation.
 */
public class MainApp {

    public static void main(String[] args) {
        System.out.println("Starting Digital Register Application Simulation...\n");

        // 1. Initialize Data Access Layer
        DigitalArchive digitalArchive = new DigitalArchive();
        IAcademicRecordRepository academicRecordRepository = new AcademicRecordRepositoryImpl(digitalArchive);

        // 2. Initialize Application Layer
        AcademicRecordService academicRecordService = new AcademicRecordService(academicRecordRepository);

        // 3. Initialize Presentation Layer
        DigitalRegisterView digitalRegisterView = new DigitalRegisterView();
        DigitalRegisterController digitalRegisterController = new DigitalRegisterController(academicRecordService, digitalRegisterView);

        // --- Simulate Sequence Diagram: View Digital Registers ---

        System.out.println("\n--- Simulating Administrator Actions ---\n");

        // Administrator -> View : clickDigitalRegisterButton() // User action, maps to R4
        // View -> View : onDigitalRegisterButtonClick() // Internal handling within View
        // View -> Controller : handleDigitalRegisterSelection()
        System.out.println("Administrator clicks the 'Digital Register' button.");
        digitalRegisterView.onDigitalRegisterButtonClick();

        // The View will now display available academic years (showYearSelectionScreen)
        // and prompt the user for input.
        // For this simulation, we'll directly call the next view method as if the user entered "AY2023-2024".

        // Administrator -> View : selectAcademicYear("2023-2024") // User action, maps to R6
        // View -> View : onAcademicYearSelected("2023-2024") // Internal handling within View
        // View -> Controller : selectAcademicYear("2023-2024")
        System.out.println("\nAdministrator selects academic year: AY2023-2024");
        digitalRegisterView.onAcademicYearSelected("AY2023-2024");

        System.out.println("\n--- Simulation Complete ---");

        // Close scanner if it was used for console input directly
        digitalRegisterView.closeScanner();
    }
}