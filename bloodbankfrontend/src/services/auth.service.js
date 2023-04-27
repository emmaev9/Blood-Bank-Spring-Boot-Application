import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';
const API_URL_DOCTOR = 'http://localhost:8080/api/doctor/';



class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'login', {
        username: user.username,
        password: user.password
      })
      .then(response => {

        console.log(response.data)
        if (response.data.token) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  registerDonor(user){
    console.log(user);
    return axios.post(API_URL + 'registerDonor', {
      username: user.username,
      email: user.email,
      password: user.password,
      firstName: user.firstName,
      lastName: user.lastName,
      county: user.county,
      bloodType: user.bloodType
    });
  }
  registerDoctor(doctor){
    console.log(doctor);
    return axios.post(API_URL_DOCTOR + 'registerDoctor', {
      username: doctor.username,
      email: doctor.email,
      password: doctor.password,
      firstName: doctor.firstName,
      lastName: doctor.lastName,
      location: doctor.location,
      cnp: doctor.cnp,
    });
}
}
export default new AuthService();