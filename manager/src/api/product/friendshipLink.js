import axios from "../../utils/axios";

export function getFriendshipLink(query) {
    return axios({
        url: "/friendshipLink/getFriendshipLink",
        method: "get",
        params: query
    });
}

export function addFriendshipLink(data) {
    return axios({
        url: "/friendshipLink/addFriendshipLink",
        method: "post",
        data: data
    });
}

export function deleteFriendshipLink(query) {
    return axios({
        url: "/friendshipLink/deleteFriendshipLink",
        method: "get",
        params: query
    });
}
