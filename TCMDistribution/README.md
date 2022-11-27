# 工程简介

本工程分2大模块：1，TCMD(中药房管理系统)2，TCMP(中药房-邮政系统)
# 配置信息
配置信息都集成在`application.yaml`内

# 命名格式

处方信息（医生站发给中药房的信息） `PresInfo`<br>
报文信息（中药房发给邮政局的信息） `ShipInfo`<br>
## 功能方法命名
业务层统一以 `动作前缀`+`操作对象`+`传入参数`命名
### 命名前缀
`查询相关` `query`<br>
`查询历史相关` `queryHistory`<br>
`新增相关` `save`<br>
`删除相关` `remove`<br>
`撤销相关` `repent`<br>
`发送相关` `send`<br>
`审核相关` `adopt/check`<br>
`批量相关` `batch`<br>
## 接口命名
接口严格按照rest风格进行编写
控制层统一以 `动作前缀`+`操作对象`命名
### 命名前缀
`分配` `Dtb`<br>
`查询` `Get`<br>
`查询历史` `His`<br>
`接收` `Rcv`<br>
`删除` `Rmv`<br>
`撤销` `Rpt`<br>
`审核` `Exm`<br>
`保存/新增` `Sav`<br>
`发送` `Snd`<br>
## 底层对象命名
`实体类对象` 仅2个：`PresInfo/ShipInfo`<br>
`数据传输对象` 前缀：`动作前缀`，词根：`实体类对象`，后缀：`DTO`<br>
`数据层接口` 词根：`实体类对象`，后缀：`Dao`<br>
`业务层对象接口` 前缀：`I`，词根：`实体类对象`，后缀：`Service`<br>
`业务层对象实现类` 词根：`实体类对象`，后缀：`ServiceImpl`<br>

# 技术栈
## 后端
### Spring相关
`Springboot` 整体项目的搭建<br>
`Springmvc` 页面持久化<br>
### 消息队列
`RabbitMQ` 用于对医生站处方发送的削峰
### 定时任务
`SpringScheduled` 异步刷新处方请求页面
### 日志与报表
`Slf4j` 日志记录<br>
`WebSocket` 轻量级信息交互模块<br>
`EasyExcel` 日志与操作报表（以Excel形式生成）
### 加密与解密
`Hutool-Crypto` 加密相关算法(默认AES，详细请看[Encrypt.java](src/main/java/cn/calendo/tcmdistribution/utils/Encrypt.java))
### 邮件与发送
`JavaMail` 可与定时任务组合，定时给指定用户/用户群体发送报表等消息
### 存储
`Mysql` 处方信息与报文信息的存储
## 前端
`Vue` 渐进式框架<br>
`ElementUI` 组件化开发<br>
`Node.js` 工程巡航<br>
## 运维
### 容器与集群
`Docker` 利用docker的沙箱隔离机制将项目的存储部署在内
### 代理
`Nginx` 前端项目的代理服务器，以作端口映射与负载均衡

## 信息流程
`医生站`->`中药房`->`邮政局`

`医生站`————`处方PresInfo系列`—————>`中药房`：分三个状态①未审核②审核通过，存入DB，但未分配药厂，未被标记废弃③分配完药厂，被标记废弃
<br>
`中药房`————`报文ShipInfo系列`—————>`邮政局`：分两个状态④未发送，未被标记废弃⑤已发送，已被标记废弃

## 加密算法

`对称加密: AES(默认) DES SM4`<br>
`非对称加密: RSA ECIES`<br>
不使用摘要加密的原因：摘要加密无法解密还原，只能比对加密结果<br>
不使用国密加密的原因：需要额外引入算法依赖，会导致加密时间略长，系统性能下降<br>
使用对称/非对称加密的原因：通过密钥进行加密，密钥由双方进行协商定制
本系统默认使用`AES对称加密`算法，明文（报文ShipInfo系列）将在中药房准备发送至邮政局时进行加密，药厂需要匹配中药房的密钥进行解密。同时支持可扩展其它对称/非对称加密算法

## 传输对象

医生站->`处方JSON`->中药房->`RcvPresDTO`->审核成功->DTO转换->`PresDTO`->存入DB;药厂分配(多条`BatchSaveFacDTO`+`PresInfo`)->
Bean转换与加密->`ShipInfo`->存入新DB->DTO转换->`SndShipDTO`->发送->`报文JSON`->邮政->`RcvShipDTO`->解码与提取->`PostMedDTO`->`DTO转换`
->`PostMed`->存入DB

