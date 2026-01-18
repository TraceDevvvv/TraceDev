package com.example.entity;

/**
 * Represents an Administrator actor.
 * Contains basic administrator details and login status.
 */
public class Administrator {
    private Long id;
    private String name;

    public Administrator(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the administrator is logged in.
     * For simplicity, this returns true; in a real system it would check session state.
     *
     * @return boolean indicating login status.
     */
    public boolean isLoggedIn() {
        return true;
    }

    /**
     * Administrator views user list (sequence diagram message).
     */
    public void viewsUserList() {
        System.out.println("Administrator views user list.");
    }

    /**
     * Administrator clicks "New User" (sequence diagram message).
     */
    public void clicksNewUser() {
        System.out.println("Administrator clicks New User.");
    }

    /**
     * Administrator fills form fields (sequence diagram message).
     */
    public void fillsFormFields() {
        System.out.println("Administrator fills form fields.");
    }

    /**
     * Administrator clicks "Save" (sequence diagram message).
     */
    public void clicksSave() {
        System.out.println("Administrator clicks Save.");
    }

    /**
     * Administrator corrects data (sequence diagram message).
     */
    public void correctsData() {
        System.out.println("Administrator corrects data.");
    }

    /**
     * Administrator clicks "Save" again (sequence diagram message).
     */
    public void clicksSaveAgain() {
        System.out.println("Administrator clicks Save again.");
    }

    /**
     * Administrator closes form (sequence diagram message).
     */
    public void closesForm() {
        System.out.println("Administrator closes form.");
    }

    /**
     * Administrator cancels/closes form (sequence diagram message).
     */
    public void cancelClose() {
        System.out.println("Administrator cancels/closes form.");
    }
}