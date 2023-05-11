package com.bloodbank.bloodbankapp.Service.Implementation;

import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Service.Reminder;
import org.springframework.stereotype.Component;

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
