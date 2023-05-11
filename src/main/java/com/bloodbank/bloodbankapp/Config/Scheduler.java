package com.bloodbank.bloodbankapp.Config;

import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Service.Implementation.ReminderFactory;
import com.bloodbank.bloodbankapp.Service.Implementation.AppoitmentService;
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

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 12)
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
