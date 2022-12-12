# tcmd-vue
TCManager系统的前端项目
## Project setup
原来的npm太慢了，选个淘宝的镜像<br>
`npm install -g cnpm --registry=https://registry.npmmirror.com`

然后接下来的`npm`都被`cnpm`代替
### 1.安装所需组件包
```
cnpm install
```

### 2.编译和运行
```
cnpm run serve
```

### 3.部署打包
```
cnpm run build
```
## 技术栈
`ant-design-vue: 1.7.8`<br>
`axios: 1.2.0`<br>
`echarts: 5.4.0`<br>
`vue: 2.6.14`<br>

## 功能
#### 登录登出
系统界面右上角的登录与登出，账号与密码需要从管理员处获取到，无法自行添加与删除
#### 查询筛选
查询筛选功能包括了所有渲染到页面上的字段的查询(`select *`)与关键词查询(`select where like`)
#### 医生处方
查询筛选功能包括了所有渲染到页面上的字段的查询(`select *`)与关键词查询(`select where like`)
#### 药厂报文
#### 厨房历史
#### 报文历史
#### 定时任务
#### 药厂信息
#### 医院信息
#### 特殊操作
#### 数据统计
#### 日志信息
#### 系统设置

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
