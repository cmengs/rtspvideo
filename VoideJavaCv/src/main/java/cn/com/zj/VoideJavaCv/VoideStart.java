package cn.com.zj.VoideJavaCv;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class VoideStart extends Thread{

	public static String DestinationIP;//remp推送地址
	public static int InNumber;//入口相机数量
	public static int OutNumber;//出口相机数量
	public static ConcurrentMap<Object,Object> InRtsp = new ConcurrentHashMap<Object,Object>();//记录当前入口相机的rtsp地址
	public static ConcurrentMap<Object,Object> OutRtsp = new ConcurrentHashMap<Object,Object>();//记录当前出口相机的rtsp地址
	public static ConcurrentMap<Object,Object> In = new ConcurrentHashMap<Object,Object>();//记录当前入口相机的ip地址
	public static ConcurrentMap<Object,Object> Out = new ConcurrentHashMap<Object,Object>();//记录当前出口相机的ip地址
	public VoideStart() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		System.out.println("Start执行");
		Properties prop = new Properties();
		try {
			prop.load(VoideMiddle.class.getResourceAsStream("/configuration.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DestinationIP = prop.getProperty("DestinationIP");
		InNumber = Integer.valueOf(prop.getProperty("InNumber"));//存储入口相机ip
		OutNumber = Integer.valueOf(prop.getProperty("OutNumber"));//存储入口相机rtsp地址
		for(int i = 0;i<InNumber;i++){
			In.put(i, prop.getProperty("InIp"+i).toString());
			InRtsp.put(i,prop.getProperty("RstpInIp"+i).toString());
			while(!PingIp.PingIp(prop.getProperty("InIp"+i))){
			}
			//			String inputFile = "rtsp://192.168.1.112/1";
			String inputFile  = prop.getProperty("RstpInIp"+i);
			//			String outputFile="rtmp://192.168.1.108:1935/live/in";
			String outputFile="rtmp://"+DestinationIP+":1935/live/in"; 
			new Voide(inputFile, outputFile,2048).start();
		}
		for(int i = 0;i<OutNumber;i++){
			Out.put(i, prop.getProperty("InIp"+i).toString());//存储出口相机ip
			InRtsp.put(i,prop.getProperty("RstpInIp"+i).toString());//存储出口相机rtsp地址
			while(!PingIp.PingIp(prop.getProperty("OutIp"+i))){
			}
			//			String inputFile = "rtsp://192.168.1.112/1";
			String inputFile  = prop.getProperty("RstpOutIp"+i);
			//			String outputFile="rtmp://192.168.1.108:1935/live/in";
			String outputFile="rtmp://"+DestinationIP+":1935/live/out"; 
			new Voide(inputFile, outputFile,2048).start();
		}
		// TODO Auto-generated method stub
		super.run();
	}

}
