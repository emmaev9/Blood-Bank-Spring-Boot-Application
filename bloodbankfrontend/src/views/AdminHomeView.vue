<template>
  <div class="container">
    <header class="jumbotron">
      <DoctorsList />
    </header>
  </div>
</template>

<script>
import UserService from "../services/user.service";
import DoctorsList from "../components/DoctorsList.vue";
export default {
  name: "AdminHomeView",
  components: {
    DoctorsList,
  },
  data() {
    return {
      content: "",
    };
  },
  mounted() {
    UserService.getAdminHome().then(
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