<template>
  <v-container class="center-login fill-height">
    <v-sheet outlined rounded elevation="10" min-width="700">
      <v-form v-model="valid" class="pa-16" v-on:keyup.enter="login">
        <v-container class="flex-column">
          <v-alert
              v-if="errorMessageFlag"
              dark>
            {{ errorMessages }}
          </v-alert>
          <v-row class="px-1">
            <v-text-field v-model="username" :rules="nameRules" label="username" required/>
          </v-row>
          <v-row>
            <v-text-field type="password" v-model="password" :rules="passwordRules" label="passowrd" required/>
          </v-row>
          <v-row>
            <v-btn x-large dark @click="login">
              Login
            </v-btn>
          </v-row>
        </v-container>
      </v-form>
    </v-sheet>
  </v-container>
</template>

<script>

import {authStore} from "@/store/authStore";

export default {
  name: "LoginView",
  setup() {
  },
  data: () => ({
    valid: false,
    username: '',
    password: '',
    nameRules: [
      v => !!v || 'username este necesar'
    ],
    passwordRules: [
      v => !!v || 'password este necesar'
    ],
    errorMessages: "Mai încearcă! Nu am găsit user sau password",
    errorMessageFlag: false
  }),
  methods: {
    login() {
      authStore().login({username: this.username, password: this.password});
    }
  }
}
</script>

<style scoped>
.center-login {
  display: grid;
  place-content: center;
}
</style>