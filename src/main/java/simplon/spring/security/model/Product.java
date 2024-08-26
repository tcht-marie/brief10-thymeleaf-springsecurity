package simplon.spring.security.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    private String description;

    private LocalDate date;

    @ManyToMany(mappedBy = "products")
    private Set<Restaurant> restaurants = new HashSet<>();
}
