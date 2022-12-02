package cn.calendo.tcmdistribution.dto;

import cn.calendo.tcmdistribution.entity.PresInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RmvPresInfoDTO extends PresInfo {

    private String id;

    private Integer isDeleted;

}
