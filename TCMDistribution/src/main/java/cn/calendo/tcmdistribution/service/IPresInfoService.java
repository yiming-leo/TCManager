package cn.calendo.tcmdistribution.service;

import cn.calendo.tcmdistribution.dto.RcvPresInfoDTO;
import cn.calendo.tcmdistribution.dto.RmvPresInfoDTO;
import cn.calendo.tcmdistribution.entity.PresInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 医院给邮政的配送信息报文
 */
public interface IPresInfoService extends IService<PresInfo> {

    /////////////////////////////////////////////审核/////////////////////////////////////////////

    /**
     * 报文校验审核
     * @param rcvPresInfoDTO 接收的处方信息
     * @return 通过与否
     */
    boolean checkPresInfo(RcvPresInfoDTO rcvPresInfoDTO);

    /////////////////////////////////////////////分配成功标记/////////////////////////////////////////////

    /**
     * 当药厂分配成功后需要标记isDeleted为1
     * @param id 唯一码
     * @return t/f
     */
    boolean adoptPresInfoMark(Long id,Integer facNumber);

    /////////////////////////////////////////////增加/////////////////////////////////////////////

    /**
     * 保存一条审核通过的处方
     * @param rcvPresInfoDTO 接收的处方信息(dto)
     * @return 保存与否
     */
    boolean saveRcvPresInfoDTO2PresInfoById(RcvPresInfoDTO rcvPresInfoDTO);

    /////////////////////////////////////////////查询历史/////////////////////////////////////////////

    /**
     * 查询所有已分配的处方的信息
     * @return PresInfo的集合
     */
    List<PresInfo> queryHistoryPresInfoAll();

    /**
     * 根据id查询某条已分配的处方的信息
     * @param id 唯一序列号
     * @return PresInfo
     */
    PresInfo queryHistoryPresInfoById(Long id);


    /////////////////////////////////////////////查询/////////////////////////////////////////////

    /**
     * 查询所有发送的药品报文
     * @return PresInfo的集合
     */
    List<PresInfo> queryPresInfoAll();

    /**
     * 根据id查询某条信息
     * @param id 唯一序列号
     * @return PresInfo
     */
    PresInfo queryPresInfoById(Long id);

    /////////////////////////////////////////////移除/////////////////////////////////////////////

    /**
     * 根据id删除某条已发送的报文
     * @param rmvPresInfoDTO 报文删除的dto
     * @return 删除与否
     */
    boolean removePresInfoById(RmvPresInfoDTO rmvPresInfoDTO);

    /**
     * 根据id永久删除某条已发送的报文
     * @param id 报文删除的dto
     * @return 删除与否
     */
    boolean foreverRemovePresInfoById(Long id);

    /////////////////////////////////////////////恢复/////////////////////////////////////////////

    /**
     * 根据id反悔还原某条已删除的报文
     * @param rmvPresInfoDTO 唯一序列号
     * @return PresInfo
     */
    boolean repentPresInfoById(RmvPresInfoDTO rmvPresInfoDTO);

}
