package simplon.spring.security.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String firstname;

    private String lastname;

    private String companyName;

    private int number;

    private String type;

    @NonNull
    private String road;

    @NonNull
    private String city;

    @NonNull
    private String country;

    @OneToOne(mappedBy = "address")
    private Restaurant restaurant;
}
