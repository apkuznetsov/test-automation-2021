import {createLocalVue, mount} from '@vue/test-utils'
import Index from './js/pages/Index.vue'
import Vuetify from 'vuetify'

describe('Index.vue', () => {

    const localVue = createLocalVue()
    let vuetify

    beforeEach(() => {
        vuetify = new Vuetify()
    })

    test('Существует ряд с викториной', () => {
        const wrapper = mount(Index, {
            localVue,
            vuetify,
            data() {
                return {
                    quizzes: [
                        {
                            answer: "цена",
                            id: 23,
                            question: "price",
                            title: "Английский",
                            user: {
                                email: "example@example.com",
                                id: 3
                            }
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
                            answer: "цена",
                            id: 23,
                            question: "price",
                            title: "Английский",
                            user: {
                                email: "example@example.com",
                                id: 3
                            }
                        }]
                }
            }
        })

        expect(wrapper.find('.my-quiz-title').text()).toBe("Английский")
        expect(wrapper.find('.my-quiz-question').text()).toBe("price")
        expect(wrapper.find('.my-quiz-answer').text()).toBe("цена")
    })

    test('Существует кнопки изменения и удаления викторины', () => {
        const wrapper = mount(Index, {
            localVue,
            vuetify,
            data() {
                return {
                    quizzes: [
                        {
                            answer: "цена",
                            id: 23,
                            question: "price",
                            title: "Английский",
                            user: {
                                email: "example@example.com",
                                id: 3
                            }
                        }]
                }
            }
        })

        expect(wrapper.find('.my-edit-btn').exists()).toBe(true)
        expect(wrapper.find('.my-del-btn').exists()).toBe(true)
    })

    test('Существует форма создания вопроса', () => {
        const wrapper = mount(Index, {
            localVue,
            vuetify,
            data() {
                return {
                    quizzes: [
                        {
                            answer: "цена",
                            id: 23,
                            question: "price",
                            title: "Английский",
                            user: {
                                email: "example@example.com",
                                id: 3
                            }
                        }]
                }
            }
        })

        expect(wrapper.find('.my-title-tf').exists()).toBe(true)
        expect(wrapper.find('.my-answer-tf').exists()).toBe(true)
        expect(wrapper.find('.my-question-tf').exists()).toBe(true)
        expect(wrapper.find('.my-save-btn').exists()).toBe(true)
    })
})