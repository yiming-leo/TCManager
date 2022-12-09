<template>
  <div class="hospital">
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
    HospitalName: '浙江省中医院',
    HospitalID: '0001',
    HospitalAddress: '杭州市上城区仁和路10号',
  },
  {
    key: '2',
    HospitalName: '浙江省新华医院',
    HospitalID: '0002',
    HospitalAddress: '杭州市拱墅区潮王路318号',
  },
  {
    key: '3',
    HospitalName: '浙江省中山医院',
    HospitalID: '0003',
    HospitalAddress: '浙江省杭州市西湖区莫干山路219号',
  },
  {
    key: '4',
    HospitalName: '浙江省西溪医院',
    HospitalID: '0004',
    HospitalAddress: '浙江省杭州市西湖区留下镇横埠街2号',
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
          title: '医院名称',
          dataIndex: 'HospitalName',
          key: 'HospitalName',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.HospitalName
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
          dataIndex: 'HospitalID',
          key: 'HospitalID',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.HospitalID
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
          title: '医院地址',
          dataIndex: 'HospitalAddress',
          key: 'HospitalAddress',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
              record.HospitalAddress
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
