package com.framework.auto.ui;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Vector;

import com.framework.auto.consts.Consts;
import com.framework.auto.path.Path;
import com.framework.auto.util.FileUtil;
import com.framework.auto.util.Log;
import com.framework.auto.util.ReflectUtil;

public class GenViewUI 
{

	//generate view page, e.g. viewStudents.htm
	public static void createViewUI(String entyObjClzName)
	{

		FileUtil fu=new FileUtil();
		String uidir=Path.WEB_CONTENT_PATH+new File(Path.ENTITY_PATH).getName()+"ui\\";
		fu.createDirIfNotExist(uidir);//create a new ui dir if not exist
		
		if(!fu.checkExist(Path.ENTITY_PATH+entyObjClzName+".java"))
		{
			Log.warning("View UI.. The java class you specified does not exist: "+Path.ENTITY_PATH+entyObjClzName+", ignored.");
			return;
		}
		String iname="view"+entyObjClzName+fu.calTemplateSuffix();
		String fullName=uidir+iname;
		if(fu.checkExist(fullName))
		{
			Log.warning(iname+" exist, ignored...");
			return;
		}else
		{
			Log.console("Generating View ui "+iname+" for "+entyObjClzName+"...");
			fu.createFileIfNotExist(fullName);
			fu.open(fullName);
			appendContent(fu,entyObjClzName,iname,uidir);
			fu.close();
			Log.console("Generating View ui "+iname+" for "+entyObjClzName+" successfully.");
		}
	
	}
	public static void appendScripts(FileUtil fu,String entyObjClzName)
	{
		Vector<Field> visible=ReflectUtil.calVisibles(entyObjClzName);
		fu.write("<script type=\"text/javascript\">");
		fu.write("	var objcount=0;");
		fu.write("	function pageselectCallback(page_index, jq){");
		fu.write("		var items_per_page = $('#items_per_page').val();");
		fu.write("		//如果是使用getJSON IE默认为不请求,去缓存.FF每次都请求,所有更换为ajax方法,或者添加timestamp");
		fu.write("		$.getJSON('/"+Path.PROJECT_NAME+"/View"+entyObjClzName+"Handler?action=qobj&page_index='+page_index+'&timestemp='+getTimestamp(), function(data) {");
		fu.write("			var newcontent = \"\";");
		String head="<th scope=col>编辑</th>";
		for(Field f:ReflectUtil.calVisibles(entyObjClzName))
		{
			head+="<th scope=col>"+ReflectUtil.znValue(f)+"</th>";
		}
		head+="<th scope=col>删除</th>";
		
		fu.write("			newcontent+=(\"<table id='AJAX_TABLE_ID' class='AJAX_TABLE_ID' cellspacing='0' ><thead><tr>"+head+"</tr></thead>\");");
		fu.write("			$.each(data,function(i){");
		fu.write("				var trid=\"TABLE_TR_TOGGLE_ID\"+i;");
		fu.write("				newcontent+=\"<tr id=\"+trid+\">\";");
		fu.write("				newcontent+=\"<td><a href=edit"+entyObjClzName+".htm?objectid=\"+ data[i]."+ReflectUtil.calid(entyObjClzName)+" +\"><img width=30px height=30px border=0 src='../image/crud/edit.png' ></img></a></td>\";");
		for(Field f:ReflectUtil.calVisibles(entyObjClzName))
		{
			fu.write("				newcontent+=(\"<td>\"+ data[i]."+f.getName()+" +\"</td>\");");
		}
		fu.write("				newcontent+=(\"<td><button id=\"+ data[i]."+ReflectUtil.calid(entyObjClzName)+" +\" class=my_button_delete  value=delete ><img src='../image/crud/delete.png'></img> Delete</button></td>\");");
		fu.write("				newcontent+=\"</tr>\";");
		fu.write("			});");
		fu.write("				newcontent+=\"</tbody></table>\";");
		fu.write("				$('#Searchresult').html(newcontent);");
		fu.addEmptyLine(2);
		fu.write("				$(\"tr:even\").css(\"background-color\", \"#E0FFFF\");");
		fu.write("				$(\"tr:odd\").css(\"background-color\", \"#FFF0F5\");");
		fu.write("				$('#AJAX_TABLE_ID tr').hover(");
		fu.write("					function () {");
		fu.write("						$(this).css(\"background-color\", \"#FFDEAD\");");
		fu.write("					 },");
		fu.write("					function () {");
		fu.write("						$(\"tr:even\").css(\"background-color\", \"#E0FFFF\");");
		fu.write("						$(\"tr:odd\").css(\"background-color\", \"#FFF0F5\");");
		fu.write("					}");
		fu.write("				);");
		fu.write("				$.each(data,function(i){");
		fu.write("				$(\"#\"+data[i]."+ReflectUtil.calid(entyObjClzName)+").click(function(){");
		fu.write("				var deleted_object_id=this.id;");
		fu.write("				jConfirm('删除后无法恢复,确定要删除这条记录吗？','确定删除记录',function(r){");
		fu.write("				if(r)");
		fu.write("				{");
		fu.write("					$.ajax({");
		fu.write("					type:\"POST\",");
		fu.write("					async:true,");
		fu.write("					dataType:\"json\",");
		fu.write("					cache: false,");
		fu.write("					url:\"/"+Path.PROJECT_NAME+"/Delete"+entyObjClzName+"Handler\",");
		fu.write("					data:({page_index:page_index,oid:deleted_object_id}),");
		fu.write("					beforeSend:function(XMLHttpRequestObject)");
		fu.write("					{");
		fu.write("					},");
		fu.write("					success:function(rdata, textStatus, XMLHttpRequest)//please attention rdata is object or array");
		fu.write("					{");
		fu.write("						objcount--;");
		fu.write("						var opts = getOptionsFromForm();");
		fu.write("						opts.current_page=page_index;");
		fu.write("						$(\"#Pagination\").pagination(objcount, opts);");
		fu.write("					},");
		fu.write("					error:function(XMLHttpRequest, textStatus, errorThrown)");
		fu.write("					{");
		fu.write("						jAlert(\"failed with info: \"+textStatus+errorThrown);");
		fu.write("					},");
		fu.write("					complete:function (XMLHttpRequest, textStatus)");
		fu.write("					{");
		fu.write("						");
		fu.write("					}");
		fu.write("					});");
		
		
		fu.write("				}");
		fu.write("					});");
		fu.write("					});");
		fu.write("					});");//each
		fu.write("					});");//getJSON()
		fu.write("					return false;");
		fu.write("					}");
		fu.addEmptyLine(4);
		
		fu.write("function getOptionsFromForm(){");
		fu.write("	var opt = {callback: pageselectCallback};");
		fu.write("	return opt;");
		fu.write("}	");
		fu.addEmptyLine(4);
		
		fu.write("	$(document).ready(function(){");
		fu.write("		$.ajax({");
		fu.write("				type:\"GET\",");
		fu.write("				async:false,");
		fu.write("				cache: false,");
		fu.write("				dataType:\"json\",//which is expected from the sever");
		fu.write("				url:\"/"+Path.PROJECT_NAME+"/View"+entyObjClzName+"Handler\",");
		fu.write("				data:{action:'qcount'},");
		fu.write("				beforeSend:function(XMLHttpRequestObject)");
		fu.write("				{");
		fu.write("				},");
		fu.write("				success:function(rdata, textStatus, XMLHttpRequest)//please attention rdata is object or array");
		fu.write("				{");
		fu.write("					objcount=rdata.count;");
		fu.write("				},");
		fu.write("				error:function(XMLHttpRequest, textStatus, errorThrown)");
		fu.write("				{");
		fu.write("					jAlert(\"failed with info: \"+textStatus+errorThrown);");
		fu.write("				},");
		fu.write("				complete:function (XMLHttpRequest, textStatus)");
		fu.write("				{");
		fu.write("				}");
		fu.write("	 });");
		fu.write("	var optInit = getOptionsFromForm();");
		fu.write("	$(\"#Pagination\").pagination(objcount, optInit);");
		fu.write("	});");
		
		fu.addEmptyLine(4);
		fu.write("		  </script>");
		fu.addEmptyLine(4);
			
		
	}
	
	
	public static void appendContent(FileUtil fu,String entyObjClzName,String pageName, String uidir)
	{
		fu.openForRead(Path.UI_TEMPLATE_VIEW);
		fu.open(uidir+pageName);
		String tem;
		int inForm=0;
		int inScript=0;
		while((tem=fu.readLine())!=null)
		{
			
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
