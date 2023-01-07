<template>
  <v-container>
    <h1>AICI AR TREBUI SA FIE O PAGINA DE PREZENTARE</h1>
    <v-btn @click="$router.push({name: 'login'})">Login</v-btn>
    <v-btn @click="$router.push({name: 'register'})">Register</v-btn>
  </v-container>
</template>

<script>
// import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    // HelloWorld
  },
  mounted() {
  }
}
</script>
