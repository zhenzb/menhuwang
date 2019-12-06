import axios from "../../utils/axios";

export function getCustomerList(query) {
    return axios({
        url: "/customer/getCustomerList",
        method: "get",
        params: query
    });
}

export function addCustomer(data) {
    return axios({
        url: "/customer/addCustomer",
        method: "post",
        data: data
    });
}

export function deleteCustomer(query) {
    return axios({
        url: "/customer/deleteCustomer",
        method: "post",
        params: query
    });
}
