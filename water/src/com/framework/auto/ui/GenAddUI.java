package com.framework.auto.ui;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Vector;
import com.framework.auto.consts.Consts;
import com.framework.auto.path.Path;
import com.framework.auto.util.DateFormate;
import com.framework.auto.util.EncodUtil;
import com.framework.auto.util.FileUtil;
import com.framework.auto.util.Log;
import com.framework.auto.util.PathUtil;
import com.framework.auto.util.ReflectUtil;

public class GenAddUI 
{
	//generate add page, e.g. addStudents.htm
	public static void createAddUI(String entyObjClzName)
	{

		FileUtil fu=new FileUtil();
		String uidir=Path.WEB_CONTENT_PATH+new File(Path.ENTITY_PATH).getName()+"ui\\";
		fu.createDirIfNotExist(uidir);//create a new ui dir if not exist
		
		if(!fu.checkExist(Path.ENTITY_PATH+entyObjClzName+".java"))
		{
			Log.warning("Add UI.. The java class you specified does not exist: "+Path.ENTITY_PATH+entyObjClzName+", ignored.");
			return;
		}
		String iname="add"+entyObjClzName+fu.calTemplateSuffix();
		String fullName=uidir+iname;
		if(fu.checkExist(fullName))
		{
			Log.warning(iname+" exist, ignored...");
		}else
		{
			Log.console("Generating add ui "+iname+" for "+entyObjClzName+"...");
			fu.createFileIfNotExist(fullName);
			fu.open(fullName);
			appendContent(fu,entyObjClzName,iname,uidir);
			fu.close();
			Log.console("Generating add ui "+iname+" for "+entyObjClzName+" successfully.");
		}
	
	}
	public static void appendScripts(FileUtil fu,String entyObjClzName)
	{
		Vector<Field> visible=ReflectUtil.calVisibles(entyObjClzName);
		fu.write("<script type=\"text/javascript\">");
		fu.write("	jQuery(document).ready(function () {");
		for(Field f:visible)
		{
			if(f.getType().getName().equals("java.util.Date")||f.getType().getName().equals("java.sql.Date"))
			{
				fu.write("		$('#"+f.getName()+"').simpleDatepicker({ startdate: 2008, enddate: 2012 });");
			}
		}
		fu.write("		$(function() {");
		fu.write("		$('#add').click(function(){");
		fu.write("			add();");
		fu.write("		return false;//this is very important. or it will refresh the FF after ajax call");
		fu.write("		 	});");
		fu.write("		 });");
		fu.write("	});");
		fu.addEmptyLine(2);
		fu.write("		  function add()");
		fu.write("		  {");
		for(Field f:visible)
		{
			fu.write("		var "+f.getName()+"=$('#"+f.getName()+"').val();");
		}
		fu.write("			$.ajax({");
		fu.write("			type:\"POST\",");
		fu.write("			async:true,");
		fu.write("			cache: false,");
		fu.write("			//dataType:\"json\",//which is expected from the sever");
		fu.write("			url:\"/"+Path.PROJECT_NAME+"/Add"+entyObjClzName+"Handler\",");
		String data="";
		for(Field f:visible)
		{
			data=data+f.getName()+":"+f.getName()+",";
		}
		if(data.length()==0)//no field to display in the entity
		{
			data=" ";
		}
		data=data.substring(0,data.length()-1);
		fu.write("			data:{"+data+"},");
		fu.write("			beforeSend:function(XMLHttpRequestObject)");
		fu.write("			{");
		fu.write("			$('#overlay').show();");
		fu.write("			$('#processing').show();");
		fu.write(("			$('#processing').html(\"<img height=29px width=29px  src='../image/processing.gif'></img>&nbsp;请求正在处理...\");"));
		//Log.console("			$('#processing').html(\"<img height=29px width=29px  src='../image/processing.gif'></img>我的\");");
		fu.write("			 },");
		fu.write("			success:function(data){");
		fu.write("			$('#overlay').hide();");
		fu.write(("			$('#processing').html(\"<img height=29px width=29px src='../image/check.gif'></img>&nbsp;请求处理成功!\");"));
		fu.write("			},");
		fu.write("			error:function(data){");
		fu.write("			$('#overlay').hide();");
		fu.write(("			$('#processing').html(\"<img height=29px width=29px src='../image/cross.png'></img>&nbsp;请求处理失败! "+"\");"));
		fu.write("			 },");
		fu.write("			complete:function (XMLHttpRequest, textStatus)");
		fu.write("			{");
		fu.write("			//alert(\"complete\"+textStatus);");
		fu.write("			}");
		fu.write(" 		}); ");
		fu.write("		  }");
		fu.write("		  </script>");
		fu.addEmptyLine(4);
			
		
	}
	
