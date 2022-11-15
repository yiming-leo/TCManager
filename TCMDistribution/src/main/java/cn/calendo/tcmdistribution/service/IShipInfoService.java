package cn.calendo.tcmdistribution.service;

import cn.calendo.tcmdistribution.entity.ShipInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * 医院给邮政的配送信息报文
 */
public interface IShipInfoService extends IService<ShipInfo> {

    /**
     * 自定义向邮政发送药品报文并保存
     * @param transactionDate
     * @param transactionTime
     * @param recipientName
     * @param recipientAddress
     * @param recipientTelephone
     * @param postalCode
     * @param prescriptionNo
     * @param hospitalNo
     * @param pharmaFactoryNo
     * @param deliveryRequire
     * @param prescriptionInfo
     * @param decoctMedicine
     * @param outpatientNo
     * @param patientName
     * @param remarks
     * @param isDel
     * @return
     */
    boolean sendShipInfoByInsert(Date transactionDate, Time transactionTime, String recipientName,
                         String recipientAddress, String recipientTelephone, String postalCode,
                         String prescriptionNo, String hospitalNo, String pharmaFactoryNo,
                         String deliveryRequire, String prescriptionInfo, Integer decoctMedicine,
                         String outpatientNo, String patientName, String infoRemarks, Integer isDeleted);

//    boolean sendShipInfoByQueryDB();

    /**
     * 查询所有发送的药品报文
     * @return ShipInfo的集合
     */
    List<ShipInfo> queryShipInfoAll();

    /**
     * 根据id查询某条信息
     * @param id 唯一序列号
     * @return ShipInfo
     */
    ShipInfo queryShipInfoById(Long id);

    /**
     * 根据日期查询某条信息
     * @param date 报文发送日期
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByDate(String date);

    /**
     * 根据时间查询某条信息
     * @param time 报文发送日期
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByTime(String time);

    /**
     * 根据收件人姓名查询某条信息
     * @param recipientName 收件人姓名
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByRecipientName(String recipientName);

    /**
     * 根据收件人地址查询某条信息
     * @param recipientAddress 收件人地址
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByRecipientAddress(String recipientAddress);

    /**
     * 根据收件人电话查询某条信息
     * @param recipientTel 收件人电话
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByRecipientTel(String recipientTel);

    /**
     * 根据邮政编码查询某条信息
     * @param postalCode 报文发送的邮政编码
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByPostalCode(String postalCode);

    /**
     * 根据处方号查询某条信息
     * @param prescriptionNo 处方号
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByPrescriptionNo(String prescriptionNo);

    /**
     * 根据医院编号查询某条信息
     * @param hospitalNo 医院编号
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByHospitalNo(String hospitalNo);

    /**
     * 根据药厂编号查询某条信息
     * @param pharmaFactoryNo 药厂编号
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByPharmaFactoryNo(String pharmaFactoryNo);

    /**
     * 根据是否煎药查询某条信息
     * @param decoctMedicine 是否煎药。0：煎药；1：不煎药（草药）；2：膏方
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByDecoctMedicine(Integer decoctMedicine);

    /**
     * 根据门诊号查询某条信息
     * @param outpatient_no 门诊号
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByOutpatientNo(String outpatientNo);

    /**
     * 根据就诊病人姓名查询某条信息
     * @param patientName 就诊病人姓名
     * @return List<ShipInfo>
     */
    List<ShipInfo> queryShipInfoByPatientName(String patientName);

}
