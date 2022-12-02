<template>
  <div id="app">
    <a-layout id="components-layout-demo-fixed" style="min-height: 100vh">
      <a-layout-sider v-model="collapsed" collapsible>
        <div class="logo"/>
        <a-menu theme="dark" mode="inline" :default-selected-keys="['1']">
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
            <a-icon type="file"/>
            <span class="nav-text">日志信息</span>
            <router-link to="/log"></router-link>
          </a-menu-item>
          <a-menu-item key="6">
            <a-icon type="schedule"/>
            <span class="nav-text">定时任务</span>
            <router-link to="/schedule"></router-link>
          </a-menu-item>
          <a-menu-item key="7">
            <a-icon type="medicine-box"/>
            <span class="nav-text">药厂数据</span>
            <router-link to="/factory"></router-link>
          </a-menu-item>
          <a-menu-item key="8">
            <a-icon type="setting"/>
            <span class="nav-text">个人设置</span>
            <router-link to="/setting"></router-link>
          </a-menu-item>
        </a-menu>
      </a-layout-sider>
      <a-layout>
        <a-layout-header :style="{ background: '#fff', padding: 0 }">
          <SearchBar></SearchBar>
        </a-layout-header>
        <a-layout-content :style="{ margin: '0 16px 0', overflow: 'initial' }">
          <br>
          <router-view/>
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
      collapsed: false,
    };
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
  height: 32px;
  background: #ffffff; //avatar
  margin: 16px;

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
