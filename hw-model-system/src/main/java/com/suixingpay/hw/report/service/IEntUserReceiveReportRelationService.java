package com.suixingpay.hw.report.service;

import com.suixingpay.hw.report.domain.EntUserReceiveReportRelation;

import java.util.List;

/**
 * @description: 企业用户邮箱接收 报告信息关系 Service
 * @author: xu_pf@suixingpay.com
 * @create: 2019-05-16 11:03
 **/
public interface IEntUserReceiveReportRelationService {

    List<EntUserReceiveReportRelation> find(EntUserReceiveReportRelation entUserReceiveReportRelation);

    List<EntUserReceiveReportRelation> findByEntReportTempId(Integer entReportTempId);

    int batchInsert(List<EntUserReceiveReportRelation> entUser3RList);

    int deleteById(Integer id);

}
