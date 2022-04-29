<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <router-link to="/accountDetails" class="link">Fromage&Co</router-link>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

<div v-if="state.isLoggedIn && state.role == 'CLIENT'">
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <router-link to="/items" class="link">Items</router-link>
      </li>
      <li class="nav-item active">
        <router-link to="/paiement" class="link">Paiement</router-link>
      </li>
            <li class="nav-item shop active">
        <router-link to="/panier" class="link">Panier</router-link>
      </li>
    </ul>
  </div>
  </div>
  <div v-if="state.isLoggedIn && state.role == 'ADMINISTRATEUR'">
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <router-link to="/users" class="link">Liste des utilisateurs</router-link>
      </li>
      <li class="nav-item active">
        <router-link to="/fromages" class="link">Liste des fromages</router-link>
      </li>
      <li class="nav-item active">
        <router-link to="/adminPaiements" class="link">Liste des paiements</router-link>
      </li>
      <li class="nav-item active">
        <router-link to="/adminAjoutFromage" class="link">Ajouter un fromage</router-link>
      </li>
    </ul>
  </div>
  </div>
</nav>
</template>



<script>
import { ref } from "vue";
import global from "./global";
export default {
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
      fetch(`http://localhost:9191/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.fullUser = data;
        });
    },
  }
};
</script>

<style scoped>

.link {
  margin-left: 10px;
  margin-right: 10px;
  text-decoration: none;
  color: white;
}
.link:hover {
  color: cyan;
}

.shop {
  margin-left: 1580px;
}
</style>