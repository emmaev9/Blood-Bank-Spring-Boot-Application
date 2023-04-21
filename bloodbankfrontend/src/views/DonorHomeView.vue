<template>
  <div class="container">
    <header class="jumbotron">
      <DonorHome />
        
    </header>
    <body>
      <div class="container text-center">

      <AppointmentCard @createAppointment="myfunction"/>
      </div>
      <h1>{{appointmentCreated}}</h1>
      <CurrentAppointment :updateTable="myfunction"/>
    </body>
      
  </div>
</template>

<script>
import DonorHome from "../components/DonorHome.vue";
import CurrentAppointment from "../components/CurrentAppointments.vue";
import AppointmentCard from "../components/AppointmentCard.vue";
export default {
  
  data() {
    return {
      count: 0,
    }
  },
  method: { 
    myfunction(value){
      console.log(value);
    }
  },
  components: {
    DonorHome,
    CurrentAppointment,
    AppointmentCard,
  },
  name: "DonorHomeView",
};

</script>