<template>
  <div>
    <v-alert
        class="text-center"
        :value="!!alert['message']"
        :type="type"
        dismissible
        outlined
        border="left"
        @input="closeAlert"
    >{{ alert['message'] }}</v-alert
    >
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

export default {
  name: "GlobalAlert",
  computed: {
    ...mapState("pgtalert", ["alert"])
  },

  methods: {
    ...mapMutations("pgtalert", ["setAlert"]),
    closeAlert(val) {
      this.setAlert({});
    }
  }
}
</script>

<style scoped>

</style>