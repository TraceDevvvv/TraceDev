package etour;

import etour.application.EditPreferencesUseCase;
import etour.data.SearchPreferencesRepositoryImpl;
import etour.framework.ServerETOURDataSource;
import etour.interfaceadapters.PreferencesForm;
import etour.interfaceadapters.PreferencesViewModel;
import etour.serv.ConfirmationServiceImpl;

/**
 * Main runnable class that wires all components and simulates the sequence diagram flow.
 */
public class MainApp {
    public static void main(String[] args) {
        // 1. Setup infrastructure
        ServerETOURDataSource dataSource = new ServerETOURDataSource();
        SearchPreferencesRepositoryImpl repository = new SearchPreferencesRepositoryImpl(dataSource);
        ConfirmationServiceImpl confirmationService = new ConfirmationServiceImpl();

        // 2. Create use case
        EditPreferencesUseCase useCase = new EditPreferencesUseCase(repository, confirmationService, dataSource);

        // 3. Create ViewModel and Form
        PreferencesViewModel viewModel = new PreferencesViewModel(useCase);
        PreferencesForm form = new PreferencesForm(viewModel);

        System.out.println("=== ETOUR Preferences Editor Simulation ===\n");

        // Simulate sequence diagram steps

        // Tourist accesses functionality
        System.out.println("1. Tourist accesses functionality.");
        if (!form.isAuthenticated()) {
            System.out.println("   Authentication failed.");
            return;
        }
        System.out.println("   Authentication passed.");

        // Load preferences
        System.out.println("\n2. Loading preferences for tourist T001...");
        try {
            viewModel.loadPreferences("T001");
            form.displayForm(viewModel.getPreferences());
        } catch (Exception e) {
            form.showError(e.getMessage());
            return;
        }

        // Simulate field edits
        System.out.println("\n3. Tourist edits some fields.");
        form.onFieldEdited("language", "French");
        form.onFieldEdited("maxDistance", 25);

        // Submit changes
        System.out.println("\n4. Tourist submits form.");
        form.onSubmit();

        System.out.println("\n=== Simulation complete ===");
    }
}