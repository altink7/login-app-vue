package at.altin.model;

public class User {
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static User fromString(String decryptedUser) {
        String userContent = decryptedUser.substring(decryptedUser.indexOf('{') + 1, decryptedUser.indexOf('}'));
        String[] userParts = userContent.split(",");

        String username = userParts[0].trim().substring(userParts[0].indexOf('=') + 1);
        String password = userParts[1].trim().substring(userParts[1].indexOf('=') + 1);

        return new User(username, password);
    }

}
