package cn.calendo.tcmdistribution.service;

import cn.calendo.tcmdistribution.dto.BatchSaveFacDTO;
import cn.calendo.tcmdistribution.dto.RmvShipInfoDTO;
import cn.calendo.tcmdistribution.dto.SndShipInfoDTO;
import cn.calendo.tcmdistribution.entity.PresInfo;
import cn.calendo.tcmdistribution.entity.ShipInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 医院给邮政的配送信息报文
 */
public interface IShipInfoService extends IService<ShipInfo> {

    /////////////////////////////////////////////查询历史/////////////////////////////////////////////

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
    ShipInfo queryHistoryShipInfoById(String id);

    /////////////////////////////////////////////查询/////////////////////////////////////////////

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
    ShipInfo queryShipInfoById(String id);

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

    /////////////////////////////////////////////移除/////////////////////////////////////////////

    /**
     * 根据id删除某条已发送的报文
     *
     * @param rmvShipInfoDTO 报文删除的dto
     * @return 删除与否
     */
    boolean removeShipInfoById(RmvShipInfoDTO rmvShipInfoDTO);

    /**
     * 根据id永久删除某条已发送的报文
     *
     * @param id 报文删除的dto
     * @return 删除与否
     */
    boolean foreverRemoveShipInfoById(String id);

    /**
     * 根据id批量删除已发送报文
     *
     * @param ids 唯一序列号的集合
     * @return List<ShipInfo>
     */
    List<ShipInfo> removeShipInfoBatch(List<String> ids);

    /////////////////////////////////////////////新增/////////////////////////////////////////////

    /**
     * 新增医院给邮政的报文
     *
     * @param shipInfo 报文
     * @return 新增与否
     */
    boolean saveShipInfo(ShipInfo shipInfo);

    /////////////////////////////////////////////按药厂批量新增/////////////////////////////////////////////

    /**
     * 按照指定的处方单与药厂数量和名字批量新增邮政报文
     * @param batchSaveFacDTO 依据处方信息批量新增邮政报文的dto
     * @param presInfo 医院给邮政的配送信息报文
     * @return 成功新增数量
     */
    Integer batchSaveShipInfoByFac(BatchSaveFacDTO batchSaveFacDTO, PresInfo presInfo);

    /////////////////////////////////////////////审核通过/////////////////////////////////////////////

    /**
     * 指定id审核通过
     * @param id 指定id
     * @return t/f
     */
    boolean adoptShipInfoById(String id);

    /////////////////////////////////////////////发送/////////////////////////////////////////////

    /**
     * 发送医院给邮政的报文
     *
     * @param sndShipInfoDTO 报文dto
     * @return 发送与否
     */
    boolean sendShipInfo(SndShipInfoDTO sndShipInfoDTO);

    /**
     * 发送给邮政信息
     *
     * @param text 自定义信息
     * @return 发送与否
     */
    boolean sendReject(String text);

    /////////////////////////////////////////////恢复/////////////////////////////////////////////

    /**
     * 根据id反悔还原某条已删除的报文
     *
     * @param rmvShipInfoDTO 唯一序列号
     * @return ShipInfo
     */
    boolean repentShipInfoById(RmvShipInfoDTO rmvShipInfoDTO);

}