	public static void appendForm(FileUtil fu,String entyObjClzName)
	{
		Vector<Field> visible=ReflectUtil.calVisibles(entyObjClzName);
		fu.write("<form id=\"ADD_"+entyObjClzName+"_FORM\">");
		fu.addEmptyLine(1);
		fu.write("<table class=\"ADD_TABLE_ID\">");

		int tdcount=2;
		int trcount=(visible.size()+1)/tdcount;//6+1 /2==3

		for(int tr=0;tr<trcount;tr++)
		{
			//Log.console(fs[i].getName()+fs[i].getType().getName());
			fu.write("	<tr>");
			for(int td=0;td<tdcount;td++)
			{
				if((td+tr*tdcount)<visible.size())
				{
					Field fc=visible.get(td+tr*tdcount);
					if(ReflectUtil.isDate(fc))
					{
						fu.write("		<td>"+ReflectUtil.znValue(fc)+": </td>");
						fu.write("		<td><input id=\""+fc.getName()+"\" class=\"my_input\" name=\""+fc.getName()+"\" type=\"text\" value=\"\"></input><img src='../image/crud/calendar_edit.png'></img></td>");

					}else
					{
						fu.write("		<td>"+ReflectUtil.znValue(fc)+": </td>");
						fu.write("		<td><input id=\""+fc.getName()+"\" class=\"my_input\" name=\""+fc.getName()+"\" type=\"text\" value=\"\"></input></td>");

					}
				}
			}
			fu.write("	</tr>");
		}
		fu.write("	<tr>");
		fu.write("		<td></td>");
		fu.write("		<td><button  id=\"add\"  class=my_button_add ><img src='../image/check.gif'></img> Save</button></td>");
		fu.write("		<td></td>");
		fu.write("		<td><button type=reset id=\"clear_all\" class=my_button_reset ><img src='../image/cancle.png'></img> Reset</button></td>");
		fu.write("	</tr>");
		
		fu.write("</table>");
		fu.write("</form>");
		fu.addEmptyLine(3);
	}
	public static void appendContent(FileUtil fu,String entyObjClzName,String pageName, String uidir)
	{
		fu.openForRead(Path.UI_TEMPLATE_ADD);
		fu.open(uidir+pageName);
		String tem;
		int inForm=0;
		int inScript=0;
		while((tem=fu.readLine())!=null)
		{
			
			if(tem.contains("<form id=\"ADD_FORM_TEMPLATE\">"))
			{
				appendForm(fu,entyObjClzName);
				inForm=1;
			}
			if(tem.contains("<script type=\"text/javascript\">"))
			{
				appendScripts(fu,entyObjClzName);
				inScript=1;
			}
			if(inForm==0&&inScript==0)
			{
				if(tem.contains("<title>Pagination</title>"))
				{
					appendTitle(fu,entyObjClzName);
				}else
				{
					fu.write(tem);
				}
				
			}
			if(tem.contains("</form>"))
			{
				inForm=0;
			}
			if(tem.contains("</script>"))
			{
				inScript=0;
			}
			
			
			
		}
		fu.close();
		fu.closeForRead();
	}
	public static void appendTitle(FileUtil fu,String entyObjClzName)
	{
		fu.write("<title>"+Consts.WEB_PAGE_TITLE+"</title>");
		fu.addEmptyLine(1);
	}
	
	

}
