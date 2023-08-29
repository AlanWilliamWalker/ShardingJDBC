package com.example.readwrite.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.readwrite.mapper.MdmDispenseInfoMapper;
import com.example.readwrite.mapper.MdmTraceLogMapper;
import com.example.readwrite.model.*;
import com.example.readwrite.service.IMdmTraceLogService;
import com.example.readwrite.sharding.DynamicDataSourceProviderConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 主数据追踪日志记录表 服务实现类
 * </p>
 *
 * @author ming
 * @since 2022-10-19
 */
@Service
public class MdmTraceLogServiceImpl extends ServiceImpl<MdmTraceLogMapper, MdmTraceLogEntity> implements IMdmTraceLogService {

    @Autowired
    private MdmTraceLogMapper traceLogMapper;

    @Autowired
    private MdmDispenseInfoMapper dispenseInfoMapper;


    /**
     * @param req 请求参数
     * @return 分页结果
     */
    @Override
    public PageInfo<MdmTraceLogResponse> queryMdmTraceLogList(MdmTraceLogListRequest req) {
        // 分发时间降序
        if (StringUtils.isBlank(req.getOrderType())) {
            req.setOrderType(MagicConstants.TRACE_TIME);
            req.setOrder(MagicConstants.ONE);
        }
        // 参数枚举转义
        if(CollectionUtils.isNotEmpty(req.getMdmModelList())){
            List<String> mdmModelList =
                    req.getMdmModelList().stream().map(model -> Objects.requireNonNull(MdmEnum.getByMdmModel(model)).getMdmModelCode()).collect(Collectors.toList());
            req.setMdmModelList(mdmModelList);
        }
        List<MdmTraceLogResponse> responses;
        List<Long> ids;
        int total;
        if(StringUtils.isBlank(req.getTargetCode()) && CollectionUtils.isEmpty(req.getCompanyName())){
            PageInfo<Long> objectPageInfo =
                    PageHelper.startPage(req.getPageNum(), req.getPageSize()).doSelectPageInfo(() -> {
                        traceLogMapper.queryMdmTraceLogList(req);
                    });
            ids = objectPageInfo.getList();
            total = (int)objectPageInfo.getTotal();
        } else {
            req.setPageNum((req.getPageNum() - MagicConstants.ONE) * req.getPageSize());
            ids = traceLogMapper.queryMdmTraceLogListWithDispense(req);
            total = traceLogMapper.queryMdmTraceLogListWithDispenseTotal(req);
        }
        if(CollectionUtils.isEmpty(ids)){
            PageInfo<MdmTraceLogResponse> page = new PageInfo<>();
            page.setList(new ArrayList<>());
            page.setTotal(0);
            return page;
        }
        responses = traceLogMapper.queryMdmTraceLogListByIds(ids,req.getOrderType(),req.getOrder());
        // 根据业务系统编码&主数据模型获取业务系统名称和事业部名称
        for (MdmTraceLogResponse response: responses) {
            MdmDispenseInfoEntity dispenseInfo = null;
            try {
                dispenseInfo = dispenseInfoMapper.findMdmDispenseInfoByTargetCodeAndMdModel(response.getTargetCode(),
                            response.getMdModel());
            } catch (Exception e) {
                System.out.println("findMdmDispenseInfoByTargetCodeAndMdModel error");
            }
            if(null != dispenseInfo){
                response.setTargetName(dispenseInfo.getTargetName());
                response.setOrgName(dispenseInfo.getCompanyName());
            }
            //主数据类型枚举
            MdmEnum mdmEnum = MdmEnum.getByMdmModelCode(response.getMdModel());
            if(null != mdmEnum){
                response.setMdType(mdmEnum.getMdmType());
                response.setMdModel(mdmEnum.getMdmModel());
            }
            // 分发状态枚举
            String traceStatusDesc = TraceStatusEnum.getDescByCode(response.getTraceStatus());
            response.setTraceStatusDesc(traceStatusDesc);
            // 分发类型枚举
            String descByCode = TraceTypeEnum.getDescByCode(response.getDispenseType());
            response.setDispenseType(descByCode);
            // 分发日志详情 枚举转义 & 加密字段解密
            if(StringUtils.isNotBlank(response.getDetailJson())){
                String json = response.getDetailJson();
                Map<String,Object> map = JSON.parseObject(json, Map.class);
                if(null != mdmEnum && null != map){
                    processResultEnum(mdmEnum.getTableName(), map);
                    response.setDetailJson(JSON.toJSONString(map));
                }
            }
        }
        PageInfo<MdmTraceLogResponse> page = new PageInfo<>();
        page.setList(responses);
        page.setTotal(total);
        return page;
    }


