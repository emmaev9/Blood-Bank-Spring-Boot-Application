import axios from 'axios';

const API_URL = 'http://localhost:8080/api/appointment/';

class AppointmentService {
    createAppointment(appointment, locations) {
      return axios.post(API_URL + 'createAppointment', appointment, locations);
    }

    getCurrentAppointments(username){
      return axios.get(API_URL + 'currentAppointments', {
        params: {
         username: username
        }
       });
    }
    getDoctorAppointments(username){
      return axios.get(API_URL + 'allDoctorAppointments', {
        params: {
         username: username
        }
       });
    }

    deleteAppointment(id){
      return axios.delete(`${API_URL}deleteAppointment/${id}`);
    }
    confirmAppointment(id){
      return axios.put(`${API_URL}confirmAppointment/${id}`);
    }
}
export default new AppointmentService(); 