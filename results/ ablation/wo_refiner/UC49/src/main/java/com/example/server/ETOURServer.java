package com.example.server;

/**
 * Represents the ETOUR Server database.
 */
public class ETOURServer {
    /**
     * Simulates a database interaction.
     *
     * @return JSON response string
     */
    public String getJSONResponse() {
        return "[{\"id\":\"S1\",\"name\":\"Eiffel Tower\",\"description\":\"Iconic tower in Paris\"}," +
                "{\"id\":\"S2\",\"name\":\"Colosseum\",\"description\":\"Ancient amphitheater in Rome\"}]";
    }
}