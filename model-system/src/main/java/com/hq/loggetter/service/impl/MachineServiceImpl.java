package com.hq.loggetter.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hq.common.core.text.Convert;
import com.hq.loggetter.domain.Machine;
import com.hq.loggetter.mapper.MachineMapper;
import com.hq.loggetter.service.IMachineService;
import com.hq.loggetter.util.CommandUtil;
import com.hq.loggetter.util.MachineElement;

/**
 * 机器 服务层实现
 * 
 * hq
 * @date 2018-06-25
 */
@Service
public class MachineServiceImpl implements IMachineService {
	@Autowired
	private MachineMapper machineMapper;

	/**
	 * 查询机器信息
	 * 
	 * @param machineId
	 * @return 机器信息
	 */
	@Override
	public Machine selectMachineById(Long machineId) {
		return machineMapper.selectMachineById(machineId);
	}

	/**
	 * 查询机器列表
	 * 
	 * @param machine 机器信息
	 * @return List<Machine> 机器信息集合
	 */
	@Override
	public List<Machine> selectMachineList(Machine machine) {
		return machineMapper.selectMachineList(machine);
	}

	/**
	 * 新增机器
	 * 
	 * @param machine 机器信息
	 * @return int
	 */
	@Override
	public int insertMachine(Machine machine) {
		return machineMapper.insertMachine(machine);
	}

	/**
	 * 修改机器
	 * 
	 * @param machine 机器信息
	 * @return int
	 */
	@Override
	public int updateMachine(Machine machine) {
		return machineMapper.updateMachine(machine);
	}

	/**
	 * 删除机器对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return int
	 */
	@Override
	public int deleteMachineByIds(String ids) {
		return machineMapper.deleteMachineByIds(Convert.toStrArray(ids));
	}

	@Override
	public String excuteCommand(Long id) {
		StringBuilder allResult =new StringBuilder();
		/**
		 * 获取机器IP列表,不考虑匹配不上的问题
		 */
		Machine machine = machineMapper.selectMachineById(id);
		System.out.println(machine.getIp());

		String[] elementIPs = machine.getIp().split(",");

		MachineElement[] targetElement = new MachineElement[elementIPs.length];

		ArrayList<MachineElement> allMachineElement = MachineElement.allMachineElement;
		for (int i = 0; i < elementIPs.length; i++) {
			String targetIP = elementIPs[i];
			for (int j = 0; j < allMachineElement.size(); j++) {
				MachineElement machineElement = allMachineElement.get(j);
				if (targetIP.equals(machineElement.getIpAddress())) {
					targetElement[i]=machineElement;
				}else {
					
				}
			}
		}
		System.out.println("目标机器数:"+targetElement.length);
		
		/**
		 * 命令处理,后续可以指定规则,解析含有参数的表达式
		 */
		String commands = machine.getContent();
		System.out.println(commands);
		
		String[] command_elements=commands.replace("<p>", "").replace("&nbsp;", "").replace("<br>", "").split("</p>");
		
		System.out.println("等待执行语句:"+commands);
		/**
		 * 逐个机器执行
		 * 
		 */
		for (int i = 0; i < targetElement.length; i++) {
			MachineElement current = targetElement[i];
			allResult.append("</br>");
			allResult.append("当前机器-").append(current.getIpAddress()).append(":").append("</br>");
			//展示语句
			for (int j = 0; j < command_elements.length; j++) {
				allResult.append(">");
				String string = command_elements[j];
				allResult.append(string).append("</br>");
			}
			System.out.println("******"+CommandUtil.executeLongDistanceCommand(current, command_elements));
			//展示结果
			allResult.append(CommandUtil.executeLongDistanceCommand(current, command_elements)).append("</br>");
		}
		return allResult.toString();
	}
}
