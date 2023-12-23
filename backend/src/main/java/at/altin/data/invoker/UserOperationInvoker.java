package at.altin.data.invoker;

import at.altin.data.operation.api.UserOperation;
import at.altin.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class UserOperationInvoker {
    private HashMap<UserOperation, LocalDate> uuserOperations = new HashMap<>();

    public User addUserOperation(UserOperation userOperation, String username, String password) {
        uuserOperations.put(userOperation, LocalDate.now());
        return userOperation.execute(username, password);
    }

    public String logOperations() {
        StringBuilder log = new StringBuilder();
        List<UserOperation> operations = new ArrayList<>(uuserOperations.keySet());
        IntStream.range(0, operations.size()).forEachOrdered(i -> {
            log.append(operations.get(i).getClass().getSimpleName()).append(" ").append(uuserOperations.get(operations.get(i)));
            if (i != operations.size() - 1) {
                log.append("\n");
            }
        });
        return log.toString();
    }
}
