<template>
  <div v-if="state.isLoggedIn && state.role == 'ADMINISTRATEUR'">
    <div v-if="listPaiements.length > 0">
  <table class="table table-dark">
  <thead>
    <tr>
      <th>Nom</th>
      <th>Description</th>
      <th>Nom du client</th>
      <th>Enlever de la liste</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="paiement in listPaiements" v-bind:key="paiement">
      <td>{{paiement.marque}}</td>
      <td>{{paiement.numCarte}}</td>
      <td>{{paiement.client.prenom}} {{paiement.client.nom}}</td>
      <td> <button @click="removePaiement(paiement)">Enlever</button></td>
    </tr>
  </tbody>
</table>
    </div>
    <div v-else class=" d-flex justify-content-center mt-4">
      <h2 >Il n'y aucun utilisateur ayant enregistré une méthode de paiement présentement</h2>
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
    const listPaiements = ref({});
    return { state, listPaiements }
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      fetch(`http://localhost:9191/paiements`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          console.log(data)
          this.listPaiements = data;
      });
    },
    removePaiement(paiement) {
      fetch(`http://localhost:9191/paiement/delete/${paiement.id}`, {method: 'DELETE'})
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
