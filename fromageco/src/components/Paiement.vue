<template>
<div v-if="state.isLoggedIn && state.role == 'CLIENT'">
  <div v-if="listPaiements.length == 0"> <h2 id="pasPaiement">Vous n'avez pas de méthode de paiement enregistré</h2></div>
  <div v-else>
  <table class="table table-dark mt-4 justify-content-center">
  <thead>
    <tr>
      <th>Marque</th>
      <th>Numéro carte</th>
      <th>Date expiration</th>
      <th>Détenteur de carte</th>
      <th>CVV</th>
      <th>Code Postale</th>
      <th>Enlever une carte</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="carte in listPaiements" v-bind:key="carte">
      <td>{{carte.marque}}</td>
      <td>{{carte.numCarte}}</td>
      <td>{{carte.dateExpiration}}</td>
      <td>{{carte.detenteurCarte}}</td>
      <td>{{carte.cvv}}</td>
      <td>{{carte.codePostale}}</td>
       <td> <button @click="addPanier(carte)">Enlever</button></td>
    </tr>
  </tbody>
  </table>
 </div>
  
    <h1>Ajouter un paiement</h1>
    <form @submit.prevent="handleSubmit">

      <label>Marque</label>
      <select v-model="marque">
        <option value="Master card">Master card</option>
        <option value="Visa">Visa</option>
      </select>

      <label>Numéro de carte : </label>
      <input type="text" required v-model="numCarte"  placeholder="1111111111111111" maxlength="16"/>

      <label>Date expiration : </label>
      <input type="text" required v-model="dateExpiration" placeholder="01/11"  maxlength="5"/>

      <label>Nom du détenteur de carte : </label>
      <input type="text" required v-model="detenteur" placeholder="John Doe"/>

      <label>cvv : </label>
      <input type="text" required v-model="cvv" placeholder="111" maxlength="3" />

      <label>Code postal : </label>
      <input type="text" required v-model="codePostale" placeholder="J1J5S1"  maxlength="6" />

      <div class="submit">
        <button id="formButton">Ajouter un mode de paiement</button>
      </div>
    </form>

</div>
  <div v-else>
    <PleaseLogin/>
  </div>
</template>

<script>
import Swal from "sweetalert2";
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
    const fullUser = ref({});
    return { state, listPaiements,fullUser };
  },
    data() {
    return {
      marque: "",
      numCarte: "",
      dateExpiration: "",
      detenteur: "",
      cvv: "",
      codePostale: "",
    };
  },
  created() {
    this.fetchPaiements();
    this.fetchData()
  },
  methods: {
    fetchPaiements() {
      fetch(`http://localhost:9191/paiement/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then(async(data) => {
          this.listPaiements = data;
          console.log(data,"paiement")
      });
    },
    fetchData() {
      fetch(`http://localhost:9191/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.fullUser = data;
          console.log(data,"data")
      });
    },
    handleSubmit() {
        const paiement = JSON.stringify({
          marque: this.marque,
          numCarte: this.numCarte,
          dateExpiration: this.dateExpiration,
          detenteurCarte: this.detenteur,
          cvv: this.cvv,
          codePostale: this.codePostale,
          client: this.fullUser
        });

         fetch("http://localhost:9191/paiement", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: paiement,
          }).then(async (res) => {
              res.json()
                await this.fetchPaiements();
                this.marque = ""
                this.numCarte = ""
                this.dateExpiration = ""
                this.detenteur = ""
                this.cvv = ""
                this.codePostale = ""

                Swal.fire({
                  title: "Succès!",
                  text: "Vous avez ajouté une méthode de paiement à votre compte.",
                  icon: "success",
                  confirmButtonText: "ok",
                });
          });
      },
      addPanier(paiement) {
        fetch(`http://localhost:9191/paiement/delete/${paiement.id}`, {method: 'DELETE'})
        .then(async () => {
          await this.fetchPaiements();
        })
      },
  },
};
</script>

<style scoped>
table {
  width: 80%;
  margin-left: auto;
  margin-right: auto;
}

h1 {
  text-align: center;
}
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

#formButton {
  background: #0b6dff;
  border: 0;
  padding: 10px 20px;
  margin-top: 20px;
  color: white;
  border-radius: 20px;
  font-size: 16px;
}
.submit {
  text-align: center;
}


#pasPaiement{
  margin-top: 20px;
  margin-bottom: 50px;
  text-align: center;
}
</style>


