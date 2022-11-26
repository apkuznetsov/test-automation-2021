<template>
  <v-card class="mx-auto"
          max-width="344">
    <v-card-text>
      <div class="my-quiz-title">{{ currPage.title }}</div>
      <p class="text-h4 text--primary my-quiz-question">
        {{ currPage.question }}
      </p>
      <div class="text--primary">
        <v-btn
            :disabled="disableBtn"
            @click="getAnswer">
          {{ btnText }}
        </v-btn>
      </div>
    </v-card-text>
    <v-card-actions>
      <v-btn
          color="green accent-4"
          text
          @click="rightAnswer"
          class="my-right-btn"
      >
        Правильный
      </v-btn>
      <v-btn
          color="red accent-4"
          text
          @click="wrongAnswer"
          class="my-wrong-btn"
      >
        Неправильный
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: ['currPageNumber', 'currPage'],
  data() {
    return {
      btnText: 'Получить ответ',
      disableBtn: false
    }
  },
  methods: {
    rightAnswer() {
      this.$resource('api/quiz/{id}/mark/right').get({id: this.currPage.id}).then(result => {
            if (result.status === 200) {
              console.log('ok')
            } else {
              console.log('not ok')
            }
          }
      )

      if (this.currPageNumber !== -1) {
        this.disableBtn = false
        this.btnText = 'Получить ответ'

        this.currPageNumber = this.currPageNumber + 1;
        this.$resource('/api/quizzes/page/{page}').get({page: this.currPageNumber}).then(result => {
              if (result.status === 200) {
                result.json().then(data =>
                    this.currPage = data
                )
              } else {
                this.currPageNumber = -1
              }
            }
        )
      } else {
        window.location.href = '/history';
      }
    },
    wrongAnswer() {
      this.$resource('api/quiz/{id}/mark/wrong').get({id: this.currPage.id}).then(result => {
            if (result.status === 200) {
              console.log('ok')
            } else {
              console.log('not ok')
            }
          }
      )

      if (this.currPageNumber !== -1) {
        this.disableBtn = false
        this.btnText = 'Получить ответ'

        this.currPageNumber = this.currPageNumber + 1;
        this.$resource('/api/quizzes/page/{page}').get({page: this.currPageNumber}).then(result => {
              if (result.status === 200) {
                result.json().then(data =>
                    this.currPage = data
                )
              } else {
                this.currPageNumber = -1
              }
            }
        )
      } else {
        window.location.href = '/history';
      }
    },
    next() {
      if (this.currPageNumber !== -1) {
        this.disableBtn = false
        this.btnText = 'Получить ответ'

        this.currPageNumber = this.currPageNumber + 1;
        this.$resource('/api/quizzes/page/{page}').get({page: this.currPageNumber}).then(result => {
              if (result.status === 200) {
                result.json().then(data =>
                    this.currPage = data
                )
              } else {
                this.currPageNumber = -1
              }
            }
        )
      }
    },
    getAnswer() {
      this.disableBtn = true
      this.btnText = this.currPage.answer
    }
  }
}
</script>

<style>

</style>