import axios from "axios";

const API_URL_DOCTOR = "http://localhost:8080/api/doctor/";
import authHeader from './auth-header';
class AdminService {

  deleteDoctor(id){
    return axios.delete(`${API_URL_DOCTOR}deleteDoctor/${id}`);
  }

  getDoctor(id){
    return axios.get(`${API_URL_DOCTOR}getDoctor/${id}`);
  }

  updateDoctor(doctor){
    console.log("Sunt in updateDoctor");
    return axios.post(API_URL_DOCTOR + "editDoctorProfile", doctor);
  }

  allDoctors(){
    return axios.get(API_URL_DOCTOR + 'getAllDoctors',{ headers: authHeader() });
  }
}

export default new AdminService();
