<template>
  <el-container class="wrapper">
    <el-scrollbar
      class="container-left"
      wrap-class="container-left-wrap"
      :class="{'slide-hide': isCollapse, 'slide-in-left': menuShow}"
    >
<!--      <div class="logo">-->
        <!--图片logo-->
        <!--文字logo-->
<!--         <span>密夹</span>-->
<!--      </div>-->
      <el-menu
        class="menu"
        :default-active="onRoutes"
        :collapse="isCollapse"
        :show-timeout="200"
        mode="vertical"
        style="border: none;"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
        unique-opened
        router
      >
        <sidebar-item v-for="item in routers" :key="item.path" :item="item"></sidebar-item>
      </el-menu>
    </el-scrollbar>
    <el-container
      class="container-box"
      :class="{'slide-hide': isCollapse, 'slide-in-left': menuShow}"
    >
      <el-header class="header">
        <div class="header-left">
          <div
            class="header-toggle"
            @click="menuShow = !menuShow; showSideBar()"
            style="display:none"
          >
            <icon-svg icon-class="zhanxiancaidan"/>
          </div>
          <div class="slide-toggle" @click="toggleSideBar">
            <icon-svg :icon-class="isCollapse?'zhanxiancaidan':'suohuicaidan'"/>
          </div>
        </div>
        <div class="header-tabs-box">
          <el-breadcrumb class="app-levelbar" separator="/">
            <transition-group name="slide-fade" mode="out-in">
              <el-breadcrumb-item v-for="(item,index)  in levelList" :key="item.path">
                <!-- <router-link v-if="index===0" :to="item.redirect||item.path">{{item.name}}</router-link>
                <span v-else class="no-redirect">{{item.name}}</span>-->
                <span
                  v-if="item.redirect==='noredirect'||index==levelList.length-1"
                  class="no-redirect"
                >{{item.name}}</span>
                <router-link v-else :to="item.redirect||item.path">{{item.name}}</router-link>
              </el-breadcrumb-item>
            </transition-group>
          </el-breadcrumb>
        </div>

        <div class="header-right">
          <!-- <div class="screenfull">
            <screenfull/>
          </div>-->
          <el-dropdown trigger="click">
            <!-- <i class="el-icon-setting" style="margin-right: 15px"></i> -->
            <span>
              <el-badge :is-dot="counts>0?true:false" class="item"></el-badge>
              {{username}}
              <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
<!--              <el-dropdown-item @click.native="todo">-->
<!--                <el-badge :value="counts" class="item">-->
<!--                  <span class="el-icon-more">&nbsp;&nbsp;待办事项</span>-->
<!--                </el-badge>-->
<!--              </el-dropdown-item>-->
              <el-dropdown-item @click.native="handlePassword">
                <span class="el-icon-edit">&nbsp;&nbsp;修改密码</span>
              </el-dropdown-item>
              <el-dropdown-item @click.native="loginOut">
                <span class="el-icon-back">&nbsp;&nbsp;退出登录</span>
              </el-dropdown-item>
              <!-- <el-dropdown-item divided>主题切换</el-dropdown-item> -->
            </el-dropdown-menu>
          </el-dropdown>
        </div>

        <!--新增界面-->
        <el-dialog
          title="修改密码"
          :visible.sync="passwordFormVisible"
          width="1000px"
          top="5vh"
          v-dialogDrag
        >
          <el-form :model="passwordFormData" :rules="passwordFormDataRules" ref="passwordFormData">
            <el-form-item label="原始密码" prop="oldPassword">
              <el-input type="password" v-model="passwordFormData.oldPassword" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="newPassword">
              <el-input type="password" v-model="passwordFormData.newPassword" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkNewPassword">
              <el-input
                type="password"
                v-model="passwordFormData.checkNewPassword"
                auto-complete="off"
              ></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click.native="passwordFormVisible = !passwordFormVisible">取消</el-button>
            <el-button
              type="primary"
              @click.native="addSubmit('passwordFormData')"
              :loading="passwordLoading"
            >提交</el-button>
          </div>
        </el-dialog>

        <!-- 待办事项 -->
        <el-dialog title="待办事项" :visible.sync="todoVisible" width="700px" bottom="5vh" v-dialogDrag>
          <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column prop="prescriptionid" label="药方编号" width="180"></el-table-column>
            <el-table-column prop="createtime" label="创建时间">
              <template slot-scope="scope">
                <i class="el-icon-time" v-if="scope.row.createtime!=null"></i>
                <span
                  v-if="scope.row.createtime!=null"
                >{{ scope.row.createtime | formatDateStr('yyyy-MM-dd hh:mm:ss') }}</span>
                <span v-else>-</span>
              </template>
            </el-table-column>
            <el-table-column width="145" label="操作" align="center">
              <template slot-scope="scope">
                <el-button
                  type="primary"
                  size="mini"
                  @click.native="goprescription(scope.row.prescriptionid)"
                >生成药方</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!-- 有人开方了 -->
        </el-dialog>
      </el-header>

      <!--遮板-->
      <div class="main-mask" v-show="menuShow" @click="menuShow = !menuShow"></div>

      <el-main class="main">
        <!-- <transition name="move" mode="out-in"> -->
        <transition name="fade-transform" mode="out-in">
          <router-view></router-view>
        </transition>
      </el-main>
    </el-container>
  </el-container>
