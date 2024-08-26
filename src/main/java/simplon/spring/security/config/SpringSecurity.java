package simplon.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SpringSecurity {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SpringSecurity(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.builder().username("user").password(passwordEncoder().encode("pass")).roles("USER").build(),
                User.builder().username("admin").password(passwordEncoder().encode("pass")).roles("ADMIN").build()
        );
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/products/**", "/cart", "/account/**").authenticated()
                        .requestMatchers("/addrestaurant/**", "/addproduct/**").authenticated()
                        .requestMatchers("/restaurant/**").authenticated()
                        .requestMatchers("/register/**").anonymous()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/css/**", "favicon.ico", "/images/**").permitAll()
        );
        // formlogin fait une session dans le serveur (cookies)
        http.formLogin((login) -> login.defaultSuccessUrl("/", true).permitAll());
        // http.logout((logout) -> logout.permitAll());
        http.logout((logout) -> logout.logoutSuccessUrl("/").permitAll());
        return http.build();
    }

    @Autowired
    public void confGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
