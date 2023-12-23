package at.altin.data.operation;
import at.altin.data.cipher.api.CipherService;
import at.altin.data.operation.api.UserOperation;
import at.altin.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetUserOperation implements UserOperation {

    private final CipherService cipherService;
    private final String fileDirectory;

    public GetUserOperation(CipherService cipherService, String fileDirectory) {
        this.cipherService = cipherService;
        this.fileDirectory = fileDirectory;
    }

    @Override
    public User execute(String username, String password) {
        try {
            Path filePath = Paths.get(fileDirectory);

            String decryptedUser = Files.lines(filePath)
                    .filter(line -> line.equals(cipherService.encrypt(new User(username, password).toString())))
                    .findFirst()
                    .map(cipherService::decrypt)
                    .orElse(null);

            if (decryptedUser == null) {
                return null;
            }

            return User.fromString(decryptedUser);
        } catch (IOException e) {
            System.out.println("Error while retrieving user: " + e);
        }

        return null;
    }
}
