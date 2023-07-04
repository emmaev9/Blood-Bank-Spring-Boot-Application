//import authHeader from './auth-header';
import axios from "axios";


const API_URL_DONOR = "http://localhost:8080/api/donor/";

class DonorService {
  makeAppointment(appointment, username) {
    return axios.get(API_URL_DONOR + "createAppointment", username, appointment)
  
  }

  editProfile(user) {
    console.log(user);
    user.password = "123456";
    return axios.post(API_URL_DONOR + "editDonorProfile", user )
  }
  deleteAccount(id){
    console.log(id);
    return axios.post(`${API_URL_DONOR}delete/${id}`);
  }

}

export default new DonorService();
