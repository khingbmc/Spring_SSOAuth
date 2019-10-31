package com.hellokoding.sso.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class WebApplication {
    @Value("${services.auth}")
    private String authService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    @Async
    public FilterRegistrationBean jwtFilter() throws InterruptedException {
        List<ServiceInstance> list = this.discoveryClient.getInstances("AUTH-SSO-SERVICE");
        Thread.sleep(1000L);
        System.out.println(list);
        this.authService = list.get(0).getUri().toString() + "/login";
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
        registrationBean.addUrlPatterns("/protected-resource");

        return registrationBean;
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }
}

