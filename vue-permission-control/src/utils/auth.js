import Cookies from 'js-cookie'

const TokenKey = 'access_token'
const RefreshTokenKey = 'access_token'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function getRefreshToken() {
  return Cookies.get(RefreshTokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
