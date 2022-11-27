<template>
  <div id="app">
    <a-layout id="components-layout-demo-fixed-sider">
      <a-layout-sider :style="{ overflow: 'auto', height: '100vh', position: 'fixed', left: 0 }">
        <div class="logo"/>
        <a-menu theme="dark" mode="inline" :default-selected-keys="['4']">
          <a-menu-item key="1">
            <a-icon type="user"/>
            <router-link to="/">Home</router-link>
          </a-menu-item>
          <a-menu-item key="2">
            <a-icon type="video-camera"/>
            <span class="nav-text">nav 2</span>
            <router-link to="/about">About</router-link>
          </a-menu-item>
          <a-menu-item key="3">
            <a-icon type="upload"/>
            <span class="nav-text">nav 3</span>
            <router-link to="/pres_info">处方信息</router-link>
          </a-menu-item>
          <a-menu-item key="4">
            <a-icon type="bar-chart"/>
            <span class="nav-text">nav 4</span>
            <router-link to="/ship_info">报文信息</router-link>
          </a-menu-item>
          <a-menu-item key="5">
            <a-icon type="cloud-o"/>
            <span class="nav-text">nav 5</span>
            <router-link to="/history">历史记录</router-link>
          </a-menu-item>
          <a-menu-item key="6">
            <a-icon type="appstore-o"/>
            <span class="nav-text">nav 6</span>
            <router-link to="/log">日志记录</router-link>
          </a-menu-item>
        </a-menu>
      </a-layout-sider>
      <a-layout :style="{ marginLeft: '200px' }">
        <a-layout-header :style="{ background: '#fff', padding: 0 }"/>
        <a-layout-content :style="{ margin: '24px 16px 0', overflow: 'initial' }">
        </a-layout-content>
        <a-layout-footer :style="{ textAlign: 'center' }">
          Ant Design ©2018 Created by Ant UED
        </a-layout-footer>
      </a-layout>
    </a-layout>
    <router-view/>
  </div>
</template>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#components-layout-demo-fixed-sider .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}

#a-layout-sider{
  background-color: #42b983;
}
</style>
