<template>
  
          
      
  <div class="card-group">
    <div v-for="fromage in fromages" v-bind:key="fromage">
    <div class="card">
      <img
        class="card-img-top"
        :src="`http://127.0.0.1:9191/fromages/images/${fromage.id}`"
        alt="Card image cap"
      />
      <div class="card-body">
        <h5 class="card-title"> {{fromage.nom}}</h5>
        <p class="card-text">Fromage vieillit de {{fromage.vieillesse}} mois.</p>
      </div>
      <div class="card-footer">
        <small class="text-muted"
          >{{ fromage.prix }}
          <input
            type="number"
            id="quantity"
            name="quantity"
            min="1"
            max="5"
            value="1"
        /></small>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import { ref } from "vue";


export default {
  created(){
    this.fetchFromages();
  },
  setup() {
    const fromages = ref({});
    return { fromages};
  },
    methods: {
    fetchFromages() {
      fetch(`http://localhost:9191/fromages`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          console.log(data,"fromage")
          this.fromages = data;
        });
    }
}
}
</script>

<style scoped>
input[type="number"] {
  float: right;
}
</style>
