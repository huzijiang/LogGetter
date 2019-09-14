/**
 * 
 */
package com.hq.loggetter.mapper;

import java.util.List;

import com.hq.loggetter.domain.Machine;



/**
 * @author huzj
 *
 */
public interface MachineMapper {
	
	
    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<Machine> selectMachineList(Machine machine);
	
	

}
