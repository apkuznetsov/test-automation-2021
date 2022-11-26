<template>
  <v-layout my-2 row>
    <v-col>
      <v-text-field
          id="my-title-tf"
          v-model="title"
          class="my-title-tf"
          placeholder="Введите тематику"
          type="text"/>
    </v-col>
    <v-col>
      <v-text-field
          id="my-question-tf"
          v-model="question"
          class="my-question-tf"
          placeholder="Введите вопрос"
          type="text"/>
    </v-col>
    <v-col>
      <v-text-field
          id="my-answer-tf"
          v-model="answer"
          class="my-answer-tf"
          placeholder="Введите ответ"
          type="text"/>
    </v-col>
    <v-col justify="center">
      <v-btn id="my-save-btn" class="my-save-btn" @click="save">
        Сохранить
      </v-btn>
    </v-col>
  </v-layout>
</template>

<script>
function getIndex(list, id) {
  for (var i = 0; i < list.length; i++) {
    if (list[i].id === id) {
      return i
    }
  }

  return -1
}

export default {
  props: ['quizzes', 'quizAttr'],
  data() {
    return {
      id: '',
      title: '',
      question: '',
      answer: ''
    }
  },
  watch: {
    quizAttr: function (newVal, oldVal) {
      this.id = newVal.id
      this.title = newVal.title
      this.question = newVal.question
      this.answer = newVal.answer
    }
  },
  methods: {
    save() {
      const quiz = {
        title: this.title,
        question: this.question,
        answer: this.answer
      }

      if (this.id) {
        this.$resource('/api/quiz{/id}').update({id: this.id}, quiz).then(result =>
            result.json().then(data => {
              const index = getIndex(this.quizzes, data.id)
              this.quizzes.splice(index, 1, data)
              this.title = ''
              this.question = ''
              this.answer = ''
            })
        );
      } else {
        this.$resource('/api/quiz{/id}').save({}, quiz).then(result =>
            result.json().then(data => {
              this.quizzes.push(data)
              this.title = ''
              this.question = ''
              this.answer = ''
            })
        )
      }
    }
  }
}
</script>

<style>

</style>