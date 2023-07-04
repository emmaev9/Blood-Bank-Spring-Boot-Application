<template>
  <br>
  <br>
  <div class="container col-10">
    <h2 class="title"> Possible donation centers </h2>
  </div>
  <hr class="container col-10">
  <div class="container col-10">

    <div>
      <table class="table table-striped table-bordered table-sm shadow-sm">
        <thead>
          <tr>
            <th class="bg-danger bg-gradient">Id</th>
            <th class="bg-danger bg-gradient">Name</th>
            <th class="bg-danger bg-gradient">Functional</th>
            <th class="bg-danger bg-gradient">Schedule(M-F)</th>
            <th class="bg-danger bg-gradient">Donations/Day</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="item in locations" :key="item.id">
            <td> {{ item.id }}</td>
            <td> {{ item.name }}</td>
            <td> ✔ </td>
            <td> {{ item.schedule}}</td>
            <td> {{ item.maximumDonations+1 }}</td>
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