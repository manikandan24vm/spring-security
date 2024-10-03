package com.springsecurity.configuration;

import com.springsecurity.filters.CsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("prod")
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // http.csrf((csrfConfigurer)->csrfConfigurer.disable());
        http//.requiresChannel(requiresChannelCustomizer-> requiresChannelCustomizer.anyRequest().requiresSecure())
                .csrf(csrfConfigurer -> csrfConfigurer
                        //.ignoringRequestMatchers() we can disable csrf for certain requests.
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                        .csrfTokenRepository(new CookieCsrfTokenRepository()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                // restricting users form creating multiple user sessions. to have that we need ro use session management
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.maximumSessions(1).maxSessionsPreventsLogin(true))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/employee/addEmp").hasAnyAuthority("create-employee","view-employee","update-employee","delete-employee")
                        .requestMatchers("/employee/delete/**").hasAnyAuthority("delete-employee")
                        .requestMatchers("/login/**").authenticated());
        // http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
/*
    @Bean
    public UserDetailsService createUsers() {
        //{noop}-> means we decided not to use any password encoder.
        UserDetails user = User.withUsername("vm")
                               .password("{bcrypt}$2y$10$S6XBD2US12z.DIRwP0XUDOuqtftMXq21vRJfGKgRKpIUzzx2EqSVa")
                               .authorities("basic").build();
        UserDetails user1 = User
                                .withUsername("vm1")
                                .password("{bcrypt}$2y$10$CGGVWkJ/Zl/ZzHIci2Nw2OjD45O05dxKe1WQKUHJiont6dbZWpIEe")
                                .authorities("basic")
                                .build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2y$10$5u0Pm.Am/5R6rrZ1UTA62er.bVwh20SaDGnlGYUQAZrJSFwkdn5di").authorities("admin").build();
        return new InMemoryUserDetailsManager(user,user1, admin);
    }
 */

    // enabling jdbc user details manager
//    @Bean
//    public UserDetailsService retrieveUserDetails(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }
    // default password encoder is BcryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
//   @Bean
//   public SecurityFilterChain disableCSRF(HttpSecurity security) throws Exception {
//        security.csrf((csrfConfigurer)->csrfConfigurer.disable());
//        return security.build();
//   }
}
