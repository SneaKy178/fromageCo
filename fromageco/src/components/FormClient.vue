<template>
  <body>
    <h1>Formulaire d'inscription</h1>
    <form @submit.prevent="handleSubmit">
      <label>Prénom : </label>
      <input type="text" required v-model="prenom" />
      <div v-if="prenomError" class="error">
        {{ prenomError }}
      </div>

      <label>Nom : </label>
      <input type="text" required v-model="nom" />
      <div v-if="nomError" class="error">
        {{ nomError }}
      </div>

      <label>Courriel : </label>
      <input type="email" required v-model="courriel" />

      <label>Mot de passe : </label>
      <input type="password" required v-model="password" />
      <div v-if="passwordError" class="error">
        {{ passwordError }}
      </div>

      <label>Adresse : </label>
      <input type="text" required v-model="adresse" />

      <label>Numéro de téléphone : </label>
      <input type="text" required v-model="numTelephone" />

      <label>Province : </label>
      <input type="text" required v-model="province" />

      <label>Ville : </label>
      <input type="text" required v-model="ville" />

      


      <div>
        <input type="checkbox" required v-model="terms" />
        <label>Acceptez vous de suivre les règles?</label>
      </div>

      <div class="submit">
        <button>Créez votre compte étudiant</button>
      </div>
    </form>
  </body>
</template>

<script>
export default {
  data() {
    return {
      nom: "",
      prenom: "",
      courriel: "",
      password: "",
      adresse: "",
      numTelephone: "",
      province: "",
      ville: "",
      terms: false,
      passwordError: "",
    };
  },
  methods: {
    handleSubmit() {
      this.passwordError = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/.test(
        this.password
      )
        ? ""
        : "Le mot de passe requiert au moins 6 lettres, une majuscule, une minuscule et un chiffre. ";
      this.prenomError =
        /^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$/.test(
          this.prenom
        )
          ? ""
          : "Le prénom ne peut pas contenir de chiffre ou de caractères spéciaux.";
      this.nomError =
        /^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$/.test(this.nom)
          ? ""
          : "Le nom ne peut pas contenir de chiffre ou de caractères spéciaux.";
      if (!this.passwordError && !this.prenomError && !this.nomError) {
        var request = new XMLHttpRequest();
        request.open("POST", "http://localhost:9191/client");
        request.setRequestHeader(
          "Content-Type",
          "application/json; charset=UTF-8"
        );

        const client = JSON.stringify({
          prenom: this.prenom,
          nom: this.nom,
          courriel: this.courriel,
          password: this.password,
          adresse: this.adresse,
          numTelephone: this.numTelephone,
          province: this.province,
          ville: this.ville,
        });

        request.send(client);
        // this.$router.push("/login");
      }
    },
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