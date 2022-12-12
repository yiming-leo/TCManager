package cn.calendo.tcmdistribution.controller.presInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.entity.PresInfo;
import cn.calendo.tcmdistribution.service.IPresInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 查询处方接口
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/pres_info/get")
public class GetPresInfo {

    @Autowired
    private IPresInfoService presInfoService;

    /**
     * 查询所有审核通过的处方
     *
     * @return R对象
     */
    @GetMapping("/all")
    public R getPresInfoAll() {
        List<PresInfo> presInfos = presInfoService.queryPresInfoAll();
        if (presInfos.size() == 0) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据id查询某条审核通过的处方
     *
     * @param id 审核通过的处方唯一序列号
     * @return R对象
     */
    @GetMapping("/by_id")
    public R getPresInfoById(@RequestParam(value = "id") String id) {
        PresInfo presInfo = presInfoService.queryPresInfoById(id);
        if (presInfo == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfo);
    }

    /**
     * 根据date查询某条审核通过的处方
     *
     * @param date 审核通过的处方唯一序列号
     * @return R对象
     */
    @GetMapping("/by_date")
    public R getPresInfoByTransactionDate(@RequestParam(value = "date") String date) {
        List<PresInfo> presInfos = presInfoService.queryPresInfoByTransactionDate(date);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据date区间查询某条审核通过的处方
     *
     * @param st 起始日期
     * @param ed 终止日期
     * @return R对象
     */
    @PostMapping("/by_date_bt")
    public R getPresInfoByTransactionDateBetween(@RequestParam(value = "dateSt") String st, @RequestParam(value = "dateEd") String ed) {
        List<PresInfo> presInfos = presInfoService.queryPresInfoByTransactionDateBetween(st, ed);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据time查询某条审核通过的处方
     *
     * @param time 审核通过的处方唯一序列号
     * @return R对象
     */
    @GetMapping("/by_time")
    public R getPresInfoByTransactionTime(@RequestParam(value = "time") String time) {
        List<PresInfo> presInfos = presInfoService.queryPresInfoByTransactionTime(time);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据time区间查询某条审核通过的处方
     *
     * @param st 起始时间
     * @param ed 终止时间
     * @return R对象
     */
    @PostMapping("/by_time_bt")
    public R getPresInfoByTransactionTimeBetween(@RequestParam(value = "timeSt") String st, @RequestParam(value = "timeEd") String ed) {
        List<PresInfo> presInfos = presInfoService.queryPresInfoByTransactionTimeBetween(st, ed);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据patientName查询某条审核通过的处方
     *
     * @param patientName 审核通过的处方唯一序列号
     * @return R对象
     */
    @GetMapping("/by_patient_name")
    public R getPresInfoByPatientName(@RequestParam(value = "patientName") String patientName) {
        List<PresInfo> presInfos = presInfoService.queryPresInfoByPatientName(patientName);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据patientGender查询某条审核通过的处方
     *
     * @param patientGender 审核通过的处方唯一序列号
     * @return R对象
     */
    @GetMapping("/by_patient_gender")
    public R getPresInfoByPatientGender(@RequestParam(value = "patientGender") String patientGender) {
        List<PresInfo> presInfos = presInfoService.queryPresInfoByPatientGender(patientGender);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据patientAge查询某条审核通过的处方
     *
     * @param patientAge 审核通过的处方唯一序列号
     * @return R对象
     */
    @GetMapping("/by_patient_age")
    public R getPresInfoByDoctorAge(@RequestParam(value = "patientAge") Integer patientAge) {
        List<PresInfo> presInfos = presInfoService.queryPresInfoByPatientAge(patientAge);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据patientAge区间查询某条审核通过的处方
     *
     * @param st 起始年龄
     * @param ed 终止年龄
     * @return R对象
     */
    @PostMapping("/by_patient_age_bt")
    public R getPresInfoByDoctorAgeBetween(@RequestParam(value = "ageSt") Integer st, @RequestParam(value = "ageEd") Integer ed) {
        List<PresInfo> presInfos = presInfoService.queryPresInfoByPatientAgeBetween(st, ed);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据doctorName查询某条审核通过的处方
     *
     * @param doctorName 审核通过的处方唯一序列号
     * @return R对象
     */
    @GetMapping("/by_doctor_name")
    public R getPresInfoByDoctorName(@RequestParam(value = "doctorName") String doctorName) {
        List<PresInfo> presInfos = presInfoService.queryPresInfoByDoctorName(doctorName);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据doctorId查询某条审核通过的处方
     *
     * @param doctorId 医生ID
     * @return R对象
     */
    @GetMapping("/by_doctor_id")
    public R getPresInfoByDoctorId(@RequestParam(value = "doctorId") String doctorId) {
        List<PresInfo> presInfos = presInfoService.queryPresInfoByDoctorId(doctorId);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据outpatientNo查询某条审核通过的处方
     *
     * @param outpatientNo 门诊号
     * @return R对象
     */
    @GetMapping("/by_outpatient_no")
    public R getPresInfoByOutpatientNo(@RequestParam(value = "outpatientNo") String outpatientNo) {
        List<PresInfo> presInfos = presInfoService.queryPresInfoByOutpatientNo(outpatientNo);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据price查询某条审核通过的处方
     *
     * @param price 价格
     * @return R对象
     */
    @GetMapping("/by_price")
    public R getPresInfoByPrice(@RequestParam(value = "price") Double price) {
        BigDecimal bigDecimal = BigDecimal.valueOf(price);
        List<PresInfo> presInfos = presInfoService.queryPresInfoByPrice(bigDecimal);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据price区间查询某条审核通过的处方
     *
     * @param st 起始价格
     * @param ed 终止价格
     * @return R对象
     */
    @PostMapping("/by_price_bt")
    public R getPresInfoByPriceBetween(@RequestParam(value = "priceSt") Double st, @RequestParam(value = "priceEd") Double ed) {
        BigDecimal bigDecimalSt = BigDecimal.valueOf(st);
        BigDecimal bigDecimalEd = BigDecimal.valueOf(ed);
        List<PresInfo> presInfos = presInfoService.queryPresInfoByPriceBetween(bigDecimalSt, bigDecimalEd);
        if (presInfos == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), presInfos);
    }

}
