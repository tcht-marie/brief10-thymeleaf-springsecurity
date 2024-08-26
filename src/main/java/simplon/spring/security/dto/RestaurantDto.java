package simplon.spring.security.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    private Long id;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    private String description;

    private int number;

    private String type;

    @NotEmpty(message = "Road must not be empty")
    private String road;

    @NotEmpty(message = "City must not be empty")
    private String city;

    @NotEmpty(message = "Country must not be empty")
    private String country;

    private Set<ProductDto> products;
}
