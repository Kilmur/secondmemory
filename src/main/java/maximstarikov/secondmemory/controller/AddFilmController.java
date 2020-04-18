package maximstarikov.secondmemory.controller;

import maximstarikov.secondmemory.model.Film;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.repository.FilmRepository;
import maximstarikov.secondmemory.repository.UserRepository;
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

    private FilmRepository filmRepository;
    private UserRepository userRepository;

    @GetMapping
    public String getAddFilmPage() {
        return "addFilm";
    }

    @PostMapping
    public String addFilm(Film newFilm, Model model) {
        Film filmFromDb = filmRepository.findFilmByNameAndYear(newFilm.getName(), newFilm.getYear());
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (filmFromDb != null) {
            user.getFilms().add(filmFromDb);
        } else {
            user.getFilms().add(newFilm);
        }
        userRepository.save(user);
        model.addAttribute("newFilm", newFilm);
        return "addFilm";
    }

    @Autowired
    public void setFilmRepository(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
