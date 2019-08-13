import request from '@/utils/request'


export function visitCount(app, ip, uri) {
  return request({
    url: '/visit/count',
    method: 'get',
    params: {
      app: app,
      ip: ip,
      uri: uri
    }
  })
}
