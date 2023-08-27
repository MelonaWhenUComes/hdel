<template>
    <div>
      <div>
        <h2>Please Log In</h2>
        <div id="loginForm">
          <form @submit.prevent="fnLogin">
            <p>
              <input class="w3-input" name="uid" placeholder="Enter your ID" v-model="user_id"><br>
            </p>
            <p>
              <input name="password" class="w3-input" placeholder="Enter your password" v-model="user_pw" type="password">
            </p>
            <p>
              <button type="submit" class="w3-button w3-green w3-round">Login</button>
            </p>
          </form>
          <!-- -->
          <form>
            <a href="http://localhost:8091/oauth2/authorize/google"> google login </a>
          </form>
          
        </div>
        <div class="common-buttons">
          <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="googleLogin">Google</button>&nbsp;
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import {mapActions, mapGetters} from 'vuex'
  
  export default {
    data() {
      return {
        user_id: '',
        user_pw: ''
      }
    },
    methods: {
      ...mapActions(['login']),
  
      async fnLogin() {
        if (this.user_id === '') {
          alert('ID를 입력하세요.')
          return
        }
  
        if (this.user_pw === '') {
          alert('비밀번호를 입력하세요.')
          return
        }
  
        try {
          let loginResult = await this.login({user_id: this.user_id, user_pw: this.user_pw})
          if (loginResult) this.goToPages()
        } catch (err) {
          if (err.message.indexOf('Network Error') > -1) {
            alert('서버에 접속할 수 없습니다. 상태를 확인해주세요.')
          } else {
            alert('로그인 정보를 확인할 수 없습니다.')
          }
        }
      },
      goToPages() {
        this.$router.push({
          name: 'BoardList'
        })
      }, 
      
      /////////////////////////////////
      googleLogin() {
        let apiUrl = this.$serverUrl + '/oauth2/authorize/google'
        this.form = {
          "idx": this.idx,
          "title": this.title,
          "contents": this.contents,
          "author": this.author
        }
      this.$axios.post(apiUrl, this.form)
      .then((res) => {
          alert('OOOOOOOOOOOOOOOO')
          this.fnView(res.data.idx)
        }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })
    }

    },
    computed: {
      ...mapGetters({
        errorState: 'getErrorState'
      })
    }
  }
  </script>
  
  <style>
  #loginForm {
    width: 500px;
    margin: auto;
  }
  </style>