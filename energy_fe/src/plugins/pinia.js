import Vue from "vue";
import {createPinia, PiniaVuePlugin} from "pinia";
import VueCompositionAPI from "@vue/composition-api";

Vue.use(PiniaVuePlugin)
const pinia = createPinia()
Vue.use(VueCompositionAPI)

export default pinia;