## 功能

### 处方（PresInfo）相关

接收医生站发送的处方信息并自动审核、存储、展示，并带有撤销、定时、查询历史、自定义查询等功能
当医生站的处方信息发送到中药房时，系统会自动审核处方信息（接收/拒绝），并将信息自动反馈给医生站（`R对象`），如果审核通过，将自动保存（新增）至数据库，等待中药房操作员进行药厂分配（未分配时，`isDeleted`=0，`isDistri`=0），
其中，`isDistri`是药厂分配数量：0：未分配；n+：分配的药厂数量；`isDeleted`是操作完成标记：0：操作未完成；1：操作已完成，且正常的查询查询不到，需要在历史查询中查询；（<font color=#ffaaaa>撤销功能可以将`isDeleted`的1改为0</font>)

#### 自动审核（接口位置：不对外提供）

```java
/**
 * 报文校验审核
 *
 * @param rcvPresInfoDTO 接收的处方信息
 * @return 通过与否
 */
boolean checkPresInfo(RcvPresInfoDTO rcvPresInfoDTO);
```

#### 查询

```java
        /**
        * 查询所有处方
        *
        * @return PresInfo的集合
        */
        List<PresInfo> queryPresInfoAll();

        /**
         * 根据id查询某条处方
         *
         * @param id 唯一序列号
         * @return PresInfo
         */
        PresInfo queryPresInfoById(Long id);

        /**
         * 根据日期查询某条处方
         *
         * @param date 日期
         * @return PresInfo的集合
         */
        List<PresInfo> queryPresInfoByTransactionDate(String date);

        /**
         * 根据日期区间查询某处方
         * @param st 起始日期
         * @param ed 截止日期
         * @return PresInfo集合
         */
        List<PresInfo> queryPresInfoByTransactionDateBetween(String st,String ed);

        /**
         * 根据日时间查询某条处方
         *
         * @param time 交易时间
         * @return PresInfo的集合
         */
        List<PresInfo> queryPresInfoByTransactionTime(String time);

        /**
         * 根据时间区间查询某处方
         * @param st 起始时间
         * @param ed 截止时间
         * @return PresInfo集合
         */
        List<PresInfo> queryPresInfoByTransactionTimeBetween(String st,String ed);

        /**
         * 根据病人姓名查询某条处方
         *
         * @param patientName 病人姓名
         * @return PresInfo的集合
         */
        List<PresInfo> queryPresInfoByPatientName(String patientName);

        /**
         * 根据病人性别查询某条处方
         *
         * @param patientGender 病人性别
         * @return PresInfo的集合
         */
        List<PresInfo> queryPresInfoByPatientGender(String patientGender);

        /**
         * 根据病人年龄查询某条处方
         *
         * @param patientAge 病人年龄
         * @return PresInfo的集合
         */
        List<PresInfo> queryPresInfoByPatientAge(Integer patientAge);

        /**
         * 根据患者年龄区间查询某处方
         * @param st 起始年龄
         * @param ed 截止年龄
         * @return PresInfo集合
         */
        List<PresInfo> queryPresInfoByPatientAgeBetween(Integer st,Integer ed);

        /**
         * 根据医生姓名查询某条处方
         *
         * @param doctorName 医生姓名
         * @return PresInfo的集合
         */
        List<PresInfo> queryPresInfoByDoctorName(String doctorName);

        /**
         * 根据医生ID查询某条处方
         *
         * @param doctorId 医生ID
         * @return PresInfo的集合
         */
        List<PresInfo> queryPresInfoByDoctorId(String doctorId);

        /**
         * 根据日门诊号查询某条处方
         *
         * @param outpatientNo 门诊号
         * @return PresInfo的集合
         */
        List<PresInfo> queryPresInfoByOutpatientNo(String outpatientNo);

        /**
         * 根据交易金额查询某条处方
         *
         * @param price 金额
         * @return PresInfo的集合
         */
        List<PresInfo> queryPresInfoByPrice(BigDecimal price);

        /**
         * 根据价格区间查询某处方
         * @param st 起始价格
         * @param ed 截止价格
         * @return PresInfo集合
         */
        List<PresInfo> queryPresInfoByPriceBetween(BigDecimal st,BigDecimal ed);
```

#### 查询历史

