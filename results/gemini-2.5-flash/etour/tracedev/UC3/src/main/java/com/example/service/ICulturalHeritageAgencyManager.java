package com.example.service;

import com.example.model.BeanCulturalHeritage;
import com.example.exception.RemoteException; // Using custom RemoteException
import java.rmi.Remote; // Standard RMI remote interface marker
import java.util.List;

/**
 * Interface for the Cultural Heritage Agency Manager application service.
 * This interface is marked as Remote for RMI communication, meaning
 * its methods can be invoked from a different Java Virtual Machine.
 */
public interface ICulturalHeritageAgencyManager extends Remote {

    /**
     * Retrieves a list of all cultural heritage objects.
     * @return A list of BeanCulturalHeritage objects.
     * @throws RemoteException if an RMI-related or underlying service error occurs.
     */
    List<BeanCulturalHeritage> getCulturalHeritage() throws RemoteException, java.rmi.RemoteException;

    /**
     * Retrieves a specific cultural heritage object by its ID.
     * @param id The ID of the cultural heritage object to retrieve.
     * @return The BeanCulturalHeritage object, or null if not found.
     * @throws RemoteException if an RMI-related or underlying service error occurs.
     */
    BeanCulturalHeritage getCulturalHeritage(int id) throws RemoteException, java.rmi.RemoteException;

    /**
     * Validates the data of a cultural heritage object.
     * @param pBC The BeanCulturalHeritage object to validate.
     * @return true if the data is valid.
     * @throws RemoteException if validation fails.
     * @throws java.rmi.RemoteException if an RMI-specific error occurs.
     */
    boolean validateCulturalHeritage(BeanCulturalHeritage pBC) throws RemoteException, java.rmi.RemoteException;

    /**
     * Commits the changes of a cultural heritage object to the persistent storage.
     * This method is intended to be called after successful validation.
     * @param pBC The BeanCulturalHeritage object to commit.
     * @return true if the commit was successful, false otherwise.
     * @throws RemoteException if a database error occurs.
     * @throws java.rmi.RemoteException if an RMI-specific error occurs.
     */
    boolean commitCulturalHeritage(BeanCulturalHeritage pBC) throws RemoteException, java.rmi.RemoteException;

    /**
     * Modifies an existing cultural heritage object.
     * Implements requirement REQ ID 17.
     * @param pBC The BeanCulturalHeritage object containing the updated data.
     * @return true if the modification was successful, false otherwise.
     * @throws RemoteException if a validation error or underlying service error occurs.
     * @throws java.rmi.RemoteException if an RMI-specific error occurs.
     */
    boolean modifyCulturalHeritage(BeanCulturalHeritage pBC) throws RemoteException, java.rmi.RemoteException;
}