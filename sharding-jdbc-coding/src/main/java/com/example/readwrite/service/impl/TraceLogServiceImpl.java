package com.example.readwrite.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.readwrite.mapper.TraceLogMapper;
import com.example.readwrite.model.TraceLogEntity;
import com.example.readwrite.service.ITraceLogService;
import com.example.readwrite.sharding.DynamicDataSourceProviderConfig;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * <p>
 * 主数据追踪日志服务实现
 * </p>
 *
 * @author admin
 * @since 2022-08-02
 */
@Log
@Service
public class TraceLogServiceImpl extends ServiceImpl<TraceLogMapper, TraceLogEntity> implements ITraceLogService {

    @Autowired
    TraceLogMapper mapper;



    @Override
    @DS(DynamicDataSourceProviderConfig.SHARDING_DATA_SOURCE_NAME)
    public void insert(TraceLogEntity log) throws InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.insert(log);
//        for (int i = 5000000;i<5000200;i++) {
//
//            mapper.insert(log);
//            TraceLogEntity entity = new TraceLogEntity();
//            BeanUtils.copyProperties(log,entity);
//            Date date1 = DateUtils.addDays( log.getTraceTime(), 1);
//            log.setId(null);
////            Thread.sleep(1000L);
//            try {
//                Date parse = df.parse(df.format(date1));
//                log.setTraceTime(parse);
//                entity.setTraceTime(parse);
//                mapper.insert(entity);
//            } catch (ParseException e) {
//                System.out.println("aaa");
//            }
//        }

//        for (int i = 0;i<86400;i++) {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            try {
//                Date date = format.parse("2023-08-02 00:00:00");
//                TraceLogEntity entity = new TraceLogEntity();
//                BeanUtils.copyProperties(log,entity);
//                DateUtils.addSeconds(date,1);
//                entity.setTraceTime(date);
//                mapper.insert(log);
//            } catch (ParseException e) {
//                System.out.println(e.getMessage());
//            }
//
//        }

//        mapper.insert(log);
    }
}
