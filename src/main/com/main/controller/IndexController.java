package com.main.controller;

import com.main.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@RestController
public class IndexController {
    /*@Autowired
    private IndexService indexService;
*/

    @Autowired
    CommonService commonService;
    @Autowired
    ProfileController profileController;
    @Value("${spring.datasource.type}")
    private String type;


    @RequestMapping("/index1")
    public String index() {
        commonService.testService();
        return "应用THIS IS A TEST.WELCOME" + type;
    }

    @RequestMapping("/getSecurityUserDetail")
    public void getSecurityUserDetail(HttpServletRequest request, Principal principal, Authentication authentication) {
        //方式1：使用request
        Principal requestUserPrincipal = request.getUserPrincipal();
        String requestUsername = requestUserPrincipal.getName();
        //方式2：使用Principal对象
        String principalUsername = principal.getName();
        //方式3：使用Authentication对象
        String authenticationUsername = authentication.getName();

    }

    //打印url
    @RequestMapping("/getAllUrl")
    public String getAllUrl(HttpServletRequest request) {
        Set<String> result = new HashSet<String>();
        WebApplicationContext wc = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            PatternsRequestCondition pc = rmi.getPatternsCondition();
            Set<String> pSet = pc.getPatterns();
            result.addAll(pSet);
        }
        return result.toString();
    }


}