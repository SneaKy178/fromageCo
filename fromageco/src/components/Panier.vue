<template>
  <div v-if="state.isLoggedIn && state.role == 'CLIENT'">
  <table class="table table-dark">
  <thead>
    <tr>
      <th>Nom</th>
      <th>Description</th>
      <th>Prix</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="fromage in listFromage" v-bind:key="fromage">
      <td>{{fromage.nom}}</td>
      <td>{{fromage.description}}</td>
      <td>{{fromage.prix}}$</td>
    </tr>
    <tr>
      <th>Nombre total</th>
      <th colspan="2">Prix total</th>
    </tr>
    <tr>
      <th>{{panier.nbrFromage}}</th>
      <th colspan="2">{{panier.prixTotal}}$</th>
    </tr>
  </tbody>
</table>
  </div>
  <div v-else>
    <PleaseLogin/>
  </div>
</template>

<script>
import PleaseLogin from "./PleaseLogin.vue"
import { ref } from "vue";
import global from "./global";
export default {
  components: {
    PleaseLogin
  },
  setup() {
    const { state } = global;
    const fullUser = ref({});
    const panier = ref({});
    const listFromage = ref({});
    return { state, fullUser, panier, listFromage };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      fetch(`http://localhost:9191/client/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.fullUser = data;
          this.panier = data.panier
          this.listFromage = data.panier.listeFromages
          console.log(data.panier,"panier")
      });
    },
  },
};
</script>

<style scoped>
table {
  text-align: center;
}
</style>
