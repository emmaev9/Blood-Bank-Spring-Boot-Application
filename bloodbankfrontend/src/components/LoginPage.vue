<template>
  <section class="vh-100" style="background-color:white;">
    <div class="container h-100" >
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-8 col-xl-11">
                <div class="card text-black" style="border-radius: 3px;">
                    <div class="card-body p-md-1">
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4" >Sign up</p>

                                <Form @submit="handleLogin" 
                                      :validation-schema="schema" 
                                      class="mx-1 mx-md-4"
                                >

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                              
                                            <Field type="text"
                                                   name="username"
                                                   class="form-control"
                                                   placeholder="Your username"
                                            />
                                            <ErrorMessage name="username" class="error-feedback"/>

                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <Field type="password"
                                                   class="form-control"
                                                   name="password"
                                                   placeholder="Your password"
                                            />
                                            <ErrorMessage name="password" class="error-feedback"/>
                                        </div>
                                    </div>


                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <Button type="submit"
                                                class="btn btn-info btn-lg"
                                                :disabled="loading">
                                                <span
                                                   v-show="loading"
                                                class="spinner-border spinner-border-sm"
                                                ></span>
                                                <span>Login</span>
                                                
                                        </Button>
                                    </div>

                                    <div class="signup-link">
                                      Don't have an account?
                                      <router-link to="/RegisterDonor" class="register-link" >Register here!</router-link>
                                    </div> 

                                </form>

                            </div>
                            <div class="form-group">
                              <div v-if="message" class="alert alert-danger" role="alert">
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
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import Button from 'primevue/button';
import "primevue/resources/themes/mira/theme.css";  //theme   
import "primevue/resources/primevue.min.css"; //core
import "primeicons/primeicons.css"; //icons

export default{
  name: "LoginPage",
  components: {
    Form,
    Field,
    ErrorMessage,
    Button
  },
  data(){
    const schema = yup.object().shape({
      username: yup.string().required("Username is required"),
      password: yup.string().required("Password is required")
    });
    return{
      loading: false,
      message: "",
      schema,
    };
  },
  computed: {
    isLoggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    currentUser(){
      return this.$store.state.auth.user;
    }
  },
  created(){
    if(this.loggedIn){
      if(this.currentUser.role === "DONOR"){
        this.$router.push("/DonorHome");
      }
      if(this.currentUser.role === "ADMIN"){
        this.$router.push("/AdminHome");
      }
      if(this.currentUser.role === "DOCTOR"){
        this.$router.push("/DoctorHome");
      }
    }
  },
  methods: {
    handleLogin(user){
      this.login = true;

      this.$store.dispatch("auth/login", user).then(
        () => {
          
          if(this.currentUser['roles'].includes('DONOR')){
          this.$router.push("/DonorHome");
          }
          if(this.currentUser['roles'].includes('ADMIN')){
            this.$router.push("/AdminHome");
          }
          if(this.currentUser['roles'].includes('DOCTOR')){
            this.$router.push("/DoctorHome");
          }
        },
        (error) =>{
          this.loading = false;
          this.message = 
          (error.response && error.response.data && error.response.data.message) ||
          error.message ||
          error.toString();
        }

      );
    },
  },
}
   
</script>

<style>
.register-link{
  color: blue;
  font-weight: lighter;
}

</style>