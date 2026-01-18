package application;

import domain.Convention;
import domain.ConventionRepository;
import domain.ConventionSubmittedEvent;
import domain.ValidationResult;
import infrastructure.ETOURServiceClient;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Command handler for submitting a convention.
 * Implements the business logic for convention submission, including validation,
 * persistence, event publishing, and integration with external ETOUR system (EC-002).
 */
public class SubmitConventionHandler {
    private final ConventionRepository conventionRepository;
    private final EventPublisher eventPublisher;
    private final ETOURServiceClient etourServiceClient;

    public SubmitConventionHandler(ConventionRepository conventionRepository, EventPublisher eventPublisher, ETOURServiceClient etourServiceClient) {
        this.conventionRepository = conventionRepository;
        this.eventPublisher = eventPublisher;
        this.etourServiceClient = etourServiceClient;
    }

    public void handle(SubmitConventionCommand command) {
        // Create convention domain object
        Convention convention = new Convention(command.getRestaurantId(), command.getAgencyId(), command.getConventionData());

        // Validate convention data (QR-001)
        ValidationResult validationResult = convention.validate(command.getConventionData());
        if (!validationResult.isValid()) {
            throw new IllegalArgumentException("Validation failed: " + validationResult.getErrors());
        }

        // Submit the convention
        convention.submit();

        // Persist convention
        conventionRepository.save(convention);

        // Publish domain event
        ConventionSubmittedEvent event = new ConventionSubmittedEvent() {
            @Override
            public String getConventionId() {
                return convention.getId();
            }

            @Override
            public String getRestaurantId() {
                return convention.getRestaurantId();
            }

            @Override
            public String getAgencyId() {
                return convention.getAgencyId();
            }

            @Override
            public LocalDateTime getTimestamp() {
                return LocalDateTime.now();
            }
        };
        eventPublisher.publish(event);

        // Submit to external ETOUR system (EC-002)
        etourServiceClient.submitToETOUR(convention.getId());
    }
}