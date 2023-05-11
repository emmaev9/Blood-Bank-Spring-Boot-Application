package com.bloodbank.bloodbankapp.Facade;

import com.bloodbank.bloodbankapp.DTO.Request.DoctorDTO;
import com.bloodbank.bloodbankapp.DTO.Request.PaginationDTO;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.User;
import com.bloodbank.bloodbankapp.Mapper.DoctorMapper;
import com.bloodbank.bloodbankapp.Service.IAppointmentService;
import com.bloodbank.bloodbankapp.Service.IDoctorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorFacade {
    private final IDoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final IAppointmentService appointmentService;

    public DoctorDTO getDoctorById(Integer id){
        Doctor doctor = doctorService.findDoctorById(id);
        return doctorMapper.doctorModelToDTO(doctor);
    }
    public boolean existsDoctorById(Integer id){
        return doctorService.existDoctorById(id);
    }
    public List<DoctorDTO> getListOfDoctors(){
        List<Doctor> doctorList = doctorService.findAllDoctors();
        List<DoctorDTO> doctorDTOList = new ArrayList<>();
        for(Doctor doc : doctorList){
            DoctorDTO doctorDTO = doctorMapper.doctorModelToDTO(doc);
            doctorDTOList.add(doctorDTO);
        }
        return doctorDTOList;
    }
    public List<Appoitment> getDoctorAppointments(String username){
        Doctor doctor = doctorService.findDoctorByUsername(username);
        return appointmentService.findAllDoctorAppointments(doctor);
    }
    public PaginationDTO getPagination(int pageNo){
        int pageSize = 5;
        Page<Appoitment> page = appointmentService.getAllAppointments(pageNo, pageSize);
        List<Appoitment> listAppointment = page.getContent();
        return new PaginationDTO(pageNo, page.getTotalPages(), page.getTotalElements(), listAppointment);
    }

    public void updateDoctor(DoctorDTO doctorDTO){
        doctorService.updateDoctor(doctorDTO);
    }

    public void saveDoctor(DoctorDTO registeredDoctor){
        Doctor newDoctor = doctorMapper.doctorDTOtoModel(registeredDoctor);
        doctorService.saveDoctor(newDoctor);
    }
    public User findUserById(Integer id){
        return doctorService.findDoctorById(id);
    }

    public void deleteDoctorById(Integer id){
        List<Appoitment> appoitmentList = appointmentService.findAllDoctorAppointments(doctorService.findDoctorById(id));
        for(Appoitment appoitment: appoitmentList){
            appointmentService.deleteAppointmentById(appoitment.getId());
        }
        doctorService.deleteDoctorById(id);
    }




}
