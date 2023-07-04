import axios from 'axios';
import authHeader from './auth-header';

const API_URL_ADMIN = 'http://localhost:8080/api/admin/';
const API_URL_DONOR = 'http://localhost:8080/api/donor/';
const API_URL_DOCTOR = 'http://localhost:8080/api/doctor/';
const API_URL_LOCATION = 'http://localhost:8080/api/location/';

class UserService {
  getAdminHome() {
    return axios.get(API_URL_ADMIN + 'adminHome', { headers: authHeader() });
  }

  getDonorHome() {
    return axios.get(API_URL_DONOR + 'donorHome', { headers: authHeader() });
  }
  getDonorDonationCenters(county){
    return axios.get(API_URL_LOCATION + 'donationCenters', {
       headers: authHeader(),
       params: {
        county: county
       }
      });
  }

  getDoctorHome(id) {
    return axios.get(API_URL_DOCTOR + `doctorHome/${id}`);
  }
}

export default new UserService();