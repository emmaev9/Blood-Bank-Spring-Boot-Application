<template>
  <br>
  <div>
    <h2 class="hh"> Possible donation centers </h2>
  </div>
  <div class="container text-center">

    

    <div>
      <table class="table table-striped table-bordered">
        <thead class="thead-dark">
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Functional</th>
            <th>Schedule(M-F)</th>
            <th>Donations/Day</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="item in locations" :key="item.id">
            <td> {{ item.id }}</td>
            <td> {{ item.name }}</td>
            <td> {{ item.functional }}</td>
            <td> {{ item.schedule }}</td>
            <td> {{ item.maximumDonations }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import UserService from "../services/user.service";
import "primevue/resources/primevue.min.css"; //core
import "primeicons/primeicons.css"; //icons
import "primevue/resources/themes/mira/theme.css";

export default {
  name: "DonorHome",
  data() {
    return {
      successful: false,
      loading: false,
      locations: "",
      date: "",
      message: "",
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
      //return JSON.parse(localStorage.getItem('user'));
    }
  },
  created() {
   // this.currentUser =  JSON.parse(localStorage.getItem('user'));
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
.hh {
  font-family: 'Courier New', Courier, monospace;
  font-weight: bold;
}
</style>