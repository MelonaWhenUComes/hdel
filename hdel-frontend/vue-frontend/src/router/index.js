import { createRouter, createWebHistory } from 'vue-router'
import PageHome from '@/views/PageHome.vue'
import BoardList from '@/views/board/BoardList.vue'
import BoardDetail from '@/views/board/BoardDetail.vue'
import BoardWrite from '@/views/board/BoardWrite.vue'
import LoginPage from "@/views/common/LoginPage"
import store from "@/vuex/store.js";

const requireAuth = () => (from, to, next) => {
  const token = localStorage.getItem('user_token')
  if (token) {
    store.state.isLogin = true;
    store
    //store.state.isLogin = true
    return next()
  } // isLogin === true면 페이지 이동
  next('/loginPage') // isLogin === false면 다시 로그인 화면으로 이동
}

const routes = [
  {
    path: '/',
    name: 'PageHome',
    component: PageHome
  },
  {
    path: '/loginPage',
    name: 'LoginPage',
    component: LoginPage  //로그인 컴포넌트 추가
  },
  {
    path: '/about',
    name: 'PageAbout',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/PageAbout.vue')
  },
  {
    path: '/board/list',
    name: 'BoardList',
    component: BoardList, 
    beforeEnter: requireAuth()
  },
  {
    path: '/board/detail',
    name: 'BoardDetail',
    component: BoardDetail,
    beforeEnter: requireAuth()
  },
  {
    path: '/board/write',
    name: 'BoardWrite',
    component: BoardWrite,
    beforeEnter: requireAuth()
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router