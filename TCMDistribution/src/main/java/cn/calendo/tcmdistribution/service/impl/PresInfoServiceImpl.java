package cn.calendo.tcmdistribution.service.impl;

import cn.calendo.tcmdistribution.dao.PresInfoDao;
import cn.calendo.tcmdistribution.dto.RcvPresInfoDTO;
import cn.calendo.tcmdistribution.dto.RmvPresInfoDTO;
import cn.calendo.tcmdistribution.entity.PresInfo;
import cn.calendo.tcmdistribution.service.IPresInfoService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PresInfoServiceImpl extends ServiceImpl<PresInfoDao, PresInfo> implements IPresInfoService {

    @Value("${constants.presInfoLocation}")
    private String presInfoLocation;

    @Value("${constants.presHisLocation}")
    private String presHisLocation;

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
    public boolean adoptPresInfoMark(String id, Integer facNumber) {
        PresInfo presInfo = getById(id);
        presInfo.setIsDistri(facNumber);
        presInfo.setIsDeleted(1);
        return updateById(presInfo);
    }

    /////////////////////////////////////////////增加/////////////////////////////////////////////

    @Override
    public boolean saveRcvPresInfoDTO2PresInfoById(RcvPresInfoDTO rcvPresInfoDTO) {
        PresInfo presInfo = new PresInfo();
        //DTO转储到实体类中
        BeanUtil.copyProperties(rcvPresInfoDTO, presInfo, "id");
        presInfo.setIsDistri(0);
        presInfo.setIsDeleted(0);
        //在DB中存入此对象
        save(presInfo);
        return true;
    }

    /////////////////////////////////////////////查询历史/////////////////////////////////////////////

    @Override
    public List<PresInfo> queryHistoryPresInfoAll() {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        //条件为删除
        lqw.eq(PresInfo::getIsDeleted, 1);
        //按日期降序排列
        lqw.orderByDesc(PresInfo::getTransactionDate);
        //按时间降序排列
        lqw.orderByDesc(PresInfo::getTransactionTime);
        return list(lqw);
    }

    @Override
    public PresInfo queryHistoryPresInfoById(String id) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        //条件为删除
        lqw.eq(PresInfo::getIsDeleted, 1);
        lqw.eq(PresInfo::getId, id);
        return getOne(lqw);
    }

    /////////////////////////////////////////////查询/////////////////////////////////////////////

    @Override
    public List<PresInfo> queryPresInfoAll() {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        //条件为未删除
        lqw.eq(PresInfo::getIsDeleted, 0);
        //按日期降序排列
        lqw.orderByDesc(PresInfo::getTransactionDate);
        //按时间降序排列
        lqw.orderByDesc(PresInfo::getTransactionTime);
        return list(lqw);
    }

    @Override
    public PresInfo queryPresInfoById(String id) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);
        lqw.eq(PresInfo::getId, id);
        return getOne(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByTransactionDate(String date) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);
        lqw.like(PresInfo::getTransactionDate, date);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByTransactionDateBetween(String st, String ed) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);
        lqw.between(PresInfo::getTransactionDate, st, ed);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByTransactionTime(String time) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);
        lqw.like(PresInfo::getTransactionTime, time);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByTransactionTimeBetween(String st, String ed) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);
        lqw.between(PresInfo::getTransactionTime, st, ed);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByPatientName(String patientName) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);
        lqw.like(PresInfo::getPatientName, patientName);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByPatientGender(String patientGender) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);
        lqw.like(PresInfo::getPatientGender, patientGender);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByPatientAge(Integer patientAge) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);
        lqw.like(PresInfo::getPatientAge, patientAge);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByPatientAgeBetween(Integer st, Integer ed) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);
        lqw.between(PresInfo::getPatientAge, st, ed);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByDoctorName(String doctorName) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PresInfo::getIsDeleted, 0);
        lqw.like(PresInfo::getDoctorName, doctorName);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByDoctorId(String doctorId) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        //条件为未删除
        lqw.eq(PresInfo::getIsDeleted, 0);
        //条件为传入的
        lqw.like(PresInfo::getDoctorId, doctorId);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByOutpatientNo(String outpatientNo) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        //条件为未删除
        lqw.eq(PresInfo::getIsDeleted, 0);
        //条件为传入的
        lqw.like(PresInfo::getOutpatientNo, outpatientNo);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByPrice(BigDecimal price) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        //条件为未删除
        lqw.eq(PresInfo::getIsDeleted, 0);
        //条件为传入的
        lqw.like(PresInfo::getPrice, price);
        return list(lqw);
    }

    @Override
    public List<PresInfo> queryPresInfoByPriceBetween(BigDecimal st, BigDecimal ed) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        //条件为未删除
        lqw.eq(PresInfo::getIsDeleted, 0);
        lqw.between(PresInfo::getPrice, st, ed);
        return list(lqw);
    }

    /////////////////////////////////////////////移除/////////////////////////////////////////////

    @Override
    public boolean removePresInfoById(RmvPresInfoDTO rmvPresInfoDTO) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        //条件为获取到的dto的id
        lqw.eq(PresInfo::getId, rmvPresInfoDTO.getId());
        //设置isDeleted为1
        rmvPresInfoDTO.setIsDeleted(1);
        //被设置更新后的实体类对象和查询条件
        return update(rmvPresInfoDTO, lqw);
    }

    @Override
    public boolean foreverRemovePresInfoById(String id) {
        return false;
    }

    /////////////////////////////////////////////恢复/////////////////////////////////////////////

    @Override
    public boolean repentPresInfoById(RmvPresInfoDTO rmvPresInfoDTO) {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        //条件为获取到的dto的id
        lqw.eq(PresInfo::getId, rmvPresInfoDTO.getId());
        //设置isDeleted为0
        rmvPresInfoDTO.setIsDeleted(0);
        //被设置更新后的实体类对象和查询条件
        return update(rmvPresInfoDTO, lqw);
    }

    /////////////////////////////////////////////导出报表/////////////////////////////////////////////

    @Override
    public void getPresInfoExcel() {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        //条件为未删除
        lqw.eq(PresInfo::getIsDeleted, 0);
        //按日期降序排列
        lqw.orderByDesc(PresInfo::getTransactionDate);
        //按时间降序排列
        lqw.orderByDesc(PresInfo::getTransactionTime);
        List<PresInfo> presInfoList = list(lqw);
        // 通过工具类创建writer "/usr/local/bttomcat/tomcat8/webapps/TCManager/TCMD/PresInfo.xlsx"
        ExcelWriter writer = ExcelUtil.getWriter(presInfoLocation);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(presInfoList, true);
        // 关闭writer，释放内存
        writer.close();
    }

    @Override
    public void getPresHisExcel() {
        LambdaQueryWrapper<PresInfo> lqw = new LambdaQueryWrapper<>();
        //条件为未删除
        lqw.eq(PresInfo::getIsDeleted, 1);
        //按日期降序排列
        lqw.orderByDesc(PresInfo::getTransactionDate);
        //按时间降序排列
        lqw.orderByDesc(PresInfo::getTransactionTime);
        List<PresInfo> presInfoList = list(lqw);
        // 通过工具类创建writer "/usr/local/bttomcat/tomcat8/webapps/TCManager/TCMD/PresInfo.xlsx"
        ExcelWriter writer = ExcelUtil.getWriter(presHisLocation);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(presInfoList, true);
        // 关闭writer，释放内存
        writer.close();
    }

}
