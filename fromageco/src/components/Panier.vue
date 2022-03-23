<template>
  <div v-if="state.isLoggedIn && state.role == 'CLIENT'">
    <div v-if="fullUser.panier.listeFromages.length > 0">
  <table class="table table-dark">
  <thead>
    <tr>
      <th>Nom</th>
      <th>Description</th>
      <th>Prix</th>
      <th>Delete</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="fromage in listFromage" v-bind:key="fromage">
      <td>{{fromage.nom}}</td>
      <td>{{fromage.description}}</td>
      <td>{{fromage.prix.toFixed(2)}}$</td>
      <td> <button @click="addPanier(fromage)">delete</button></td>
    </tr>
    <tr>
      <th>Nombre total</th>
      <th colspan="3">Prix total</th>
    </tr>
    <tr>
      <th>{{panier.nbrFromage}}</th>
      <th colspan="3">{{panier.prixTotal.toFixed(2)}}$</th>
    </tr>
  </tbody>
</table>
  </div>
  <div v-else class="center">
    <h2 id="panierVide">Votre panier est vide</h2>
    <h3>Vous pouvez ajouter des fromages ici :</h3>
    <button id="routerButton"><router-link to="/items" class="link">Fromages</router-link></button><br /> 
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
          console.log(data.panier.listeFromages,"liste")
      });
    },
    addPanier(fromage) {

      const newPanier = {
            id : this.fullUser.id,
            nbrFromage: this.fullUser.panier.nbrFromage,
            prixTotal: this.fullUser.panier.prixTotal,
            listeFromages: this.fullUser.panier.listeFromages,
          };

          newPanier.nbrFromage -= 1
          newPanier.prixTotal -= fromage.prix
          newPanier.listeFromages = newPanier.listeFromages.filter(item => item !== fromage)

          console.log(this.fullUser.panier.listeFromages.indexOf(fromage))
          console.log(newPanier.listeFromages.indexOf(fromage))

          fetch("http://localhost:9191/panier", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newPanier),
          }).then(async (res) => {
              res.json()
              await this.fetchData()
          });


    }
  },
};
</script>

<style scoped>
table {
  text-align: center;
}

#panierVide {
  margin-top:20%;
  text-align: center;
}
h3 {
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
