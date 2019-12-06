import axios from "../../utils/axios";

// 获取常见问题集合
export function getProblenList(query) {
    return axios({
        url: "/problem/getList",
        method: "get",
        params: query
    });
}
// 常见问题保存更改
export function addOrEdit(data) {
    return axios({
        url: "problem/addOrEdit",
        method: "post",
        params: data
    });
}
// 常见问题删除或排序
export function delOrSort(data) {
    return axios({
        url: "problem/deleteOrSort",
        method: "post",
        data: data
    });
}

// 获取反馈集合
export function getOpinionBackList(query) {
    return axios({
        url: "/opinionBack/getList",
        method: "get",
        params: query
    });
}
// 删除反馈
export function delOpinionback(data) {
    return axios({
        url: "opinionBack/delOpinionback",
        method: "post",
        data: data
    });
}

// 获取背景图片
export function getBackgroudPic(query) {
    return axios({
        url: "/problem/getBackgroudPic",
        method: "get",
        params: query
    });
}

// 保存更改
export function backgroudPicEdit(data) {
    return axios({
        url: "product/backgroudPicEdit",
        method: "post",
        params: data
    });
}


