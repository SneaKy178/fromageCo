<template>
      <div class="center">
    <h1>Veuillez vous conneter</h1>
    <button><router-link to="/login" class="link">Login</router-link></button><br /> 
  </div>
</template>


<style scoped>
.center {
  margin-top: 50px;
  text-align: center;
  width: 400px;
  left: 50%;
  position: relative;
  transform: translate(-50%);
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

a {
  text-decoration: none;
  color:white;
}
</style>
