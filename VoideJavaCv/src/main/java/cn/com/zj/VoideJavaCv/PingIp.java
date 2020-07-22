package cn.com.zj.VoideJavaCv;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingIp {
	public static boolean PingIp(String ip){
		InetAddress address;  
		boolean isIpReachable = false;
		try {
			address = InetAddress.getByName(ip);
			try {
				 isIpReachable = address.isReachable(3000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return isIpReachable;
	}
}
