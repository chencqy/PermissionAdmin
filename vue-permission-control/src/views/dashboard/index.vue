<template>
  <div class="dashboard-container">
    <div class="dashboard-text" style="text-align: center">Welcome, {{ name }}</div>
    <div class="user-content">
      <div class="avatar">
        <p class="head-btn" @click="toggleShow()">设置头像</p>
        <my-upload ref="upload"
                   field="picture"
                   @crop-upload-success="cropUploadSuccess"
                   @crop-upload-fail="cropUploadFail"
                   v-model="show"
                   :width="300"
                   :height="300"
                   url="http://47.110.10.150/api/user/avatar/upload"
                   :headers="headers"
                   img-format="png"></my-upload>
        <img :src="avatar">
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
import { uploadImage } from '@/api/image'
import myUpload from 'vue-image-crop-upload'
import { getToken } from '@/utils/auth'
import 'babel-polyfill'

export default {
  name: 'Dashboard',
  components: {
    'my-upload': myUpload
  },
  data() {
    return {
      avatar: '',
      show: false,
      userInfo: {
        id: null,
        name: null,
        avatar: '',
        email: null,
        info: null
      },
      headers: {
        Authorization: 'Bearer ' + getToken()
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
    },
    /**
     * upload success
     *
     * [param] jsonData   服务器返回数据，已进行json转码
     * [param] field
     */
    cropUploadSuccess(jsonData, field) {
      uploadImage(jsonData).then(response => {
        this.avatar = jsonData.data
        this.show = false
        this.$notify({
          title: '成功',
          message: '更新头像成功',
          type: 'success',
          duration: 2000
        })
        this.$refs.upload.off()
      }).catch(err => {
        console.log(err)
      })
    },
    /**
     * upload fail
     *
     * [param] status    server api return error status, like 500
     * [param] field
     */
    cropUploadFail(status, field) {
      this.$notify({
        title: '失败',
        message: '更新头像失败',
        type: 'error',
        duration: 2000
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
