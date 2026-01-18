
## Program call flow:

(See `delete_banner/docs/system_design-sequence-diagram.mermaid` for detailed sequence diagram)

## Anything UNCLEAR

Based on the PRD, the following aspects require further clarification:

*   **Q1: Data Source for Refreshment Points and Banners**: The PRD mentions an "ETOUR server." We are assuming a RESTful API for communication. The exact endpoints, request/response formats (JSON/XML), and authentication headers need to be defined. For this design, we've assumed `/refreshmentpoints` for listing points and `/refreshmentpoints/{rpId}/banners` for listing banners, and `/banners/{bannerId}` for deletion.
*   **Q2: Authentication and Authorization**: The PRD states "The agency has logged." We assume an existing authentication token or session mechanism is in place, and the `EtourApiClient` will implicitly handle sending this with requests. Specific authorization checks for the delete operation on the backend are also assumed.
*   **Q3: ETOUR Server Connection Interruption Handling**: The design includes a `NetworkException` to handle connection issues. Further clarification is needed on specific retry policies (e.g., automatic retries, number of retries, backoff strategy) and whether specific error codes from the ETOUR server should trigger different user messages.
*   **Q4: Banner Archiving vs. Permanent Deletion**: The PRD states "Removes the banner." We have designed for permanent deletion. If soft-deletion/archiving is required, the `deleteBanner` method in `BannerService` and the corresponding API call would need to be modified to update a status field rather than physically removing the record.
*   **Q5: UI Framework/Library**: For this design, we've assumed JavaFX for the UI, given its modern capabilities for desktop applications. This choice impacts the specific UI implementation details (e.g., FXML for layouts, JavaFX controllers).
*   **Q6: Concurrency**: The PRD does not specify concurrency requirements. For a single-user desktop application, this is less critical. However, if multiple operators can access the same data concurrently, the backend API would need to implement appropriate locking mechanisms (e.g., optimistic locking) to prevent race conditions.
*   **Q7: Logging and Auditing**: The PRD does not explicitly detail logging and auditing requirements. It is recommended to implement basic logging for successful and failed deletion operations, including user ID, timestamp, and banner ID, for auditing purposes.
*   **Q8: Internationalization**: No internationalization requirements were mentioned in the PRD. The current design assumes English as the sole language for UI messages.