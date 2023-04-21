//import authHeader from './auth-header';
import axios from "axios";


const API_URL_DONOR = "http://localhost:8080/api/donor/";

class DonorService {
  makeAppointment(appointment, username) {
    return axios.get(API_URL_DONOR + "createAppointment", username, appointment)
  
  }

  editProfile(user) {
    console.log(user);
    return axios.post(API_URL_DONOR + "editDonorProfile", user )
  }
}

export default new DonorService();
