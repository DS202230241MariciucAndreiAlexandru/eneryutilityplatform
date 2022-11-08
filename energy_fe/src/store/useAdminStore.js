import {defineStore} from "pinia";
import axios from "@/plugins/myAxios";
import roles from "@/utils/roleUtil";

export const useAdminStore = defineStore("adminStore", {
    state: () => ({
        users: [],
        devices: [],
        currentAdmin: JSON.parse(localStorage.getItem('user')),
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
                const response = await axios.get(`/admin/devices`);
                this.devices = response.data;
            } catch (e) {
                console.error(e);
            }
        },
        updateUser(updatedUser) {
            const user = this.users.find(u => u.id === updatedUser.id);
            if (!user) return;

            let r = true;

            axios.put("/admin/update-user", {id: updatedUser.id, username: updatedUser.username})
                .then(response => {
                    if (user.id === this.currentAdmin.id) {
                        this.currentAdmin.username = user.username;
                    }
                    user.username = updatedUser.username;
                    r = r && response.data;
                })
                .catch(err => {
                    console.error(err);
                    r = false;
                });

            axios.put(`/admin/user/${updatedUser.id}/update-devices`, updatedUser.devices).then(response => {
                const updatedDevices = updatedUser.devices;
                user.devices = user.devices.filter(device => {
                    const d = updatedDevices.find(d => d.id === device.id);
                    if (!d) return true;
                    return d.checked;
                });
                console.log(user.devices);
                r = r && response.data;
            }).catch(err => {
                console.error(err);
                r = false;
            });

            return r;
        },
        deleteUser(user) {
            axios.delete(`/admin/delete-user/${user.id}`)
                .then(response => {
                    if (response.data) {
                        this.users.splice(this.users.indexOf(user), 1);
                    }
                })
                .catch(err => console.error(err));
        },
        deleteDevice(device) {
            const i = this.devices.indexOf(device);
            if (i >= 0)
                this.devices.splice(i, 1);
        }
    }
});