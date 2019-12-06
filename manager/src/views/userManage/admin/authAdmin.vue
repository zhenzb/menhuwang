<template>
  <div style="width: 95%; margin-left: 2%;">
    <el-form :inline="true" :model="query" class="query-form" size="small">
      <el-form-item class="query-form-item">
        <el-input
          v-model="query.username"
          placeholder="请输入用户名"
          prefix-icon="el-icon-search"
          @keyup.enter.native="onSubmit"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item class="query-form-item">
        <el-select v-model="query.status" placeholder="请选择状态" @change="onSubmit" clearable>
          <el-option label="全部" value></el-option>
          <el-option label="禁用" value="0"></el-option>
          <el-option label="正常" value="1"></el-option>
<!--          <el-option label="未验证" value="2"></el-option>-->
        </el-select>
      </el-form-item>
      <el-form-item class="query-form-item">
        <el-select v-model="query.roleId" placeholder="请选择角色" @change="onSubmit" clearable>
          <el-option label="全部角色" value></el-option>
          <el-option v-for="item in roles" :key="item.id" :label="item.name" :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-refresh" @click="onRest">刷新</el-button>
        <el-button type="primary" icon="el-icon-search" @click="onSubmit">查询</el-button>
        <el-button type="primary" icon="el-icon-edit" @click.native="handleForm(null,null)">添加</el-button>
      </el-form-item>
    </el-form>
    <!-- <el-scrollbar class="table-scrollbar" wrap-style="overflow-x: hidden;"> -->
    <el-table
      ref="multipleTable"
      v-loading="loading"
      :data="list"
      :header-cell-style="{background:'#F4F4F5',color:'#606266'}"
      @selection-change="handleSelectionChange"
      border
    >
      <el-table-column type="selection" width="50" align="center" fixed/>
      <el-table-column label="用户 ID" prop="id" width="80" align="center"/>
      <el-table-column label="用户名" prop="username" align="center"/>
      <el-table-column label="状态" align="center" width="100">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.status | statusFilterType"
          >{{scope.row.status | statusFilterName}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="登录时间" with="300" :show-overflow-tooltip="true" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time" v-if="scope.row.lastLoginTime!=null"></i>
          <span
            v-if="scope.row.lastLoginTime!=null"
          >{{ scope.row.lastLoginTime | formatDateStr('yyyy-MM-dd hh:mm:ss') }}</span>
          <span v-else>暂未登录</span>
        </template>
      </el-table-column>
      <el-table-column label="登录IP" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.lastLoginTime!=null">{{ scope.row.lastLoginIp }}</span>
          <span v-else>暂未登录</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            @click.native="handleForm(scope.$index, scope.row)"
          >编辑</el-button>
          <el-button
            type="danger"
            size="mini"
            :disabled="scope.row.id!=1?false:true"
            @click.native="handleDel(scope.row.id)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="left-container">
      <el-button type="danger" size="small" @click="delGroup()">删除已选</el-button>
      <transition name="el-fade-in-linear">
        <el-button size="small" @click="toggleSelection()" v-show="cancelToggle">取消选择</el-button>
      </transition>
    </div>
    <el-pagination
      class="pagination-container"
      background
      :page-size="query.pageSize"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      layout="total,sizes,prev, pager, next, jumper"
      :current-page="this.query.currentPage"
      :page-sizes="[10, 20, 30, 50]"
      :total="totalNumber"
    ></el-pagination>
    <!-- </el-scrollbar> -->
    <!--表单-->
    <el-dialog
      :title="formMap[formName]"
      :visible.sync="formVisible"
      :before-close="hideForm"
      width="1000px"
      top="5vh"
      v-dialogDrag
    >
      <el-form :model="formData" :rules="formRules" ref="dataForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="formData.username" auto-complete="off" placeholder="请输入用户名" clearable></el-input>
        </el-form-item>
        <el-form-item label="登录密码" prop="password">
          <el-input
            type="password"
            v-model="formData.password"
            placeholder="请输入登录密码"
            auto-complete="off"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPassword">
          <el-input
            type="password"
            v-model="formData.checkPassword"
            placeholder="请再次输入密码"
            auto-complete="off"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="formData.status">
            <el-radio :label="0">禁用</el-radio>
            <el-radio :label="1">正常</el-radio>
<!--            <el-radio :label="2">未验证</el-radio>-->
          </el-radio-group>
        </el-form-item>
        <el-form-item label="角色" v-if="formData.id!=1">
          <el-checkbox-group v-model="formData.roles">
            <el-checkbox v-for="item in roles" :key="item.id" :label="item.id">{{item.name}}</el-checkbox>
          </el-checkbox-group>
          <!-- <el-radio-group  v-model="formData.roles">
            <el-radio v-for="item in roles" :key="item.id" :label="item.id">{{item.name}}</el-radio>
          </el-radio-group>-->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="hideForm">取消</el-button>
        <el-button type="primary" @click.native="formSubmit()" :loading="formLoading">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
    authAdminList,
    authAdminRoleList,
    authAdminSave,
    authAdminDelete
} from "../../../api/auth/authAdmin";
const formJson = {
    id: "",
    password: "",
    username: "",
    checkPassword: "",
    status: 1,
    roles: []
};
export default {
    data() {
        let validatePass = (rule, value, callback) => {
            if (value === "") {
                callback(new Error("请输入密码"));
            } else {
                callback();
            }
        };
        let validatePass2 = (rule, value, callback) => {
            if (value === "") {
                callback(new Error("请再次输入密码"));
            } else if (value !== this.formData.password) {
                callback(new Error("两次输入密码不一致!"));
            } else {
                callback();
            }
        };
        let validatePass3 = (rule, value, callback) => {
            if (this.formData.password !== "" && value === "") {
                callback(new Error("请再次输入密码"));
            } else if (value !== this.formData.password) {
                callback(new Error("两次输入密码不一致!"));
            } else {
                callback();
            }
        };
        let validatePass4 = (rule, value, callback) => {
            if (value === "" && this.formData.checkPassword !== "") {
                callback(new Error("请输入密码"));
            } else {
                callback();
            }
        };
        return {
            roles: [],
            query: {
                username: "",
                status: "",
                currentPage: 1,
                pageSize: 10,
                roleId: ""
            },
            list: [],
            totalNumber: 0,
            loading: true,
            index: null,
            formName: null,
            formMap: {
                add: "添加",
                edit: "编辑"
            },
            cancelToggle: false,
            formLoading: false,
            formVisible: false,
            formData: formJson,
            formRules: {},
            addRules: {
                username: [
                    {
                        required: true,
                        message: "请输入用户名",
                        trigger: "blur"
                    },
                    {
                        min: 5,
                        max: 20,
                        message: "账号最少字符5，最大20",
                        trigger: "blur"
                    }
                ],
                password: [
                    { required: true, message: "请输入密码", trigger: "blur" },
                    { validator: validatePass, trigger: "blur" }
                ],
                checkPassword: [
                    {
                        required: true,
                        message: "请再次输入密码",
                        trigger: "blur"
                    },
                    { validator: validatePass2, trigger: "blur" }
                ],
                status: [
                    { required: true, message: "请选择状态", trigger: "change" }
                ]
            },
            editRules: {
                username: [
                    { required: true, message: "请输入用户名", trigger: "blur" }
                ],
                password: [{ validator: validatePass4, trigger: "blur" }],
                checkPassword: [{ validator: validatePass3, trigger: "blur" }],
                status: [
                    { required: true, message: "请选择状态", trigger: "change" }
                ]
            },
            multipleSelection: [] //多选集合
        };
    },
    methods: {
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
            if (val == "") {
                this.cancelToggle = false;
                return;
            }
            this.cancelToggle = true;
        },
        delGroup() {
            var ids = this.multipleSelection.map(item => item.id).join(); //获取所有选中行的id组成的字符串，以逗号分隔
            var idss = ids.split(",");
            for (var i = 0; i < idss.length; i++) {
                if ("1" === idss[i]) {
                    this.$message({
                        message: "不能删除管理员",
                        type: "warning"
                    });
                    return;
                }
            }
            if (ids == "") {
                this.$message({
                    message: "请先选择",
                    type: "warning"
                });
                return;
            }
            this.handleDel(ids);
        },
        onRest() {
            this.$router.push({
                path: ""
            });
            this.query = {
                username: "",
                status: "",
                currentPage: 1,
                pageSize: 10,
                roleId: ""
            };
            this.getList();
        },
        onSubmit() {
            this.query.currentPage = 1;
            this.$router.push({
                path: ""
                //query: this.query
            });
            this.getList();
        },
        handleCurrentChange(val) {
            this.query.currentPage = val;
            //scrollTo(0, 800);
            this.getList();
        },
        handleSizeChange(val) {
            this.query.pageSize = val;
            this.getList();
        },
        getList() {
            this.loading = true;
            authAdminList(this.query)
                .then(response => {
                    if (response.code) {
                        this.$message.error(response.message);
                    }
                    this.list = response.data.list || [];
                    this.totalNumber = response.data.totalNumber || 0;
                    setTimeout(() => {
                        this.loading = false;
                    }, 0.3 * 1000);
                })
                .catch(() => {
                    this.loading = false;
                    this.list = [];
                    this.totalNumber = 0;
                    this.roles = [];
                });
        },
        getRoleList() {
            authAdminRoleList(this.query)
                .then(response => {
                    if (response.code) {
                        this.$message.error(response.message);
                        return false;
                    }
                    this.roles = response.data.list || [];
                })
                .catch(() => {});
        },
        // 隐藏表单
        hideForm() {
            // 更改值
            this.formVisible = !this.formVisible;
            // 清空表单
            this.$refs["dataForm"].resetFields();
            return true;
        },
        // 显示表单
        handleForm(index, row) {
            this.formVisible = true;
            this.formData = Object.assign({}, formJson);
            if (row !== null) {
                this.formData = Object.assign({}, row);
            }
            this.formName = "add";
            this.formRules = this.addRules;
            //编辑
            if (index !== null) {
                this.index = index;
                this.formName = "edit";
                this.formRules = this.editRules;
            }
            // 清空验证信息表单
            if (this.$refs["dataForm"]) {
                this.$refs["dataForm"].clearValidate();
            }
        },
        formSubmit() {
            this.$refs["dataForm"].validate(valid => {
                if (valid) {
                    this.formLoading = true;
                    let data = Object.assign({}, this.formData);
                    authAdminSave(data, this.formName).then(response => {
                        this.formLoading = false;
                        if (response.code) {
                            this.$message.error(response.message);
                            return false;
                        }
                        this.$message.success("操作成功");
                        // 向头部添加数据
                        // this.list.unshift(res)
                        // if (this.formName === "add") {
                        //     // 向头部添加数据
                        //     let resData = response.data || {};
                        //     this.list.unshift(resData);
                        // } else {
                        //     this.list.splice(this.index, 1, data);
                        // }
                        // 刷新表单
                        this.$refs["dataForm"].resetFields();
                        this.formVisible = false;
                        this.getList();
                    });
                }
            });
        },
        // 删除
        handleDel(ids) {
            if (ids) {
                this.$confirm("确认要删除吗?", "提示", {
                    type: "warning"
                })
                    .then(() => {
                        this.loading = true;
                        let para = { ids: ids };
                        authAdminDelete(para)
                            .then(response => {
                                this.loading = false;
                                if (response.code) {
                                    this.$message.error(response.message);
                                    return false;
                                }
                                this.$message.success("删除成功");
                                //返回第一页
                                this.query.currentPage = 1;
                                // 刷新数据
                                this.getList();
                            })
                            .catch(() => {
                                this.loading = false;
                            });
                    })
                    .catch(() => {});
            }
        }
    },
    filters: {
        statusFilterType(status) {
            const statusMap = {
                0: "gray",
                1: "success",
                2: "danger"
            };
            return statusMap[status];
        },
        statusFilterName(status) {
            const statusMap = {
                0: "禁用",
                1: "正常",
                // 2: "未验证"
            };
            return statusMap[status];
        }
    },
    mounted() {},
    created() {
        // 将参数拷贝进查询对象
        let query = this.$route.query;
        this.query = Object.assign(this.query, query);
        this.query.pageSize = parseInt(this.query.pageSize);
        // 加载表格数据
        this.getList();
        // 加载角色列表
        this.getRoleList();
    }
};
</script>

<style>
body {
    margin: 0;
}
</style>
