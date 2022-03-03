<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <router-link to="/accountDetails" class="link">Fromage&Co</router-link>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <router-link to="/items" class="link">Items</router-link>
      </li>
      <li class="nav-item active">
        <router-link to="/paiement" class="link">Paiement</router-link>
      </li>
            <li class="nav-item shop active">
        <router-link to="/panier" class="link">Panier</router-link>
      </li>
    </ul>
  </div>
</nav>
</template>


<style scoped>

.link {
  margin-left: 10px;
  margin-right: 10px;
  text-decoration: none;
  color: white;
}
.link:hover {
  color: cyan;
}

.shop {
  margin-left: 1580px;
}
</style>