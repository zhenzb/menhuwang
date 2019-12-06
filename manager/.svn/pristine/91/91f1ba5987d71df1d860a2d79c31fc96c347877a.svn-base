/**
 * Created by lk on 17/6/4.
 */
import axios from "../../utils/axios";
// 获取信息
export function userInfo(id, token) {
    return axios({
        url: "admin/login/userInfo",
        method: "get",
        params: {
            id,
            token
        }
    });
}

export function loginName(username, pwd) {
    return axios({
        url: "admin/login/index",
        method: "post",
        data: {
            username,
            pwd
        }
    });
}

export function logout(uid, token) {
    return axios({
        url: "admin/login/out",
        method: "post",
        data: {
            uid,
            token
        }
    });
}

export function password(data) {
    return axios({
        url: "admin/login/password",
        method: "post",
        data: data
    });
}
