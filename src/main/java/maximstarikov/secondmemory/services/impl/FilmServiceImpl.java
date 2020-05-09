package maximstarikov.secondmemory.services.impl;

import maximstarikov.secondmemory.model.Film;
import maximstarikov.secondmemory.repository.FilmRepository;
import maximstarikov.secondmemory.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

    public Film getByNameAndYear(String name, int year) {
        Optional<Film> optionalFilm = filmRepository.findFilmByNameAndYear(name, year);
        return optionalFilm.orElse(null);
    }

    @Autowired
    public void setFilmRepository(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

}
