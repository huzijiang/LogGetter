/**
 * 
 */
package com.hq.loggetter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hq.loggetter.domain.Machine;
import com.hq.loggetter.mapper.MachineMapper;
import com.hq.loggetter.service.IMachineService;

/**
 * 
 * 
 * 机器集群信息服务
 * @author huzj
 *
 */
@Service
public class MachineServiceImpl implements IMachineService {
	
	private MachineMapper  machineMapper;
	
	
	/**
	 * 查询机器集群服务信息
	 */
	@Override
	public List<Machine> selectMachines(Machine machine) {
		
		return machineMapper.selectMachineList(machine);
	}

}
