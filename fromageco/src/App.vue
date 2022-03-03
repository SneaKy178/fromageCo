<template>
  <header>
    <Navbar/>
  </header>
  <router-view />
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

