<template>
  <div class="ship_info">
    <div>
      <a-row type="flex">
        <a-col :span="10" :order="1">
          <a-space>
            开方日期查询
            <a-range-picker :locale="locale" @change="dateBetweenSelect"/>
          </a-space>
        </a-col>
        <a-col :span="10" :order="2">
          <a-space>
            开方时间查询
            <a-time-picker style=" width: 100px; text-align: center" placeholder="起始时间" @change="timeStartSelect"/>
            ~
            <a-time-picker style="width: 100px; text-align: center; " placeholder="终止时间" @change="timeEndSelect"/>
            <a-button @click="timeBetweenSelect(timeSt,timeEd)">查询</a-button>
          </a-space>
        </a-col>
        <a-col :span="2" :order="5">
          <a-space>
            <a-button @click="shipInfoExcel" type="primary">导出报文表格</a-button>
          </a-space>
        </a-col>
      </a-row>
    </div>
    <br>
    <a-table :data-source="tableData" :columns="columns">
      <div
          slot="filterDropdown"
          slot-scope="{ setSelectedKeys, selectedKeys, confirm, clearFilters, column }"
          style="padding: 8px"
      >
        <a-input
            v-ant-ref="c => (searchInput = c)"
            :placeholder="`Search ${column.dataIndex}`"
            :value="selectedKeys[0]"
            style="width: 188px; margin-bottom: 8px; display: block;"
            @change="e => setSelectedKeys(e.target.value ? [e.target.value] : [])"
            @pressEnter="() => handleSearch(selectedKeys, confirm, column.dataIndex)"
        />
        <a-button
            type="primary"
            icon="search"
            size="small"
            style="width: 90px; margin-right: 8px"
            @click="() => handleSearch(selectedKeys, confirm, column.dataIndex)"
        >
          Search
        </a-button>
        <a-button size="small" style="width: 90px" @click="() => handleReset(clearFilters)">
          Reset
        </a-button>
      </div>
      <a-icon
          slot="filterIcon"
          slot-scope="filtered"
          type="search"
          :style="{ color: filtered ? '#108ee9' : undefined }"
      />
      <template slot="customRender" slot-scope="text, record, index, column">
      <span v-if="searchText && searchedColumn === column.dataIndex">
        <template
            v-for="(fragment, i) in text
            .toString()
            .split(new RegExp(`(?<=${searchText})|(?=${searchText})`, 'i'))"
        >
          <mark
              v-if="fragment.toLowerCase() === searchText.toLowerCase()"
              :key="i"
              class="highlight"
          >{{ fragment }}</mark>
          <template v-else>{{ fragment }}</template>
        </template>
      </span>
        <template v-else>
          {{ text }}
        </template>
      </template>
      <template slot="operation" slot-scope="text, record, column, index">
        <a-button type="primary" @click="showModal(record.id)">发送报文</a-button>
      </template>
    </a-table>
    <a-modal
        title="请再次确认发送信息"
        :visible="confirmVisible"
        :confirm-loading="confirmLoading"
        @ok="handleOk"
        @cancel="handleCancel"
        cancelText="取消"
        okText="确定"
    >
      <p>{{ ModalText }}</p>
    </a-modal>
  </div>
</template>
<script>
import Axios from "axios";
import locale from 'ant-design-vue/es/date-picker/locale/zh_CN';

