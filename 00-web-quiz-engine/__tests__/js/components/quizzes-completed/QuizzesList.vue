<template>
  <v-layout
      align-space-around
      column
      justify-start
      my-10>
    <quiz-row v-for="quiz in quizzes"
              :key="quiz.id"
              :quiz="quiz"
              :quizzes="quizzes"/>
  </v-layout>
</template>

<script>
import QuizRow from './QuizRow.vue'

export default {
  props: ['quizzes'],
  components: {
    QuizRow
  },
  data() {
    return {
      quiz: null
    }
  }
}
</script>

<style>

</style>