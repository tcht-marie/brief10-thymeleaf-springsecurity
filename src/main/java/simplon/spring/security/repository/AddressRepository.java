package simplon.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simplon.spring.security.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
