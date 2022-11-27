import Vue from 'vue'
import Antd from 'ant-design-vue';
import App from './App.vue'
import router from './router'
import store from './store'
import { Button, message } from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

Vue.config.productionTip = false

Vue.use(Antd);
Vue.use(Button);
Vue.prototype.$message = message;

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
