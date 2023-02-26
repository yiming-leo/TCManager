<template>
  <div class="ship_his">
    <a-row type="flex">
      <a-col :span="2" :order="5">
        <a-space>
          <a-button @click="shipHisExcel" type="primary">导出报文历史表格</a-button>
        </a-space>
      </a-col>
    </a-row>
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
    </a-table>
  </div>
</template>
<script>
import Axios from "axios";

export default {
  components: {},
  data() {
    return {
      tableData: [],
      searchText: '',
      searchInput: null,
      searchedColumn: '',
      columns: [
        {
          title: '序列号',
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
      ],
    };
  },
  created() {
    this.init();
  },
  methods: {
    async init() {
      //将所有已分配药厂的处方的历史记录，进行查询
      const {data: res} = await Axios.get('http://49.235.113.96:8085/ship_info/his/all')
      this.tableData = res.data
      for (let i = 0; i < this.tableData.length; i++) {
        this.tableData[i].transactionDate += (" " + this.tableData[i].transactionTime)
      }
    },
    //导出presinfo医生处方的excel数据报表
    async shipHisExcel() {
      //发送请求
      await Axios.request({
        method: 'POST',
        url: 'http://49.235.113.96:8085/poi/get/shiphis_excel',
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Headers": "*",
          "Access-Control-Allow-Methods": "*",
          "Access-Control-Allow-Credentials": "true",
          "Access-Control-Max-Age": "3600",
          "Cache-Control": "no-cache",
          "Content-Type": "application/octet-stream",
        },
        responseType: 'blob', // 声明返回blob格式
      }).then(res => {
        const blob = new Blob([res.data])  // res.data 为接口返回的数据，依实例而行
        const url = URL.createObjectURL(blob)  // 生成一个 Blob URL
        const fileName = 'ShipHis.xlsx'  // 文件名+后缀
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
