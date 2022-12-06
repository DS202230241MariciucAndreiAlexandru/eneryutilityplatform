<template>
  <v-container justify="center">
    <v-dialog
        v-model="dialog"
        max-width="600px"
    >
      <template v-slot:activator="{on, attrs}">
        <v-btn icon v-bind="attrs" v-on="on">
          <v-icon>mdi-note-plus</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">Device Update</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-text-field
                  label="description"
                  v-model="deviceUpdated.description"
                  required/>
            </v-row>
            <v-row>
              <v-text-field
                  label="address"
                  v-model="deviceUpdated.address.name"
                  required/>
            </v-row>
            <v-row>
              <v-text-field
                  type="number"
                  label="Max Consumption"
                  v-model="deviceUpdated.maxEnergyConsumption"
                  required/>
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
              @click="updateHandler">
            Update
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>

import axios from "@/plugins/myAxios";
import {useAdminStore} from "@/store/useAdminStore";

export default {
  name: "DeviceUpdateComponent",
  props: ["device"],
  data: () => ({
    dialog: false,
    deviceUpdated: {
      description: '',
      address: {
        name: ''
      },
      maxEnergyConsumption: 0
    }
  }),
  mounted() {
    if (this.device !== null)
      this.deviceUpdated = this.device;
  },
  methods: {
    updateHandler() {
      this.dialog = false;
      axios.post("/admin/add-device", this.deviceUpdated)
          .then(response => {
            if(!this.deviceUpdated.id)
              useAdminStore().devices.push(response.data);
          })
          .catch(err => console.error(err));
    }
  },
}
</script>

<style scoped>

</style>