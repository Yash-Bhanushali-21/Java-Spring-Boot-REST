package com.service.payroll.oauth;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.common.net.HttpHeaders;


@Controller
public class LoginController {
	
	private static String authorizationRequestBaseUri
    = "oauth2/authorization";
  Map<String, String> oauth2AuthenticationUrls
    = new HashMap<>();

  @Autowired
  private ClientRegistrationRepository clientRegistrationRepository;
  
  @Autowired
  private OAuth2AuthorizedClientService authorizedClientService;
  
  
  @GetMapping("/getClients")
  @ResponseBody public Map<String, String> getClients() {
	  Iterable<ClientRegistration> clientRegistrations = null;
	    ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
	      .as(Iterable.class);
	    if (type != ResolvableType.NONE && 
	      ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
	        clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
	    }

	    clientRegistrations.forEach(registration -> 
	      oauth2AuthenticationUrls.put(registration.getClientName(), 
	      authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
	    
	    return oauth2AuthenticationUrls;
	  
  }
  
  @GetMapping("/user")
  public OAuth2User user(@AuthenticationPrincipal OAuth2User principal) {
	  //returns the logged in user!~
      return principal;
  }
  
  @RequestMapping("/success")
  public String successPage(Model model ,@AuthenticationPrincipal OAuth2User principal  ) {
	  model.addAttribute("user" , principal.getAttribute("name"));
	 // System.out.println(principal.getAttribute("name"));
	  Map<String,Object> mp = Collections.singletonMap("name", principal.getAttribute("name"));
	  
      Iterator<Map.Entry<String, Object>> itr = mp.entrySet().iterator();
        
      while(itr.hasNext())
      {
           Map.Entry<String, Object> entry = itr.next();
           System.out.println("Key = " + entry.getKey() + 
                               ", Value = " + entry.getValue());
      }
	  
	  return "success";
  }
 
  
  
  @RequestMapping("/oauth_login")
  public String getLoginPage(Model model) {
	    Map<String , String> authenticationUrls = getClients();
	    model.addAttribute("urls", authenticationUrls);
	    
        Iterator<Map.Entry<String, String>> itr = authenticationUrls.entrySet().iterator();
          
        while(itr.hasNext())
        {
             Map.Entry<String, String> entry = itr.next();
             System.out.println("Key = " + entry.getKey() + 
                                 ", Value = " + entry.getValue());
        }

	    return "oauth_login";
	}
  
  

  

}
