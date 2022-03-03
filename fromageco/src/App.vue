<template>
  <header>
    <Navbar/>
  </header>
  <body>
    <router-view />
  </body>
  
</template>

<style>
</style>

<script>
import Navbar from "./components/NavBar.vue"
export default {
  name: "app",
  components: {
    Navbar,
  }
};
</script>


