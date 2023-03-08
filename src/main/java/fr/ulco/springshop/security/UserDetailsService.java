package fr.ulco.springshop.security;

import fr.ulco.springshop.model.bo.UserBO;
import fr.ulco.springshop.service.core.UserServiceInterface;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailsService implements UserDetailsServiceInterface {

    public static UserDetailsService create(UserServiceInterface userService) {
        return new UserDetailsService(userService);
    }

    private UserServiceInterface userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserBO> user = userService.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), user.get().getAuthorities());
        //return user.get();
    }
}
