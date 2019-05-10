package com.suixingpay.hw.report.service;

import com.suixingpay.hw.report.domain.ReportInfo;
import com.suixingpay.hw.report.domain.TargetDataInfo;

import java.util.List;
import java.util.Map;

/**
 * @description: 报告管理
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-11 11:02
 **/
public interface IReportManageService {
    /**
     * 获取企业报告信息列表
     * @param reportInfo 报告信息
     * @return  报告信息列表
     */
    List<ReportInfo> selectReportInfoList(ReportInfo reportInfo);

    /**
     * 获取报告指标数据
     * @param reportId 报告编号
     * @return  报告及其指标数据
     */
    List<ReportInfo> selectReportTargetDataList(Integer reportId);

    /**
     * 报告发布
     * @param mapParam [targetPublishInfoId enterpriseTargetDataId content]
     * @return 变更数
     */
    int batchInsertTargetDataPublishInfo(Map<Integer, TargetDataInfo> mapParam);

    /**
     * 更新企业报告发布状态以及发布者
     * @param publisher 发布者
     * @param publishState 发布状态
     * @param enterpriseReportId 报告编号
     * @return  变更数
     */
    int updateReportPublishState(String publisher,String publishState, Integer enterpriseReportId);

    /**
     * 更新报告指标发布状态
     * @param targetDataInfoList 指标信息列表
     * @return 变更数
     */
    int updateEnterpriseTargetDataPublishState(List<TargetDataInfo> targetDataInfoList);

    /**
     * 保存报告发布内容
     * @param reportPublishInfoId   报告发布内容编号
     * @param reportPublishContent  报告发布内容
     * @param enterpriseReportId    报告编号
     * @return  变更数
     */
    int insertEnterpriseReportPublishInfo(Integer reportPublishInfoId,  String reportPublishContent, Integer enterpriseReportId);

    /**
     * 查询企业报告（根据企业编号）
     *
     * @param enterpriseId 企业编号
     * @return List<ReportInfo>
     */
    List<ReportInfo> selectEntReportByEntId(Integer enterpriseId);

    /**
     * 删除：报告发布表
     *
     * @param reportId 报告编号
     */
    void deleteReportPublishInfoByReportId(Integer reportId);

    /**
     * 删除：报告对应的指标发布表
     *
     * @param targetIds 指标编号
     */
    void deleteTargetPublishInfoByTargetIds(List<Integer> targetIds);
}
