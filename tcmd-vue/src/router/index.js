import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {//处方信息栏
    path: '/pres_info',
    name: 'pres_info',
    component: () => import('../views/PresView.vue')
  },
  {//报文信息栏
    path: '/ship_info',
    name: 'ship_info',
    component: () => import('../views/ShipView.vue')
  },
  {//处方历史栏
    path: '/pres_his',
    name: 'pres_his',
    component: () => import('../views/PresHisView.vue')
  },
  {//报文历史栏
    path: '/ship_his',
    name: 'ship_his',
    component: () => import('../views/ShipHisView.vue')
  },
  {//日志信息栏
    path: '/log',
    name: 'log',
    component: () => import('../views/LogView.vue')
  },
  {//设置栏
    path: '/setting',
    name: 'setting',
    component: () => import('../views/SettingView.vue')
  },
  {//定时任务栏
    path: '/schedule',
    name: 'schedule',
    component: () => import('../views/ScheduleView.vue')
  },
  {//药厂栏
    path: '/factory',
    name: 'factory',
    component: () => import('../views/FactoryView.vue')
  },
  {//特殊操作栏
    path: '/special',
    name: 'special',
    component: () => import('../views/SpecialView.vue')
  },
  {//PVUV统计栏
    path: '/pvuv',
    name: 'pvuv',
    component: () => import('../views/PvuvView.vue')
  },
]

const router = new VueRouter({
  routes
})

export default router
