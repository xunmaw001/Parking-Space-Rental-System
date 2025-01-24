import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
    // 解决多次点击左侧菜单报错问题
    const VueRouterPush = VueRouter.prototype.push
    VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
    }
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import beifen from '@/views/modules/databaseBackup/beifen'
import huanyuan from '@/views/modules/databaseBackup/huanyuan'

     import users from '@/views/modules/users/list'
    import caozuorizhi from '@/views/modules/caozuorizhi/list'
    import chewei from '@/views/modules/chewei/list'
    import cheweiCollection from '@/views/modules/cheweiCollection/list'
    import cheweiLiuyan from '@/views/modules/cheweiLiuyan/list'
    import cheweiZulin from '@/views/modules/cheweiZulin/list'
    import cheweiyuyue from '@/views/modules/cheweiyuyue/list'
    import dictionary from '@/views/modules/dictionary/list'
    import gonggao from '@/views/modules/gonggao/list'
    import liuyan from '@/views/modules/liuyan/list'
    import yonghu from '@/views/modules/yonghu/list'
    import config from '@/views/modules/config/list'
    import dictionaryChewei from '@/views/modules/dictionaryChewei/list'
    import dictionaryCheweiCollection from '@/views/modules/dictionaryCheweiCollection/list'
    import dictionaryCheweiZhuangtai from '@/views/modules/dictionaryCheweiZhuangtai/list'
    import dictionaryCheweiZulin from '@/views/modules/dictionaryCheweiZulin/list'
    import dictionaryCheweiyuyueYesno from '@/views/modules/dictionaryCheweiyuyueYesno/list'
    import dictionaryGonggao from '@/views/modules/dictionaryGonggao/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShangxia from '@/views/modules/dictionaryShangxia/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }, {
        path: '/huanyuan',
        name: '数据还原',
        component: huanyuan
    }, {
        path: '/beifen',
        name: '数据备份',
        component: beifen
    }, {
        path: '/users',
        name: '管理信息',
        component: users
    }
    ,{
        path: '/dictionaryChewei',
        name: '车位类型',
        component: dictionaryChewei
    }
    ,{
        path: '/dictionaryCheweiCollection',
        name: '收藏表类型',
        component: dictionaryCheweiCollection
    }
    ,{
        path: '/dictionaryCheweiZhuangtai',
        name: '车位状态',
        component: dictionaryCheweiZhuangtai
    }
    ,{
        path: '/dictionaryCheweiZulin',
        name: '订单类型',
        component: dictionaryCheweiZulin
    }
    ,{
        path: '/dictionaryCheweiyuyueYesno',
        name: '申请状态',
        component: dictionaryCheweiyuyueYesno
    }
    ,{
        path: '/dictionaryGonggao',
        name: '公告类型',
        component: dictionaryGonggao
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShangxia',
        name: '上下架',
        component: dictionaryShangxia
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/caozuorizhi',
        name: '操作日志',
        component: caozuorizhi
      }
    ,{
        path: '/chewei',
        name: '车位',
        component: chewei
      }
    ,{
        path: '/cheweiCollection',
        name: '车位收藏',
        component: cheweiCollection
      }
    ,{
        path: '/cheweiLiuyan',
        name: '车位留言',
        component: cheweiLiuyan
      }
    ,{
        path: '/cheweiZulin',
        name: '车位租赁订单',
        component: cheweiZulin
      }
    ,{
        path: '/cheweiyuyue',
        name: '车位预约',
        component: cheweiyuyue
      }
    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/gonggao',
        name: '公告',
        component: gonggao
      }
    ,{
        path: '/liuyan',
        name: '留言板',
        component: liuyan
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
