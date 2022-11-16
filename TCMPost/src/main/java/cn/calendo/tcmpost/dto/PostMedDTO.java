package cn.calendo.tcmpost.dto;

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
public class PostMedDTO implements Serializable {
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
    private Date transactionDate;
    /**
     * 交易时间 HH:MM:SS
     */
    private Time transactionTime;
    /**
     * 处方号
     */
    private String prescriptionNo;
    /**
     * 医院编号
     */
    private String hospitalNo;
    /**
     * 加密处方信息，一般为json格式
     */
    private String prescriptionInfo;
    /**
     * 是否煎药。0：煎药；1：不煎药（草药）；2：膏方
     */
    private Integer decoctMedicine;
    /**
     * 备注
     */
    private String infoRemarks;

}
