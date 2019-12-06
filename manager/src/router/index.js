import Vue from "vue";
import VueRouter from "vue-router";

if (process.env.NODE_ENV === "development") {
    Vue.use(VueRouter);
}

import { ROUTER_MODE } from "../config/app";

// 主页
import Home from "../views/home/index.vue";

// 管理组相关
import authAdmin from "../views/userManage/admin/authAdmin.vue";
import authRole from "../views/userManage/admin/authRole.vue";

import software from "../views/software/software.vue"; //软件下载
//产品
// import productIntroduction from "../views/product/introduction.vue"; //产品优势
import productAdvantage from "../views/product/advantage.vue"; //产品优势
import productBackground from "../views/product/background.vue"; //产品背景设置
//服务中心
// import problem from "../views/serviceCentre/problem.vue"; //常见问题
// import opinionBack from "../views/serviceCentre/opinionBack.vue"; //反馈内容
// import serviceCentreBackground from "../views/serviceCentre/background.vue"; //服务中心背景设置
//商城
import shoppingMall from "../views/shoppingMall/shoppingMall.vue"; //服务中心背景设置
//合作伙伴
import cooperativePartner from "../views/cooperativePartner/cooperativePartner.vue"; //合作伙伴
//关于我们
// import sysText from "../views/sysText/sysText.vue"; //关于我们
import category from "../views/product/category.vue"; //分类标签
//新闻动态
import newsinfo from "../views/product/newsinfo.vue";
//SEO关键字
import keyword from "../views/product/keyword.vue";
//客服管理
import customer from "../views/product/customer.vue";
//友情链接
import friendshipLink from "../views/product/friendshipLink.vue";
//联系我们
import sysCustomer from "../views/sysCustomer/sysCustomer.vue"; //联系我们

ipAddress;
//网站管理
import ipAddress from "../views/website/ipAddress.vue"; //联系我们
// import basics from "../views/website/basics.vue"; //联系我们
const err401 = r =>
    require.ensure([], () => r(require("../views/error/err401.vue")), "home");
const err404 = r =>
    require.ensure([], () => r(require("../views/error/err404.vue")), "home");
const login = r =>
    require.ensure([], () => r(require("../views/login/index.vue")), "home");
const main = r =>
    require.ensure([], () => r(require("../views/home/main.vue")), "home");

// 注意 权限字段 authRule （严格区分大小写）
export const constantRouterMap = [
    {
        path: "*",
        component: err404,
        hidden: true
    },
    {
        path: "/401",
        component: err401,
        name: "401",
        hidden: true
    },
    {
        path: "/404",
        component: err404,
        name: "404",
        hidden: true
    },
    {
        path: "/500",
        component: err404,
        name: "500",
        hidden: true
    },
    {
        path: "/login",
        component: login,
        name: "登录",
        hidden: true
    },
    {
        path: "/",
        component: Home,
        redirect: "/home",
        name: "首页",
        hidden: true
    },
    {
        path: "/home",
        component: Home,
        redirect: "/home/main",
        icon: "shouye",
        name: "后台管理系统",
        noDropdown: true,
        children: [
            {
                path: "main",
                component: main
            }
        ]
    }
];

export default new VueRouter({
    mode: ROUTER_MODE,
    routes: constantRouterMap,
    strict: process.env.NODE_ENV !== "production"
});

