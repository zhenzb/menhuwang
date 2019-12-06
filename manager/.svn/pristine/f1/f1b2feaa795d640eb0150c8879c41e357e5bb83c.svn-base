<template>
    <div class="introduction">
        <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="editSumbit">编辑</el-button>
        </span>
        <el-form label-width="100px" style="margin-top: 20px" class="textcontent">
            <el-form-item label="文字内容:">
<!--                <el-input v-model="formData.softwaretitle" style="width: 60%"></el-input>-->
                <div style="color: black;width: 60%">{{productintroductioninfo.textcontent}} </div>
            </el-form-item>
            <el-form-item label="按钮下文字" >
                <div style="color: black;width: 60%">{{productintroductioninfo.btndowntext}}</div>
            </el-form-item>
            <el-form-item label="banner图片">
                <div v-for="item in bannerList" >
                    <img style="width: 335.5px;height: 168px;border-radius: 6px;" :src="item"  >
                </div>

            </el-form-item>
            <el-form-item label="背景图片">
                <span v-if="productintroductioninfo.backgroundpicurl!=null">
                    <img style="width: 334px;height:  177.25px;border-radius: 6px;" :src="productintroductioninfo.backgroundpicurl">
                </span>
                <span v-else style="color: black">暂无背景图片</span>
            </el-form-item>
            <el-form-item label="标题图片">
                <span v-if="productintroductioninfo.titlepicurl!=null">
                    <img style="width: 346px;height: 67px;border-radius: 6px;"  :src="productintroductioninfo.titlepicurl"  >
                </span>
                <span v-else style="color: black">暂无标题图片</span>
            </el-form-item>
        </el-form>
        <!--新增/编辑弹框start--333s-->
        <el-dialog
            :title="dialogAddOrEditName"
            :visible.sync="dialogAddOrEdit"
            width="60%"
            :before-close="hideDialogAddOrEdit"
            class="introductionAddOrEditDialog"
            top="5vh"
        >
            <el-form :model="formData" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm textcontentDialog">
                <el-form-item label="内容文字：" prop="textcontent" >
                    <el-input type="textarea" maxlength="500" v-model="formData.textcontent" ></el-input>
                </el-form-item>
                <el-form-item label="按钮下文字：" prop="btndowntext">
                    <el-input type="textarea" v-model="formData.btndowntext" ></el-input>
                </el-form-item>
                <el-form-item label="banner图片" class="introductionBannerpic">
                    <el-upload
                        :action="uploaduri"
                        list-type="picture-card"
                        :on-remove="handleBannerPicRemove"
                        :on-success="handleBannerPicSuccess"
                        accept=".png, .jpg, .gif, .jpeg, .bmp"
                        :show-file-list="true"
                        :file-list="urlList"
                        :limit="5"
                        >
                        <i class="el-icon-plus"></i>
                    </el-upload>
                    <span>注：上传图片尺寸为1342*672像素</span>
                </el-form-item>
                <el-form-item label="背景图片" prop="backgroundpicurl" class="introductionBackgroudurl">
                    <el-upload
                        class="avatar-uploader"
                        :action="uploaduri"
                        :on-success="handleBackgroudPicSuccess"
                        :before-upload="beforeAvatarUpload"
                        accept=".png, .jpg, .gif, .jpeg, .bmp"
                        :show-file-list="false"
                    >
                        <img v-if="formData.backgroundpicurl" :src="formData.backgroundpicurl"  >
                        <i v-else class="el-icon-plus avatar-uploader-icon" style="font-size: 26px;line-height: 6;"></i>
                    </el-upload>
                    <span>注：上传图片尺寸宽为2732像素</span>
                </el-form-item>
                <el-form-item label="标题图片" prop="titlepicurl" class="introductionTitlepicurl">
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
    import {getIntroduction,introductionEdit} from "../../api/product/product";
    import { BASE_URL } from "../../config/app";
    export default {
        data() {
            return {
                productintroductioninfo:{},
                bannerList:[],
                uploaduri:BASE_URL+"/tool/uploadpro",
                urlList:[],
                urls:"",
                totalNumber: 0,//分页总页数
                loading: false,//列表加载
                allLoding: false,//全屏加载
                formSumbitLoding: false,//表单提交加载
                cancelToggle: false,//列表是否全选
                multipleSelection: [], //多选集合
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
            handleBannerPicSuccess(res) {
                console.info(res)
                if(res.code==0){
                    alert(res.message)
                }else if(res.code==9){
                    alert(res.message)
                }else {
                    let urls={
                        url:res.data
                    }
                    this.urlList.push(urls)
                }
                console.info(this.urlList)
            },
            handleBannerPicRemove(file){
                console.info(file)
                for (var i = 0; i < this.urlList.length; i++) {
                    if (this.urlList[i].url == file.url) {
                        this.urlList.splice(i, 1);
                    }
                }
                console.info(this.urlList)
            },
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
            getIntroduction() {
                this.loading = true;
                getIntroduction().then(response => {
                    console.info(response)
                    if (response.code != 0) {
                        this.$message.error(response.message);
                        this.loading = false;
                        return false;
                    }
                    this.productintroductioninfo=response.data;
                    this.formData=response.data;
                    this.bannerList=response.data.bannerpic.split(",");
                    setTimeout(() => {
                        this.loading = false;
                    }, 0.3 * 1000);
                }).catch(() => {
                    this.loading = false;
                })
            },
            editSumbit(){
                this.urlList=[];
                for(let i = 0; i <this.bannerList.length;i++){
                    let urls={
                        url:this.bannerList[i]
                    }
                    this.urlList.push(urls);
                }
                this.dialogAddOrEdit = true;
                console.info(this.formData)
            },
            /*新增或者编辑提交事件*/
            formSumbit() {
                //表单校验通过
                this.$refs["ruleForm"].validate(valid => {
                    if (valid) {
                        if(this.urlList.length>0){
                            var urls="";
                            for(var i=0;i<this.urlList.length;i++){
                                urls+=this.urlList[i].url+",";
                            }
                           this.formData.bannerpic= urls.substr(0,urls.length-1)

                        }
                        console.info(this.formData)
                        this.formSumbitLoding = true;
                        //提交操作

                        introductionEdit(this.formData).then(response =>{
                            if (response.code) {
                                this.$message.error(response.message);
                                this.formSumbitLoding = false;
                                return false;
                            }
                            setTimeout(() => {
                                this.$message.success("提交成功");
                                this.formSumbitLoding = false;
                                this.dialogAddOrEdit = false;
                                this.getIntroduction();
                            }, 1000);
                        }).catch(()=>{
                            this.formSumbitLoding = false;
                        })

                    }
                });
                //new URLSearchParams(this.query)  提交数据格式不正确时可以转一下
            },

            /*显示 -新增或者编辑弹框*/
            showDialogAddOrEdit(index, row) {
                this.dialogAddOrEdit = true;
                if (row !== null) {
                    //如果row不为空，说明是修改，因为row是修改时携带过来此列的所有信息
                    this.formData = Object.assign({}, row);//修改则将列内容深拷贝到表单内容中
                    this.dialogAddOrEditName = "编辑";
                } else {
                    this.dialogAddOrEditName = "新增";
                }
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
             this.getIntroduction();
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
    .introduction .textcontent .el-form-item{
        border-top: 1px solid rgb(204, 204, 204);
        padding-top: 30px;
    }
    .introduction {
        width: 95%;
        margin-left: 2%;
    }
    .introductionAddOrEditDialog .el-textarea__inner{
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
    .introductionBackgroudurl .el-upload{
        width: 334px;
        height:  177.25px;
    }
    .introductionBackgroudurl img{
        width: 334px;
        height:  177.25px;
    }
    /*introductionTitlepicurl .el-upload--picture-card{*/
    /*    width: 346px !important;*/
    /*    height: 67px !important;*/
    /*}*/
    .introductionTitlepicurl .el-upload{
        width: 455px;
        height: 67px;
    }
    .introductionTitlepicurl img{
        width: 455px;
        height: 67px;
    }
   .introductionBannerpic .el-upload-list__item{
        width: 335.5px !important;
       height: 168px!important;
    }
    .introductionBannerpic .el-upload {
        width:  335.5px  !important;
        height: 168px!important;
    }
    .introductionAddOrEditDialog .el-upload-list__item{
        display: block !important;
    }
    .introductionAddOrEditDialog .el-dialog__footer{
        padding: 0px 30px 20px !important;
    }
    .introductionAddOrEditDialog .el-dialog__body{
        padding: 30px 20px 0px 20px !important;
    }
</style>
