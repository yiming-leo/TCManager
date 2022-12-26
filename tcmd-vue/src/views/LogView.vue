<template>
  <div class="log">
<!--    <a-button @click="queryLog">查询日志</a-button>-->
    <div id="log-container" style="height: 650px; overflow-y: scroll; background: #333; color: #aaa; padding: 10px;">
      {{ dataLog }}
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
      var websocket = new WebSocket('ws://localhost:8085/log');
      websocket.onmessage = function (event) {
        this.dataLog = event.data
      }
    }
  },
}
// $(document).ready(function () {
//   var websocket = new WebSocket('ws://localhost:8085/log');
//   websocket.onmessage = function (event) {
//     $("#log-container div").append(event.data);
//     $("#log-container").scrollTop($("#log-container div").height() - $("#log-container").height());
//   };
// });
</script>
