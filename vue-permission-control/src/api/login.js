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

export function register(user) {
  return request({
    url: '/register',
    method: 'post',
    data: user
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

export function githubLogin(code) {
  return request({
    url: '/login/github',
    method: 'post',
    params: {
      code: code
    },
    auth: {
      username: 'client',
      password: 'secret'
    }
  })
}
