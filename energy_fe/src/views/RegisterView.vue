<template>
  <v-container class="center-login fill-height">
    <v-row class="center-login pa-md-11">
      <h1>Register</h1>
    </v-row>
    <v-row>
      <v-sheet outlined rounded elevation="10" min-width="700">
        <v-form v-model="valid" class="pa-16" v-on:keyup.enter="register">
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
              <v-select
                  :items="roles"
                  label="Role"
                  v-model="selectedRole"
              ></v-select>
            </v-row>
            <v-row class="mt-5">
              <v-btn x-large dark @click="register">
                Register
              </v-btn>
            </v-row>
          </v-container>
        </v-form>
      </v-sheet>
    </v-row>
  </v-container>
</template>

<script>

import roles from "@/utils/roleUtil";
import axios from "@/plugins/myAxios";

export default {
  name: "RegisterView",
  data: () => ({
    valid: false,
    username: '',
    password: '',
    selectedRole: '',
    roles: [roles.USER, roles.ADMIN],
    nameRules: [
      v => !!v || 'username este necesar'
    ],
    passwordRules: [
      v => !!v || 'password este necesar'
    ],
    errorMessages: "Incearca cu un alt username!",
    errorMessageFlag: false
  }),
  methods: {
    register() {
      const registerForm = {username: this.username, password: this.password, role: this.selectedRole};
      axios.post("/register", registerForm)
          .then(response => {
            this.$router.push({name: "login", params: {afterRegister: response.data}})
          })
          .catch(_ => {
            this.errorMessageFlag = true;
          });
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