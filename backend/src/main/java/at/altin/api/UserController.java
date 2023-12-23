package at.altin.api;

import at.altin.logic.UserServiceImpl;
import at.altin.logic.api.UserService;
import at.altin.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Validated
public class UserController {
    private final UserService userService = new UserServiceImpl();

    @GetMapping("/login/user/{username}/password/{password}")
    public String login(@PathVariable String username, @PathVariable String password) {
        return userService.login(username, password).getUsername();
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user.getUsername(), user.getPassword());
    }

    @GetMapping("/log")
    public String log() {
        return userService.log();
    }
}
