<template>
<div v-if="state.isLoggedIn && state.role == 'CLIENT'">
  <table class="table table-dark mt-4 justify-content-center">
  <thead>
    <tr>
      <th>Marque</th>
      <th>Numéro carte</th>
      <th>Date expiration</th>
      <th>Détenteur de carte</th>
      <th>CVV</th>
      <th>Code Postale</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>{{fullUser.marque}}</td>
      <td>**** **** **** ****</td>
      <td>{{fullUser.dateExpiration}}</td>
      <td>{{fullUser.detenteurCarte}}</td>
      <td>***</td>
      <td>{{fullUser.codePostale}}</td>
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
    return { state, fullUser };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      fetch(`http://localhost:9191/paiement/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then(async(data) => {
          this.fullUser = data;
          console.log(data,"data")
      });
    }
  }
};
</script>

<style scoped>
table {
  width: 80%;
  margin-left: auto;
  margin-right: auto;
}
</style>


