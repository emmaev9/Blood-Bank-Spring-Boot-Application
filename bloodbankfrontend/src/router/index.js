import { createRouter, createWebHashHistory } from "vue-router";
import Login from "../components/LoginPage.vue";
import RegisterDonor from "../components/RegisterDonor.vue";
import RegisterDoctor from "../components/RegisterDoctor.vue";
const UpdateDoctorAccount = () => import("../components/UpdateDoctorAccount.vue");
//lazy-loaded
const DonorHome = () => import("../views/DonorHomeView.vue");
const AdminHome = () => import("../views/AdminHomeView.vue");
const DoctorHome = () => import("../views/DoctorHomeView.vue");
const EditDonorProfile = () => import("../components/EditDonorProfile.vue");


const routes = [
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/RegisterDonor",
    name: "RegisterDonor",
    component: RegisterDonor,
  },
  {
    path: "/RegisterDoctor",
    component: RegisterDoctor,
  },
  {
    path: "/DonorHome",
    name: "DonorHome",
    component: DonorHome,
  },
  {
    path: "/AdminHome",
    name: "AdminHome",
    component: AdminHome,
  },
  {
    path: "/DoctorHome",
    name: "DoctorHome",
    component: DoctorHome,
  },
  {
    path: "/EditDonorProfile",
    name: "EditDonorProfille",
    component: EditDonorProfile,
  },
  {
    path: "/UpdateDoctorAccount",
    name: "UpdateDoctorAccount",
    component: UpdateDoctorAccount,
  }

];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});


export default router;
