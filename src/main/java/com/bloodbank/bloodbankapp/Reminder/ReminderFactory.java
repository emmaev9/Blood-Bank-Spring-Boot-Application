package com.bloodbank.bloodbankapp.Reminder;

import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Service.EmailService;
import com.bloodbank.bloodbankapp.Service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ReminderFactory {

    public Reminder sendReminder(Appoitment appoitment){
        if(appoitment.getReminderType().equals("Reminder by email")){
            return new EmailReminder(appoitment);
        }
        if(appoitment.getReminderType().equals("Reminder by SMS")){
            return new SmsReminder(appoitment);
        }
        return null;
    }
}
