import {defineStore} from "pinia";
import axios from "@/plugins/myAxios";
import roles from "@/utils/roleUtil";

export const useAdminStore = defineStore("adminStore", {
    state: () => ({
        users: [],
        devices: [],
        currentAdmin: JSON.parse(localStorage.getItem('user'))
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
        },
        updateUser(updatedUser) {
            const user = this.users.find(u => u.id === updatedUser.id);
            if (!user) return;

            axios.put("/admin/update-user", {id: updatedUser.id, username: updatedUser.username})
                .then(response => {
                    user.username = updatedUser.username;
                })
                .catch(err => {
                    console.error(err);
                });

            axios.put(`/admin/user/${updatedUser.id}/update-devices`, updatedUser.devices).then(response => {
                const updatedDevices = updatedUser.devices;
                user.devices = user.devices.filter(device => {
                    const d = updatedDevices.find(d => d.id === device.id);
                    if (!d) return true;
                    return d.checked;
                });
            }).catch(err => console.error(err));
        }
    }
});