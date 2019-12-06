import axios from "../../utils/axios";


// 获取产品介绍信息
export function getList(query) {
    return axios({
        url: "/mbIp/getList",
        method: "get",
        params: query
    });
}
// 保存更改
export function ipstatsEdit(data) {
    return axios({
        url: "mbIp/ipstatsEdit",
        method: "post",
        params: data
    });
}
export function delOrNolanding(data) {
    return axios({
        url: "mbIp/delOrNolanding",
        method: "post",
        data: data
    });
}

