<template>
  <br>
  <div class="container text-center">
    <div v-if="message" class="alert alert-success">
      {{message}}
    </div>
    <Card style="width: 25em">
      <template #title> Make an appointment </template>
      <template #content>
        <form class="mx-1 mx-md-4" @submit="makeAppointment">
          <div>
            <div class="d-flex flex-row align-items-center mb-4">
              <i class="fas fa-user fa-lg me-3 fa-fw"></i>
              <div class="form-outline flex-fill mb-0">
                  
                  <input v-model="date" 
                         class="datepicker-input" 
                         type="date" 
                         name="date" 
                         id="appointment_date"
                         :min="new Date()"
                         > 
                 
               
              </div>
            </div>
            <div class="d-flex flex-row align-items-center mb-4">
              <i class="fas fa-user fa-lg me-3 fa-fw"></i>
              <div class="form-outline flex-fill mb-0">
                <Dropdown v-model="selectedLocation" :options="locations" optionLabel="name"
                  placeholder="Select a donation center">
                </Dropdown>
              </div>
            </div>

            <div>
              <button class="button">
                Submit
              </button>
            </div>
          </div>
        </form>
        </template>
    </Card>
  </div>
</template>

<script>
import UserService from "../services/user.service";
import AppointmentService from "../services/appointment";
//import Calendar from 'primevue/calendar';
import Dropdown from 'primevue/dropdown';
import Card from 'primevue/card';
//import Button from 'primevue/button';
import "primevue/resources/primevue.min.css"; //core
import "primeicons/primeicons.css"; //icons
//import { Form } from "vee-validate";
import "primevue/resources/themes/mira/theme.css";


export default {
  name: "AppointmentCard",  
  components: {
   // Form,
    Dropdown,
    Card,
   // Button
  },
  data() {
    return {
      successful: false,
      loading: false,
      locations: [],
      selectedLocation: "",
      date: "",
      message: "",
      updateListOfAppointments:"",
      today: new Date().toISOString().split("T")[0],
    };
  },
  computed: {
    currentUser() {
      //return JSON.parse(localStorage.getItem("user"))
      return this.$store.state.auth.user;
    }
  },
  methods: {

    makeAppointment(appointment) {
      console.log(this.today + " " + new Date());
      this.message = "";
      this.successful = false;
      this.loading = true;
      this.updateListOfAppointments = "true";
    
      this.$emit('myevent',this.updateListOfAppointments);

      this.updateListOfAppointments = "false";

      appointment.location = this.selectedLocation;
      appointment.date = this.date;
      appointment.username = this.currentUser.username;
    
      AppointmentService.createAppointment(appointment)
        .then(
          () => {
            this.message = "Appointment successfully created!"
            this.successful = false;
            this.loading = false;
          },
          (error) => {
            this.message =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
            this.successful = true;
            this.loading = false;
          }
        );
    },
  },

  created() {
    UserService.getDonorDonationCenters(this.currentUser.county).then(
      (response) => {
        this.locations = response.data;
      },
      (error) => {
        this.content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  },
}

</script>

<style>
.button {
  position: relative;
  background-color: #21aeaa;
  border: none;
  font-size: 15px;
  color: #FFFFFF;
  padding: 20px;
  width: 100px;
  text-align: center;
  transition-duration: 0.4s;
  text-decoration: none;
  overflow: hidden;
  cursor: pointer;
}

.button:after {
  content: "";
  background: #f1f1f1;
  display: block;
  position: absolute;
  padding-top: 300%;
  padding-left: 350%;
  margin-left: -20px !important;
  margin-top: -120%;
  opacity: 0;
  transition: all 0.8s
}

.button:active:after {
  padding: 0;
  margin: 0;
  opacity: 2;
  transition: 0s
}
</style>