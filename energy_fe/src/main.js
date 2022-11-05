import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import pinia from './plugins/pinia'
import router from './router/index'
import axios from 'axios';

Vue.config.silent = true

Vue.config.productionTip = false

axios.defaults.baseURL = "http://localhost:8090";

new Vue({
    vuetify,
    router,
    pinia,
    render: h => h(App)
}).$mount('#app')

