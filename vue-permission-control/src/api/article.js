import request from '@/utils/request'

export function getArticleList(query) {
  return request({
    url: '/article/list',
    method: 'get',
    params: query
  })
}

export function fetchArticle(id) {
  return request({
    url: '/article/detail',
    method: 'get',
    params: id
  })
}

export function addArticle(article) {
  return request({
    url: '/article/add',
    method: 'post',
    data: article
  })
}
