package maximstarikov.secondmemory.repository;

import maximstarikov.secondmemory.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
    Film findFilmByNameAndYear(String name, int year);
}
