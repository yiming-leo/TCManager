<template>
  <div class="log">
    <!--    <a-button @click="queryLog">查询日志</a-button>-->
    <div id="log-container" style="height: 650px; overflow-y: scroll; background: #333; color: #aaa; padding: 10px; text-align: left">
<!--      {{dataLog}}-->
      matching logs: 0<br>
      ----------no no matching logs----------<br>
      please cmd `tail -f /root` in os
      <div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      dataLog: '',
    }
  },
  created() {
    this.queryLog();
  },
  methods: {
    queryLog() {
      var websocket = new WebSocket('ws://49.235.113.96:8085/log');
      websocket.onopen = function () {
        console.log("websocket已打开")
      }
      websocket.onmessage = function (event) {
        this.dataLog = event.data
        console.log("data已输出")
        console.log(this.dataLog)
      }
    }
  },
}
// $(document).ready(function () {
//   var websocket = new WebSocket('ws://49.235.113.96:8085/log');
//   websocket.onmessage = function (event) {
//     $("#log-container div").append(event.data);
//     $("#log-container").scrollTop($("#log-container div").height() - $("#log-container").height());
//   };
// });
</script>
