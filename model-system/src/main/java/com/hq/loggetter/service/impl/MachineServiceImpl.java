package com.hq.loggetter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hq.common.core.text.Convert;
import com.hq.loggetter.domain.Machine;
import com.hq.loggetter.mapper.MachineMapper;
import com.hq.loggetter.service.IMachineService;


/**
 * 机器 服务层实现
 * 
 * hq
 * @date 2018-06-25
 */
@Service
public class MachineServiceImpl implements IMachineService
{
    @Autowired
    private MachineMapper machineMapper;

    /**
     * 查询机器信息
     * 
     * @param machineId 
     * @return 机器信息
     */
    @Override
    public Machine selectMachineById(Long machineId)
    {
        return machineMapper.selectMachineById(machineId);
    }

    /**
     * 查询机器列表
     * 
     * @param machine 机器信息
     * @return List<Machine>  机器信息集合
     */
    @Override
    public List<Machine> selectMachineList(Machine machine)
    {
        return machineMapper.selectMachineList(machine);
    }

    /**
     * 新增机器
     * 
     * @param machine 机器信息
     * @return int
     */
    @Override
    public int insertMachine(Machine machine)
    {
        return machineMapper.insertMachine(machine);
    }

    /**
     * 修改机器
     * 
     * @param machine 机器信息
     * @return int 
     */
    @Override
    public int updateMachine(Machine machine)
    {
        return machineMapper.updateMachine(machine);
    }

    /**
     * 删除机器对象
     * 
     * @param ids 需要删除的数据ID
     * @return int 
     */
    @Override
    public int deleteMachineByIds(String ids)
    {
        return machineMapper.deleteMachineByIds(Convert.toStrArray(ids));
    }
}
