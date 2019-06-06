package com.example.lab1_reyes_a.data;

import com.example.lab1_reyes_a.data.model.LoggedInUser;
import com.example.lab1_reyes_a.db.DbHelper;
import com.example.lab1_reyes_a.db.User;
import com.example.lab1_reyes_a.ui.login.LoginActivity;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
