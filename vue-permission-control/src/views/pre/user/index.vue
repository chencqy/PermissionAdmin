<template>
  <div class="app-container">
    <div class="user-container">
      <el-form ref="condition" :model="condition" class="form-container">
        <el-row>
          <el-col :span="20">
            <el-form-item label-width="60px" label="Title:" >
              <el-input v-model="condition.userName" placeholder="用户名"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-button v-loading="listLoading" style="margin-left: 10px; margin-bottom: 20px" type="success" @click="searchUserList(condition)">
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

        <el-table-column width="120px" align="center" label="Author">
          <template slot-scope="scope">
            <span>{{ scope.row.accountName }}</span>
          </template>
        </el-table-column>

        <el-table-column width="120px" align="center" label="type">
          <el-radio v-model="scope.row.userType" label="1">管理员</el-radio>
          <el-radio v-model="scope.row.userType" label="0">普通用户</el-radio>
        </el-table-column>


        <el-table-column align="center" label="Actions" width="120">
          <template slot-scope="scope">
              <el-button type="primary" size="small" icon="el-icon-edit">
                更新
              </el-button>
          </template>
        </el-table-column>
        <!--<el-table-column align="center" label="Actions" width="120">-->
          <!--<template slot-scope="scope">-->
            <!--<el-button type="danger" size="small" icon="el-icon-delete" @click="deleteArticle(scope.row.id)">-->
              <!--删除-->
            <!--</el-button>-->
          <!--</template>-->
        <!--</el-table-column>-->
      </el-table>
    </div>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getList" />
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import { searchUser } from '@/api/user'
import { getUserList } from '@/api/user'

export default {
  components: { Pagination },
  data() {
    return {
      list: null,
      condition: {
        userName: '',
      },
      total: 0,
      listLoading: true,
      listQuery: {
        pageNum: 1,
        pageSize: 20
      },
    }
  },
  created() {
    this.getUser()
  },
  mounted() {},
  methods: {
    searchUserList(condition) {
      this.listLoading = true
      searchUser(this.listQuery, condition).then(response => {
        this.list = response.data.list
        this.total = response.data.total
        this.listLoading = false
      })
    },
    getUser() {
      this.listLoading = true
      getUserList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
        this.listLoading = false
      })
    },
  }
}

</script>
<style lang='scss' scoped>
</style>

