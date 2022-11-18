package cn.calendo.tcmpost.service;

import cn.calendo.tcmpost.dto.PostMedDTO;
import cn.calendo.tcmpost.dto.RcvShipInfoDTO;
import cn.calendo.tcmpost.entity.PostMed;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IPostMedService extends IService<PostMed> {

    /**
     * 接收医院的报文
     * @param rcvShipInfoDTO
     * @return
     */
    PostMedDTO receivePostMedicine(RcvShipInfoDTO rcvShipInfoDTO);

    /**
     * 保存接收到的报文
     * @param postMedDTO 接收到的报文
     * @return t/f
     */
    boolean savePostMedicine(PostMedDTO postMedDTO);

}
