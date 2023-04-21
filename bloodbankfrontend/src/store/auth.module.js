import AuthService from '../services/auth.service';
import DonorService from '../services/donor.service';

const user = JSON.parse(localStorage.getItem('user'));
const initialState = user
  ? { status: { loggedIn: true }, user }
  : { status: { loggedIn: false }, user: null };

export const auth = {
  namespaced: true,
  state: initialState,
  actions: {
    login({ commit }, user) {
      return AuthService.login(user).then(
        user => {
          commit('loginSuccess', user);
          return Promise.resolve(user);
        },
        error => {
          commit('loginFailure');
          return Promise.reject(error);
        }
      );
    },
    logout({ commit }) {
      AuthService.logout();
      commit('logout');
    },
    registerDonor({ commit }, user) {
      return AuthService.registerDonor(user).then(
        response => {
          commit('registerSuccess');
          return Promise.resolve(response.data);
        },
        error => {
          commit('registerFailure');
          return Promise.reject(error);
        }
      );
    },
    registerDoctor({ commit }, user) {
      return AuthService.registerDoctor(user).then(
        response => {
          commit('registerSuccess');
          return Promise.resolve(response.data);
        },
        error => {
          commit('registerFailure');
          return Promise.reject(error);
        }
      );
    },
    editDonor({commit}, user) {
      return DonorService.editProfile(user)
      .then(
        () => {
          commit('updateUser', user);
          return Promise.resolve();
        },
        error => {
          console.log(user);
          return Promise.reject(error);
        }
      )
    },
  },
  mutations: {
    loginSuccess(state, user) {
      state.status.loggedIn = true;
      state.user = user;
    },
    loginFailure(state) {
      state.status.loggedIn = false;
      state.user = null;
    },
    logout(state) {
      state.status.loggedIn = false;
      state.user = null;
    },
    registerSuccess(state) {
      state.status.loggedIn = false;
    },
    registerFailure(state) {
      state.status.loggedIn = false;
    },
    updateUser(state, user) {
      state.user.firstName = user.firstName;
      state.user.lastName = user.lastName;
      state.user.username = user.username;
      state.user.email = user.email;
      state.user.password = user.password;
      state.user.county = user.county;
      state.user.bloodType = user.bloodType; 

    }
  }
};