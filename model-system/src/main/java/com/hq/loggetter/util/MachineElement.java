package com.hq.loggetter.util;

import java.util.ArrayList;

import lombok.Data;

/**
 * 
 * @author huzj
 *
 */
@Data
public class MachineElement {

	public static ArrayList<MachineElement> allMachineElement = new ArrayList<MachineElement>();

	/**
	 * ip
	 */
	private String ipAddress;

	/**
	 * 端口号
	 */
	private int port;

	/**
	 * 账号
	 */
	private String UserNname;

	/**
	 * 密码
	 */
	private String password;

	static {
		MachineElement  element = new MachineElement();
						element.setIpAddress("47.92.149.107");
						element.setPort(22);
						element.setUserNname("root");
						element.setPassword("Hu100200");
		allMachineElement.add(element);
	}

}
