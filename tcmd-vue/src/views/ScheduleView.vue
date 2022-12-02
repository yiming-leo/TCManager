<template>
  <div class="schedule">
    <h1>This is an schedule page</h1>
    <div>
      <a-select default-value="lucy" style="width: 120px" @change="handleChange">
        <a-select-option value="jack">
          Jack
        </a-select-option>
        <a-select-option value="lucy">
          Lucy
        </a-select-option>
        <a-select-option value="disabled" disabled>
          Disabled
        </a-select-option>
        <a-select-option value="Yiminghe">
          yiminghe
        </a-select-option>
      </a-select>
    </div>
    <div>
      <a-row>
        <a-col :span="12">
          <a-slider v-model="inputValue1" :min="1" :max="20"/>
        </a-col>
        <a-col :span="4">
          <a-input-number v-model="inputValue1" :min="1" :max="20" style="marginLeft: 16px"/>
        </a-col>
      </a-row>
      <a-row>
        <a-col :span="12">
          <a-slider v-model="inputValue" :min="0" :max="1" :step="0.01"/>
        </a-col>
        <a-col :span="4">
          <a-input-number
              v-model="inputValue"
              :min="0"
              :max="1"
              :step="0.01"
              style="marginLeft: 16px"
          />
        </a-col>
      </a-row>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      inputValue: 0,
      inputValue1: 1,
    };
  },
};
</script>
<style scoped>
.code-box-demo .ant-slider {
  margin-bottom: 16px;
}
</style>