var routerMap = [
    // {
    //     path: "/software",
    //     redirect: "/software/mbDoctor",
    //     component: Home,
    //     icon: "yonghu",
    //     name: "用户管理",
    //     meta: {
    //         authRule: ["software"]
    //     },
    //     children: [
    //         {
    //             path: "bd",
    //             component: software,
    //             name: "用户管理",
    //             meta: {
    //                 authRule: ["software/auth/index"]
    //             }
    //         }
    //     ]
    // },
    //  {
    //     path: "/software",
    //     redirect: "/software/software",
    //     component: Home,
    //     icon: "ruanjianguanli",
    //     name: "产品APP",
    //     meta: {
    //         authRule: ["software/auth/index"]
    //     },
    //     noDropdown: true,
    //     children: [
    //         {
    //             path: "",
    //             component: software
    //         }
    //     ]
    // },
    // {
    //     path: "/cooperativePartner",
    //     redirect: "/cooperativePartner/cooperativePartner",
    //     component: Home,
    //     icon: "hezuohuoban",
    //     name: "企业合作",
    //     meta: {
    //         authRule: ["cooperativePartner/auth/cooperativePartner"]
    //     },
    //     noDropdown: true,
    //     children: [
    //         {
    //             path: "",
    //             component: cooperativePartner,
    //         }
    //     ]
    // },
    // {
    //     path: "/productIntroduce",
    //     redirect: "/product/productIntroduction",
    //     component: Home,
    //     icon: "chanpinjieshao",
    //     name: "产品介绍",
    //     meta: {
    //         authRule: ["product/auth/introduction"]
    //     },
    //     noDropdown: true,
    //     children: [
    //         {
    //             path: "",
    //             component: productIntroduction
    //         }
    //     ]
    // },
    {
        path: "/product",
        redirect: "/product/productAdvantage",
        component: Home,
        icon: "chanpinguanli",
        name: "案例展示",
        meta: {
            authRule: ["product/auth/index"]
        },
        children: [
            {
                path: "productAdvantage",
                component: productAdvantage,
                name: "案例管理",
                meta: {
                    authRule: ["product/auth/advantage"]
                }
            }
            // {
            //     path: "productBackground",
            //     component: productBackground,
            //     name: "背景设置",
            //     meta: {
            //         authRule: ["product/auth/background"]
            //     }
            // }
        ]
    },
    {
        path: "/newinfo",
        redirect: "/product/productAdvantage",
        component: Home,
        icon: "chanpinjieshao",
        name: "新闻动态",
        meta: {
            authRule: ["product/auth/index"]
        },
        children: [
            {
                path: "newsinfo",
                component: newsinfo,
                name: "新闻动态",
                meta: {
                    authRule: ["product/auth/advantage"]
                }
            },
            {
                path: "keyword",
                component: keyword,
                name: "SEO关键字",
                meta: {
                    authRule: ["product/auth/advantage"]
                }
            }
            // {
            //     path: "productBackground",
            //     component: productBackground,
            //     name: "背景设置",
            //     meta: {
            //         authRule: ["product/auth/background"]
            //     }
            // }
        ]
    },
    {
        path: "/customer",
        // redirect: "/product/productAdvantage",
        component: Home,
        icon: "fuwuzhongxin",
        name: "客服管理",
        meta: {
            authRule: ["product/auth/index"]
        },
        children: [
            {
                path: "customer",
                component: customer,
                name: "客服管理",
                meta: {
                    authRule: ["product/auth/advantage"]
                }
            }
        ]
    },
    {
        path: "/friendshipLink",
        component: Home,
        icon: "hezuohuoban",
        name: "友情链接",
        meta: {
            authRule: ["product/auth/index"]
        },
        children: [
            {
                path: "friendshipLink",
                component: friendshipLink,
                name: "友情链接",
                meta: {
                    authRule: ["product/auth/advantage"]
                }
            }
        ]
    },
    // {
    //     path: "/serviceCentre",
    //     redirect: "/serviceCentre/problem",
    //     component: Home,
    //     icon: "fuwuzhongxin",
    //     name: "服务中心",
    //     meta: {
    //         authRule: ["serviceCentre/auth/index"]
    //     },
    //     children: [
    //         {
    //             path: "problem",
    //             component: problem,
    //             name: "常见问题",
    //             meta: {
    //                 authRule: ["serviceCentre/auth/problem"]
    //             }
    //         },
    //         {
    //             path: "opinionBack",
    //             component: opinionBack,
    //             name: "反馈内容",
    //             meta: {
    //                 authRule: ["serviceCentre/auth/opinionBack"]
    //             }
    //         },
    //         {
    //             path: "serviceCentreBackground",
    //             component: serviceCentreBackground,
    //             name: "背景设置",
    //             meta: {
    //                 authRule: ["serviceCentre/auth/background"]
    //             }
    //         }
    //     ]
    // },
    // {
    //     path: "/mbShoppingMall",
    //     redirect: "/mbShoppingMall/mbShoppingMall",
    //     component: Home,
    //     icon: "shangcheng",
    //     name: "首页广告图",
    //     meta: {
    //         authRule: ["mbShoppingMall/auth/index"]
    //     },
    //     noDropdown: true,
    //     children: [
    //         {
    //             path: "",
    //             component: shoppingMall
    //         }
    //     ]
    // },
    // {
    //     path: "/sysText",
    //     redirect: "/sysText/sysText",
    //     component: Home,
    //     icon: "guanyuwomen1",
    //     name: "关于我们",
    //     meta: {
    //         authRule: ["sysText/auth/index"]
    //     },
    //     noDropdown: true,
    //     children: [
    //         {
    //             path: "",
    //             component: sysText
    //         }
    //     ]
    // },
    {
        path: "/sysCustomer",
        redirect: "/sysCustomer/sysCustomer",
        component: Home,
        icon: "guanyuwomen1",
        name: "标签管理",
        meta: {
            authRule: ["sysCustomer/auth/index"]
        },
        noDropdown: true,
        children: [
            {
                path: "",
                component: category
            }
        ]
    },
    {
        path: "/userManage",
        redirect: "/userManage/authAdmin",
        component: Home,
        icon: "ic_opt_feature",
        name: "权限管理",
        meta: {
            authRule: ["jurisdiction"]
        },
        children: [
            {
                path: "authAdmin",
                component: authAdmin,
                name: "管理员管理",
                meta: {
                    authRule: ["jurisdiction/auth/admin"]
                }
            },
            {
                path: "authRole",
                component: authRole,
                name: "角色管理",
                meta: {
                    authRule: ["jurisdiction/auth/role"]
                }
            }
        ]
    }
    // {
    //     path: "/website",
    //     redirect: "/website/basics",
    //     component: Home,
    //     icon: "wangzhanguanli",
    //     name: "网站管理",
    //     meta: {
    //         authRule: ["website/auth/index"]
    //     },
    //     children: [
    //         {
    //             path: "basics",
    //             component: basics,
    //             name: "基础设置",
    //             meta: {
    //                 authRule: ["website/auth/basics"]
    //             }
    //         },
    //         {
    //             path: "ipAddress",
    //             component: ipAddress,
    //             name: "IP地址管理",
    //             meta: {
    //                 authRule: ["website/auth/ipAddress"]
    //             }
    //         }
    //     ]
    // }
];

// 本地开发环境开放权限设置 仅限开发人员使用
// if (process.env.NODE_ENV === "development") {
//     routerMap[routerMap.length - 1].children.push({
//         path: "authPermissionRule",
//         component: authPermissionRule,
//         name: "权限设置",
//         meta: {
//             authRule: ["admin/auth/permission_rule/index"]
//         }
//     });
// }

export const asyncRouterMap = routerMap;
