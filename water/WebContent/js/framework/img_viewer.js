var img_viewer_js_vars={
		current_selected_img_name:null
};

function img_viewer_js_open_img_view_dialog(inputIDToBeChanged)
{
	var dialogid='img_viewer_js_open_img_view_dialog_id';
	if($('#'+dialogid).length!=0)
	{
		$('#'+dialogid).dialog('destroy');
		$('#'+dialogid).remove();
	}
	$('body').append("<div id='"+dialogid+"' title='工具：图片查看、选择'>"+
					 "</div>");
	$('#'+dialogid).dialog({
		resizable: false,
		autoOpen: true,
		height: 500,
		width: 800,
		modal: true,
		buttons:[{
			text: "上传",
			click: function() {
				var courseid=0;
				var success_func=function(){
					$('#ajax_upload_js_single_upload_dialog').dialog('close');
					img_viewer_js_refreshImageList();
				};
				var args_array={upload_url:'/lattice/CommonActionProxy?clazz=com.lattice.action.proxy.opes.FileUploadProxy&service=uploadZipFile&args={taskid:'+initialize_ui_js_vars.taskid+'}',success:success_func};
				load_js_then_exec_func_util('js/framework/ajax_upload.js','ajax_upload_js_open_upload_dialog',args_array);
			}
		},{
			text: "刷新",
			click: function() {
				img_viewer_js_refreshImageList();
			}
		},{
			text: "确定",
			click: function() {
				img_viewer_js_selectImgAndClose(inputIDToBeChanged);
			}
		}]
	});
	$('#'+dialogid).dialog('open');
	img_viewer_js_refreshImageList(inputIDToBeChanged);
}
function img_viewer_js_selectImgAndClose(inputIDToBeChanged)
{
	//alert(img_viewer_js_vars.current_selected_img_name+"============"+inputIDToBeChanged);
	if(img_viewer_js_vars.current_selected_img_name==null)
	{
		tips('请选择一个图片！');
	}else
	{
		var dialogid='img_viewer_js_open_img_view_dialog_id';
		$('#'+inputIDToBeChanged).val(img_viewer_js_vars.current_selected_img_name);
		$('#'+dialogid).dialog('close');
	}
}
function img_viewer_js_refreshImageList()
{
	var argsdata={taskid:initialize_ui_js_vars.taskid};
	var	data={clazz:'com.lattice.action.proxy.opes.FileViewerProxy',service:'listAllImgFilesForOneTask',args:JSON.stringify(argsdata)};
	var success=function(rdata){
		img_viewer_js_showImageList(rdata);
	};
	invoke_proxy(data,success);
	img_viewer_js_vars.current_selected_img_name=null;
}

function img_viewer_js_showImageList(rdata)
{
	var dialogid='img_viewer_js_open_img_view_dialog_id';
	var newcontent = "";
	newcontent+=("<table class='OPES_TABLE_ID' cellspacing='0' ><thead><tr><th data-sort-ignore='true' scope=col colspan=100%>PIC</th></tr></thead>");
	if(rdata.length==0)
	{
		newcontent+="<tr>";
		newcontent+=("<td colspan='100%' style='text-align:center'>暂时没有数据</td>");
		newcontent+="</tr>";
	}else
	{
		//var tmpcount=0;
		for(var i=0;i<rdata.length;i++)
		{
			newcontent+="<tr>";
			for(var j=0;j<4;j++)
			{
				if(i<rdata.length)
				{
					newcontent+="<td><a href='javascript:;'><img id='img_viewer_js_showImageList_imgid_"+i+"' width=55px height=55px border=0 src='/lattice"+rdata[i].filePath+rdata[i].fileName+"' ></img></a><br />"+ rdata[i].fileName +"</td>";
					i++;
				}else
				{
					newcontent+="<td>&nbsp;</td>";
				}
			}
			newcontent+="</tr>";
		}
	}
	newcontent+="</tbody></table>";
	$('#'+dialogid).html(newcontent);
	
	$.each(rdata,function(i){
		$("#img_viewer_js_showImageList_imgid_"+i).click(function(){
			$('#'+dialogid+' img').css({'border': '0'});
			$('#img_viewer_js_showImageList_imgid_'+i).css({'border': '5px solid blue'});
			img_viewer_js_vars.current_selected_img_name=rdata[i].fileName;
		});
	});
	
	
	$('.OPES_TABLE_ID').footable();
	$('.OPES_TABLE_ID td').css({'text-align': 'center'});
	$('.OPES_TABLE_ID tr').css({'display': 'table-row'});
}

