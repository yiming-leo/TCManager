package cn.calendo.tcmdistribution.service.impl;

import cn.calendo.tcmdistribution.dao.PresInfoDao;
import cn.calendo.tcmdistribution.dto.RcvPresInfoDTO;
import cn.calendo.tcmdistribution.dto.RmvPresInfoDTO;
import cn.calendo.tcmdistribution.entity.PresInfo;
import cn.calendo.tcmdistribution.service.IPresInfoService;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PresInfoServiceImpl extends ServiceImpl<PresInfoDao, PresInfo> implements IPresInfoService {

    /////////////////////////////////////////////审核/////////////////////////////////////////////

    @Override
    public boolean checkPresInfo(RcvPresInfoDTO rcvPresInfoDTO) {
        if (rcvPresInfoDTO == null ||
                rcvPresInfoDTO.getId() == null ||
                rcvPresInfoDTO.getPrice() == null ||
                rcvPresInfoDTO.getDoctorId() == null ||
                rcvPresInfoDTO.getUnit() == null ||
                rcvPresInfoDTO.getTransactionDate() == null ||
                rcvPresInfoDTO.getTransactionTime() == null ||
                rcvPresInfoDTO.getOutpatientNo() == null) {
            return false;
        }
        return true;
    }

    /////////////////////////////////////////////分配成功标记/////////////////////////////////////////////

    @Override
    public boolean adoptPresInfoMark(Long id, Integer facNumber) {
        PresInfo presInfo = getById(id);
        presInfo.setIsDistri(facNumber);
        presInfo.setIsDeleted(1);
        return updateById(presInfo);
    }

    /////////////////////////////////////////////增加/////////////////////////////////////////////

    @Override
    public boolean saveRcvPresInfoDTO2PresInfoById(RcvPresInfoDTO rcvPresInfoDTO) {
        PresInfo presInfo = new PresInfo();
        BeanUtil.copyProperties(rcvPresInfoDTO, presInfo, "id");//DTO转储到实体类中
        presInfo.setIsDistri(0);
        presInfo.setIsDeleted(0);
        save(presInfo);//在DB中存入此对象
        return true;
    }

    /////////////////////////////////////////////查询历史/////////////////////////////////////////////

    @Override
    public List<PresInfo> queryHistoryPresInfoAll() {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 1);//条件为删除
        lqw.orderByDesc(PresInfo::getTransactionDate);//按日期降序排列
        lqw.orderByDesc(PresInfo::getTransactionTime);//按时间降序排列
        return list(lqw);
    }

    @Override
    public PresInfo queryHistoryPresInfoById(Long id) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 1);//条件为删除
        lqw.eq(PresInfo::getId, id);
        return getOne(lqw);
    }

    /////////////////////////////////////////////查询/////////////////////////////////////////////

    @Override
    public List<PresInfo> queryPresInfoAll() {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.orderByDesc(PresInfo::getTransactionDate);//按日期降序排列
        lqw.orderByDesc(PresInfo::getTransactionTime);//按时间降序排列
        return list(lqw);
    }

    @Override
    public PresInfo queryPresInfoById(Long id) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.eq(PresInfo::getId, id);
        return getOne(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByTransactionDate(String date) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(PresInfo::getTransactionDate, date);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByTransactionDateBetween(String st, String ed) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.between(PresInfo::getTransactionDate, st, ed);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByTransactionTime(String time) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(PresInfo::getTransactionTime, time);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByTransactionTimeBetween(String st, String ed) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.between(PresInfo::getTransactionTime, st, ed);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByPatientName(String patientName) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(PresInfo::getPatientName, patientName);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByPatientGender(String patientGender) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(PresInfo::getPatientGender, patientGender);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByPatientAge(Integer patientAge) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(PresInfo::getPatientAge, patientAge);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByPatientAgeBetween(Integer st, Integer ed) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.between(PresInfo::getPatientAge, st, ed);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByDoctorName(String doctorName) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(PresInfo::getDoctorName, doctorName);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByDoctorId(String doctorId) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(PresInfo::getDoctorId, doctorId);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByOutpatientNo(String outpatientNo) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(PresInfo::getOutpatientNo, outpatientNo);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByPrice(BigDecimal price) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.like(PresInfo::getPrice, price);//条件为传入的
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByPriceBetween(BigDecimal st, BigDecimal ed) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);//条件为未删除
        lqw.between(PresInfo::getPrice, st, ed);
        return list(lqw);
    }

    /////////////////////////////////////////////移除/////////////////////////////////////////////

    @Override
    public boolean removePresInfoById(RmvPresInfoDTO rmvPresInfoDTO) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getId, rmvPresInfoDTO.getId());//条件为获取到的dto的id
        rmvPresInfoDTO.setIsDeleted(1);//设置isDeleted为1
        return update(rmvPresInfoDTO, lqw);//被设置更新后的实体类对象和查询条件
    }

    @Override
    public boolean foreverRemovePresInfoById(Long id) {
        return false;
    }

    /////////////////////////////////////////////恢复/////////////////////////////////////////////

    @Override
    public boolean repentPresInfoById(RmvPresInfoDTO rmvPresInfoDTO) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getId, rmvPresInfoDTO.getId());//条件为获取到的dto的id
        rmvPresInfoDTO.setIsDeleted(0);//设置isDeleted为0
        return update(rmvPresInfoDTO, lqw);//被设置更新后的实体类对象和查询条件
    }

}
