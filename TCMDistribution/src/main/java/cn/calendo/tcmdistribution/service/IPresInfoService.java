package cn.calendo.tcmdistribution.service;

import cn.calendo.tcmdistribution.dto.RcvPresInfoDTO;
import cn.calendo.tcmdistribution.dto.RmvPresInfoDTO;
import cn.calendo.tcmdistribution.entity.PresInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * 医院给邮政的配送信息报文
 */
public interface IPresInfoService extends IService<PresInfo> {

    /////////////////////////////////////////////审核/////////////////////////////////////////////

    /**
     * 报文校验审核
     *
     * @param rcvPresInfoDTO 接收的处方信息
     * @return 通过与否
     */
    boolean checkPresInfo(RcvPresInfoDTO rcvPresInfoDTO);

    /////////////////////////////////////////////分配成功标记/////////////////////////////////////////////

    /**
     * 当药厂分配成功后需要标记isDeleted为1
     *
     * @param id 唯一码
     * @return t/f
     */
    boolean adoptPresInfoMark(String id, Integer facNumber);

    /////////////////////////////////////////////增加/////////////////////////////////////////////

    /**
     * 保存一条审核通过的处方
     *
     * @param rcvPresInfoDTO 接收的处方信息(dto)
     * @return 保存与否
     */
    boolean saveRcvPresInfoDTO2PresInfoById(RcvPresInfoDTO rcvPresInfoDTO);

    /////////////////////////////////////////////查询历史/////////////////////////////////////////////

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
    PresInfo queryHistoryPresInfoById(String id);


    /////////////////////////////////////////////查询/////////////////////////////////////////////

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
    PresInfo queryPresInfoById(String id);

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
    List<PresInfo> queryPresInfoByPriceBetween(BigDecimal st, BigDecimal ed);

    /////////////////////////////////////////////移除/////////////////////////////////////////////

    /**
     * 根据id删除某条处方
     *
     * @param rmvPresInfoDTO 报文删除的dto
     * @return 删除与否
     */
    boolean removePresInfoById(RmvPresInfoDTO rmvPresInfoDTO);

    /**
     * 根据id永久删除某条某条处方
     *
     * @param id 报文删除的dto
     * @return 删除与否
     */
    boolean foreverRemovePresInfoById(String id);

    /////////////////////////////////////////////恢复/////////////////////////////////////////////

    /**
     * 根据id反悔还原某条已删除的处方
     *
     * @param rmvPresInfoDTO 移除的dto
     * @return t/f
     */
    boolean repentPresInfoById(RmvPresInfoDTO rmvPresInfoDTO);

}
