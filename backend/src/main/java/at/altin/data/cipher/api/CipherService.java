package at.altin.data.cipher.api;

public interface CipherService {
    String encrypt(String text);

    String decrypt(String text);
}
