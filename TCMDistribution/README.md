# 工程简介

本工程分2大模块：1，TCMD(中药房管理系统)2，TCMP(中药房-邮政系统)
## 信息流程
`医生站`————`处方PresInfo系列`—————>`中药房`：分三个状态①未审核②审核通过，存入DB，但未分配药厂，未被标记废弃③分配完药厂，被标记废弃
<br>
`中药房`————`报文ShipInfo系列`—————>`邮政局`：分两个状态④未发送，未被标记废弃⑤已发送，已被标记废弃
## 加密算法
本系统默认使用`AES对称加密`算法，明文（报文ShipInfo系列）将在中药房准备发送至邮政局时进行加密，药厂需要匹配中药房的密钥进行解密。同时支持可扩展其它加密算法

## 传输对象
医生站->`处方JSON`->中药房->`RcvPresDTO`->审核成功->DTO转换->`PresDTO`->存入DB->Bean转换->`ShipInfo`->DTO转换->`SndShipDTO`->发送->`报文JSON`

## 功能
接收医生站发送的处方信息并自动审核、存储、展示，对其进行药厂分配（可选≥1个药厂），并展示分配后的报文、手动选择发送报文，并带有加密、撤销、定时、查询历史等功能

## 数据库设计
数据库名叫`tcmd`
内含几个表，其中`tbl_post2fact`表归属于邮政系统，可以另外单独存放与配置。
```mysql
create table tbl_hosp2post
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
    is_deleted          tinyint default 0 null comment '删除备用行。0：未删除；1：删除',
    constraint tbl_hosp2post_id_uindex
        unique (id)
)
    comment '医院给邮政报文';

alter table tbl_hosp2post
    add primary key (id);

```
```mysql
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
    is_deleted        tinyint default 0 not null,
    constraint tbl_post2fact_id_uindex
        unique (id)
)
    comment '邮政发给药厂报文';

alter table tbl_post2fact
    add primary key (id);

```
```mysql
create table tbl_prescription
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
    is_deleted          tinyint default 0 not null comment '删除',
    constraint tbl_prescription_id_uindex
        unique (id)
)
    comment '医生站发往中药房的处方信息表';

alter table tbl_prescription
    add primary key (id);

```
# 延伸阅读

