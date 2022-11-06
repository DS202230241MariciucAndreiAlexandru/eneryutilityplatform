import {defineStore} from "pinia";
import axios from "@/plugins/myAxios";
import roles from "@/utils/roleUtil";

export const useAdminStore = defineStore("adminStore", {
    state: () => ({
        users: [],
        devices:[],
        currentAdmin:  JSON.parse(localStorage.getItem('user'))
    }),
    getters: {
        simpleUsers() {
            return this.users.filter(user => roles.USER === user.role);
        }
    },
    actions: {
        async getUsers() {
            try {
                const response = await axios.get("/admin/users");
                this.users = response.data;
            } catch (e) {
                console.error(e);
            }
        },
        async getDevices() {
            try {
                const response = await axios.get("/admin/devices");
                this.devices = response.data;
            } catch (e) {
                console.error(e);
            }
        }
    }
});