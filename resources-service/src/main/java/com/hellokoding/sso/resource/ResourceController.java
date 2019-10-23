package com.hellokoding.sso.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ResourceController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public String home() {
        List<ServiceInstance> list = this.discoveryClient.getInstances("AUTH-SSO-SERVICE");
        System.out.println(list.get(0).getHost());
        System.out.println(list.get(0).getUri());
        System.out.println(list.get(0).getPort());
        return "redirect:/protected-resource";
    }

    @RequestMapping("/protected-resource")
    public String protectedResource() {
        return "protected-resource";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse httpServletResponse) {
        List<ServiceInstance> list = this.discoveryClient.getInstances("AUTH-SSO-SERVICE");
        CookieUtil.clear(httpServletResponse, jwtTokenCookieName, list.get(0).getHost());
        return "redirect:/";
    }
}
