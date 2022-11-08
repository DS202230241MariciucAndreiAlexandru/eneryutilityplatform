<template>
  <v-card elevation="5">
    <v-card-title>{{ user.username }}</v-card-title>
    <v-card-subtitle>{{ user.role }}</v-card-subtitle>
    <v-card-text v-if="!isHide">
      <v-select
          :items="user.devices"
          label="Vezi device-urile"
      >
        <template v-slot:selection="{item}">
          {{ item.description }}:{{ item.address.name }}
        </template>
        <template v-slot:item="{item}">
          {{ item.description }}:{{ item.address.name }}
        </template>
      </v-select>
    </v-card-text>
    <v-divider/>
    <v-card-actions>
      <UserUpdateComponent :user="user"/>
      <v-btn icon v-if="!isHide">
        <v-icon>mdi-delete</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import UserUpdateComponent from "@/components/UserUpdateComponent";

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
  }
}
</script>

<style scoped>

</style>