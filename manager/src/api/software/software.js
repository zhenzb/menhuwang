import axios from "../../utils/axios";


// 获取用户列表
export function getMbSoftware() {
    return axios({
        url: "/software/getMbSoftware",
        method: "get"
    });
}
// 保存更改
export function addOrEditSoftware(data) {
    return axios({
        url: "software/addOrEdit",
        method: "post",
        params: data
    });
}
// 删除
export function softwareDelOrSort(data) {
    return axios({
        url: "software/deleteOrSort",
        method: "post",
        data: data
    });
}
2

