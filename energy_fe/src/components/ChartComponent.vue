<template>
  <v-container justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn icon v-bind="attrs" v-on="on">
          <v-icon>mdi-chart-line</v-icon>
        </v-btn>
      </template>
      <v-card class="mx-auto"
              color="grey lighten-4">
        <v-card-title>
          <span class="text-h5">Consumul din data: {{ date }} </span>
        </v-card-title>
        <v-card-text>
          <v-sparkline
              :value="value"
              :gradient="gradient"
              :smooth="radius || false"
              :padding="padding"
              :line-width="width"
              :stroke-linecap="lineCap"
              :gradient-direction="gradientDirection"
              :fill="fill"
              :type="type"
              :auto-line-width="autoLineWidth"
              :show-labels="true"
              :labels="labels"
              auto-draw>

          </v-sparkline>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="blue darken-1"
              text
              @click="dialog = false">
            Close
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import axios from "@/plugins/myAxios";

const gradients = [
  ['#222'],
  ['#42b3f4'],
  ['red', 'orange', 'yellow'],
  ['purple', 'violet'],
  ['#00c6ff', '#F0F', '#FF0'],
  ['#f72047', '#ffd200', '#1feaea'],
];

export default {
  name: "ChartComponent",
  props: ["deviceId", "date"],
  data: () => ({
    dialog: false,
    width: 1,
    radius: 10,
    padding: 8,
    lineCap: 'round',
    gradient: gradients[5],
    labels: [],
    value: [],
    gradientDirection: 'top',
    gradients,
    fill: false,
    type: 'trend',
    autoLineWidth: false,
  }),
  watch: {
    date(newDate, oldDate) {
      axios.get(`/user/device/${this.deviceId}/consumption?date=${newDate}`)
          .then(response => {
            const ec = response.data;
            this.value = ec.map(e => e.energy * 10);
            this.labels = ec.map(e => e.timeStamp);
            console.log(this.labels);
          })
          .catch(error => console.error(error));
    }
  },
  mounted() {
    axios.get(`/user/device/${this.deviceId}/consumption?date=${this.date}`)
        .then(response => {
          const ec = response.data;
          this.value = ec.map(e => e.energy);
          this.labels = ec.map(e => e.timeStamp);
          console.log(this.labels);
        })
        .catch(error => console.error(error));
  }
}
</script>

<style scoped>

</style>