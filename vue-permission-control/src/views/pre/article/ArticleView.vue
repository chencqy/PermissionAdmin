<template>
  <div class="main container">
    <div class="news_details">
      <div v-loading="listLoading" :data="list" >
        <h1>{{ list.title }}</h1>
        <p style="text-align: center; font-size: 15px" >发布时间: {{ list.createTime | formatDate }}</p>
        <div class="news_content" >
          <p v-html="list.content"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { fetchArticle } from '@/api/article'

export default {
  name: 'ArticleView',
  components: {},
  filters: {
    formatDate(time) {
      const moment = require('moment')
      return moment(time).format('YYYY-MM-DD HH:mm:ss')
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,
      tempRoute: {}
    }
  },
  computed: {},
  created() {
    this.listLoading = true
    const id = this.$route.params && this.$route.params.id
    this.fetchData(id)
    this.tempRoute = Object.assign({}, this.$route)
    this.listLoading = false
  },
  methods: {
    fetchData(id) {
      fetchArticle(id).then(response => {
        this.list = response.data
        debugger
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style scoped>
h1 {
  text-align: center;
  font-size: 220%;
}

.news_content {
  padding: 50px;
  margin: 10px;
  line-height: 2;
}
</style>
