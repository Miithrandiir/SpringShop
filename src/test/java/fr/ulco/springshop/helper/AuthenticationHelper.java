package fr.ulco.springshop.helper;

import fr.ulco.springshop.security.UserDetailsServiceInterface;
import fr.ulco.springshop.security.jwt.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuthenticationHelper {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceInterface userDetailsService;

    public String generateToken(String username) {
        return jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(username));
    }

}