export default {
  components: {},
  data() {
    return {
      locale,

      ModalText: '确认将此报文发送至药厂？',
      confirmVisible: false,
      confirmLoading: false,

      checkId: 0,
      tableData: [],

      dateSt: "",
      dateEd: "",
      timeSt: "",
      timeEd: "",

      searchText: '',
      searchInput: null,
      searchedColumn: '',
      columns: [
        {
          title: '序列号',
          // dataIndex: this.data.id,
          dataIndex: 'id',
          key: 'id',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.id
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: '开方时间',
          dataIndex: 'transactionDate',
          key: 'transactionDate',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.date
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: '收件人姓名',
          dataIndex: 'recipientName',
          key: 'recipientName',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.recipientName
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: '收件人地址',
          dataIndex: 'recipientAddress',
          key: 'recipientAddress',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.recipientAddress
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              });
            }
          },
        },
        {
          title: '收件人电话',
          dataIndex: 'recipientTelephone',
          key: 'recipientTelephone',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.recipientTelephone
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              });
            }
          },
        },
        {
          title: '邮政编码',
          dataIndex: 'postalCode',
          key: 'postalCode',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.postalCode
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              });
            }
          },
        },
        {
          title: '处方号',
          dataIndex: 'prescriptionNo',
          key: 'prescriptionNo',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.prescriptionNo
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              });
            }
          },
        },
        {
          title: '医院编号',
          dataIndex: 'hospitalNo',
          key: 'hospitalNo',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.hospitalNo
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              });
            }
          },
        },
        {
          title: '药厂编号',
          dataIndex: 'pharmaFactoryNo',
          key: 'pharmaFactoryNo',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.pharmaFactoryNo
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              });
            }
          },
        },
        {
          title: '配送要求',
          dataIndex: 'deliveryRequire',
          key: 'deliveryRequire',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.deliveryRequire
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              });
            }
          },
        },
        {
          title: '门诊号',
          dataIndex: 'outpatientNo',
          key: 'outpatientNo',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.outpatientNo
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              });
            }
          },
        },
        {
          title: '病人姓名',
          dataIndex: 'patientName',
          key: 'patientName',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.patientName
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              });
            }
          },
        },
        {
          title: '备注',
          dataIndex: 'infoRemarks',
          key: 'infoRemarks',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.infoRemarks
                  .toString()
                  .toLowerCase()
                  .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              });
            }
          },
        },
        {
          title: '操作',
          dataIndex: 'operation',
          key: 'operation',
          scopedSlots: {customRender: 'operation'},
        },
      ],
    };
  },
  created() {
    this.init();
  },
  methods: {
    //区间查询交易时间按钮
    async timeStartSelect(time, timeString) {
      this.timeSt = timeString
      if (this.timeSt == null || this.timeSt === "") {
        await this.init()
        return null
      }
    },
    async timeEndSelect(time, timeString) {
      this.timeEd = timeString
      if (this.timeEd == null || this.timeEd === "") {
        await this.init()
        return null
      }
    },
    async timeBetweenSelect(st, ed) {
      if (st == null || ed == null || st === "" || ed === "") {
        await this.init()
        return null
      }
      //组装参数
      let requestParam = new FormData()
      requestParam.append("timeSt", st)
      requestParam.append("timeEd", ed)
      //发送请求
      await Axios.request({
        method: 'POST',
        url: 'http://49.235.113.96:8085/ship_info/get/by_time_bt',
        data: requestParam,
      }).then(res => {
        this.tableData = res.data.data
        for (let i = 0; i < this.tableData.length; i++) {
          this.tableData[i].transactionDate += (" " + this.tableData[i].transactionTime)
        }
        console.log(res.data)
        //结果集处理
        if (res.data.status != 200) {
          this.$message.error('时间区间查询失败！');
        }
      })
    },
    //区间查询交易日期按钮
    async dateBetweenSelect(date, dateString) {
      this.dateSt = dateString[0]
      this.dateEd = dateString[1]
      if (this.dateSt == null || this.dateEd == null || this.dateSt === "" || this.dateEd === "") {
        await this.init()
        return null
      }
      //组装参数
      let requestParam = new FormData()
      requestParam.append("dateSt", this.dateSt)
      requestParam.append("dateEd", this.dateEd)
      //发送请求
      await Axios.request({
        method: 'POST',
        url: 'http://49.235.113.96:8085/ship_info/get/by_date_bt',
        data: requestParam,
      }).then(res => {
        this.tableData = res.data.data
        for (let i = 0; i < this.tableData.length; i++) {
          this.tableData[i].transactionDate += (" " + this.tableData[i].transactionTime)
        }
        console.log(res.status)
        //结果集处理
        if (res.status != 200) {
          this.$message.error('日期区间查询失败！');
        }
      })
    },
    //提交表单前的确认框
    showModal(id) {
      this.checkId = id
      this.confirmVisible = true;
    },
    handleOk(e) {
      console.log(this.checkId)
      this.sendShipInfo(this.checkId)
      console.log("yes")
      this.confirmLoading = true;
      setTimeout(() => {
        this.confirmVisible = false;
        this.confirmLoading = false;
      }, 2000);
    },
    handleCancel(e) {
      this.confirmVisible = false;
    },
    //发送报文至药厂
    async sendShipInfo(id) {
      //组装参数
      let requestParam = new FormData()
      requestParam.append('id', id)
      //发送请求
      await Axios.request({
        method: 'POST',
        url: 'http://49.235.113.96:8085/ship_info/snd/normal',
        data: requestParam,
      }).then(res => {
        //结果集处理
        console.log(res.data)
        if (res.data.status === 200) {
          this.$message.success('报文发送成功！');
          this.$notification.success({
            message: '报文发送成功！',
            description: '操作行ID: ' + id + '  状态码: ' + res.data.status + '  时间戳: ' + res.data.timestamp,
            icon: <a-icon type="check-circle" style="color: #16E09a"/>,
            duration: 0
          });
        } else {
          this.$message.error('报文发送失败！');
          this.$notification.error({
            message: '报文发送失败！',
            description: '操作行ID: ' + id + '  状态码: ' + res.data.status + '  时间戳: ' + res.data.timestamp,
            icon: <a-icon type="close-circle" style="color: #CE1919FF"/>,
            duration: 0
          });
        }
      })
      //重新刷新表格
      await this.init()
    },
    //导出presinfo医生处方的excel数据报表
    async shipInfoExcel() {
      //发送请求
      await Axios.request({
        method: 'POST',
        url: 'http://49.235.113.96:8085/poi/get/shipinfo_excel',
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/octet-stream",
        },
        responseType: 'blob', // 声明返回blob格式
      }).then(res => {
        const blob = new Blob([res.data])  // res.data 为接口返回的数据，依实例而行
        const url = URL.createObjectURL(blob)  // 生成一个 Blob URL
        const fileName = 'ShipInfo.xlsx'  // 文件名+后缀
        const listNode = document.createElement("a")
        listNode.download = fileName
        listNode.style.display = "none"
        listNode.href = url // 文件流生成的url
        document.body.appendChild(listNode)
        listNode.click()  // 模拟在按钮上实现一次鼠标点击
        url.revokeObjectURL(listNode.href)  // 释放 URL 对象
        document.body.removeChild(listNode)  // 移除 a 标签

        //结果集处理
        this.visible = false;
        console.log(res.data)
        if (res.data != null) {
          this.$message.success('导出医生处方成功！');
          this.$notification.success({
            message: '导出医生处方成功！',
            icon: <a-icon type="check-circle" style="color: #16E09a"/>,
            duration: 0
          });
        } else {
          this.$message.error('导出医生处方失败！');
          this.$notification.error({
            message: '导出医生处方失败！',
            icon: <a-icon type="close-circle" style="color: #CE1919FF"/>,
            duration: 0
          });
        }
      })
    },
    //将所有未配药厂的报文，进行查询
    async init() {
      await Axios.request({
        method: 'GET',
        url: 'http://49.235.113.96:8085/ship_info/get/all',
      }).then(res => {
        this.tableData = res.data.data
        console.log(this.tableData)
        for (let i = 0; i < this.tableData.length; i++) {
          this.tableData[i].transactionDate += (" " + this.tableData[i].transactionTime)
        }
      })
    },
    handleSearch(selectedKeys, confirm, dataIndex) {
      confirm();
      this.searchText = selectedKeys[0];
      this.searchedColumn = dataIndex;
    },

    handleReset(clearFilters) {
      clearFilters();
      this.searchText = '';
    },
  },
}
</script>
<style scoped>
.highlight {
  background-color: rgb(255, 192, 105);
  padding: 0px;
}
</style>
