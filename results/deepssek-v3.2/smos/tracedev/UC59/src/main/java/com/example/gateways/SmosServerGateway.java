package com.example.gateways;

/**
 * Gateway to communicate with SMOS server.
 * Implements connection participant in sequence diagram.
 */
public class SmosServerGateway {
    private ServerConnection connection;
    private String serverAddress;
    
    public SmosServerGateway(String serverAddress) {
        this.serverAddress = serverAddress;
        this.connection = new ServerConnection(serverAddress);
    }
    
    /**
     * Connects to the SMOS server.
     */
    public boolean connect() {
        return connection.open();
    }
    
    /**
     * Fetches data from the specified endpoint.
     */
    public String fetchData(String endpoint) {
        // Check connection status as per sequence diagram
        if (!connection.isActive()) {
            throw new RuntimeException("Connection to SMOS server is interrupted");
        }
        
        // Send request to server
        return connection.sendRequest(endpoint);
    }
    
    /**
     * Implements message m5: fetchStudentReports(studentId)
     * Delegates to the Repository's method.
     */
    public String fetchStudentReports(String studentId) {
        // This method would be called by Repository to fetch data
        // For sequence diagram traceability, we ensure the method exists
        return fetchData("GET /reports/" + studentId);
    }
    
    /**
     * Implements message m26: fetchReportById(reportId)
     * Delegates to the Repository's method.
     */
    public String fetchReportById(String reportId) {
        // This method would be called by Repository to fetch data
        // For sequence diagram traceability, we ensure the method exists
        return fetchData("GET /reports/" + reportId);
    }
    
    /**
     * Implements return message m29: Report object
     * This would parse JSON data to Report object
     */
    public Object parseReportData(String jsonData) {
        // This method would parse JSON and return Report object
        // For sequence diagram traceability, we ensure the method exists
        return null; // placeholder
    }
    
    /**
     * Disconnects from the server.
     */
    public void disconnect() {
        connection.close();
    }
}