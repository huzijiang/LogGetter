package com.suixingpay.hw.report.service.impl;

import com.suixingpay.hw.report.domain.EntUserReceiveReportRelation;
import com.suixingpay.hw.report.mapper.EntUserReceiveReportRelationMapper;
import com.suixingpay.hw.report.service.IEntUserReceiveReportRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 企业用户邮箱接收 报告信息关系 Service 实现
 * @author: xu_pf@suixingpay.com
 * @create: 2019-05-16 11:11
 **/
@Service
public class EntUserReceiveReportRelationServiceImpl implements IEntUserReceiveReportRelationService {
    @Autowired
    private EntUserReceiveReportRelationMapper entUserReceiveReportRelationMapper;

    @Override
    public List<EntUserReceiveReportRelation> find(EntUserReceiveReportRelation entUserReceiveReportRelation) {
        return entUserReceiveReportRelationMapper.find(entUserReceiveReportRelation);
    }

    @Override
    public List<EntUserReceiveReportRelation> findByEntReportTempId(Integer entReportTempId) {
        return entUserReceiveReportRelationMapper.findByEntReportTempId(entReportTempId);
    }

    @Override
    public int batchInsert(List<EntUserReceiveReportRelation> entUser3RList) {
        return entUserReceiveReportRelationMapper.batchInsert(entUser3RList);
    }

    @Override
    public int deleteById(Integer id) {
        return entUserReceiveReportRelationMapper.deleteById(id);
    }
}
