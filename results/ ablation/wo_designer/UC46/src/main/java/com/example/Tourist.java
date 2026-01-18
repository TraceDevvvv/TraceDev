import java.util.List;

public class Tourist {
    private String username;
    private String password;
    private SearchPreference searchPreference;

    public Tourist(String username, String password, SearchPreference searchPreference) {
        this.username = username;
        this.password = password;
        this.searchPreference = searchPreference;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SearchPreference getSearchPreference() {
        return searchPreference;
    }

    public void setSearchPreference(SearchPreference searchPreference) {
        this.searchPreference = searchPreference;
    }
}