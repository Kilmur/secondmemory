package maximstarikov.secondmemory.repository;

import maximstarikov.secondmemory.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findFilmByNameAndYear(String name, int year);
}
