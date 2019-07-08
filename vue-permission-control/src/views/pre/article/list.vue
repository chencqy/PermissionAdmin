<template>
  <div class="app-container">
    <div class="article-container">
      <el-form ref="condition" :model="condition" class="form-container">
        <el-row>
          <el-col :span="20">
            <el-form-item label-width="60px" label="Title:" >
              <el-input v-model="condition.title" placeholder="title"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10">
            <el-form-item label-width="60px" label="Author:" >
              <el-input v-model="condition.author" placeholder="author" />
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label-width="60px" label="Status:" style="margin-left: 50px">
              <el-select v-model="condition.status" clearable placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-button v-loading="listLoading" style="margin-left: 10px; margin-bottom: 20px" type="success" @click="searchArticle(condition)">
        搜索
      </el-button>
      <el-table
        v-loading="listLoading"
        :data="list"
        border
        fit
        highlight-current-row
        style="width: 100%">
        <el-table-column align="center" label="ID" width="80">
          <template slot-scope="scope">
            <span>{{ scope.row.id }}</span>
          </template>
        </el-table-column>

        <el-table-column width="180px" align="center" label="Date" prop="date" sortable>
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | formatDate }}</span>
          </template>
        </el-table-column>

        <el-table-column width="120px" align="center" label="Author">
          <template slot-scope="scope">
            <span>{{ scope.row.author }}</span>
          </template>
        </el-table-column>

        <el-table-column width="100px" label="Vote" prop="vote" sortable>
          <template slot-scope="scope">
            <svg-icon v-for="n in +scope.row.vote" :key="n" icon-class="star" class="meta-item__icon" />
          </template>
        </el-table-column>

        <el-table-column class-name="status-col" label="Status" width="110">
          <template slot-scope="{row}">
            <el-tag :type="row.status | statusFilter">
              {{ row.status }}
            </el-tag>
          </template>
        </el-table-column>

        <el-table-column min-width="300px" label="Title">
          <template slot-scope="{row}">
            <router-link :to="'/article/edit/'+row.id" class="link-type">
              <span>{{ row.title }}</span>
            </router-link>
          </template>
        </el-table-column>

        <el-table-column align="center" label="Actions" width="120">
          <template slot-scope="scope">
            <router-link :to="'/article/view/'+scope.row.id">
              <el-button type="primary" size="small" icon="el-icon-edit">
                查看
              </el-button>
            </router-link>
          </template>
        </el-table-column>
        <el-table-column align="center" label="Actions" width="120">
          <template slot-scope="scope">
            <router-link :to="'/article/edit/'+scope.row.id">
              <el-button type="primary" size="small" icon="el-icon-edit" :disabled=canEdit>
                编辑
              </el-button>
            </router-link>
          </template>
        </el-table-column>
        <el-table-column align="center" label="Actions" width="120">
          <template slot-scope="scope">
            <el-button type="danger" size="small" icon="el-icon-delete" @click="deleteArticle(scope.row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getList" />
  </div>
</template>

<script>
import { getArticleList } from '@/api/article'
import { searchArticleList } from '@/api/article'
import { deleteArticle } from '@/api/article'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'ArticleList',
  components: { Pagination },
  filters: {
    formatDate(time) {
      const moment = require('moment')
      return moment(time).format('YYYY-MM-DD HH:mm:ss')
    },
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      condition: {
        title: '',
        author: '',
        status: ''
      },
      total: 0,
      listLoading: true,
      listQuery: {
        pageNum: 1,
        pageSize: 20
      },
      options: [{
        value: 'published',
        label: 'published'
      }, {
        value: 'draft',
        label: 'draft'
      }],
      value: '',
      canEdit: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getArticleList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
        this.listLoading = false
      })
    },
    searchArticle(condition) {
      this.listLoading = true
      searchArticleList(this.listQuery, condition).then(response => {
        this.list = response.data.list
        this.total = response.data.total
        this.listLoading = false
      })
    },
    deleteArticle(id) {
      this.listLoading = true
      deleteArticle(id).then(response => {
        this.$notify({
          title: '成功',
          message: '删除文章成功',
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
      // location.reload()
    }
  }
}
</script>

<style scoped>
  .edit-input {
    padding-right: 100px;
  }
  .cancel-btn {
    position: absolute;
    right: 15px;
    top: 10px;
  }
</style>
