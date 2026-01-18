package com.example.service;

import com.example.business.CulturalHeritageChecker;
import com.example.model.BeanCulturalHeritage;
import com.example.repository.DBCulturalHeritage;
import com.example.util.ErrorMessage;
import com.example.exception.RemoteException; // Custom RemoteException
import com.example.exception.SQLException; // Custom SQLException

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Application Service implementation for managing Cultural Heritage data.
 * This class implements the remote interface `ICulturalHeritageAgencyManager`
 * and provides the business logic coordination for cultural heritage operations.
 */
public class CulturalHeritageAgencyManager extends UnicastRemoteObject implements ICulturalHeritageAgencyManager {

    private static final long serialVersionUID = 1L; // Recommended for Serializable classes

    // Attributes from Class Diagram:
    private CulturalHeritageChecker culturalHeritageChecker;
    private DBCulturalHeritage dbCulturalHeritage;

    /**
     * Constructs a CulturalHeritageAgencyManager.
     * @throws java.rmi.RemoteException if an RMI error occurs during object export.
     */
    public CulturalHeritageAgencyManager() throws java.rmi.RemoteException {
        super(); // Call UnicastRemoteObject constructor
        this.culturalHeritageChecker = new CulturalHeritageChecker();
        this.dbCulturalHeritage = new DBCulturalHeritage();
        System.out.println("CulturalHeritageAgencyManager instance created and exported.");
    }

    /**
     * Retrieves a list of all cultural heritage objects.
     * @return A list of BeanCulturalHeritage objects.
     * @throws RemoteException if an underlying service error occurs.
     * @throws java.rmi.RemoteException if an RMI-specific error occurs.
     */
    @Override
    public List<BeanCulturalHeritage> getCulturalHeritage() throws RemoteException, java.rmi.RemoteException {
        try {
            return dbCulturalHeritage.getAllCulturalHeritage();
        } catch (SQLException e) {
            System.err.println("DB Error in getCulturalHeritage(): " + e.getMessage());
            throw new RemoteException(ErrorMessage.ERROR_DBMS, e);
        } catch (Exception e) {
            System.err.println("Unexpected error in getCulturalHeritage(): " + e.getMessage());
            throw new RemoteException(ErrorMessage.ERROR_UNKNOWN, e);
        }
    }

    /**
     * Retrieves a specific cultural heritage object by its ID.
     * @param id The ID of the cultural heritage object to retrieve.
     * @return The BeanCulturalHeritage object, or null if not found.
     * @throws RemoteException if an underlying service error occurs.
     * @throws java.rmi.RemoteException if an RMI-specific error occurs.
     */
    @Override
    public BeanCulturalHeritage getCulturalHeritage(int id) throws RemoteException, java.rmi.RemoteException {
        try {
            return dbCulturalHeritage.getCulturalHeritageById(id);
        } catch (SQLException e) {
            System.err.println("DB Error in getCulturalHeritage(id): " + e.getMessage());
            throw new RemoteException(ErrorMessage.ERROR_DBMS, e);
        } catch (Exception e) {
            System.err.println("Unexpected error in getCulturalHeritage(id): " + e.getMessage());
            throw new RemoteException(ErrorMessage.ERROR_UNKNOWN, e);
        }
    }

    /**
     * Validates the data of a cultural heritage object.
     * @param pBC The BeanCulturalHeritage object to validate.
     * @return true if the data is valid.
     * @throws RemoteException if validation fails.
     * @throws java.rmi.RemoteException if an RMI-specific error occurs.
     */
    @Override
    public boolean validateCulturalHeritage(BeanCulturalHeritage pBC) throws RemoteException, java.rmi.RemoteException {
        try {
            return culturalHeritageChecker.checkDataCulturalHeritage(pBC);
        } catch (RemoteException e) {
            System.err.println("Validation Error in validateCulturalHeritage(): " + e.getMessage());
            throw e; // Re-throw the validation RemoteException
        } catch (Exception e) {
            System.err.println("Unexpected error in validateCulturalHeritage(): " + e.getMessage());
            throw new RemoteException(ErrorMessage.ERROR_UNKNOWN, e);
        }
    }

    /**
     * Commits the changes of a cultural heritage object to the persistent storage.
     * This method is intended to be called after successful validation.
     * @param pBC The BeanCulturalHeritage object to commit.
     * @return true if the commit was successful, false otherwise.
     * @throws RemoteException if a database error occurs.
     * @throws java.rmi.RemoteException if an RMI-specific error occurs.
     */
    @Override
    public boolean commitCulturalHeritage(BeanCulturalHeritage pBC) throws RemoteException, java.rmi.RemoteException {
        try {
            boolean success = dbCulturalHeritage.modifyCulturalHeritage(pBC);
            if (!success) {
                // If modifyCulturalHeritage returns false, it means the ID was not found or other internal logic failed
                throw new RemoteException(ErrorMessage.ERROR_MODIFY_FAILED + " - Cultural Heritage with ID " + pBC.getId() + " not found or could not be updated.");
            }
            return true;
        } catch (SQLException e) {
            System.err.println("DB Error in commitCulturalHeritage(): " + e.getMessage());
            // Wrap SQLException into RemoteException for client consumption
            throw new RemoteException(ErrorMessage.ERROR_DBMS, e);
        } catch (RemoteException e) {
            // This catches the RemoteException thrown by the "if (!success)" block above
            throw e;
        } catch (Exception e) {
            System.err.println("Unexpected error in commitCulturalHeritage(): " + e.getMessage());
            throw new RemoteException(ErrorMessage.ERROR_UNKNOWN, e);
        }
    }

    /**
     * Modifies an existing cultural heritage object.
     * Implements requirement REQ ID 17.
     * @param pBC The BeanCulturalHeritage object containing the updated data.
     * @return true if the modification was successful, false otherwise.
     * @throws RemoteException if a validation error or underlying service error occurs.
     * @throws java.rmi.RemoteException if an RMI-specific error occurs.
     */
    @Override
    public boolean modifyCulturalHeritage(BeanCulturalHeritage pBC) throws RemoteException, java.rmi.RemoteException {
        try {
            // 1. Check data validity using business logic component (now via public validateCulturalHeritage)
            validateCulturalHeritage(pBC); // This throws RemoteException on failure

            // 2. If valid, persist changes using repository component (now via public commitCulturalHeritage)
            return commitCulturalHeritage(pBC);
        } catch (RemoteException e) {
            // Catches validation errors from validateCulturalHeritage or DB errors from commitCulturalHeritage
            System.err.println("Error in modifyCulturalHeritage(): " + e.getMessage());
            throw e; // Re-throw the validation or DB RemoteException
        } catch (Exception e) {
            System.err.println("Unexpected error in modifyCulturalHeritage(): " + e.getMessage());
            throw new RemoteException(ErrorMessage.ERROR_UNKNOWN, e);
        }
    }
}