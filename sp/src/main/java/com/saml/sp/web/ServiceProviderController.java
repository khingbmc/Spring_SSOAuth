package com.saml.sp.web;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.provider.provisioning.SamlProviderProvisioning;
import org.springframework.security.saml.provider.service.ServiceProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@Controller
public class ServiceProviderController {

    private static final Log logger =LogFactory.getLog(ServiceProviderController.class);
    private SamlProviderProvisioning<ServiceProviderService> provisioning;

    @Autowired
    public void setSamlService(SamlProviderProvisioning<ServiceProviderService> provisioning) {
        this.provisioning = provisioning;
    }

    @RequestMapping(value = {"/", "/index", "/logged-in"})
    public String home(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        model.addAttribute("username", authentication.getName());
        logger.info("Sample SP Application - You are logged in!");
        return "logged-in";

    }

    @RequestMapping(value = {"/test"})
    public String test() {
        return "test";
    }

    @RequestMapping(value = {"/data"})
    public String data(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
//        System.out.println(principal.getName());
        return "data";
    }


}
