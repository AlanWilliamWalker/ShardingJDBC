package com.example.readwrite.alg;

import com.example.readwrite.model.RedisUtil;
import groovy.util.logging.Slf4j;
import org.apache.shardingsphere.sharding.spi.KeyGenerateAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TraceLogByRedisKeyGenerator  implements KeyGenerateAlgorithm {

    @Autowired
    RedisUtil redisUtil;
    @Override
    public String getType() {
        // 返回算法类型表示
        return "CUSTOM";
    }

    @Override
    public void init() {
        // 这里可以进行必要的初始化
    }

    /**
     * 生成18位订单编号:8位日期+7位以上自增id
     * @return
     */
    @Override
    public Long generateKey() {
//        return 5222156521L;
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = "trace:log:id:prefix:" + date;
        //增长值
        Long increment = null;
        if (redisUtil.hasKey(key)) {
            increment = redisUtil.incr(key, 1);
        } else {
            increment = redisUtil.incr(key, 1);
            redisUtil.expire(key, 86400);
        }
        sb.append(date);
        String incrementStr = increment.toString();
        if (incrementStr.length() <= 7) {
            sb.append(String.format("%07d", increment));
        } else {
            sb.append(incrementStr);
        }
        return Long.parseLong(sb.toString());
    }


}
