import {createLocalVue, mount} from '@vue/test-utils'
import Index from './js/pages/History.vue'
import Vuetify from 'vuetify'

describe('History.vue', () => {

    const localVue = createLocalVue()
    let vuetify

    beforeEach(() => {
        vuetify = new Vuetify()
    })

    test('Существует ряд с историей ответа', () => {
        const wrapper = mount(Index, {
            localVue,
            vuetify,
            data() {
                return {
                    quizzes: [
                        {
                            quiz: {
                                title: "Английский",
                                question: "price",
                                answer: "цена",
                            },
                            completedAt: "2021-12-12T19:31:23.207255",
                            right: true
                        }]
                }
            }
        })

        expect(wrapper.find('.my-row').exists()).toBe(true)
    })

    test('Кооректно отображаются данные викторины', () => {
        const wrapper = mount(Index, {
            localVue,
            vuetify,
            data() {
                return {
                    quizzes: [
                        {
                            quiz: {
                                title: "Английский",
                                question: "price",
                                answer: "цена",
                            },
                            completedAt: "2021-12-12T19:31:23.207255",
                            right: true
                        }]
                }
            }
        })

        expect(wrapper.find('.my-quiz-title').text()).toBe("Английский")
        expect(wrapper.find('.my-quiz-question').text()).toBe("price")
        expect(wrapper.find('.my-quiz-answer').text()).toBe("цена")
        expect(wrapper.find('.my-quiz-completed-at').text()).toBe("2021-12-12T19:31:23.207255")
        expect(wrapper.find('.my-quiz-right').text()).toBe("правильно")
    })
})