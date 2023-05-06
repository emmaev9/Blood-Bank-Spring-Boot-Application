package com.bloodbank.bloodbankapp.Config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("twilio")
@Getter
@Setter
@NoArgsConstructor
public class TwilioConfiguration {

    private String accountSid;
    private String authToken;
    private String trialNumber;
}
