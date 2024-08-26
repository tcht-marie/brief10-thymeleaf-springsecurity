package simplon.spring.security.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import simplon.spring.security.dto.RestaurantDto;
import simplon.spring.security.service.RestaurantService;

import java.util.List;

@Controller
public class RestauController {

    private final RestaurantService restaurantService;

    public RestauController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/addrestaurant")
    public String addrestaurant(Model model) {
        RestaurantDto restaurant = new RestaurantDto();
        model.addAttribute("restaurant", restaurant);
        return "addrestaurant";
    }

    @PostMapping("/addrestaurant/save")
    public String registerRestau(@Valid @ModelAttribute RestaurantDto restauMapping) {
        if (restauMapping.getName() == null) {
            return "redirect:/addrestaurant?error";
        }
        restaurantService.saveRestau(restauMapping);

        return "redirect:/addrestaurant?success=restauRegistered";
    }

    @GetMapping("/restaurant")
    public String restaurant(Model model) {
        List<RestaurantDto> restaurants = restaurantService.getAllRestaurants();
        model.addAttribute("restaurants", restaurants);
        return "forknow-restaurants-list";
    }

    @GetMapping("/restaurant/{id}")
    public String getRestaurant(@PathVariable("id") Long id, Model model) {
        System.out.println("test");
        RestaurantDto restaurantDto = restaurantService.getRestaurantById(id);
        model.addAttribute("restaurant", restaurantDto);
        if (restaurantDto.getProducts().isEmpty()) {
            return "no-products";
        } else {
            return "products";
        }
    }
}
