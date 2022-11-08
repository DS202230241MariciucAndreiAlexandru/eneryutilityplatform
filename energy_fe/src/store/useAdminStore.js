import {defineStore} from "pinia";
import axios from "@/plugins/myAxios";
import roles from "@/utils/roleUtil";

export const useAdminStore = defineStore("adminStore", {
    state: () => ({
        users: [],
        devices: [],
        currentAdmin: JSON.parse(localStorage.getItem('user')),
        updatedUser: {
            id: null,
            username: '',
            devices: []
        }
    }),
    getters: {
        simpleUsers() {
            return this.users.filter(user => roles.USER === user.role);
        }
    },
    actions: {
        initUpdatedUser(user) {
            this.updatedUser.id = user.id;
            this.updatedUser.username = user.username;
            // {
            //     id: id,
            //     username: username,
            //     devices: [{id: 19, description: "Huawei", address: {id: 15, name: "Baritiu"}}]
            // };

            this.updatedUser.devices = this.devices.map(device => {
                const checked = user.devices.filter(d => d.id === device.id).length > 0;
                return {
                    id: device.id,
                    address: device.address,
                    description: device.description,
                    checked
                }
            });
        },
        async getUsers() {
            try {
                const response = await axios.get("/admin/users");
                this.users = response.data;
            } catch (e) {
                console.error(e);
            }
        },
        async getDevices(id) {
            try {
                const response = await axios.get(`/admin/${id}/devices`);
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
                r = r && response.data;
            }).catch(err => {
                console.error(err);
                r = false;
            });

            return r;
        }
    }
});