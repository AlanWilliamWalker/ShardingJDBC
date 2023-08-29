package com.example.readwrite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.readwrite.model.TraceLogEntity;


public interface ITraceLogService extends IService<TraceLogEntity> {
    void insert(TraceLogEntity log) throws InterruptedException;
}
