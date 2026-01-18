package presentation;

import shared.RegistrationRequestDTO;

import java.util.List;

/**
 * View component responsible for displaying the list of registration requests.
 * It receives data from a ViewModel and formats it for presentation.
 */
public class RegistrationListView {
    private RegistrationListViewModel viewModel;

    /**
     * Constructs a new RegistrationListView with a reference to its ViewModel.
     * @param viewModel The ViewModel containing the data to display.
     */
    public RegistrationListView(RegistrationListViewModel viewModel) {
        this.viewModel = viewModel;
        System.out.println("[View] View initialized with ViewModel reference.");
    }

    /**
     * Displays the registration requests by rendering data from its internal ViewModel reference.
     *
     * @return A formatted String representation of the list of pending registrations,
     *         or a message indicating no requests if the list is empty.
     */
    public String displayRequests() {
        System.out.println("[View] View renders data from its internal ViewModel reference.");
        List<RegistrationRequestDTO> requests = viewModel.getRegistrationRequests();
        if (requests.isEmpty()) {
            return "No pending registration requests.";
        } else {
            StringBuilder sb = new StringBuilder("--- List of Pending Registration Requests ---\n");
            for (RegistrationRequestDTO dto : requests) {
                sb.append(dto.toString()).append("\n");
            }
            sb.append("-------------------------------------------\n");
            return sb.toString();
        }
    }
}