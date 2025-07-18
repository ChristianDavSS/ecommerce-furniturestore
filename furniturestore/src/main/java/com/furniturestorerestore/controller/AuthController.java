package com.furniturestorerestore.controller;

import com.furniturestorerestore.dto.request.LoginRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest data, HttpServletRequest request){
        /*
        * POST Method: User´s login
        * */

        // We call the method authenticate from the manager.
        Authentication authentication = authenticationManager.authenticate(
                // This is the auth we use by default with the config.
                new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword())
        );

        // We define the auth data on the SecurityContext.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // We get the session to invalidate old ones (in case they exist).
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // We create the new session (it creates the JSESSIONID automatically and put it in Cookies)
        session = request.getSession(true);

        // We save the SecurityContextHolder into the session make it to persist and know we are authenticated.
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) throws ServletException {
        if (request.getUserPrincipal() == null){
            throw new ServletException("You are not logged in");
        }
        // We invalidate the session without creating another one
        request.getSession(false).invalidate();
        // We use the logout method to logout.
        request.logout();
    }
}
