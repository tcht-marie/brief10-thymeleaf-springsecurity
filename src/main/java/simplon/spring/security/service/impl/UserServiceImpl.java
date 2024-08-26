package simplon.spring.security.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import simplon.spring.security.dto.RegisterDto;
import simplon.spring.security.model.User;
import simplon.spring.security.dto.UserDto;
import simplon.spring.security.model.UserRole;
import simplon.spring.security.repository.UserRepository;
import simplon.spring.security.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto user) {
        User userEntity = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(UserRole.USER)
                .build();
        userRepository.save(userEntity);
    }

    @Override
    public void saveUser(RegisterDto user) {
        User userEntity = User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(UserRole.USER)
                .build();
        userRepository.save(userEntity);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> from(Authentication authentication) {
        if(authentication == null){
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();
        if(!(principal instanceof UserDetails)){
            return Optional.empty();
        }

        UserDetails userDetails = (UserDetails)principal;
        return userRepository.findByUsername(userDetails.getUsername());
    }
}
