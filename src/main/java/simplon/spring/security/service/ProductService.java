package simplon.spring.security.service;

import simplon.spring.security.dto.ProductDto;
import simplon.spring.security.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void saveProduct(ProductDto productDto);

    Optional<Product> getByProductName(String name);

    List<ProductDto> getAllProducts();
}
