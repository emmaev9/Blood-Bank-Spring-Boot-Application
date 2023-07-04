<template>
  <br>
  <br>
  <div v-if="message" class="alert alert-success">
    {{message}}
  </div>

  <div class="container text-center col-10">
    <h2 class="title">History of appointments</h2>
  </div>
  <hr class="container text-center col-10">
  <div class="container text-center col-10 ">

    <div>
      <table class="table table-striped table-bordered table-sm shadow-lg rounded">
        <thead>
          <tr>
            <th class="bg-danger bg-gradient">Donation Center</th>
            <th class="bg-danger bg-gradient">Address</th>
            <th class="bg-danger bg-gradient">Date</th>
       
            <th class="bg-danger bg-gradient">Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in appointments" :key="item.id">
            <td> {{ item.donationCenter }}</td>
            <td> {{ item.address }}</td>
            <td> {{ item.date }}</td>
         
            <td>
              <button v-if="!showResults(item.result)" @click="deleteSelectedAppointment(item.id)" class="button-33" role="button">
                  Cancel
              </button>
              <button v-if="showResults(item.result)" @click="this.seeResults(item.id, item.result)" class="button-333" role="button">
                  Result
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
    updateTable:{
      type: Boolean,
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
    showResults(result){
      if(result == ""){
        return false;
      }
      return true;
    },
    
    seeResults(id,result){
      console.log(id)
      localStorage.setItem('appointmentId', JSON.stringify(id));
      this.$emit('add', true);
     // console.log(result);
      this.$emit('result', result);
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
    updateTable: {
      immediate: true, // call handler immediately with current value
      deep:true,
      handler(){
        this.refreshTable()
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
@import url(../assets/styles/update_button.css);

.title {
  font-family: "Roboto Slab", serif;
  font-style:bold;
  font-stretch: condensed;
  font-size: 30px;
  color:  #53008e !important; 
  text-align: left;
  margin-left: 20px;
  margin-top: 25px;
}
.bg-danger {
  background-color:#b8a5e1 !important;
  color: white;
}
</style>