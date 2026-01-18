
import java.sql.ResultSet; // For type compatibility, though we'll use a mock implementation
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents a simulated database connection.
 * Provides a `query` method that mimics database interaction and can simulate errors.
 * Traceability: This class represents the ETOUR Database participant in the sequence diagram.
 */
public class Database {
    // Static list to store mock data for TouristAccount
    private static List<TouristAccount> mockTouristAccounts;
    // Flag to simulate database connection errors
    private boolean simulateConnectionError = false;
    // Random object for error simulation
    private final Random random = new Random();

    /**
     * Initializes the mock database with some sample data.
     */
    public Database() {
        if (mockTouristAccounts == null) {
            mockTouristAccounts = new ArrayList<>(Arrays.asList(
                new TouristAccount("T001", "John Doe", "john.doe@example.com", "US", 5),
                new TouristAccount("T002", "Jane Smith", "jane.smith@example.com", "CA", 3),
                new TouristAccount("T003", "Alice Johnson", "alice.j@example.com", "US", 1),
                new TouristAccount("T004", "Bob Williams", "bob.w@example.com", "UK", 7),
                new TouristAccount("T005", "Charlie Brown", "charlie.b@example.com", "DE", 2),
                new TouristAccount("T006", "David Lee", "david.l@example.com", "US", 4)
            ));
        }
    }

    /**
     * Sets the flag to simulate a database connection error.
     *
     * @param simulateConnectionError True to simulate error, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
    }

    /**
     * Executes a mock SQL query and returns a mocked ResultSet.
     * Can simulate connection interruptions based on a flag.
     * Traceability: Fully implemented +query(sql: String): ResultSet method as per class diagram.
     *
     * @param sql The SQL query string (not actually parsed, used for logging).
     * @return A mocked ResultSet containing the query results.
     * @throws RuntimeException if a simulated connection error occurs.
     */
    public ResultSet query(String sql) {
        System.out.println("Database: Executing query: " + sql);

        // Simulate random connection error if flag is set (e.g., 20% chance)
        if (simulateConnectionError && random.nextInt(100) < 50) { // 50% chance of error if flag is true
            System.err.println("Database: Simulating connection interruption!");
            throw new RuntimeException("Simulated ETOUR Database connection interrupted."); // This simulates m14 connectionError.
        }

        // Apply filtering based on the SQL-like conditions (simplified parsing)
        List<TouristAccount> filteredAccounts = mockTouristAccounts.stream()
            .filter(account -> {
                // Simplified filtering based on common patterns in the generated SQL
                boolean matches = true;
                if (sql.contains("name LIKE ?")) {
                    String nameCriteria = extractParam(sql, "name LIKE ?");
                    if (nameCriteria != null && account.name != null) {
                        matches &= account.name.toLowerCase().contains(nameCriteria.toLowerCase());
                    }
                }
                if (sql.contains("nationality = ?")) {
                    String nationalityCriteria = extractParam(sql, "nationality = ?");
                    if (nationalityCriteria != null && account.nationality != null) {
                        matches &= account.nationality.equalsIgnoreCase(nationalityCriteria);
                    }
                }
                if (sql.contains("bookingCount >= ?")) {
                    Integer minBookingCountCriteria = extractIntParam(sql, "bookingCount >= ?");
                    if (minBookingCountCriteria != null) {
                        matches &= account.bookingCount >= minBookingCountCriteria;
                    }
                }
                return matches;
            })
            .collect(Collectors.toList());

        // Convert TouristAccount objects to a List of Maps to simulate a ResultSet's structure
        List<Map<String, Object>> resultRows = filteredAccounts.stream()
            .map(account -> Map.of(
                "id", (Object)account.id,
                "name", (Object)account.name,
                "email", (Object)account.email,
                "nationality", (Object)account.nationality,
                "bookingCount", (Object)account.bookingCount
            ))
            .collect(Collectors.toList());

        System.out.println("Database: Query returned " + resultRows.size() + " rows.");
        return new MockResultSet(resultRows); // Return our mock ResultSet
    }

