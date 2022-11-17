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
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static cn.calendo.tcmdistribution.common.Constants.POSTER_URL_POST_NORMAL;
import static cn.calendo.tcmdistribution.common.Constants.POSTER_URL_POST_REJECT;

/**
 * 医院给邮政的配送信息报文
 */
@Service
public class ShipInfoServiceImpl extends ServiceImpl<ShipInfoDao, ShipInfo> implements IShipInfoService {

    @Autowired
    private SendRequest sendRequest;

    @Autowired
    private Encrypt encrypt;

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
    public ShipInfo queryShipInfoById(Long id) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getId, id);//条件为传入的
        return getOne(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByDate(String date) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getTransactionDate, date);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByTime(String time) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getTransactionTime, time);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByRecipientName(String recipientName) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getRecipientName, recipientName);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByRecipientAddress(String recipientAddress) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getRecipientAddress, recipientAddress);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByRecipientTel(String recipientTel) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getRecipientTelephone, recipientTel);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByPostalCode(String postalCode) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getPostalCode, postalCode);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByPrescriptionNo(String prescriptionNo) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getPrescriptionNo, prescriptionNo);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByHospitalNo(String hospitalNo) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getHospitalNo, hospitalNo);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByPharmaFactoryNo(String pharmaFactoryNo) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getPharmaFactoryNo, pharmaFactoryNo);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByDecoctMedicine(Integer decoctMedicine) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getDecoctMedicine, decoctMedicine);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByOutpatientNo(String outpatientNo) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getOutpatientNo, outpatientNo);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<ShipInfo> queryShipInfoByPatientName(String patientName) {
        LambdaQueryWrapper<ShipInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShipInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(ShipInfo::getPatientName, patientName);//条件为传入的
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
    public boolean foreverRemoveShipInfoById(Long id) {
        return removeById(id);
    }

    @Override
    public List<ShipInfo> removeShipInfoBatch(List<Long> ids) {
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

            String md5Encrypt = encrypt.MD5Encrypt(String.valueOf(rcvPresInfoDTO));
            shipInfo.setPrescriptionInfo(md5Encrypt);//处方信息需要加密
            String md5Decrypt = encrypt.MD5Decrypt(md5Encrypt);
            System.out.println("---------------->" + md5Decrypt);

            shipInfo.setDecoctMedicine(batchSaveFacDTO.getDecoctMedicine());
            shipInfo.setOutpatientNo(presInfo.getOutpatientNo());
            shipInfo.setPatientName(presInfo.getPatientName());
            shipInfo.setInfoRemarks(presInfo.getMethodRemark());
            save(shipInfo);
            count++;
        }
        return count;
    }

    /////////////////////////////////////////////发送/////////////////////////////////////////////

    @Override
    public boolean sendShipInfo(SndShipInfoDTO sndShipInfoDTO) {
        try {
            String res = sendRequest.sendPost(POSTER_URL_POST_NORMAL, JSON.toJSONString(sndShipInfoDTO));
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
