import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from './plugins/font-awesome'
import PrimeVue from 'primevue/config';
import VCalendar from 'v-calendar';
import VDatePicker from 'v-calendar';
import 'v-calendar/style.css';

createApp(App)
.use(store)
.use(router)
.use(PrimeVue)
.use(VCalendar, {})
.use(VDatePicker, {})
.component("font-awesome-icon", FontAwesomeIcon)
.mount('#app')
