package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.DTO.Request.AppointmentRequestDTO;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import org.springframework.data.domain.Page;
import java.text.ParseException;
import java.util.*;

public interface IAppointmentService {
    void saveAppoitment(AppointmentRequestDTO appointmentDTO) throws ParseException;
    void save(Appoitment appoitment);
    boolean existsAppointmentById(Integer id);
    void deleteAppointmentById(Integer id);
    List<Appoitment> findAllDoctorAppointments(Doctor doctor);
    Appoitment findById(Integer id);
    List<Date> nonAvailableDatesForAppointments(Integer id);
    Page<Appoitment> getAllAppointments(int pageNo, int pageSize, Integer doctorId);
    List<Appoitment> findNextDayAppointments();
    List<Appoitment> findAppointmentsByDonorUsername(String username);
    List<Appoitment> findAppointmentsByDoctorUsername(String username);
    Date setTimeToMidnight(Date date);
    void updateAppointmentResult(String result, Integer id);
    String getBloodResult(Integer id);
}
