package maximstarikov.secondmemory.services.impl;

import maximstarikov.secondmemory.model.Film;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.repository.FilmRepository;
import maximstarikov.secondmemory.services.FilmService;
import maximstarikov.secondmemory.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private static final Logger LOGGER = Logger.getLogger(FilmServiceImpl.class);

    private FilmRepository filmRepository;
    private UserService userService;

    public ServiceResult<Film> getByNameAndYear(String name, int year) {
        Optional<Film> optionalFilm = filmRepository.findFilmByNameAndYear(name, year);
        if (!optionalFilm.isPresent()) {
            return ServiceResult.error("Film not found");
        }
        return ServiceResult.success(optionalFilm.get());
    }

    public ServiceResult<User> addFilmForCurrentUser(Film newFilm) {
        ServiceResult<Film> getFilmResult = getByNameAndYear(newFilm.getName(), newFilm.getYear());
        ServiceResult<User> currentUserResult = userService.getCurrentUser();
        if (!currentUserResult.isOk()) {
            LOGGER.error(currentUserResult.getErrorMessage());
            return currentUserResult;
        }
        User user = currentUserResult.get();
        if (getFilmResult.isOk()) {
            user.getFilms().add(getFilmResult.get());
        } else {
            user.getFilms().add(newFilm);
        }
        userService.save(user);
        return ServiceResult.success(user);
    }

    @Autowired
    public void setFilmRepository(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
