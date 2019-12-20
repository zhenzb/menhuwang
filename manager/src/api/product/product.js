import axios from "../../utils/axios";

// 获取标签列表
export function getCategory(query) {
    return axios({
        url: "/category/getCategory",
        method: "get",
        params: query
    });
}

// 获取产品介绍信息
export function getIntroduction() {
    return axios({
        url: "/product/getIntroduction",
        method: "get"
    });
}
// 保存更改
export function introductionEdit(data) {
    return axios({
        url: "product/introductionEdit",
        method: "post",
        params: data
    });
}

// 获取产品集合
export function getProductList(query) {
    return axios({
        url: "/product/getProductList",
        method: "get",
        params: query
    });
}
// 保存更改
export function advantageAddOrEdit(data) {
    return axios({
        url: "product/advantageAddOrEdit",
        method: "post",
        params: data
    });
}
// 删除或排序
export function productDelOrSort(data) {
    return axios({
        url: "product/deleteOrSort",
        method: "post",
        data: data
    });
}

// 获取背景图片
export function getBackgroudPic(query) {
    return axios({
        url: "/product/getBackgroudPic",
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

//查询新闻动态列表
export function addNewsInfo(data) {
    return axios({
        url: "newsInfo/addNewsInfo",
        method: "post",
        data: data
    });
}

//查询新闻动态列表
export function getNewsInfo(query) {
    return axios({
        url: "newsInfo/getNewsInfoList",
        method: "get",
        params: query
    });
}

//删除新闻动态列表
export function deleteNewsInfo(query) {
    return axios({
        url: "newsInfo/deleteNewsInfo",
        method: "post",
        params: query
    });
}

//一键静态化案例网页
export function staticState() {
    return axios({
        url: "product/staticState",
        method: "get"
    });
}
//一键静态化新闻网页
export function staticStateNews() {
    return axios({
        url: "newsInfo/staticState",
        method: "get"
    });
}
