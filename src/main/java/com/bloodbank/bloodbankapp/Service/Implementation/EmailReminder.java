package com.bloodbank.bloodbankapp.Service.Implementation;

import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Service.Reminder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class EmailReminder implements Reminder {

    private Appoitment appoitment;

    public EmailReminder(Appoitment appoitment){
        this.appoitment = appoitment;
    }

    @Override
    public void sendReminder() {

        Donor donor = appoitment.getDonor();
        DonationCenter donationCenter = appoitment.getDonationCenter();
        String body = "Hello  "+ donor.getFirstName()+",\n\n" +
                        "This is a reminder that you have an appointment tomorrow at  "+ donationCenter.getName();
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(donor.getEmail());
        message.setSubject("Appointment reminder");
        message.setText(body);
        getJavaMailSender().send(message);
    }
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("szakacsemma9@gmail.com");
        mailSender.setPassword("sgwazpxfdvkcoaio");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

}
