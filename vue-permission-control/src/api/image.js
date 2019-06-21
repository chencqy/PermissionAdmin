import request from '@/utils/request'

export function uploadImage(jsonData) {
  return request({
    url: '/user/updateAvatar',
    method: 'patch',
    params: {
      headImg: jsonData.data
    }
  })
}

