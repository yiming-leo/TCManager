<template>
  <div id="app">
    <a-layout id="components-layout-demo-fixed" style="min-height: 100vh">
      <a-layout-sider v-model="collapsed" collapsible theme="light">
        <div class="logo"/>
        <a-affix :offset-top="0">
          <a-menu theme="light" mode="inline" :default-selected-keys="['1']">
            <a-menu-item key="1">
              <a-icon type="bars"/>
              <span class="nav-text">医生处方</span>
              <router-link to="/pres_info"></router-link>
            </a-menu-item>
            <a-menu-item key="2">
              <a-icon type="container"/>
              <span class="nav-text">药厂报文</span>
              <router-link to="/ship_info"></router-link>
            </a-menu-item>
            <a-menu-item key="3">
              <a-icon type="calendar"/>
              <span class="nav-text">处方历史</span>
              <router-link to="/pres_his"></router-link>
            </a-menu-item>
            <a-menu-item key="4">
              <a-icon type="history"/>
              <span class="nav-text">报文历史</span>
              <router-link to="/ship_his"></router-link>
            </a-menu-item>
            <a-menu-item key="5">
              <a-icon type="schedule"/>
              <span class="nav-text">定时任务</span>
              <router-link to="/schedule"></router-link>
            </a-menu-item>
            <a-menu-item key="6">
              <a-icon type="medicine-box"/>
              <span class="nav-text">药厂数据</span>
              <router-link to="/factory"></router-link>
            </a-menu-item>
            <a-menu-item key="7">
              <a-icon type="tool"/>
              <span class="nav-text">特殊操作</span>
              <router-link to="/special"></router-link>
            </a-menu-item>
            <a-menu-item key="8">
              <a-icon type="pie-chart"/>
              <span class="nav-text">PV与UV</span>
              <router-link to="/pvuv"></router-link>
            </a-menu-item>
            <a-menu-item key="9">
              <a-icon type="file"/>
              <span class="nav-text">日志信息</span>
              <router-link to="/log"></router-link>
            </a-menu-item>
            <a-menu-item key="10" @mouseover="iconSpin" @mouseout="iconUnSpin">
              <a-icon type="setting" :spin="settingSpin"/>
              <span class="nav-text">个人设置</span>
              <router-link to="/setting"></router-link>
            </a-menu-item>
          </a-menu>
        </a-affix>
      </a-layout-sider>
      <a-layout>
        <a-layout-header :style="{ background: '#fff', padding: 0 }">
          <SearchBar></SearchBar>
        </a-layout-header>
        <a-layout-content :style="{ margin: '0 16px 0', overflow: 'initial' }">
          <br>
          <transition name="slide-fade" mode="out-in">
            <router-view v-if="viewVisible"></router-view>
          </transition>
        </a-layout-content>
        <a-layout-footer :style="{ textAlign: 'center' }">
          TCManager ©2022 Created by Calendo
        </a-layout-footer>
      </a-layout>
    </a-layout>
  </div>
</template>
<script>
import {Icon} from 'ant-design-vue';
import SearchBar from "@/components/SearchBar.vue";

const IconFont = Icon.createFromIconfontCN({
  scriptUrl: '//at.alicdn.com/t/font_8d5l8fzk5b87iudi.js',
});
export default {
  components: {
    SearchBar,
    IconFont,
  },
  data() {
    return {
      settingSpin: false,

      viewVisible: true,
      collapsed: false,
    };
  },
  methods: {
    //同步图标旋转控制
    iconUnSpin() {
      this.settingSpin = false;
    },
    iconSpin() {
      this.settingSpin = true;
    },
  },
};
</script>
<style lang="less" scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#components-layout-demo-fixed .logo {
  background-image: url("assets/icon-logo.png");
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
  height: 32px;
  margin: 16px;

}

/* 可以设置不同的进入和离开动画 */
/* 设置持续时间和动画函数 */
.slide-fade-enter-active {
  transition: all 250ms cubic-bezier(1.0, 0.5, 0.8, 1.0);
}

.slide-fade-leave-active {
  transition: all 250ms cubic-bezier(1.0, 0.5, 0.8, 1.0);
}

.slide-fade-leave-to
  /* .slide-fade-leave-active for below version 2.1.8 */ {
  transform: translateX(20px);
  opacity: 0;
}

.slide-fade-enter {
  transform: translateX(-20px);
  opacity: 0;
}

.ant-layout-sider-light .ant-layout-sider-trigger {
  color: #aaaaaa;
  background: #aaaaaa;
  background-color: #aaaaaa;
}

//.ant-layout-sider-children{
//  box-shadow: initial;//shadow
//}x
//.ant-layout-sider-children{
//  background: #FFFFEE;
//}
//.icons-list >>> .anticon {
//  margin-right: 6px;
//  font-size: 24px;
//}

</style>
