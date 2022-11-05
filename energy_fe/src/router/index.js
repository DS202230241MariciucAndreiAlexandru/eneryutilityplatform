import Vue from 'vue'
import VueRouter from 'vue-router'
import roles from '../utils/roleUtil'
import {useAuthStore} from "@/store/useAuthStore";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'home',
        component: () => import("../views/HomeView"),
        meta: {role: roles.ANONYMOUS},
    },
    {
        path: '/login',
        name: 'login',
        props: true,
        component: () => import('../views/LoginView'),
        meta: {role: roles.ANONYMOUS},
    },
    {
        path: '/admin',
        name: roles.ADMIN,
        component: () => import("../views/AdminView"),
        meta: {role: roles.ADMIN},
    },
    {
        path: '/user',
        name: roles.USER,
        component: () => import("../views/UserView"),
        meta: {role: roles.USER},
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('../views/RegisterView'),
        meta: {role: roles.ANONYMOUS},
    },
    {
        path: '/404',
        name: '404',
        component: () => import('../views/404View'),
        meta: {role: roles.ANONYMOUS},
    }
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

router.beforeEach((to, from, next) => {
    const authStore = useAuthStore();

    if (roles.ANONYMOUS === to.meta.role) {
        next();
    } else {
        if (authStore.isAuthenticated) {
            if (authStore.role !== to.meta.role) {
                next({path: '/404'})
            } else {
                next();
            }
        } else {
            next({path: '/login'});
        }
    }
});

export default router;
