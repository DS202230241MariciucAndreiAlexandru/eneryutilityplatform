<template>
  <v-container>
    <h1>Heii, {{ adminStore.currentAdmin.username }}</h1>
    <v-tabs
        color="white accent-4"
        right
        dark
        v-model="active"
    >
      <v-tab v-for="(tab, index) in tabs" :key="index" @click="reloadTab(index)">
        {{ tab.name }}
      </v-tab>

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
                v-for="(device, index) in adminStore.devices"
                :key="index"
                cols="12"
                md="4"
            >
              <AdminDeviceCard :device="device"/>
            </v-col>
            <v-col
                cols="12"
                md="4">
              <v-card>
                <v-card-title>
                  <span class="text-h5">Create new device</span>
                </v-card-title>
                <v-card-actions>
                  <DeviceUpdateComponent :device="null"/>
                </v-card-actions>
              </v-card>
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
import AdminDeviceCard from "@/components/AdminDeviceCard";
import DeviceUpdateComponent from "@/components/DeviceUpdateComponent";

export default {
  name: "AdminView",
  components: {
    UserCard,
    AdminDeviceCard,
    DeviceUpdateComponent
  },
  setup() {
    const authStore = useAuthStore();
    const adminStore = useAdminStore();

    adminStore.getUsers();
    adminStore.getDevices();

    return {
      authStore,
      adminStore
    }
  },
  data: () => ({
    active: 1,
    tabs: [
      {id: 1, name: 'Profile'},
      {id: 2, name: 'Users'},
      {id: 3, name: 'Devices'}
    ],
  }),
  methods: {
    reloadTab(a) {
      if (a === 1)
        window.location.reload();
      this.active = a;
    }
  }
}
</script>

<style scoped>

</style>