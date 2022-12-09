<template>
  <div class="search-bar">
    <a-page-header
        style="border: 1px solid rgb(235, 237, 240)"
        title="返回"
        sub-title="当前位置"
        @back="() => $router.go(-1)"
    >
      <template slot="extra">
        <transition name="slide-fade">
          <a-space>
            <div v-if="loginTCMA===false">
              <a-form-model layout="inline" :model="formInline" @submit.native.prevent>
                <a-form-model-item>
                  <a-input v-model="formInline.username" placeholder="用户名">
                    <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)"/>
                  </a-input>
                </a-form-model-item>
                <a-form-model-item>
                  <a-input v-model="formInline.password" type="password" placeholder="密码">
                    <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)"/>
                  </a-input>
                </a-form-model-item>
                <a-form-model-item>
                  <a-button
                      type="primary"
                      html-type="submit"
                      :disabled="formInline.username === '' || formInline.password === ''"
                      @click="handleSubmit"
                  >
                    登录
                  </a-button>
                </a-form-model-item>
              </a-form-model>
            </div>
            <div v-else-if="loginTCMA===true">
              <a-form-model layout="inline" :model="formLogout">
                <a-form-model-item>
                  <a-input v-model="this.currentUsername" placeholder="用户名" disabled>
                    <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)"/>
                  </a-input>
                </a-form-model-item>
                <a-form-model-item>
                  <a-button type="danger" @click="logout">登出</a-button>
                </a-form-model-item>
              </a-form-model>
            </div>
            <a-button id="refresh" @mouseover="" @mouseout="" @click="refresh">
              <a-icon type="sync" :spin="syncSpin"/>
              数据同步
            </a-button>
          </a-space>
        </transition>
      </template>
    </a-page-header>
  </div>
</template>

<script>
import PresView from "@/views/PresView.vue";

export default {
  name: "SearchBar",
  data() {
    return {
      setClock: false,
      iconLoading: false,
      loginTCMA: false,
      syncSpin: false,
      currentUsername: '',
      formInline: {
        username: '',
        password: '',
      },
      formLogout: {}
    }
  },
  methods: {
    //登出
    logout() {
      this.loginTCMA = false;
      this.$message.info('登出成功');
      this.iconLoading = false;
      this.formInline.username = '';
      this.$emit("isLogin", this.formInline.username)
    },
    //同步图标旋转控制
    // iconUnSpin() {
    //   this.syncSpin = false;
    // },
    // iconSpin() {
    //   this.syncSpin = true;
    // },
    //同步图标刷新
    refresh() {
      this.setClock = !this.setClock;
      console.log(this.syncSpin)
      this.syncSpin = false;
      let t1;
      if (this.setClock == true) {
        this.syncSpin = true;
        t1 = window.setInterval(function () {
          console.log('5秒钟之后执行了')
          let init = PresView.methods.init()
          console.log(init)
        }, 5000)
      }else {
        this.syncSpin = false;
        window.clearInterval(t1);
        window.setInterval(function () {
          console.log('99999秒钟之后执行了')
          let init = PresView.methods.init()
          console.log(init)
        }, 9999000)
      }
    },
    //账号密码表单登录
    handleSubmit(e) {
      if (this.formInline.username === 'admin' && this.formInline.password === '123456') {
        this.$message.success('登录成功');
        this.loginTCMA = true;
        this.currentUsername = this.formInline.username;
        console.log(this.currentUsername)
        this.formInline.username = ' ';
        this.formInline.password = '';
      } else {
        this.$message.error('用户名或密码错误');
      }
      this.$emit("isLogin", this.formInline.username)
    },
    onSearch(value) {
      console.log(value);
    },
  },
}
</script>

<style lang="less" scoped>
//.ant-btn:hover, .ant-btn:focus {
//  color: #42b983;
//  border-color: #42b983;
//}
.slide-fade-enter-active {
  transition: all .3s ease;
}

.slide-fade-leave-active {
  transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}

.slide-fade-enter, .slide-fade-leave-to
  /* .slide-fade-leave-active for below version 2.1.8 */ {
  transform: translateX(10px);
  opacity: 0;
}

.search-bar {
  align-items: center;
  vertical-align: center;
  position: center;
}

.loginForm {
  align-items: center;
  vertical-align: center;
  position: relative;
  left: auto;
}

.refresh {
  align-items: center;
  vertical-align: center;
  position: center;
}
</style>
