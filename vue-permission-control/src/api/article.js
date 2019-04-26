import request from '@/utils/request'

export function getArticleList() {
  return request({
    url: '/article/list',
    method: 'get'
  })
}
