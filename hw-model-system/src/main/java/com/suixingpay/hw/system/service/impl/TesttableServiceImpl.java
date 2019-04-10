package com.suixingpay.hw.system.service.impl;

import java.util.List;

import com.suixingpay.hw.common.core.text.Convert;
import com.suixingpay.hw.system.domain.Testtable;
import com.suixingpay.hw.system.service.ITesttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.suixingpay.hw.system.mapper.TesttableMapper;
import com.suixingpay.hw.system.domain.Testtable;
import com.suixingpay.hw.system.service.ITesttableService;
import com.suixingpay.hw.common.core.text.Convert;

/**
 * 测试 服务层实现
 * 
 * suixingpay
 * @date 2019-03-20
 */
@Service
public class TesttableServiceImpl implements ITesttableService
{
	@Autowired
	private TesttableMapper testtableMapper;

	/**
     * 查询测试信息
     * 
     * @param id 测试ID
     * @return 测试信息
     */
    @Override
	public Testtable selectTesttableById(Integer id)
	{
	    return testtableMapper.selectTesttableById(id);
	}
	
	/**
     * 查询测试列表
     * 
     * @param testtable 测试信息
     * @return 测试集合
     */
	@Override
	public List<Testtable> selectTesttableList(Testtable testtable)
	{
	    return testtableMapper.selectTesttableList(testtable);
	}
	
    /**
     * 新增测试
     * 
     * @param testtable 测试信息
     * @return 结果
     */
	@Override
	public int insertTesttable(Testtable testtable)
	{
	    return testtableMapper.insertTesttable(testtable);
	}
	
	/**
     * 修改测试
     * 
     * @param testtable 测试信息
     * @return 结果
     */
	@Override
	public int updateTesttable(Testtable testtable)
	{
	    return testtableMapper.updateTesttable(testtable);
	}

	/**
     * 删除测试对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTesttableByIds(String ids)
	{
		return testtableMapper.deleteTesttableByIds(Convert.toStrArray(ids));
	}
	
}
