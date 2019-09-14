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
public interface IMachineService {

	List<Machine> selectMachines(Machine machine);
	
	

}
