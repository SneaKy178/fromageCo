<template>
  <div v-if="state.isLoggedIn && state.role == 'ADMINISTRATEUR'">

    <div class="dropzone center" @change="selectedFile">
        <span>Veuillez sélectionner une image</span>
        <label for="dropzoneFile">Select File</label>
        <input type="file" id="dropzoneFile">
        <span>Fichier : {{fileName.name}}</span>

    </div>


    <form @submit.prevent="handleSubmit">

        <label>Nom : </label>
        <input type="text" required v-model="nom" />

        <label>Prix : </label>
        <input type="number" required v-model="prix" min="1" />

        <label>Description : </label>
        <textarea type="textarea" required v-model="description" rows="4" cols="50"/>

        <div class="submit">
            <button>Ajouter ce fromage à la liste</button>
      </div>

    </form>
</div>
<div v-else>
    <PleaseLogin/>
</div>
    
</template>


<script>
import PleaseLogin from "./PleaseLogin.vue"
import Swal from "sweetalert2";
import { ref } from "vue";
import global from "./global";
export default {
    components: {
     PleaseLogin
  },
    setup() {
        const fileName = ref("");

        const selectedFile = () => {
            fileName.value = document.querySelector("#dropzoneFile").files[0]
        }
        
        const { state } = global;

        return {fileName,selectedFile,state};
    },
    data() {
        return {
            nom: "",
            prix: "",
            description: "",
            quatiteDispnible: "",
            data: "",
        };
    },
    methods: {
        handleSubmit() {
            

        var request = new XMLHttpRequest();
        request.open("POST", "http://localhost:9191/fromage");
        request.setRequestHeader(
          "Content-Type",
          "application/json; charset=UTF-8"
        );

    const file = document.querySelector("#dropzoneFile").files[0]
    const reader = new FileReader()

        let rawImg;

        reader.onloadend = () => {
        rawImg = reader.result;


        const fromage = JSON.stringify({
          nom: this.nom,
          prix: this.prix,
          description: this.description,
          data: rawImg.substring(rawImg.indexOf(',') + 1)
        });

            this.nom = ""
            this.prix = ""
            this.description = ""
            this.quatiteDispnible = ""
            this.data = ""
            this.fileName = ""

            Swal.fire({
              title: "Succès!",
              text: "Ce fromage est ajouté à la liste des fromages",
              icon: "success",
              confirmButtonText: "ok",
            });

        request.send(fromage);

        }
        reader.readAsDataURL(file);
        }
    }

    
}
</script>

<style scoped>
.dropzone {
    width: 500px;
    height: 200px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    row-gap: 16px;
    border: 2px dashed #41B883;
    background-color: #FFF;
    transition: .3s ease all;

    margin: 0 auto;
    margin-top: 80px;
}

.dropzone label {
    padding: 8px 12px;
    color: #FFF;
    background-color: #41B883;
    transition: 0.3s ease all;
}

.dropzone input {
    display: none;
}

.dropzone:hover {
     border: 2px dashed black;
    background-color: #41B883;
    color: #FFF;
}

.dropzone:hover label {
    padding: 8px 12px;
    color: black ;
    background-color: #FFF;
    transition: 0.3s ease all;
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
button {
  background: #0b6dff;
  border: 0;
  padding: 10px 20px;
  margin-top: 20px;
  color: white;
  border-radius: 20px;
  font-size: 16px;
  margin-left: 22%;
}
</style>