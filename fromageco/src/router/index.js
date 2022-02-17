import { createRouter, createWebHistory } from "vue-router";
import FormClient from '../components/FormClient.vue'
import Login from '../components/Login.vue'
import AccountDetails from '../components/AccountDetails.vue'

const routes = [
  {
    path:"/",
    component: Login
  },
  {
    path:"/login",
    component: Login
  },
  {
    path:"/form",
    component: FormClient
  },
  {
    path:"/accountDetails",
    component: AccountDetails
  }

  
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
