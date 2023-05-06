package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Config.TwilioConfiguration;
import com.bloodbank.bloodbankapp.DTO.Request.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SmsService {

    private final TwilioConfiguration twilioConfiguration;

    public void sendSms(SmsRequest smsRequest){
        MessageCreator creator = Message.creator(
                new PhoneNumber(smsRequest.getPhoneNumber()),
                new PhoneNumber(twilioConfiguration.getTrialNumber()),
                smsRequest.getMessage()
        );
        creator.create();
        System.out.println("Sms " + smsRequest+ "sent!");
    }
}
