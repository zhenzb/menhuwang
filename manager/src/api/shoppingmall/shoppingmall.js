import axios from "../../utils/axios";


// 获取产品介绍信息
export function getShoppingMallInfo() {
return axios({
url: "/shoppingMall/getShoppingMallInfo",
method: "get"
});
}
// 保存更改
export function shoppingMallEdit(data) {
    return axios({
    url: "shoppingMall/shoppingMallEdit",
    method: "post",
    params: data
});
}
