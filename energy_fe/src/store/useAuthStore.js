import {defineStore} from "pinia";
import axios from "axios";


export const useAuthStore = defineStore("authStore", {
    state: () => ({
        username: "",
        jwtToken: "",
        role: "",
        isAuthenticated: false
    }),
    actions: {
        async login(loginForm) {
            try {
                const response = await axios.post("/login", loginForm);

                this.jwtToken = response.headers['authorization'];
                axios.defaults.headers.common['Authorization'] = `Bearer ${this.jwtToken}`;

                const userDto = response.data;

                this.role = userDto.role;
                this.username = userDto.username;
                this.isAuthenticated = true;
                console.log(`is auth: ${this.isAuthenticated}`);
            } catch (err) {
                this.isAuthenticated = false;
            }
        }
    }
});