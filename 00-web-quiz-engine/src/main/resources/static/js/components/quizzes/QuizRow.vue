<template>
  <v-layout row class="my-row">
    <v-col class="my-quiz-title">{{ quiz.title }}</v-col>
    <v-col class="my-quiz-question">{{ quiz.question }}</v-col>
    <v-col class="my-quiz-answer">{{ quiz.answer }}</v-col>

    <v-col>
        <span style="position: absolute; right: 0">
        <v-btn rounded small text @click="edit" class="my-edit-btn">Изменить</v-btn>
        <v-btn icon @click="del" class="my-del-btn"><v-icon>delete</v-icon></v-btn>
        </span>
    </v-col>
  </v-layout>
</template>

<script>
export default {
  props: ['quiz', 'editQuiz', 'deleteQuiz', 'quizzes'],

  methods: {
    edit() {
      this.editQuiz(this.quiz)
    },
    del() {
      this.deleteQuiz(this.quiz)
    }
  }
}
</script>

<style>

</style>