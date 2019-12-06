<template>
    <div>
        <!--查询条件start --111s-->
        <el-form :inline="true" :model="query" class="query-form" size="small">
            <el-form-item class="query-form-item">
                <el-input
                    v-model="query.ipaddress"
                    placeholder="请输入关键字"
                    prefix-icon="el-icon-search"
                    @keyup.enter.native="onSubmit"
                    clearable
                ></el-input>
            </el-form-item>
            <el-form-item class="query-form-item">
                <el-select v-model="query.ipstats">
                    <el-option label="全部" value="-1"></el-option>
                    <el-option label="正常" value="0"></el-option>
                    <el-option label="禁止登陆" value="1"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item>
                <el-date-picker
                    v-model="createTime"
                    type="datetimerange"
                    :picker-options="pickerOptions"
                    range-separator="-"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    align="left"
                    format="yyyy-MM-dd HH:mm:ss"
                    value-format="yyyy-MM-dd HH:mm:ss"
                    :default-time="['00:00:00','23:59:59']"
                    @change="dateChange"
                ></el-date-picker>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" icon="el-icon-refresh" @click="onRest">刷新</el-button>
                <el-button type="primary" icon="el-icon-search" @click="onSubmit">查询</el-button>
                <el-button type="primary" icon="el-icon-edit" @click.native="showDialogAddOrEdit(null,null)">添加
                </el-button>
            </el-form-item>
        </el-form>
        <!--查询条件end --111e-->

        <!--列表start --222s-->
        <el-table
            ref="multipleTable"
            v-loading="loading"
            :data="list"
            :header-cell-style="{background:'#F4F4F5',color:'#606266'}"
            @selection-change="handleSelectionChange"
            border
        >
            <el-table-column type="selection" width="50" fixed/>
            <el-table-column label="编号" prop="number" width="80" align="center"/>
            <el-table-column label="登陆IP地址" prop="ipaddress" align="center"/>
            <el-table-column label="访问时间" with="300" :show-overflow-tooltip="true" align="center">
                <template slot-scope="scope">
                    <i class="el-icon-time" v-if="scope.row.accesstime!=null"></i>
                    <span
                        v-if="scope.row.accesstime!=null"
                    >{{ scope.row.accesstime | formatDateStr('yyyy-MM-dd hh:mm:ss') }}</span>
                    <span v-else>暂未记录</span>
                </template>
            </el-table-column>
            <el-table-column label="状态" with="300" :show-overflow-tooltip="true" align="center">
                <template slot-scope="scope">
                    <span
                        v-if="scope.row.ipstats==0"
                    >正常</span>
                    <span v-else>禁止登陆</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button
                        type="primary"
                        size="mini"
                        @click.native="showDialogAddOrEdit(scope.row)"
                    >
                            <span
                                v-if="scope.row.ipstats==0"
                            >禁止登陆</span>
                            <span v-else>恢复登录</span>
                    </el-button>
                    <el-button
                        type="danger"
                        size="mini"
                        @click.native="handleDel(scope.row.ipid,2)"
                    >删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--列表end --222e-->

        <!--全选删除start-->
        <div class="left-container">
            <el-button type="info" size="small" @click="delGroup(1)">禁止登陆</el-button>
            <el-button type="danger" size="small" @click="delGroup(2)">删除已选</el-button>
            <transition name="el-fade-in-linear">
                <el-button size="small" @click="toggleSelection()" v-show="cancelToggle">取消选择</el-button>
            </transition>
        </div>
        <!--全选删除end-->

        <!--分页模块start-->
        <el-pagination
            class="pagination-container"
            background
            :page-size="query.pageSize"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            layout="total,sizes,prev, pager, next, jumper"
            :current-page="query.currentPage"
            :page-sizes="[10, 20, 30, 50]"
            :total="totalNumber"
        ></el-pagination>
        <!--分页模块end-->

        <!--新增/编辑弹框start--333s-->
        <el-dialog
            :title="dialogAddOrEditName"
            :visible.sync="dialogAddOrEdit"
            width="30%"
            :before-close="hideDialogAddOrEdit">
            <el-form :model="formData" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="活动名称" prop="username">
                    <el-input v-model="formData.username"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
    <el-button @click="dialogAddOrEdit = false">取 消</el-button>
    <el-button type="primary" :loading="formSumbitLoding" @click="formSumbit">提交</el-button>
  </span>
        </el-dialog>
        <!--新增/编辑弹框end--333e-->
    </div>
