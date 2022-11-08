<template>
  <v-card elevation="5">
    <v-card-title>{{ user.username }}</v-card-title>
    <v-card-subtitle>{{ user.role }}</v-card-subtitle>
    <v-card-text v-if="!isHide">
      <v-select
          v-model="select"
          :items="user.devices"
          item-text="description"
          label="Vezi device-urile"
      >
      </v-select>
    </v-card-text>
    <v-divider/>
    <v-card-actions>
      <UserUpdateComponent v-if="!isHide" :user="user"/>
      <v-btn icon v-if="!isHide" @click="deleteUser">
        <v-icon>mdi-delete</v-icon>
      </v-btn>
      <v-btn v-if="isHide" icon @click="$router.push({name: 'login'})">
        <v-icon>mdi-logout</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import UserUpdateComponent from "@/components/UserUpdateComponent";
import {useAdminStore} from "@/store/useAdminStore";

export default {
  name: "UserCard",
  components: {
    UserUpdateComponent
  },
  props: {
    user: null,
    isAdmin: {
      type: Boolean,
      default: false
    },
    isHide: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      select: {
        description: '',
        address: {
          name: ''
        }
      }
    }
  },
  methods: {
    deleteUser() {
      useAdminStore().deleteUser(this.user);
      window.location.reload();
    }
  }
}
</script>

<style scoped>

</style>