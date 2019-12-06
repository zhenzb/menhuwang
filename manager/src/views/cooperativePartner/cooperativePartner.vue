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
                <el-button type="primary" icon="el-icon-edit" @click.native="showDialogAddOrEdit(null,null)">添加</el-button>
                <el-button type="primary" icon="el-icon-sort" @click="sortGroup">变更排序</el-button>
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
            <el-table-column label="公司名称" prop="cooperativepartnername" align="left" :show-overflow-tooltip="true"/>
            <el-table-column label="状态" align="center" width="80">
                <template slot-scope="scope">
                    <span
                        v-if="scope.row.cooperativepartnerstate==0"
                    >显示</span>
                    <span v-else>隐藏</span>
                </template>
            </el-table-column>
            <el-table-column label="排序值" align="center" width="80">
                <template slot-scope="scope">
                    <span
                        v-if="scope.row.cooperativepartnersort!=null"
                    >{{ scope.row.cooperativepartnersort}}</span>
                    <span v-else>-</span>
                </template>
            </el-table-column>
            <el-table-column label="创建时间" with="300" :show-overflow-tooltip="true" align="center">
                <template slot-scope="scope">
                    <i class="el-icon-time" v-if="scope.row.creationtime!=null"></i>
                    <span
                        v-if="scope.row.creationtime!=null"
                    >{{ scope.row.creationtime | formatDateStr('yyyy-MM-dd hh:mm:ss') }}</span>
                    <span v-else>-</span>
                </template>
            </el-table-column>
            <el-table-column label="描述" prop="cooperativepartnerdescribe" align="left" :show-overflow-tooltip="true"/>
            <el-table-column label="操作" align="center" width="210">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini"  @click.native="showDialogDetails(scope.$index, scope.row)" >查看 </el-button>
                    <el-button type="primary" size="mini"  @click.native="showDialogAddOrEdit(scope.$index, scope.row)" >编辑 </el-button>
                    <el-button type="danger" size="mini" @click.native="handleDel(scope.row.cooperativepartnerid)" >删除 </el-button>
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
            class="cooperativePartnerDialog"
            :title="dialogAddOrEditName"
            :visible.sync="dialogDetails"
            width="40%"
            top="5vh"
            :before-close="hideDialogAddOrEdit">
            <el-form label-width="85px">
                <el-form-item label="公司名称:">
                    <el-input v-model="formData.cooperativepartnername" style="width: 60%" readonly="readonly"></el-input>
                    <span style="margin-left: 26px;">是否显示:
                        <span v-if="formData.cooperativepartnerstate==0"> 是</span>
                        <span v-else> 否</span>
                    </span>
                </el-form-item>
                <el-form-item label="描述:">
                    <el-input v-model="formData.cooperativepartnerdescribe" style="width: 98%" readonly="readonly"></el-input>
                </el-form-item>
                <el-form-item label="正文内容:" style="width: 98%">
                    <span class="producttext" style="display:block;width:100%;" v-html="formData.cooperativepartnercontent"/>

                </el-form-item>
                <el-form-item label="LOGO图片:">
                    <span v-if="formData.cooperativepartnerpic!=null">
                    <img style="width: 200px; height: 143px;border-radius: 6px;" :src="formData.cooperativepartnerpic">
                </span>
                    <span v-else style="color: black">暂无LOGO图片</span>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer advantagebtn">
                <el-button @click="hideDialogAddOrEdit">取 消</el-button>
            </span>
        </el-dialog>

        <!--新增/编辑弹框start--333s-->
        <el-dialog
            class="cooperativePartnerDialog"
            :title="dialogAddOrEditName"
            :visible.sync="dialogAddOrEdit"
            width="40%"
            top="5vh"
            :before-close="hideDialogAddOrEdit">
            <el-form :model="formData" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="公司名称:" prop="cooperativepartnername" >
                    <el-input v-model="formData.cooperativepartnername" style="width: 60%"></el-input>
                    <span style="margin-left: 26px;">是否显示</span>
                    <template>
                        <el-radio v-model="formData.cooperativepartnerstate" label="0" style="margin-left: 26px;">是</el-radio>
                        <el-radio v-model="formData.cooperativepartnerstate" label="1">否</el-radio>
                    </template>
                </el-form-item>
                <el-form-item label="描述:" prop="cooperativepartnerdescribe">
                    <el-input v-model="formData.cooperativepartnerdescribe" style="width: 98%"></el-input>
                </el-form-item>
                <el-form-item label="正文内容:" prop="cooperativepartnercontent" style="width: 98%">
                    <Tinymce ref="tinymce" v-model="formData.cooperativepartnercontent"></Tinymce>
                </el-form-item>

                <el-form-item label="LOGO图片:" prop="cooperativepartnerpic" class="cooperativepartnerpic">
                    <el-upload
                        class="avatar-uploader"
                        :action="uploaduri"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload"
                        :show-file-list="false"

                    >
                        <img v-if="formData.cooperativepartnerpic" :src="formData.cooperativepartnerpic"  >
                        <i v-else class="el-icon-plus avatar-uploader-icon" style="font-size: 26px;line-height: 6;"></i>
                    </el-upload>
                    <span>注：上传图片尺寸为1920*1080像素</span>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer advantagebtn">
                <el-button @click="hideDialogAddOrEdit">取 消</el-button>
                <el-button type="primary" :loading="formSumbitLoding" @click="formSumbit">提交</el-button>
            </span>
        </el-dialog>
        <!--新增/编辑弹框end--333e-->

        <el-dialog
            :visible.sync="sortVisible"
            width="15%"
            :before-close="hideEditSort"
            class="softwareAddOrEdit"
            top="15vh"
        >
            <div style=" font-size: 28px; text-align: center; margin-bottom: 13px;">请输入排序值</div>
            <el-form class="demo-ruleForm">
                <el-form-item>
                    <el-input v-model="cooperativepartnersort" ></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="hideEditSort">取 消</el-button>
                <el-button type="primary" :loading="sortSumbitLoding" @click="sortSumbit">提交</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import Tinymce from "../../components/Tinymce/index";
