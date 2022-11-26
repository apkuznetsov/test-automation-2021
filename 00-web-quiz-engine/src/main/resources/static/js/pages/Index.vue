<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title>Главная</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn href="/history">
        История
      </v-btn>
      &nbsp;&nbsp;&nbsp;
      <v-btn href="/game">
        Викторина
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
import QuizzesList from 'components/quizzes/QuizzesList.vue'

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