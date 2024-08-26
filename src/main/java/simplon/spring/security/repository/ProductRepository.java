package simplon.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simplon.spring.security.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
}
