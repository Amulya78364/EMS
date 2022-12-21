package com.expenseManagementSystem.securityconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@org.springframework.context.annotation.Configuration
public class Configuration extends WebSecurityConfigurerAdapter {
    @Value("${spring.security.oauth2.client.provider.okta.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH","OPTIONS");
            }
        };
    }

    @Bean
    public FilterRegistrationBean<Filter> simpleCorsFilter ()

    {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        List<String> allowedOrigins=new ArrayList<>();
        List<String> allowedMethods=new ArrayList<>();
        List<String> allowedHeaders=new ArrayList<>();


        //allowedOrigins.add("*");
        allowedOrigins.add("http://54.214.113.249");
        config.setAllowedOrigins(allowedOrigins);
        allowedMethods.add("*");
        allowedHeaders.add("*");
        config.setAllowedMethods(allowedMethods);
        config.setAllowedHeaders(allowedHeaders);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean((Filter) new CorsFilter(source));
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;

    }
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).jwsAlgorithm(SignatureAlgorithm.RS256).build();
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.cors().configurationSource(request -> new CorsConfiguration(new CorsConfiguration()));
// http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
// .and().
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated();
        http.oauth2ResourceServer().jwt().decoder(jwtDecoder());
    }
}





