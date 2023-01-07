<template>
  <v-container>
    <h1>Heii, {{ userStore.user.username }}</h1>
    <v-tabs
        color="white accent-4"
        right
        dark
    >
      <v-tab>Profile</v-tab>
      <v-tab>Devices</v-tab>
      <v-tab-item>
        <UserCard :user="userStore.user" :is-hide="true"/>
      </v-tab-item>
      <v-tab-item>
        <v-container fluid>
          <v-row>
            <v-col
                v-for="(device, index) in userStore.devices"
                :key="index"
                cols="12"
                md="4"
            >
              <DeviceCard :device="device"/>
            </v-col>
          </v-row>
        </v-container>
      </v-tab-item>
    </v-tabs>
    <ChatCard :user="userStore.user"/>
  </v-container>
</template>

<script>
import {useUserStore} from "@/store/useUserStore";
import UserCard from "@/components/UserCard";
import DeviceCard from "@/components/DeviceCard";
import ChatCard from "@/components/UserChatCard.vue";

export default {
  name: "UserView",
  components: {ChatCard, UserCard, DeviceCard},
  setup() {
    const userStore = useUserStore();
    userStore.getDevices();

    return {userStore};
  },
  data: () => ({
    // stompClient: null
  }),
  mounted() {

  }
}
</script>

<style scoped>

</style>