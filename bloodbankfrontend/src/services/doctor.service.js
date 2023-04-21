import axios from 'axios';
//import authHeader from './auth-header';

const API_URL_DOCTOR = 'http://localhost:8080/api/doctor/';
//const API_URL_ADMIN =  'http://localhost:8080/api/admin/';

class DoctorService {

    getDoctorData(id){
      return axios.get(API_URL_DOCTOR + 'getDoctorById', {
        params: {
         id: id
        }
       });
    }
}
export default new DoctorService(); 