package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/allfilms")
public class AllFilmsController {

    private UserService userService;

    @GetMapping
    public String getAllFilms(Model model) {
        ServiceResult<User> currentUserResult = userService.getCurrentUser();
        if (!currentUserResult.isOk()) {
            return "redirect:/error";
        }
        model.addAttribute("films", currentUserResult.get().getFilms());
        return "allFilms";
    }

    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }
}
