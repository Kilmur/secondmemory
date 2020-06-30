package maximstarikov.secondmemory.services.dao;

import maximstarikov.secondmemory.model.Film;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;

public interface FilmService {

    ServiceResult<Film> getByNameAndYear(String name, int year);

    ServiceResult<User> addFilmForCurrentUser(Film newFilm);

}
