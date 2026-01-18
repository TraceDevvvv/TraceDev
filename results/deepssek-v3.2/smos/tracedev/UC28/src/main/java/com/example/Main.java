package com.example;

/**
 * Main class to run the application.
 * This simulates the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies.
        ISmosDataSource smosDataSource = new ISmosDataSource() {
            @Override
            public ClassRegisterDTO fetchRegisterData(String registerId) throws ConnectionException {
                // Simulate fetching data. In reality, connect to SMOS server.
                return new ClassRegisterDTO(new ClassRegister(registerId, "Math 101", null, null, null, null, null));
            }
        };
        ClassRegisterRepository repository = new ClassRegisterRepository(smosDataSource);
        RegisterDetailsPresenter presenter = new RegisterDetailsPresenter();
        RegisterDetailsInteractor interactor = new RegisterDetailsInteractor(repository, presenter);
        RegisterDetailsController controller = new RegisterDetailsController(interactor);
        RegisterDetailsViewModel viewModel = new RegisterDetailsViewModel();
        RegisterDetailsView view = new RegisterDetailsView(viewModel, controller);

        // Simulate Administrator clicking DetailsButton.
        view.onDetailsButtonClicked("register123");
    }
}