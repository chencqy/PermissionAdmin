<template>
  <div>
    github登录
  </div>
</template>

<script>
import { githubLogin } from '@/api/login'
import { setToken } from '@/utils/auth'

export default {
  name: "GithubLogin",
  mounted() {
    setTimeout(function () {
      console.log(this.$route)
    }, 10000)

  },
  created() {
    debugger
    const code = this.$route.query.code
    this.githubLogin(code)
  },
  methods: {
    githubLogin(code) {
      githubLogin(code).then(
        function (response) {
          debugger
          const data = response.data
          setToken(data.access_token)
          this.$store.commit('SET_TOKEN', data.access_token)
          this.$router.push({ path: "/" })
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
