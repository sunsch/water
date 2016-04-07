package com.framework.auto.test;

import java.io.*;

import com.framework.auto.util.FileUtil;

public class FileEncode {
	
	public static FileInputStream fi;
	public static InputStreamReader is;
	public static BufferedReader br;
	
	public static FileOutputStream fo;
	public static OutputStreamWriter os;
	public static BufferedWriter bw;
	
	public static void main(String []args)
	{
		testFileUtil();
		//test1();
	}
	
	public static void testFileUtil()
	{
		FileUtil f=new FileUtil();
		f.open("c:\\test.htm");
		try {
			//below will be okay
			f.write("没有区别，只是满足DOS仅能识别8+3的文件名而已，但在有的网站中规定必须用HTM或HTML.一些老的系统bytes的编码方式将由jdk根据操作系统决定。一个一个");
			//below will be okay
			//byte[] 	getBytes()
	        //  Convert this String into bytes according to the platform's default character encoding, storing the result into a new byte array.
			f.write(new String("bytes的编码方式将由jdk根据操作系统决定。一个一个".getBytes()));
			//below will NOT be okay
			//String(byte[] bytes)
	        //  Construct a new String by converting the specified array of bytes using the platform's default character encoding.
			// byte[] 	getBytes(String enc)
	        //  Convert this String into bytes according to the specified character encoding, storing the result into a new byte array.
			f.write(new String("bytes的编码方式将由jdk根据操作系统决定。一个一个".getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f.write("utf8gbk:JDom输出Xml文件，使用字符编码GBK正常，输出UTF-8时乱码..。。。。运行编码为utf-8的php。。。文件如何解决乱码问题？ - PHP - 提供最全...bytes的编码方式将由jdk根据操作系统决定。一个一个");
		
		f.close();
	}
	
	public static void test1()
	{
		try {

			fo=new FileOutputStream("c:\\test.htm");
			os=new OutputStreamWriter(fo,"utf-8");
			bw=new BufferedWriter(os);
			//bw.write(new String("bytes的编码方式将由jdk根据操作系统决定。一个一个".getBytes(),"utf-8"));
			bw.write("没有区别，只是满足DOS仅能识别8+3的文件名而已，但在有的网站中规定必须用HTM或HTML.一些老的系统bytes的编码方式将由jdk根据操作系统决定。一个一个");
			
			bw.write(new String("bytes的编码方式将由jdk根据操作系统决定。一个一个".getBytes()));
			bw.write("utf8gbk:JDom输出Xml文件，使用字符编码GBK正常，输出UTF-8时乱码..。。。。运行编码为utf-8的php。。。文件如何解决乱码问题？ - PHP - 提供最全...bytes的编码方式将由jdk根据操作系统决定。一个一个");
			bw.flush();

			bw.close();
			os.close();
			fo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void testEncoding() 
	{
		//genAll();
		
		FileUtil f=new FileUtil();
		//f.openForRead("c:\\log.htm");
		
		//f.createFileIfNotExist("c:\\log1.txt");
		f.open("c:\\log1.html");
		//f.write(f.readLine());
			f.write(new String("字符串对象中的每一个元素始终占据两个字节长度字符串对象中占据两个字节长度在这些部分之间数据交换的地方，就会出现编码问题	.。。。。。比如，数"));
			
			f.write(new String("字符串对象中的每一个元素始终占据两个字节长度字符串对象中占据两个字节长度在这些部分之间数据交换的地方，就会出现编码问题	.。。。。。比如，数".getBytes()));
		
		f.write("字符串对象中的每一个元素始终占据两个字节长度字符串对象中占据两个字节长度在这些部分之间数据交换的地方，就会出现编码问题	.。。。。。比如，数");
		
		
		f.addEmptyLine(5);
		f.close();
		//f.closeForRead();
		
	}

}
