package simplon.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simplon.spring.security.model.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name);

}
