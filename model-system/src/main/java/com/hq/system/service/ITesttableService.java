package com.hq.system.service;

import java.util.List;

import com.hq.system.domain.Testtable;

/**
 * 测试 服务层
 * 
 * hq
 * @date 2019-03-20
 */
public interface ITesttableService 
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
     * 删除测试信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTesttableByIds(String ids);
	
}
