package com.bloodbank.bloodbankapp.Service.Implementation;

import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Service.Reminder;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

public class SmsReminder implements Reminder {

    private Appoitment appoitment;
    public SmsReminder(Appoitment appoitment){
        this.appoitment = appoitment;
    }
    @Override
    public void sendReminder() {
        Donor donor = appoitment.getDonor();
        DonationCenter donationCenter = appoitment.getDonationCenter();
        String body = "Hello  "+ donor.getFirstName()+",\n\n" +
                "This is a reminder that you have an appointment tomorrow at  "+ donationCenter.getName();

        MessageCreator creator = Message.creator(
                new PhoneNumber("+40" + appoitment.getDonor().getPhoneNumber()),
                new PhoneNumber("+13184963990"),
                body
        );
        creator.create();
    }

}
