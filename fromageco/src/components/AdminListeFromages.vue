<template>
  <div v-if="state.isLoggedIn && state.role == 'ADMINISTRATEUR'">
    <div v-if="listFromages.length > 0">
  <table class="table table-dark">
  <thead>
    <tr>
      <th>Nom</th>
      <th>Prix</th>
      <th>Description</th>
      <th>Enlever de la liste</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="fromage in listFromages" v-bind:key="fromage">
      <td>{{fromage.nom}}</td>
      <td>{{fromage.prix}}$</td>
      <td>{{fromage.description}}</td>
      <td> <button @click="removeFromage(fromage)">Enlever</button></td>
    </tr>
  </tbody>
</table>
    </div>
    <div v-else class=" d-flex justify-content-center mt-4">
      <h2>Il n'y a aucun fromage disponible présentement</h2>
    </div>
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
    const listFromages = ref({});
    return { state, listFromages }
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      fetch(`http://localhost:9191/fromages`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.listFromages = data;
      });
    },
    removeFromage(fromage) {
      fetch(`http://localhost:9191/fromage/delete/${fromage.id}`, {method: 'DELETE'})
        .then(async () => {
          await this.fetchData();
        })
      },
  },
};
</script>

<style scoped>
table {
  text-align: center;
}


#routerButton {
  background: #0b6dff;
  border: 0;
  padding: 10px 20px;
  margin-top: 20px;
  color: white;
  border-radius: 20px;
  font-size: 16px;
}
</style>