    // Helper to extract a parameter value from the mock SQL string.
    // This is a very crude approximation and won't work for complex SQL.
    private String extractParam(String sql, String clause) {
        // This is a simplification. In a real scenario, parameters are passed separately, not embedded in SQL.
        // For `name LIKE ?` and `nationality = ?`, we'll just check if the criteria itself is present
        // and return a placeholder value that the repository would use.
        // This method assumes the query will be roughly built as in TouristAccountRepository.
        if (sql.contains("name LIKE '%John%'")) return "John";
        if (sql.contains("name LIKE '%Jane%'")) return "Jane";
        if (sql.contains("name LIKE '%Alice%'")) return "Alice";
        if (sql.contains("nationality = 'US'")) return "US";
        if (sql.contains("nationality = 'CA'")) return "CA";
        if (sql.contains("nationality = 'UK'")) return "UK";
        if (sql.contains("nationality = 'DE'")) return "DE";
        return null;
    }

    private Integer extractIntParam(String sql, String clause) {
        // Similar crude extraction for integers
        if (sql.contains("bookingCount >= 1")) return 1;
        if (sql.contains("bookingCount >= 2")) return 2;
        if (sql.contains("bookingCount >= 3")) return 3;
        if (sql.contains("bookingCount >= 4")) return 4;
        if (sql.contains("bookingCount >= 5")) return 5;
        return null; // Default or no specific int param found
    }
}

/**
 * A mock implementation of `java.sql.ResultSet` for simulation purposes.
 * It stores query results as a list of maps.
 * In a real application, this would be `java.sql.ResultSet`.
 */
class MockResultSet implements ResultSet {
    private final List<Map<String, Object>> rows;
    private int currentRowIndex = -1;

