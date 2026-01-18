package smos.system;

/**
 * Error message class with code and text.
 * Satisfies requirement REQ-002.
 */
public class ErrorMessage {
    private String code;
    private String text;

    public ErrorMessage(String text) {
        this.text = text;
        this.code = "ERR"; // default code
    }

    public ErrorMessage(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text; // as per sequence diagram, message uses error text
    }
}