```java
/**
 * 查询所有已分配的处方的信息
 *
 * @return PresInfo的集合
 */
List<PresInfo> queryHistoryPresInfoAll();

/**
 * 根据id查询某条已分配的处方的信息
 *
 * @param id 唯一序列号
 * @return PresInfo
 */
PresInfo queryHistoryPresInfoById(Long id);
```

#### 新增（审核通过后自动执行）

```java
/**
 * 保存一条审核通过的处方
 *
 * @param rcvPresInfoDTO 接收的处方信息(dto)
 * @return 保存与否
 */
boolean saveRcvPresInfoDTO2PresInfoById(RcvPresInfoDTO rcvPresInfoDTO);
```

#### 标记废弃（分配后自动执行）

```java
/**
 * 根据id删除某条处方
 *
 * @param rmvPresInfoDTO 报文删除的dto
 * @return 删除与否
 */
boolean removePresInfoById(RmvPresInfoDTO rmvPresInfoDTO);
```

#### 撤销

```java
/**
 * 根据id反悔还原某条已删除的处方
 *
 * @param rmvPresInfoDTO 移除的dto
 * @return t/f
 */
boolean repentPresInfoById(RmvPresInfoDTO rmvPresInfoDTO);
```

#### 永久删除

```java
/**
 * 根据id永久删除某条某条处方
 *
 * @param id 报文删除的dto
 * @return 删除与否
 */
boolean foreverRemovePresInfoById(Long id);
```

### 报文（ShipInfo）相关

对单个处方进行药厂分配（可选≥1个药厂）（会生成多条报文），并展示分配后的报文、手动选择发送报文，并带有加密、撤销、定时、查询历史、自定义查询等功能
当中药房操作员需要分配药厂时，需要在前端传入几个参数，并以json格式发送至相应接口（接口文档请看附件），下面是一个例子
```json
{
    "id": 1593871823018184706,
    "facNumber": 2,
    "facName": [
        "滁州制药",
        "台州制药"
    ],
    "recipientName": "哈哈哈你爷爷在此！",
    "recipientAddress": "浙江宁波",
    "recipientTelephone": "1234555667",
    "postalCode": "310000",
    "hospitalNo": "022391",
    "deliveryRequire": "保持干燥与平稳",
    "decoctMedicine": 1
}
```
`id`雪花算法生成的唯一标识码<br>
`facNumber`药厂数量<br>
`facName`药厂名称（使用List集合表示，前端需要将数据转化为集合）<br>
`recipientName`收件人姓名<br>
`recipientAddress`收件人地址<br>
`recipientTelephone`收件人电话<br>
`postalCode`邮政编码<br>
`hospitalNo`医院编号<br>
`deliveryRequire`配送要求<br>
`decoctMedicine`是否煎药（0：煎药；1：不煎药（草药）；2：膏方）<br>
当操作员设置好并保存后，系统会自动审核格式与数据，若不符合将报错，若符合，将把操作的处方信息(PresInfo)的`isDeleted`=0设置为1，`isDistri`=0设置为`facNumber`条，并在报文数据库中新增`facNumber`条报文（未发送报文时，`isDeleted`=0），
其中，`isDistri`是药厂分配数量：0：未分配；n+：分配的药厂数量；`isDeleted`是操作完成标记：0：操作未完成；1：操作已完成，且正常的查询查询不到，需要在历史查询中查询；（<font color=#ffaaaa>撤销功能可以将`isDeleted`的1改为0</font>)
当报文成功发送至邮政时，报文数据库的相应的发送报文的行的`isDeleted`也会自动变成1

#### 手动分配
```java
/**
     * 按照指定的处方单与药厂数量和名字批量新增邮政报文
     * @param batchSaveFacDTO 依据处方信息批量新增邮政报文的dto
     * @param presInfo 医院给邮政的配送信息报文
     * @return 成功新增数量
     */
Integer batchSaveShipInfoByFac(BatchSaveFacDTO batchSaveFacDTO, PresInfo presInfo);
```

#### 发送报文
```java
/**
     * 发送医院给邮政的报文
     *
     * @param sndShipInfoDTO 报文dto
     * @return 发送与否
     */
boolean sendShipInfo(SndShipInfoDTO sndShipInfoDTO);
```

