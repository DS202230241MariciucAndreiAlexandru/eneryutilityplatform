<template>
  <v-container justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn icon v-bind="attrs" v-on="on">
          <v-icon>mdi-update</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">User Update</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-text-field
                  label="username"
                  v-model="updatedUser.username"
                  required/>
            </v-row>
            <v-row>
              <v-container>
                <v-row>
                  Devices
                </v-row>
                <v-row>
                  <v-virtual-scroll
                      class="flex-row"
                      :bench="benched"
                      :items="updatedUser.devices"
                      height="300"
                      item-height="64">

                    <template v-slot:default="{ item, index }">
                      <v-list-item :key="item">
                        <v-list-item-action>
                          <v-btn
                              fab
                              small
                              depressed
                              color="primary"
                          >
                            {{ index + 1 }}
                          </v-btn>
                        </v-list-item-action>

                        <v-list-item-content>
                          <v-list-item-title>
                            {{ item.description }} : {{ item.address }}
                          </v-list-item-title>
                        </v-list-item-content>

                        <v-checkbox v-model="item.checked"/>

                      </v-list-item>

                      <v-divider></v-divider>
                    </template>
                  </v-virtual-scroll>
                </v-row>
              </v-container>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="blue darken-1"
              text
              @click="dialog = false">
            Close
          </v-btn>
          <v-btn
              color="blue darken-1"
              text
              @click="dialog = false">
            Update
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>

import {useAdminStore} from "@/store/useAdminStore";


export default {
  name: "UserUpdateComponent",
  props: {
    user: null
  },
  data: () => ({
    dialog: false,
    updatedUser: {
      username: '',
      devices: []
    }
  }),
  mounted() {
    this.updatedUser.username = this.user.username;

    // fetch devices again!
    const adminStore = useAdminStore();
    adminStore.getDevices();
    console.log(this.user.devices);
    console.log(adminStore.devices);

    this.updatedUser.devices = adminStore.devices.map(device => {
      const checked = this.user.devices.filter(d => d.id === device.id).length > 0;
      return {
        id: device.id,
        address: device.address.name,
        description: device.description,
        checked
      }
    });
  }
}
</script>

<style scoped>

</style>