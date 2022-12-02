package cn.calendo.tcmdistribution.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 依据处方信息批量新增邮政报文的dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchSaveFacDTO implements Serializable {
    /**
     * 序列化配置
     */
    private static final long serialVersionUID = 1L;
    /**
     * 传入的PresInfo的id
     */
    private String id;
    /**
     * 选择药厂数量
     */
    private Integer facNumber;
    /**
     * 填写药厂名字，为一个集合
     */
    private List facName;
    //////////////接下来为需给报文添加的外增字段//////////////
    /**
     * 收件人姓名
     */
    private String recipientName;
    /**
     * 收件人地址
     */
    private String recipientAddress;
    /**
     * 收件人手机
     */
    private String recipientTelephone;
    /**
     * 邮政编码
     */
    private String postalCode;
    /**
     * 医院编号
     */
    private String hospitalNo;
    /**
     * 配送要求
     */
    private String deliveryRequire;
    /**
     * 是否煎药
     */
    private Integer decoctMedicine;
}
