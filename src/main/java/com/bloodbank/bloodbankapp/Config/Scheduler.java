package com.bloodbank.bloodbankapp.Config;

import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Reminder.Reminder;
import com.bloodbank.bloodbankapp.Reminder.ReminderFactory;
import com.bloodbank.bloodbankapp.Service.AppoitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {
    @Autowired
    private ReminderFactory reminderFactory;

    @Autowired
    private AppoitmentService appoitmentService;

    @Scheduled(fixedDelay = 40000)
    public void jobScheduler(){
       List<Appoitment> listOfAppointments = appoitmentService.findNextDayAppointments();
       if(!listOfAppointments.isEmpty()){
           for(Appoitment ap: listOfAppointments) {
               System.out.println(ap.getReminderType());
               reminderFactory.sendReminder(ap).sendReminder();
           }
       }

    }

}
