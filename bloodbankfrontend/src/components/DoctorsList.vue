<template>
  <br>
  <br>
  <div v-if="message" class="alert alert-success">
    {{ message }}
  </div>

  <div>
    <h2 class="title">List of doctors</h2>
  </div>
  <div class="container text-center">

    <div>
      <table class="table table-striped table-bordered">
        <thead class="thead-dark">
          <tr>
            <th>First Name </th>
            <th>Last Name </th>
            <th>Donation center</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in doctors" :key="item.id">
            <td> {{ item.firstName }}</td>
            <td> {{ item.lastName }}</td>
            <td> {{ item.location.name }}</td>
            <td>
              <button @click="deleteSelectedDoctor(item.id)" class="button-33" role="button">Delete</button>
              
              <button @click="updateSelectedDoctor(item.id)" class="button-222" role="button">Update</button>
            
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  <router-link to="/RegisterDoctor" class="nav-link">
   <button class="button-333" role="button">Add new doctor</button>
  </router-link>
  <br>
  <br>
</template>

<script>
import AdminService from "../services/admin.service";

export default {
  name: "DoctorsList",

  data() {
    return {
      successful: false,
      loading: false,
      doctors: "",
      message: "",
    };
  },
  computed: {
    currentUser() {
      return JSON.parse(localStorage.getItem("user"));
    }
  },
  methods: {
    deleteSelectedDoctor(username){
      console.log("Delete doctor before service call  " + username);
      AdminService.deleteDoctor(username).then(
        () => {
          this.message = "Doctor successfully deleted!";
          this.refreshTable();
        }
      )
    },
    updateSelectedDoctor(id) {
      console.log("Update doctor before service call  " + id);
      localStorage.setItem('doctorToUpdateId', JSON.stringify(id));
      this.$router.push("/UpdateDoctorAccount");
    },
    refreshTable() {
      AdminService.allDoctors().then(
        (response) => {
          this.doctors = response.data;
        },
        (error) => {
          this.content =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
        }
      )
    }
  },
  mounted() {
    this.refreshTable();
  }
}

</script>
<style>
@import url(../assets/styles/submit_button.css);
@import url(../assets/styles/cancel_button.css);
@import url(../assets/styles/update_button.css);
.title {
  font-family: 'Courier New', Courier, monospace;
  font-weight: bold;
  font-stretch: condensed;
}
</style>
