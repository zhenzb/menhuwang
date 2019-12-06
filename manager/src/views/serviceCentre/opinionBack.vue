<template>
    <div style="width: 95%; margin-left: 2%;">
        <!--查询条件start --111s-->
        <el-form :inline="true" :model="query" class="query-form" size="small">
            <el-form-item class="query-form-item">
                <el-input
                    v-model="query.text"
                    placeholder="请输入关键字"
                    prefix-icon="el-icon-search"
                    @keyup.enter.native="onSubmit"
                    clearable
                ></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-refresh" @click="onRest">刷新</el-button>
                <el-button type="primary" icon="el-icon-search" @click="onSubmit">查询</el-button>
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
            <el-table-column type="selection" width="50" align="center" fixed/>
            <el-table-column label="编号" prop="number" align="center"/>
            <el-table-column label="姓名" prop="username" align="left" :show-overflow-tooltip="true"/>
            <el-table-column label="手机号" prop="phone" align="center" :show-overflow-tooltip="true" />
            <el-table-column label="反馈内容" prop="content" align="left" :show-overflow-tooltip="true" />
            <el-table-column label="反馈日期" with="300" :show-overflow-tooltip="true" align="center">
                <template slot-scope="scope">
                    <i class="el-icon-time" v-if="scope.row.createtime!=null"></i>
                    <span
                        v-if="scope.row.createtime!=null"
                    >{{ scope.row.createtime | formatDateStr('yyyy-MM-dd hh:mm:ss') }}</span>
                    <span v-else>-</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini"  @click.native="showDialogDetails(scope.$index, scope.row)" >查看 </el-button>
                    <el-button type="danger" size="mini" @click.native="handleDel(scope.row.opinionbackid)" >删除 </el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--列表end --222e-->

        <!--全选删除start-->
        <div class="left-container">
            <el-button type="danger" size="small" @click="delGroup()">删除已选</el-button>
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
        <!-- 查看 -->
        <el-dialog
            class="opinionBackDialog"
            :title="dialogAddOrEditName"
            :visible.sync="dialogDetails"
            width="40%"
            top="5vh"
            :before-close="hideDialogAddOrEdit">
            <el-form label-width="15%">
                <el-form-item label="姓名:">
                    <el-input v-model="formData.username" style="width: 40%" readonly="readonly"/>
                    <div style="display: inline-block; width: 10%;  margin-left: 8%;" >手机号:</div>
                    <el-input v-model="formData.phone" style="width: 40%" readonly="readonly"/>
                </el-form-item>
                <el-form-item label="反馈时间:">
                    <i class="el-icon-time" v-if="formData.createtime!=null"></i>
                    <span
                        v-if="formData.createtime!=null"
                    >{{ formData.createtime | formatDateStr('yyyy-MM-dd hh:mm:ss') }}</span>
                    <span v-else>-</span>
                </el-form-item>
                <el-form-item label="反馈内容:">
                    <el-input type="textarea" :rows="10" v-model="formData.content" readonly="readonly" style="width: 98%"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer advantagebtn">
                <el-button @click="hideDialogAddOrEdit">取 消</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
    import {getOpinionBackList,delOpinionback} from "../../api/problem/problem";

    export default {
        data() {
            return {
                query: {//查询条件
                    text:"",
                    currentPage: 1,
                    pageSize: 10,
                },
                dialogDetails:false,
                list: [],//列表集合
                totalNumber: 0,//分页总页数
                loading: false,//列表加载
                allLoding: false,//全屏加载
                cancelToggle: false,//列表是否全选
                multipleSelection: [], //多选集合
                dialogAddOrEditName: "",//新增或编辑弹框名字
                formData: {},//新增或修改的对象
            };
        },
        methods: {
            /*获取列表事件*/
            getList() {

                this.loading = true;
                console.info(this.query)
                getOpinionBackList(this.query).then(response => {
                    console.info(response.data)
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
                this.query = {
                    currentPage: 1,
                    pageSize: 10,
                };
                this.getList();
            },

            showDialogDetails(index, row){
                this.formData = Object.assign({}, row);//修改则将列内容深拷贝到表单内容中
                this.dialogAddOrEditName = "查看";
                this.dialogDetails = true;
            },
            /*隐藏 -新增或编辑弹框*/
            hideDialogAddOrEdit() {
                this.dialogDetails = false;
                this.formData={};
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
            delGroup() {
                console.log(this.multipleSelection)
                var ids = this.multipleSelection.map(item => item.opinionbackid).join(); //获取所有选中行的id组成的字符串，以逗号分隔
                if (ids == "") {
                    this.$message({
                        message: "请先选择",
                        type: "warning"
                    });
                    return;
                }
                this.handleDel(ids);
            },
            /*单击删除事件*/
            handleDel(ids) {
                if (ids) {
                    this.$confirm("确认要删除吗?", "提示", {
                        type: "warning"
                    })
                        .then(() => {
                            this.loading = true;
                            var params = new URLSearchParams();
                            params.append("ids", ids);
                            delOpinionback(params)
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
<style type="text/scss" lang="scss" >
    .opinionBackDialog .el-dialog__footer{
        padding: 20px 34px 20px !important;
    }
    .opinionBackDialog .el-dialog__body{
        padding: 30px 20px 0px 20px !important;
    }
</style>

