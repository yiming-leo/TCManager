package cn.calendo.tcmdistribution.service.impl;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.common.SendRequest;
import cn.calendo.tcmdistribution.dao.ShipInfoDao;
import cn.calendo.tcmdistribution.dto.BatchSaveFacDTO;
import cn.calendo.tcmdistribution.dto.RcvPresInfoDTO;
import cn.calendo.tcmdistribution.dto.RmvShipInfoDTO;
import cn.calendo.tcmdistribution.dto.SndShipInfoDTO;
import cn.calendo.tcmdistribution.entity.PresInfo;
import cn.calendo.tcmdistribution.entity.ShipInfo;
import cn.calendo.tcmdistribution.service.IShipInfoService;
import cn.calendo.tcmdistribution.utils.Encrypt;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * 医院给邮政的配送信息报文
 */
@Service
public class ShipInfoServiceImpl extends ServiceImpl<ShipInfoDao, ShipInfo> implements IShipInfoService {

    @Autowired
    private SendRequest sendRequest;

    @Autowired
    private Encrypt encrypt;

    @Value("${constants.posterUrlPostNormal}")
    private String POSTER_URL_POST_NORMAL;

    @Value("${constants.posterUrlPostReject}")
    private String POSTER_URL_POST_REJECT;

    @Value("${constants.aesKey}")
    private String AES_KEY;

    /////////////////////////////////////////////查询历史/////////////////////////////////////////////

