import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in subMenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if false, the item will hidden in breadcrumb(default is true)
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },
  { path: '*', redirect: '/404', hidden: true },
  {
    path: '/article',
    component: Layout,
    // component: () => import('@/views/pre/article/index'),
    name: 'article',
    meta: {
      title: '文章管理',
      icon: 'edit'
    },
    children: [
      {
        path: 'card',
        component: () => import('@/views/pre/article/card'),
        name: 'Card',
        meta: {
          // resources: '',
          title: '文章展示',
          icon: 'edit'
        }
      },
      {
        path: 'view/:id(\\d+)',
        component: () => import('@/views/pre/article/ArticleView'),
        name: 'View',
        meta: {
          // resources: '',
          title: '文章详情',
          noCache: true,
          activeMenu: '/article/card',
          icon: 'edit'
        },
        hidden: true
      },
      {
        path: 'detail',
        component: () => import('@/views/pre/article/ArticleDetail'),
        name: 'Detail',
        meta: {
          // resources: '',
          title: '新建文章',
          icon: 'edit'
        }
      },
      {
        path: 'list',
        component: () => import('@/views/pre/article/list'),
        name: 'List',
        meta: {
          // resources: '',
          title: '文章列表',
          icon: 'edit'
        }
      },
      {
        path: 'edit/:id(\\d+)',
        component: () => import('@/views/pre/article/edit'),
        name: 'EditArticle',
        meta: {
          title: 'editArticle',
          noCache: true,
          activeMenu: '/article/list'
        },
        hidden: true
      }
    ]
  }
]

export const asyncRouterMap = [
  {
    path: '/pre',
    component: Layout,
    name: 'pre',
    meta: {
      resources: 'pre',
      title: '权限管理'
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/pre/permission/index'),
        name: 'perm',
        meta: {
          resources: 'perm'
        }
      },
      {
        path: 'user',
        component: () => import('@/views/pre/user/index'),
        name: 'user',
        meta: {
          resources: 'user'
        }
      },
      {
        path: 'role',
        component: () => import('@/views/pre/role/index'),
        name: 'role',
        meta: {
          resources: 'role'
        }
      },
      {
        path: 'article',
        component: Layout,
        // component: () => import('@/views/pre/article/index'),
        name: 'article',
        meta: {
          resources: 'article',
          title: '文章管理'
        }
      }
    ]
  },

  {
    path: '/sys',
    component: Layout,
    name: 'sys',
    meta: {
      resources: 'sys',
      title: '系统设置'
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/sys/backstage/index'),
        name: 'backstage',
        meta: {
          resources: 'backstage'
        }
      }]
  },

  {
    path: 'external-link',
    component: Layout,
    name: 'Link',
    meta: {
      resources: 'control',
      title: '系统监控',
      icon: 'link'
    },
    children: [{
      path: 'http://localhost/api/swagger-ui.html',
      meta: {
        resources: 'interface',
        title: '接口文档',
        icon: 'link'
      }
    }]
  }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
