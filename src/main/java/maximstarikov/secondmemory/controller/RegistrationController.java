package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    @GetMapping
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping
    public String addNewUser(User user, Model model) {
        ServiceResult addUserResult = userService.addNewUser(user);
        if (!addUserResult.isOk()) {
            model.addAttribute("message", addUserResult.getErrorMessage());
            return "registration";
        }
        return "redirect:/login";
    }

    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }
}
