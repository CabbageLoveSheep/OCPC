/**
 * Created by gy on 2018/5/22.
 */
import axios from 'axios'
import { serverUrl, API_FAILED } from './config'

import { getCookie } from '../common/js/util/util'
import { baseUrl } from '../common/env'

export function get(r, p) {
    // let url = serverUrl.api + "/" + r;//http://192.168.0.200:8082/pbx-core-facade/v1/m/supplier/all?param=%7b%22loginId%22:%22admin1%22%7d

    console.log(r)
    let url = baseUrl + r

    serverUrl.req.param = p
    var jsonstr = JSON.stringify(serverUrl.req.param)
    var jsonstr2 = JSON.stringify(serverUrl.req.requestId)

    var finalJson = 'param=' + jsonstr.replace(/\{/g, '%7b').replace(/\}/g, '%7d').replace(/\"/g, '%22') + '&requestId=' +
        jsonstr2.replace(/\{/g, '%7b').replace(/\}/g, '%7d').replace(/\"/g, '%22')
    url = url + '?' + finalJson

    // 获取Cookie
    var authorization = getCookie('authorization')

    // 创建新的实例来添加请求头消息
    var instance = axios.create({
        baseURL: url,
        method: 'get',
        headers: { 'authorization': authorization }
    })

    return instance.get(url).then(res => {
        console.log('get result')
        return res.data
    }, res => {
        return JSON.parse(res.data)
    }).catch((res) => {
        return API_FAILED
    })

    // return axios({
    //   method: 'get',
    //   url: url,
    //   params: serverUrl.req
    // }).then((res) => {
    //   //return  Promise.resolve(res.data);
    //   //console.log(res)
    //   return res.data;
    // }).catch((res) => {
    //   return API_FAILED
    // })
}
