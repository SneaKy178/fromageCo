<template>

  <h1>Fromage&Co</h1>
  <p>Allez-vous feta ce soir?</p>
   
  <form @submit.prevent="handleSubmit" class="center">
    <label>Courriel : </label>
    <input type="email" required v-model="courriel" />

    <label>Mot de passe : </label>
    <input type="password" required v-model="password" />

    <div class="submit">
      <button>Login</button>
    <div id="inscription">
      <h5>Ou inscrivez-vous</h5>
      <router-link to="/form"> s'inscrire</router-link>
    </div>
      
    </div>
  </form>


</template>

<script>
import Swal from "sweetalert2";
import global from "./global";
export default {
    setup() {
    const { state } = global;
    return { state };
  },
  data() {
    return {
      courriel: "",
      password: "",
    };
  },
  methods: {
    handleSubmit() {
      fetch(`http://localhost:9191/login/${this.courriel}/${this.password}`)
        .then((res) => {
          if (res.ok) {
            return res.json();
          } else if (!res.ok) {
            Swal.fire({
              title: "Erreur!",
              text: "Le courriel ou le mot de passe est invalide.",
              icon: "error",
              confirmButtonText: "ok",
            });
          }
          throw res;
        })
        .then((data) => {
          console.log(data, "Objet de retour data");
          this.state.courriel = data.courriel;
          this.state.role = data.role;
          this.state.isLoggedIn = true;
          this.$router.push("/accountDetails");
        });
    },
  },
};
</script>

<style scoped>
form {
  max-width: 500px;
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
.center {
  width: 400px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
#inscription {
  left: 50%;
  position: absolute;
  transform: translate(-50%);
  margin-top: 20px;
}

#inscription a{
  text-decoration: none;
  color: black;
  font-weight: bold;
  margin-top: 20px;
  font-size: 20px;
}

h1 {
  text-align: center;
  margin-top: 70px;
  font-size: 60px;
}

p {
  text-align: center;
  font-size: 25px;
}
</style>