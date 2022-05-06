<template>
  <div class="row">
  <div class="card-group">
    <div class="col-4" v-for="fromage in fromages" v-bind:key="fromage">
    <div class="card">
      <img
        class="card-img-top"
        :src="`http://127.0.0.1:9191/fromages/images/${fromage.id}`"
        alt="Card image cap"
      />
      <div class="card-body">
        <h5 class="card-title"> {{fromage.nom}}</h5>
        <p class="card-text">{{fromage.description}} </p>
      </div>
      <div class="card-footer">
        <small class="text-muted">{{fromage.prix.toFixed(2)}}$</small>
          <button @click="addPanier(fromage)">Ajouter au panier</button>
      </div>
    </div>
  </div>
  </div>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import { ref } from "vue";
import global from "./global";
export default {
  created(){
    this.fetchFromages();
    this.fetchData();
  },
  setup() {
    const fromages = ref({});
    const { state } = global;
    const fullUser = ref({});
    return { fromages, state, fullUser};
  },
    methods: {
          fetchData() {
      console.log(this.state.isLoggedIn);
      console.log(this.fullUser, "fullUser");
      fetch(`http://localhost:9191/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          console.log(data, "data");
          this.fullUser = data;
        });
    },
    fetchFromages() {
      fetch(`http://localhost:9191/fromages`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          console.log(data,"fromage")
          this.fromages = data;
        });
    },
    addPanier(item) {

      
        const listItem = []
        listItem.push(item)

        const newPanier = {
          id : this.fullUser.id,
          nbrFromage: 1,
          prixTotal: item.prix,
          listeFromages: listItem,
        };

        newPanier.nbrFromage += this.fullUser.panier.nbrFromage
        newPanier.prixTotal += this.fullUser.panier.prixTotal
        newPanier.listeFromages.push(...this.fullUser.panier.listeFromages)

        Swal.fire({
              title: "Succès!",
              text: "Ce fromage est ajouté à votre panier",
              icon: "success",
              confirmButtonText: "ok",
    })

          fetch("http://localhost:9191/panier", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newPanier),
          }).then(async (res) => {
              res.json()
              await this.fetchData()
          });

      
  }
    }
    
}
</script>

<style scoped>

button {
  float: right;
}

img {
  height: 500px;
  width: 500px;
}
.card {
  height: 660px;
}
</style>
