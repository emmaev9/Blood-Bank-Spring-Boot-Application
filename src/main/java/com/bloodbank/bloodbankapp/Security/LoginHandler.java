package com.bloodbank.bloodbankapp.Security;

import com.bloodbank.bloodbankapp.Security.UserDetailsServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


@Component
public class LoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        String redirectURL = request.getContextPath();

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            redirectURL =  "/AdminHome";
        } else if (roles.contains("ROLE_USER")) {
            redirectURL = "/DonorHome";
        } else if (roles.contains("ROLE_DOCTOR")) {
            redirectURL = "/DoctorHome";
        }

        response.sendRedirect(redirectURL);

    }
}
