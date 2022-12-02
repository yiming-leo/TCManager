<template>
  <div class="log">
    <h1>This is an log page</h1>
    <div id="log-container" style="height: 650px; overflow-y: scroll; background: #333; color: #aaa; padding: 10px;">
      <div>
      </div>
    </div>
  </div>
</template>

<script>


$(document).ready(function () {
  var websocket = new WebSocket('ws://49.235.113.96:8085/log');
  websocket.onmessage = function (event) {
    $("#log-container div").append(event.data);
    $("#log-container").scrollTop($("#log-container div").height() - $("#log-container").height());
  };
});
</script>
