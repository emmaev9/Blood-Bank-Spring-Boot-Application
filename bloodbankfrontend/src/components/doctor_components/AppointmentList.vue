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
            <th>Donor</th>
            <th>Date</th>
            <th>Confirmed</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in appointments" :key="item.id">
            <td> {{ item.donationCenter.name }}</td>
            <td> {{ item.donor.firstName + " " + item.donor.lastName }} </td>
            <td> {{ item.date.year }}</td>
            <td> {{ item.confirmed }}</td>
            <td>
                <button v-if="visible && !item.confirmed" @click="confirmAppointment(item.id)" class="btn btn-info"> Confirm </button>
          
              
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
import AppointmentService from "@/services/appointment";

export default {
  name: "AppointmentList",

  data() {
    return {
      successful: false,
      loading: false,
      appointments: "",
      message: "",
      visible: true,
    };
  },
  computed: {
    currentUser() {
      //return JSON.parse(localStorage.getItem("user"));
      return this.$store.state.auth.user;
    }
  },
  methods: {
    confirmAppointment(id){
      AppointmentService.confirmAppointment(id).then(
        () => {
          this.refreshTable();
          this.visible=false;
        }
      )
    },
    
    refreshTable(){
      AppointmentService.getDoctorAppointments(this.currentUser.username).then(
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

  created() {
    this.refreshTable();
  }
}

</script>
<style>
.title {
  font-family: 'Courier New', Courier, monospace;
  font-weight: bold;
  font-stretch: condensed;
}
</style>