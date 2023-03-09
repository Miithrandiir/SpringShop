package fr.ulco.springshop.helper;

import fr.ulco.springshop.security.UserDetailsServiceInterface;
import fr.ulco.springshop.security.jwt.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

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

    public RequestPostProcessor jwt(String username){
        return new AuthenticationHelperPostProcessor(generateToken(username));
    }

    public static RequestPostProcessor customJwt(String token){
        return new AuthenticationHelperPostProcessor(token);
    }

    public static class AuthenticationHelperPostProcessor implements RequestPostProcessor {

        private final String token;

        private AuthenticationHelperPostProcessor(String token) {
            this.token = token;
        }

        @Override
        public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {

            request.addHeader("Authorization", "Bearer " + this.token);

            return request;
        }
    }

}
