package smos.dettaglio;

import smos.system.AddressDetails;

/**
 * Use case for viewing address details.
 * Satisfies entry condition requirement REQ-001.
 */
public class ViewDettaglizzazioneUseCase {
    private boolean taken = false;

    public AddressDetails execute(String addressId) {
        // Simulate execution
        System.out.println("Executing ViewDettaglizzazione for address: " + addressId);
        taken = true;
        return new AddressDetails(); // dummy return
    }

    // Method to check if the use case has been taken
    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}