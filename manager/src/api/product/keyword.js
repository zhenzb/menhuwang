import axios from "../../utils/axios";

// 获取标签列表
export function getKeyWord(query) {
    return axios({
        url: "/keyWord/getKeyWordList",
        method: "get",
        params: query
    });
}

//新增标签
export function addKeyWord(data) {
    return axios({
        url: "/keyWord/addKeyWord",
        method: "post",
        data: data
    });
}

//删除标签
export function deleteKeyWord(data) {
    return axios({
        url: "/keyWord/deleteKeyWord",
        method: "post",
        data: data
    });
}
