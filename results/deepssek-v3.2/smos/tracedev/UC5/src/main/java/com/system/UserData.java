package com.system;

/**
 * Data transfer object representing raw user data from the Archive.
 */
public class UserData {
    private String dataId;
    private String name;
    private String contact;

    public UserData(String dataId, String name, String contact) {
        this.dataId = dataId;
        this.name = name;
        this.contact = contact;
    }

    public String getDataId() {
        return dataId;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}