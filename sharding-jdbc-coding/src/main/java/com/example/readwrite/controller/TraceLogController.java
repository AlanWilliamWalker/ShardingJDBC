package com.example.readwrite.controller;

import com.example.readwrite.model.BaseResponse;
import com.example.readwrite.model.MdmTraceLogListRequest;
import com.example.readwrite.model.MdmTraceLogResponse;
import com.example.readwrite.model.TraceLogEntity;
import com.example.readwrite.service.IMdmTraceLogService;
import com.example.readwrite.service.ITraceLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trace")
public class TraceLogController {

    @Autowired
    private ITraceLogService service;

    @Autowired
    private IMdmTraceLogService mdmTraceLogService;

    /**
     * 添加数据分发记录
     */
    @PostMapping("/recordLog")
    public String recordLog(@RequestBody @Validated List<TraceLogEntity> logEntities) throws InterruptedException {
        for (TraceLogEntity log: logEntities ) {
            service.insert(log);
        }
        return "success";
    }

    /**
     * 查询分发日志列表信息
     */
    @PostMapping(value = "/queryMdmTraceLogList")
    @ResponseBody
    public BaseResponse<PageInfo<MdmTraceLogResponse>> queryMdmTraceLogList(@RequestBody MdmTraceLogListRequest req) {
        try {
            PageInfo<MdmTraceLogResponse> pageInfo = mdmTraceLogService.queryMdmTraceLogList(req);
            return BaseResponse.success(pageInfo);
        } catch (Exception e) {
            System.out.println("query mdm trace log page info list error."+e.getMessage());
            return BaseResponse.error("操作失败");
        }
    }
}
