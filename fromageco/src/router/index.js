import { createRouter, createWebHistory } from "vue-router";
import FormClient from '../components/FormClient.vue'

const routes = [
  {
    path:"/",
    component: FormClient
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
