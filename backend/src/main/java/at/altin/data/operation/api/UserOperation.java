package at.altin.data.operation.api;

import at.altin.model.User;

@FunctionalInterface
public interface UserOperation {

    User execute(String username, String password);

}
