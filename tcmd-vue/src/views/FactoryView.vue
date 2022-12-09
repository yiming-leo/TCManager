<template>
  <div class="factory">
    <a-table :data-source="data" :columns="columns">
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
      <template slot="operation" slot-scope="text, record">
        <a-button type="primary">分配药厂</a-button>
      </template>
    </a-table>
  </div>
</template>
<script>
const data = [
  {
    key: '1',
    factoryName: '富阳制药',
    factoryID: '0001',
    factoryAddress: '浙江省杭州市富阳区前江路224号',
  },
  {
    key: '2',
    factoryName: '华东制药',
    factoryID: '0002',
    factoryAddress: '浙江省杭州市钱塘区1325号',
  },
  {
    key: '3',
    factoryName: '滁州制药',
    factoryID: '0003',
    factoryAddress: '安徽省滁州市来安县纬一路69号',
  },
  {
    key: '4',
    factoryName: '台州制药',
    factoryID: '0004',
    factoryAddress: '台州市临海市东海大道12号',
  },
  {
    key: '5',
    factoryName: '处州制药',
    factoryID: '0005',
    factoryAddress: '浙江省丽水市莲都区工业园区1号',
  },
  {
    key: '6',
    factoryName: '长兴制药',
    factoryID: '0006',
    factoryAddress: '湖州市长兴县长兴经济开发区经四路',
  },
  {
    key: '7',
    factoryName: '金华制药',
    factoryID: '0007',
    factoryAddress: '金华市金东区环城东路1180号',
  },
  {
    key: '8',
    factoryName: '康恩贝制药',
    factoryID: '0008',
    factoryAddress: '金华市兰溪市康恩贝大道1号',
  },
  {
    key: '9',
    factoryName: '甬州制药',
    factoryID: '0009',
    factoryAddress: '宁波市海曙区新丰路228号',
  },
  {
    key: '10',
    factoryName: '温州制药',
    factoryID: '0010',
    factoryAddress: '温州市瓯海区南纬一路4号',
  },
  {
    key: '11',
    factoryName: '衢州制药',
    factoryID: '0011',
    factoryAddress: '衢州市江山市兴工北路15号',
  },
];
export default {
  components: {},
  data() {
    return {
      data,
      searchText: '',
      searchInput: null,
      searchedColumn: '',
      columns: [
        {
          title: '药厂名称',
          dataIndex: 'factoryName',
          key: 'factoryName',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.factoryName
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
          dataIndex: 'factoryID',
          key: 'factoryID',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.factoryID
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
          title: '药厂地址',
          dataIndex: 'factoryAddress',
          key: 'factoryAddress',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.factoryAddress
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
  methods: {
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
};
</script>
<style scoped>
</style>
