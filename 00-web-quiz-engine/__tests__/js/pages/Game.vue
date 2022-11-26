<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title>Викторина</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn href="/">
        Главная
      </v-btn>
    </v-app-bar>
    <v-main>
      <v-container>
        <page-list
            :currPageNumber="currPageNumber"
            :currPage="currPage"/>
      </v-container>
    </v-main>

  </v-app>
</template>

<script>
import PageList from '../components/game/PageList.vue'

export default {
  components: {
    PageList
  },
  data() {
    return {
      currPageNumber: 1,
      currPage: {
        title: "Английский",
        question: "price"
      }
    }
  }
}
</script>

<style>

</style>