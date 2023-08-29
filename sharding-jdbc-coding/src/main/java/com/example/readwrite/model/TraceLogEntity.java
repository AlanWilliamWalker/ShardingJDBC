package com.example.readwrite.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 主数据追踪日志记录表
 * </p>
 *
 * @author admin
 * @since 2022-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mdm_trace_log")
public class TraceLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("trace_id")
    private Long traceId;

    /**
     * 分发系统编码
     */
    private String targetCode;

    /**
     * 主数据id，全局唯一，bi生成
     */
    private String mdDataId;


    /**
     * 主数据业务编码,业务主键
     */
    private String mdDataCode;

    /**
     * 主数据模型，与表映射
     */
    private String mdModel;

    /**
     * 请求参数json串
     */
    private String param;

    /**
     * 分发状态，1成功，2失败
     */
    private Integer traceStatus;

    /**
     * 分发日志详情json
     */
    private String detailJson;

    /**
     * trace_batch，分发批次
     */
    private String traceBatch;

    /**
     * 跟踪时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date traceTime;

    /**
     * 主数据分发类型：TB，同步；YBQL，异步全量；YBZL，异步增量
     */
    private String dispenseType;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}
