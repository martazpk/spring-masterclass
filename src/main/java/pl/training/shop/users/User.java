package pl.training.shop.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.training.shop.common.validator.Name;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(exclude = "id")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Pattern(regexp = "[A-Za-z]")
    @Column(name = "name", nullable = false)
    private String name;
    @Name
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(unique = true, nullable = false)
    private String email;
}
