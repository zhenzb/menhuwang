let baseUrl="http://127.0.0.1"  //测试


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