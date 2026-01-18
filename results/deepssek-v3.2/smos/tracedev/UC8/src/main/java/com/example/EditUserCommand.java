package com.example;

/**
 * Command object for editing a user.
 */
public class EditUserCommand {
    private String userId;
    private EditUserDTO editUserDTO;

    public EditUserCommand(String userId, EditUserDTO editUserDTO) {
        this.userId = userId;
        this.editUserDTO = editUserDTO;
    }

    public String getUserId() {
        return userId;
    }

    public EditUserDTO getEditUserDTO() {
        return editUserDTO;
    }
}