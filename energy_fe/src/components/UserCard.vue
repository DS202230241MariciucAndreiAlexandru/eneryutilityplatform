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
      <AdminChatCard v-if="!isHide" :admin="admin" :user="user"/>
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
import stompClient from "@/plugins/socket";
import AdminChatCard from "@/components/AdminChatCard.vue";

export default {
  name: "UserCard",
  components: {
    AdminChatCard,
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
    },
    admin: null
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
      // window.location.reload();
    }
  },
  mounted() {

  }
}
</script>

<style scoped>

</style>