</template>


<script>
import { mapGetters } from "vuex";
import SidebarItem from "./SidebarItem.vue";
import Screenfull from "@/components/Screenfull";
import { password } from "../../api/auth/login";
import { getAdminId } from "../../utils/auth";
export default {
    data() {
        let validatePass = (rule, value, callback) => {
            if (value === "") {
                callback(new Error("请输入密码"));
            } else {
                if (this.passwordFormData.checkNewPassword !== "") {
                    this.$refs.passwordFormData.validateField(
                        "checkNewPassword"
                    );
                }
                callback();
            }
        };
        let validatePass2 = (rule, value, callback) => {
            if (value === "") {
                callback(new Error("请再次输入密码"));
            } else if (value !== this.passwordFormData.newPassword) {
                callback(new Error("两次输入密码不一致!"));
            } else {
                callback();
            }
        };
        return {
            menuShow: false,
            levelList: null,
            passwordLoading: false,
            passwordFormVisible: false,
            passwordFormData: {
                oldPassword: "",
                newPassword: "",
                checkNewPassword: ""
            },
            passwordFormDataRules: {
                oldPassword: [
                    {
                        required: true,
                        message: "请输入原始密码",
                        trigger: "blur"
                    }
                ],
                newPassword: [
                    {
                        required: true,
                        message: "请输入新密码",
                        trigger: "blur"
                    },
                    { validator: validatePass, trigger: "blur" }
                ],
                checkNewPassword: [
                    {
                        required: true,
                        message: "请再次输入密码",
                        trigger: "blur"
                    },
                    { validator: validatePass2, trigger: "blur" }
                ]
            },
            isshow: 0,
            websock: null,
            type: 0,
            counts: 0,
            todoVisible: false,
            tableData: [],
            lockReconnect: false, //当前websocket是否连接
            reconnectCount: 5, //连接失败次数
            a: null
        };
    },
    components: {
        SidebarItem,
        Screenfull
    },
    computed: {
        ...mapGetters({
            routers: "routers"
        }),
        onRoutes() {
            return this.$route.path;
        },
        username() {
            let username = this.$store.state.admin.userName;
            return !username ? this.name : username;
        },
        isCollapse() {
            return this.$store.state.app.sidebar.opened;
        }
    },
    mounted() {},
    methods: {
        initWebSocket: function() {
            //如果未连接则进行连接
            if (this.lockReconnect == false) {
                this.websock = new WebSocket("ws://192.168.1.195:80/websocket");
                this.websock.onopen = this.websocketonopen;
                this.websock.onerror = this.websocketonerror;
                this.websock.onmessage = this.websocketonmessage;
                this.websock.onclose = this.websocketclose;
            }
        },
        //心跳检测
        websocketsend() {
            this.reconnectCount = 5;
            console.log("发送心跳");
            let that = this;
            this.a = setInterval(function() {
                if (that.reconnectCount > 0) {
                    console.log("发送心跳剩余次数：" + that.reconnectCount);
                    that.websock.send("heartbeat");
                    that.reconnectCount = that.reconnectCount - 1;
                } else {
                    that.websocketclose();
                    clearInterval(this.a);
                }
            }, 5000);
        },
        //重新连接
        reconnect() {
            let that = this;
            if (this.websock.readyState == 3) {
                setTimeout(function() {
                    that.initWebSocket();
                }, 2000);
            }
        },
        //连接成功
        websocketonopen: function() {
            this.lockReconnect = true;
            //心跳检测
            this.websocketsend();
        },
        //连接错误
        websocketonerror: function() {},
        //接收到消息
        websocketonmessage: function(e) {
            if (e.data == "heartbeatsuccess") {
                //心跳正常
                console.log("心跳正常");
            } else {
                this.$notify({
                    title: "提示",
                    message: "这是一条不会自动关闭的消息",
                    duration: 0
                });
                this.msg = e.data;
            }
            this.reconnectCount = 5;
        },
        //连接关闭
        websocketclose: function(e) {
            console.log("连接关闭" + JSON.stringify(e));
            this.lockReconnect = false;
            //关闭心跳检测
            clearInterval(this.a);
            //重新连接
            this.reconnect();
        },
        toggleSideBar() {
            this.$store.dispatch("ToggleSideBar");
        },
        showSideBar() {
            this.$store.dispatch("ShowSideBar");
        },
        getBreadcrumb() {
            let matched = this.$route.matched.filter(item => item.name);
            const first = matched[0];
            if (first && (first.name !== "首页" || first.path !== "")) {
                matched = [{ name: "首页", path: "/" }].concat(matched);
            }
            this.levelList = matched;
        },
        loginOut() {
            this.$confirm("此操作将退出系统, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$store.dispatch("loginOut").then(() => {
                        location.reload(); // 为了重新实例化vue-router对象 避免bug
                    });
                })
                .catch(() => {});
        },
        // 显示修改密码界面
        handlePassword() {
            this.passwordFormVisible = true;
            this.passwordFormData = {
                oldPassword: "",
                newPassword: "",
                checkNewPassword: ""
            };
        },
        addSubmit(formName) {
            this.$refs[formName].validate(valid => {
                if (valid) {
                    this.passwordLoading = true;
                    let data = Object.assign({}, this.passwordFormData);
                    data.adminId = getAdminId();
                    password(data)
                        .then(res => {
                            this.passwordLoading = false;
                            if (res.code) {
                                this.$message({
                                    message: res.message,
                                    type: "error"
                                });
                            } else {
                                this.$message({
                                    message: "修改成功",
                                    type: "success"
                                });
                                // 刷新表单
                                this.$refs["passwordFormData"].resetFields();
                                this.passwordFormVisible = false;
                                this.$store.dispatch("loginOut").then(() => {
                                    location.reload(); // 为了重新实例化vue-router对象 避免bug
                                });
                            }
                        })
                        .catch(() => {
                            this.$message({
                                type: "error",
                                message: "操作失败"
                            });
                        });
                }
            });
        }
    },
    created() {
        this.getBreadcrumb();
        //判断浏览器是否支持socket，如果不支持socket就使用轮询
        // if ("WebSocket" in window) {
        //     //支持，使用websocket
        //     this.initWebSocket();
        // } else {
        //     //不支持，使用轮询来监听新消息提醒
        //     this.getList();
        // }
    },
    destroyed: function() {
        //this.websocketclose();
    },
    watch: {
        $route() {
            this.getBreadcrumb();
        }
    }
};
</script>

