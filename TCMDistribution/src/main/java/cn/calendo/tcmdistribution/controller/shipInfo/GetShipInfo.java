package cn.calendo.tcmdistribution.controller.shipInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.entity.ShipInfo;
import cn.calendo.tcmdistribution.service.IShipInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 查询邮政配药报文接口
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/ship_info/get")
public class GetShipInfo {

    @Autowired
    private IShipInfoService shipInfoService;

    /**
     * 查询所有发送给邮政配药的报文
     *
     * @return R对象
     */
    @PostMapping("/all")
    public R getShipInfoAll() {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoAll();
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据id查询某条报文
     *
     * @param id 报文唯一序列号
     * @return R对象
     */
    @PostMapping("/by_id")
    public R getShipInfoById(@RequestParam(value = "id") Long id) {
        ShipInfo shipInfo = shipInfoService.queryShipInfoById(id);
        if (shipInfo == null) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfo);
    }

    /**
     * 根据交易日期查询某条报文
     *
     * @param transactionDate 报文交易日期
     * @return R对象
     */
    @PostMapping("/by_date")
    public R getShipInfoByDate(@RequestParam(value = "transactionDate") String transactionDate) {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoByDate(transactionDate);
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据交易时间查询某条报文
     *
     * @param transactionTime 报文交易时间
     * @return R对象
     */
    @PostMapping("/by_time")
    public R getShipInfoByTime(@RequestParam(value = "transactionTime") String transactionTime) {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoByTime(transactionTime);
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据收件人姓名查询某条报文
     *
     * @param recipientName 报文收件人姓名
     * @return R对象
     */
    @PostMapping("/by_re_name")
    public R getShipInfoByRecipientName(@RequestParam(value = "recipientName") String recipientName) {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoByRecipientName(recipientName);
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据收件人地址查询某条报文
     *
     * @param recipientAddress 报文收件人地址
     * @return R对象
     */
    @PostMapping("/by_re_address")
    public R getShipInfoByRecipientAddress(@RequestParam(value = "recipientAddress") String recipientAddress) {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoByRecipientAddress(recipientAddress);
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据收件人电话查询某条报文
     *
     * @param recipientTel 报文收件人电话
     * @return R对象
     */
    @PostMapping("/by_re_tel")
    public R getShipInfoByRecipientTel(@RequestParam(value = "recipientTel") String recipientTel) {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoByRecipientTel(recipientTel);
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据邮政编码查询某条报文
     *
     * @param postalCode 报文邮政编码
     * @return R对象
     */
    @PostMapping("/by_postal_code")
    public R getShipInfoByPostalCode(@RequestParam(value = "postalCode") String postalCode) {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoByPostalCode(postalCode);
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据处方号查询某条报文
     *
     * @param prescriptionNo 报文处方号
     * @return R对象
     */
    @PostMapping("/by_pre_no")
    public R getShipInfoByPrescriptionNo(@RequestParam(value = "prescriptionNo") String prescriptionNo) {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoByPrescriptionNo(prescriptionNo);
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据医院编号查询某条报文
     *
     * @param hospitalNo 报文医院编号
     * @return R对象
     */
    @PostMapping("/by_hospital_no")
    public R getShipInfoByHospitalNo(@RequestParam(value = "hospitalNo") String hospitalNo) {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoByHospitalNo(hospitalNo);
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据药厂编号查询某条报文
     *
     * @param pharmaFactoryNo 报文药厂编号
     * @return R对象
     */
    @PostMapping("/by_fac_no")
    public R getShipInfoByPharmaFactoryNo(@RequestParam(value = "pharmaFactoryNo") String pharmaFactoryNo) {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoByPharmaFactoryNo(pharmaFactoryNo);
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据是否煎药查询某条报文
     *
     * @param decoctMedicine 报文是否煎药
     * @return R对象
     */
    @PostMapping("/by_medicine")
    public R getShipInfoByDecoctMedicine(@RequestParam(value = "decoctMedicine") Integer decoctMedicine) {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoByDecoctMedicine(decoctMedicine);
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据门诊号查询某条报文
     *
     * @param outpatientNo 报文门诊号
     * @return R对象
     */
    @PostMapping("/by_outpatient_no")
    public R getShipInfoByOutpatientNo(@RequestParam(value = "outpatientNo") String outpatientNo) {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoByOutpatientNo(outpatientNo);
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据就诊病人姓名查询某条报文
     *
     * @param patientName 报文就诊病人姓名
     * @return R对象
     */
    @PostMapping("/by_pat_name")
    public R getShipInfoByPatientName(@RequestParam(value = "patientName") String patientName) {
        List<ShipInfo> shipInfos = shipInfoService.queryShipInfoByPatientName(patientName);
        if (shipInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

}