    public MockResultSet(List<Map<String, Object>> rows) {
        this.rows = rows;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    // --- Mocked methods for basic ResultSet-like behavior (minimal implementation) ---\
    // In a full mock, all methods would be implemented.
    @Override
    public boolean next() {
        currentRowIndex++;
        return currentRowIndex < rows.size();
    }

    @Override
    public String getString(String columnLabel) {
        if (currentRowIndex < 0 || currentRowIndex >= rows.size()) return null;
        Object value = rows.get(currentRowIndex).get(columnLabel);
        return value != null ? value.toString() : null;
    }

    @Override
    public int getInt(String columnLabel) {
        if (currentRowIndex < 0 || currentRowIndex >= rows.size()) return 0;
        Object value = rows.get(currentRowIndex).get(columnLabel);
        return value instanceof Integer ? (Integer) value : 0;
    }

    // --- Other ResultSet methods are not implemented for brevity ---\
    // These methods are required by the `ResultSet` interface but not used in this mock scenario.
    // They would throw UnsupportedOperationException if called.
    @Override public void close() {}
    @Override public boolean wasNull() { return false; }
    @Override public String getString(int columnIndex) { return null; }
    @Override public boolean getBoolean(int columnIndex) { return false; }
    @Override public byte getByte(int columnIndex) { return 0; }
    @Override public short getShort(int columnIndex) { return 0; }
    @Override public int getInt(int columnIndex) { return 0; }
    @Override public long getLong(int columnIndex) { return 0; }
    @Override public float getFloat(int columnIndex) { return 0; }
    @Override public double getDouble(int columnIndex) { return 0; }
    @Override public java.math.BigDecimal getBigDecimal(int columnIndex, int scale) { return null; }
    @Override public byte[] getBytes(int columnIndex) { return null; }
    @Override public java.sql.Date getDate(int columnIndex) { return null; }
    @Override public java.sql.Time getTime(int columnIndex) { return null; }
    @Override public java.sql.Timestamp getTimestamp(int columnIndex) { return null; }
    @Override public java.io.InputStream getAsciiStream(int columnIndex) { return null; }
    @Override public java.io.InputStream getUnicodeStream(int columnIndex) { return null; }
    @Override public java.io.InputStream getBinaryStream(int columnIndex) { return null; }
    // REMOVED: @Override public String getString(String columnLabel, java.util.Map<String, Class<?>> map) { return null; }
    @Override public boolean getBoolean(String columnLabel) { return false; }
    @Override public byte getByte(String columnLabel) { return 0; }
    @Override public short getShort(String columnLabel) { return 0; }
    @Override public long getLong(String columnLabel) { return 0; }
    @Override public float getFloat(String columnLabel) { return 0; }
    @Override public double getDouble(String columnLabel) { return 0; }
    @Override public java.math.BigDecimal getBigDecimal(String columnLabel, int scale) { return null; }
    @Override public byte[] getBytes(String columnLabel) { return null; }
    @Override public java.sql.Date getDate(String columnLabel) { return null; }
    @Override public java.sql.Time getTime(String columnLabel) { return null; }
    @Override public java.sql.Timestamp getTimestamp(String columnLabel) { return null; }
    @Override public java.io.InputStream getAsciiStream(String columnLabel) { return null; }
    @Override public java.io.InputStream getUnicodeStream(String columnLabel) { return null; }
    @Override public java.io.InputStream getBinaryStream(String columnLabel) { return null; }
    @Override public java.sql.SQLWarning getWarnings() { return null; }
    @Override public void clearWarnings() {}
    @Override public String getCursorName() { return null; }
    @Override public java.sql.ResultSetMetaData getMetaData() { return null; }
    @Override public Object getObject(int columnIndex) { return null; }
    @Override public Object getObject(String columnLabel) { return null; }
    @Override public int findColumn(String columnLabel) { return 0; }
    @Override public java.io.Reader getCharacterStream(int columnIndex) { return null; }
    @Override public java.io.Reader getCharacterStream(String columnLabel) { return null; }
    @Override public java.math.BigDecimal getBigDecimal(int columnIndex) { return null; }
    @Override public java.math.BigDecimal getBigDecimal(String columnLabel) { return null; }
    @Override public boolean isBeforeFirst() { return false; }
    @Override public boolean isAfterLast() { return false; }
    @Override public boolean isFirst() { return false; }
    @Override public boolean isLast() { return false; }
    @Override public void beforeFirst() {}
    @Override public void afterLast() {}
    @Override public boolean first() { return false; }
    @Override public boolean last() { return false; }
    @Override public int getRow() { return 0; }
    @Override public boolean absolute(int row) { return false; }
    @Override public boolean relative(int rows) { return false; }
    @Override public boolean previous() { return false; }
    @Override public void setFetchDirection(int direction) {}
    @Override public int getFetchDirection() { return 0; }
    @Override public void setFetchSize(int rows) {}
    @Override public int getFetchSize() { return 0; }
    @Override public int getType() { return 0; }
    @Override public int getConcurrency() { return 0; }
    @Override public boolean rowUpdated() { return false; }
    @Override public boolean rowInserted() { return false; }
    @Override public boolean rowDeleted() { return false; }
    @Override public void updateNull(int columnIndex) {}
    @Override public void updateBoolean(int columnIndex, boolean x) {}
    @Override public void updateByte(int columnIndex, byte x) {}
    @Override public void updateShort(int columnIndex, short x) {}
    @Override public void updateInt(int columnIndex, int x) {}
    @Override public void updateLong(int columnIndex, long x) {}
    @Override public void updateFloat(int columnIndex, float x) {}
    @Override public void updateDouble(int columnIndex, double x) {}
    @Override public void updateBigDecimal(int columnIndex, java.math.BigDecimal x) {}
    @Override public void updateString(int columnIndex, String x) {}
    @Override public void updateBytes(int columnIndex, byte[] x) {}
    @Override public void updateDate(int columnIndex, java.sql.Date x) {}
    @Override public void updateTime(int columnIndex, java.sql.Time x) {}
    @Override public void updateTimestamp(int columnIndex, java.sql.Timestamp x) {}
    @Override public void updateAsciiStream(int columnIndex, java.io.InputStream x, int length) {}
    @Override public void updateBinaryStream(int columnIndex, java.io.InputStream x, int length) {}
    @Override public void updateCharacterStream(int columnIndex, java.io.Reader x, int length) {}
    @Override public void updateObject(int columnIndex, Object x, int scaleOrLength) {}
    @Override public void updateObject(int columnIndex, Object x) {}
    @Override public void updateNull(String columnLabel) {}
    @Override public void updateBoolean(String columnLabel, boolean x) {}
    @Override public void updateByte(String columnLabel, byte x) {}
    @Override public void updateShort(String columnLabel, short x) {}
    @Override public void updateInt(String columnLabel, int x) {}
    @Override public void updateLong(String columnLabel, long x) {}
    @Override public void updateFloat(String columnLabel, float x) {}
    @Override public void updateDouble(String columnLabel, double x) {}
    @Override public void updateBigDecimal(String columnLabel, java.math.BigDecimal x) {}
    @Override public void updateString(String columnLabel, String x) {}
    @Override public void updateBytes(String columnLabel, byte[] x) {}
    @Override public void updateDate(String columnLabel, java.sql.Date x) {}
    @Override public void updateTime(String columnLabel, java.sql.Time x) {}
    @Override public void updateTimestamp(String columnLabel, java.sql.Timestamp x) {}
    @Override public void updateAsciiStream(String columnLabel, java.io.InputStream x, int length) {}
    @Override public void updateBinaryStream(String columnLabel, java.io.InputStream x, int length) {}
    @Override public void updateCharacterStream(String columnLabel, java.io.Reader x, int length) {}
    @Override public void updateObject(String columnLabel, Object x, int scaleOrLength) {}
    @Override public void updateObject(String columnLabel, Object x) {}
    @Override public void insertRow() {}
    @Override public void updateRow() {}
    @Override public void deleteRow() {}
    @Override public void refreshRow() {}
    @Override public void cancelRowUpdates() {}
    @Override public void moveToInsertRow() {}
    @Override public void moveToCurrentRow() {}
    @Override public java.sql.Statement getStatement() { return null; }
    @Override public Object getObject(int columnIndex, java.util.Map<String, Class<?>> map) { return null; }
    @Override public Object getObject(String columnLabel, java.util.Map<String, Class<?>> map) { return null; } // ADDED THIS METHOD
    @Override public java.sql.Ref getRef(int columnIndex) { return null; }
    @Override public java.sql.Blob getBlob(int columnIndex) { return null; }
    @Override public java.sql.Clob getClob(int columnIndex) { return null; }
    @Override public java.sql.Array getArray(int columnIndex) { return null; }
    @Override public java.sql.Ref getRef(String columnLabel) { return null; }
    @Override public java.sql.Blob getBlob(String columnLabel) { return null; }
    @Override public java.sql.Clob getClob(String columnLabel) { return null; }
    @Override public java.sql.Array getArray(String columnLabel) { return null; }
    @Override public java.sql.Date getDate(int columnIndex, java.util.Calendar cal) { return null; }
    @Override public java.sql.Date getDate(String columnLabel, java.util.Calendar cal) { return null; }
    @Override public java.sql.Time getTime(int columnIndex, java.util.Calendar cal) { return null; }
    @Override public java.sql.Time getTime(String columnLabel, java.util.Calendar cal) { return null; }
    @Override public java.sql.Timestamp getTimestamp(int columnIndex, java.util.Calendar cal) { return null; }
    @Override public java.sql.Timestamp getTimestamp(String columnLabel, java.util.Calendar cal) { return null; }
    @Override public java.net.URL getURL(int columnIndex) { return null; }
    @Override public java.net.URL getURL(String columnLabel) { return null; }
    @Override public void updateRef(int columnIndex, java.sql.Ref x) {}
    @Override public void updateRef(String columnLabel, java.sql.Ref x) {}
    @Override public void updateBlob(int columnIndex, java.sql.Blob x) {}
    @Override public void updateBlob(String columnLabel, java.sql.Blob x) {}
    @Override public void updateClob(int columnIndex, java.sql.Clob x) {}
    @Override public void updateClob(String columnLabel, java.sql.Clob x) {}
    @Override public void updateArray(int columnIndex, java.sql.Array x) {}
    @Override public void updateArray(String columnLabel, java.sql.Array x) {}
    @Override public java.sql.RowId getRowId(int columnIndex) { return null; }
    @Override public java.sql.RowId getRowId(String columnLabel) { return null; }
    @Override public void updateRowId(int columnIndex, java.sql.RowId x) {}
    @Override public void updateRowId(String columnLabel, java.sql.RowId x) {}
    @Override public int getHoldability() { return 0; }
    @Override public boolean isClosed() { return false; }
    @Override public void updateNString(int columnIndex, String nString) {}
    @Override public void updateNString(String columnLabel, String nString) {}
    @Override public void updateNClob(int columnIndex, java.sql.NClob nClob) {}
    @Override public void updateNClob(String columnLabel, java.sql.NClob nClob) {}
    @Override public java.sql.NClob getNClob(int columnIndex) { return null; }
    @Override public java.sql.NClob getNClob(String columnLabel) { return null; }
    @Override public java.sql.SQLXML getSQLXML(int columnIndex) { return null; }
    @Override public java.sql.SQLXML getSQLXML(String columnLabel) { return null; }
    @Override public void updateSQLXML(int columnIndex, java.sql.SQLXML xmlObject) {}
    @Override public void updateSQLXML(String columnLabel, java.sql.SQLXML xmlObject) {}
    @Override public String getNString(int columnIndex) { return null; }
    @Override public String getNString(String columnLabel) { return null; }
    @Override public java.io.Reader getNCharacterStream(int columnIndex) { return null; }
    @Override public java.io.Reader getNCharacterStream(String columnLabel) { return null; }
    @Override public void updateNCharacterStream(int columnIndex, java.io.Reader x, long length) {}
    @Override public void updateNCharacterStream(String columnLabel, java.io.Reader x, long length) {}
    @Override public void updateBlob(int columnIndex, java.io.InputStream inputStream, long length) {}
    @Override public void updateBlob(String columnLabel, java.io.InputStream inputStream, long length) {}
    @Override public void updateClob(int columnIndex, java.io.Reader reader, long length) {}
    @Override public void updateClob(String columnLabel, java.io.Reader reader, long length) {}
    @Override public void updateNClob(int columnIndex, java.io.Reader reader, long length) {}
    @Override public void updateNClob(String columnLabel, java.io.Reader reader, long length) {}
    @Override public void updateAsciiStream(int columnIndex, java.io.InputStream x, long length) {}
    @Override public void updateBinaryStream(int columnIndex, java.io.InputStream x, long length) {}
    @Override public void updateCharacterStream(int columnIndex, java.io.Reader x, long length) {}
    @Override public void updateAsciiStream(String columnLabel, java.io.InputStream x, long length) {}
    @Override public void updateBinaryStream(String columnLabel, java.io.InputStream x, long length) {}
    @Override public void updateCharacterStream(String columnLabel, java.io.Reader x, long length) {}
    @Override public void updateBlob(int columnIndex, java.io.InputStream inputStream) {}
    @Override public void updateBlob(String columnLabel, java.io.InputStream inputStream) {}
    @Override public void updateClob(int columnIndex, java.io.Reader reader) {}
    @Override public void updateClob(String columnLabel, java.io.Reader reader) {}
    @Override public void updateNClob(int columnIndex, java.io.Reader reader) {}
    @Override public void updateNClob(String columnLabel, java.io.Reader reader) {}
    @Override public void updateNCharacterStream(int columnIndex, java.io.Reader x) {}
    @Override public void updateNCharacterStream(String columnLabel, java.io.Reader x) {}
    @Override public void updateAsciiStream(int columnIndex, java.io.InputStream x) {}
    @Override public void updateBinaryStream(int columnIndex, java.io.InputStream x) {}
    @Override public void updateCharacterStream(int columnIndex, java.io.Reader x) {}
    @Override public void updateAsciiStream(String columnLabel, java.io.InputStream x) {}
    @Override public void updateBinaryStream(String columnLabel, java.io.InputStream x) {}
    @Override public void updateCharacterStream(String columnLabel, java.io.Reader x) {}
    @Override public <T> T getObject(int columnIndex, Class<T> type) { return null; }
    @Override public <T> T getObject(String columnLabel, Class<T> type) { return null; }
    @Override public <T> T unwrap(Class<T> iface) { return null; }
    @Override public boolean isWrapperFor(Class<?> iface) { return false; }
}
