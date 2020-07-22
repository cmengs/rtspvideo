/*package cn.com.zj.VoideJavaCv;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

*//**
 * spring boot实现启动执行后执行某个方法
 * 实现方法ApplicationRunner
 * 通过这个注解也可以配置运行顺序
 * @author Administrator
 *
 *//*
@Controller
//@Order(value = 1)
public class VoideMiddleOut implements ApplicationRunner{
	
	public int getOrder(){
		return 1;//通过这里的数据来指定顺序
	}
	
	*//**
	 * 需要运行的方法放在这里
	 *//*
	public void run(ApplicationArguments args){

		System.out.println("----------------------------");
		String inputFile = "rtsp://192.168.1.109/1";
//		String outputFile="rtmp://192.168.1.108:1935/live/out";
		String outputFile="rtmp://211.149.149.68:1935/live/out";
		new Voide(inputFile, outputFile,25).start();
		
	}
}
*/