<style>
/* 可以设置不同的进入和离开动画 */
/* 设置持续时间和动画函数 */
.slide-fade-enter-active {
    transition: all 1.6s ease;
}
.slide-fade-leave-active {
    transition: all 0.18s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-fade-enter, .slide-fade-leave-to
/* .slide-fade-leave-active for below version 2.1.8 */ {
    transform: translateX(14px);
    opacity: 0;
}
</style>

<style type="text/scss" lang="scss">
@import "../../styles/mixin";
.screenfull {
    position: relative;
    right: 25%;
    cursor: pointer;
}
.el-header {
    /*background-color: #eef1f6;*/
    color: #333;
    padding: 0 10px;
}

.el-aside {
    color: #333;
}

.el-main {
    position: relative;
    //padding: 0 10px 10px;
    background-color: #fff;
    // margin-left: 7px;
    // margin-top: 5px;
}
.main-mask {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 200%;
    z-index: 100;
    background-color: #000;
    opacity: 0.3;
}

.move-enter-active,
.move-leave-active {
    transition: opacity 0.5s;
}

.move-enter,
.move-leave {
    opacity: 0;
}

/*fade-transform*/
.fade-transform-leave-active,
.fade-transform-enter-active {
    transition: all 0.4s;
}
.fade-transform-enter {
    opacity: 0;
    transform: translateX(-20px);
}
.fade-transform-leave-to {
    opacity: 0;
    transform: translateX(20px);
}

.wrapper {
    height: 100%;
    overflow-y: hidden;
    background-color: #f5f7f9;
}

.container-box {
    height: 100%;
    transition: -webkit-transform 0.3s ease-in-out;
    transition: transform 0.3s ease-in-out, -webkit-transform 0.3s ease-in-out;
    overflow-y: auto;
}

.container-left {
    // background-color: #545c64;
    background-color: #304156;
    transition: 0.5s ease-in-out;
    -moz-transition: 0.5s ease-in-out; /* Firefox 4 */
    -webkit-transition: 0.5s ease-in-out; /* Safari 和 Chrome */
    -o-transition: 0.5s ease-in-out; /* Opera */
    height: 100%;
    width: 60px;
    min-width: 60px;
}
.container-left-wrap {
    overflow-x: hidden !important;
}

.container-left .svg-icon {
    font-size: 20px;
    margin-right: 5px;
}

.container-left:not(.slide-hide) {
    width: 180px;
    min-width: 180px;
}

.container-left.slide-hide {
    .logo img {
        display: none;
    }
    .logo span {
        display: inline-block;
    }
}

.menu {
    width: 100% !important;
}

/*.menu::-webkit-scrollbar{*/
/*display: none;*/
/*}*/

.header {
    position: relative;
    text-align: left;
    font-size: 15px;
    line-height: $header-height;
    border-bottom: 1px solid #d8dce5;
    background-color: #fff;
    font-family: "MicrosoftYaHei" !important;
}

.header .el-dropdown {
    @include fxied-center;
}

.logo {
    display: block;
    width: 100%;
    line-height: 80px;
    text-align: center;
    color: #fff;
    transition: display 0.7s ease-in-out;
    img {
        vertical-align: middle;
        width: 80%;
    }
    span {
        display: none;
    }
}

.header-left {
    display: inline-block;
    height: $header-height;
    overflow: hidden;
    position: absolute;
}

.header-tabs-box {
    display: inline-block;
    height: $header-height;
    width: 75%;
    max-width: 75%;
    overflow: hidden;
    margin-left: 25px;
}

.header-right {
    position: absolute;
    top: 0;
    right: 5px;
    display: inline-block;
    width: 80px;
    height: 100%;
    margin-left: 10px;
    .el-dropdown {
        display: flex;
        cursor: pointer;
    }
    span {
        display: inline-block;
        margin-right: 20px;
        @include text-overflow;
    }
    i {
        line-height: $header-height;
        margin-top: 2px;
    }
}

.app-levelbar {
    margin-left: 20px;
    line-height: 58px;
}

.app-levelbar.el-breadcrumb {
    display: inline-block;
    font-size: 14px;
    line-height: 58px;
    margin-left: 10px;
}

/*宽屏时出现*/
.slide-toggle {
    display: block;
    padding: 0px 5px;
    height: $header-height;
    line-height: 56px;
    text-align: center;
    cursor: pointer;
}

.slide-toggle span {
    display: inline-block;
    width: 2px;
    height: 12px;
    background-color: #000000;
    margin-left: 5px;
}
.slide-toggle-open span {
    display: block;
    margin-left: 4px;
    margin-top: 4px;
    width: 18px;
    height: 2px;
}
.slide-toggle-open {
    padding-top: 17px;
}

@media screen and (min-width: 768px) {
    .main-mask {
        display: none;
    }
}

/*.slide-hide {*/
/*width: 64px !important;*/
/*}*/

@media screen and (max-width: 768px) {
    .slide-hide.slide-in-left {
        -webkit-transform: translate3d(64px, 0, 0) !important;
        transform: translate3d(64px, 0, 0) !important;
    }
    .container-left.slide-hide.slide-in-left {
        left: -64px;
        width: 64px;
    }
    .container-left.slide-in-left {
        left: 0;
    }
    .container-left {
        transition: left 0.23s ease-in-out;
        -moz-transition: left 0.23s ease-in-out; /* Firefox 4 */
        -webkit-transition: left 0.23s ease-in-out; /* Safari 和 Chrome */
        -o-transition: left 0.23s ease-in-out; /* Opera */
        margin-right: 0;
        opacity: 1;
        position: absolute !important;
        top: 0;
        left: -215px;
        z-index: 10;
        padding: 0;
    }

    .container-box.slide-in-left {
        min-width: 0;
        opacity: 1;
        -webkit-transform: translate3d(180px, 0, 0);
        transform: translate3d(180px, 0, 0);
        overflow: initial;
    }
    .header-toggle {
        // display: inline-block !important;
        // vertical-align: middle;
        // /*background-color: #26a2ff;*/
        // border-radius: 4px;
        // //margin: 20px 0;
        // outline: none;
        // width: 15px;
        // z-index: 10;

        display: block !important;
        padding: 0px 5px;
        height: $header-height;
        line-height: 56px;
        text-align: center;
        cursor: pointer;
    }

    .header-toggle span {
        display: block;
        width: 100%;
        height: 4px;
        margin: 5px auto;
        background-color: #000000;
    }

    // .header-toggle svg {
    //     display: block;
    // }

    .logo {
        display: none;
    }

    .slide-toggle {
        display: none;
    }

    .app-levelbar {
        margin-left: 20px;
        line-height: 62px;
    }

    .app-levelbar.el-breadcrumb {
        display: inline-block;
        font-size: 14px;
        line-height: 62px;
        margin-left: 10px;
    }
    .item {
        margin-top: 10px;
        margin-right: 40px;
    }
}
</style>
