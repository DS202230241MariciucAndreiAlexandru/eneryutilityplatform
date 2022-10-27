<template>
  <v-container class="c">
    <v-sheet dark class="align-self-center">
      <h1 class="align-center ma-16" >awdaws</h1>
    </v-sheet>
  </v-container>
</template>

<script>
export default {
  name: "LoginView",
  data: () => ({
    valid: false,
    username: '',
    token: '',
    nameRules: [
      v => !!v || 'username este necesar'
    ],
    tokenRules: [
      v => !!v || 'Token este necesar'
    ],
    errorMessages: "Mai incearca! Nu am gasit user sau token! Foloseste numele oficial!",
    errorMessageFlag: false
  }),
  methods: {
    login() {
      console.log(this.username, this.token);
      this.errorMessageFlag = true;
    }
  }
}
</script>

<style scoped>
@media {
  .c {
    display: grid;
    place-content: center;
  }
}
</style>