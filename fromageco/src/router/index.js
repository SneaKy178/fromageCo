import { createRouter, createWebHistory } from "vue-router";
import FormClient from '../components/FormClient.vue'
import Login from '../components/Login.vue'
import AccountDetails from '../components/AccountDetails.vue'
import Fromages from '../components/Fromages.vue'
import Panier from '../components/Panier.vue'
import PleaseLogin from '../components/PleaseLogin.vue'
import Paiement from '../components/Paiement.vue'
import AdminListeClients from '../components/AdminListeClients.vue'
import AdminListeFromages from '../components/AdminListeFromages.vue'
import AdminPaiement from '../components/AdminPaiements.vue'
import AdminAjoutFromage from '../components/AdminAjoutFromage.vue'

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
    component: Fromages
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
    component: Paiement
  },
  {
    path:"/users",
    component: AdminListeClients
  },
  {
    path:"/fromages",
    component: AdminListeFromages
  },
  {
    path:"/adminPaiements",
    component: AdminPaiement
  },
  {
    path:"/adminAjoutFromage",
    component: AdminAjoutFromage
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
