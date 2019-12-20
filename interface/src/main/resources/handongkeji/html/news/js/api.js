let baseUrl="http://192.168.1.127:8886"  //测试


function ajax(url, data, params) {
    return new Promise(function(resolve, reject) {
        $.ajax({
            url: baseUrl + url,
            type: params && params.type || 'post',
            dataType: params && params.dataType || 'JSON',
            data: data,
            success: function(res) {
                resolve(res)
            },
            error: function(res) {
                //console(res.m)
            }
        });
    });
}