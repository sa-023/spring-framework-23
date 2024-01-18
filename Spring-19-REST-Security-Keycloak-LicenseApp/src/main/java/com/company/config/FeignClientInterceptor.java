package com.company.config;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
/*
 * 🌀 RequestInterceptor
 * · Feign provides a RequestInterceptor interface. With this, we can add request headers.
 * · It makes sense to add a request interceptor when it’s known that the header should be included in every call.
 *   This pattern removes the dependency of the invoking code to implement non-functional requirements like authentication or tracing.
 * 🖍️...
 * · Whenever feinClient(OrganizationFeignClient) is called, this Interceptor class stops the execution and runs the FeignClientInterceptor apply() method first.
 *
 */
@Component
public class FeignClientInterceptor implements RequestInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer ";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SimpleKeycloakAccount details = (SimpleKeycloakAccount) authentication.getDetails();
        requestTemplate.header(AUTHORIZATION_HEADER, TOKEN_TYPE + details.getKeycloakSecurityContext().getTokenString());
    }

}