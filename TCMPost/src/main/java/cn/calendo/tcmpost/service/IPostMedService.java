package cn.calendo.tcmpost.service;

import cn.calendo.tcmpost.dto.PostMedDTO;
import cn.calendo.tcmpost.dto.RcvShipInfoDTO;
import cn.calendo.tcmpost.entity.PostMed;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IPostMedService extends IService<PostMed> {

    PostMedDTO receivePostMedicine(RcvShipInfoDTO rcvShipInfoDTO);

}
