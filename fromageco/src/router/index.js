import { createRouter, createWebHistory } from "vue-router";
import FormClient from '../components/FormClient.vue'
import Login from '../components/Login.vue'

const routes = [
  {
    path:"/",
    component: Login
  },
  {
    path:"/form",
    component: FormClient
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
