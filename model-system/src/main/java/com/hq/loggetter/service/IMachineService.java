/**
 * 
 */
package com.hq.loggetter.service;

import java.util.List;

import com.hq.loggetter.domain.Machine;

/**
 * @author huzj
 *
 */
public interface IMachineService
{
    /**
     * 查询公告信息
     * 
     * @param machineId 公告ID
     * @return 公告信息
     */
    public Machine selectMachineById(Long machineId);

    /**
     * 查询公告列表
     * 
     * @param machine 公告信息
     * @return 公告集合
     */
    public List<Machine> selectMachineList(Machine machine);

    /**
     * 新增公告
     * 
     * @param machine 公告信息
     * @return 结果
     */
    public int insertMachine(Machine machine);

    /**
     * 修改公告
     * 
     * @param machine 公告信息
     * @return 结果
     */
    public int updateMachine(Machine machine);

    /**
     * 删除公告信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMachineByIds(String ids);
    
    
    
    /**
     * 执行命令
     * @return
     */
    public String excuteCommand(Long id);
}
