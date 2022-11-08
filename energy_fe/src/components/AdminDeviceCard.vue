<template>
  <v-card elevation="5">
    <v-card-title>{{ device.description }}</v-card-title>
    <v-card-subtitle>{{ device.address.name }}</v-card-subtitle>
    <v-card-text>
      <v-select
          :items="adminStore.simpleUsers"
          item-text="username"
          label="User"
          v-model="user"
      >
      </v-select>
    </v-card-text>
    <v-divider/>
    <v-card-actions>
      <!--      <UserUpdateComponent :user="user"/>-->
      <v-btn icon @click="deleteDevice">
        <v-icon>mdi-delete</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import axios from "@/plugins/myAxios";
import {useAdminStore} from "@/store/useAdminStore";

export default {
  name: "AdminDeviceCard",
  setup() {
    const adminStore = useAdminStore();

    return {adminStore}
  },
  props: {
    device: null,
  },
  data: () => ({
    user: null
  }),
  watch: {
    user(_) {
      const u = this.adminStore.users.find(u => u.username === this.user);
      if (u) {
        u.devices.push(this.device);
        axios.put(`/admin/user/${u.id}/device/${this.device.id}`)
            .then(response => response.data)
            .catch(err => console.log(err));
      }
    }
  },
  mounted() {
    const id = this.device.id;
    axios.get(`/admin/user/${id}`)
        .then(response => {
          if (response.data) {
            this.user = response.data.username;
          }
        })
        .catch(err => console.error(err));
  },
  methods: {
    async deleteDevice() {
      await axios.delete(`/admin/delete-device/${this.device.id}`);
      this.adminStore.deleteDevice(this.device);
    }
  }
}
</script>

<style scoped>

</style>