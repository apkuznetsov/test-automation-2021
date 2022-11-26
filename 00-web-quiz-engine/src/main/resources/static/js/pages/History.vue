<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title>История</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn href="/">
        Главная
      </v-btn>
    </v-app-bar>
    <v-main>
      <v-container>
        <quizzes-list :quizzes="quizzes"/>
      </v-container>
    </v-main>

  </v-app>
</template>

<script>
import QuizzesList from 'components/quizzes-completed/QuizzesList.vue'

export default {
  components: {
    QuizzesList
  },
  data() {
    return {
      quizzes: frontendData.quizzes
    }
  }
}
</script>

<style>

</style>