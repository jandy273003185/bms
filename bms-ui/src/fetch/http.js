// 二次封装axios
import axios from 'axios'

// 将token添加到请求头
axios.defaults.headers.common['token'] = 'token';

// url添加初始url
axios.defaults.baseURL = '/api'
// 超时设置
axios.defaults.timeout = 10000

// http 请求 拦截器
axios.interceptors.request.use(
  config => {
    // 添加请求拦截需要的逻辑，如添加token等等
    return config
  },
  err => Promise.reject(err)
)

// 响应拦截器即异常处理
axios.interceptors.response.use(response => {
  return response
}, err => {
  if (err && err.response) {
    switch (err.response.status) {
      case 400:
        console.log('错误请求')
        break
      case 401:
        console.log('未授权，请重新登录')
        break
      case 403:
        console.log('拒绝访问')
        break
      case 404:
        console.log('请求错误,未找到该资源')
        break
      case 405:
        console.log('请求方法未允许')
        break
      case 408:
        console.log('请求超时')
        break
      case 500:
        console.log('服务器端出错')
        break
      case 501:
        console.log('网络未实现')
        break
      case 502:
        console.log('网络错误')
        break
      case 503:
        console.log('服务不可用')
        break
      case 504:
        console.log('网络超时')
        break
      case 505:
        console.log('http版本不支持该请求')
        break
      default:
        console.log(`连接错误${err.response.status}`)
    }
  } else {
    console.log('连接到服务器失败')
  }
  return Promise.resolve(err.response)
})

export default ({
  url = '',
  method = 'post',
  data = {},
  name = '',
  params = {}
}) => {
  const _ops = method.toUpperCase() === 'GET' ? { url, method, params, name } : { url, method, data, params, name };
  return new Promise((resolve, reject) => {
    // const _ops = method.toUppercase() === 'GET' ? { url: host + url, method, params, name } : { url: host + url, method, data, params, name };
    axios(_ops)
      .then((res) => {
        // 输出请求内容，便于查看
        console.groupCollapsed(`[${method}]] [${name}] `, res.data) // console输出请求名
        console.log(`%c${JSON.stringify(data, null, '\t')}`, 'font-style:italic;color:#666') // console输出请求体
        console.log(`%c${JSON.stringify(res.data, null, '\t')}`, 'color:green') // console输出响应体
        console.groupEnd()
        resolve(res)
      })
      .catch((err) => {
        reject(err)
      })
  })
}
