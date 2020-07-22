package cn.com.zj.VoideJavaCv;

public class Reconnection extends Thread {
	
	public Reconnection() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		for(int i = 0;i<VoideStart.InNumber;i++){
			while(!PingIp.PingIp((String) VoideStart.In.get(i))){
			}
			String inputFile  = (String) VoideStart.InRtsp.get(i);
			String outputFile="rtmp://"+VoideStart.DestinationIP+":1935/live/in"; 
			new Voide(inputFile, outputFile,25).start();
		}
		for(int i = 0;i<VoideStart.OutNumber;i++){
			while(!PingIp.PingIp((String) VoideStart.Out.get(i))){
			}
			String inputFile  = (String) VoideStart.OutRtsp.get(i);
			String outputFile="rtmp://"+VoideStart.DestinationIP+":1935/live/out"; 
			new Voide(inputFile, outputFile,25).start();
		}
		super.run();
	}
}
