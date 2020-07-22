package cn.com.zj.VoideJavaCv;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.FrameGrabber.Exception;

/**
 * 实时视频现场
 * 对摄像头画面进行实时推流操作
 * @author Administrator
 *
 */
public class Voide extends Thread{
	String inputFile;
	String outputFile;
	int v_rs;
	/**
	 * 实时视频现场
	 * 对摄像头画面进行实时推流操作
	 * @author Administrator
	 *
	 */
	public Voide(String inputFile,String outputFile,int v_rs) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
		this.v_rs = v_rs;
	}
	@Override
	public void run() {
		System.out.println("进入线程----");
		// TODO Auto-generated method stub

		Loader.load(opencv_objdetect.class);
		long startTime=0;
		FrameGrabber grabber=null;
		try {
			grabber = FFmpegFrameGrabber.createDefault(inputFile);
			grabber.setFrameRate(v_rs);//获取视频源的参数设置：帧率
			//			grabber.setOption("rtsp_transport", "tcp"); // 使用tcp的方式，不然会丢包很严重 
			grabber.setImageWidth(1080);
			grabber.setImageHeight(720);
		} catch (Exception e2) {
			System.out.println("错误1");
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			grabber.start();
		} catch (Exception e) {
			try {
				grabber.restart();
			} catch (Exception e1) {
				System.out.println("错误2");
				try {
					throw e;
				} catch (Exception e2) {
					System.out.println("错误3");
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
		OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
		Frame grabframe=null;
		try {
			grabframe = grabber.grab();
		} catch (Exception e2) {
			System.out.println("错误4");
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		IplImage grabbedImage =null;
		if(grabframe!=null){
			System.out.println("取到第一帧");
			grabbedImage = converter.convert(grabframe);
		}else{
			System.out.println("没有取到第一帧");
		}
		//如果想要保存图片,可以使用 opencv_imgcodecs.cvSaveImage("hello.jpg", grabbedImage);来保存图片
		//		opencv_imgcodecs.cvSaveImage("hello.jpg", grabbedImage);
		FrameRecorder recorder=null;
		try {
			recorder = FrameRecorder.createDefault(outputFile, 1080, 720);
		} catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
			System.out.println("错误5");
			try {
				throw e;
			} catch (org.bytedeco.javacv.FrameRecorder.Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // avcodec.AV_CODEC_ID_H264
		recorder.setFormat("flv");
		recorder.setFrameRate(v_rs);
		recorder.setGopSize(5);
		System.out.println("["+inputFile+"]准备开始推流...");
		try {
			System.out.println("进入try");
			recorder.start();
		} catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
			System.out.println("错误6");
			try {
				System.out.println("录制器启动失败，正在重新启动...");
				if(recorder!=null)
				{
					System.out.println("尝试关闭录制器");
					recorder.stop();
					System.out.println("尝试重新开启录制器");
					recorder.start();
				}

			} catch (org.bytedeco.javacv.FrameRecorder.Exception e1) {
				System.out.println("错误8");
				try {
					throw e;
				} catch (org.bytedeco.javacv.FrameRecorder.Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
		/**
		 * 文字水印参数
		 */
		// 水印文字位置
		Point point = new Point(10, 50);
		// 颜色，使用黄色
		Scalar scalar = new Scalar(44,238, 238, 0);

		System.out.println("开始推流");
		try {
			while ((grabframe=grabber.grab()) != null) {
				// 取一帧视频（图像）
				Mat mat= null;
				mat = converter.convertToMat(grabframe);
				/**
				 * 给视频加入文字水印
				 */
				Date day=new Date();    
				SimpleDateFormat dfym = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//年月日 时分秒
				// 加文字水印，opencv_imgproc.putText（图片，水印文字，文字位置，字体，字体大小，字体颜色，字体粗度，平滑字体，是否翻转文字）
				//				opencv_imgproc.putText(mat, dfym.format(day), point, opencv_imgproc.CV_FONT_VECTOR0, 1.2, scalar, 1, 20, false);
				opencv_imgproc.putText(mat, dfym.format(day), point, opencv_imgproc.CV_FONT_VECTOR0, 1.6, scalar, 2, 20, false);

				//				System.out.println("推流...");
				grabbedImage = converter.convert(grabframe);
				Frame rotatedFrame = converter.convert(grabbedImage);

				if (startTime == 0) {
					startTime = System.currentTimeMillis();
				}
				recorder.setTimestamp(1000 * (System.currentTimeMillis() - startTime));//时间戳
				if(rotatedFrame!=null){
					recorder.record(rotatedFrame);
				}

				Thread.sleep(0);
			}
		}catch (Exception e) {
			System.out.println("错误9");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
			System.out.println("错误10");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("错误11");
			e.printStackTrace();
		}
		try {
			recorder.stop();
			recorder.release();
		} catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
			System.out.println("错误12");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			grabber.stop();
		} catch (Exception e) {
			System.out.println("错误13");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Reconnection().start();
//		System.exit(2);
		super.run();
	}
}
