package simplon.spring.security.service.impl;

import org.springframework.stereotype.Service;
import simplon.spring.security.RestaurantMapper;
import simplon.spring.security.dto.RestaurantDto;
import simplon.spring.security.model.Address;
import simplon.spring.security.model.Restaurant;
import simplon.spring.security.repository.RestaurantRepository;
import simplon.spring.security.service.RestaurantService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void saveRestau(RestaurantDto restau) {
        Address addressEntity = Address.builder()
                .number(restau.getNumber())
                .type(restau.getType())
                .road(restau.getRoad())
                .city(restau.getCity())
                .country(restau.getCountry())
                .build();

        Restaurant restauEntity = Restaurant.builder()
                .name(restau.getName())
                .description(restau.getDescription())
                .address(addressEntity)
                .build();

        restaurantRepository.save(restauEntity);
    }

    @Override
    public Optional<Restaurant> getByName(String name) {
        return restaurantRepository.findByName(name);
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(this::convertToDto).toList();
    }

    private RestaurantDto convertToDto(Restaurant restaurant) {
        RestaurantDto dto = new RestaurantDto();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setDescription(restaurant.getDescription());
        dto.setProducts(restaurant.getProducts().stream().map(RestaurantMapper::toProductDto).collect(Collectors.toSet()));
        return dto;
    }

    public RestaurantDto getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        return RestaurantMapper.toRestaurantDto(restaurant);
    }
}
