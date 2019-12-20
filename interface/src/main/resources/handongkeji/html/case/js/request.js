// axios 请求拦截
axios.interceptors.request.use(
    config => {
      return config
    },
    error => {
      return Promise.eror(error)
  })
  let that=this;
  // axios 响应拦截
  axios.interceptors.response.use(
    response => {
     
      if(response.data.status == 602){
        // ViewUI.Message.config({
        //   top:50,
        //   duration: 0.2
        // });
        // ViewUI.Message.destroy();
        // ViewUI.Message.error('您的账户已在其他设备登录，请重新登录')
        setTimeout(()=>{
          router.push({path: '/'})
        },1500)
        localStorage.setItem('Authorization','');
        localStorage.setItem('userId','');
      }
      return Promise.resolve(response)
      
    },
    error => {
      return Promise.reject(error.response);
    }
  )
  
  /*
  * get、post
  * url(string) [请求地址]
  * params(Object) [传参]
  */
  
  // get
  function get(url,params){
    return new Promise((resolve,reject)=>{
      axios.get(url,{
        params: params
      }).then(res=>{
        resolve(res.data)
      }).catch(err=>{
        reject(err.data)
      })
    })
  }
  // post
  function post(url,params){
    return new Promise((resolve,reject)=>{
      axios.post(url, QS.stringify(params))
      .then(res=>{
        resolve(res.data)
      })
      .catch(err=>{
        reject(err.data)
      })
    })
  }