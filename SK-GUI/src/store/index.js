import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user:{},
    loggedIn:false
  },
  mutations: {
    set_user(state, user){
      state.user = user;
      state.loggedIn = true;
    },
    remove_user(state){
      state.user = {};
      state.loggedIn = false;
    }
  },
  actions: {
  },
  modules: {
  }
})
