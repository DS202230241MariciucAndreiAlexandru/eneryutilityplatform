import {defineStore} from "pinia";
import axios from "@/plugins/myAxios";

export const useUserStore = defineStore("userStore", {
        state: () => ({
            user: JSON.parse(localStorage.getItem('user')),
            devices: []
        }),
        actions: {
            async getDevices() {
                try {
                    const response = await axios.get(`/user/${this.user.id}/devices`);
                    this.devices = response.data;
                } catch (e) {
                    console.error(e);
                }
            },
            getConsumption(id, date) {

            }
        }
    }
);