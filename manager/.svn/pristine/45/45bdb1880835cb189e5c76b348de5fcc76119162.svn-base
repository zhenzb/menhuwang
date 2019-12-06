import axios from "axios";
import { Message } from "element-ui";
import store from "../store/index";
import { BASE_URL } from "../config/app";
//import router from "../router/index";

// 创建axios实例
const service = axios.create({
    baseURL: BASE_URL, // api的base_url
    timeout: 60000 // 请求超时时间(60s)
});

// request拦截器
service.interceptors.request.use(
    config => {
        // Do something before request is sent
        if (store.getters.adminId && store.getters.token) {
            config.headers["X-Adminid"] = store.getters.adminId;
            config.headers["X-Token"] = store.getters.token;
        }
        return config;
    },
    error => {
        // Do something with request error
        Promise.reject(error);
    }
);

// respone拦截器
service.interceptors.response.use(
    response => {
        const data = response.data;
        if (data.code) {
            if (data.code === 2) {
                store.dispatch("loginOut").then(() => {
                    Message.error("登录信息过期,请重新登录");
                    // router.push({
                    //     path: "/login"
                    //     // query: { redirect: router.currentRoute.fullPath } // 从哪个页面跳转过来
                    // });
                    window.location.replace("/login");
                });
            }
        }
        return data;
    },
    error => {
        let msg = error.message;
        if (msg === "Network Error") {
            msg = "网络异常 请检查网络设置";
        }
        Message({
            message: msg,
            type: "error",
            duration: 5 * 1000
        });
        return Promise.reject(error);
    }
);

export default service;
