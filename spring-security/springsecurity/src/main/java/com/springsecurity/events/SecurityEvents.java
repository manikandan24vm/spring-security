package com.springsecurity.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecurityEvents {
    @EventListener
    public void successfulAuthentication(AuthenticationSuccessEvent success){
        log.info("Login successful for the user : {}",success.getAuthentication().getName());
    }

    @EventListener
    public void failureAuthentication(AbstractAuthenticationFailureEvent failure){
        log.info("Login failed for the user : {} due to {} ",failure.getAuthentication().getName(),failure.getException().getMessage());
    }

}
