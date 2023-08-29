package com.example.readwrite.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 查询主数据分发日志列表请求参数
 *
 * @author ming
 * @since 2022-10-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdmTraceLogListRequest extends PageDto {

    private Long id;

    private String mdmDataId;

    private List<String> mdmModelList;

    private String targetCode;

    private List<String> companyName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date traceTimeStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date traceTimeEnd;

    private String orderType;

    private Integer order;

    private Integer traceStatus;

    private Long traceBatch;
}
