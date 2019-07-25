import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '../pages/HomePage'
import PortfolioListPage from '../pages/PortfolioListPage'
import PostListPage from '../pages/PostListPage'
import SignUpPage from '../pages/SignUpPage'
import WriteArticlePage from '../pages/WriteArticlePage'
import WritePostPage from '../pages/WritePostPage'
import DetailArticlePage from '../pages/DetailArticlePage'
import DetailPostPage from '../pages/DetailPostPage'
import NaverCallBack from '../components/NaverCallBack'
import RepoPage from '../pages/RepoPage'
import TestPage from '../pages/TestPage'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'homepage',
      component: HomePage
    },
    {
      path: '/articles/portfolios',
      name: 'portfoliolistpage',
      component: PortfolioListPage
    },
    {
      path: '/articles/portfolios/new',
      name: 'newportfolio',
      component: WriteArticlePage
    },
    {
      path: '/articles/portfolios/:article_no',
      name: 'portfoliodetail',
      component: DetailArticlePage

    },
    {
      path: '/articles/portfolios/:article_no/edit',
      name: 'editarticle',
      component: WriteArticlePage
    },
    {
      path: '/postlist',
      name: 'postlistpage',
      component: PostListPage
    },
    {
      path: '/articles/posts/new',
      name: 'newpost',
      component: WritePostPage
    },
    {
      path: '/articles/posts/:article_no',
      name: 'postdetail',
      component: DetailPostPage

    },
    {
      path: '/articles/posts/:article_no/edit',
      name: 'editpost',
      component: WritePostPage
    },
    {
      path: '/signup',
      name: 'signuppage',
      component: SignUpPage
    },
    {
      path: '/callback',
      name: 'callback',
      component: NaverCallBack
    },
    {
      path: '/login',
      name: 'testpage',
      component: TestPage
    },
    {
      path: '/repo',
      name: 'repopage',
      component: RepoPage
    }
    
    
  ]
});
