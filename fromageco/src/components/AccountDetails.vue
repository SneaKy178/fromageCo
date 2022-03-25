<template>
  <div v-if="state.isLoggedIn" class="center">
    <form>
      <label>Prénom : </label>
      <input type="text" readonly :value="fullUser.prenom" />

      <label>Nom : </label>
      <input type="text" readonly :value="fullUser.nom" />

      <label>Courriel : </label>
      <input type="email" readonly :value="fullUser.courriel" />

      <div v-if="fullUser.role === 'CLIENT'">

        <label>Adresse : </label>
        <input type="text" readonly :value="fullUser.adresse" />

        <label>Numéro de téléphone : </label>
        <input type="text" readonly :value="fullUser.numTelephone" />

        <label>Province : </label>
        <input type="text" readonly :value="fullUser.province" />

        <label>Ville : </label>
        <input type="text" readonly :value="fullUser.ville" />

      </div>
      <div v-if="fullUser.role === 'ADMINISTRATEUR'">
        <label>Votre titre : </label>
        <input type="text" readonly :value="fullUser.departement" />
      </div>
    </form>
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
      fetch(`http://localhost:9191/client/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.fullUser = data;
        });
    },
  },
};
</script>

<style scoped>
form {
  max-width: 500px;
  margin: 30px auto;
  background: white;
  text-align: left;
  padding: 40px;
  border-radius: 10px;
}
label {
  color: #aaa;
  display: inline-block;
  margin: 25px 0 15px;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bold;
}
input,
select {
  display: block;
  padding: 10px 6px;
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid #ddd;
  color: #555555;
}
input[type="checkbox"] {
  display: inline-block;
  width: 12px;
  margin: 0 10px 0 0;
  position: relative;
  top: 2px;
}
.pill {
  display: inline-block;
  margin: 20px 10px 0 0;
  padding: 6px 12px;
  background: #eee;
  border-radius: 20px;
  font-size: 12px;
  letter-spacing: 1px;
  font-weight: bold;
  color: #777;
  cursor: pointer;
}



</style>