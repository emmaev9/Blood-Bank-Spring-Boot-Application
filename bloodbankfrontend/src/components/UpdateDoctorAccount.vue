<template>
  <section class="vh-100" style="background-color:white;">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-lg-12 col-xl-11">
          <div class="card text-black" style="border-radius: 25px;">
            <div class="card-body p-md-5">
              <div class="row justify-content-center">
                <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                  <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Update Doctor</p>

                  <Form @submit="handleEdit"  class="mx-1 mx-md-4">
                    <div v-if="!successful">

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="firstName">First Name</label>
                          <Field v-model="this.currentDoctor.firstName" type="text" name="firstName"
                            class="form-control" />
                          <ErrorMessage name="firstName" class="error-feedback" />

                        </div>
                      </div>

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="lastName">Last Name</label>
                          <Field v-model="this.currentDoctor.lastName" type="text" name="lastName" class="form-control" />
                          <ErrorMessage name="lastName" class="error-feedback" />

                        </div>
                      </div>

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="cnp">CNP</label>
                          <Field v-model="this.currentDoctor.cnp" type="text" name="cnp" class="form-control" />
                          <ErrorMessage name="cnp" class="error-feedback" />

                        </div>
                      </div>

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="username">Username</label>
                          <Field v-model="this.currentDoctor.username" type="text" name="username" class="form-control" />
                          <ErrorMessage name="username" class="error-feedback" />
                        </div>
                      </div>

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="email">Email</label>
                          <Field v-model="this.currentDoctor.email" type="email" name="email" class="form-control" />
                          <ErrorMessage name="email" class="error-feedback" />
                        </div>
                      </div>

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <Dropdown v-model="this.currentDoctor.location" :options="this.locations" optionLabel="label"
                            placeholder="Donation Center" class="location" name="location">
                          </Dropdown>
                          <ErrorMessage name="location" class="error-feedback" />

                        </div>
                      </div>


                      <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                        <button class="btn btn-info btn-lg" :disabled="loading">
                          <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                          Update
                        </button>
                      </div>
                    </div>

                  </Form>

                  <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
                    {{ message }}
                    <div>
                      <router-link to="/AdminHome" class="register-link">
                        <button class="btn btn-info">Back to home page</button>
                      </router-link>
                    </div>

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <div class="container">
    <router-view />
  </div>
</template>
  
<script>
import Dropdown from 'primevue/dropdown';
//import Card from 'primevue/card';
import { Field, Form, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import "primevue/resources/themes/soho-light/theme.css";  //theme   
import "primevue/resources/primevue.min.css"; //core
import "primeicons/primeicons.css"; //icons
import DoctorService from '@/services/doctor.service';
import DonationCenterService from '@/services/location.service';
import AdminService from '@/services/admin.service';

export default {
  name: "UpdateDoctorAccount",
  components: {
    Form,
    Field,
    ErrorMessage,
    Dropdown,
  },

  data() {

    const schema = yup.object().shape({
      username: yup
        .string()
        .required("Username is required!")
        .min(3, "Must be at least 3 characters!")
        .max(20, "Must be maximum 20 characters!"),
      email: yup
        .string()
        .required("Email is required!")
        .email("Email is invalid!")
        .max(50, "Must be maximum 50 characters!"),
      password: yup
        .string()
        .required("Password is required!")
        .min(6, "Must be at least 6 characters!")
        .max(40, "Must be maximum 40 characters!"),
      lastName: yup
        .string()
        .required("Last Name is required!"),
      firstName: yup
        .string()
        .required("First Name is required!"),
      cnp: yup
        .string()
        .required("CNP is required!"),
     


    });

    return {
      successful: false,
      loading: false,
      message: "",
      schema,
      locations: [],
      selectedLocation: "",
      currentDoctor: ""
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    selectedDoctorId() {
      return JSON.parse(localStorage.getItem('doctorToUpdateId'));
    }
  },
  methods: {
    handleEdit() {
      this.message = "";
      this.successful = false;
      this.loading = true;
     // this.currentDoctor.location = this.selectedLocation;

      console.log("Sunt in handleEdit")

      AdminService.updateDoctor(this.currentDoctor).then(
        () => {
          this.message = "UPDATED!"
          this.successful = true;
          this.loading = false;

        },
        (error) => {
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
          this.successful = false;
          this.loading = false;
        }
      );
    },

  },
  created() {
    DoctorService.getDoctorData(this.selectedDoctorId).then(
      (respone) => {
        console.log(respone.data)
        this.currentDoctor = respone.data
      },
      (error) => {
        this.message =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
      }
    ),

      DonationCenterService.getDonationCentersWithLabels().then(
        (response) => {
          this.locations = response.data;
          console.log(this.locations)
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
  }
};
</script>
  
<style scoped>
.error-feedback {
  color: red;
  font-size: 0.8rem;
}
</style>