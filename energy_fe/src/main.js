import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router/index'
import {createPinia, PiniaVuePlugin} from 'pinia'
import VueCompositionAPI from '@vue/composition-api'
import roles from './utils/roleUtil'
import axios from 'axios';

Vue.use(PiniaVuePlugin)
const pinia = createPinia()
Vue.use(VueCompositionAPI)

Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
    if (roles.ANONYMOUS === to.meta.role) {
        next();
    } else {
        //aici o sa testez cu rolul userului curent
        next({path: "/login"});
    }
});

axios.defaults.baseURL = "http://localhost:8090";

new Vue({
    vuetify,
    router,
    pinia,
    render: h => h(App)
}).$mount('#app')
