import ajax from './http'
import * as api from './api'

// 使用函数封装
export const test = (data) => ajax({
  method: 'GET',
  url: api.movieOnInfoList,
  params: data,
  name: 'test'
});
