package at.altin.data.operation;

import at.altin.data.cipher.api.CipherService;
import at.altin.data.operation.api.UserOperation;
import at.altin.model.User;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SaveUserOperation implements UserOperation {

    private final CipherService cipherService;
    private final String fileDirectory;

    public SaveUserOperation(CipherService cipherService, String fileDirectory) {
        this.cipherService = cipherService;
        this.fileDirectory = fileDirectory;
    }

    @Override
    public User execute(String username, String password) {
        String userToBeSaved = cipherService.encrypt(new User(username, password).toString());

        Path path = Paths.get(fileDirectory);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            if (Files.lines(path).anyMatch(line -> line.equals(userToBeSaved))) {
                writer.close();
                return null;
            }

            writer.write(userToBeSaved);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error while saving user: " + e);
        }

        System.out.println("User saved successfully:" + userToBeSaved);
        return User.fromString(cipherService.decrypt(userToBeSaved));
    }
}
