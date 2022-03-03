import { createRouter, createWebHistory } from "vue-router";
import FormClient from '../components/FormClient.vue'
import Login from '../components/Login.vue'
import AccountDetails from '../components/AccountDetails.vue'
import Items from '../components/Items.vue'
import Panier from '../components/Panier.vue'
import PleaseLogin from '../components/PleaseLogin.vue'

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
  },
  {
    path:"/items",
    component: Items
  },
  {
    path:"/panier",
    component: Panier
  },
  {
    path:"/pleaseLogin",
    component: PleaseLogin
  }


  
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
