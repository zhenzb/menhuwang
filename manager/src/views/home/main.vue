<template>
    <!-- <div class="adsad"> -->
        <!-- <div class="ms-doc">
            <h3>README.md</h3>
            <article>
                <h1>vue-admin</h1>
                <p>基于Vue.js 2.x系列 + Element UI + RBAC/AUTH权限 的后台管理系统</p>
                <h2>前言</h2>
                <p><strong>项目前端地址：</strong><a href="https://github.com/lmxdawn/vue-admin-html" target="_blank">https://github.com/lmxdawn/vue-admin-html</a></p>
                <p><strong>项目JAVA后端地址：</strong><a href="https://github.com/lmxdawn/vue-admin-java" target="_blank">https://github.com/lmxdawn/vue-admin-java</a></p>
                <p><strong>项目PHP后端地址：</strong><a href="https://github.com/lmxdawn/vue-admin-php" target="_blank">https://github.com/lmxdawn/vue-admin-php</a></p>
                <p>欢迎大家的star</p>
                <h2>功能</h2>
                <el-checkbox disabled checked>管理员登录</el-checkbox>
                <br>
                <el-checkbox disabled checked>登录</el-checkbox>
                <br>
                <el-checkbox disabled checked>修改密码</el-checkbox>
                <br>
                <el-checkbox disabled checked>角色管理</el-checkbox>
                <br>
                <el-checkbox disabled checked>权限管理</el-checkbox>
                <br>
            </article>
        </div> -->
        <!-- <img src="https://img.zcool.cn/community/0183075934cfd6a8012193a35ab4d4.png@2o.png"/> -->

    <!-- </div> -->

    <div class='welcomeBox'>
        <div class='inWelcome'>
            <img class='welcomeImg' src="http://www.gemshield.cn/mijia/image/welcome.png">
            <div class='TextBox'>
                <div class='welcome'>WELCOME</div>
                <div class='sys_name'>欢迎使用撼动科技管理系统</div>
            </div>
        </div>
        
    </div>
</template>

<script>
export default {
    data() {
        return {};
    }
};
</script>

<style>
/* .ms-doc {
    width: 100%;
    max-width: 980px;
    font-family: PingFang SC, Helvetica Neue, Arial, sans-serif;
    color: #2d2f33;
}
.ms-doc h3 {
    padding: 9px 10px 10px;
    margin: 0;
    font-size: 14px;
    line-height: 17px;
    background-color: #f5f5f5;
    border: 1px solid #d8d8d8;
    border-bottom: 0;
    border-radius: 3px 3px 0 0;
}
.ms-doc article {
    padding: 45px;
    word-wrap: break-word;
    background-color: #fff;
    border: 1px solid #ddd;
    border-bottom-right-radius: 3px;
    border-bottom-left-radius: 3px;
}
.ms-doc article h1 {
    font-size: 32px;
    padding-bottom: 10px;
    margin-bottom: 15px;
    border-bottom: 1px solid #ddd;
}
.ms-doc article h2 {
    margin: 24px 0 16px;
    font-weight: 600;
    line-height: 1.25;
    padding-bottom: 7px;
    font-size: 24px;
    border-bottom: 1px solid #eee;
}
.ms-doc article p {
    margin-bottom: 15px;
    line-height: 1.5;
}
.ms-doc article .el-checkbox {
    margin-bottom: 5px;
} */
/* .el-main {
    margin: 0px !important;
} */
.welcomeBox {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;
    width: 100%;
    height: 100%;
    background: #e3e3e3;
}
.inWelcome {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;
    width: 350px;
    height: 380px;
    text-align: center;
}
.welcomeImg {
    width: 350px;
}
.TextBox {
    width: 100%;
    height: auto;
    margin-top: 30px;
    text-align: center;
    letter-spacing: 2px;
}
.welcome {
    font-size: 50px;
    color: #3b5e8d;
    margin-bottom: 5px;
}
.sys_name {
    font-size: 22px;
    color: grey;
}
</style>
