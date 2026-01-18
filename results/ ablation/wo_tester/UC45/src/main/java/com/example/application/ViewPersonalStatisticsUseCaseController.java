package com.example.application;

import com.example.domain.AuthenticationService;
import com.example.domain.ConnectionException;
import com.example.domain.StatisticsData;
import com.example.infrastructure.DatabaseStatisticsRepository;
import com.example.infrastructure.StatisticsRepository;
import com.example.presentation.StatisticsViewModel;

/**
 * Use case controller coordinating the flow of viewing personal statistics.
 * Implements the sequence diagram interactions.
 */
public class ViewPersonalStatisticsUseCaseController implements IViewPersonalStatisticsUseCase {
    private ViewPersonalStatisticsPresenter presenter;
    private StatisticsRepository repository;
    private AuthenticationService authenticationService;

    // Constructor as per class diagram: creates dependencies
    public ViewPersonalStatisticsUseCaseController(ViewPersonalStatisticsPresenter presenter) {
        this.presenter = presenter;
        this.repository = new DatabaseStatisticsRepository();
        this.authenticationService = new AuthenticationService();
    }

    @Override
    public StatisticsViewModel execute(String operatorId) {
        // Step 0 from sequence diagram: authenticate operator
        if (!authenticationService.authenticate(operatorId)) {
            throw new SecurityException("Authentication failed for operator: " + operatorId);
        }

        // Sequence diagram message m4: "new ViewPersonalStatisticsPresenter()"
        // The presenter is created in the constructor and passed to this controller

        // Sequence diagram message m5: "3. findByOperatorId(operatorId)"
        StatisticsData statisticsData = repository.findByOperatorId(operatorId);

        // Sequence diagram message m10: "4. present(statisticsData)"
        return presenter.present(statisticsData);
    }
}