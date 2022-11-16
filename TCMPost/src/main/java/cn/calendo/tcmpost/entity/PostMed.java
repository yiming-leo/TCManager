package cn.calendo.tcmpost.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * 邮政发往药厂的报文
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tbl_post2fact")
public class PostMed implements Serializable {
    /**
     * 序列化配置
     */
    private static final long serialVersionUID = 1L;
    /**
     * 雪花算法自动生成的id
     */
    private Long id;
    /**
     * 交易日期 YYYY MM DD
     */
    @TableField(value = "transaction_date")
    private Date transactionDate;
    /**
     * 交易时间 HH:MM:SS
     */
    @TableField(value = "transaction_time")
    private Time transactionTime;
    /**
     * 处方号
     */
    @TableField(value = "prescription_no")
    private String prescriptionNo;
    /**
     * 医院编号
     */
    @TableField(value = "hospital_no")
    private String hospitalNo;
    /**
     * 加密处方信息，一般为json格式
     */
    @TableField(value = "prescription_info")
    private String prescriptionInfo;
    /**
     * 是否煎药。0：煎药；1：不煎药（草药）；2：膏方
     */
    @TableField(value = "decoct_medicine")
    private Integer decoctMedicine;
    /**
     * 备注
     */
    @TableField(value = "info_remarks")
    private String infoRemarks;
    /**
     * 删除备用行。0：未删除；1：删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

}
