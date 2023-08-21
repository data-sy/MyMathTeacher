import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue';
import AppLayout from '@/layout/AppLayout.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: AppLayout,
      children: [
        // {
        //   path: '/',
        //   name: 'home',
        //   component: HomeView
        // },
        {
          path: '/',
          name: 'dashboard',
          component: () => import('@/views/Dashboard.vue')
        }
      ]
    }
  ]
})

export default router
