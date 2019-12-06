/**
 * 上传相关
 */
import axios from "../../utils/axios";
//上传文件upload
export function uploadFile(data) {
    return axios({
        url: "/tool/uploadpro",
        method: "post",
        data: data

    });
}
