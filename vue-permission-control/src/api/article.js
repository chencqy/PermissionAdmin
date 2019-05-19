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
    params: { id: id }
  })
}

export function addArticle(article) {
  return request({
    url: '/article/add',
    method: 'post',
    data: article
  })
}

export function updateArticle(article) {
  return request({
    url: '/article/update',
    method: 'post',
    data: article
  })
}

export function deleteArticle(id) {
  return request({
    url: '/article/' + id,
    method: 'delete'
  })
}
