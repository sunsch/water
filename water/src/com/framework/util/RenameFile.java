package com.framework.util;
import java.io.File;

public class RenameFile 
{
    public static void rename()
    {
        File pics=new File("C:\\Documents and Settings\\Administrator\\Desktop\\bei_shi_da\\pictures110\\pictures110\\");
        if(pics.isDirectory())
        {
            File []ps=pics.listFiles();
            StringBuffer bf=new StringBuffer();
            for(File p:ps)
            {
                String fileName=p.getName();
                String correctResult=fileName.substring(0,1);
                bf.append(correctResult);
                bf.append(",");
                System.out.println(correctResult+"   "+fileName+"   "+p.getParent()+File.separator+fileName.substring(1));
                p.renameTo(new File(p.getParent()+File.separator+"lattice_"+fileName.substring(1)));
                
            }
            System.out.println(bf);
        }
    }
    
    public static void rename1()
    {
    	 File pics=new File("C:\\Documents and Settings\\Administrator\\Desktop\\bei_shi_da\\pictures110\\pictures110\\");
         char[]result=new char[110];
        if(pics.isDirectory())
        {
            File []ps=pics.listFiles();
            StringBuffer bf=new StringBuffer();
            for(File p:ps)
            {
                String fileName=p.getName();
                if(fileName.endsWith("jpg"))
                {
                	String head=fileName.substring(0,1);
                	String num=fileName.substring(2,5);
                	result[Integer.parseInt(num,10)-1]=head.charAt(0);      
                }
            }
            for(char c:result)
            {
            	bf.append("'"+c+"',");
            }
            System.out.println(bf);
        }
    }
    public static void main(String[]args)
    {
        rename();
    }

}

