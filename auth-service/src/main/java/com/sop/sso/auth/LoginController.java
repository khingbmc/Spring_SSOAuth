package com.sop.sso.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";
    private static final Map<String, String> credentials = new HashMap<>();

    @Autowired
    private DiscoveryClient discoveryClient;


    public LoginController() {
        credentials.put("admin", "admin");
    }

    @RequestMapping("/")
    public String home(){
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(RedirectAttributes redirectAttributes, HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model) throws IOException {
//        InetAddress address = InetAddress.getByName(new URL(redirect).getHost());
//        String ip = address.getHostAddress();
//        System.out.println(address);
//        System.out.println(ip);
//        System.out.println(redirect);
        if (username == null || !credentials.containsKey(username) || !credentials.get(username).equals(password)){
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }
        List<ServiceInstance> list = this.discoveryClient.getInstances("AUTH-SSO-SERVICE");
        String token = JwtUtil.generateToken(signingKey, username);

        redirectAttributes.addAttribute("token", token);
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "10.110.193.45");
        return "redirect:" + redirect;
    }
}
