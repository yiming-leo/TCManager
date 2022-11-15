package cn.calendo.tcmdistribution.dto;

import cn.calendo.tcmdistribution.entity.ShipInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RmvShipInfoDTO extends ShipInfo {

    private Long id;

    private Integer isDeleted;

}
