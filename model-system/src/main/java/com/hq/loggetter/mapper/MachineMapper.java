package com.hq.loggetter.mapper;

import java.util.List;

import com.hq.loggetter.domain.Machine;
import com.hq.system.domain.SysNotice;

/**
 * 公告 数据层
 * 
 * hq
 */
public interface MachineMapper
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public Machine selectMachineById(Long machineId);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<Machine> selectMachineList(Machine machine);

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertMachine(Machine machine);

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateMachine(Machine machine);

    /**
     * 批量删除公告
     * 
     * @param noticeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMachineByIds(String[] machineIds);
}