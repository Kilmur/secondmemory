package maximstarikov.secondmemory.controller;

import lombok.RequiredArgsConstructor;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.services.dao.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/allfilms")
@RequiredArgsConstructor
public class AllFilmsController {

    private final UserService userService;

    @GetMapping
    public String getAllFilms(Model model) {
        ServiceResult<User> currentUserResult = userService.getCurrentUser();
        if (!currentUserResult.isOk()) {
            return "redirect:/error";
        }
        model.addAttribute("films", currentUserResult.get().getFilms());
        return "allFilms";
    }

}
