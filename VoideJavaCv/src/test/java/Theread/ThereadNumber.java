package Theread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ThereadNumber {
	public static void main(String[] args) {
		
		//判断某个ip是否能ping通
		boolean isIpReachable = false;  
		   while (!isIpReachable)  
		   {  
		       InetAddress address;  
		       try  
		       {  
		           address = InetAddress.getByName("192.168.1.198");  
		           isIpReachable = address.isReachable(3000);  
		           System.out.println("Name: " + address.getHostName());  
		             
		           System.out.println("Addr: " + address.getHostAddress());  
		             
		           System.out.println("isIpReachable: " + isIpReachable);  
		       }   
		       catch (UnknownHostException e)  
		       {  
		           e.printStackTrace();  
		       } catch (IOException e)  
		       {  
		           e.printStackTrace();  
		       }  
		         
		   }  
		   System.out.println("===================================");  
		   
		   
	/*	//获取线程数
		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		while(threadGroup.getParent() != null){
			threadGroup = threadGroup.getParent();
		}
		for (int i = 0; i < 2; i++) {

			new Thread(new Runnable(){
				public void run(){
					while(true){
						}
					}
			}).start();
		}
		new Thread(new Runnable(){
			public void run(){

			}
		}).start();

		int totalThread = threadGroup.activeCount();
		int A = threadGroup.activeGroupCount();
		int B = threadGroup.getMaxPriority();

		System.out.println("当前运行线程数："+totalThread+":"+A+":"+B);*/

	}
}
