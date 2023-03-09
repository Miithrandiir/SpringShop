package fr.ulco.springshop.configurations;

import fr.ulco.springshop.controllers.Routes;
import fr.ulco.springshop.security.CustomCorsFilter;
import fr.ulco.springshop.security.UserDetailsService;
import fr.ulco.springshop.security.UserDetailsServiceInterface;
import fr.ulco.springshop.security.jwt.JwtAuthenticationEntryPoint;
import fr.ulco.springshop.security.jwt.JwtTokenFilter;
import fr.ulco.springshop.security.jwt.JwtTokenUtil;
import fr.ulco.springshop.service.core.UserServiceInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsServiceInterface userDetailsService(UserServiceInterface userService) {
        return UserDetailsService.create(userService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }


    public JwtTokenFilter jwtTokenFilter(final JwtTokenUtil jwtTokenUtil, final UserDetailsServiceInterface userDetailsService) {
        return new JwtTokenFilter(userDetailsService, jwtTokenUtil);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http, final JwtTokenFilter jwtTokenFilter, final CustomCorsFilter customCorsFilter) throws Exception {
        //https://github.com/spring-projects/spring-security/issues/12766
        AuthorityAuthorizationManager<RequestAuthorizationContext> hasRoleUser =
                AuthorityAuthorizationManager.hasRole("USER");
        hasRoleUser.setRoleHierarchy(roleHierarchy());

        http.csrf()
                .disable()
                .authorizeHttpRequests()
//                .requestMatchers(Routes.DELETE_CATEGORY_BY_SLUG, Routes.UPDATE_CATEGORY_BY_SLUG).hasRole("USER")
//                .requestMatchers(Routes.GET_CATEGORIES).access(hasRoleUser)
                .requestMatchers("/api/auth**", "/api/auth/**").permitAll()
                .requestMatchers("/webjars/**", "/swagger-ui*/*swagger-initializer.js", "/swagger-ui*/**", "/api/doc**", "/api/doc/**", "/api").permitAll()

                .requestMatchers(HttpMethod.GET, Routes.GET_PRODUCTS, Routes.GET_PRODUCTS + "/**").permitAll()
                .requestMatchers(HttpMethod.GET, Routes.GET_CATEGORIES, Routes.GET_CATEGORIES + "/**").permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint()).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        return http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class).addFilterBefore(customCorsFilter, JwtTokenFilter.class).build();
    }

    @Bean
    public CorsFilter corsFilter() {
        final var source = new UrlBasedCorsConfigurationSource();
        final var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        config.addExposedHeader("Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
                "Content-Type, Access-Control-Request-Method, Location");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        final RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return roleHierarchy;
    }

    @Bean
    public DefaultMethodSecurityExpressionHandler expressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }
}
