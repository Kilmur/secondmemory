package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.Film;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.repository.FilmRepository;
import maximstarikov.secondmemory.services.FilmService;
import maximstarikov.secondmemory.services.UserService;
import maximstarikov.secondmemory.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addfilm")
public class AddFilmController {

    private FilmService filmService;
    private UserService userService;

    @GetMapping
    public String getAddFilmPage() {
        return "addFilm";
    }

    @PostMapping
    public String addFilm(Film newFilm, Model model) {
        Film filmFromDb = filmService.getByNameAndYear(newFilm.getName(), newFilm.getYear());
        User user = userService.getByName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (filmFromDb != null) {
            user.getFilms().add(filmFromDb);
        } else {
            user.getFilms().add(newFilm);
        }
        userService.save(user);
        model.addAttribute("newFilm", newFilm);
        return "addFilm";
    }

    @Autowired
    public void setFilmRepository(FilmService filmService) {
        this.filmService = filmService;
    }

    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }
}
