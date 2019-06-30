<template>
  <div class="app-container">
    <div class="user-container">
      <el-form ref="condition" :model="condition" class="form-container">
        <el-row>
          <el-col :span="20">
            <el-form-item label-width="10px" >
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

        <el-table-column width="220px" align="center" label="Name">
          <template slot-scope="scope">
            <span>{{ scope.row.accountName }}</span>
          </template>
        </el-table-column>

        <el-table-column width="420px" align="center" label="Type">
          <template slot-scope="scope">
            <el-radio-group v-model="scope.row.userType">
              <el-radio :label="1">管理员</el-radio>
              <el-radio :label="0">普通用户</el-radio>
            </el-radio-group>
          </template>
        </el-table-column>

        <el-table-column align="center" label="Actions" width="120">
          <template slot-scope="scope">
              <el-button type="primary" size="small" icon="el-icon-edit" @click="updateUserType(scope.row.id, scope.row.userType)">
                更新
              </el-button>
          </template>
        </el-table-column>
        <el-table-column align="center" label="Actions" width="120">
          <template slot-scope="scope">
            <el-button type="danger" size="small" icon="el-icon-delete" @click="deleteUser(scope.row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getUser" />
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import { searchUser } from '@/api/user'
import { getUserList } from '@/api/user'
import { deleteUser } from '@/api/user'
import { updateUserType } from '@/api/user'

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
      radio: '1'
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
        debugger
        this.list = response.data.list
        this.total = response.data.total
        this.listLoading = false
      })
    },
    deleteUser(id) {
      this.listLoading = true
      deleteUser(id).then(response => {
        this.$notify({
          title: '成功',
          message: '删除用户成功',
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
    updateUserType(id, userType) {
      this.listLoading = true
      updateUserType(id, userType).then(response => {
        this.$notify({
          title: '成功',
          message: '更新用户类型成功',
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
  }
}

</script>
<style lang='scss' scoped>
</style>

