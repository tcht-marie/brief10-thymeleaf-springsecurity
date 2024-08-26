package simplon.spring.security.service;

import simplon.spring.security.dto.RestaurantDto;
import simplon.spring.security.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    void saveRestau(RestaurantDto restaurantRegister);

    Optional<Restaurant> getByName(String name);

    List<RestaurantDto> getAllRestaurants();

    RestaurantDto getRestaurantById(Long id);
}
