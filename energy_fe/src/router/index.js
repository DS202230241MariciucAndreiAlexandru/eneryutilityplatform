import Vue from 'vue'
import VueRouter from 'vue-router'
import roles from '../utils/roleUtil'

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'home',
        component: () => import("../views/HomeView"),
        meta: {role: roles.ANONYMOUS}
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/LoginView'),
        meta: {role: roles.ANONYMOUS}
    },
    {
        path: '/admin',
        name: 'admin',
        component: null,
        meta: {role: roles.ADMIN}
    },
    {
        path: '/user',
        name: 'user',
        component: null,
        role: roles.USER
    }
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default router;
