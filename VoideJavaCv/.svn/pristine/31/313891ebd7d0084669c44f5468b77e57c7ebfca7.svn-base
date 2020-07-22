/*package cn.com.zj.VoideJavaCv;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;


*//**
 * 启动项目后自动执行的类
 * 设置视频推流的参数
 * @author Administrator
 *
 *//*
@Controller
public class VoideMiddleIn implements ApplicationRunner {

	public int getOrder(){
		return 2;//通过这里的数据来指定顺序
	}
	public void run(ApplicationArguments args){
		Properties prop = new Properties();
		try {
			prop.load(VoideMiddleIn.class.getResourceAsStream("/configuration.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String DestinationIP = prop.getProperty("DestinationIP");
		System.out.println("执行");
		String inputFile = "rtsp://192.168.1.112/1";
//		String outputFile="rtmp://192.168.1.108:1935/live/in";
		String outputFile="rtmp://"+DestinationIP+":1935/live/in"; 
		new Voide(inputFile, outputFile,25).start();
	}

}
*/