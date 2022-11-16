package cn.calendo.tcmpost.service.impl;

import cn.calendo.tcmpost.dao.PostMedDao;
import cn.calendo.tcmpost.dto.PostMedDTO;
import cn.calendo.tcmpost.dto.RcvShipInfoDTO;
import cn.calendo.tcmpost.entity.PostMed;
import cn.calendo.tcmpost.service.IPostMedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostMedServiceImpl extends ServiceImpl<PostMedDao, PostMed> implements IPostMedService {


    @Override
    public PostMedDTO receivePostMedicine(RcvShipInfoDTO rcvShipInfoDTO) {
        PostMedDTO postMedDTO = new PostMedDTO();
        postMedDTO.setTransactionDate(rcvShipInfoDTO.getTransactionDate());
        postMedDTO.setTransactionTime(rcvShipInfoDTO.getTransactionTime());
        postMedDTO.setPrescriptionNo(rcvShipInfoDTO.getPrescriptionNo());
        postMedDTO.setHospitalNo(rcvShipInfoDTO.getHospitalNo());
        postMedDTO.setPrescriptionInfo(rcvShipInfoDTO.getPrescriptionInfo());
        postMedDTO.setDecoctMedicine(rcvShipInfoDTO.getDecoctMedicine());
        postMedDTO.setInfoRemarks(rcvShipInfoDTO.getInfoRemarks());
        log.info(String.valueOf(postMedDTO));
        return postMedDTO;
    }
}
