<template>
  <div class="container">
    <header class="jumbotron">
      <DonorHome />
        
    </header>
    <body>
      <div class="container text-center col-10">

           <AppointmentCard @update="refresh"/>
      </div>
      <h1>{{appointmentCreated}}</h1>
      <CurrentAppointment :updateTable="this.refreshTableh" @add="showModal = true" @result="getResult"/>
    </body>
    
    <ShowResultsModal :show="showModal" @close="showModal=false" :resultProp="this.rezz"/>
  </div>
</template>

<script>
import DonorHome from "../components/DonorHome.vue";
import CurrentAppointment from "../components/CurrentAppointments.vue";
import AppointmentCard from "../components/AppointmentCard.vue";
import ShowResultsModal from "@/components/ShowResultsModal.vue";
//import AppointmentService from "../services/appointment.js";
//import { ref } from "vue";
export default {
  
  data() {
    return {
      count: 0,
      showModal: false,
      refreshTable: false,
      rezz:"",
    }
  },
  methods:{
    getResult(value){
      console.log(value);
      this.rezz = value;
      }
    },
    refresh(value){
      console.log(value);
      this.refreshTable = value;
    },
  
  components: {
    DonorHome,
    CurrentAppointment,
    AppointmentCard,
    ShowResultsModal
  },
  name: "DonorHomeView"
};


</script>
<style>
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
  opacity: 0.8;
}

</style>