/* service/loginAPI.js */
import axios from 'axios'

const getUserInfo = (userId, userPw, loginPath) => {
  const reqData = {
    'user_id': userId,
    'user_pw': userPw,
    'login_path': loginPath
  }

  let serverUrl = '//localhost:8091'
  if(loginPath == 'google') {
    return axios.post(serverUrl + '/member/login', reqData, {
      headers: {
        'Content-type': 'application/json'
      }
    })
  }
  else {
    return axios.post(serverUrl + '/member/login', reqData, {
      headers: {
        'Content-type': 'application/json'
      }
    })
  }
}

export default {
  async doLogin(userId, userPw) {
    let loginPath = 'internal'
    try {
      const getUserInfoPromise = getUserInfo(userId, userPw, loginPath)
      const [userInfoResponse] = await Promise.all([getUserInfoPromise])
      if (userInfoResponse.data.length === 0) {
        return 'notFound'
      } else {
        localStorage.setItem('user_token', userInfoResponse.data.user_token)
        localStorage.setItem('user_role', userInfoResponse.data.user_role)
        return userInfoResponse
      }
    } catch (err) {
      console.error(err)
    }
  }, 

  async doLoginByGoogle(userId, userPw) {
    let loginPath = 'google'
    try {
      const getUserInfoPromise = getUserInfo(userId, userPw, loginPath)
      const [userInfoResponse] = await Promise.all([getUserInfoPromise])
      if (userInfoResponse.data.length === 0) {
        return 'notFound'
      } else {
        localStorage.setItem('user_token', userInfoResponse.data.user_token)
        localStorage.setItem('user_role', userInfoResponse.data.user_role)
        return userInfoResponse
      }
    } catch (err) {
      console.error(err)
    }
  }
}