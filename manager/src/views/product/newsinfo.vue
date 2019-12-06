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
                <!-- <el-button type="primary" icon="el-icon-sort" @click="sortGroup">变更排序</el-button> -->
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
            <el-table-column label="编号" prop="newsinfoId" width="50" align="center"/>
            <el-table-column label="新闻标题" prop="newsTitel" align="left" :show-overflow-tooltip="true"/>
            <el-table-column label="新闻标签" prop="categoryName" align="left" :show-overflow-tooltip="true"/>
            <el-table-column label="关键字" prop="keyWord" align="left" :show-overflow-tooltip="true"/>
            <el-table-column label="描述" prop="description" align="left" :show-overflow-tooltip="true"/>
            <el-table-column label="编辑时间" with="300" :show-overflow-tooltip="true" align="center">
                <template slot-scope="scope">
                    <i class="el-icon-time" v-if="scope.row.editTime!=null"></i>
                    <span
                        v-if="scope.row.editTime!=null"
                    >{{ scope.row.editTime | formatDateStr('yyyy-MM-dd hh:mm:ss') }}</span>
                    <span v-else>-</span>
                </template>
            </el-table-column>
            <el-table-column label="创建时间" with="300" :show-overflow-tooltip="true" align="center">
                <template slot-scope="scope">
                    <i class="el-icon-time" v-if="scope.row.createTime!=null"></i>
                    <span
                        v-if="scope.row.createTime!=null"
                    >{{ scope.row.createTime | formatDateStr('yyyy-MM-dd hh:mm:ss') }}</span>
                    <span v-else>-</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" width="210">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini"  @click.native="showDialogDetails(scope.$index, scope.row)" >查看 </el-button>
                    <el-button type="primary" size="mini"  @click.native="showDialogAddOrEdit(scope.$index, scope.row)" >编辑 </el-button>
                    <el-button type="danger" size="mini" @click.native="handleDel(scope.row.newsinfoId)" >删除 </el-button>
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
            class="advantageDialog"
            :title="dialogAddOrEditName"
            :visible.sync="dialogDetails"
            width="50%"
            top="5vh"
            :before-close="hideDialogAddOrEdit">
            <el-form label-width="85px">
                <el-form-item label="新闻标题:">
                    <el-input v-model="formData.newsTitel" style="width: 60%" readonly="readonly"></el-input>
                </el-form-item>
                <el-form-item label="新闻主图:">
                <span v-if="formData.newsPic!=null">
                    <img style="width:160px;height: 200px;border-radius: 6px;" :src="formData.newsPic">
                </span>
                    <span v-else style="color: black">暂无标题图片</span>
                </el-form-item>
                <el-form-item label="正文内容:" style="width: 98%">
                    <br>
                    <span class="producttext" style="display:block;width:100%;" v-html="formData.newsContent"/>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer advantagebtn">
                <el-button @click="hideDialogAddOrEdit">取 消</el-button>
            </span>
        </el-dialog>

        <!--新增/编辑弹框start--333s-->
        <el-dialog
            class="advantageAddOrEdit"
            :title="dialogAddOrEditName"
            :visible.sync="dialogAddOrEdit"
            width="50%"
            top="5vh"
            :before-close="hideDialogAddOrEdit">
            <el-form :model="formData" :rules="rules" ref="ruleForm" label-width="85px" class="demo-ruleForm">
                <el-form-item label="新闻标题:" prop="newsTitel" >
                    <el-input v-model="formData.newsTitel" style="width: 60%"></el-input>
                </el-form-item>
                <el-form-item label="新闻标签:" prop="categoryId">
                    <el-select v-model="formData.categoryId" placeholder="请选择" style="width: 330px">
                          <el-option
                              v-for="item in categoryList"
                              :key="item.categoryId"
                              :label="item.categoryName"
                              :value="item.categoryId">
                          </el-option>
                      </el-select>
                </el-form-item>
                 <el-form-item label="关键字:" prop="keyWord">
                    <el-input v-model="formData.keyWord" style="width: 60%"></el-input>
                </el-form-item>
                <el-form-item label="描述:" prop="description">
                    <el-input v-model="formData.description" style="width: 60%"></el-input>
                </el-form-item>
                <el-form-item label="新闻主图" prop="newsPic" class="productpic">
                    <el-upload
                        class="avatar-uploader"
                        :action="uploaduri"
                        :on-success="handleTitlePicSuccess"
                        :before-upload="beforeAvatarUpload"
                        :show-file-list="false"
                    >
                        <img v-if="formData.newsPic" :src="formData.newsPic"  >
                        <i v-else class="el-icon-plus avatar-uploader-icon" style="font-size: 26px;line-height:7;"></i>
                    </el-upload>
                    <span>注：上传图片尺寸为470*603像素</span>
                </el-form-item>
                <el-form-item label="正文内容:" prop="newsContent" style="width: 98%">
                    <Tinymce ref="tinymce" v-model="formData.newsContent"></Tinymce>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer ">
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
                    <el-input v-model="productSort" ></el-input>
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
import {
    getCategory,
    addNewsInfo,
    getNewsInfo,
    deleteNewsInfo
} from "../../api/product/product";
import Tinymce from "../../components/Tinymce/index";
import { BASE_URL } from "../../config/app";
/*用作表单的数据初始化*/
const formJson = {
    producttitle: "",
    productstate: "0",
    productcontext: "",
    categoryId: ""
};

