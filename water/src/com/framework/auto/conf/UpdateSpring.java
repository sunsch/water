package com.framework.auto.conf;

import com.framework.auto.path.Path;
import com.framework.auto.util.FileUtil;
import com.framework.auto.util.Log;
import com.framework.auto.util.PathUtil;

public class UpdateSpring 
{
	public static void update(String entyObjClzName)
	{
		FileUtil fu=new FileUtil();
		
		fu.createDirIfNotExist(Path.ENTITY_PATH);//create a new entity dir if not exist
		if(!fu.checkExist(Path.ENTITY_PATH+entyObjClzName+".java"))
		{
			Log.warning("The java class you specified does not exist: "+Path.ENTITY_PATH+entyObjClzName+", ignored.");
			return;
		}
		
		//backup(fu);
		String tempf=Path.CONFIG_PATH+"tempspring.xml";
		fu.createFileIfNotExist(tempf);
		fu.openForRead(Path.SPRING_CONFIG_FILE);
		fu.open(tempf);
		String te;
		int daoProcessed=0;
		int transactionProxyProcessed=0;
		while((te=fu.readLine())!=null)
		{
			if(te.contains("<bean id=\""+entyObjClzName+"Dao\" "))
			{
				daoProcessed=1;
				Log.console("<bean id=\""+entyObjClzName+"Dao\" > exist, ignored..");
			}
			if(te.contains("<bean id=\""+entyObjClzName+"DaoTxProxy\" parent=\"homeTxProxy\">"))
			{
				transactionProxyProcessed=1;
				Log.console("<bean id=\""+entyObjClzName+"DaoTxProxy\" parent=\"homeTxProxy\"> exist, ignored..");
			}
			if(te.contains("<!--ENTITY DAO END HERE.")&&(daoProcessed==0))
			{
				fu.write("    <bean id=\""+entyObjClzName+"Dao\" class=\""+PathUtil.calPackage(Path.ENTITY_PATH)+"."+entyObjClzName+"Dao\" >");
				fu.write("      <property name=\"sessionFactory\">");
				fu.write("    		<ref bean=\"sessionFactory\"  />");
				fu.write("      </property>");
				fu.write("    </bean>");
				Log.console("add <bean id=\""+entyObjClzName+"Dao\" class=\""+PathUtil.calPackage(Path.ENTITY_PATH)+"."+entyObjClzName+"Dao\" > successfully");
				daoProcessed=1;
			}
			if(te.contains("<!--TRANSACTION PROXY END HERE.")&&(transactionProxyProcessed==0))
			{
				fu.write(" 	<bean id=\""+entyObjClzName+"DaoTxProxy\" parent=\"homeTxProxy\">");
				fu.write("       <property name=\"transactionManager\"> ");
				fu.write("    		<ref bean=\"transactionManager\"  />");
				fu.write("      </property>");
				fu.write("		<property name=\"target\" ref=\""+entyObjClzName+"Dao\"></property> ");
				fu.write("  </bean>");
				Log.console("add <bean id=\""+entyObjClzName+"DaoTxProxy\" parent=\"homeTxProxy\"> successfully");
				transactionProxyProcessed=1;
			}
			if(te.contains("</beans>")&&(daoProcessed==0))//end of spring config file no comment in it.
			{
				fu.write("    <bean id=\""+entyObjClzName+"Dao\" class=\""+PathUtil.calPackage(Path.ENTITY_PATH)+"."+entyObjClzName+"Dao\" >");
				fu.write("      <property name=\"sessionFactory\">");
				fu.write("    		<ref bean=\"sessionFactory\"  />");
				fu.write("      </property>");
				fu.write("    </bean>");
				Log.console("add <bean id=\""+entyObjClzName+"Dao\" class=\""+PathUtil.calPackage(Path.ENTITY_PATH)+"."+entyObjClzName+"Dao\" > successfully");
				daoProcessed=1;
			}
			if(te.contains("</beans>")&&(transactionProxyProcessed==0))//end of spring config file no comment in it.
			{
				fu.write(" 	<bean id=\""+entyObjClzName+"DaoTxProxy\" parent=\"homeTxProxy\">");
				fu.write("       <property name=\"transactionManager\"> ");
				fu.write("    		<ref bean=\"transactionManager\"  />");
				fu.write("      </property>");
				fu.write("		<property name=\"target\" ref=\""+entyObjClzName+"Dao\"></property> ");
				fu.write("  </bean>");
				Log.console("add <bean id=\""+entyObjClzName+"DaoTxProxy\" parent=\"homeTxProxy\"> successfully");
				transactionProxyProcessed=1;
			}
			fu.write(te);
		}
		fu.close();
		fu.closeForRead();
		
		fu.deleteFile(Path.SPRING_CONFIG_FILE);
		fu.renameTo(tempf, Path.SPRING_CONFIG_FILE);
		
	}
	public static void backup(FileUtil fu)
	{
		fu.backup(Path.SPRING_CONFIG_FILE);
	}

}
