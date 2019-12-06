import axios from "../../utils/axios";
// 获取标签列表
export function getCategory(query) {
    return axios({
        url: "/category/getCategoryList",
        method: "get",
        params: query
    });
}

//新增标签
export function addCategory(data) {
    return axios({
        url: "/category/addCaseCategory",
        method: "post",
        data: data
    });
}


//删除标签
export function deleteCategory(data) {
    return axios({
        url: "/category/deleteCaseCategory",
        method: "post",
        data: data
    });
}