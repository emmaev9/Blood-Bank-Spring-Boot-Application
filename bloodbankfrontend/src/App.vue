<template>
  <div>

  </div>
 <div id="app">
    <nav class="navbar navbar-expand shadow fixed-top" style="background-color:white;">
      
      <div v-if="!currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/RegisterDonor" class="nav-link">
            <font-awesome-icon icon="user-plus" /> Register
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" class="nav-link">
            <font-awesome-icon icon="sign-in-alt" /> Login
          </router-link>
        </li>
      </div>

      <div v-if="this.currentUser" class="navbar-nav ml-auto">
        <div v-if="this.isAdmin" class="nav-item">
          <router-link to="/AdminHome" class="nav-link">
            <font-awesome-icon icon="home" />
              {{ this.currentUser.unsername}}
          </router-link>
        </div>
        <div v-if="this.isDonor" class="nav-item">
            <router-link to="/DonorHome" class="nav-link" style="font-weight: bold;">
              <font-awesome-icon icon="home" />
              {{ this.currentUser.firstName }} {{ this.currentUser.lastName }}
            </router-link>
        </div>
        <div v-if="this.isDonor" class="nav-item">
            <router-link to="/EditDonorProfile" class="nav-link">
              <font-awesome-icon icon="user-edit" />
            </router-link>  
        </div>
         
        

        <li v-if="isDoctor" class="nav-item">
          <router-link to="/DoctorHome" class="nav-link">
            <font-awesome-icon icon="user" />
              {{ this.currentUser.firstName }} {{ this.currentUser.lastName }}
          </router-link>
        </li>

        <li class="nav-item">
          <a class="nav-link" @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" /> 
          </a>
        </li>
        </div>
    </nav>

    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>


export default {
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
      
    },
    isAdmin() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('ADMIN');
      }
      return false;
    },
    isDonor() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('DONOR');
      }

      return false;
    },
    isDoctor() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('DOCTOR');
      }
      return false;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #ffffff;
}

.bg-img{
  width: 100vh;
  height: 100vw;
  object-fit: contain;
  filter: brightness(0.6);
  top:0;
  left:0;
  z-index:-1;
  position: absolute
}
.navbar{
  height: 40px;
  font-size: 15px;
  font-weight: bold;
  background-color:#b8a5e1 !important;
  
  color:rgb(255, 254, 254);
  text-align: left;
  padding-left: 20px;
  padding-right: 20px;
  opacity: 0.9;


}
</style>
