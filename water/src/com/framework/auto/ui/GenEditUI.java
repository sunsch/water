package com.framework.auto.ui;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Vector;

import com.framework.auto.consts.Consts;
import com.framework.auto.path.Path;
import com.framework.auto.util.FileUtil;
import com.framework.auto.util.Log;
import com.framework.auto.util.ReflectUtil;

public class GenEditUI
{

	//generate add page, e.g. editStudents.htm
	public static void createEditUI(String entyObjClzName)
	{

		FileUtil fu=new FileUtil();
		String uidir=Path.WEB_CONTENT_PATH+new File(Path.ENTITY_PATH).getName()+"ui\\";
		fu.createDirIfNotExist(uidir);//create a new ui dir if not exist
		
		if(!fu.checkExist(Path.ENTITY_PATH+entyObjClzName+".java"))
		{
			Log.warning("Edit UI.. The java class you specified does not exist: "+Path.ENTITY_PATH+entyObjClzName+", ignored.");
			return;
		}
		String iname="edit"+entyObjClzName+fu.calTemplateSuffix();
		String fullName=uidir+iname;
		if(fu.checkExist(fullName))
		{
			Log.warning(iname+" exist, ignored...");
			return;
		}else
		{
			Log.console("Generating edit ui "+iname+" for "+entyObjClzName+"...");
			fu.createFileIfNotExist(fullName);
			fu.open(fullName);
			appendContent(fu,entyObjClzName,iname,uidir);
			fu.close();
			Log.console("Generating edit ui "+iname+" for "+entyObjClzName+" successfully.");
		}
	
	}
	public static void appendScripts(FileUtil fu,String entyObjClzName)
	{
		Vector<Field> visible=ReflectUtil.calVisibles(entyObjClzName);
		fu.write("<script type=\"text/javascript\">");
		fu.write("	jQuery(document).ready(function () {");
		for(Field f:visible)
		{
			if(ReflectUtil.isDate(f))
			{
				fu.write("		$('#"+f.getName()+"').simpleDatepicker({ startdate: 2008, enddate: 2012 });");
			}
		}
		fu.addEmptyLine(1);
		fu.write("		getObject();");
		fu.addEmptyLine(1);
		
		fu.write("		$(function() {");
		fu.write("		$('#edit').click(function(){");
		fu.write("			edit();");
		fu.write("		return false;//this is very important. or it will refresh the FF after ajax call");
		fu.write("		 	});");
		fu.write("		 });");
		fu.write("	});");
		fu.addEmptyLine(2);
		fu.write("		  function edit()");
		fu.write("		  {");
		fu.write("			var oid=$('#oid').val();");
		for(Field f:visible)
		{
			fu.write("			var "+f.getName()+"=$('#"+f.getName()+"').val();");
		}
		fu.write("			$.ajax({");
		fu.write("			type:\"POST\",");
		fu.write("			async:true,");
		fu.write("			cache: false,");
		fu.write("			//dataType:\"json\",//which is expected from the sever");
		fu.write("			url:\"/"+Path.PROJECT_NAME+"/Edit"+entyObjClzName+"Handler\",");
		String data="";
		for(Field f:visible)
		{
			data=data+f.getName()+":"+f.getName()+",";
		}
		data=data.substring(0,data.length()-1);
		fu.write("			data:{oid:oid,"+data+"},");
		fu.write("			beforeSend:function(XMLHttpRequestObject)");
		fu.write("			{");
		fu.write("			$('#overlay').show();");
		fu.write("			$('#processing').show();");
		fu.write(("			$('#processing').html(\"<img height=29px width=29px  src='../image/processing.gif'></img>&nbsp;请求正在处理...\");"));
		fu.write("			 },");
		fu.write("			success:function(rdata, textStatus, XMLHttpRequest){//please attention rdata is object or array");
		fu.write("			$('#overlay').hide();");
		fu.write(("			$('#processing').html(\"<img height=29px width=29px src='../image/check.gif'></img>&nbsp;请求处理成功!\");"));
		fu.write("			},");
		fu.write("			error:function(XMLHttpRequest, textStatus, errorThrown){");
		fu.write("			$('#overlay').hide();");
		fu.write(("			$('#processing').html(\"<img height=29px width=29px src='../image/cross.png'></img>&nbsp;请求处理失败! "+"\");"));
		fu.write("			jAlert(\"failed with info: \"+textStatus+errorThrown);");
		fu.write("			 },");
		fu.write("			complete:function (XMLHttpRequest, textStatus)");
		fu.write("			{");
		fu.write("				");
		fu.write("			}");
		fu.write(" 		}); ");
		fu.write("		  }");
		fu.addEmptyLine(3);
		fu.write("function getObject()");
		fu.write("{	");
		fu.write("	var request=GetRequest();");
		fu.write("	var objectid=request['objectid'];");
		fu.write("	$('#oid').val(objectid);");
		fu.write("	if(objectid==null)");
		fu.write("	{");
		fu.write("		jAlert('请求非法！INFO:'+objectid);");
		fu.write("		return false;");
		fu.write("	}else");
		fu.write("	{");
		fu.write("		$.ajax({");
		fu.write("				type:\"GET\",");
		fu.write("				async:true,");
		fu.write("				cache: false,");
		fu.write("				dataType:\"json\",//which is expected from the sever");
		fu.write("				url:\"/"+Path.PROJECT_NAME+"/Edit"+entyObjClzName+"Handler\",");
		fu.write("				data:{oid:objectid},");
		fu.write("				beforeSend:function(XMLHttpRequestObject)");
		fu.write("				{");
		fu.write("					$('#overlay').show();");
		fu.write("					$('#processing').show();");
		fu.write("					$('#processing').html(\"<img height=29px width=29px  src='../image/processing.gif'></img>&nbsp;请求正在处理...\");");
		fu.write("				},");
		fu.write("				success:function(rdata, textStatus, XMLHttpRequest)//please attention rdata is object or array");
		fu.write("				{");
		fu.write("					$('#overlay').hide();");
		fu.write("					$('#processing').html(\"<img height=29px width=29px src='../image/check.gif'></img>&nbsp;处理请求成功！\");");
		for(Field f:visible)
		{
			fu.write("					$('#"+f.getName()+"').val(rdata."+f.getName()+");");
		}
		fu.write("				},");
		fu.write("				error:function(XMLHttpRequest, textStatus, errorThrown)");
		fu.write("				{");
		fu.write("					$('#overlay').hide();");
		fu.write("					$('#processing').html(\"<img height=29px width=29px src='../image/cross.png'></img>&nbsp;处理请求失败！\");");
		fu.write("					jAlert(\"failed with info: \"+textStatus+errorThrown);");
		fu.write("				},");
		fu.write("				complete:function (XMLHttpRequest, textStatus)");
		fu.write("				{");
		fu.write("					");
		fu.write("				}");
		fu.write("	 });");
		fu.write("	}");
		fu.write("}");
		
		fu.addEmptyLine(4);
		fu.write("		  </script>");
		fu.addEmptyLine(4);
			
		
	}
	
	public static void appendForm(FileUtil fu,String entyObjClzName)
	{
		Vector<Field> visible=ReflectUtil.calVisibles(entyObjClzName);
		fu.write("<form id=\"EDIT_"+entyObjClzName+"_FORM\">");
		fu.write("<input type=hidden value=\"DEFAULT_OBJECT_ID\" name=\"oid\" id=\"oid\"/>");
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
		fu.write("		<td><button  id=\"edit\"  class=my_button_add ><img src='../image/check.gif'></img> Save</button></td>");
		fu.write("		<td></td>");
		fu.write("		<td><button type=reset id=\"clear_all\" class=my_button_reset ><img src='../image/cancle.png'></img> Reset</button></td>");
		fu.write("	</tr>");
		
		fu.write("</table>");
		fu.write("</form>");
		fu.addEmptyLine(3);
	}
	public static void appendContent(FileUtil fu,String entyObjClzName,String pageName, String uidir)
	{
		fu.openForRead(Path.UI_TEMPLATE_EDIT);
		fu.open(uidir+pageName);
		String tem;
		int inForm=0;
		int inScript=0;
		while((tem=fu.readLine())!=null)
		{
			
			if(tem.contains("<form id=\"EDIT_FORM_TEMPLATE\">"))
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
