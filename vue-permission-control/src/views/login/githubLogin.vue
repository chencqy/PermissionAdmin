<template>
  <div>
  </div>
</template>

<script>
import { githubLogin } from '@/api/login'
import { setToken } from '@/utils/auth'

export default {
  name: "GithubLogin",
  mounted() {
  },
  created() {
    const code = this.$route.query.code
    this.githubLogin(code)
  },
  methods: {
    githubLogin(code) {
      this.$notify({
        title: '登录',
        message: '正在登录...',
        position: 'top-left',
        duration: 2000
      })
      githubLogin(code).then(
        function (response) {
          const data = response.data
          setToken(data.access_token)
          this.$store.commit('SET_TOKEN', data.access_token)
          this.$router.push({ path: "/" })
          this.$message.success("登陆成功")
        }.bind(this)
      ).catch(
        function(error) {
          this.$message.error("登陆失败")
        }.bind(this)
      )
    },
  }
}
</script>

<style scoped>

</style>
