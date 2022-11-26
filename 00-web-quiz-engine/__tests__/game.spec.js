import {createLocalVue, mount} from '@vue/test-utils'
import Index from './js/pages/Game.vue'
import Vuetify from 'vuetify'

describe('Game.vue', () => {

    const localVue = createLocalVue()
    let vuetify

    beforeEach(() => {
        vuetify = new Vuetify()
    })

    test('Существуют кнопки пометки ответа', () => {
        const wrapper = mount(Index, {
            localVue,
            vuetify,
            data() {
                return {
                    currPage: {
                        title: "Английский",
                        question: "price"
                    }
                }
            }
        })

        expect(wrapper.find('.my-right-btn').exists()).toBe(true)
        expect(wrapper.find('.my-wrong-btn').exists()).toBe(true)
    })

    test('Кооректно отображаются данные карточки викторины', () => {
        const wrapper = mount(Index, {
            localVue,
            vuetify,
            data() {
                return {
                    currPage: {
                        title: "Английский",
                        question: "price"
                    }
                }
            }
        })

        expect(wrapper.find('.my-quiz-title').text()).toBe("Английский")
        expect(wrapper.find('.my-quiz-question').text()).toBe("price")
    })
})