package at.altin.logic.api;

import at.altin.model.User;

public interface UserService {

    User login(String username, String password);

    User register(String username, String password);

    String log();
}
