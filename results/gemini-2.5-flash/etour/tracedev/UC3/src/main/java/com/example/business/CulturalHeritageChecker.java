package com.example.business;

import com.example.model.BeanCulturalHeritage;
import com.example.util.ErrorMessage;
import com.example.exception.RemoteException; // Use the custom RemoteException for business logic errors

import java.util.Date;

/**
 * Business Logic component responsible for validating CulturalHeritage data.
 */
public class CulturalHeritageChecker {

    /**
     * Checks if the data of a BeanCulturalHeritage object is valid.
     * Performs basic validation rules.
     *
     * @param pBC The BeanCulturalHeritage object to validate.
     * @return true if the data is valid.
     * @throws RemoteException if validation fails, encapsulating an appropriate error message.
     *                         Although the sequence diagram shows `Checker --x Mgr_Impl : RemoteException(...)`,
     *                         in a real implementation, a Checker typically throws a more specific
     *                         ValidationException or simply returns false, and the manager then
     *                         wraps it into a RemoteException. For direct translation, we'll
     *                         let it throw RemoteException, which implies the manager will catch it.
     */
    public boolean checkDataCulturalHeritage(BeanCulturalHeritage pBC) throws RemoteException {
        // Assume some basic validation rules for demonstration
        if (pBC == null) {
            throw new RemoteException(ErrorMessage.ERROR_FORMAT_BEAN + " - Cultural Heritage object cannot be null.");
        }
        if (pBC.getName() == null || pBC.getName().trim().isEmpty()) {
            throw new RemoteException(ErrorMessage.ERROR_FORMAT_BEAN + " - Name cannot be empty.");
        }
        if (pBC.getCity() == null || pBC.getCity().trim().isEmpty()) {
            throw new RemoteException(ErrorMessage.ERROR_FORMAT_BEAN + " - City cannot be empty.");
        }
        if (pBC.getPhone() == null || pBC.getPhone().trim().isEmpty()) {
            // Basic phone format check, e.g., only digits and plus sign, min length
            if (!pBC.getPhone().matches("^[+]?[0-9\\s]{7,20}$")) {
                throw new RemoteException(ErrorMessage.ERROR_FORMAT_BEAN + " - Phone number format is invalid.");
            }
        }
        if (pBC.getDescription() == null || pBC.getDescription().trim().isEmpty()) {
            throw new RemoteException(ErrorMessage.ERROR_FORMAT_BEAN + " - Description cannot be empty.");
        }
        if (pBC.getStreet() == null || pBC.getStreet().trim().isEmpty()) {
            throw new RemoteException(ErrorMessage.ERROR_FORMAT_BEAN + " - Street cannot be empty.");
        }
        if (pBC.getCap() == null || pBC.getCap().trim().isEmpty()) {
            // Basic CAP (postal code) format check, e.g., 5 digits for Italian CAPs
            if (!pBC.getCap().matches("^\\d{5}$")) {
                throw new RemoteException(ErrorMessage.ERROR_FORMAT_BEAN + " - CAP format is invalid (e.g., 5 digits).");
            }
        }
        if (pBC.getProvince() == null || pBC.getProvince().trim().isEmpty()) {
            throw new RemoteException(ErrorMessage.ERROR_FORMAT_BEAN + " - Province cannot be empty.");
        }
        if (pBC.getOpeningTime() == null || pBC.getClosingTime() == null) {
            throw new RemoteException(ErrorMessage.ERROR_FORMAT_BEAN + " - Opening and closing times cannot be empty.");
        }
        // Ensure opening time is before closing time
        if (pBC.getOpeningTime().after(pBC.getClosingTime())) {
            throw new RemoteException(ErrorMessage.ERROR_FORMAT_BEAN + " - Opening time must be before closing time.");
        }
        if (pBC.getClosingDay() == null || pBC.getClosingDay().trim().isEmpty()) {
            throw new RemoteException(ErrorMessage.ERROR_FORMAT_BEAN + " - Closing day cannot be empty.");
        }
        if (pBC.getLocation() == null || pBC.getLocation().trim().isEmpty()) {
            throw new RemoteException(ErrorMessage.ERROR_FORMAT_BEAN + " - Location cannot be empty.");
        }

        // All checks passed
        return true;
    }
}