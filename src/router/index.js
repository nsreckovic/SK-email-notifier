import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'HomePage',
        component: () => import(/* webpackChunkName: "about" */ '../views/HomePage.vue')
    },
    {
        path: '/users/register',
        name: 'Register',
        component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
    },
    {
        path: '/users/login',
        name: 'Login',
        component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
    }
];

const index = new VueRouter({
    mode: 'history',
    routes
});

export default index