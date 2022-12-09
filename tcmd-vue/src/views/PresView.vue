<template>
  <div class="pres_info">
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
      <!--      操作区-->
      <template slot="operation" slot-scope="text, record">
        <a-button type="primary" @click="distributeFactory(record.id)">分配药厂</a-button>
      </template>
    </a-table>
    <!--    分配框-->
    <a-drawer
        title="将此处方分配至指定药厂"
        :width="720"
        :visible="visible"
        :body-style="{ paddingBottom: '80px' }"
        @close="onClose"
    >
      <a-form :form="form" layout="vertical" hide-required-mark>
        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-item label="处方ID">
              <a-input id="id"
                       addon-before="UUID"
                       disabled
                       :placeholder="this.currentId"
                       value="this.currentId"
                       v-decorator="[
                  'id',
                  {
                    rules: [
                        { required: true, message: '请输入处方ID！' }
                    ],
                  },
                ]"
              />
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="收件人姓名">
              <a-input id="recipientName"
                       v-decorator="[
                  'recipientName',
                  {
                    rules: [{ required: true, message: '收件人姓名不能为空！' }],
                  },
                ]"
                       placeholder="请填写收件人姓名"
              />
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="收件人地址">
              <a-input id="recipientAddress"
                       v-decorator="[
                  'recipientAddress',
                  {
                    rules: [{ required: true, message: '请填写收件人地址' }],
                  },
                ]"
                       placeholder="请填写收件人地址"
              />
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="收件人电话">
              <a-input id="recipientTelephone"
                       addon-before="+86"
                       v-decorator="[
                  'recipientTelephone',
                  {
                    rules: [
                        { required: true, message: '请填写收件人电话' },
                        { pattern: /^1(3\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\d|9[0-35-9])\d{8}$/, message: '电话格式不正确' },
                    ],
                  },
                ]"
                       placeholder="请填写收件人电话"
              />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-item label="邮政编码">
              <a-input id="postalCode"
                       v-decorator="[
                  'postalCode',
                  {
                    rules: [
                        { required: true, message: '请填写邮政编码' },
                        { pattern: /^[1-9]\d{5}$/, message: '邮政编码格式不正确'}
                    ],
                  },
                ]"
                       placeholder="邮政编码"
              />
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="医院信息">
              <a-select id="hospitalNo"
                        v-decorator="[
                  'hospitalNo',
                  {
                    rules: [{ required: true, message: '请填写医院信息' }],
                  },
                ]"
                        placeholder="请填写医院信息"
              >
                <a-select-option value="0001">浙江省中医院</a-select-option>
                <a-select-option value="0002">浙江省新华医院</a-select-option>
                <a-select-option value="0003">浙江省中山医院</a-select-option>
                <a-select-option value="0004">浙江省西溪医院</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="选择药厂">
              <a-select id="facName"
                        mode="multiple"
                        style="width: 100%"
                        placeholder="请选择至少一个以上的药厂"
                        @change="handleChange"
                        v-decorator="[
                  'facName',
                  {
                    rules: [{ required: true, message: '请选择至少一个以上的药厂' }],
                  },
                ]"
              >
                <a-select-option value="0001">富阳制药</a-select-option>
                <a-select-option value="0002">华东制药</a-select-option>
                <a-select-option value="0003">滁州制药</a-select-option>
                <a-select-option value="0004">台州制药</a-select-option>
                <a-select-option value="0005">处州制药</a-select-option>
                <a-select-option value="0006">湖州制药</a-select-option>
                <a-select-option value="0007">金华制药</a-select-option>
                <a-select-option value="0008">康恩贝制药</a-select-option>
                <a-select-option value="0009">甬州制药</a-select-option>
                <a-select-option value="0010">温州制药</a-select-option>
                <a-select-option value="0011">衢州制药</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="是否煎药（药品类型）">
              <a-select id="decoctMedicine"
                        v-decorator="[
                  'decoctMedicine',
                  {
                    rules: [
                        { required: true, message: '请填写是否煎药' },
                        { min: -99, max: 99, message: '煎药格式不正确'}
                    ],
                  },
                ]"
                        placeholder="请填写是否煎药"
              >
                <a-select-option value="0">煎药</a-select-option>
                <a-select-option value="1">草药</a-select-option>
                <a-select-option value="2">膏方</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="配送要求">
              <a-textarea id="deliveryRequire"
                          v-decorator="[
                  'deliveryRequire',
                  {
                    rules: [{ required: true, message: '请填写配送要求' }],
                  },
                ]"
                          :rows="4"
                          placeholder="请填写配送要求"
              />
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
      <div
          :style="{
          position: 'absolute',
          right: 0,
          bottom: 0,
          width: '100%',
          borderTop: '1px solid #e9e9e9',
          padding: '10px 16px',
          background: '#fff',
          textAlign: 'right',
          zIndex: 1,
        }"
      >
        <a-button :style="{ marginRight: '8px' }" @click="onClose">取消</a-button>
        <a-button type="primary" @click="showModal">分配</a-button>
        <a-modal
            title="请再次确认分配信息"
            :visible="confirmVisible"
            :confirm-loading="confirmLoading"
            cancelText="取消"
            okText="确认"
            @ok="handleOk"
            @cancel="handleCancel"
        >
          <p>{{ ModalText }}</p>
        </a-modal>
      </div>
    </a-drawer>
  </div>