#### 查询
```java
    /**
     * 查询所有发送的药品报文
     *
     * @return ShipInfo的集合
     */
    List<ShipInfo> queryShipInfoAll();

    /**
     * 根据id查询某条报文
     *
     * @param id 唯一序列号
     * @return ShipInfo
     */
    ShipInfo queryShipInfoById(Long id);

    /**
     * 根据日期查询某条报文
     *
     * @param date 报文发送日期
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByDate(String date);

    /**
     * 根据日期区间查询某报文
     * @param st 起始日期
     * @param ed 截止日期
     * @return ShipInfo集合
     */
    List<ShipInfo> queryShipInfoByDateBetween(String st,String ed);

    /**
     * 根据时间查询某条报文
     *
     * @param time 报文发送日期
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByTime(String time);

    /**
     * 根据时间区间查询某报文
     * @param st 起始时间
     * @param ed 截止时间
     * @return ShipInfo集合
     */
    List<ShipInfo> queryShipInfoByTimeBetween(String st,String ed);

    /**
     * 根据收件人姓名查询某条报文
     *
     * @param recipientName 收件人姓名
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByRecipientName(String recipientName);

    /**
     * 根据收件人地址查询某条报文
     *
     * @param recipientAddress 收件人地址
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByRecipientAddress(String recipientAddress);

    /**
     * 根据收件人电话查询某条报文
     *
     * @param recipientTel 收件人电话
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByRecipientTel(String recipientTel);

    /**
     * 根据邮政编码查询某条报文
     *
     * @param postalCode 报文发送的邮政编码
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByPostalCode(String postalCode);

    /**
     * 根据处方号查询某条报文
     *
     * @param prescriptionNo 处方号
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByPrescriptionNo(String prescriptionNo);

    /**
     * 根据医院编号查询某条报文
     *
     * @param hospitalNo 医院编号
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByHospitalNo(String hospitalNo);

    /**
     * 根据药厂编号查询某条报文
     *
     * @param pharmaFactoryNo 药厂编号
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByPharmaFactoryNo(String pharmaFactoryNo);

    /**
     * 根据是否煎药查询某条报文
     *
     * @param decoctMedicine 是否煎药。0：煎药；1：不煎药（草药）；2：膏方
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByDecoctMedicine(Integer decoctMedicine);

    /**
     * 根据门诊号查询某条报文
     *
     * @param outpatientNo 门诊号
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByOutpatientNo(String outpatientNo);

    /**
     * 根据就诊病人姓名查询某条报文
     *
     * @param patientName 就诊病人姓名
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByPatientName(String patientName);
```

#### 查询历史
```java
    /**
     * 查询所有已发送的报文信息
     * @return ShipInfo的集合
     */
    List<ShipInfo> queryHistoryShipInfoAll();

    /**
     * 根据id查询某条已发送的报文信息
     * @param id 唯一序列号
     * @return ShipInfo
     */
    ShipInfo queryHistoryShipInfoById(Long id);
```

### 定时任务相关

```java
/**
 * 任务启动
 *
 * @param taskId     任务id
 * @param expression cron表达式
 * @return TaskIdDTO TaskIdDTO对象
 */
TaskIdDTO scheduleStart(String taskId, String expression);

/**
* 任务停止
*
* @param taskId 任务id
* @return TaskIdDTO TaskIdDTO对象
*/
TaskIdDTO scheduleStop(String taskId);
```

### 日志相关
日志查询无业务层方法，查询地址已集成在 `application.yaml`内，需部署到linux服务器后方可使用
<br>
相关代码为`LogQuery.java`

#### 加密（不对外提供）
默认AES

#### 标记废弃（分配后自动执行）
```java
/**
     * 根据id删除某条已发送的报文
     *
     * @param rmvShipInfoDTO 报文删除的dto
     * @return 删除与否
     */
boolean removeShipInfoById(RmvShipInfoDTO rmvShipInfoDTO);
```

#### 撤销
```java
/**
     * 根据id反悔还原某条已删除的报文
     *
     * @param rmvShipInfoDTO 唯一序列号
     * @return ShipInfo
     */
boolean repentShipInfoById(RmvShipInfoDTO rmvShipInfoDTO);
```

#### 永久删除
```java
/**
     * 根据id永久删除某条已发送的报文
     *
     * @param id 报文删除的dto
     * @return 删除与否
     */
boolean foreverRemoveShipInfoById(Long id);
```

## 数据库设计

内含2个系统数据库，其中`tcmp`表归属于邮政系统，可以另外单独存放与配置。

#### TCMD(中药房管理系统)

数据库名叫`tcmd`
初始化配置

```mysql
drop database if exists tcmd;
create schema tcmd collate utf8mb4_general_ci;
use tcmd;
```

