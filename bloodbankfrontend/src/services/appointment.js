import axios from 'axios';

const API_URL = 'http://localhost:8080/api/appointment/';
const API_URL_DOCTOR= 'http://localhost:8080/api/doctor/';

class AppointmentService {
    createAppointment(appointment) {
      return axios.post(API_URL + 'createAppointment', appointment);
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

    getCurrentDayAppointments(username,date){
      return axios.get(`${API_URL}getCurrentDayAppointments/${username}/${date}`);
    }

    getNonAvailableAppointmentDates(id){
      return axios.get(`${API_URL}getNonAvailableDays/${id}`);
    }

    deleteAppointment(id){
      return axios.delete(`${API_URL}deleteAppointment/${id}`);
    }
    confirmAppointment(id){
      return axios.put(`${API_URL}confirmAppointment/${id}`);
    }
    pagination(pageNo, id){
      return axios.get(`${API_URL_DOCTOR}page/${pageNo}/${id}`);
    }
    sendBloodTestResults(id,results){
      console.log(results);
      return axios.post(API_URL_DOCTOR + 'sendBloodTestResults', {
        appointmentId: id,
        result: results,
      });
    }
    getResult(id){
      return axios.get(`${API_URL}getBloodResults/${id}`);
    }

}
export default new AppointmentService(); 