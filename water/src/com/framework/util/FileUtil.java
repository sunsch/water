package com.framework.util;

import java.io.*;

public class FileUtil 
{
	private FileOutputStream fo;
	private PrintWriter pw;
	
	
	public void open(String fileName)
	{
		try {
			fo=new FileOutputStream(fileName);
			pw=new PrintWriter(fo);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void flush()
	{
		pw.flush();
	}
	
	public void close()
	{
		try {
			pw.close();
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String content)
	{
		pw.println(content);
		pw.flush();
	}

}
