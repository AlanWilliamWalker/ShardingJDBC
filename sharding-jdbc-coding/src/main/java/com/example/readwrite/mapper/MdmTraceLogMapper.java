package com.example.readwrite.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.readwrite.model.MdmTraceLogEntity;
import com.example.readwrite.model.MdmTraceLogListRequest;
import com.example.readwrite.model.MdmTraceLogResponse;
import com.example.readwrite.sharding.DynamicDataSourceProviderConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 主数据追踪日志记录表 Mapper 接口
 * </p>
 *
 * @author ming
 * @since 2022-10-19
 */
@Mapper
@Repository
@DS(DynamicDataSourceProviderConfig.SHARDING_DATA_SOURCE_NAME)
public interface MdmTraceLogMapper extends BaseMapper<MdmTraceLogEntity> {

    List<Long> queryMdmTraceLogList(@Param("req") MdmTraceLogListRequest req);

    List<Long> queryMdmTraceLogListWithDispense(@Param("req") MdmTraceLogListRequest req);

    int queryMdmTraceLogListWithDispenseTotal(@Param("req") MdmTraceLogListRequest req);

    List<MdmTraceLogResponse> queryMdmTraceLogListByIds(@Param("idList") List<Long> idList,
                                                        @Param("orderType") String orderType,
                                                        @Param("order") Integer order);

    List<String> queryOverViewMdmDataRateTrace(@Param("mdmModelCode") String mdmModelCode,
                                               @Param("startOfDay") Date startOfDay, @Param(
            "endOfDay") Date endOfDay);

    Integer queryOverViewMdmDataRateTraceTotal(@Param("mdmModelCode") String mdmModelCode,
                                                    @Param("targetCode") String targetCode,
                                                    @Param("startOfDay") Date startOfDay, @Param(
            "endOfDay") Date endOfDay);

    List<String> queryDetailJsonByModelCode(@Param("mdmModelCode") String mdmModelCode,
                                            @Param("startOfDay") Date startOfDay, @Param(
            "endOfDay") Date endOfDay);

}
