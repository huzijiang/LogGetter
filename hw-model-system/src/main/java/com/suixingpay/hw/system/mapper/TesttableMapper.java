package com.suixingpay.hw.system.mapper;

import com.suixingpay.hw.system.domain.Testtable;
import com.suixingpay.hw.system.domain.Testtable;

import java.util.List;

/**
 * 测试 数据层
 * 
 * suixingpay
 * @date 2019-03-20
 */
public interface TesttableMapper 
{
	/**
     * 查询测试信息
     * 
     * @param id 测试ID
     * @return 测试信息
     */
	public Testtable selectTesttableById(Integer id);
	
	/**
     * 查询测试列表
     * 
     * @param testtable 测试信息
     * @return 测试集合
     */
	public List<Testtable> selectTesttableList(Testtable testtable);
	
	/**
     * 新增测试
     * 
     * @param testtable 测试信息
     * @return 结果
     */
	public int insertTesttable(Testtable testtable);
	
	/**
     * 修改测试
     * 
     * @param testtable 测试信息
     * @return 结果
     */
	public int updateTesttable(Testtable testtable);
	
	/**
     * 删除测试
     * 
     * @param id 测试ID
     * @return 结果
     */
	public int deleteTesttableById(Integer id);
	
	/**
     * 批量删除测试
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTesttableByIds(String[] ids);
	
}