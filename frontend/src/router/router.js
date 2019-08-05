import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '../pages/home/HomePage.vue'
import PortfolioCreatePage from '../pages/portfolio/PortfolioCreatePage.vue'
import PortfolioListPage from '../pages/portfolio/PortfolioListPage.vue'
import PortfolioDetailPage from '../pages/portfolio/PortfolioDetailPage.vue'
import PortfolioUpdatePage from '../pages/portfolio/PortfolioUpdatePage.vue'

import PostListPage from '@/pages/post/PostListPage.vue'

import SignUpPage from '../pages/signup/SignUpPage.vue'
import NaverCallBack from '../components/widget/NaverCallBack.vue'

import BackOfficeLoginPage from '@/pages/backoffice/BackOfficeLoginPage.vue'
import BackOfficeMainPage from '@/pages/backoffice/BackOfficeMainPage.vue'

Vue.use(Router)

export default new Router({
	mode: 'history',
	base: process.env.BASE_URL,
	routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path : '/portfolios/create',
      name : 'PortfolioCreatePage',
      component : PortfolioCreatePage
    },
    {
      path: '/portfolios',
      name: 'PortfolioListPage',
      component: PortfolioListPage
    },
    {
      path: '/portfolios/:portfolioId',
      name: 'PortfolioDetailPage',
      component: PortfolioDetailPage
    },
    {
      path: '/portfolios/:portfolioId/update',
      name: 'PortfolioUpdatePage',
      component: PortfolioUpdatePage
    },
    {
      path : '/posts',
      name : 'PostListPage',
      component : PostListPage
    },
    {
      path: '/signuppage',
      name: 'SignUpPage',
      component: SignUpPage
    },
    {
      path: '/callback',
      name: 'callback',
      component: NaverCallBack
    },
    {
      path: '/admin',
      name: 'BackOfficeLoginPage',
      component: BackOfficeLoginPage
    },
    {
      path : '/admin/main',
      name : 'BackOfficeMainPage',
      component : BackOfficeMainPage
    },
  ]
})