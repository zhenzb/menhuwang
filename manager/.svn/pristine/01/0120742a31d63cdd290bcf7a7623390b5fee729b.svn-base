<template>
    <div class="productbackgroud ">
        <el-form label-width="80px" class="syscustomer">
            <el-form-item label="地址:">
                <el-input v-model="customerInfo.address" style="width: 45%" readonly="readonly"></el-input>
            </el-form-item>
            <el-form-item label="联系电话:">
                <el-input v-model="customerInfo.phone" style="width: 45%" readonly="readonly"></el-input>
            </el-form-item>
            <el-form-item label="邮箱:">
                <el-input v-model="customerInfo.email" style="width: 45%" readonly="readonly"></el-input>
            </el-form-item>
            <el-form-item label="公众号:">
                <span v-if="customerInfo.qrcode!=null">
                    <img style="width: 190px;height: 190px;border-radius: 6px;" :src="customerInfo.qrcode">
                </span>
                <span v-else style="color: black">暂无背景图片</span>
            </el-form-item>

        </el-form>
        <span slot="footer" class="dialog-footer" >
            <el-button type="primary" @click="editSumbit">编辑</el-button>
        </span>

        <!--新增/编辑弹框start--333s-->
        <el-dialog
            :title="dialogAddOrEditName"
            :visible.sync="dialogAddOrEdit"
            width="50%"
            :before-close="hideDialogAddOrEdit"
            class="customerAddOrEdit"
            top="5vh"
        >

            <el-form :model="formData" :rules="rules" ref="ruleForm" label-width="110px" class="demo-ruleForm">
                <el-form-item label="地址" prop="address">
                    <el-input v-model="formData.address"></el-input>
                </el-form-item>
                <el-form-item label="联系电话:" prop="phone">
                    <el-input v-model="formData.phone" ></el-input>
                </el-form-item>
                <el-form-item label="邮箱:" prop="email">
                    <el-input v-model="formData.email" ></el-input>
                </el-form-item>
                <el-form-item label="公众号" prop="qrcode" class="qrcode">
                    <el-upload
                        class="avatar-uploader"
                        :action="uploaduri"
                        :on-success="handleQRCodeSuccess"
                        :before-upload="beforeAvatarUpload"
                        accept=".png, .jpg, .gif, .jpeg, .bmp"
                        :show-file-list="false"
                    >
                        <img v-if="formData.qrcode" :src="formData.qrcode"  >
                        <i v-else class="el-icon-plus avatar-uploader-icon" style="font-size: 26px;line-height: 7;"></i>
                    </el-upload>
                    <span>注：上传图片尺寸为190*190像素</span>
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
    import {getCustomerInfo,customerEdit} from "../../api/sysCustomer/syscustomer";
    import { BASE_URL } from "../../config/app";
    export default {
        data() {
            return {
                customerInfo:{},
                uploaduri:BASE_URL+"/tool/uploadpro",
                totalNumber: 0,//分页总页数
                loading: false,//列表加载
                allLoding: false,//全屏加载
                formSumbitLoding: false,//表单提交加载
                dialogAddOrEdit: false,//新增或编辑弹框开关
                dialogAddOrEditName: "",//新增或编辑弹框名字
                formData: {},//新增或修改的对象
                rules: {//表单校验
                    address: [
                        {required: true, message: '请输入地址', trigger: 'blur'},
                    ],
                    phone: [
                        {required: true, message: '请输入手机号', trigger: 'blur'},
                    ],
                    email: [
                        {required: true, message: '请输入邮箱', trigger: 'blur'},
                    ],
                    qrcode: [
                        {required: true, message: '请上传公众号二维码', trigger: 'blur'},
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
            /*标题图片上传*/
            handleQRCodeSuccess(res) {
                console.info(res)
                if(res.code==0){
                    alert(res.message)
                }else if(res.code==9){
                    alert(res.message)
                }else {
                    this.formData.qrcode=res.data;
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
                    this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传图片大小不能超过 2MB!');
                }
                return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
            },
            /*获取列表事件*/
            getCustomerInfo() {
                this.loading = true;
                getCustomerInfo().then(response => {
                    console.info(response)
                    if (response.code != 0) {
                        this.$message.error(response.message);
                        this.loading = false;
                        return false;
                    }
                    this.customerInfo=response.data;
                    setTimeout(() => {
                        this.loading = false;
                    }, 0.3 * 1000);
                }).catch(() => {
                    this.loading = false;
                })
            },
            editSumbit(){
                this.dialogAddOrEdit = true;
                this.formData=  JSON.parse(JSON.stringify(this.customerInfo))
                console.info(this.formData)
            },
            /*新增或者编辑提交事件*/
            formSumbit() {
                //表单校验通过
                this.$refs["ruleForm"].validate(valid => {
                    if (valid) {
                        this.formSumbitLoding = true;
                        //提交操作
                        if(this.formData.customerid==null){
                            delete this.formData.customerid;
                        }
                        customerEdit(this.formData).then(response =>{
                            if (response.code) {
                                this.$message.error(response.message);
                                this.formSumbitLoding = false;
                                return false;
                            }
                            setTimeout(() => {
                                this.$message.success("提交成功");
                                this.formSumbitLoding = false;
                                this.dialogAddOrEdit = false;
                                this.getCustomerInfo();
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
            this.getCustomerInfo();
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
    .syscustomer .el-form-item__label{
        text-align:left;
    }
    .productbackgroud {
        width: 95%;
        margin-left: 2%;
        margin-top: 40px;
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
    .customerbackgroudurl .el-upload{
        width: 341.5px;
        height: 63px;
    }
    
    .customerbackgroudurl img{
        width: 341.5px;
        height: 63px;
    }
    .qrcode .el-upload {
        width: 190px ;
        height: 190px ;
    }
    .qrcode img{
        width: 190px;
        height: 190px;
    }
    .customerAddOrEdit .el-dialog__footer{
        padding: 20px 34px 20px !important;
    }
    .customerAddOrEdit .el-dialog__body{
        padding: 30px 20px 0px 20px !important;
    }
</style>