export default {
    data() {
        return {
            uploaduri: BASE_URL + "/tool/uploadpro",
            query: {
                //查询条件
                text: "",
                currentPage: 1,
                pageSize: 10
            },
            sortVisible: false,
            categoryList: [],
            productSort: "",
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
                newsTitel: [
                    {
                        required: true,
                        message: "请输入新闻标题",
                        trigger: "blur"
                    }
                ],
                categoryId: [
                    {
                        required: true,
                        message: "请选择新闻标签",
                        trigger: "blur"
                    }
                ],
                productpic: [
                    {
                        required: true,
                        message: "请上传案例图片",
                        trigger: "blur"
                    }
                ],
                newsContent: [
                    {
                        required: true,
                        message: "请输入正文内容",
                        trigger: "blur"
                    }
                ]
            }
        };
    },
    methods: {
        handleTitlePicSuccess(res) {
            console.info("图片上传成功返回数据:", res);
            if (res.status == 0) {
                alert(res.message);
            } else if (res.status == 9) {
                alert(res.message);
            } else {
                this.formData.newsPic = res.data;
            }
        },
        /*图片上传前操作*/
        beforeAvatarUpload(file) {
            const isJPG = file.type === "image/jpeg";
            const isGIF = file.type === "image/gif";
            const isPNG = file.type === "image/png";
            const isBMP = file.type === "image/bmp";
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG && !isGIF && !isPNG && !isBMP) {
                this.$message.error("上传图片必须是JPG/GIF/PNG/BMP 格式!");
            }
            if (!isLt2M) {
                this.$message.error("上传图片大小不能超过 2MB!");
            }
            return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
        },
        /*获取列表事件*/
        getNewsInfo() {
            this.loading = true;
            console.info(this.query);
            getNewsInfo(this.query)
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
                    delete this.formData["createTime"];
                    delete this.formData["editTime"];
                    let data = new URLSearchParams(this.formData);
                    addNewsInfo(data)
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
                                this.getNewsInfo();
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
            var ids = this.multipleSelection.map(item => item.productid).join();
            this.loading = true;
            if (this.productSort != "" && this.productSort.length > 0) {
                var params = new URLSearchParams();
                params.append("ids", ids);
                params.append("sort", this.productSort);
                addNewsInfo(params)
                    .then(response => {
                        this.loading = false;
                        if (response.code) {
                            this.$message.error(response.message);
                            return false;
                        }
                        this.$message.success("操作成功");
                        this.sortVisible = false;
                        this.productSort = "";
                        //返回第一页
                        this.query.currentPage = 1;
                        // 刷新数据
                        this.getNewsInfo();
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
            this.getNewsInfo();
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
            this.getNewsInfo();
        },

        /*显示 -新增或者编辑弹框*/
        showDialogAddOrEdit(index, row) {
            this.formData = Object.assign({}, formJson); //将弹框内表单内容初始化
            if (row !== null) {
                //如果row不为空，说明是修改，因为row是修改时携带过来此列的所有信息
                this.formData = Object.assign({}, row); //修改则将列内容深拷贝到表单内容中
                this.formData.productstate += "";
                delete this.formData.creationtime;
                delete this.formData.productsort;
                setTimeout(() => {
                    this.$refs.tinymce.setContent(row.newsContent);
                }, 100);
                this.dialogAddOrEditName = "编辑";
            } else {
                this.dialogAddOrEditName = "新增";
            }
            this.dialogAddOrEdit = true;
        },
        showDialogDetails(index, row) {
            this.formData = Object.assign({}, row); //修改则将列内容深拷贝到表单内容中
            this.formData.productstate += "";
            delete this.formData.creationtime;
            delete this.formData.productsort;
            setTimeout(() => {
                this.$refs.tinymce.setContent(row.productcontext);
            }, 100);
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
            if (this.$refs["tinymce"]) {
                this.$refs.tinymce.clearContent();
            }
            //如果上传文件，还需要清除文件列表
        },
        hideEditSort() {
            this.sortVisible = false;
            this.productSort = "";
        },
        /*点击页数*/
        handleCurrentChange(val) {
            this.query.currentPage = val;
            this.getNewsInfo();
        },

        /*选择每页条数【10,20,30,40,50】*/
        handleSizeChange(val) {
            this.query.pageSize = val;
            this.getNewsInfo();
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
            var ids = this.multipleSelection.map(item => item.newsinfoId).join(); //获取所有选中行的id组成的字符串，以逗号分隔
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
            var ids = this.multipleSelection.map(item => item.newsinfoId).join(); //获取所有选中行的id组成的字符串，以逗号分隔
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
                        deleteNewsInfo(params)
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
                                this.getNewsInfo();
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
        /*获取标签*/
        getCategory() {
            this.loading = true;
            console.info(this.query);
             this.query.categoryType = 2;
            getCategory(this.query)
                .then(response => {
                    console.info(response.data);
                    if (response.code != 0) {
                        this.$message.error(response.message);
                        return false;
                    }
                    this.list = [];
                    if (response.data.length > 0) {
                        this.categoryList = response.data || [];
                        // for (var i in response.data.list) {
                        //     response.data.list[i].number =
                        //         (this.query.currentPage - 1) * 10 +
                        //         parseInt(i) +
                        //         1;
                        //     this.list.push(response.data.list[i]);
                        // }
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
        this.getNewsInfo();
        this.getCategory();
    },
    components: {
        Tinymce
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
.advantageAddOrEdit .el-dialog__body {
    padding: 10px 60px !important;
}
.advantageDialog .el-dialog__footer {
    padding: 20px 38px 20px !important;
}
.advantageDialog .el-dialog__body {
    padding: 30px 20px 0px 20px !important;
}
.advantageAddOrEdit .el-dialog__footer {
    padding: 20px 39px 20px !important;
}
.advantageAddOrEdit .el-dialog__body {
    padding: 30px 20px 0px 20px !important;
}
.productpic .el-upload {
    width: 165px;
    height: 210px;
}
.productpic img {
    width: 165px;
    height: 210px;
}
.producttext img {
    width: 60%;
    height: 263.5px;
}
</style>
