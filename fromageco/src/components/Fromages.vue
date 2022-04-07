<template>
  <table class="table table-dark">
  <thead>
    <tr>
      <th>Nom</th>
      <th>Description</th>
      <th>Enlever de la liste</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="fromage in listFromages" v-bind:key="fromage">
      <td>{{fromage.prenom}}</td>
      <td>{{fromage.nom}}</td>
      <td> <button>Enlever</button></td>
    </tr>
  </tbody>
</table>
  
</template>

<script>
import { ref } from "vue";
import global from "./global";
export default {
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
