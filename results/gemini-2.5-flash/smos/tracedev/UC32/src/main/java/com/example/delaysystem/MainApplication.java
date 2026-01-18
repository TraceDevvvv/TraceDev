package com.example.delaysystem;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat; // For formatting date output in a readable way

/**
 * Main application class to demonstrate the Delay Modification Use Case.
 * This class sets up the dependencies and simulates user interactions
 * according to the sequence diagram.
 */
public class MainApplication {

    public static void main(String[] args) {
        // Setup Dependencies
        ISMOSServerGateway smosServerGateway = new SMOSServerGateway();
        IDelayRepository delayRepository = new DelayRepository(smosServerGateway);
        UserSession userSession = new UserSession(true, "adminUser123"); // Admin is logged in

        DelayModificationUseCaseController useCaseController =
                new DelayModificationUseCaseController(delayRepository, userSession);
        DelayModificationViewModel viewModel =
                new DelayModificationViewModel(useCaseController);
        DelayModificationView view =
                new DelayModificationView(viewModel);

        // Format for consistent date representation
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

        System.out.println("--- Starting Delay Modification Application Simulation ---");

        // --- Simulate Sequence Diagram: Fetch Scheduling Info ---

        // Entry Conditions: Administrator logged in, registered, and date selected for modification.
        // Admin -> View : selectDate(selectedDate : Date)

        // Scenario 1: Successful Fetch for Oct 26, 2023
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.OCTOBER, 26, 0, 0, 0);
        Date dateNormal = cal.getTime();
        System.out.println("\n--- Simulating Scenario 1: Successful Fetch for " + sdf.format(dateNormal) + " ---");
        view.onDateSelected(dateNormal);
        // Expect: displaySchedulingInfo

        // Scenario 2: Simulate Connection Failure for Oct 28, 2023
        // SMOSServerGateway's checkConnection is simulated to randomly fail or based on some condition.
        // For strict testing of the sequence diagram path, let's make it always fail for this date.
        // Note: The current SMOSServerGateway simulates random failure. To force the path, you'd
        //       need to modify SMOSServerGateway or use a mock. For now, rely on random chance
        //       or the implicit "if Math.random() < 0.1" which *might* trigger.
        //       The current SMOSServerGateway also has a "specific date failure" built in for connection.
        cal.set(2023, Calendar.OCTOBER, 28, 0, 0, 0); // This date is programmed to fail connection in SMOSServerGateway
        Date dateConnectionFail = cal.getTime();
        System.out.println("\n--- Simulating Scenario 2: Connection Failure for " + sdf.format(dateConnectionFail) + " ---");
        view.onDateSelected(dateConnectionFail);
        // Expect: showErrorMessage("Failed to load scheduling data. Check server connection.")

        // Scenario 3: Simulate getSchedulingInfo Failure for Oct 29, 2023 (after successful connection)
        cal.set(2023, Calendar.OCTOBER, 29, 0, 0, 0); // This date is programmed to fail getSchedulingInfo in SMOSServerGateway
        Date dateFetchFail = cal.getTime();
        System.out.println("\n--- Simulating Scenario 3: Fetch Failure (after connection) for " + sdf.format(dateFetchFail) + " ---");
        view.onDateSelected(dateFetchFail);
        // Expect: showErrorMessage("Failed to load scheduling data.")

        // Scenario 4: Fetch for a date with no existing delays (should return an empty list/DTO)
        cal.set(2023, Calendar.NOVEMBER, 1, 0, 0, 0);
        Date dateNoDelays = cal.getTime();
        System.out.println("\n--- Simulating Scenario 4: Fetch for date with no delays for " + sdf.format(dateNoDelays) + " ---");
        view.onDateSelected(dateNoDelays);
        // Expect: displaySchedulingInfo with empty delays list

        // --- Simulate Sequence Diagram: Save Delay ---

        // Flow of Events: 2. Administrator edits the delay. (Simulated by getEditedDelayData in View)
        // Flow of Events: 3. Administrator clicks on "Save".

        // Scenario 5: Successful Save for an existing delay (editing D001 from Oct 26)
        System.out.println("\n--- Simulating Scenario 5: Successful Save (editing existing delay) ---");
        // For this scenario, getEditedDelayData in View will return D001 with updated duration/reason for Oct 26
        view.onSaveButtonClick();
        // Expect: showSuccessMessage("Delay updated successfully.")

        // Re-fetch to see the updated data
        System.out.println("\n--- Re-fetching data for " + sdf.format(dateNormal) + " after save ---");
        view.onDateSelected(dateNormal);

        // Scenario 6: Simulate Save Failure for Oct 30, 2023 (after successful connection)
        cal.set(2023, Calendar.OCTOBER, 30, 0, 0, 0); // This date is programmed to fail sendUpdateDelayRequest
        Date dateSaveFail = cal.getTime();
        System.out.println("\n--- Simulating Scenario 6: Save Failure (after connection) for " + sdf.format(dateSaveFail) + " ---");
        // Override getEditedDelayData for this specific test
        DelayModificationView saveFailView = new DelayModificationView(viewModel) {
            @Override
            public DelayDTO getEditedDelayData() {
                // This simulates the admin editing a delay for the save fail date
                return new DelayDTO("D_NEW_FAIL", dateSaveFail, 120, "Critical Failure Inducer");
            }
        };
        saveFailView.onSaveButtonClick();
        // Expect: showErrorMessage("Failed to save delay.")

        // Scenario 7: Simulate Connection Failure during Save for Oct 28, 2023
        // Re-use connection fail date, but this time for saving.
        System.out.println("\n--- Simulating Scenario 7: Connection Failure during Save for " + sdf.format(dateConnectionFail) + " ---");
        DelayModificationView connectionFailSaveView = new DelayModificationView(viewModel) {
            @Override
            public DelayDTO getEditedDelayData() {
                // This simulates the admin editing a delay for the connection fail date
                return new DelayDTO("D_NEW_CONN_FAIL", dateConnectionFail, 180, "Severe Network Outage");
            }
        };
        connectionFailSaveView.onSaveButtonClick();
        // Expect: showErrorMessage("Failed to save delay. Check server connection.")


        System.out.println("\n--- Delay Modification Application Simulation Finished ---");
    }
}