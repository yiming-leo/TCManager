package cn.calendo.tcmdistribution.service.impl;

import cn.calendo.tcmdistribution.dao.ShipInfoDao;
import cn.calendo.tcmdistribution.entity.ShipInfo;
import cn.calendo.tcmdistribution.service.IShipInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * 医院给邮政的配送信息报文
 */
@Service
public class ShipInfoServiceImpl extends ServiceImpl<ShipInfoDao, ShipInfo> implements IShipInfoService {
    @Override
    public boolean sendShipInfoByInsert(Date transactionDate, Time transactionTime, String recipientName,
                                        String recipientAddress, String recipientTelephone, String postalCode,
                                        String prescriptionNo, String hospitalNo, String pharmaFactoryNo,
                                        String deliveryRequire, String prescriptionInfo, Integer decoctMedicine,
                                        String outpatientNo, String patientName, String remarks, Integer isDel) {

        return false;
    }

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

}
