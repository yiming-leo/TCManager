<template>
  <div class="search-bar">
    <a-page-header
        style="border: 1px solid rgb(235, 237, 240)"
        :title="currentTitle"
        sub-title="当前位置"
        @back="() => $router.go(-1)"
    >
      <template slot="extra">
        <a-form-model id="loginForm" layout="inline" :model="formInline" @submit="handleSubmit" @submit.native.prevent>
          <a-form-model-item>
            <a-input v-model="formInline.user" placeholder="账号">
              <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)"/>
            </a-input>
          </a-form-model-item>
          <a-form-model-item>
            <a-input v-model="formInline.password" type="password" placeholder="密码">
              <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)"/>
            </a-input>
          </a-form-model-item>
          <a-form-model-item>
            <a-space>
              <a-button
                  type="primary"
                  html-type="submit"
                  :disabled="formInline.user === '' || formInline.password === ''"
              >
                登录
              </a-button>
              <a-button id="refresh" @mouseover="iconSpin" @mouseout="iconUnSpin" @click="refresh">
                <a-icon type="sync" :spin="syncSpin"/>
                数据同步
              </a-button>
            </a-space>
          </a-form-model-item>
        </a-form-model>
      </template>
    </a-page-header>
  </div>
</template>

<script>
export default {
  name: "SearchBar",
  props: [
    // currentTitle,
  ],
  data() {
    return {
      syncSpin: false,
      currentTitle: '',
      formInline: {
        user: '',
        password: '',
      },
    }
  },
  methods: {
    //同步图标旋转控制
    iconUnSpin(){
      this.syncSpin = false;
    },
    iconSpin(){
      this.syncSpin = true;
    },
    //同步图标刷新
    refresh(){
    },
    //账号密码表单
    handleSubmit(e) {
      console.log(this.formInline);
    },
    onSearch(value) {
      console.log(value);
    },
  },
}
</script>

<style lang="less" scoped>
.ant-btn:hover, .ant-btn:focus{
  color: #42b983;
  border-color: #42b983;
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
