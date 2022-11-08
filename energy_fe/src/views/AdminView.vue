<template>
  <v-container>
    <h1>Heii, {{ adminStore.currentAdmin.username }}</h1>
    <v-tabs
        color="white accent-4"
        right
        dark
    >
      <v-tab>Profile</v-tab>
      <v-tab>Users</v-tab>
      <v-tab>Devices</v-tab>
      <v-tab-item>
        <UserCard :user="adminStore.currentAdmin" :is-hide="true"/>
      </v-tab-item>
      <v-tab-item>
        <v-container fluid>
          <v-row>
            <v-col
                v-for="(user, index) in adminStore.simpleUsers"
                :key="index"
                cols="12"
                md="4"
            >
              <UserCard :user="user"/>
            </v-col>
          </v-row>
        </v-container>
      </v-tab-item>
      <v-tab-item>
        <v-container fluid>
          <v-row>
            <v-col
                v-for="(user, index) in adminStore.users"
                :key="index"
                cols="12"
                md="4"
            >
              {{ user.username }} awdaw <br>
              {{ user.role }} <br>
            </v-col>
          </v-row>
        </v-container>
      </v-tab-item>
    </v-tabs>
  </v-container>
</template>

<script>

import {useAuthStore} from "@/store/useAuthStore";
import {useAdminStore} from "@/store/useAdminStore";
import UserCard from "@/components/UserCard";

export default {
  name: "AdminView",
  components: {UserCard},
  setup() {
    const authStore = useAuthStore();
    const adminStore = useAdminStore();

    adminStore.getUsers();

    return {
      authStore,
      adminStore
    }
  }
}
</script>

<style scoped>

</style>