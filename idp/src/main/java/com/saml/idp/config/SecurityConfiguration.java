package com.saml.idp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.saml.provider.identity.config.SamlIdentityProviderSecurityConfiguration;

import static org.springframework.security.saml.provider.identity.config.SamlIdentityProviderSecurityDsl.identityProvider;

@EnableWebSecurity
public class SecurityConfiguration {

    @Configuration
    @Order(1)
    public static class SamlSecurity extends SamlIdentityProviderSecurityConfiguration {

        private final AppConfig appConfig;
        private final BeanConfig beanConfig;

        public SamlSecurity(BeanConfig beanConfig, @Qualifier("appConfig") AppConfig appConfig) {
            super("/saml/idp/", beanConfig);
            this.appConfig = appConfig;
            this.beanConfig = beanConfig;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            super.configure(http);
            http.formLogin().loginPage("/login");
//            http
//                    .userDetailsService(beanConfig.userDetailsService()).formLogin();
            http.apply(identityProvider())
                    .configure(appConfig);
        }
    }


    public class AppSecurity extends WebSecurityConfigurerAdapter {
        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/resources/**", "/registration").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
        }

        @Bean
        public AuthenticationManager customAuthenticationManager() throws Exception {
            return authenticationManager();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        }
    }
//    @Configuration
//    public static class AppSecurity extends WebSecurityConfigurerAdapter {
//
//        private final BeanConfig beanConfig;
//
//        public AppSecurity(BeanConfig beanConfig) {
//            this.beanConfig = beanConfig;
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .antMatcher("/**")
//                    .authorizeRequests()
//                    .antMatchers("/**").authenticated()
//                    .and()
//                    .userDetailsService(beanConfig.userDetailsService()).formLogin()
//            ;
//        }
//    }
}