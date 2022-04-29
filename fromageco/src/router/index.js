import { createRouter, createWebHistory } from "vue-router";
import FormClient from '../components/FormClient.vue'
import Login from '../components/Login.vue'
import AccountDetails from '../components/AccountDetails.vue'
import Items from '../components/Items.vue'
import Panier from '../components/Panier.vue'
import PleaseLogin from '../components/PleaseLogin.vue'
import NewPaiement from '../components/NewPaiement.vue'
import Users from '../components/Users.vue'
import Fromages from '../components/Fromages.vue'
import AdminPaiement from '../components/AdminPaiements.vue'

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
  },
  {
    path:"/paiement",
    component: NewPaiement
  },
  {
    path:"/users",
    component: Users
  },
  {
    path:"/fromages",
    component: Fromages
  },
  {
    path:"/adminPaiements",
    component: AdminPaiement
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
