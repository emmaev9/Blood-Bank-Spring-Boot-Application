<template>
  <section class="vh-100" style="background-color:white;">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-lg-6 col-sm-11">
          <div class="card text-black  shadow-lg p-3 mb-5 bg-white rounded">
            <div class="card-body">
              <div class="row justify-content-center">
                <div class="col-md-9">
                  <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Update Profile</p>

                  <Form @submit="handleEditProfile" :validation-schema="schema" class="mx-1 mx-md-4">
                    <div v-if="!successful">

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="firstName">First Name</label>
                          <Field v-model="this.currentUser.firstName" name="firstName" type="text" class="form-control  form-control-sm">
                          </Field>
                          <ErrorMessage name="firstName" class="error-feedback" />

                        </div>
                      </div>

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="lastName">Last Name</label>
                          <Field v-model="this.currentUser.lastName" name="lastName" type="text" class="form-control  form-control-sm">
                          </Field>
                          <ErrorMessage name="lastName" class="error-feedback" />

                        </div>
                      </div>


                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="username">Username</label>
                          <Field v-model="this.currentUser.username" name="username" type="text" class="form-control  form-control-sm">
                          </Field>
                          <ErrorMessage name="username" class="error-feedback" />
                        </div>
                      </div>

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="email">Email</label>
                          <Field v-model="this.currentUser.email" name="email" type="text" class="form-control  form-control-sm"></Field>
                          <ErrorMessage name="email" class="error-feedback" />
                        </div>
                      </div>

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="email">Phone Number</label>
                          <Field v-model="this.currentUser.phoneNumber" type="phoneNumber" name="phoneNumber" class="form-control form-control-sm" />
                          <ErrorMessage name="phoneNumber" class="error-feedback" />
                        </div>
                      </div>
                      <!--
                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="password">Password</label>
                          <Field v-model="this.currentUser.password" name="password" type="password" class="form-control  form-control-sm">
                          </Field>
                          <ErrorMessage name="password" class="error-feedback" />
                        </div>
                      </div>  -->

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <label class="form-label" for="county">County</label>
                          <Field v-model="this.currentUser.county" name="county" type="text" class="form-control  form-control-sm"></Field>
                          <ErrorMessage name="county" class="error-feedback" />
                        </div>
                      </div>
                      <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                        <label class="form-label" for="bloodType"> Blood type </label>
                        <Dropdown v-model="this.currentUser.bloodType" :options="bloodtypes" optionLabel="name"
                          optionValue="name" placeholder="Select your bloodtype" class="bloodType">
                        </Dropdown>

                      </div>

                      <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                        <button class="button-222" role="button">
                          Update
                        </button>
                        <button @click="this.deleteAccount"  class="button-33" role="button">
                          Delete account
                        </button>
                      </div>
                    </div>

                  </Form>
                  

                  <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
                    {{ message }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
  
<script>
import Dropdown from 'primevue/dropdown';
//import Card from 'primevue/card';
import { Field, Form, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import "primevue/resources/themes/soho-light/theme.css";  //theme   
import "primevue/resources/primevue.min.css"; //core
import "primeicons/primeicons.css"; //icons
import DonorService from "../services/donor.service";

export default {
  name: "EditDonorProfile",
  components: {
    Form,
   // Card,
    Field,
    ErrorMessage,
    Dropdown,
  },

  data() {
    const phoneRegExp = /^((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?$/
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
     /* password: yup
        .string()
        .required("Password is required!")
        .min(6, "Must be at least 6 characters!")
        .max(40, "Must be maximum 40 characters!"),*/
      lastName: yup
        .string()
        .required("Last Name is required!"),
      firstName: yup
        .string()
        .required("First Name is required!"),
        phoneNumber: yup
        .string()
        .required("Phone number is required")
        .matches(phoneRegExp, 'Phone number is not valid'),
  
      county: yup
        .string()
        .required("County is required!")
    });

    return {
      successful: false,
      loading: false,
      message: "",
      schema,
      bloodtypes: [{ name: "A+" },
      { name: "A-" },
      { name: "B+" },
      { name: "B-" },
      { name: "AB+" },
      { name: "AB-" },
      { name: "0+" },
      { name: "0-" },]
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    currentUser() {
      return this.$store.state.auth.user;
      //return JSON.parse(localStorage.getItem('user'));
    }
  },
  methods: {

    deleteAccount(){
        DonorService.deleteAccount(this.currentUser.id).then(
          (response) => {
            this.message = response.data.message;
            this.$store.dispatch("auth/logout");
            this.$router.push("/LoginDonor");
          },
          (error) => {
            this.message =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
          });
    },

    handleEditProfile() {
      this.message = "";
      this.successful = false;
      this.loading = true;
      console.log(this.currentUser)
      this.$store.dispatch("auth/editDonor", this.currentUser).then(
        () => {
          this.message = "UPDATED!"
          localStorage.setItem('user', JSON.stringify(this.currentUser));
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
  }
};
</script>
  
<style scoped>
@import url(../assets/styles/submit_button.css);
@import url(../assets/styles/cancel_button.css);
@import url(../assets/styles/update_button.css);


.error-feedback {
  color: red;
  font-size: 0.8rem;
}</style>