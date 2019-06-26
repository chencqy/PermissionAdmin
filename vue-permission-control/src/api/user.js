import request from '@/utils/request'

export function updateUserInfo(user) {
  return request({
    url: '/user/update',
    method: 'post',
    data: user
  })
}

export function searchUser(query, list) {
  return request({
    url: '/article/search',
    method: 'get',
    params: {
      pageNum: query.pageNum,
      pageSize: query.pageSize,
      title: list.title,
      author: list.author,
      status: list.status
    }
  })
}

export function getUserList(query) {
  return request({
    url: '/user/list',
    method: 'get',
    params: query
  })
}
