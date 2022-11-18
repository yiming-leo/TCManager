package cn.calendo.tcmpost.service.impl;

import cn.calendo.tcmpost.common.Encrypt;
import cn.calendo.tcmpost.dao.PostMedDao;
import cn.calendo.tcmpost.dto.PostMedDTO;
import cn.calendo.tcmpost.dto.RcvShipInfoDTO;
import cn.calendo.tcmpost.entity.PostMed;
import cn.calendo.tcmpost.service.IPostMedService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.calendo.tcmpost.common.Constants.AES_KEY;

@Service
@Slf4j
public class PostMedServiceImpl extends ServiceImpl<PostMedDao, PostMed> implements IPostMedService {

    @Autowired
    private Encrypt encrypt;

    @Override
    public PostMedDTO receivePostMedicine(RcvShipInfoDTO rcvShipInfoDTO) {
        PostMedDTO postMedDTO = new PostMedDTO();
        postMedDTO.setTransactionDate(rcvShipInfoDTO.getTransactionDate());
        postMedDTO.setTransactionTime(rcvShipInfoDTO.getTransactionTime());
        postMedDTO.setPrescriptionNo(rcvShipInfoDTO.getPrescriptionNo());
        postMedDTO.setHospitalNo(rcvShipInfoDTO.getHospitalNo());

        String info = rcvShipInfoDTO.getPrescriptionInfo();

        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, AES_KEY.getBytes());
//        System.out.println(aes);
        String decrypt = encrypt.AESDecrypt(info, aes);
//        System.out.println(decrypt);
        postMedDTO.setPrescriptionInfo(decrypt);

        postMedDTO.setDecoctMedicine(rcvShipInfoDTO.getDecoctMedicine());
        postMedDTO.setInfoRemarks(rcvShipInfoDTO.getInfoRemarks());
        log.info(String.valueOf(postMedDTO));
        return postMedDTO;
    }

    @Override
    public boolean savePostMedicine(PostMedDTO postMedDTO) {
        PostMed postMed = new PostMed();
        BeanUtil.copyProperties(postMedDTO, postMed);
        postMed.setIsDeleted(0);
        return save(postMed);
    }
}
