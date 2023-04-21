<template>
  <div class="container">
    <header class="jumbotron">
      <AppointmentList />
    </header>
  </div>
</template>

<script>
import UserService from "../services/user.service";
import AppointmentList from "../components/doctor_components/AppointmentList.vue"

export default {
  name: "DoctorHomeView",
  components:{
    AppointmentList
  },
  data() {
    return {
      content: "",
    };
  },
  mounted() {
    UserService.getDoctorHome().then(
      (response) => {
        this.content = response.data;
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
};
</script>