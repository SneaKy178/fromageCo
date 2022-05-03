<template>
  <header>
    <Navbar/>
  </header>
  <body>
    <router-view />
  </body>
  <footer>
    <Footer/>
  </footer>
  
</template>

<style>
</style>

<script>
import Navbar from "./components/NavBar.vue"
import Footer from "./components/Footer.vue"
export default {
  name: "app",
  components: {
    Navbar,
    Footer
  }
};
</script>