</template>
<script>
import Axios from "axios";

export default {
  components: {},
  data() {
    return {
      ModalText: '确认将此处方分配至指定药厂？',
      confirmVisible: false,
      confirmLoading: false,

      id: 0,
      recipientName: '',
      recipientAddress: '',
      recipientTelephone: '',
      facNumber: 0,
      facName: [],
      postalCode: '',
      hospitalNo: '',
      decoctMedicine: '',
      deliveryRequire: '',

      currentId: 0,
      form: this.$form.createForm(this),
      visible: false,
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
          title: '开方日期',
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
          title: '开方时间',
          dataIndex: 'transactionTime',
          key: 'transactionTime',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.time
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
              }, 0);
            }
          },
        },
        {
          title: '病人年龄',
          dataIndex: 'patientAge',
          key: 'patientAge',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.patientAge
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
          title: '病人性别',
          dataIndex: 'patientGender',
          key: 'patientGender',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.patientGender
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
          title: '医生姓名',
          dataIndex: 'doctorName',
          key: 'doctorName',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.doctorName
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
          title: '医生ID',
          dataIndex: 'doctorId',
          key: 'doctorId',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.doctorID
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
          title: '交易金额（RMB）',
          dataIndex: 'price',
          key: 'price',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.price
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
  //组件初始化时自动执行
  created() {
    this.init();
  },
  methods: {
    //提交表单前的确认框
    showModal() {
      this.confirmVisible = true;
    },
    handleOk(e) {
      this.submitDistributionForm()
      this.confirmLoading = true;
      setTimeout(() => {
        this.confirmVisible = false;
        this.confirmLoading = false;
      }, 2000);
    },
    handleCancel(e) {
      this.confirmVisible = false;
    },
    //提交分配药厂的信息按钮
    async submitDistributionForm() {
      //获取参数
      this.id = this.currentId
      this.recipientName = this.form.getFieldValue('recipientName')
      this.recipientAddress = this.form.getFieldValue('recipientAddress')
      this.recipientTelephone = this.form.getFieldValue('recipientTelephone')
      this.facName = this.form.getFieldValue('facName')
      this.facNumber = this.facName.length
      this.postalCode = this.form.getFieldValue('postalCode')
      this.hospitalNo = this.form.getFieldValue('hospitalNo')
      this.decoctMedicine = this.form.getFieldValue('decoctMedicine')
      this.deliveryRequire = this.form.getFieldValue('deliveryRequire')
      //组装参数
      let dataJson = {
        "id": this.id,
        "recipientName": this.recipientName,
        "recipientAddress": this.recipientAddress,
        "recipientTelephone": this.recipientTelephone,
        "facName": this.facName,
        "facNumber": this.facNumber,
        "postalCode": this.postalCode,
        "hospitalNo": this.hospitalNo,
        "decoctMedicine": this.decoctMedicine,
        "deliveryRequire": this.deliveryRequire,
      }
      //发送请求
      await Axios.request({
        method: 'POST',
        url: 'http://49.235.113.96:8085/pres_info/dtb/fac/hand',
        data: dataJson,
      }).then(res => {
        //结果集处理
        this.visible = false;
        console.log(res.data)
        if (res.data.status === 200) {
          this.$message.success('处方分配成功！');
          this.$notification.success({
            message: '处方分配成功！',
            description: '操作行ID: ' + id + '  状态码: ' + res.data.status + '  时间戳: ' + res.data.timestamp
                + '您现在可以在“药厂报文”菜单里查看新增的待发送的报文了。',
            icon: <a-icon type="check-circle" style="color: #16E09a"/>,
            duration: 0
          });
        } else {
          this.$message.error('处方分配失败！');
          this.$notification.error({
            message: '处方分配失败！',
            description: '操作行ID: ' + id + '  状态码: ' + res.data.status + '  时间戳: ' + res.data.timestamp,
            icon: <a-icon type="close-circle" style="color: #CE1919FF"/>,
            duration: 0
          });
        }
      })
      //重新刷新表格
      await this.init()
    },
    //分配药厂按钮
    distributeFactory(id) {
      this.visible = true;
      this.currentId = id;
    },
    //分配药厂的复选框
    handleChange(value) {
      console.log(`selected ${value}`);
    },
    onClose() {
      this.visible = false;
    },
    //查询所有已分配药厂的处方的记录
    async init() {
      const {data: res} = await Axios.get('http://49.235.113.96:8085/pres_info/get/all')
      this.tableData = res.data
    },
    //表格查询内置按钮
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
