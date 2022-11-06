<template>
  <v-card elevation="5">
    <v-card-title>{{ device.description }}</v-card-title>
    <v-card-subtitle>{{ device.address.name }}</v-card-subtitle>
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
      <ChartComponent :deviceId="device.id" :date="picker"/>
    </v-card-actions>
  </v-card>
</template>
<script>
import ChartComponent from "@/components/ChartComponent";

export default {
  name: "DeviceCard",
  components: {ChartComponent},
  props: {
    device: null
  },
  data: () => ({
    picker: new Date().toISOString().substr(0, 10)
  }),
  methods: {}
}
</script>

<style scoped>

</style>