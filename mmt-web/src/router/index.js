import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ApiTest from '../components/ApiTest.vue'
import LoginView from '../views/user/LoginView.vue'
import RegisterView from '../views/user/RegisterView.vue'
import StudentsView from '../views/StudentsView.vue'
import TestsView from '../views/TestsView.vue'
import DownloadView from '../views/DownloadView.vue'
import ListView from '../views/ListView.vue'
import RecordView from '../views/RecordView.vue'
import AnalysisView from '../views/AnalysisView.vue'
import PersonalView from '../views/PersonalView.vue'
import PersonalPreview from '../views/PersonalPreview.vue'
import StatisticView from '../views/StatisticView.vue'

// 기본 경로로 web/v1 붙이기?

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/apitest',
    name: 'apitest',
    component: ApiTest
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/students',
    name: 'students',
    component: StudentsView
  },
  {
    path: '/tests',
    name: 'tests',
    component: TestsView
  },
  {
    path: '/download',
    name: 'download',
    component: DownloadView
  },
  {
    path: '/list',
    name: 'list',
    component: ListView
  },
  {
    path: '/record',
    name: 'record',
    component: RecordView
  },
  {
    path: '/analysis',
    name: 'analysis',
    component: AnalysisView
  },
  {
    path: '/personal',
    name: 'personal',
    component: PersonalView
  },
  {
    path: '/personalpreview',
    name: 'personalpreview',
    component: PersonalPreview
  },
  {
    path: '/statistic',
    name: 'statistic',
    component: StatisticView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
