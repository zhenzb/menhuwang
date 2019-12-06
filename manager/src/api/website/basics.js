import axios from "../../utils/axios";


// 获取产品介绍信息
export function getBasicsInfo() {
    return axios({
        url: "/basics/getBasicsInfo",
        method: "get"
    });
}
// 保存更改
export function basicsEdit(data) {
    return axios({
        url: "basics/basicsEdit",
        method: "post",
        params: data
    });
}
