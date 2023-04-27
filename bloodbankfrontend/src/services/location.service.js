import axios from 'axios';

const API_URL_LOCATION = 'http://localhost:8080/api/location/';


class DonationCenterService {
 
  getDonationCenters(){
    return axios.get(API_URL_LOCATION + 'getAllDonationCenters'); 
  }
  getDonationCentersWithLabels(){
    return axios.get(API_URL_LOCATION + 'getDonationCentersWithLabel');
  }
}

export default new DonationCenterService();