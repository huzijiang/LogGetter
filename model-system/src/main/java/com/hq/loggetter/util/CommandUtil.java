package com.hq.loggetter.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

/**
 * 命令执行工具包
 * 
 * @author huzj
 *
 */
public class CommandUtil {

	public static String executeLongDistanceCommand(MachineElement element, String[] commands) {
		// 创建连接
		Connection conn = new Connection(element.getIpAddress(), element.getPort());
		Session session = null;

		String result = "";
		try {
			// 启动连接
			conn.connect();
			// 验证用户密码
			conn.authenticateWithPassword(element.getUserNname(), element.getPassword());
			
			try {
				String command = "";
				for (int i = 0; i < commands.length; i++) {
					session = conn.openSession();
					command = commands[i];
					System.out.println("命令:"+command);
					session.execCommand(command);
					//执行结果
					result+= consumeInputStream(session.getStdout());
					result+= consumeInputStream(session.getStderr());//无用
					result+="</br>";
					
					session.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("单台机器结果:"+result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			conn.close();
		}
		return result;
	}

	/**
	 * 消费inputstream，并返回
	 * 
	 * @throws IOException
	 */
	public static String consumeInputStream(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = br.readLine()) != null) {

			sb.append(s);
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		String[] commands = { "cd /;", "ls;", "pwd;", "ls -a;","java -version;" };

		System.out.println(executeLongDistanceCommand(MachineElement.allMachineElement.get(0), commands));

	}

}