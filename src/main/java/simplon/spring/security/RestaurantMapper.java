package simplon.spring.security;

import simplon.spring.security.dto.ProductDto;
import simplon.spring.security.dto.RestaurantDto;
import simplon.spring.security.model.Product;
import simplon.spring.security.model.Restaurant;

import java.util.Set;
import java.util.stream.Collectors;

// Classe pour mapper entre les entity et les Dto
public class RestaurantMapper {

    // converti entity Restaurant en RestaurantDto
    public static RestaurantDto toRestaurantDto(Restaurant restaurant) {
        if (restaurant == null) {
            // Si entity est null, retourne null
            return null;
        }

        RestaurantDto restaurantDTO = new RestaurantDto();
        // recup l'ID
        restaurantDTO.setId(restaurant.getId());
        // recup le nom
        restaurantDTO.setName(restaurant.getName());
        // recup la description
        restaurantDTO.setDescription(restaurant.getDescription());

        // Converti la liste des produits en ProductDto
        Set<ProductDto> products = restaurant.getProducts().stream()
                // Utilise la méthode toProductDto pour chaque produit
                .map(RestaurantMapper::toProductDto)
                .collect(Collectors.toSet());
        restaurantDTO.setProducts(products);

        // Retourne le dto
        return restaurantDTO;
    }

    // converti un RestaurantDto en entity Restaurant
    /*public static Restaurant toEntity(RestaurantDto restaurantDTO) {
        if (restaurantDTO == null) {
            // Si dto est null, retourne null
            return null;
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDTO.getId());
        restaurant.setName(restaurantDTO.getName());

        // Converti la liste des ProductDto en Product et les ajoute à l'entity
        Set<Product> products = restaurantDTO.getProducts().stream()
                .map(RestaurantMapper::toEntity)
                .collect(Collectors.toSet());
        restaurant.setProducts(products);

        return restaurant;
    }*/

    // converti une entity Product en ProductDto
    public static ProductDto toProductDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDto productDTO = new ProductDto();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());

        return productDTO;
    }

    // converti un ProductDto en entity Product
    /*public static Product toEntity(ProductDto productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());

        return product;
    }*/
}