    @Override
    public List<ShipInfo> queryHistoryShipInfoAll() {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 1);//条件为删除
        lqw.orderByDesc(ShipInfo::getTransactionDate);//按日期降序排列
        lqw.orderByDesc(ShipInfo::getTransactionTime);//按时间降序排列
        return list(lqw);
    }

    @Override
    public ShipInfo queryHistoryShipInfoById(String id) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 1);//条件为删除
        lqw.eq(ShipInfo::getId, id);
        return getOne(lqw);
    }

    /////////////////////////////////////////////查询/////////////////////////////////////////////

    @Override
    public List<ShipInfo> queryShipInfoAll() {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.orderByDesc(ShipInfo::getTransactionDate);//按日期降序排列
        lqw.orderByDesc(ShipInfo::getTransactionTime);//按时间降序排列
        return list(lqw);
    }

    @Override
    public ShipInfo queryShipInfoById(String id) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getId, id);//条件为传入的
        return getOne(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByDate(String date) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(ShipInfo::getTransactionDate, date);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByDateBetween(String st, String ed) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.between(ShipInfo::getTransactionDate, st, ed);
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByTime(String time) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(ShipInfo::getTransactionTime, time);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByTimeBetween(String st, String ed) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.between(ShipInfo::getTransactionTime, st, ed);
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByRecipientName(String recipientName) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(ShipInfo::getRecipientName, recipientName);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByRecipientAddress(String recipientAddress) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(ShipInfo::getRecipientAddress, recipientAddress);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByRecipientTel(String recipientTel) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(ShipInfo::getRecipientTelephone, recipientTel);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByPostalCode(String postalCode) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(ShipInfo::getPostalCode, postalCode);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByPrescriptionNo(String prescriptionNo) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(ShipInfo::getPrescriptionNo, prescriptionNo);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByHospitalNo(String hospitalNo) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(ShipInfo::getHospitalNo, hospitalNo);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByPharmaFactoryNo(String pharmaFactoryNo) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(ShipInfo::getPharmaFactoryNo, pharmaFactoryNo);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByDecoctMedicine(Integer decoctMedicine) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(ShipInfo::getDecoctMedicine, decoctMedicine);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByOutpatientNo(String outpatientNo) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(ShipInfo::getOutpatientNo, outpatientNo);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByPatientName(String patientName) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(ShipInfo::getPatientName, patientName);//条件为传入的
        return list(lqw);
    }

    /////////////////////////////////////////////移除/////////////////////////////////////////////

    @Override
    public boolean removeShipInfoById(RmvShipInfoDTO rmvShipInfoDTO) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getId, rmvShipInfoDTO.getId());//条件为获取到的dto的id
        rmvShipInfoDTO.setIsDeleted(1);//设置isDeleted为1
        return update(rmvShipInfoDTO, lqw);//被设置更新后的实体类对象和查询条件
    }

    @Override
    public boolean foreverRemoveShipInfoById(String id) {
        return removeById(id);
    }

    @Override
    public List<ShipInfo> removeShipInfoBatch(List<String> ids) {
        return null;
    }

    /////////////////////////////////////////////新增/////////////////////////////////////////////

    @Override
    public boolean saveShipInfo(ShipInfo shipInfo) {
        return save(shipInfo);
    }

    /////////////////////////////////////////////按药厂批量新增/////////////////////////////////////////////

    @Override
    public Integer batchSaveShipInfoByFac(BatchSaveFacDTO batchSaveFacDTO, PresInfo presInfo) {
        //bean拷贝：从presInfo到rcvPresInfoDTO
        RcvPresInfoDTO rcvPresInfoDTO = new RcvPresInfoDTO();
        BeanUtil.copyProperties(presInfo, rcvPresInfoDTO, "isDistri", "isDeleted");
        Integer facNumber = batchSaveFacDTO.getFacNumber();
        List facName = batchSaveFacDTO.getFacName();
        Integer count = 0;
        for (int i = 0; i < facNumber; i++) {
            ShipInfo shipInfo = new ShipInfo();
            shipInfo.setTransactionDate(presInfo.getTransactionDate());
            shipInfo.setTransactionTime(presInfo.getTransactionTime());
            shipInfo.setRecipientName(batchSaveFacDTO.getRecipientName());
            shipInfo.setRecipientAddress(batchSaveFacDTO.getRecipientAddress());
            shipInfo.setRecipientTelephone(batchSaveFacDTO.getRecipientTelephone());
            shipInfo.setPostalCode(batchSaveFacDTO.getPostalCode());
            shipInfo.setPrescriptionNo(String.valueOf(presInfo.getId()));
            shipInfo.setHospitalNo(batchSaveFacDTO.getHospitalNo());
            shipInfo.setPharmaFactoryNo(String.valueOf(facName.get(i)));
            shipInfo.setDeliveryRequire(batchSaveFacDTO.getDeliveryRequire());

            SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, AES_KEY.getBytes());
            System.out.println(Arrays.toString(AES_KEY.getBytes()));
            System.out.println(aes);

            String aesEncrypt = encrypt.AESEncrypt(String.valueOf(rcvPresInfoDTO), aes);
            shipInfo.setPrescriptionInfo(aesEncrypt);//处方信息需要加密

            shipInfo.setDecoctMedicine(batchSaveFacDTO.getDecoctMedicine());
            shipInfo.setOutpatientNo(presInfo.getOutpatientNo());
            shipInfo.setPatientName(presInfo.getPatientName());
            shipInfo.setInfoRemarks(presInfo.getMethodRemark());
            save(shipInfo);
            count++;
        }
        return count;
    }

    /////////////////////////////////////////////审核通过/////////////////////////////////////////////

    @Override
    public boolean adoptShipInfoById(String id) {
        ShipInfo shipInfo = getById(id);
        shipInfo.setIsDeleted(1);
        return updateById(shipInfo);
    }

    /////////////////////////////////////////////发送/////////////////////////////////////////////

    @Override
    public boolean sendShipInfo(SndShipInfoDTO sndShipInfoDTO) {
        try {
            String res = sendRequest.sendPost(POSTER_URL_POST_NORMAL + "?token=123", JSON.toJSONString(sndShipInfoDTO));
            R r = JSONObject.parseObject(res, R.class);
            System.out.println("res: " + res);
            System.out.println("r: " + r);
            if (r.getStatus() != 200) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean sendReject(String text) {
        try {
            String res = sendRequest.sendPost(POSTER_URL_POST_REJECT, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /////////////////////////////////////////////恢复/////////////////////////////////////////////

    @Override
    public boolean repentShipInfoById(RmvShipInfoDTO rmvShipInfoDTO) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getId, rmvShipInfoDTO.getId());//条件为获取到的dto的id
        rmvShipInfoDTO.setIsDeleted(0);//设置isDeleted为0
        return update(rmvShipInfoDTO, lqw);//被设置更新后的实体类对象和查询条件
    }

}
