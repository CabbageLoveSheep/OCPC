/**
 * Created by gy on 2018/5/22.
 */
export const commonParams = {
  g_tk: 1928093487,
  inCharset: 'utf-8',
  outCharset: 'utf-8',
  notice: 0,
  format: 'jsonp'
}

export const serverUrl = {
  // api: 'http://192.168.0.115:8088/login/'
  api: '/api',
  req: {
    param: {},
    requestId: {}
  }
}

export const options = {
  param: 'jsonpCallback'
}

export const API_SUCCESS = '000000'
export const API_FAILED = { retCode: '000009' }
export const ERR_OK = 0
