package simplon.spring.security.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import simplon.spring.security.dto.ProductDto;
import simplon.spring.security.service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(Model model) {
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/addproduct")
    public String addproduct(Model model) {
        ProductDto product = new ProductDto();
        model.addAttribute("product", product);
        return "addproduct";
    }

    @PostMapping("addproduct/save")
    public String registerProduct(@Valid @ModelAttribute ProductDto productMapping) {
        if (productMapping.getName() == null) {
            return "redirect:/addproduct?error";
        }
        productService.saveProduct(productMapping);
        return "redirect:/addproduct?success=productRegistered";
    }
}
