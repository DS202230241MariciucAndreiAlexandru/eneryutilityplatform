import {defineStore} from "pinia";
import axios from "@/plugins/myAxios";


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
                localStorage.setItem("jwt", this.jwtToken);

                const userDto = response.data;

                this.role = userDto.role;
                this.username = userDto.username;
                this.isAuthenticated = true;

                localStorage.setItem("user", JSON.stringify(userDto));
            } catch (err) {
                this.isAuthenticated = false;
            }
        }
    }
});