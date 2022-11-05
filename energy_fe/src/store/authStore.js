import {defineStore} from "pinia";
import AuthService from "@/services/AuthService";


export const authStore = defineStore("authStore", {
    state: () => ({
        username: "",
        jwtToken: "",
        roles: [],
        isAuthenticated: false
    }),
    getters: {},
    actions: {
        login(loginForm) {
            AuthService.login(loginForm)
                .then(response => {

                }).catch(error => {
                    console.log(error);
            });
        }
    }
});