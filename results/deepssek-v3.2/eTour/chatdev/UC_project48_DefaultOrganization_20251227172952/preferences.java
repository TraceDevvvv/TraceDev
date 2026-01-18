/**
 * Represents the generic personal preferences of a tourist.
 * Serializable to allow storage to disk/database.
 */
package modifygenericpreference;
import java.io.Serializable;
public class Preferences implements Serializable {
    private static final long serialVersionUID = 1L;
    private String preferredLanguage;
    private String theme;
    private boolean emailNotifications;
    private boolean pushNotifications;
    private String currency;
    private String dateFormat;
    private int resultsPerPage;
    private boolean accessibilityMode;
    public Preferences() {
        this.preferredLanguage = "English";
        this.theme = "auto";
        this.emailNotifications = true;
        this.pushNotifications = true;
        this.currency = "USD";
        this.dateFormat = "YYYY-MM-DD";
        this.resultsPerPage = 10;
        this.accessibilityMode = false;
    }
    public Preferences(String preferredLanguage, String theme, boolean emailNotifications,
                      boolean pushNotifications, String currency, String dateFormat,
                      int resultsPerPage, boolean accessibilityMode) {
        this.preferredLanguage = preferredLanguage;
        this.theme = theme;
        this.emailNotifications = emailNotifications;
        this.pushNotifications = pushNotifications;
        this.currency = currency;
        this.dateFormat = dateFormat;
        this.resultsPerPage = resultsPerPage;
        this.accessibilityMode = accessibilityMode;
    }
    public String getPreferredLanguage() {
        return preferredLanguage;
    }
    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    public boolean isEmailNotifications() {
        return emailNotifications;
    }
    public void setEmailNotifications(boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }
    public boolean isPushNotifications() {
        return pushNotifications;
    }
    public void setPushNotifications(boolean pushNotifications) {
        this.pushNotifications = pushNotifications;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getDateFormat() {
        return dateFormat;
    }
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    public int getResultsPerPage() {
        return resultsPerPage;
    }
    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
    public boolean isAccessibilityMode() {
        return accessibilityMode;
    }
    public void setAccessibilityMode(boolean accessibilityMode) {
        this.accessibilityMode = accessibilityMode;
    }
    @Override
    public String toString() {
        return "Preferences{" +
               "language='" + preferredLanguage + '\'' +
               ", theme='" + theme + '\'' +
               ", emailNotifications=" + emailNotifications +
               ", pushNotifications=" + pushNotifications +
               ", currency='" + currency + '\'' +
               ", dateFormat='" + dateFormat + '\'' +
               ", resultsPerPage=" + resultsPerPage +
               ", accessibilityMode=" + accessibilityMode +
               '}';
    }
}