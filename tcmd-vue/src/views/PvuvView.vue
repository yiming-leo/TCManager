<template>
  <div class="pvuv">
    <div v-show="true" id="pv" class="pv">
    </div>
    <div v-show="true" id="uv" class="uv">
    </div>
  </div>
</template>
<script>
import * as echarts from 'echarts'

export default {
  data() {
    return {
      myPVChart: '',
      myUVChart: '',
      UVechartsOption: {
        color: ['rgb(29,122,229)', 'rgb(225,118,119)',],
        title: {
          text: 'TCManager的UV图'
        },
        tooltip: { //提示框
          trigger: 'axis',
        },
        legend: {//图例的类型
          icon: 'roundRect',//图例icon图标
          data: [
            {
              name: "去年",
              textStyle: {
                color: '#8c8686'
              }

            }, {
              name: "今年",
              textStyle: {
                color: '#8c8686'
              }
            },
          ],
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          top: '17%',
          containLabel: true //grid区域是否包含坐标轴的刻度标签
        },
        xAxis: {
          type: 'category', //坐标轴类型。
          boundaryGap: true, //坐标轴两边留白策略
          data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
          axisLabel: {//坐标轴刻度标签的相关设置
            interval: 0,
            textStyle: {
              color: '#8c8686',
              fontSize: 10
            },
          },
          axisLine: {//坐标轴轴线相关设置
            // show: true,
            lineStyle: {
              // color: 'rgb(2,121,253)'
            }
          },
          axisTick: { //坐标轴刻度相关设置。
            show: true,
          }
        },
        yAxis: {},
        // yAxis: {
        //   type: 'value',
        //   axisLabel: { //x轴的坐标文字
        //     show: true,
        //     textStyle: {
        //       color: '#fff' //文字的颜色
        //     },
        //   },
        //   // max: 100,//最大值100
        //   axisLine: {//坐标轴轴线相关设置
        //     // show: true,
        //     lineStyle: {
        //       // color: 'rgb(2,121,253)'
        //     }
        //   },
        //   axisTick: { //坐标轴刻度相关设置。
        //     // show: true,
        //   },
        // },
        series: [
          {
            name: '去年',
            type: 'line',
            data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 27, 33, 62],
            smooth: true,
            lineStyle: {
              color: 'rgb(29,122,229)'  //线的颜色
            }
          },
          {
            name: '今年',
            type: 'line',
            smooth: true,
            data: [64, 132, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            lineStyle: {
              color: 'rgb(225,144,195)' //线的颜色
            }
          }
        ]
      },
      PVechartsOption: {
        color: ['rgb(56,225,195)', 'rgb(225,160,48)',],
        title: {
          text: 'TCManager的PV图'
        },
        tooltip: { //提示框
          trigger: 'axis',
        },
        legend: {//图例的类型
          icon: 'roundRect',//图例icon图标
          data: [
            {
              name: "前日",
              textStyle: {
                color: '#8c8686'
              }

            }, {
              name: "昨日",
              textStyle: {
                color: '#8c8686'
              }
            },
          ],

        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          top: '17%',
          containLabel: true //grid区域是否包含坐标轴的刻度标签
        },
        xAxis: {
          data: ['02:00', '04:00', '06:00', '08:00', '10:00', '12:00', '14:00', '16:00', '18:00', '20:00', '22:00', '24:00'],
        },
        yAxis: {},
        series: [
          {
            name: '前日',
            data: [0, 0, 0, 0, 25, 47, 169, 137, 57, 62, 77, 10],
            type: 'line',
            smooth: true,
            lineStyle: {
              color: 'rgb(29,229,158)'  //线的颜色
            }
          },
          {
            name: '昨日',
            data: [0, 0, 0, 0, 0, 12, 45, 77, 22, 41, 37, 0],
            type: 'line',
            smooth: true,
            lineStyle: {
              color: 'rgb(225,160,48)'  //线的颜色
            }
          }
        ]
      }
    }
  },
  created() {
    this.$nextTick(function () {
      this.initPVCharts();
      this.initUVCharts();
    })
  },
  mounted() {

  },
  methods: {
    //初始化PV图表
    initPVCharts() {
      this.myPVChart = echarts.init(document.getElementById('pv'));
      console.log(this.myPVChart)
      // 绘制图表
      this.myPVChart.setOption(this.PVechartsOption);
      //图表自适应
      window.addEventListener("resize", function () {
        this.myPVChart.resize()  // myChart 是实例对象
      })
    },
    //初始化UV图表
    initUVCharts() {
      this.myUVChart = echarts.init(document.getElementById('uv'));
      console.log(this.myUVChart)
      // 绘制图表
      this.myUVChart.setOption(this.UVechartsOption);
      //图表自适应
      window.addEventListener("resize", function () {
        this.myUVChart.resize()  // myChart 是实例对象
      })
    },
  }
}
</script>
<style lang="less" scoped>
.pv {
  width: 100%;
  height: 400px;
  background: #FFFFff;
}

.uv {
  width: 100%;
  height: 400px;
  background: #FFFFff;
}
</style>
