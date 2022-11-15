package cn.calendo.tcmdistribution.dao;

import cn.calendo.tcmdistribution.entity.ShipInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 医院给邮政的配送信息报文
 */
@Mapper
public interface ShipInfoDao extends BaseMapper<ShipInfo> {
}
