package simplon.spring.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Getter
@Setter
// permet d'avoir un constructeur vide
@NoArgsConstructor
// permet d'avoir un constructor avec tous les paramètres non null
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String email;

    private String password;

    private LocalDate date;

    private UserRole role;
}
