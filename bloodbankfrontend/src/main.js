import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from './plugins/font-awesome'
import PrimeVue from 'primevue/config';

createApp(App)
.use(store)
.use(router)
.use(PrimeVue)
.component("font-awesome-icon", FontAwesomeIcon)
.mount('#app')
