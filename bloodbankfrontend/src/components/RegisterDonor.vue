<template>
  <section class="vh-100" style="background-color:white;">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-lg-6 col-sm-11">
          <div class="card text-black  shadow-lg p-3 mb-5 bg-white rounded">
            <div class="card-body">
              <div class="row justify-content-center">
                <div class="col-md-8">
        <p class="text-center h2 fw-bold mb-4 mx-1 mx-md-4 mt-4">Register Form</p>
        <Form @submit="handleRegister" :validation-schema="schema"
        >
          <div v-if="!successful" >

            <div class="d-flex flex-row align-items-sm-center mb-4">
              <i class="fas fa-user fa-lg me-3 fa-fw"></i>
              <div class="form-outline flex-fill mb-0">
                <label class="form-label" for="firstName">First Name</label>
                <Field type="text" name="firstName" class="form-control form-control-sm" />
                <ErrorMessage name="firstName" class="error-feedback" />
              </div>
            </div>

            <div class="d-flex flex-row align-items-center mb-4">
              <i class="fas fa-user fa-lg me-3 fa-fw"></i>
              <div class="form-outline flex-fill mb-0">
                <label class="form-label" for="lastName">Last Name</label>
                <Field type="text" name="lastName" class="form-control form-control-sm" />
                <ErrorMessage name="lastName" class="error-feedback" />
              </div>
            </div>


            <div class="d-flex flex-row align-items-center mb-4">
              <i class="fas fa-user fa-lg me-3 fa-fw"></i>
              <div class="form-outline flex-fill mb-0">
                <label class="form-label" for="username">Username</label>
                <Field type="text" name="username" class="form-control form-control-sm" />
                <ErrorMessage name="username" class="error-feedback" />
              </div>
            </div>

            <div class="d-flex flex-row align-items-center mb-4">
              <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
              <div class="form-outline flex-fill mb-0">
                <label class="form-label" for="email">Email</label>
                <Field type="email" name="email" class="form-control form-control-sm" />
                <ErrorMessage name="email" class="error-feedback" />
              </div>
            </div>

            <div class="d-flex flex-row align-items-center mb-4">
              <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
              <div class="form-outline flex-fill mb-0">
                <label class="form-label" for="email">Phone Number</label>
                <Field type="phoneNumber" name="phoneNumber" class="form-control form-control-sm" />
                <ErrorMessage name="phoneNumber" class="error-feedback" />
              </div>
            </div>

            <div class="d-flex flex-row align-items-center mb-4">
              <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
              <div class="form-outline flex-fill mb-0">
                <label class="form-label" for="password">Password</label>
                <Field type="password" name="password" class="form-control form-control-sm" />
                <ErrorMessage name="password" class="error-feedback" />
              </div>
            </div>

            <div class="d-flex flex-row align-items-center mb-4">
              <i class="fas fa-key fa-lg me-3 fa-fw"></i>
              <div class="form-outline flex-fill mb-0">
                <label class="form-label" for="county">County</label>
                <Field type="text" name="county" class="form-control form-control-sm" />
                <ErrorMessage name="county" class="error-feedback" />
              </div>
            </div>

            <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
              <Dropdown v-model="bloodType" :options="bloodtypes" optionLabel="name" optionValue="name"
                placeholder="Select your bloodtype">
              </Dropdown>
            </div>

            <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
              <button class="button-333" role="button">
                Register
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
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import "primevue/resources/themes/lara-light-indigo/theme.css";  //theme   
import "primevue/resources/primevue.min.css"; //core
import "primeicons/primeicons.css"; //icons

export default {
  name: "RegisterDonor",
  components: {
    Form,
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
      phoneNumber: yup
        .string()
        .required("Phone number is required")
        .matches(phoneRegExp, "Invalid phone number!"),
      county: yup
        .string()
        .required("County is required!")
    });

    return {
      successful: false,
      loading: false,
      message: "",
      bloodType: "",
      bloodtypes: [
        { name: "A+" },
        { name: "A-" },
        { name: "B+" },
        { name: "B-" },
        { name: "AB+" },
        { name: "AB-" },
        { name: "0+" },
        { name: "0-" },
      ],
      schema,
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    blood() {
      return this.bloodtypes;
    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push("/DonorHomeView");
    }
    else {
      this.$router.push("/RegisterDonor");
    }
  },
  methods: {
    handleRegister(user) {
      this.message = "";
      this.successful = false;
      this.loading = true;
      user.bloodType = this.bloodType;

      this.$store.dispatch("auth/registerDonor", user).then(
        (data) => {
          this.message = data.message;
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
};
</script>
  
<style scoped>
@import url(../assets/styles/submit_button.css);

.error-feedback {
  color: red;
  font-size: 0.8rem;
}
</style>