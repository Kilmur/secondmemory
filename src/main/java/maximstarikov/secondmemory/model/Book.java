package maximstarikov.secondmemory.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = {"name", "author"})
@ToString(of = {"name", "author"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String author;

    @ManyToMany(mappedBy = "books")
    @Getter(AccessLevel.NONE)
    private Set<User> users;

    public Set<User> getUsers() {
        if (users == null) {
            return new HashSet<>();
        }
        return users;
    }

}
