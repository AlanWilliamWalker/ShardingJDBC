package com.example.readwrite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.readwrite.model.TraceLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 主数据追踪日志表 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-05-11
 */
@Mapper
public interface TraceLogMapper extends BaseMapper<TraceLogEntity> {

    int insertLog(TraceLogEntity log);
}
