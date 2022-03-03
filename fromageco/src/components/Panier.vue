<template>

</template>

<script>
import { ref } from "vue";
import global from "./global";
export default {
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
        .then(async(data) => {
          this.fullUser = data;
          await this.fetchPanier();
        });
    },
    fetchPanier() {
        fetch(`http://localhost:9191/panier/${this.fullUser.id}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.panier = data;
          this.listFromage =  data.listeFromages;
          console.log(data,"panier")
          console.log(data.listeFromages,"fromages")
        });
    },
    login() {
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>

</style>
