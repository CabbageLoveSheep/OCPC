/*
 * 配置编译环境和线上环境之间的切换
 * baseUrl: 域名地址
 */
let baseUrl = ''
let linkUrl = ''
let linkUrlPeppol = ''
let linkUrlPaymentSeller = ''
let linkUrlPaymentBuyer = ''
let DEBUG = false
if (process.env.NODE_ENV == 'unit') {
    baseUrl = 'http://127.0.0.1:8080/ocpc/'
    linkUrl = 'http://192.168.0.110:8080/passport/'
    linkUrlPeppol = 'http://192.168.0.110:8080/peppol/#'
    linkUrlPaymentSeller = 'http://192.168.0.110:8080/pbxp-seller/#'
    linkUrlPaymentBuyer = 'http://192.168.0.110:8080/pbxp-buyer/#'
    DEBUG = true
} else if (process.env.NODE_ENV == 'sit') {
    baseUrl = 'http://192.168.0.111:8080/usercenter/'
    linkUrl = 'http://192.168.0.111:8080/passport/'
    linkUrlPeppol = 'http://192.168.0.111:8080/peppol/#'
    linkUrlPaymentSeller = 'http://192.168.0.111:8080/pbxp-seller/#'
    linkUrlPaymentBuyer = 'http://192.168.0.111:8080/pbxp-buyer/#'
    DEBUG = true
} else if (process.env.NODE_ENV == 'pre') {
    baseUrl = 'https://www.asiapbx.com/usercenter/'
    linkUrl = 'https://www.asiapbx.com/passport/'
    linkUrlPeppol = 'https://www.asiapbx.com/peppol/#'
    linkUrlPaymentSeller = 'https://www.asiapbx.com/pbxp-seller/#'
    linkUrlPaymentBuyer = 'https://www.asiapbx.com/pbxp-buyer/#'
    DEBUG = false
} else if (process.env.NODE_ENV == 'demo') {
    baseUrl = 'http://192.168.0.113:8180/pbx-buyer-reg-web/'
    DEBUG = false
} else if (process.env.NODE_ENV == 'uat') {
    baseUrl = 'http://pbxuat.myddns.me:9988/usercenter/'
    linkUrl = 'http://pbxuat.myddns.me:9988/passport/'
    linkUrlPeppol = 'http://pbxuat.myddns.me:9988/peppol/#'
    linkUrlPaymentSeller = 'http://pbxuat.myddns.me:9988/pbxp-seller/#'
    linkUrlPaymentBuyer = 'http://pbxuat.myddns.me:9988/pbxp-buyer/#'
    DEBUG = true
} else if (process.env.NODE_ENV == 'prod') {
    baseUrl = 'https://pbx.pracbiz.com/pbx-buyer-reg-web/'
    DEBUG = false
} else if (process.env.NODE_ENV == 'pro') {
    baseUrl = 'http://www.asiapbx.com/usercenter/'
    linkUrl = 'http://www.asiapbx.com/passport/'
    linkUrlPeppol = 'http://www.asiapbx.com/peppol/#'
    linkUrlPaymentSeller = 'http://www.asiapbx.com/pbxp-seller/#'
    linkUrlPaymentBuyer = 'http://www.asiapbx.com/pbxp-buyer/#'
    DEBUG = false
}

export {
    baseUrl,
    linkUrl,
    linkUrlPeppol,
    linkUrlPaymentSeller,
    linkUrlPaymentBuyer,
    DEBUG
}
