package com.example.readwrite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.readwrite.model.MdmTraceLogEntity;
import com.example.readwrite.model.MdmTraceLogListRequest;
import com.example.readwrite.model.MdmTraceLogResponse;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 主数据追踪日志记录表 服务类
 * </p>
 *
 * @author ming
 * @since 2022-10-19
 */
public interface IMdmTraceLogService extends IService<MdmTraceLogEntity> {

    PageInfo<MdmTraceLogResponse> queryMdmTraceLogList(MdmTraceLogListRequest req);


}
