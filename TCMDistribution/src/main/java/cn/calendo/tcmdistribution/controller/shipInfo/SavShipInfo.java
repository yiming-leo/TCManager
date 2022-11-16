package cn.calendo.tcmdistribution.controller.shipInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.entity.ShipInfo;
import cn.calendo.tcmdistribution.service.IShipInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/ship_info")
public class SavShipInfo {

    @Autowired
    private IShipInfoService shipInfoService;

    /**
     * 全字段手动新增一条报文
     * @param transactionDate
     * @param transactionTime
     * @param recipientName
     * @param recipientAddress
     * @param recipientTelephone
     * @param postalCode
     * @param prescriptionNo
     * @param hospitalNo
     * @param deliveryRequire
     * @param prescriptionInfo
     * @param decoctMedicine
     * @param outpatientNo
     * @param patientName
     * @param infoRemarks
     * @return
     */
    @PostMapping("/sav")
    public R saveShipInfo(@RequestParam(value = "transactionDate") String transactionDate,
                          @RequestParam(value = "transactionTime") String transactionTime,
                          @RequestParam(value = "recipientName") String recipientName,
                          @RequestParam(value = "recipientAddress") String recipientAddress,
                          @RequestParam(value = "recipientTelephone") String recipientTelephone,
                          @RequestParam(value = "postalCode") String postalCode,
                          @RequestParam(value = "prescriptionNo") String prescriptionNo,
                          @RequestParam(value = "hospitalNo") String hospitalNo,
                          @RequestParam(value = "pharmaFactoryNo") String pharmaFactoryNo,
                          @RequestParam(value = "deliveryRequire") String deliveryRequire,
                          @RequestParam(value = "prescriptionInfo") String prescriptionInfo,
                          @RequestParam(value = "decoctMedicine") Integer decoctMedicine,
                          @RequestParam(value = "outpatientNo") String outpatientNo,
                          @RequestParam(value = "patientName") String patientName,
                          @RequestParam(value = "infoRemarks") String infoRemarks) {
        ShipInfo shipInfo = new ShipInfo();
        shipInfo.setTransactionDate(java.sql.Date.valueOf(transactionDate));
        shipInfo.setTransactionTime(Time.valueOf(transactionTime));
        shipInfo.setRecipientName(recipientName);
        shipInfo.setRecipientAddress(recipientAddress);
        shipInfo.setRecipientTelephone(recipientTelephone);
        shipInfo.setPostalCode(postalCode);
        shipInfo.setPrescriptionNo(prescriptionNo);
        shipInfo.setHospitalNo(hospitalNo);
        shipInfo.setPharmaFactoryNo(pharmaFactoryNo);
        shipInfo.setDeliveryRequire(deliveryRequire);
        shipInfo.setPrescriptionInfo(prescriptionInfo);
        shipInfo.setDecoctMedicine(decoctMedicine);
        shipInfo.setOutpatientNo(outpatientNo);
        shipInfo.setPatientName(patientName);
        shipInfo.setInfoRemarks(infoRemarks);

        boolean res = shipInfoService.saveShipInfo(shipInfo);
        if (!res) {
            return R.error(404, "新增失败", new Date());
        }
        return R.success(200, "新增成功", new Date());
    }

}
