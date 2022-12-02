<template>
  <div class="search-bar">
    <a-input-search placeholder="input search text" enter-button="Search"
                    size="large" style="width: 50vw; vertical-align: center" @search="onSearch" allow-clear/>
  </div>
</template>

<script>
export default {
  name: "SearchBar",
  data() {
    return{
    }
  },
  methods: {
    onSearch(value) {
      console.log(value);
    },
  },
}
</script>

<style lang="less" scoped>
.search-bar {
  align-items: center;
  vertical-align: center;
}
</style>
