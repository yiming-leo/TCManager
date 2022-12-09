<template>
  <div class="setting">
    <a-form-model
        ref="ruleForm"
        :model="form"
        :rules="rules"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
    >
      <a-form-model-item ref="name" label="系统名称" prop="name">
        <a-input
            v-model="form.name"
            @blur="() => {$refs.name.onFieldBlur();}"
            disabled
            value="TCManager"
        />
      </a-form-model-item>
      <a-form-model-item label="使用医院" prop="region">
        <a-select v-model="form.region" placeholder="please select your zone">
          <a-select-option value="1">
            浙江省杭州市中山医院
          </a-select-option>
          <a-select-option value="2">
            浙江省杭州市新华医院
          </a-select-option>
          <a-select-option value="3">
            浙江省杭州市中医院
          </a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="Activity time" required prop="date1">
        <a-date-picker
            v-model="form.date1"
            show-time
            type="date"
            placeholder="Pick a date"
            style="width: 100%;"
        />
      </a-form-model-item>
      <a-form-model-item label="Instant delivery" prop="delivery">
        <a-switch v-model="form.delivery"/>
      </a-form-model-item>
      <a-form-model-item label="Activity type" prop="type">
        <a-checkbox-group v-model="form.type">
          <a-checkbox value="1" name="type">
            Online
          </a-checkbox>
          <a-checkbox value="2" name="type">
            Promotion
          </a-checkbox>
          <a-checkbox value="3" name="type">
            Offline
          </a-checkbox>
        </a-checkbox-group>
      </a-form-model-item>
      <a-form-model-item label="Pull Hub Resources" prop="resource">
        <a-radio-group v-model="form.resource">
          <a-radio value="1">
            GitHub
          </a-radio>
          <a-radio value="2">
            Gitee
          </a-radio>
        </a-radio-group>
      </a-form-model-item>
      <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-button type="primary" @click="onSubmit">
          确定
        </a-button>
        <a-button style="margin-left: 10px;" @click="resetForm">
          取消
        </a-button>
      </a-form-model-item>
    </a-form-model>
  </div>
</template>
<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data() {
    return {
      labelCol: {span: 4},
      wrapperCol: {span: 14},
      other: '',
      form: {
        name: 'TCManager',
        region: undefined,
        date1: undefined,
        delivery: false,
        type: [],
        resource: '',
        desc: '',
      },
      rules: {
        name: [
          {required: true, message: 'Please input Activity name', trigger: 'blur'},
          {min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur'},
        ],
        region: [{required: true, message: 'Please select Activity zone', trigger: 'change'}],
        date1: [{required: true, message: 'Please pick a date', trigger: 'change'}],
        type: [
          {
            type: 'array',
            required: true,
            message: 'Please select at least one activity type',
            trigger: 'change',
          },
        ],
        resource: [
          {required: true, message: 'Please select activity resource', trigger: 'change'},
        ],
        desc: [{required: true, message: 'Please input activity form', trigger: 'blur'}],
      },
    };
  },
  methods: {
    onSubmit() {
      this.$refs.ruleForm.validate(valid => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },
  },
}
</script>
