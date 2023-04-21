<template>
  <div class="card">
    <div class="flex align-items-center card-container">
      <div
        class="flex align-items-center justify-content-center w-2rem h-2rem bg-indigo-500 font-bold text-white border-round m-2">
        2</div>
      <Card style="width: 35em">
        <template #title> Edit Profile </template>
        <template #content>
          <Form @submit="handleEditProfile" :validation-schema="schema" class="mx-1 mx-md-4">
            <div v-if="!successful">

              <div class="d-flex flex-row align-items-center mb-4">
                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                <div class="form-outline flex-fill mb-0">
                  <label class="form-label" for="firstName">First Name</label>
                  <Field v-model="this.currentUser.firstName" name="firstName" type="text" class="form-control"></Field>
                  <ErrorMessage name="firstName" class="error-feedback" />

                </div>
              </div>

              <div class="d-flex flex-row align-items-center mb-4">
                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                <div class="form-outline flex-fill mb-0">
                  <label class="form-label" for="lastName">Last Name</label>
                  <Field v-model="this.currentUser.lastName" name="lastName" type="text" class="form-control"></Field>
                  <ErrorMessage name="lastName" class="error-feedback" />

                </div>
              </div>


              <div class="d-flex flex-row align-items-center mb-4">
                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                <div class="form-outline flex-fill mb-0">
                  <label class="form-label" for="username">Username</label>
                  <Field v-model="this.currentUser.username" name="username" type="text" class="form-control"></Field>
                  <ErrorMessage name="username" class="error-feedback" />
                </div>
              </div>

              <div class="d-flex flex-row align-items-center mb-4">
                <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                <div class="form-outline flex-fill mb-0">
                  <label class="form-label" for="email">Email</label>
                  <Field v-model="this.currentUser.email" name="email" type="text" class="form-control"></Field>
                  <ErrorMessage name="email" class="error-feedback" />
                </div>
              </div>

              <div class="d-flex flex-row align-items-center mb-4">
                <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                <div class="form-outline flex-fill mb-0">
                  <label class="form-label" for="password">Password</label>
                  <Field v-model="this.currentUser.password" name="password" type="password" class="form-control"></Field>
                  <ErrorMessage name="password" class="error-feedback" />
                </div>
              </div>

              <div class="d-flex flex-row align-items-center mb-4">
                <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                <div class="form-outline flex-fill mb-0">
                  <label class="form-label" for="county">County</label>
                  <Field v-model="this.currentUser.county" name="county" type="text" class="form-control"></Field>
                  <ErrorMessage name="county" class="error-feedback" />
                </div>
              </div>
              <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                <label class="form-label" for="bloodType">Blood type</label>
                <Dropdown v-model="this.currentUser.bloodType" :options="bloodtypes" optionLabel="name" optionValue="name"
                  placeholder="Select your bloodtype"
                  class="bloodType" >
                </Dropdown>

              </div>

              <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                <button class="btn btn-primary btn-lg" :disabled="loading">
                  <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                  Update
                </button>
              </div>
            </div>

          </Form>

          <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
            {{ message }}
          </div>
        </template>
      </Card>
    </div>
  </div>
</template>
  
<script>
import Dropdown from 'primevue/dropdown';
import Card from 'primevue/card';
import { Field, Form, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import "primevue/resources/themes/soho-light/theme.css";  //theme   
import "primevue/resources/primevue.min.css"; //core
import "primeicons/primeicons.css"; //icons

export default {
  name: "EditDonorProfile",
  components: {
    Form,
    Card,
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
.error-feedback {
  color: red;
  font-size: 0.8rem;
}
</style>