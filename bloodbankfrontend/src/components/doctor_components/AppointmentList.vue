<template>
  <br>
  <br>
  <div v-if="message" class="alert alert-success">
    {{ message }}
  </div>

  <div>
    <h2 class="title">Today appointments</h2>
  </div>
  <div class="container text-center">
    <table class="table table-striped table-bordered table-sm" cellspacing="0" width="50%">
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
        <tr v-for="item in this.todayAppointments" :key="item.id">
          <td> {{ item.donationCenter.name }}</td>
          <td> {{ item.donor.firstName + " " + item.donor.lastName }} </td>
          <td> {{ item.date }}</td>
          <td> {{ item.confirmed }}</td>
          <td>
            <button v-if="item.confirmed == 'Not confirmed'" @click="confirmAppointment(item.id)" class="button-222"
              role="button">
              Confirm
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>


  <div>
    <h2 class="title">List of appointments</h2>
  </div>

  <div>
    <table class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
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
            <td v-if="item.doctor.id == this.currentUser.id"> {{ item.donationCenter.name }}</td>
            <td v-if="item.doctor.id == this.currentUser.id"> {{ item.donor.firstName + " " + item.donor.lastName }} </td>
            <td v-if="item.doctor.id == this.currentUser.id"> {{ new Date(item.date).toJSON().slice(0, 10).replace(/-/g, '-').toString() }}</td>
            <td v-if="item.doctor.id == this.currentUser.id"> {{ item.confirmed }}</td>
            <td v-if="item.doctor.id == this.currentUser.id">
              <button v-if="!item.confirmed" @click="confirmAppointment(item.id)" class="button-222" role="button">
                Confirm
              </button>
            </td>
        </tr>
      </tbody>
    </table>


    <div v-if="this.totalPages > 1">
      <div class="row col-sm-10">
        <div class="col-sm-2">
          Total Appointments: {{ this.totalItems }}
        </div>
      </div>
    </div>

    <div v-if="this.totalPages > 1">
      <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
          <li class="page-item">
            <span v-if="this.currentPage <= this.totalPages && this.currentPage != 1">

              <a @click="getPage(this.currentPage + -1)" class="page-link d-inline" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Previous</span>
              </a>
            </span>
          </li>

          <li class="page-item">
            <span v-for="i in Array.from({ length: this.totalPages }, (_, i) => i + 1)" :key="i">
              <span v-if="i == this.currentPage" class="page-link d-inline active">{{ i }}</span>
              <span v-else>
                <a @click="getPage(i)" class="page-link d-inline">{{ i }}</a>
              </span>
            </span>
          </li>
          <li class="page-item">
            <span v-if="this.currentPage < this.totalPages">
              <a @click="getPage(this.currentPage + 1)" class="page-link d-inline" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
              </a>
            </span>
          </li>


        </ul>
      </nav>
    </div>



  </div>

  <br>
  <br>
</template>

<script>
import AppointmentService from "@/services/appointment";

export default {
  name: "AppointmentList",

  components: {
    //DataTable,
  },
  data() {
    return {
      successful: false,
      loading: false,
      appointments: [],
      message: "",
      visible: true,
      totalPages: "",
      totalItems: "",
      currentPage: "",
      today: new Date().toJSON().slice(0, 10).replace(/-/g, '-'),
      todayAppointments: []
    };
  },
  computed: {
    currentUser() {
      //return JSON.parse(localStorage.getItem("user"));
      return this.$store.state.auth.user;
    }
  },
  methods: {
    confirmAppointment(id) {
      AppointmentService.confirmAppointment(id).then(
        () => {
          this.getPage(this.currentPage);
          this.visible = false;
        }
      )
    },
    getPage(page) {
      AppointmentService.pagination(page).then(
        (response) => {
          console.log(response.data.appoitmentList);
          this.appointments = response.data.appoitmentList;
          this.totalPages = response.data.totalPages;
          this.totalItems = response.data.totalItems;
          this.currentPage = response.data.pageNo;
        }
      )
    },
    toJSONLocal(date) {
      var local = new Date(date);
      local.setMinutes(date.getMinutes() - date.getTimezoneOffset());
      return local.toJSON().slice(0, 10);
    },

    getCurrentDayAppointments() {
      console.log(this.today);
      AppointmentService.getCurrentDayAppointments(this.currentUser.username, this.today).then(
        (response) => {
          this.todayAppointments = response.data;
          console.log("Current appointments");
          console.log(response.data);
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

  created() {
    this.getPage(1);
    this.getCurrentDayAppointments();
  }
}

</script>
<style>
@import url(../../assets/styles/update_button.css);

.title {
  font-family: 'Courier New', Courier, monospace;
  font-weight: bold;
  font-stretch: condensed;
}
</style>