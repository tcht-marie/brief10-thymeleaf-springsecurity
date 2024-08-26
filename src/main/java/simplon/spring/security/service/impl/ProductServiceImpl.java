package simplon.spring.security.service.impl;

import org.springframework.stereotype.Service;
import simplon.spring.security.dto.ProductDto;
import simplon.spring.security.model.Product;
import simplon.spring.security.repository.ProductRepository;
import simplon.spring.security.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(ProductDto product) {
        Product productEntity = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .build();
        productRepository.save(productEntity);
    }

    @Override
    public Optional<Product> getByProductName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDto).toList();
    }

    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        return dto;
    }
}
