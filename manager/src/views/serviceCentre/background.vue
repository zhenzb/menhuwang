<template>
    <div class="productbackgroud">
        <el-form label-width="100px">
            <el-form-item label="背景图片">
                <span v-if="productbackgroudpicinfo.backgroundpicurl!=null">
                    <img style="width: 341.5px; height:  177.25px;border-radius: 6px;" :src="productbackgroudpicinfo.backgroundpicurl">
                </span>
                <span v-else style="color: black">暂无背景图片</span>
            </el-form-item>
            <el-form-item label="标题图片">
                <span v-if="productbackgroudpicinfo.titlepicurl!=null">
                    <img style="width: 346px; height: 67px;border-radius: 6px;"  :src="productbackgroudpicinfo.titlepicurl"  >
                </span>
                <span v-else style="color: black">暂无标题图片</span>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer" style="position: absolute;margin-top: -10px;">
            <el-button type="primary" @click="editSumbit">编辑</el-button>
        </span>

        <!--新增/编辑弹框start--333s-->
        <el-dialog
            :title="dialogAddOrEditName"
            :visible.sync="dialogAddOrEdit"
            width="50%"
            :before-close="hideDialogAddOrEdit"
            class="softwareAddOrEdit"
            top="5vh"
        >

            <el-form :model="formData" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="背景图片" prop="backgroundpicurl" class="serverbackgroudurl">
                    <el-upload
                        class="avatar-uploader"
                        :action="uploaduri"
                        :on-success="handleBackgroudPicSuccess"
                        :before-upload="beforeAvatarUpload"
                        accept=".png,.jpg,.gif, .jpeg, .bmp"
                        :show-file-list="false"
                    >
                        <img v-if="formData.backgroundpicurl" :src="formData.backgroundpicurl"  >
                        <i v-else class="el-icon-plus avatar-uploader-icon" style="font-size: 26px;line-height: 6;"></i>
                    </el-upload>
                    <span>注：上传图片尺寸宽为2732像素</span>
                </el-form-item>
                <el-form-item label="标题图片" prop="titlepicurl" class="servertitlepicurl">
                    <el-upload
                        class="avatar-uploader"
                        :action="uploaduri"
                        :on-success="handleTitlePicSuccess"
                        :before-upload="beforeAvatarUpload"
                        :show-file-list="false"
                    >
                        <img v-if="formData.titlepicurl" :src="formData.titlepicurl"  >
                        <i v-else class="el-icon-plus avatar-uploader-icon" style="font-size: 26px;line-height:2.5;"></i>
                    </el-upload>
                    <span>注：上传图片尺寸为1384*268像素</span>
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
    import {getBackgroudPic,backgroudPicEdit} from "../../api/problem/problem";
    import { BASE_URL } from "../../config/app";
    export default {
        data() {
            return {
                productbackgroudpicinfo:{},
                uploaduri:BASE_URL+"/tool/uploadpro",
                totalNumber: 0,//分页总页数
                loading: false,//列表加载
                allLoding: false,//全屏加载
                formSumbitLoding: false,//表单提交加载
                dialogAddOrEdit: false,//新增或编辑弹框开关
                dialogAddOrEditName: "",//新增或编辑弹框名字
                formData: {},//新增或修改的对象
                rules: {//表单校验
                    // username: [
                    //     {required: true, message: '请输入姓名', trigger: 'blur'},
                    //     {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    // ]
                }
            };
        },
        methods: {
            /*背景图片上传*/
            handleBackgroudPicSuccess(res) {
                console.info(res)
                if(res.code==0){
                    alert(res.message)
                }else if(res.code==9){
                    alert(res.message)
                }else {
                    this.formData.backgroundpicurl=res.data;
                }
            },
            /*标题图片上传*/
            handleTitlePicSuccess(res){
                console.info(res)
                if(res.code==0){
                    alert(res.message)
                }else if(res.code==9){
                    alert(res.message)
                }else {
                    this.formData.titlepicurl=res.data;
                }
            },
            /*图片上传前操作*/
            beforeAvatarUpload(file) {;
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
            getBackgroudPic() {
                this.loading = true;
                let query={
                    type:2
                }
                getBackgroudPic(query).then(response => {
                    console.info(response)
                    if (response.code != 0) {
                        this.$message.error(response.message);
                        this.loading = false;
                        return false;
                    }
                    this.productbackgroudpicinfo=response.data;
                    setTimeout(() => {
                        this.loading = false;
                    }, 0.3 * 1000);
                }).catch(() => {
                    this.loading = false;
                })
            },
            editSumbit(){
                this.dialogAddOrEdit = true;
                this.formData=  JSON.parse(JSON.stringify(this.productbackgroudpicinfo))
                console.info(this.formData)
            },
            /*新增或者编辑提交事件*/
            formSumbit() {
                //表单校验通过
                this.$refs["ruleForm"].validate(valid => {
                    if (valid) {
                        this.formSumbitLoding = true;

                        console.info(this.formData.backgroundpicid)
                        //提交操作
                        if(this.formData.backgroundpicid==null){
                            delete this.formData.backgroundpicid;
                        }
                        this.formData.backgroundtype=2;

                        backgroudPicEdit(this.formData).then(response =>{
                            if (response.code) {
                                this.$message.error(response.message);
                                this.formSumbitLoding = false;
                                return false;
                            }
                            setTimeout(() => {
                                this.$message.success("提交成功");
                                this.formSumbitLoding = false;
                                this.dialogAddOrEdit = false;
                                this.getBackgroudPic();
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
            this.getBackgroudPic();
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
<style type="text/scss" lang="scss">
    .productbackgroud .el-form-item{
        border-bottom: 1px solid rgb(204, 204, 204);
        padding-bottom: 30px;
    }
    .productbackgroud {
        width: 95%;
        margin-left: 2%;
        margin-top: 50px;
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
    /*    width: 320px !important;*/
    /*    height: 190px !important;*/
    /*}*/
    .serverbackgroudurl .el-upload{
        width: 341.5px;
        height:  177.25px;
    }
    .serverbackgroudurl img{
        width: 341.5px;
        height:  177.25px;
    }
    .servertitlepicurl .el-upload{
        width: 346px;
        height: 67px;
    }
    .servertitlepicurl img{
        width: 346px;
        height: 67px;
    }
</style>
