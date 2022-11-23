package com.john.coupons.security;

import com.john.coupons.dto.UserLoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetails myUserDetails;
    private static UserLoginDetails userLoginDetails;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetails).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception { // ! permit specific paths

        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/coupons").hasAnyRole("Admin", "Company")
                .antMatchers(HttpMethod.POST, "/purchases").permitAll()
                .antMatchers(HttpMethod.POST, "/companies").hasRole("Admin")
                .antMatchers(HttpMethod.POST, "/customers").permitAll()
                .antMatchers(HttpMethod.PUT, "/users").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/customers").permitAll()
                .antMatchers(HttpMethod.PUT, "/companies").hasAnyRole("Admin", "Company")
                .antMatchers(HttpMethod.PUT, "/coupons").hasAnyRole("Admin", "Company")
                .antMatchers(HttpMethod.PUT, "/purchases").hasRole("Admin")
                .antMatchers(HttpMethod.GET, "/coupons").permitAll()
                .antMatchers(HttpMethod.GET, "/coupons/**").permitAll()
                .antMatchers(HttpMethod.GET, "/users").hasRole("Admin")
                .antMatchers(HttpMethod.GET, "/users/id").permitAll()
                .antMatchers(HttpMethod.GET, "/purchases/pagesOfPurchaseDetails/ByCustomerId/{id}/{pageNumber}/{pageSize}").permitAll()
                .antMatchers(HttpMethod.GET, "/purchases/ByCustomerId/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/purchases//ByCouponId/{id}").hasAnyRole("Admin", "Company")
                .antMatchers(HttpMethod.GET, "/purchases/").hasRole("Admin")
                .antMatchers(HttpMethod.GET, "/customers").hasAnyRole("Admin", "Company")
                .antMatchers(HttpMethod.GET, "/customers/id").permitAll()
                .antMatchers(HttpMethod.GET, "/companies").hasRole("Admin")
                .antMatchers(HttpMethod.GET, "/companies/**").hasAnyRole("Admin", "Company")
                .antMatchers(HttpMethod.GET, "/companies/{id}").hasAnyRole("Admin", "Company")
                .antMatchers(HttpMethod.DELETE, "/users").hasRole("Admin")
                .antMatchers(HttpMethod.DELETE, "/purchases").hasRole("Admin")
                .antMatchers(HttpMethod.DELETE, "/coupons").hasAnyRole("Admin", "Company")
                .antMatchers(HttpMethod.DELETE, "/companies").hasAnyRole("Admin")
                .antMatchers(HttpMethod.DELETE, "/customers").hasAnyRole("Admin");

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfiguration() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }
}
