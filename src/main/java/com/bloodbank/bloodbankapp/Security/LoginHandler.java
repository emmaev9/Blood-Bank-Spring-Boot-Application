package com.bloodbank.bloodbankapp.Security;

import com.bloodbank.bloodbankapp.Entity.ERole;
import com.bloodbank.bloodbankapp.Repository.RoleRepository;
import com.bloodbank.bloodbankapp.Security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        String redirectURL = request.getContextPath();


        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ADMIN")) {
            redirectURL =  "/AdminHome";
        } else if (roles.contains("DONOR")) {
            redirectURL = "/DonorHome";
        } else if (roles.contains("DOCTOR")) {
            redirectURL = "/DoctorHome";
        }

        response.sendRedirect(redirectURL);

    }
}
