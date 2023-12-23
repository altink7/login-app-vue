package at.altin.logic;

import at.altin.data.cipher.AESCipherService;
import at.altin.data.cipher.api.CipherService;
import at.altin.data.invoker.UserOperationInvoker;
import at.altin.data.operation.GetUserOperation;
import at.altin.data.operation.SaveUserOperation;
import at.altin.data.operation.api.UserOperation;
import at.altin.logic.api.UserService;
import at.altin.model.User;

import java.io.File;
import java.io.IOException;

public class UserServiceImpl implements UserService {
    UserOperationInvoker invoker = new UserOperationInvoker();
    UserOperation userOperation;
    CipherService cipherService = new AESCipherService();
    String fileDirectory = getPath();


    public String getPath() {
        try {
            String fileName = "user.txt";
            String projectDirectory = System.getProperty("user.dir");
            File file = new File(projectDirectory, fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            return file.getAbsolutePath();
        } catch (IOException e) {
            System.out.println("Error while getting path: " + e);
            return null;
        }
    }

    @Override
    public User login(String username, String password) {
        userOperation = new GetUserOperation(cipherService, fileDirectory);
        return invoker.addUserOperation(userOperation, username, password);
    }

    @Override
    public User register(String username, String password) {
        userOperation = new SaveUserOperation(cipherService, fileDirectory);
        return invoker.addUserOperation(userOperation, username, password);
    }

    @Override
    public String log() {
        return invoker.logOperations();
    }
}
