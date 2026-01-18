package com.etour.controller;

import com.etour.repository.RefreshmentPointRepository;
import com.etour.session.SessionManager;
import com.etour.model.RefreshmentPoint;
import com.etour.model.RefreshmentPointDetails;
import com.etour.exception.ConnectionException;
import com.etour.exception.NotFoundException;
import com.etour.view.RefreshmentPointView;
import java.util.List;

/**
 * Controller for refreshment point operations
 */
public class PointOfRestController {
    private RefreshmentPointRepository repository;
    private SessionManager sessionManager;
    private RefreshmentPointView view;

    public PointOfRestController() {
        this.repository = new RefreshmentPointRepository();
        this.sessionManager = new SessionManager();
        this.view = new RefreshmentPointView(this);
    }

    public RefreshmentPoint selectPointOfRest(String pointId) {
        // Validate login (Entry Conditions)
        if (!sessionManager.isLoggedIn("operator123")) {
            throw new SecurityException("Operator not logged in");
        }

        return repository.retrieveOrInitializeRefreshmentPoint(pointId);
    }

    public RefreshmentPointDetails viewRefreshmentPointDetails(String pointId) {
        try {
            RefreshmentPointDetails details = repository.fetchPointDetails(pointId);
            updateView(details);
            return details;
        } catch (ConnectionException e) {
            // Propagate to view
            throw e;
        } catch (NotFoundException e) {
            // Propagate to view
            throw e;
        }
    }

    public void updateView(RefreshmentPointDetails details) {
        // Flow of Events 4: Notifies view to update with retrieved refreshment point details
        view.updateView(details);
    }

    // Additional method for Flow of Events 1
    public List<RefreshmentPoint> requestDisplayPoints() {
        return repository.getAllRefreshmentPoints();
    }

    // Getter for view (for demo purposes)
    public RefreshmentPointView getView() {
        return view;
    }
}