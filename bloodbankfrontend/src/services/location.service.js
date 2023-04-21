import axios from 'axios';

const API_URL_LOCATION = 'http://localhost:8080/api/location/';


class DonationCenterService {
 
  getDonationCenters(){
    return axios.get(API_URL_LOCATION + 'getAllDonationCenters'); 
  }
}

export default new DonationCenterService();