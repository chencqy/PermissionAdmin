<template>
  <div class="dashboard-container">
    <div class="dashboard-text" style="text-align: center">Welcome, {{ name }}</div>
    <div class="user-content">
      <div class="avatar" @click="toggleShow()">
        <img :src="avatar">
        <p class="head-btn">修改头像</p>
      </div>
      <div class="info">
        <el-form ref="userInfo" :model="userInfo" label-position="top" label-width="80px">
          <el-form-item label="用户名">
            <el-input v-model="userInfo.name"/>
          </el-form-item>
          <el-form-item label="用户主页">
            <el-input v-model="userInfo.url"/>
          </el-form-item>
          <el-form-item label="邮箱地址">
            <el-input v-model="userInfo.email"/>
          </el-form-item>
          <el-form-item label="个人简介">
            <el-input v-model="userInfo.info" :rows="3" type="textarea"/>
          </el-form-item>
          <el-button type="primary" class="update-button" @click="updateInfo">更新</el-button>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import store from '../../store'
import { Message } from 'element-ui'
import { updateUserInfo } from '@/api/user'

export default {
  name: 'Dashboard',
  data() {
    return {
      avatar: '',
      show: false,
      userInfo: {
        id: null,
        name: null,
        avatar: null,
        email: null,
        info: null
      }
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  },
  mounted() {
    this.getUserInfo()
  },
  methods: {
    toggleShow() {
      this.show = !this.show
    },
    getUserInfo() {
      store.dispatch('GetInfo').then(res => { // 拉取user_info
        this.userInfoSet(res.data.data)
        this.avatar = res.data.data.avatar
      }).catch((err) => {
        store.dispatch('FedLogOut').then(() => {
          Message.error(err || 'get user information failed, please try again')
        })
      })
    },
    userInfoSet(e) {
      debugger
      this.userInfo.id = e.id
      this.userInfo.avatar = e.avatar
      this.userInfo.name = e.accountName
      this.userInfo.url = e.url
      this.userInfo.email = e.email
      this.userInfo.info = e.info
    },
    updateInfo() {
      this.loading = true
      updateUserInfo(this.userInfo).then(response => {
        this.$notify({
          title: '成功',
          message: '更新用户信息成功',
          type: 'success',
          duration: 2000
        })
        this.loading = false
      }).catch(err => {
        console.log(err)
      })
      const { fullPath } = this.$route
      this.$router.replace({
        path: '/redirect' + fullPath
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
.user-content {
  width: 550px;
  margin:0 auto;
  height: 625px;
}
.user-content .title-text {
  color: #24292e;
}
.user-content .info {
  padding:16px 16px;
  float: left;
  width: 400px;
  color: #24292e;
}
.user-content .info label {
  color: #24292e;
}
.user-content .avatar {
  float: left;
  margin-top: 16px;
  padding: 0 16px;
  cursor: pointer;
}
.user-content .avatar img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin: 0 auto;
}
.user-content .update-button {
  width: 100%;
}
.head-btn {
  padding: 5px 15px 6px;
  font-size: 12px;
  border-radius: 4px;
  color: #47cb89;
  text-align: center;
  border: 1px solid #47cb89;
}
.head-btn:hover{
  background: rgba(244,252,248,.5);
}
</style>
