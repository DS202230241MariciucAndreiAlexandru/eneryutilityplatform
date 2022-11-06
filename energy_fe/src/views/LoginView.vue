<template>
  <v-container class="center-login fill-height">
    <v-alert type="success"
             v-if="isFromRegister">
      {{ registerMsg }}
    </v-alert>
    <v-row class="center-login pa-md-11">
      <h1>Login</h1>
    </v-row>
    <v-row>
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
            <v-row class="mt-5">
              <v-btn x-large dark @click="login">
                Login
              </v-btn>
            </v-row>
          </v-container>
        </v-form>
      </v-sheet>
    </v-row>
  </v-container>
</template>

<script>

import {useAuthStore} from "@/store/useAuthStore";
import roles from "@/utils/roleUtil";

export default {
  name: "LoginView",
  props: ["afterRegister"],
  setup() {
    const authStore = useAuthStore();
    return {
      authStore: authStore
    };
  },
  data: () => ({
    isFromRegister: false,
    registerMsg: '',
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
    async login() {
      await this.authStore.login({username: this.username, password: this.password});

      if (!this.authStore.isAuthenticated) {
        this.errorMessageFlag = true;
      } else {
        this.errorMessageFlag = false;
        if (this.authStore.role === roles.ADMIN) {
          await this.$router.push({name: roles.ADMIN});
        } else if (this.authStore.role === roles.USER) {
          await this.$router.push({name: roles.USER});
        }
      }
    }
  },
  mounted() {
    localStorage.clear();
    if (this.afterRegister) {
      const username = this.afterRegister.username;
      console.log(username);
      this.registerMsg = `Te-ai inregistrat cu success ${username}. Logheaza-te din nou pentru a intra in cont!`;
      this.isFromRegister = true;
    }

    setTimeout(() => this.isFromRegister = false, 5000);
  }
}
</script>

<style scoped>
.center-login {
  display: grid;
  place-content: center;
}
</style>