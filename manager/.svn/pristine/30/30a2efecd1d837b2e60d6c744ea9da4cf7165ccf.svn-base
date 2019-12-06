import axios from "../../utils/axios";

// 获取公司集合
export function getList(query) {
    return axios({
        url: "/cooperativePartner/getList",
        method: "get",
        params: query
    });
}
// 公司信息更改
export function addOrEdit(data) {
    return axios({
        url: "cooperativePartner/addOrEdit",
        method: "post",
        params: data
    });
}
// 常见问题删除或排序
export function delOrSort(data) {
    return axios({
        url: "cooperativePartner/deleteOrSort",
        method: "post",
        data: data
    });
}
// 获取背景图片
export function getBackgroudPic(query) {
    return axios({
        url: "/cooperativePartner/getBackgroudPic",
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


