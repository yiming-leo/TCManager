# 工程简介


## 数据库设计
数据库名叫`tcmd`
内含几个表
```mysql
create table tbl_hosp2post
(
    id bigint(25) not null comment '主键，用于区分行',
    transaction_date date not null comment '交易日期 YYYY MM DD',
    transaction_time time not null comment '交易时间 HH:MM:SS',
    recipient_name varchar(60) not null comment '收件人姓名',
    recipient_address varchar(100) not null comment '收件人地址',
    recipient_telephone varchar(30) not null comment '收件人电话',
    postal_code varchar(6) null comment '邮政编码',
    prescription_no varchar(8) not null comment '处方号',
    hospital_no varchar(6) not null comment '医院编号',
    pharma_factory_no varchar(6) not null comment '药厂编号',
    delivery_require varchar(60) null comment '配送要求',
    prescription_info varchar(2000) not null comment '加密处方信息，一般为json格式',
    decoct_medicine tinyint null comment '是否煎药。0：煎药；1：不煎药（草药）；2：膏方',
    outpatient_no varchar(10) null comment '门诊号',
    patient_name varchar(20) null comment '就诊病人姓名',
    remarks varchar(30) null comment '备注',
    is_del tinyint null comment '删除备用行。0：未删除；1：删除'
)
    comment '医院给邮政报文';

create unique index tbl_hosp2post_id_uindex
    on tbl_hosp2post (id);

alter table tbl_hosp2post
    add constraint tbl_hosp2post_pk
        primary key (id);
```


# 延伸阅读

