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
          <LineChartGenerator
              :chart-options="chartOptions"
              :chart-data="chartData"
              :chart-id="chartId"
              :dataset-id-key="datasetIdKey"
              :plugins="plugins"
              :css-classes="cssClasses"
              :styles="styles"
              :width="width"
              :height="height"
          />
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
import {Line as LineChartGenerator} from 'vue-chartjs/legacy'

import {CategoryScale, Chart as ChartJS, Legend, LinearScale, LineElement, PointElement, Title, Tooltip} from 'chart.js'

ChartJS.register(
    Title,
    Tooltip,
    Legend,
    LineElement,
    LinearScale,
    CategoryScale,
    PointElement
)

export default {
  name: "ChartComponent",
  components: {
    LineChartGenerator
  },
  props: {
    deviceId: Number,
    date: String,
    chartId: {
      type: String,
      default: 'line-chart'
    },
    datasetIdKey: {
      type: String,
      default: 'label'
    },
    width: {
      type: Number,
      default: 600
    },
    height: {
      type: Number,
      default: 600
    },
    cssClasses: {
      default: '',
      type: String
    },
    styles: {
      type: Object,
      default: () => {
      }
    },
    plugins: {
      type: Array,
      default: () => []
    }
  },
  data: () => ({
    dialog: false,
    chartData: {
      labels: [],
      datasets: [
        {
          label: 'E[kWh]',
          backgroundColor: '#f87979',
          data: []
        }
      ]
    },
    chartOptions: {
      responsive: true,
      maintainAspectRatio: false
    }
  }),
  watch: {
    date(newDate, _) {
      this.updateChar(newDate);
    }
  },
  mounted() {
    this.updateChar(this.date);
  },
  methods: {
    updateChar(d) {
      axios.get(`/user/device/${this.deviceId}/consumption?date=${d}`)
          .then(response => {
            const ec = response.data;
            this.chartData.datasets[0].data = ec.map(e => e.energy);
            this.chartData.labels = ec.map(e => e.timeStamp);
          })
          .catch(error => console.error(error));
    }
  }
}
</script>

<style scoped>

</style>