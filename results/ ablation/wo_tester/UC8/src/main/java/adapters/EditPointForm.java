package adapters;

import application.RefreshmentPointDTO;

/**
 * Represents the edit form for a refreshment point.
 */
public class EditPointForm {
    private RefreshmentPointDTO pointDTO;

    public EditPointForm() {
        this.pointDTO = new RefreshmentPointDTO();
    }

    /**
     * Displays the form (simulated).
     */
    public void display() {
        System.out.println("Displaying edit form for point: " + pointDTO.getName());
    }

    /**
     * Gets the form data.
     * @return the DTO with form data.
     */
    public RefreshmentPointDTO getFormData() {
        return pointDTO;
    }

    /**
     * Sets the form data.
     * @param data the DTO containing point data.
     */
    public void setFormData(RefreshmentPointDTO data) {
        this.pointDTO = data;
    }

    /**
     * Enables the submission block (simulated).
     */
    public void enableSubmissionBlock() {
        System.out.println("Submission block enabled.");
    }

    /**
     * Disables the submission block (simulated).
     */
    public void disableSubmissionBlock() {
        System.out.println("Submission block disabled.");
    }

    /**
     * Updates form fields (simulated).
     */
    public void updateFormFields() {
        System.out.println("Form fields updated.");
    }
}