package cn.calendo.tcmdistribution.dao;

import cn.calendo.tcmdistribution.entity.PresInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 中药房接收医生站的处方信息
 */
@Mapper
public interface PresInfoDao extends BaseMapper<PresInfo> {
}
