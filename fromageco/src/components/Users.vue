<template>
  <div v-if="state.isLoggedIn && state.role == 'ADMINISTRATEUR'">
    <div v-if="listUsers.length > 0">
  <table class="table table-dark">
  <thead>
    <tr>
      <th>Prenom</th>
      <th>Nom</th>
      <th>Courriel</th>
      <th>Enlever de la liste</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="user in listUsers" v-bind:key="user">
      <td>{{user.prenom}}</td>
      <td>{{user.nom}}</td>
      <td>{{user.courriel}}</td>
      <td> <button @click="removeClient(user)">Enlever</button></td>
    </tr>
  </tbody>
</table>
  </div>
      <div v-else class=" d-flex justify-content-center mt-4">
        <h2 >La liste est vide</h2>
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
    const listUsers = ref({});
    return { state, listUsers }
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      fetch(`http://localhost:9191/clients`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.listUsers = data;
      });
    },
    removeClient(user) {
      fetch(`http://localhost:9191/client/delete/${user.id}`, {method: 'DELETE'})
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

a {
  text-decoration: none;
  color:white;
}

.center {
  margin-top: 15%;
  text-align: center;
  width: 500px;
  left: 50%;
  position: relative;
  transform: translate(-50%);
}
</style>