    private void processResultEnum(String tableName, Map<String, Object> map) {

        if(tableName.equals(MdmEnum.MDM_CUSTOMER_INFO.getTableName())){
            if(map.containsKey("sex")){
                String sex = SexEnum.getDescByCode((Integer) map.get("sex"));
                map.put("sex",sex);
            }
            if(map.containsKey("maritalStatus")){
                String maritalStatus = MaritalEnum.getDescByCode((Integer) map.get("maritalStatus"));
                map.put("maritalStatus",maritalStatus);
            }
            if(map.containsKey("industry")){
                String industry = IndustryEnum.getDescByCode((Integer) map.get("industry"));
                map.put("industry",industry);
            }
        }
        if(tableName.equals(MdmEnum.MDM_CUSTOMER_PHONE_INFO.getTableName())){
            if(map.containsKey("isPrimary")){
                String status = PhonePrimaryEnum.getDescByCode((Integer) map.get("isPrimary"));
                map.put("isPrimary",status);
            }
        }
        if(tableName.equals(MdmEnum.MDM_HR_DEPARTMENT_INFO.getTableName())){
            if(map.containsKey("deptStatus")){
                if("active".equals(map.get("deptStatus"))){
                    map.put("deptStatus","可用");
                }else{
                    map.put("deptStatus","不可用");
                }
            }
        }
        if(tableName.equals(MdmEnum.MDM_HR_ORGANIZATION_INFO.getTableName())){
            if(map.containsKey("enabled") ){
                if("Y".equals(map.get("enabled"))){
                    map.put("enabled","可用");
                }else{
                    map.put("enabled","不可用");
                }
            }
        }
        if(tableName.equals(MdmEnum.MDM_HR_POST_INFO.getTableName())){
            if(map.containsKey("xSfmggw")){
                if( "t".equals(map.get("xSfmggw"))){
                    map.put("xSfmggw","是");
                } else {
                    map.put("xSfmggw","否");
                }
            }
            if(map.containsKey("enabled") ){
                if( "Y".equals(map.get("enabled"))){
                    map.put("enabled","可用");
                }else{
                    map.put("enabled","不可用");
                }
            }
        }
        if(tableName.equals(MdmEnum.MDM_HR_STAFF_INFO.getTableName())){
            if(map.containsKey("isProbation") ){
                if("1".equals(map.get("isProbation"))){
                    map.put("isProbation","是");
                }else{
                    map.put("isProbation","否");
                }
            }
            // AES解密 - 证件号码
            if(map.containsKey("nationalId")){
                String nationalId = AesUtil.decrypt((String) map.get("nationalId"));
                String maskPassport = PrivacyDimmerUtils.maskPassport(nationalId);
                map.put("nationalId", maskPassport);
            }
            // AES解密 - 个人手机号码
            if(map.containsKey("xPhoneno")){
                String xPhoneno = AesUtil.decrypt((String) map.get("xPhoneno"));
                String maskMobile = PrivacyDimmerUtils.maskMobile(xPhoneno);
                map.put("xPhoneno", maskMobile);
            }
        }
        if(tableName.equals(MdmEnum.MDM_SUPPLIER_BASIC_FLOW_INFO.getTableName())){
            if(map.containsKey("cooperationCategory")){
                String category = (String) map.get("cooperationCategory");
                String[] splitArray = category.split(",");
                StringBuilder stringBuilder = new StringBuilder();
                for (String split:splitArray) {
                    int cooperationCategoryCode = Integer.parseInt(split);
                    String cooperationCategory = CooperationCategoryEnum.getDescByCode(cooperationCategoryCode);
                    stringBuilder.append(cooperationCategory);
                    stringBuilder.append(",");
                }
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
                map.put("cooperationCategory",stringBuilder.toString());
            }
            if(map.containsKey("supplierType")){
                String supplierType = SupplierTypeEnum.getDescByCode((String) map.get("supplierType"));
                map.put("supplierType",supplierType);
            }
            if(map.containsKey("masterAccount")){
                String masterAccount = SupplierBasicStatusEnum.getDescByCode((String) map.get("masterAccount"));
                map.put("masterAccount",masterAccount);
            }
            if(map.containsKey("supplierStopUseStatus")){
                String supplierStopUseStatus = SupplierBasicStatusEnum.getDescByCode((String) map.get(
                        "supplierStopUseStatus"));
                map.put("supplierStopUseStatus",supplierStopUseStatus);
            }
            if(map.containsKey("signSunshineStatus")){
                String signSunshineStatus = SupplierBasicStatusEnum.getDescByCode((String) map.get(
                        "signSunshineStatus"));
                map.put("signSunshineStatus",signSunshineStatus);
            }
            if(map.containsKey("isolateSupplierStatus")){
                String isolateSupplierStatus = SupplierBasicStatusEnum.getDescByCode((String) map.get(
                        "isolateSupplierStatus"));
                map.put("isolateSupplierStatus",isolateSupplierStatus);
            }
            // AES解密 - 统一信用代码
            if(map.containsKey("unifiedCreditCode")){
                String unifiedCreditCode = AesUtil.decrypt((String) map.get("unifiedCreditCode"));
                String maskPassport = PrivacyDimmerUtils.maskPassport(unifiedCreditCode);
                map.put("unifiedCreditCode", maskPassport);
            }
            // AES解密 - 银行账号
            if(map.containsKey("bankAccount")){
                String bankAccount = AesUtil.decrypt((String) map.get("bankAccount"));
                String maskBankCard = PrivacyDimmerUtils.maskBankCard(bankAccount);
                map.put("bankAccount", maskBankCard);
            }
            // AES解密 - 销售联系人邮箱
            if(map.containsKey("saleEmail")){
                String saleEmail = AesUtil.decrypt((String) map.get("saleEmail"));
                String maskEmail = PrivacyDimmerUtils.maskEmail(saleEmail);
                map.put("saleEmail", maskEmail);
            }
            // AES解密 - 销售联系人手机号码
            if(map.containsKey("salePhone")){
                String salePhone = AesUtil.decrypt((String) map.get("salePhone"));
                String maskMobile = PrivacyDimmerUtils.maskMobile(salePhone);
                map.put("salePhone", maskMobile);
            }
        }
    }


}
