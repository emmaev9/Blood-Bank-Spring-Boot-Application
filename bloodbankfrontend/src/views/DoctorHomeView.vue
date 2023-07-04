<template>
  <div class="container">
    <header class="jumbotron">
      <AppointmentList @add="showModal = true" />
    </header>
   <ModalResult :show="this.showModal" @cancel="this.showModal = false"/> 
  </div>
</template>

<script>
import UserService from "../services/user.service";
import AppointmentList from "../components/doctor_components/AppointmentList.vue"
import ModalResult from "../components/ModalResult.vue";
import {ref} from 'vue';


export default {
  name: "DoctorHomeView",
  components:{
    AppointmentList,
    ModalResult,
  },
  data() {
    return {
      content: "",
      showModal: ref(false),
    };
  },
  computed: {
    currentUser() {
      //return JSON.parse(localStorage.getItem("user"));
      return this.$store.state.auth.user;
    }
  },
  created() {
    UserService.getDoctorHome(this.currentUser.id).then(
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