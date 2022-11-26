<template>
  <v-layout row class="my-row">
    <v-col class="my-quiz-title">{{ quiz.quiz.title }}</v-col>
    <v-col class="my-quiz-question">{{ quiz.quiz.question }}</v-col>
    <v-col class="my-quiz-answer">{{ quiz.quiz.answer }}</v-col>
    <v-col class="my-quiz-completed-at">{{ quiz.completedAt | formatDate }}</v-col>
    <v-col class="my-quiz-right">{{ quiz.right ? 'правильно' : 'неправильно'}}</v-col>
  </v-layout>
</template>

<script>
export default {
  props: ['quiz', 'quizzes']
}
</script>

<style>

</style>