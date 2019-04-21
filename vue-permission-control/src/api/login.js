import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/oauth/token',
    method: 'post',
    params: {
      'username': username,
      'password': password,
      'grant_type': 'password',
      'scope': 'all'
    },
    auth: {
      username: 'client',
      password: 'secret'
    }
  })
}

export function getInfo(access_token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { access_token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
