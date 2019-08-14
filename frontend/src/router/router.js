import Vue from 'vue'
import Router from 'vue-router'
import firebase from '../apis/firebase/firebase'

import HomePage from '../pages/home/HomePage.vue'
import PortfolioCreatePage from '../pages/portfolio/PortfolioCreatePage.vue'
import PortfolioListPage from '../pages/portfolio/PortfolioListPage.vue'
import PortfolioDetailPage from '../pages/portfolio/PortfolioDetailPage.vue'
import PortfolioUpdatePage from '../pages/portfolio/PortfolioUpdatePage.vue'

import PostCreatePage from '../pages/post/PostCreatePage'
import PostListPage from '../pages/post/PostListPage'
import PostDetailPage from '../pages/post/PostDetailPage'
import PostUpdatePage from '../pages/post/PostUpdatePage'

import SignUpPage from '../pages/signup/SignUpPage.vue'
import NaverCallBack from '../components/widget/NaverCallBack.vue'

import BackOfficeLoginPage from '@/pages/backoffice/BackOfficeLoginPage.vue'
import BackOfficeMainPage from '@/pages/backoffice/BackOfficeMainPage.vue'

import ErrorPage from '../pages/error/ErrorPage'
import TestPage from '../pages/error/TestPage'

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
      component: PortfolioListPage,  
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
      path : '/posts/create',
      name : 'PostCreatePage',
      component : PostCreatePage
    },
    {
      path: '/posts',
      name: 'PostListPage',
      component: PostListPage
    },
    {
      path: '/posts/:postId',
      name: 'PostDetailPage',
      component: PostDetailPage
    },
    {
      path: '/posts/:postId/update',
      name: 'PostUpdatePage',
      component: PostUpdatePage
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
      component: BackOfficeLoginPage,
    },
    {
      path : '/admin/main',
      name : 'BackOfficeMainPage',
      component : BackOfficeMainPage,
      beforeEnter(from,to,next) {
        if (!sessionStorage.getItem('key')) {
          next({name: 'BackOfficeLoginPage'})
        } else {
          firebase.getInfo(sessionStorage.getItem('key'))
            .then((res) => {
              if (res.auth === "1") {
                next()
              } else {
                next({name: 'BackOfficeLoginPage'})
              }
            })
        }
      }  
    },
    {
      path: '*',
      name: 'ErrorPage',
      component: ErrorPage
    },
    {
      path: '/test',
      name: 'TestPage',
      component: TestPage
    },
  ]
})
