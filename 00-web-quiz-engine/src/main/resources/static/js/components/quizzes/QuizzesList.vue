<template>
  <v-layout align-space-around column justify-start>
    <quiz-form :quizAttr="quiz" :quizzes="quizzes"/>
    <quiz-row v-for="quiz in sortedQuizzes"
              :key="quiz.id"
              :deleteQuiz="deleteQuiz"
              :editQuiz="editQuiz"
              :quiz="quiz"
              :quizzes="quizzes"/>
  </v-layout>
</template>

<script>
import QuizRow from 'components/quizzes/QuizRow.vue'
import QuizForm from 'components/quizzes/QuizForm.vue'

export default {
  props: ['quizzes'],
  components: {
    QuizRow,
    QuizForm
  },
  data() {
    return {
      quiz: null
    }
  },
  computed: {
    sortedQuizzes() {
      return this.quizzes.sort((a, b) => -(a.id - b.id))
    }
  },
  methods: {
    editQuiz(quiz) {
      this.quiz = quiz
    },
    deleteQuiz(quiz) {
      this.$resource('/api/quiz{/id}').remove({id: quiz.id}).then(result => {
        if (result.ok) {
          this.quizzes.splice(this.quizzes.indexOf(quiz), 1)
        }
      })
    }
  }
}
</script>

<style>

</style>