import Vue from 'vue'
import App from './App.vue'
import index from "./router"
import store from './store'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios'
import Notifications from 'vue-notification'

Vue.config.productionTip = false;
Vue.use(BootstrapVue);
Vue.use(Notifications);

// Used for requests
axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
axios.defaults.headers.post['Content-Type'] = 'application/json';
window.axios = axios;

new Vue({
  router: index,
  store,
  render: h => h(App),
}).$mount('#app')
