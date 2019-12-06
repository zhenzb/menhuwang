<template>
    <div class="productbackgroud">
        <el-form label-width="100px" >
            <el-form-item label="网站LOGO:">
                <span v-if="basicsInfo.logopic!=null">
                    <img style="width: 214px;height: 90px;border-radius: 6px;" :src="basicsInfo.logopic">
                </span>
                <span v-else style="color: black">暂无背景图片</span>
            </el-form-item>
            <el-form-item label="小程序码:">
                <span v-if="basicsInfo.vxcode!=null">
                    <img style="width: 120px; height: 120px; border-radius: 6px;" :src="basicsInfo.vxcode">
                </span>
                <span v-else style="color: black">暂无背景图片</span>
            </el-form-item>
            <el-form-item label="导航背景:">
                <span v-if="basicsInfo.backgroudpic!=null">
                    <img style="width: 341.5px;height: 63px; border-radius: 6px;" :src="basicsInfo.backgroudpic">
                </span>
                <span v-else style="color: black">暂无标题图片</span>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="editSumbit">编辑</el-button>
        </span>

        <!--新增/编辑弹框start--333s-->
        <el-dialog
            :title="dialogAddOrEditName"
            :visible.sync="dialogAddOrEdit"
            width="50%"
            :before-close="hideDialogAddOrEdit"
            class="basicsAddOrEdit"
            top="5vh"
        >

            <el-form :model="formData" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="网站LOGO" prop="logopic" class="logopic">
                    <el-upload
                        class="avatar-uploader"
                        :action="uploaduri"
                        :on-success="handleLogoPicSuccess"
                        :before-upload="beforeAvatarUpload"
                        accept=".png, .jpg, .gif, .jpeg, .bmp"
                        :show-file-list="false"
                    >
                        <img v-if="formData.logopic" :src="formData.logopic"  >
                        <i v-else class="el-icon-plus avatar-uploader-icon" style="font-size: 26px;line-height: 3;"></i>
                    </el-upload>
                    <span>注：上传图片尺寸为214*90像素</span>
                </el-form-item>
                <el-form-item label="小程序码" prop="vxcode" class="vxcode">
                    <el-upload
                        class="avatar-uploader"
                        :action="uploaduri"
                        :on-success="handleVXCodeSuccess"
                        :before-upload="beforeAvatarUpload"
                        accept=".png, .jpg, .gif, .jpeg, .bmp"
                        :show-file-list="false"
                    >
                        <img v-if="formData.vxcode" :src="formData.vxcode"  >
                        <i v-else class="el-icon-plus avatar-uploader-icon" style="font-size: 26px;line-height: 4.5;"></i>
                    </el-upload>
                    <span>注：上传图片尺寸为240*240像素</span>
                </el-form-item>
                <el-form-item label="导航背景" prop="backgroudpic" class="basicsbackgroudpic">
                    <el-upload
                        class="avatar-uploader"
                        :action="uploaduri"
                        :on-success="handleBackgroudUrlSuccess"
                        :before-upload="beforeAvatarUpload"
                        accept=".png, .jpg, .gif, .jpeg, .bmp"
                        :show-file-list="false"
                    >
                        <img v-if="formData.backgroudpic" :src="formData.backgroudpic"  >
                        <i v-else class="el-icon-plus avatar-uploader-icon" style="font-size: 26px;line-height: 2.5;"></i>
                    </el-upload>
                    <span>注：上传图片尺寸为2732*378像素</span>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogAddOrEdit = false">取 消</el-button>
                <el-button type="primary" :loading="formSumbitLoding" @click="formSumbit">提交</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
    import {getBasicsInfo,basicsEdit} from "../../api/website/basics";
    import { BASE_URL } from "../../config/app";
    export default {
        data() {
            return {
                basicsInfo:{},
                uploaduri:BASE_URL+"/tool/uploadpro",
                totalNumber: 0,//分页总页数
                loading: false,//列表加载
                allLoding: false,//全屏加载
                formSumbitLoding: false,//表单提交加载
                dialogAddOrEdit: false,//新增或编辑弹框开关
                dialogAddOrEditName: "",//新增或编辑弹框名字
                formData: {},//新增或修改的对象
                rules: {//表单校验
                    logopic: [
                        {required: true, message: '请上传LOGO图', trigger: 'blur'},
                    ],
                    vxcode: [
                        {required: true, message: '请上传小程序码', trigger: 'blur'},
                    ],
                    backgroudpic: [
                        {required: true, message: '请上传背景图片', trigger: 'blur'},
                    ]
                }
            };
        },
        methods: {
            /*背景图片上传*/
            handleBackgroudUrlSuccess(res) {
                console.info(res)
                if(res.code==0){
                    alert(res.message)
                }else if(res.code==9){
                    alert(res.message)
                }else {
                    this.formData.backgroudpic=res.data;
                }
            },
            /*LOGO图片上传*/
            handleLogoPicSuccess(res) {
                console.info(res)
                if(res.code==0){
                    alert(res.message)
                }else if(res.code==9){
                    alert(res.message)
                }else {
                    this.formData.logopic=res.data;
                }
            },
            /*小程序图片上传*/
            handleVXCodeSuccess(res) {
                console.info(res)
                if(res.code==0){
                    alert(res.message)
                }else if(res.code==9){
                    alert(res.message)
                }else {
                    this.formData.vxcode=res.data;
                }
            },
            /*图片上传前操作*/
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isGIF = file.type === 'image/gif';
                const isPNG = file.type === 'image/png';
                const isBMP = file.type === 'image/bmp';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG && !isGIF && !isPNG && !isBMP) {
                    this.common.errorTip('上传图片必须是JPG/GIF/PNG/BMP 格式!');
                }
                if (!isLt2M) {
                    this.common.errorTip('上传图片大小不能超过 2MB!');
                }
                return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
            },
            /*获取列表事件*/
            getBasicsInfo() {
                this.loading = true;
                getBasicsInfo().then(response => {
                    console.info(response)
                    if (response.code != 0) {
                        this.$message.error(response.message);
                        this.loading = false;
                        return false;
                    }
                    this.basicsInfo=response.data;
                    setTimeout(() => {
                        this.loading = false;
                    }, 0.3 * 1000);
                }).catch(() => {
                    this.loading = false;
                })
            },
            editSumbit(){
                this.dialogAddOrEdit = true;
                this.formData=  JSON.parse(JSON.stringify(this.basicsInfo))
                console.info(this.formData)
            },
            /*新增或者编辑提交事件*/
            formSumbit() {
                //表单校验通过
                this.$refs["ruleForm"].validate(valid => {
                    if (valid) {
                        this.formSumbitLoding = true;
                        //提交操作
                        if(this.formData.id==null){
                            delete this.formData.id;
                        }

                        basicsEdit(this.formData).then(response =>{
                            if (response.code) {
                                this.$message.error(response.message);
                                this.formSumbitLoding = false;
                                return false;
                            }
                            setTimeout(() => {
                                this.$message.success("提交成功");
                                this.formSumbitLoding = false;
                                this.dialogAddOrEdit = false;
                                this.getBasicsInfo();
                            }, 1000);
                        }).catch(()=>{
                            this.formSumbitLoding = false;
                        })

                    }
                });
                //new URLSearchParams(this.query)  提交数据格式不正确时可以转一下
            },

            /*显示 -新增或者编辑弹框*/
            showDialogAddOrEdit() {
                this.dialogAddOrEdit = true;
                this.dialogAddOrEditName = "编辑";
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
        },
        created() {
            this.getBasicsInfo();
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
    /*.productbackgroud .el-form-item{*/
    /*    border-bottom: 1px solid rgb(204, 204, 204);*/
    /*    padding-bottom: 30px;*/
    /*}*/
    .productbackgroud {
        width: 95%;
        margin-left: 2%;
        margin-top: 20px;
    }
    .softwareAddOrEdit .el-textarea__inner{
        height: 80px;
    }
    .avatar-uploader .el-upload {
        border: 1px dashed #409EFF;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    /*avatar-uploader .el-upload--picture-card{*/
    /*    width: 200px !important;*/
    /*    height: 190px !important;*/
    /*}*/
    /*.customerbackgroudurl .el-upload{*/
    /*    width: 320px;*/
    /*    height: 180px;*/
    /*}*/

    /*.customerbackgroudurl img{*/
    /*    width: 320px;*/
    /*}*/
    .logopic .el-upload {
        width: 214px ;
        height: 90px ;
    }
    .logopic img{
        width: 214px;
        height: 90px;
    }
    .vxcode .el-upload {
        width: 120px ;
        height: 120px ;
    }
    .vxcode img{
        width: 120px;
        height: 120px;

    }
    .basicsbackgroudpic .el-upload{
        width: 341.5px;
        height: 63px;
    }
    .basicsbackgroudpic img{
        width: 341.5px;
        height: 63px;
    }
    .basicsAddOrEdit .el-dialog__footer{
        padding: 20px 60px 20px !important;
    }
    .basicsAddOrEdit .el-dialog__body{
        padding: 30px 20px 0px 20px !important;
    }
</style>
