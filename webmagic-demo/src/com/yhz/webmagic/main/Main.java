package com.yhz.webmagic.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/** 
* @ClassName:Main 
* @Description:临时测试 
* @author:hzyuan@iflytek.com 
* @date:2017年3月21日 下午4:58:39 
*  
*/

public class Main {
	public static void testDeleteFile(){
		File file = new File("E:\\xfyun\\andriodmarket\\apk.hiapk.com");
		BufferedReader bufferReader = null;
		try{
			if(file.isDirectory()){
				File[] filelist = file.listFiles();
				System.out.println(filelist.length);
				for(File filestr : filelist){
					bufferReader = new BufferedReader(new FileReader(filestr));
					String tempStr = null;
					int count = 0;
					while ((tempStr = bufferReader.readLine()) != null) {
						if(count >= 2){
							System.out.println(tempStr);
						}
			        	count++;
			        }
					if(count == 1){
						bufferReader.close();
						//filestr.getAbsoluteFile().delete();
						filestr.delete();
					}
				}
				//System.out.println(filelist.length);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void testRegex(){
		/*String s = "1、abc-cdef";
		int index = s.indexOf("-");
		String s1 = s.substring(2, index);
		System.out.println(s1);
		
		Pattern pattern = Pattern.compile("/appdetail/(\\d+)");
		String str = "http://www.cqaso.com/appdetail/1102320385/rank?country=CN";
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.find());
		System.out.println(matcher.group());
		if (matcher.find()){
			System.out.println(matcher.group());
		}*/
		
		String string = "./appdetail/1102320385/rank?country=CN";                     
		String regex = "./appdetail/\\d+/rank\\?country=CN";                           
		System.out.println(string.matches(regex));
		
		String str = "/appinfo/abc.abc.cn";
		String reg = "/appinfo/.{1,}";
		System.out.println(str.matches(reg));
		
	}
	
	public static void testSubstring(){
		String appName = "叽里呱啦-专注儿童英语的幼儿早教启蒙";
		int index = appName.indexOf("-");
		if(index <= 0){
			index = appName.length();
		}
		//System.out.println(index);
		appName = appName.substring(0, index);
		System.out.println(appName);
		
	}
	
	public static void testIndex(){
		String str = "http://zhushou.360.cn/detail/index/soft_id/1784346?recrefer=SE_D_好家长";
		int index = str.lastIndexOf("_");
		String subStr = str.substring(index+1);
		System.out.println(subStr);
	}
	
	public static void testJavaRegex(){
		// 按指定模式在字符串查找
	      String line = "This order was placed for QT3000! OK?";
	      String pattern = "(\\D*)(\\d+)(.*)";
	 
	      // 创建 Pattern 对象
	      Pattern r = Pattern.compile(pattern);
	 
	      // 现在创建 matcher 对象
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	         System.out.println("Found value: " + m.group(0) );
	         System.out.println("Found value: " + m.group(1) );
	         System.out.println("Found value: " + m.group(2) );
	         System.out.println("Found value: " + m.group(3) ); 
	      } else {
	         System.out.println("NO MATCH");
	      }
	      System.out.println(m.groupCount());
	}
	
	public static void main(String[] args) {
		testJavaRegex();
	}
}
