<template>
  <br>
  <br>
  <div v-if="message" class="alert alert-success">
    {{message}}
  </div>

  <div>
    <h2 class="title">List of appointments</h2>
  </div>
  <div class="container text-center">

    <div>
      <table class="table table-striped table-bordered">
        <thead class="thead-dark">
          <tr>
            <th>Donation Center</th>
            <th>Address</th>
            <th>Date</th>
            <th>Confirmed</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in appointments" :key="item.id">
            <td> {{ item.donationCenter }}</td>
            <td> {{ item.address }}</td>
            <td> {{ item.date }}</td>
            <td> {{ item.confirmed }}</td>
            <td>
              <button @click="deleteSelectedAppointment(item.id)" class="button-33" role="button">
                  Cancel
                
              </button>
          </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  <br>
  <br>
</template>

<script>
import AppointmentService from "../services/appointment";

export default {
  name: "CurrentAppointments",

  props: {
    updateTable: {
      type: String,
      default: "true"
    }
  },

  data() {
    return {
      successful: false,
      loading: false,
      appointments: "",
      message: "",
    };
  },
  computed: {
    currentUser() {
      return JSON.parse(localStorage.getItem("user"));
    }
  },
  methods: {
    deleteSelectedAppointment(id){
      AppointmentService.deleteAppointment(id).then(
        () => {
          this.message = "Appointment canceled!";
          this.refreshTable();
        }
      )
    },
    refreshTable(){
      AppointmentService.getCurrentAppointments(this.currentUser.username).then(
          (response) => {
            this.appointments = response.data;
          },
          (error) => {
            this.content =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
          }
    )}
  },


  watch: {
    updateTable: function () {
      if (this.updateTable == "true") {
        this.refreshTable();
      }
    }
  },
  created() {
    this.refreshTable();
  }
}

</script>
<style>
@import url(../assets/styles/cancel_button.css);
.title {
  font-family: 'Courier New', Courier, monospace;
  font-weight: bold;
  font-stretch: condensed;
}
</style>