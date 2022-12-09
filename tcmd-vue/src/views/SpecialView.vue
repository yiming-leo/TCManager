<template>
  <div class="special">
    <a-form-model :model="formData">
      <a-form-model-item label="撤销与删除操作">
        <a-input-group>
          <a-select default-value="1" style="width: 20%" v-model="formData.select">
            <a-select-option value="1">
              从医生处方中撤销已分配的处方
            </a-select-option>
            <a-select-option value="2">
              从药厂报文中撤销已发送的报文
            </a-select-option>
            <a-select-option value="3">
              从处方历史永久删除审核通过的处方
            </a-select-option>
            <a-select-option value="4">
              从报文历史永久删除已发送的报文
            </a-select-option>
          </a-select>
          <a-input-search v-model="formData.id" style="width: 20%" default-value="" placeholder="数据id" enter-button
                          @search="onSearch(formData.select,formData.id)">
          </a-input-search>
        </a-input-group>
      </a-form-model-item>
    </a-form-model>
  </div>
</template>
<script>
import Axios from "axios";

export default {
  data() {
    return {
      name: '处方',
      selectModel: 1,
      dataId: '',
      formData: {
        select: '请选择需要操作的数据类型',
        id: '',
      }
    }
  },
  methods: {
    async onSearch(option, dataId) {
      console.log(option)
      console.log(dataId)
      if (option == 1) {
        //组装参数
        let requestParam = new FormData()
        requestParam.append('id', dataId)
        //发送请求
        await Axios.request({
          method: 'PUT',
          url: 'http://49.235.113.96:8085/pres_info/rpt',
          data: requestParam,
        }).then(res => {
          //结果集处理
          console.log("res.data: " + res.data)
          if (res.data.status == 200) {
            this.$message.success('处方撤销成功！');
            this.$notification.success({
              message: '处方撤销成功！',
              description: '操作行ID: ' + dataId + '  状态码: ' + res.data.status + '  时间戳: ' + res.data.timestamp,
              icon: <a-icon type="check-circle" style="color: #16E09a"/>,
              duration: 0
            });
          } else {
            this.$message.error('处方撤销失败！');
            this.$notification.error({
              message: '处方撤销失败！',
              description: '操作行ID: ' + dataId + '  状态码: ' + res.data.status + '  时间戳: ' + res.data.timestamp,
              icon: <a-icon type="close-circle" style="color: #CE1919FF"/>,
              duration: 0
            });
          }
        })
      } else if (option == 2) {
        //组装参数
        let requestParam = new FormData()
        requestParam.append('id', dataId)
        //发送请求
        await Axios.request({
          method: 'PUT',
          url: 'http://49.235.113.96:8085/ship_info/rpt',
          data: requestParam,
        }).then(res => {
          //结果集处理
          console.log("res.data: " + res.data)
          if (res.data.status == 200) {
            this.$message.success('报文撤销成功！');
            this.$notification.success({
              message: '报文撤销成功！',
              description: '操作行ID: ' + dataId + '  状态码: ' + res.data.status + '  时间戳: ' + res.data.timestamp,
              icon: <a-icon type="check-circle" style="color: #16E09a"/>,
              duration: 0
            });
          } else {
            this.$message.error('报文撤销失败！');
            this.$notification.error({
              message: '报文撤销失败！',
              description: '操作行ID: ' + dataId + '  状态码: ' + res.data.status + '  时间戳: ' + res.data.timestamp,
              icon: <a-icon type="close-circle" style="color: #CE1919FF"/>,
              duration: 0
            });
          }
        })
      }
    }
  }
}
</script>
<style>

</style>
