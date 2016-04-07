package com.framework.auto.util;

import java.io.*;
import java.util.Date;

import javax.servlet.ServletConfig;

import com.framework.auto.path.Path;


public class FileUtil 
{
	private FileOutputStream fo;
	private OutputStreamWriter os;
	private FileInputStream fi;
	private InputStreamReader is;
	//private PrintWriter pw;
	private BufferedWriter bw;
	//private FileReader fr;
	private BufferedReader br;
	
	private static String baseDir = "/shared/";// 上传文件存储目录
	
	public static String getWebAppPath(ServletConfig sc)
	{
		String saveFilePath = sc.getServletContext().getRealPath("") + baseDir; //文件存储在容器中的绝对路径
		return saveFilePath;
	}
	
	/*
	 *   /shared/courseid/kid/
	 *   kid=-1 is for uploading the items for a course
	 *   kid=0 is for testpaper
	 */
	public static String reCreateItemDirForKid(int courseid, int kid,ServletConfig sc)
	{
		String saveFilePath = getWebAppPath(sc);
		saveFilePath=saveFilePath+courseid+"/";
		File f=new File(saveFilePath);
		if(!f.exists())
		{
			f.mkdirs();
		}
		saveFilePath=saveFilePath+kid+"/";
		if(kid==0)// can be 1 or more test papers for a course
		{
			
		}else
		{
			//DeleteFileUtil.delete(saveFilePath);
		}
		f=new File(saveFilePath);
		f.mkdirs();
		
		return saveFilePath;
	}
	/*
	 *   /shared/courseid/kid_desc/
	 */
	public static String reCreateKidDescriptionDirForKid(int courseid, int kid,ServletConfig sc)
	{
		String saveFilePath = getWebAppPath(sc);
		saveFilePath=saveFilePath+courseid+"/";
		File f=new File(saveFilePath);
		if(!f.exists())
		{
			f.mkdirs();
		}
		saveFilePath=saveFilePath+kid+"_desc/";
		DeleteFileUtil.delete(saveFilePath);
		f=new File(saveFilePath);
		f.mkdirs();
		
		return saveFilePath;
	}
	/*
	 *   /shared/courseid/multi_kid_item/
	 */
	public static String reCreateItemDirForMultiKid(int courseid,ServletConfig sc)
	{
		String saveFilePath = getWebAppPath(sc);
		saveFilePath=saveFilePath+courseid+"/multi_kid_item/";
		File f=new File(saveFilePath);
		if(!f.exists())
		{
			f.mkdirs();
		}
		
		return saveFilePath;
	}
	/*
	 *   /shared/courseid/testpaper_item/
	 */
	public static String reCreateTestpaperItemDirForCid(int courseid,ServletConfig sc)
	{
		String saveFilePath = getWebAppPath(sc);
		saveFilePath=saveFilePath+courseid+"/testpaper_item/";
		File f=new File(saveFilePath);
		if(!f.exists())
		{
			f.mkdirs();
		}
		
		return saveFilePath;
	}
	 //获取文件扩展名
	public static String getFileExtendName(String fileName)
	{
		 String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1); //获取文件扩展名
		 return "." +extensionName;
	}
	
	
	public void openForRead(String fileName)
	{
		try {
			fi=new FileInputStream(fileName);
			is=new InputStreamReader(fi,"UTF-8");
			br = new BufferedReader(is);
		} catch (FileNotFoundException e)
		{
			Log.error("Can not find file path: "+fileName);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String readLine()
	{
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void closeForRead()
	{
		try {
		br.close();
		is.close();
		fi.close();
		} catch (IOException e)
		{
			Log.warning("Error while closing file, in method closeForRead() ");
			e.printStackTrace();
		}
	}
	public boolean deleteFile(String fileName)
	{
		File f=new File(fileName);
		//Log.console("delete file "+fileName);
		return f.delete();
	}
	public void renameTo(String src,String dest)
	{
		File f=new File(src);
		f.renameTo(new File(dest));
	}
	public void backup(String fileName)
	{
		String shortName=new File(fileName).getName();
		String bkfile=new File(fileName).getParent()+"\\backupOf"+shortName;
		deleteFile(bkfile);
		createFileIfNotExist(bkfile);
		
		openForRead(fileName);
		open(bkfile);
		String temp;
		while((temp=readLine())!=null)
		{
			write(temp);
		}
		closeForRead();
		close();
		
	}
	//if not exist, create it
	public void createDirIfNotExist(String dir)
	{
		File file =new File(dir);
		if(!file.exists())
		{
			if(!file.mkdir())
			{
				Log.error("can not make dir:"+dir);
				
			}
		}
	}
	
	//initialize the action package, if not exit create the dirs
	//define a full file name, check if all its parents are exist
	public void createFullDirIfNotExist(String packages)
	{
		String[]dirs=packages.split("\\.");
		int i=dirs.length;
		String d=Path.SRC_PATH;
		for(int j=0;j<i;j++)
		{
			d+=dirs[j]+"\\";
			createDirIfNotExist(d);
		}
	}
	public static boolean createDirs(String fullPath)
	{
		File file =new File(fullPath);
		if(file.exists())
		{
			return true;
		}else
		{
			return file.mkdirs();
		}
	}
	//if not exist, create it
	public void createFileIfNotExist(String f)
	{
		File file =new File(f);
		file.getParentFile().mkdirs();
		if(!file.exists())
		{
			try {
				if(!file.createNewFile())
				{
					Log.error("can not create file:"+f);
					
				}else
				{
					//Log.console("create file "+f);
				}
			} catch (IOException e) {
				Log.error("can not create file:"+f);
				e.printStackTrace();
			}
		}
	}
	public boolean checkExist(String dirOrFile)
	{
		File file =new File(dirOrFile);
		if(file.exists())
			return true;
		return false;
	}
	
	//auto create parent dir and this file (if not exist)
	public void open(String fileName)
	{
		createFileIfNotExist(fileName);
		try {
			fo=new FileOutputStream(fileName,true);
			os=new OutputStreamWriter(fo,"utf-8");//
			bw=new BufferedWriter(os);
			
			
		} catch (FileNotFoundException e) {
			Log.error("can not open file:"+fileName);
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void flush()
	{
		try {
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		try {
			bw.close();
			os.close();
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String content)
	{
		try {
			bw.write(content);
			addEmptyLine(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void writeWithTimeStemp(String content)
	{
		try {
			bw.write(DateFormate.Format(new Date())+"      "+content);
			addEmptyLine(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void addEmptyLine(int newLineCount)
	{
		try {
			//bw.write("\n");
			//bw.newLine();
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getParent(String path)
	{
		File f=new File(path);
		return f.getParent();
	}
	
	public String calTemplateSuffix()
	{
		String suffix=".htm";
		String sr[]=Path.UI_TEMPLATE_ADD.split("\\.");
		if(sr.length>1)
		{
			suffix="."+sr[sr.length-1];
		}
		return suffix;
	}

}
