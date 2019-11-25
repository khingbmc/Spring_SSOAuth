package sop.test.Testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.provider.provisioning.SamlProviderProvisioning;
import org.springframework.security.saml.provider.service.ServiceProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    private SamlProviderProvisioning<ServiceProviderService> provisioning;

    @Autowired
    public void setSamlService(SamlProviderProvisioning<ServiceProviderService> provisioning) {
        this.provisioning = provisioning;
    }


    @RequestMapping(value = "/")
    public String home(ModelMap model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        model.addAttribute("username", authentication.getName());
        return "index";
    }

}
