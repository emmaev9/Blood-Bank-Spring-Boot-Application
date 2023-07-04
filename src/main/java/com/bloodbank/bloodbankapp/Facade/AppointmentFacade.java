package com.bloodbank.bloodbankapp.Facade;

import com.bloodbank.bloodbankapp.DTO.Request.AppointmentDTO;
import com.bloodbank.bloodbankapp.DTO.Request.AppointmentRequestDTO;
import com.bloodbank.bloodbankapp.DTO.Request.DoctorAppointmentsDTO;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Mapper.AppointmentMapper;
import com.bloodbank.bloodbankapp.Service.IAppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentFacade {

    private final IAppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;

    public void deleteAppointment(Integer id){
        appointmentService.deleteAppointmentById(id);
    }

    public boolean existsAppointment(Integer id){
        return appointmentService.existsAppointmentById(id);
    }
    public void saveAppointment(AppointmentRequestDTO appointmentRequestDTO) throws ParseException {
        appointmentService.saveAppoitment(appointmentRequestDTO);
    }

    public void confirmAppointment(Integer id){
        Appoitment appoitment = appointmentService.findById(id);
        appoitment.setConfirmed(true);
        appointmentService.save(appoitment);
    }
    public List<AppointmentDTO> getDonorCurrentAppointments(String username){
        List<Appoitment> allAppointments = appointmentService.findAppointmentsByDonorUsername(username);
        List<AppointmentDTO> requestedAppointments = new ArrayList<>();
        for(Appoitment appoitment: allAppointments){
            requestedAppointments.add(appointmentMapper.entityToDto(appoitment));
        }
        return requestedAppointments;
    }

    public List<DoctorAppointmentsDTO> getDoctorCurrentDayAppointments(String username, Date date){
        List<Appoitment> allAppointments = appointmentService.findAppointmentsByDoctorUsername(username);
        List<Appoitment> currentAppointments = new ArrayList<>();

        for(Appoitment appoitment: allAppointments){
            if(appoitment.getDate().compareTo(appointmentService.setTimeToMidnight(date))==0){
                currentAppointments.add(appoitment);
            }
        }
        List<DoctorAppointmentsDTO> doctorAppointmentsDTOList = new ArrayList<>();
        for(Appoitment appoitment: currentAppointments){
            doctorAppointmentsDTOList.add( appointmentMapper.appointmentsModelToDoctorAppointmentsDTO(appoitment, appoitment.getDoctor()));
        }
        return doctorAppointmentsDTOList;
    }

    public List<Appoitment> findAllDoctorAppointments(String username){
        return appointmentService.findAppointmentsByDoctorUsername(username);
    }

    public List<Date> getUnavailableDatesForLocation(Integer id){
        return appointmentService.nonAvailableDatesForAppointments(id);
    }
    public String getBloodResult(Integer id){
        return appointmentService.getBloodResult(id);
    }
}

