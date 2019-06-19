import request from '@/utils/request'

export function updateUserInfo(user) {
  return request({
    url: '/user/update',
    method: 'post',
    data: user
  })
}

