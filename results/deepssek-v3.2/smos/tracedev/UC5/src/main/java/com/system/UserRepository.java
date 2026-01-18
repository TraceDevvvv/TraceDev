package com.system;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete repository implementation.
 * Depends on Archive and transforms UserData to User.
 * REQ: Flow of Events #2 - Data transformation
 */
public class UserRepository implements IUserRepository {
    private Archive archive;

    public UserRepository() {
        this.archive = new Archive();
    }

    /**
     * Finds all users by querying the Archive.
     * Sequence diagram: Controller -> Repository : findAll()
     */
    @Override
    public List<User> findAll() {
        // REQ: Exit Conditions #2 - Check connection
        if (!archive.isConnected()) {
            System.err.println("Archive connection interrupted.");
            return new ArrayList<>(); // Return empty list as per sequence diagram
        }

        List<UserData> userDataList = archive.searchForUsers();
        return convertToUsers(userDataList);
    }

    /**
     * Transforms UserData objects to User objects.
     * Sequence diagram: Repository -> Repository : convertToUsers(userData)
     */
    List<User> convertToUsers(List<UserData> userDataList) {
        List<User> users = new ArrayList<>();
        if (userDataList != null) {
            for (UserData data : userDataList) {
                // Assume mapping: dataId -> userId, name -> username, contact -> email
                User user = new User(data.getDataId(), data.getName(), data.getContact());
                users.add(user);
            }
        }
        return users;
    }
}