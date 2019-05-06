import request from '@/utils/request'

export function getArticleList(query) {
  return request({
    url: '/article/list',
    method: 'get',
    params: query
  })
}
