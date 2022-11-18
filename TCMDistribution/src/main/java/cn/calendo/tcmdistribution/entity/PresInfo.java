package cn.calendo.tcmdistribution.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 * 中药房接收医生站的处方信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tbl_pres_info")
public class PresInfo implements Serializable {
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
     * 病人姓名
     */
    @TableField(value = "patient_name")
    private String patientName;
    /**
     * 病人性别
     */
    @TableField(value = "patient_gender")
    private String patientGender;
    /**
     * 病人年龄
     */
    @TableField(value = "patient_age")
    private Integer patientAge;
    /**
     * 开单医生姓名
     */
    @TableField(value = "doctor_name")
    private String doctorName;
    /**
     * 医生工号
     */
    @TableField(value = "doctor_id")
    private String doctorId;
    /**
     * 门诊号
     */
    @TableField(value = "outpatient_no")
    private String outpatientNo;
    /**
     * 用法：口服、外用、浸泡、熏蒸等
     */
    @TableField(value = "use_method")
    private String useMethod;
    /**
     * 服法：如饭后温服、睡前温服
     */
    @TableField(value = "take_method")
    private String takeMethod;
    /**
     * 煎法：如浓煎二汁各150ml
     */
    @TableField(value = "decoct_method")
    private String decoctMethod;
    /**
     * 方法备注
     */
    @TableField(value = "method_remark")
    private String methodRemark;
    /**
     * 处方贴数
     */
    @TableField(value = "prescription_number")
    private Integer prescriptionNumber;
    /**
     * 总金额（RMB）
     */
    @TableField(value = "price")
    private BigDecimal price;
    /**
     * 草药味数
     */
    @TableField(value = "herbs_number")
    private Integer herbsNumber;
    /**
     * 药品名称规格
     */
    @TableField(value = "medicine_detail")
    private String medicineDetail;
    /**
     * 单位
     */
    @TableField(value = "unit")
    private String unit;
    /**
     * 单帖量
     */
    @TableField(value = "single_post_vol")
    private Integer singlePostVol;
    /**
     * 处理方法：如先煎，后下
     */
    @TableField(value = "process_method")
    private String processMethod;
    /**
     * 药品明细备注
     */
    @TableField(value = "medicine_remark")
    private String medicineRemark;
    /**
     * 分配情况。0：未分配；n：药厂数量
     */
    @TableField(value = "is_distri")
    private Integer isDistri;
    /**
     * 删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

}