import {
    addOrEdit,
    getList,
    delOrSort
} from "../../api/cooperativePartner/cooperativePartner";
import { BASE_URL } from "../../config/app";

/*用作表单的数据初始化*/
const formJson = {
    cooperativepartnername: "",
    cooperativepartnerstate: "0",
    cooperativepartnerpic: "",
    cooperativepartneraddress: ""
};

export default {
    components: { Tinymce },
    data() {
        return {
            query: {
                //查询条件
                text: "",
                currentPage: 1,
                pageSize: 10
            },
            uploaduri: BASE_URL + "/tool/uploadpro",
            sortVisible: false,
            cooperativepartnersort: "",
            sortSumbitLoding: false,
            dialogDetails: false,
            list: [], //列表集合
            totalNumber: 0, //分页总页数
            loading: false, //列表加载
            allLoding: false, //全屏加载
            formSumbitLoding: false, //表单提交加载
            cancelToggle: false, //列表是否全选
            multipleSelection: [], //多选集合
            dialogAddOrEdit: false, //新增或编辑弹框开关
            dialogAddOrEditName: "", //新增或编辑弹框名字
            formData: formJson, //新增或修改的对象
            rules: {
                //表单校验
                cooperativepartnername: [
                    {
                        required: true,
                        message: "请输入公司名称",
                        trigger: "blur"
                    }
                ],
                cooperativepartnerdescribe: [
                    {
                        required: true,
                        message: "请输入描述内容",
                        trigger: "blur"
                    }
                ],
                cooperativepartnercontent: [
                    {
                        required: true,
                        message: "请输入正文内容",
                        trigger: "blur"
                    }
                ],
                cooperativepartnerpic: [
                    {
                        required: true,
                        message: "请输上传背景图",
                        trigger: "blur"
                    }
                ]
            }
        };
    },
    methods: {
        handleAvatarSuccess(res) {
            console.info(res.data);
            if (res.code == -1) {
                alert(res.message);
            } else if (res.code == 9) {
                alert(res.message);
            } else {
                this.formData.cooperativepartnerpic = res.data;
            }
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === "image/jpeg";
            const isGIF = file.type === "image/gif";
            const isPNG = file.type === "image/png";
            const isBMP = file.type === "image/bmp";
            //const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG && !isGIF && !isPNG && !isBMP) {
                this.$message.error("上传图片必须是JPG/GIF/PNG/BMP 格式!");
            }
            // if (!isLt2M) {
            //     this.$message.error("上传图片大小不能超过 2MB!");
            // }
            return isJPG || isBMP || isGIF || isPNG;
        },
        /*获取列表事件*/
        getList() {
            this.loading = true;
            console.info(this.query);
            getList(this.query)
                .then(response => {
                    console.info(response.data);
                    if (response.code != 0) {
                        this.$message.error(response.message);
                        return false;
                    }
                    this.list = [];
                    if (response.data.list.length > 0) {
                        for (var i in response.data.list) {
                            response.data.list[i].number =
                                (this.query.currentPage - 1) * 10 +
                                parseInt(i) +
                                1;
                            this.list.push(response.data.list[i]);
                        }
                    }
                    this.totalNumber = response.data.totalNumber || 0;
                    setTimeout(() => {
                        this.loading = false;
                    }, 0.3 * 1000);
                })
                .catch(() => {
                    this.loading = false;
                    this.list = [];
                });
        },

        /*新增或者编辑提交事件*/
        formSumbit() {
            //表单校验通过
            this.$refs["ruleForm"].validate(valid => {
                if (valid) {
                    this.formSumbitLoding = true;
                    addOrEdit(this.formData)
                        .then(response => {
                            if (response.code) {
                                this.$message.error(response.message);
                                this.formSumbitLoding = false;
                                return false;
                            }
                            setTimeout(() => {
                                this.$message.success("提交成功");
                                this.hideDialogAddOrEdit();
                                this.formSumbitLoding = false;
                                this.getList();
                            }, 1000);
                        })
                        .catch(() => {
                            this.formSumbitLoding = false;
                        });
                }
            });
            //new URLSearchParams(this.query)  提交数据格式不正确时可以转一下
        },
        sortSumbit() {
            var ids = this.multipleSelection
                .map(item => item.cooperativepartnerid)
                .join();
            this.loading = true;
            if (
                this.cooperativepartnersort != "" &&
                this.cooperativepartnersort.length > 0
            ) {
                var params = new URLSearchParams();
                params.append("ids", ids);
                params.append("sort", this.cooperativepartnersort);
                delOrSort(params)
                    .then(response => {
                        this.loading = false;
                        if (response.code) {
                            this.$message.error(response.message);
                            return false;
                        }
                        this.$message.success("操作成功");
                        this.sortVisible = false;
                        this.cooperativepartnersort = "";
                        //返回第一页
                        this.query.currentPage = 1;
                        // 刷新数据
                        this.getList();
                    })
                    .catch(() => {
                        this.loading = false;
                    });
            } else {
                this.$message({ message: "排序值不可为空！", type: "warning" });
            }
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
                pageSize: 10
            };
            this.getList();
        },

        /*显示 -新增或者编辑弹框*/
        showDialogAddOrEdit(index, row) {
            this.formData = Object.assign({}, formJson); //将弹框内表单内容初始化
            if (row !== null) {
                //如果row不为空，说明是修改，因为row是修改时携带过来此列的所有信息
                this.formData = Object.assign({}, row); //修改则将列内容深拷贝到表单内容中
                this.formData.cooperativepartnerstate += "";
                delete this.formData.cooperativepartnersort;
                delete this.formData.creationtime;
                this.dialogAddOrEditName = "编辑";
            } else {
                this.dialogAddOrEditName = "新增";
            }
            this.dialogAddOrEdit = true;
        },
        showDialogDetails(index, row) {
            this.formData = Object.assign({}, row); //修改则将列内容深拷贝到表单内容中
            this.formData.problemstats += "";
            this.dialogAddOrEditName = "查看";
            this.dialogDetails = true;
        },
        /*隐藏 -新增或编辑弹框*/
        hideDialogAddOrEdit() {
            this.dialogAddOrEdit = false;
            this.dialogDetails = false;

            //隐藏弹框时需要清除弹框内容
            if (this.$refs["ruleForm"]) {
                this.$refs["ruleForm"].resetFields(); //清除formData表单
                this.$refs["ruleForm"].clearValidate(); //清除表单验证
            }
        },
        hideEditSort() {
            this.sortVisible = false;
            this.cooperativepartnersort = "";
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
            console.log(this.multipleSelection);
            var ids = this.multipleSelection
                .map(item => item.cooperativepartnerid)
                .join(); //获取所有选中行的id组成的字符串，以逗号分隔
            if (ids == "") {
                this.$message({
                    message: "请先选择",
                    type: "warning"
                });
                return;
            }
            this.handleDel(ids);
        },
        sortGroup() {
            console.log(this.multipleSelection);
            var ids = this.multipleSelection
                .map(item => item.cooperativepartnerid)
                .join(); //获取所有选中行的id组成的字符串，以逗号分隔
            if (ids == "") {
                this.$message({
                    message: "请先选择",
                    type: "warning"
                });
                return;
            }
            this.sortVisible = true;
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
                        delOrSort(params)
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
            console.log(this.query);
        }
    },
    created() {
        this.getList();
    }
};
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
<style type="text/scss" lang="scss">
.cooperativePartnerDialog .el-dialog__footer {
    padding: 20px 33px 20px !important;
}
.cooperativePartnerDialog .el-dialog__body {
    padding: 30px 20px 0px 20px !important;
}
.cooperativepartnerpic .el-upload {
    width: 200px;
    height: 143px;
}
.cooperativepartnerpic img {
    width: 200px;
    height: 143px;
}
</style>
