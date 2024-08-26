package simplon.spring.security.service;

import org.springframework.security.core.Authentication;
import simplon.spring.security.dto.RegisterDto;
import simplon.spring.security.model.User;
import simplon.spring.security.dto.UserDto;

import java.util.Optional;

public interface UserService {
    void saveUser(UserDto user);

    void saveUser(RegisterDto userRegister);

    Optional<User> getByUsername(String username);

    Optional<User> getByEmail(String email);

    Optional<User> from(Authentication authentication);
}
