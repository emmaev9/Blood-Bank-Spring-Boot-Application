<template>
  <br>
  <div class="container text-center">
    <div v-if="message" class="alert alert-success">
      {{ message }}
    </div>
    <Card style="width: 25em">
      <template #title> Make an appointment </template>
      <template #content>

        <Form class="mx-1 mx-md-4" @submit="makeAppointment">
          <div class="d-flex flex-row align-items-center mb-4">
            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
            <div class="form-outline flex-fill mb-0">
              <Dropdown v-model="this.selectedLocation" :options="locations" optionLabel="name" 
                placeholder="Select a donation center" @change="chooseDonationCenter()">
              </Dropdown>

            </div>
          </div>

          <div>
            <div class="d-flex flex-row align-items-center mb-4">
              <i class="fas fa-user fa-lg me-3 fa-fw"></i>
              <div class="form-outline flex-fill mb-0">

                <Calendar @change="checkDate" v-model="date"
                          dateFormat="yy/MM/dd" 
                          placeholder="Select a date" 
                          :minDate="disabledBefore"
                          :maxDate="disabledAfter"
                          :disabledDates="disabledDates"
                          disabledDays="[0,6]"
                          showIcon="true" />

              </div>
            </div>
          </div>
          
          <div v-for="category in reminderTypes" :key="category.key" class="radio">
            <RadioButton v-model="selectedReminder" :inputId="category.key" name="pizza" :value="category.name" p-radiobutton-icon />
            <label :for="category.key" class="ml-2">{{ category.name }}</label>
          </div>
        



          <div>
            <button class="button-333" role="button">
              Submit
            </button>
          </div>
        </Form>

      </template>
    </Card>
  </div>
</template>

<script>
import UserService from "../services/user.service";
import AppointmentService from "../services/appointment";
import Dropdown from 'primevue/dropdown';
import Card from 'primevue/card';
import Calendar from 'primevue/calendar';
import {Form} from 'vee-validate';
import "primevue/resources/primevue.min.css"; //core
import "primeicons/primeicons.css"; //icons
import "primevue/resources/themes/mira/theme.css";
import RadioButton from 'primevue/radiobutton';
export default {

  name: "AppointmentCard",
  components: {
    Dropdown,
    Card,
    Calendar,
    Form,
    RadioButton
  },

  data() {
    return {

      successful: false,
      loading: false,
      locations: [],
      selectedLocation: "",
      selectedReminder: "",
      date: "",
      message: "",
      updateListOfAppointments: "flase",
      disabledBefore: null,
      disabledAfter: null,
      datesToDisable: [],
      disabledDates: [],
      reminderTypes: [
        {key: 1, name: "Reminder by email"},
        {key: 2, name: "Reminder by SMS"}
      ]
    };

  },
  computed: {
    currentUser() {
      return JSON.parse(localStorage.getItem("user"))
      //return this.$store.state.auth.user;
    }
  },
  methods: {
    checkDate(date){
      if(!this.disabledDates.includes(date)){
        this.date = date;
      }
    },

    populateTable(){
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

    chooseDonationCenter() {
      this.disabledDates = [];
      AppointmentService.getNonAvailableAppointmentDates(this.selectedLocation.id).then(
        (response) => {
      
          for (let i = 0; i < response.data.length; i++) {
               this.disabledDates[i] = new Date(response.data[i]);
          }
          console.log(this.disabledDates);
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

    makeAppointment(appointment) {
      // console.log(this.date);
      this.message = "";
      this.successful = false;
      this.loading = true;
      this.updateListOfAppointments = "true";
      console.log(this.selectedReminder);
      //this.date = new Date(this.date.getUTCFullYear(), this.date.getUTCMonth(), this.date.getUTCDate() + 2);
      let dateToSend = new Date(this.date.getUTCFullYear(), this.date.getUTCMonth(), this.date.getUTCDate() + 2);
    
      this.$emit('myevent', this.updateListOfAppointments);

      this.updateListOfAppointments = "false";

      appointment.location = this.selectedLocation;
      appointment.date = dateToSend;
      appointment.username = this.currentUser.username;
      appointment.reminderType = this.selectedReminder;

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
  mounted(){
    this.populateTable();
  },

  created() {
    this.disabledBefore = new Date();
    let today = new Date();
    this.disabledAfter = new Date(today.getUTCFullYear(), today.getUTCMonth(), today.getUTCDate() + 60);
    
    this.populateTable();
  },
}

</script>

<style>

@import url(../assets/styles/submit_button.css);

.radio {
  /* ...existing styles */
  place-content: center;
  border: 0px;
    width: 100%;
    height: 2em;
}

.radio::before {
  content: "";
  width: 0.65em;
  height: 0.65em;
  border-radius: 50%;
  transform: scale(0);
  transition: 120ms transform ease-in-out;
  box-shadow: inset 1em 1em var(--form-control-color);
}

.radio:checked::before {
  transform: scale(1);
}
.card {
  margin-top: 10%;
}
.form-control:focus {
  border-color: #ed0dfd;
  box-shadow: 0 0 0 0.2rem rgba(253, 13, 173, 0.25);
}
.register-link {
  color: #0D6EFD;
  text-decoration: none;
  margin-left: 5px;
}
.register-link:hover {
  text-decoration: underline;
}


</style>