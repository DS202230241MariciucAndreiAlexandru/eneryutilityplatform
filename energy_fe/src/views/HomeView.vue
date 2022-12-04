<template>
  <v-container>
    <h1>AICI AR TREBUI SA FIE O PAGINA DE PREZENTARE</h1>
    <v-btn @click="$router.push({name: 'login'})">Login</v-btn>
    <v-btn @click="$router.push({name: 'register'})">Register</v-btn>
  </v-container>
</template>

<script>
// import HelloWorld from '@/components/HelloWorld.vue'
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

export default {
  name: 'HomeView',
  components: {
    // HelloWorld
  },
  mounted() {
    var soc = new SockJS("http://localhost:8090" + "/vue-socket-endpoint");
    var stompClient = Stomp.over(soc);

    stompClient.connect({}, () => {
      stompClient.subscribe("/warns/alert", message => {
        console.log(message);
        alert(message);
      });
    });
  }
}
</script>
