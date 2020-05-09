package maximstarikov.secondmemory.services;

import maximstarikov.secondmemory.model.Film;

public interface FilmService {

    Film getByNameAndYear(String name, int year);

}