```mysql
drop table if exists tbl_ship_info;
create table tbl_ship_info
(
    id                  bigint(25)        not null comment '主键，用于区分行',
    transaction_date    date              not null comment '交易日期 YYYY MM DD',
    transaction_time    time              not null comment '交易时间 HH:MM:SS',
    recipient_name      varchar(60)       not null comment '收件人姓名',
    recipient_address   varchar(100)      not null comment '收件人地址',
    recipient_telephone varchar(30)       not null comment '收件人电话',
    postal_code         varchar(6)        null comment '邮政编码',
    prescription_no     varchar(25)       not null comment '处方号',
    hospital_no         varchar(6)        not null comment '医院编号',
    pharma_factory_no   varchar(6)        not null comment '药厂编号',
    delivery_require    varchar(60)       null comment '配送要求',
    prescription_info   varchar(2000)     not null comment '加密处方信息，一般为json格式',
    decoct_medicine     tinyint           null comment '是否煎药。0：煎药；1：不煎药（草药）；2：膏方',
    outpatient_no       varchar(10)       null comment '门诊号',
    patient_name        varchar(20)       null comment '就诊病人姓名',
    info_remarks        varchar(30)       null comment '备注',
    is_deleted          tinyint default 0 null comment '操作完成标记。0：操作未完成；1：操作完成',
    constraint tbl_ship_info_id_uindex
        unique (id)
)
    comment '医院给邮政报文';

alter table tbl_ship_info
    add primary key (id);

```

```mysql
drop table if exists tbl_pres_info;
create table tbl_pres_info
(
    id                  bigint(25)        not null comment '唯一码',
    transaction_date    date              not null comment '开方日期',
    transaction_time    time              not null comment '开方时间',
    patient_name        varchar(20)       null comment '病人姓名',
    patient_gender      varchar(8)        null comment '病人性别',
    patient_age         tinyint           null comment '病人年龄',
    doctor_name         varchar(20)       null comment '开单医生姓名',
    doctor_id           varchar(10)       null comment '医生工号',
    outpatient_no       varchar(10)       null comment '门诊号',
    use_method          varchar(20)       null comment '用法：口服、外用、浸泡、熏蒸等',
    take_method         varchar(30)       null comment '服法：如饭后温服、睡前温服',
    decoct_method       varchar(30)       null comment '煎法：如浓煎二汁各150ml',
    method_remark       varchar(1000)     null comment '方法备注',
    prescription_number int               null comment '处方贴数',
    price               decimal(7, 2)     not null comment '总金额（RMB）',
    herbs_number        int               null comment '草药味数',
    medicine_detail     varchar(60)       not null comment '药品名称规格',
    unit                varchar(10)       not null comment '单位',
    single_post_vol     varchar(20)       null comment '单帖量',
    process_method      varchar(20)       null comment '处理方法：如先煎，后下',
    medicine_remark     varchar(60)       null comment '药品明细备注',
    is_distri           tinyint default 0 not null comment '分配情况。0：未分配；n：药厂数量',
    is_deleted          tinyint default 0 not null comment '操作完成标记',
    constraint tbl_pres_info_id_uindex
        unique (id)
)
    comment '医生站发往中药房的处方信息表';

alter table tbl_pres_info
    add primary key (id);
```

#### TCMP(中药房-邮政系统)

数据库名叫`tcmp`
初始化配置

```mysql
drop database if exists tcmp;
create schema tcmp collate utf8mb4_general_ci;
use tcmp;
```

```mysql
drop table if exists tbl_post2fact;
create table tbl_post2fact
(
    id                bigint(25)        not null comment 'mp里雪花算法自动生成的id',
    transaction_date  date              not null comment '交易日期 YYYY MM DD',
    transaction_time  time              not null comment '交易时间 HH:MM:SS',
    prescription_no   varchar(25)       not null comment '处方号',
    hospital_no       varchar(6)        not null comment '医院编号',
    prescription_info varchar(2000)     not null comment '加密处方信息，一般为json格式',
    decoct_medicine   tinyint           not null comment '是否煎药。0：煎药；1：不煎药（草药）；2：膏方',
    info_remarks      varchar(50)       null comment '备注',
    is_deleted        tinyint default 0 not null comment  '操作完成标记',
    constraint tbl_post2fact_id_uindex
        unique (id)
)
    comment '邮政发给药厂报文';

alter table tbl_post2fact
    add primary key (id);

```

# 延伸阅读

