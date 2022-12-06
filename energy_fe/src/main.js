import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import pinia from './plugins/pinia'
import router from './router/index'
import stompClient from "@/plugins/socket";

Vue.config.silent = true

Vue.config.productionTip = false

new Vue({
    vuetify,
    router,
    pinia,
    // stompClient,
    render: h => h(App)
}).$mount('#app')

