package maximstarikov.secondmemory.controller;

import lombok.RequiredArgsConstructor;
import maximstarikov.secondmemory.model.Film;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.services.FilmService;
import maximstarikov.secondmemory.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addfilm")
@RequiredArgsConstructor
public class AddFilmController {

    private final FilmService filmService;
    private final UserService userService;

    @GetMapping
    public String getAddFilmPage() {
        return "addFilm";
    }

    @PostMapping
    public String addFilm(Film newFilm, Model model) {
        ServiceResult<User> addFilmResult = filmService.addFilmForCurrentUser(newFilm);
        if (!addFilmResult.isOk()) {
            model.addAttribute("errorMessage", "Фильм не добавлен, попробуйте снова");
            return "addFilm";
        }
        model.addAttribute("newFilm", newFilm);
        return "addFilm";
    }

}
