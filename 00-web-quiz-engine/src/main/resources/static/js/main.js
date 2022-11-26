import Vue from 'vue'
import VueResource from 'vue-resource'
import Index from 'pages/Index.vue'
import History from 'pages/History.vue'
import Game from 'pages/Game.vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import moment from 'moment'

Vue.use(Vuetify)
Vue.use(VueResource)

Vue.filter('formatDate', function(value) {
    if (value) {
        return moment(String(value)).format('MM/DD/YYYY hh:mm')
    }
})

new Vue({
    vuetify : new Vuetify(),
    el: '#index',
    render: a => a(Index)
})

new Vue({
    vuetify : new Vuetify(),
    el: '#history',
    render: b => b(History)
})

new Vue({
    vuetify : new Vuetify(),
    el: '#game',
    render: c => c(Game)
})
