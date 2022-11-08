<template>
  <v-container justify="center">
    <!--    <GlobalAlert/>-->
    <v-btn icon @click.stop="load">
      <v-icon>mdi-update</v-icon>
    </v-btn>
    <v-dialog
        v-model="dialog"
        max-width="600px"
    >
      <v-card>
        <v-card-title>
          <span class="text-h5">User Update</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-text-field
                  label="username"
                  v-model="adminStore.updatedUser.username"
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
                      :items="adminStore.updatedUser.devices"
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
                            {{ item.description }} : {{ item.address.name }}
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
              @click="updateHandler">
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
  components: {
    // GlobalAlert: () => import("@/components/GlobalAlert")
  },
  props: {
    user: null,
    updatedUser: null
  },
  setup() {
    const adminStore = useAdminStore();

    return {adminStore};
  },
  data: () => ({
    dialog: false,
  }),
  mounted() {
    // this.load(false);
    this.adminStore.getDevices(this.user.id);
    this.adminStore.initUpdatedUser(this.user);
  },
  methods: {
    load(v) {
      console.log("sunt in load");

      // fetch devices again!
      this.adminStore.getDevices(this.user.id);
      this.adminStore.initUpdatedUser(this.user);
      if(v)
        this.dialog = v;
      else
        this.dialog = true;
    },
    updateHandler() {
      this.dialog = false;
      const adminStore = useAdminStore();
      adminStore.updateUser(this.updatedUser);
    }
  }
}
</script>

<style scoped>

</style>