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
      PVechartsOption: {
        color: ['rgb(255,151,177)', 'rgb(225,160,48)',],
        title: {
          text: ''
        },
        tooltip: { //提示框
          trigger: 'axis',
        },
        legend: {//图例的类型
          icon: 'roundRect',//图例icon图标
          data: [
            {
              name: "上年",
              textStyle: {
                color: '#8c8686'
              }

            }, {
              name: "本年",
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
          boundaryGap: false, //坐标轴两边留白策略
          data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
          axisLabel: {//坐标轴刻度标签的相关设置
            interval: 0,
            textStyle: {
              color: '#8c8686',
              fontSize: 10
            },
          },
          axisLine: {//坐标轴轴线相关设置
            show: true,
            lineStyle: {
              color: 'rgb(2,121,253)'
            }
          },
          axisTick: { //坐标轴刻度相关设置。
            show: false,
          }
        },
        yAxis: {
          type: 'value',
          axisLabel: { //x轴的坐标文字
            show: true,
            textStyle: {
              color: '#fff' //文字的颜色
            },

          },
          max: 100,//最大值100
          axisLine: {//坐标轴轴线相关设置
            show: true,
            lineStyle: {
              color: 'rgb(2,121,253)'
            }
          },
          axisTick: { //坐标轴刻度相关设置。
            show: false,
          },
          splitLine: {  //坐标在grid区域的分割线
            lineStyle: { //设置分割线的样式(图表横线颜色)
              color: ['#153a8a']
            }
          }
        },
        series: [
          {
            name: '上年',
            type: 'line',
            data: [10, 20, 30, 50, 50, 10, 50, 60, 10, 50, 10, 30],
            lineStyle: {
              color: 'rgb(8,252,7)'  //线的颜色
            }
          },
          {
            name: '本年',
            type: 'line',
            data: [20, 20, 30, 50, 50, 10, 50, 20, 30, 50, 50, 30],
            lineStyle: {
              color: 'rgb(255,168,0)' //线的颜色
            }
          }
        ]
      },
      UVechartsOption: {
        color: ['rgb(56,225,195)', 'rgb(101,113,225)',],
        xAxis: {
          data: ['A', 'B', 'C', 'D', 'E']
        },
        yAxis: {},
        series: [
          {
            data: [10, 22, 28, 23, 19],
            type: 'line',
            smooth: true
          }
        ]
      }
    }
  },
  mounted() {
    this.$nextTick(function () {
      this.initPVCharts();
      this.initUVCharts();
    })
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
