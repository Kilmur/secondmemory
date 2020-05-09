package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.Role;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    @GetMapping
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping
    public String addNewUser(User user, Map<String, Object> model) {

        User userFromDb = userService.getByName(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User already exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.save(user);

        return "redirect:/login";
    }

    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }
}
