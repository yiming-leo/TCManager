<template>
  <div class="schedule">
    <template>
      <a-form-model :model="formData" :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-form-model-item label="模式选择">
          <a-radio-group default-value="1" button-style="solid" v-model="formData.model" :disabled="disabled">
            <a-radio-button value="1">
              输入Cron表达式以开启定时任务
            </a-radio-button>
            <a-radio-button value="2">
              填写列表以开启定时任务
            </a-radio-button>
          </a-radio-group>
        </a-form-model-item>
        <div v-if="this.formData.model==='1'">
          <a-form-model-item label="cron 表达式">
            <a-input default-value="*/10 * * * * *" v-model="formData.cron"
                     placeholder="*/10 * * * * *" :disabled="disabled"/>
          </a-form-model-item>
        </div>
        <div v-else-if="this.formData.model!=='1'">
          <a-form-model-item label="时间间隔" v-model="formData.timeString">
            每隔&nbsp;&nbsp;
            <a-time-picker :default-open-value="moment('00:10:00', 'HH:mm:ss')" @change="onChange"/>
            &nbsp;&nbsp;
            刷新一次处方信息
          </a-form-model-item>
        </div>
        <div>
          <br>
          <a-button :type="buttonType" @click="onToggle(formData.cron,formData.timeString)">
            {{ scheduleStatus }}
          </a-button>
        </div>
      </a-form-model>
    </template>
  </div>
</template>
<script>
import moment from 'moment';
import Axios from "axios";

export default {
  data() {
    return {
      disabled: true,
      labelCol: {span: 4},
      wrapperCol: {span: 14},
      scheduleStatus: '定时任务: 开启',
      formData: {
        cron: '',
        model: "1",
        date1: undefined,
        delivery: false,
        type: [],
        resource: '',
        desc: '',
        timeString: '',
      },
      buttonType: 'primary',
    };
  },
  computed: {},
  created() {

  },
  methods: {
    //发送设置的定时任务
    async startSchedule(cron, timeString) {
      //解构参数
      console.log(cron)
      let expression = cron
      //组装参数
      let requestParam = new FormData()
      requestParam.append('taskId', 1)
      requestParam.append('expression', expression)
      //发送请求
      await Axios.request({
        method: 'POST',
        url: 'http://49.235.113.96:8085/auto_refresh/start',
        data: requestParam,
      }).then(res => {
        //结果集处理
        console.log(res.data)
        if (res.data.status === 200) {
          this.$message.success('定时任务设置成功！');
          this.$notification.success({
            message: '定时任务设置成功！',
            description: '状态码: ' + res.data.status + '  时间戳: ' + res.data.timestamp,
            icon: <a-icon type="check-circle" style="color: #16E09a"/>,
            duration: 0
          });
        } else {
          this.$message.error('定时任务设置失败！');
          this.$notification.error({
            message: '定时任务设置失败！',
            description: '状态码: ' + res.data.status + '  时间戳: ' + res.data.timestamp,
            icon: <a-icon type="close-circle" style="color: #CE1919FF"/>,
            duration: 0
          });
        }
      })
    },
    async stopSchedule() {
      //组装参数
      let requestParam = new FormData()
      requestParam.append('taskId', 1)
      await Axios.request({
        method: 'POST',
        url: 'http://49.235.113.96:8085/auto_refresh/stop',
        data: requestParam,
      })
    },
    onToggle(cron, timeString) {
      this.disabled = !this.disabled;
      if (this.scheduleStatus === '定时任务: 关闭') {
        this.startSchedule(cron, timeString);
        this.$message.success('刚刚开启了定时任务！');
        this.scheduleStatus = '定时任务: 开启'
        this.buttonType = 'primary'
      } else {
        this.stopSchedule()
        this.$message.info('刚刚关闭了定时任务！');
        this.scheduleStatus = '定时任务: 关闭'
        this.buttonType = 'danger'
      }
    },
    //时间选择框
    moment,
    onChange(time, timeString) {
      console.log(time, timeString);
    },
  },
};
</script>
<style scoped>
.code-box-demo .ant-slider {
  margin-bottom: 16px;
}
.ant-form-item-control{
  width: 20px;
}
</style>
