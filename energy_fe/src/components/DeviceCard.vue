<template>
  <v-card elevation="5">
    <v-card-title>{{ device.description }}</v-card-title>
    <v-card-subtitle>{{ device.address.name }}[{{ device.id }}]</v-card-subtitle>
    <v-card-subtitle>Max consumption: {{ device.maxEnergyConsumption }}</v-card-subtitle>
    <v-card-subtitle v-if="lastEnergy">
      <v-progress-circular
          indeterminate
          color="green"
          v-if="lastEnergy < device.maxEnergyConsumption"
          size="20"
      ></v-progress-circular>
      <v-progress-circular
          indeterminate
          color="red"
          v-if="lastEnergy >= device.maxEnergyConsumption"
      ></v-progress-circular>
      last received : {{ lastEnergy }}
    </v-card-subtitle>
    <v-card-text>
      <v-expansion-panels>
        <v-expansion-panel>
          <v-expansion-panel-header>Alege o zi</v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-date-picker dark v-model="picker" autofocus :full-width="true"/>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-card-text>
    <v-divider/>
    <v-card-actions>
      <ChartComponent :deviceId="device.id" :date="picker" ref="chart"/>
    </v-card-actions>
  </v-card>
</template>
<script>
import ChartComponent from "@/components/ChartComponent";
import * as SockJS from "sockjs-client";
import axios from "@/plugins/myAxios";
import * as Stomp from "stompjs";

export default {
  name: "DeviceCard",
  components: {ChartComponent},
  props: {
    device: null,
  },
  data: () => ({
    picker: new Date().toISOString().substr(0, 10),
    lastEnergy: null
  }),
  mounted() {
    const soc = new SockJS(axios.defaults.baseURL + "/vue-socket-endpoint");
    const stompClient = Stomp.over(soc);

    stompClient.connect({}, () => {
      stompClient.subscribe(`/warns/alert/${this.device.id}`, message => {
        this.lastEnergy = +(message.body);
        this.$refs.chart.updateCharRef();
      });
    });
  },
  methods: {}
}
</script>

<style scoped>

</style>