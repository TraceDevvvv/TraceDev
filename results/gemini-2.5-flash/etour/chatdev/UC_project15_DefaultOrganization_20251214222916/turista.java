/*
 * DOCSTRING:
 * This class represents a Turista (Tourist) account with basic personal information.
 * It includes fields for ID, name, surname, email, and phone number,
 * along with constructors, getters, and setters for these fields.
 * The toString() method is overridden for convenient display in UI components.
 */
class Turista {
    private String id;
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    /**
     * Constructs a new Turista object.
     *
     * @param id The unique identifier for the tourist.
     * @param nome The first name of the tourist.
     * @param cognome The last name of the tourist.
     * @param email The email address of the tourist.
     * @param telefono The phone number of the tourist.
     */
    public Turista(String id, String nome, String cognome, String email, String telefono) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
    }
    /**
     * Returns the ID of the tourist.
     * @return The tourist's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the ID of the tourist.
     * @param id The new ID for the tourist.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Returns the first name of the tourist.
     * @return The tourist's first name.
     */
    public String getNome() {
        return nome;
    }
    /**
     * Sets the first name of the tourist.
     * @param nome The new first name for the tourist.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Returns the last name of the tourist.
     * @return The tourist's last name.
     */
    public String getCognome() {
        return cognome;
    }
    /**
     * Sets the last name of the tourist.
     * @param cognome The new last name for the tourist.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    /**
     * Returns the email address of the tourist.
     * @return The tourist's email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the email address of the tourist.
     * @param email The new email for the tourist.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Returns the phone number of the tourist.
     * @return The tourist's phone number.
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * Sets the phone number of the tourist.
     * @param telefono The new phone number for the tourist.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * Overrides the toString method to provide a user-friendly string representation
     * of the Turista object, typically used for displaying in UI components.
     * @return A string combining the tourist's name and ID.
     */
    @Override
    public String toString() {
        return nome + " " + cognome + " (ID: " + id + ")";
    }
}