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
            <el-table-column type="selection" width="50" fixed align="center"/>
            <el-table-column label="编号" prop="number" width="50" align="center"/>
            <el-table-column label="标题" prop="softwaretitle" align="left" :show-overflow-tooltip="true" />
            <el-table-column label="状态" prop="softwarestate" align="center" width="80">
                <template slot-scope="scope">
                    <span
                        v-if="scope.row.softwarestate==0"
                    >显示</span>
                    <span v-else>隐藏</span>
                </template>
            </el-table-column>
            <el-table-column label="排序值" align="center" width="80">
                <template slot-scope="scope">
                    <span
                        v-if="scope.row.softwaresort!=null"
                    >{{ scope.row.softwaresort}}</span>
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
            <el-table-column label="备注" prop="softwareremarks" align="left" :show-overflow-tooltip="true"/>
            <el-table-column label="操作" align="center" width="210">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini"  @click.native="showDialogDetails(scope.$index, scope.row)" >查看 </el-button>
                    <el-button  type="primary" size="mini" @click.native="showDialogAddOrEdit(scope.$index, scope.row)"  >编辑 </el-button>
                    <el-button type="danger" size="mini" @click.native="handleDel(scope.row.softwareid)" >删除 </el-button>
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
            <el-form label-width="100px">
                <el-form-item label="标题名称:">
                    <el-input v-model="formData.softwaretitle" style="width: 60%" readonly="readonly"></el-input>
                    <span style="margin-left: 26px;">是否显示:
                        <span v-if="formData.softwarestate==0">
                            是
                         </span>
                        <span v-else>
                            否
                        </span>
                    </span>
                </el-form-item>
                <el-form-item label="备注信息:">
                    <el-input v-model="formData.softwareremarks"  readonly="readonly"></el-input>
                </el-form-item>
                <el-form-item label="iPhone地址" v-if="formData.addressdisplay==0">
                    <el-input v-model="formData.iphoneaddress" readonly="readonly" ></el-input>
                </el-form-item>
                <el-form-item  label="Android地址" v-if="formData.addressdisplay==0">
                    <el-input v-model="formData.androidaddress" readonly="readonly" ></el-input>
                </el-form-item>
                <el-form-item  label="Windows地址" v-if="formData.addressdisplay==0">
                    <el-input v-model="formData.windowsaddress"  readonly="readonly" ></el-input>
                </el-form-item>
                <el-form-item label="banner图片" prop="softwarepic">
                    <span v-if="formData.softwarepic!=null">
                        <img style="width: 341.5px;height:177.25px;border-radius: 6px;" :src="formData.softwarepic">
                     </span>
                    <span v-else style="color: black">暂无背景图片</span>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer advantagebtn">
                <el-button @click="hideDialogAddOrEdit">取 消</el-button>
            </span>
        </el-dialog>
        <!--新增/编辑弹框start--333s-->
        <el-dialog
            :title="dialogAddOrEditName"
            :visible.sync="dialogAddOrEdit"
            width="50%"
            :before-close="hideDialogAddOrEdit"
            class="softwareAddOrEdit"
            top="5vh"
        >
            <el-form :model="formData" :rules="rules" ref="ruleForm" label-width="110px" class="demo-ruleForm">
                <el-form-item label="标题名称" prop="softwaretitle" >
                    <el-input v-model="formData.softwaretitle" style="width: 70%"></el-input>
                    <span style="margin-left: 26px;">是否显示</span>
                    <template>
                        <el-radio v-model="formData.softwarestate" label="0" style="margin-left: 26px;">是</el-radio>
                        <el-radio v-model="formData.softwarestate" label="1">否</el-radio>
                    </template>
                </el-form-item>
                <el-form-item label="备注信息" prop="softwareremarks">
                    <el-input v-model="formData.softwareremarks" ></el-input>
                </el-form-item>
                <el-form-item label="更多内容" >
                    <template>
                        <el-radio v-model="formData.addressdisplay" label="0" style="margin-left: 26px;">是</el-radio>
                        <el-radio v-model="formData.addressdisplay" label="1">否</el-radio>
                    </template>
                </el-form-item>
                <el-form-item v-if="formData.addressdisplay==0" label="iPhone地址" prop="iphoneaddress">
                    <el-input v-model="formData.iphoneaddress" ></el-input>
                </el-form-item>
                <el-form-item v-if="formData.addressdisplay==0" label="Android地址" prop="androidaddress">
                    <el-input v-model="formData.androidaddress" ></el-input>
                </el-form-item>
                <el-form-item v-if="formData.addressdisplay==0" label="Windows地址" prop="windowsaddress">
                    <el-input v-model="formData.windowsaddress" ></el-input>
                </el-form-item>
                <el-form-item label="banner图片" prop="softwarepic" class="softwareBanner">
                    <el-upload
                        class="avatar-uploader"
                        :action="uploaduri"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload"
                        :show-file-list="false"

                    >
                        <img v-if="formData.softwarepic" :src="formData.softwarepic"  >
                        <i v-else class="el-icon-plus avatar-uploader-icon" style="font-size: 26px;line-height: 6;"></i>
                    </el-upload>
                    <span>注：上传图片尺寸为2732*1418像素</span>
                </el-form-item>
            </el-form>

            <span slot="footer" class="dialog-footer advantagebtn">
                <el-button @click="dialogAddOrEdit = false">取 消</el-button>
                <el-button type="primary" :loading="formSumbitLoding" @click="formSumbit">提交</el-button>
            </span>
        </el-dialog>
        <!--新增/编辑弹框end--333e-->

        <el-dialog
            :visible.sync="sortVisible"
            width="15%"
            :before-close="hideEditSort"
            class="softwareSort"
            top="15vh"
        >
            <div style=" font-size: 28px; text-align: center; margin-bottom: 13px;">请输入排序值</div>
            <el-form class="demo-ruleForm">
                <el-form-item>
                    <el-input v-model="softwareSort" ></el-input>
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
    import {getMbSoftware,addOrEditSoftware,softwareDelOrSort} from "../../api/software/software";
    import { BASE_URL } from "../../config/app";

    /*用作表单的数据初始化*/
    const formJson = {
        softwareid:"",
        softwaretitle:"",
        softwarestate:"0",
        addressdisplay:"1",
        softwarepic:"sdafdsfsdfdsfsd",
        iphoneaddress:"",
        windowsaddress:"",
        androidaddress:"",
        softwareremarks:""
    };

    export default {
        data() {
            return {
                uploaduri:BASE_URL+"/tool/uploadpro",
                query: {//查询条件
                    text:"",
                    currentPage: 1,
                    pageSize: 10,
                },
                sortVisible:false,
                dialogDetails:false,
                softwareSort:"",
                sortSumbitLoding:false,
                createTime:[],//选择日期时需要的model
                list: [],//列表集合
                totalNumber: 0,//分页总页数
                loading: false,//列表加载
                allLoding: false,//全屏加载
                formSumbitLoding: false,//表单提交加载
                cancelToggle: false,//列表是否全选
                multipleSelection: [], //多选集合
                dialogAddOrEdit: false,//新增或编辑弹框开关
                dialogAddOrEditName: "",//新增或编辑弹框名字
                formData: formJson,//新增或修改的对象
                rules: {//表单校验
                    softwaretitle: [
                        {required: true, message: '请输入标题', trigger: 'blur'}
                    ],
                    softwareremarks: [
                        {required: true, message: '请输入备注', trigger: 'blur'}
                    ],
                    // iphoneaddress: [
                    //     {required: true, message: '请输入iPhone地址', trigger: 'blur'}
                    // ],
                    // windowsaddress: [
                    //     {required: true, message: '请输入Windows地址', trigger: 'blur'}
                    // ],
                    // androidaddress: [
                    //     {required: true, message: '请输入Android地址', trigger: 'blur'}
                    //  ]
                    // ,
                    softwarepic: [
                        {required: true, message: '请上传banner图', trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            handleAvatarSuccess(res) {
                console.info(res.data)
                if(res.code==-1){
                    alert(res.message)
                }else if(res.code==9){
                    alert(res.message)
                }else {
                    this.formData.softwarepic = res.data;
                }
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isGIF = file.type === 'image/gif';
                const isPNG = file.type === 'image/png';
                const isBMP = file.type === 'image/bmp';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG && !isGIF && !isPNG && !isBMP) {
                    this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传图片大小不能超过 2MB!');
                }
                return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
            },
            /*获取列表事件*/
            getMbSoftware() {
                this.loading = true;
                getMbSoftware().then(response => {
                    console.info(response)
                    if (response.code != 0) {
                        this.$message.error(response.message);
                        this.loading = false;
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
                        if(this.formData.androidaddress==null||this.formData.androidaddress.length==0){
                            delete this.formData.androidaddress
                        }
                        if(this.formData.windowsaddress==null||this.formData.windowsaddress.length==0){
                            delete this.formData.windowsaddress
                        }
                        if(this.formData.iphoneaddress==null||this.formData.iphoneaddress.length==0){
                            delete this.formData.iphoneaddress
                        }
                        this.formSumbitLoding = true;
                        addOrEditSoftware(this.formData).then(response => {
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
                            // 刷新表单
                            // this.$refs["ruleForm"].resetFields();
                        });
                    }
                });
                //new URLSearchParams(this.query)  提交数据格式不正确时可以转一下
            },
            sortSumbit(){
                var ids = this.multipleSelection.map(item => item.softwareid).join();
                this.loading = true;
                if(this.softwareSort!="" && this.softwareSort.length>0){
                    var params = new URLSearchParams();
                    params.append("ids", ids);
                    params.append("sort",this.softwareSort);
                    softwareDelOrSort(params)
                        .then(response => {
                            this.loading = false;
                            if (response.code) {
                                this.$message.error(response.message);
                                return false;
                            }
                            this.$message.success("操作成功");
                            this.sortVisible=false;
                            this.softwareSort="";
                            //返回第一页
                            this.query.currentPage = 1;
                            // 刷新数据
                            this.getList();
                        })
                        .catch(() => {
                            this.loading = false;
                        });
                }else {
                    this.$message({ message: "排序值不可为空！",type: "warning"});
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
                this.createTime = [];
                this.query = {
                    currentPage: 1,
                    pageSize: 10,
                };
                this.getList();
            },

            /*显示 -新增或者编辑弹框*/
            showDialogAddOrEdit(index, row) {
                console.info(row)
                this.formData = Object.assign({}, formJson);//将弹框内表单内容初始化
                if (row !== null) {
                    //如果row不为空，说明是修改，因为row是修改时携带过来此列的所有信息
                    this.formData = Object.assign({}, row);//修改则将列内容深拷贝到表单内容中
                    this.formData.softwarestate+="";
                    this.formData.addressdisplay+="";

                    delete this.formData.creationtime;
                    delete this.formData.softwaresort;
                    this.dialogAddOrEditName = "编辑";
                } else {
                    this.dialogAddOrEditName = "新增";
                }
                this.dialogAddOrEdit = true;
            },
            showDialogDetails(index, row){
                this.formData = Object.assign({}, row);//修改则将列内容深拷贝到表单内容中
                this.dialogAddOrEditName = "查看";
                this.dialogDetails = true;
            },
            /*隐藏 -新增或编辑弹框*/
            hideDialogAddOrEdit() {
                this.dialogAddOrEdit = false;
                this.dialogDetails =false;
                //隐藏弹框时需要清除弹框内容
                if (this.$refs["ruleForm"]) {
                    this.$refs['ruleForm'].resetFields();//清除formData表单
                    this.$refs["ruleForm"].clearValidate();//清除表单验证
                }
            },
            hideEditSort(){
                this.sortVisible=false;
                this.softwareSort="";
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
                var ids = this.multipleSelection.map(item => item.softwareid).join(); //获取所有选中行的id组成的字符串，以逗号分隔
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
                            softwareDelOrSort(params)
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
            sortGroup() {
                console.log(this.multipleSelection)
                var ids = this.multipleSelection.map(item => item.softwareid).join(); //获取所有选中行的id组成的字符串，以逗号分隔
                if (ids == "") {
                    this.$message({
                        message: "请先选择",
                        type: "warning"
                    });
                    return;
                }
                 this.sortVisible=true;
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
            this.getMbSoftware();
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
    .softwareAddOrEdit el-radio+.el-radio {
        margin-left: 20px !important;
    }
    /*.softwareAddOrEdit .el-form-item__label{*/
    /*    width: 110px !important;*/
    /*}*/
    .avatar-uploader .el-upload {
        border: 1px dashed #409EFF;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    /*avatar-uploader .el-upload--picture-card{*/
    /*    width: 341.5px !important;*/
    /*    height: 177.25px !important;*/
    /*}*/
    /*.el-upload{*/
    /*    width: 341.5px;*/
    /*    height: 177.25px;*/
    /*}*/
    .softwareBanner .el-upload{
        width: 334px;
        height:  177.25px;
    }
    .softwareBanner img{
        width: 334px;
        height:  177.25px;
    }
    .softwareAddOrEdit .el-dialog__footer{
        padding: 0px 20px 20px !important;
    }
    .softwareAddOrEdit .el-dialog__body{
        padding: 30px 20px 0px 20px !important;
    }
    .el-table th>.cell {
        text-align: center!important;
    }
</style>
