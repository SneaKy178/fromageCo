<template>
  <body>
    <h1>Ajouter un paiement</h1>
    <form @submit.prevent="handleSubmit">

      <label>Marque</label>
      <select v-model="marque">
        <option value="Master card">Master card</option>
        <option value="Visa">Visa</option>
      </select>

      <label>Numéro de carte : </label>
      <input type="text" required v-model="numCarte" />

      <label>Date expiration : </label>
      <input type="text" required v-model="dateExpiration" />

      <label>Nom du détenteur de carte : </label>
      <input type="text" required v-model="detenteur" />

      <label>cvv : </label>
      <input type="text" required v-model="cvv" />

      <label>Code postal : </label>
      <input type="text" required v-model="codePostale" />

      <div class="submit">
        <button>Ajouter un mode de paiement</button>
      </div>
    </form>
  </body>
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
    this.fetchData();
  },
  methods: {
    fetchData() {
      fetch(`http://localhost:9191/client/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then(async(data) => {
          this.fullUser = data;
          console.log(data,"data")
      });
    },
    handleSubmit() {
        var request = new XMLHttpRequest();
        request.open("POST", "http://localhost:9191/paiement");
        request.setRequestHeader(
          "Content-Type",
          "application/json; charset=UTF-8"
        );

        const paiement = JSON.stringify({
          marque: this.marque,
          numCarte: this.numCarte,
          dateExpiration: this.dateExpiration,
          detenteur: this.detenteur,
          cvv: this.cvv,
          codePostale: this.codePostale,
          client: this.fullUser
        });

        request.send(paiement);
      }
  },
};
</script>

<style scoped>
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

button {
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

.error {
  color: #ff0062;
  margin-top: 10px;
  font-size: 0.8em;
  font-weight: bold;
}
</style>