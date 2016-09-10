package com.cn.tonyou.utils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;
@SuppressWarnings("restriction")
@Controller
public class UploadImage {
	 
	/**
	 * 文件到服务器上
	 */
	private String savePath = "/upload";
	
	
	/**
	 * 获取相对路径
	 * @param req
	 * @return
	 */
	public String getSavePath(HttpServletRequest req) {

	     //file:/D:/JavaWeb/.metadata/.me_tcat/webapps/TestBeanUtils/WEB-INF/classes/   
	     String path=Thread.currentThread().getContextClassLoader().getResource("").toString();  
	     path=path.replace('/', '\\'); // 将/换成\  
	     path=path.replace("file:", ""); //去掉file:  
	     path=path.replace("classes\\", ""); //去掉class\  
	     path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...  
	     path+="server.xml";  
	     System.out.println(path);  

		return path+savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getImgPath(HttpServletRequest req) {
		String realPath = req.getSession().getServletContext().getRealPath("/");
		return realPath+"upload\\";
	}
	 
	/** 
	 * 商品信息的图片提交 – 只保存文件到服务器上  (upload路徑下)
	 * 
	 * @throws Exception 
	 */  
	public String goodImage(@RequestParam("uploadFile") MultipartFile[] uploadFile,HttpServletRequest req) throws Exception {  
		String path = ""; 
		//判断上传文件保存的路径是否存在，如果不存则创建  savePathDir.exists()
		File savaPathDir = new File(getSavePath(req));
		if(!savaPathDir.exists()){
			savaPathDir.mkdirs();
		}
		for(int i = 0; i<uploadFile.length;i++){ 
		    String filename =  UUID.randomUUID() + uploadFile[i].getOriginalFilename(); 
			path = path + filename;
		    InputStream fis = uploadFile[i].getInputStream();  
		    // 开始保存文件到服务器  
		    FileOutputStream fos = new FileOutputStream(getSavePath(req) +File.separator+  filename);  
		    byte[] buffer = new byte[fis.available()]; // 每次读8K字节  
		    int count = 0;  
		    // 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中  
		    while ((count = fis.read(buffer)) > 0) {  
		        fos.write(buffer, 0, count); // 向服务端文件写入字节流  
		     }  
		    fos.close(); // 关闭FileOutputStream对象  
		    fis.close(); // InputStream对象  
		}  
		return path;
	}  
	/** 
	 * 新增 - 提交 – 只保存文件到服务器上  (upload路徑下)
	 * 
	 * @throws Exception 
	 */  
	 
	public String getImageForBase64(String image,HttpServletRequest req) throws Exception {  
		String encodeBase64 = "";
		String path = "D://userIcon//";
		if (image.equals("") || image == null) {
			image = "defalut.png";
		}
		String imgFile = path + image;// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		encodeBase64 = encoder.encode(data);
		return encodeBase64;// 返回Base64编码过的字节数组字符串
	} 
	/** 
	 * 新增 - 提交 – 
	 * 
	 * @throws Exception 
	 */  
	 
	public String oneImage(String stuId,String image,HttpServletRequest req) throws Exception {  
		String imageName = "";
		String imagePath = "";
		String path = "D://userIcon//";
        // 接收图片数据   （base64）
        
		//判断上传文件保存的路径是否存在，如果不存则创建  savePathDir.exists()
		File savaPathDir = new File(path);
		if(!savaPathDir.exists()){
			savaPathDir.mkdirs();
		}
		// 将base64 转 字节数组
        Base64 base = new Base64(); 
        byte[] decode = base.decode(image);
        imageName = UUID.randomUUID()+""+ System.currentTimeMillis()+ "-"+ stuId + ".png";
        // 图片输出路径
        imagePath =  path + imageName;
        // 定义图片输入流
        InputStream fin = new ByteArrayInputStream(decode);
        // 定义图片输出流
		FileOutputStream fout=new FileOutputStream(imagePath);
        // 写文件
        byte[] b=new byte[1024];
        int length=0;
        while((length=fin.read(b))>0){
            fout.write(b, 0, length);
        }

	 return imageName;
	} 
	/** 
	 * 删除图片– 
	 * 
	 * @throws Exception 
	 */  
	 
	public boolean delegeImage(String imageName){  
		String imagePath = "";
		String path = "D://userIcon//";

        imagePath =  path + imageName;
        File file = new File(imagePath);
     // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if(file.exists() && file.isFile()) {
        	if(file.delete()) {
        		System.out.println("删除单个文件" + imagePath + "成功！");
        		return true;
        	} else {
        		System.out.println("删除单个文件" + imagePath + "失败！");
        		return false;
        	}
        } else {
        	System.out.println("删除单个文件失败：" + imagePath + "不存在！");
        	return false;
        }


	} 
	
	/** 
	 * 新增 - 提交 – 只保存文件到服务器上  (upload路徑下)
	 * 
	 * @throws Exception 
	 */  
	 
	public String moreImage(@RequestParam("uploadFile") MultipartFile[] uploadFile,HttpServletRequest req) throws Exception {  
		String path = "";
		//判断上传文件保存的路径是否存在，如果不存则创建  savePathDir.exists()
		File savaPathDir = new File(getSavePath(req));
		if(!savaPathDir.exists()){
			savaPathDir.mkdirs();
		}
		for(int i = 0; i<uploadFile.length;i++){
		    String filename =  UUID.randomUUID() + uploadFile[i].getOriginalFilename(); 
			path = path +","+filename;
		    InputStream fis = uploadFile[i].getInputStream();  
		    // 开始保存文件到服务器  
		    FileOutputStream fos = new FileOutputStream(getSavePath(req) +File.separator+  filename);  
		    byte[] buffer = new byte[fis.available()]; // 每次读8K字节  
		    int count = 0;
		    // 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中  
		    while ((count = fis.read(buffer)) > 0) {  
		        fos.write(buffer, 0, count); // 向服务端文件写入字节流  
		     }  
		    fos.close(); // 关闭FileOutputStream对象  
		    fis.close(); // InputStream对象  
		}  
	 return path;
	}  
	
	
	/** 
	 * 新增 - 提交 – 只保存文件到本地 如：
	 * saveMyPath = "d:\\temp_upload_file\\";  
	 * @throws Exception 
	 */  
	 
	public String moreImage(@RequestParam("uploadFile") MultipartFile[] uploadFile,HttpServletRequest req,String saveMyPath) throws Exception {  
		String path = ""; 
		File savaPathDir = new File(saveMyPath);
		if(!savaPathDir.exists()){
			savaPathDir.mkdirs();
		}
		for(int i = 0; i<uploadFile.length;i++){ 
		    String filename =  UUID.randomUUID() + uploadFile[i].getOriginalFilename(); 
			path = path +","+filename;
		    InputStream fis = uploadFile[i].getInputStream();  
		    // 开始保存文件到服务器  
		    FileOutputStream fos = new FileOutputStream(saveMyPath +  filename);  
		    byte[] buffer = new byte[fis.available()]; // 每次读8K字节  
		    int count = 0;  
		    // 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中  
		    while ((count = fis.read(buffer)) > 0) {  
		        fos.write(buffer, 0, count); // 向服务端文件写入字节流  
		     }  
		    fos.close(); // 关闭FileOutputStream对象  
		    fis.close(); // InputStream对象  
		}  
	 return path;
	}  
	
	
	
	
}	
	
	 
	