</template>
<script>
    import {getList,ipstatsEdit,delOrNolanding} from "../../api/website/ipaddress";

    /*用作表单的数据初始化*/
    const formJson = {
        username: ""
    };

    export default {
        data() {
            return {
                pickerOptions: {//快速选择日期
                    shortcuts: [
                        {
                            text: "最近一周",
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(
                                    start.getTime() - 3600 * 1000 * 24 * 7
                                );
                                picker.$emit("pick", [start, end]);
                            }
                        },
                        {
                            text: "最近一个月",
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(
                                    start.getTime() - 3600 * 1000 * 24 * 30
                                );
                                picker.$emit("pick", [start, end]);
                            }
                        },
                        {
                            text: "最近三个月",
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(
                                    start.getTime() - 3600 * 1000 * 24 * 90
                                );
                                picker.$emit("pick", [start, end]);
                            }
                        },
                        {
                            text: "最近六个月",
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(
                                    start.getTime() - 3600 * 1000 * 24 * 180
                                );
                                picker.$emit("pick", [start, end]);
                            }
                        }
                    ]
                },
                query: {//查询条件
                    startTime: "",
                    endTime: "",
                    ipaddress:"",
                    ipstats:"-1",
                    currentPage: 1,
                    pageSize: 10,
                },
                createTime:[],//选择日期时需要的model
                list: [],//列表集合
                totalNumber: 0,//分页总页数
                loading: true,//列表加载
                allLoding: false,//全屏加载
                formSumbitLoding: false,//表单提交加载
                cancelToggle: false,//列表是否全选
                multipleSelection: [], //多选集合
                dialogAddOrEdit: false,//新增或编辑弹框开关
                dialogAddOrEditName: "",//新增或编辑弹框名字
                formData: formJson,//新增或修改的对象
                rules: {//表单校验
                    username: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                        {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            /*获取列表事件*/
            getList() {
                this.loading = true;
                getList(this.query).then(response => {
                    if (response.code != 0) {
                        this.$message.error(response.message);
                        return false;
                    }
                    this.list =  [];
                    if(response.data.list.length>0){
                        for(var i in response.data.list){
                            response.data.list[i].number=(this.query.currentPage-1)*10+parseInt(i)+1;
                            this.list.push(response.data.list[i])
                        }
                    }
                    this.totalNumber = response.data.totalNumber || 0;
                    setTimeout(() => {
                        this.loading = false;
                    }, 0.3 * 1000);
                }).catch(() => {
                    this.loading = false;
                    this.list = [];
                })
            },

            /*新增或者编辑提交事件*/
            formSumbit() {
                //表单校验通过
                this.$refs["ruleForm"].validate(valid => {
                    if (valid) {
                        this.formSumbitLoding = true;
                        //提交操作
                        setTimeout(() => {
                            this.hideDialogAddOrEdit();
                            this.$message.success("提交成功");
                            this.formSumbitLoding = false;
                            this.getList();
                        }, 1000);
                    }
                });
                //new URLSearchParams(this.query)  提交数据格式不正确时可以转一下
            },

            /*列表查询条件事件*/
            onSubmit() {
                this.query.currentPage = 1;
                this.$router.push({
                    path: "",
                    query: this.query
                });
                this.getList();
            },

            /*刷新*/
            onRest() {
                this.$router.push({
                    path: ""
                });
                //刷新事件需要重置查询条件
                this.createTime = [];
                this.query = {
                    currentPage: 1,
                    pageSize: 10,
                };
                this.getList();
            },

            /*显示 -新增或者编辑弹框*/
            showDialogAddOrEdit(row) {
                var message;
                let froms={
                    ipid:row.ipid,
                    ipstats:""
                }
                if(row.ipstats==0){
                    message="确定要禁止登陆?";
                    froms.ipstats=1;
                }else {
                    message="确定要恢复登陆?";
                    froms.ipstats=0;
                }
                this.$confirm(message, "提示", {
                    type: "warning"
                })
                    .then(() => {
                        ipstatsEdit(froms).then(response =>{
                            if (response.code) {
                                this.$message.error(response.message);
                                return false;
                            }
                            setTimeout(() => {
                                this.$message.success("提交成功");
                                this.getList();
                            }, 1000);
                        }).catch(()=>{

                        })
                    })
                    .catch(() => {
                        this.$message.warning("已取消");
                    });
            },

            /*隐藏 -新增或编辑弹框*/
            hideDialogAddOrEdit() {
                this.dialogAddOrEdit = false;

                //隐藏弹框时需要清除弹框内容
                if (this.$refs["ruleForm"]) {
                    this.$refs['ruleForm'].resetFields();//清除formData表单
                    this.$refs["ruleForm"].clearValidate();//清除表单验证
                }
                //如果上传文件，还需要清除文件列表
            },

            /*点击页数*/
            handleCurrentChange(val) {
                this.query.currentPage = val;
                this.getList();
            },

            /*选择每页条数【10,20,30,40,50】*/
            handleSizeChange(val) {
                this.query.pageSize = val;
                this.getList();
            },

            /*取消全选事件*/
            toggleSelection(rows) {
                if (rows) {
                    rows.forEach(row => {
                        this.$refs.multipleTable.toggleRowSelection(row);
                    });
                } else {
                    this.$refs.multipleTable.clearSelection();
                }
            },

            /*列表复选框选择事件*/
            handleSelectionChange(val) {
                this.multipleSelection = val;
                if (val == "") {
                    this.cancelToggle = false;
                    return;
                }
                this.cancelToggle = true;
            },

            /*全选删除事件*/
            delGroup(type) {
                console.log(this.multipleSelection)
                var ids = this.multipleSelection.map(item => item.ipid).join(); //获取所有选中行的id组成的字符串，以逗号分隔
                if (ids == "") {
                    this.$message({
                        message: "请先选择",
                        type: "warning"
                    });
                    return;
                }
                this.handleDel(ids,type);
            },

            /*单击删除事件*/
            handleDel(ids,type) {
                var message;
                var alertmessage;
                if(type==1){
                    message="确定要禁止登陆?";
                    alertmessage="操作成功";
                }else{
                    message= "确认要删除吗?";
                    alertmessage="删除成功";
                }
                if (ids) {
                    this.$confirm(message, "提示", {
                        type: "warning"
                    })
                        .then(() => {
                            this.loading = true;
                            var params = new URLSearchParams();
                            params.append("ids", ids);
                            params.append("type", type);
                            delOrNolanding(params)
                                .then(response => {
                                    this.loading = false;
                                    if (response.code) {
                                        this.$message.error(response.message);
                                        return false;
                                    }
                                    this.$message.success(alertmessage);
                                    //返回第一页
                                    this.query.currentPage = 1;
                                    // 刷新数据
                                    this.getList();
                                })
                                .catch(() => {
                                    this.loading = false;
                                });
                        })
                        .catch(() => {
                            this.$message.warning("已取消");
                        });
                }
            },

            /*日期选择事件*/
            dateChange(val) {
                if (val == null) {
                    this.query.startTime = "";
                    this.query.endTime = "";
                } else {
                    this.query.startTime = val[0];
                    this.query.endTime = val[1];
                    this.query.currentPage = 1;
                }
                this.getList();
                console.log(this.query)
            },
        }, created() {
            this.getList();
        }
    }
    //跳转路由的方法
    // this.$router.push({
    //     path: "",
    //     query:{
    //         username:"张三"
    //     }
    // });
    //接受路由传递的参数
    //let query = this.$route.query;
</script>
