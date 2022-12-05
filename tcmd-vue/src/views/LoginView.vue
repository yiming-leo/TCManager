<template>
  <div class="login">
    <a-empty
        id="empty"
        image="https://gw.alipayobjects.com/mdn/miniapp_social/afts/img/A*pevERLJC9v0AAAAAAAAAAABjAQAAAQ/original"
        :image-style="{
      height: '60px',
    }"
    >
      <br>
      <span slot="description"> 您尚未登录系统，请在页头右上角处登录 </span>
    </a-empty>
  </div>
</template>
<script>
export default {
  name: 'Login',
  props: {
    msg: String
  },
  data() {
    return {

    };
  },
  methods: {

  },
}
</script>
<style lang="less" scoped>
.ant-empty {
  font-size: 25px;
  line-height: 200px;
}
.ant-empty-image img {
  height: 200%;
}
.empty{
  position: center;
  vertical-align: center;
}
</style>
