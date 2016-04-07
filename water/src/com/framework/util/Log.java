package com.framework.util;

import java.io.*;
import java.util.Vector;

public class Log 
{
	public static void console(Object o)
	{
		System.out.println(o.toString());
	}
	public static void warning(Object o)
	{
		System.out.println(
				"BEGIN###########################\n"
				+o.toString()
				+"\nEND###########################\n");
	}

	public static FileOutputStream getfos(String fileName)
	{
		try {
			File f = new File(fileName);
			FileOutputStream o = new FileOutputStream(f,true);
			return o;
		} catch (FileNotFoundException e) 
		{
			System.out.println("Can not find file path: "+fileName);
			e.printStackTrace();
			return null;
		}
	}
	public static PrintWriter getpw(FileOutputStream o)
	{
		PrintWriter pw= new PrintWriter(o);
		return pw;
	}
	
	public static PrintWriter getpw_revise(String fileName)
	{
		
		try {
			return new PrintWriter(new BufferedWriter(new FileWriter(fileName),2048));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedReader getbr(FileReader fr)
	{
		BufferedReader br = new BufferedReader(fr);
		return br;
	}
	
	public static FileReader getfr(String fileName)
	{
		FileReader pw=null;
		try {
			pw = new FileReader(fileName);
		} catch (FileNotFoundException e)
		{
			Log.console("Can not find file path: "+fileName);
			e.printStackTrace();
		}
		return pw;
	}
	
	
	
	
	public static void logVector(Vector<String> m)
	{
		for(String s:m)
		{
			Log.console(s);
		}
	}
	
	public static void logWithoutSepra(PrintWriter pw,String content)
	{
		pw.println(content);
		pw.flush();
	}
	
	public static void close(FileOutputStream fos,PrintWriter pw)
	{
		try {
			fos.close();
			pw.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	public static void close(FileReader fr,BufferedReader br)
	{
		try {
			fr.close();
			br.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	public static void log(String filePathAndName,String content)
	{
		FileOutputStream fos = getfos(filePathAndName);
		PrintWriter pw = getpw(fos);
		pw.println(content);
		close(fos,pw);
	}
	
	


}
