package pl.training.shop.users;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(exclude = "id")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(unique = true, nullable = false)
    private String email;
}
