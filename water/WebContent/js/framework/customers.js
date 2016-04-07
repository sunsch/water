var customers_js_vars={
};

function customers_js_OpenAddCustomersDialog()
{
	var dialogid='customers_js_OpenAddCustomersDialog_dialog_id';
	if($('#'+dialogid).length!=0)
	{
		$('#'+dialogid).dialog('destroy');
		$('#'+dialogid).remove();
	}
	$('body').append("<div id='"+dialogid+"' title='添加'>"+
					 "</div>");
	$('#'+dialogid).dialog({
		resizable: false,
		autoOpen: true,
		height: 500,
		width: 800,
		modal: true,
		buttons:[{
			text: "确定",
			click: function() {
				customers_js_AddCustomers();
				$('#'+dialogid).dialog('close');
			}
		},{
			text: "取消",
			click: function() {
				$('#'+dialogid).dialog('close');
			}
		}]
	});
	$('#'+dialogid).dialog('open');
	customers_js_ShowAddCustomersTable();
}
function customers_js_ShowAddCustomersTable()
{
	var dialogid='customers_js_OpenAddCustomersDialog_dialog_id';
	
	var newcontent = "";
	newcontent+=("<table class='OPES_TABLE_ID' id='customers_js_ShowAddCustomersTable_id' data-sort='false' cellspacing='0' ><thead><tr><th scope=col colspan=100%>属性：数据工具: 新建一条数据系列：添加数据</th></tr></thead>");
	
	newcontent+="<tr>";
	newcontent+=("<td>选择添加类型：</td>");
	newcontent+=("<td><select id='customers_js_types' style='width:200px;'>" +
				"<option selected value='1'>代理商</option>" +
				"<option value='0'>普通客户</option>" +
				"</select></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>选择代理商：</td>");
	newcontent+=("<td><select id='customers_js_agent_customer_cid' style='width:200px;'></select></td>");
	newcontent+="</tr>";
	
	
	newcontent+="<tr>";
	newcontent+=("<td>客户名称：</td>");
	newcontent+=("<td><input type='text' id='customers_js_customer_name' style='width:200px;' value='' ></input></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>选择性别：</td>");
	newcontent+=("<td><select id='customers_js_sex' style='width:200px;'>" +
				"<option selected value='1'>男</option>" +
				"<option value='0'>女</option>" +
				"</select></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>联系方式：</td>");
	newcontent+=("<td><input type='text' id='customers_js_phone' style='width:200px;' value='' ></input></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>客户地址：</td>");
	newcontent+=("<td><input type='text' id='customers_js_address' style='width:200px;' value='' ></input></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>客户备注：</td>");
	newcontent+=("<td><input type='text' id='customers_js_comments' style='width:200px;' value='' ></input></td>");
	newcontent+="</tr>";
	
	newcontent+="</table>";
	
	$('#'+dialogid).html(newcontent);
	$('#'+dialogid+' input').css({'height': '16px','width':'280px','padding':'6px 9px'});
	$('#'+dialogid+' select').css({'height': '30px','width':'300px'});
	$('#customers_js_ShowAddCustomersTable_id').footable();
	$('#customers_js_ShowAddCustomersTable_id'+' td').css({'padding': '2px'});
	
	customers_js_ListAgents();
}
function customers_js_AddCustomers()
{
	var customers_js_types=$('#customers_js_types').val();
	var customers_js_agent_customer_cid=$('#customers_js_agent_customer_cid').val();
	var customers_js_customer_name=$('#customers_js_customer_name').val();
	var customers_js_sex=$('#customers_js_sex').val();
	var customers_js_phone=$('#customers_js_phone').val();
	var customers_js_address=$('#customers_js_address').val();
	var customers_js_comments=$('#customers_js_comments').val();
	
	var argsdata={types:customers_js_types,agent_customer_cid:customers_js_agent_customer_cid,customer_name:customers_js_customer_name,comments:customers_js_comments,sex:customers_js_sex,phone:customers_js_phone,address:customers_js_address};
	var	data={clazz:'com.exam.action.proxy.customer.CustomerProxy',service:'addCustomer',args:JSON.stringify(argsdata)};
	var success=function(rdata){
		customers_js_ListCustomers();
	};
	invoke_proxy(data,success);
}
function customers_js_ListAgents()
{
	var argsdata={};
	var	data={clazz:'com.exam.action.proxy.customer.CustomerProxy',service:'getAgentList',args:JSON.stringify(argsdata)};
	var success=function(rdata){
		var selObj = $("#customers_js_agent_customer_cid");
		if(rdata.length==0)
		{
			selObj.append("<option selected value=0>选择代理商（可不选）</option>");
		}else
		{
			for(var i=0;i<rdata.length;i++)
			{
				selObj.append("<option value='"+rdata[i].cid+"'>"+rdata[i].customer_name+"</option>");
			}
		}
	};
	invoke_proxy(data,success);
}
function customers_js_ListCustomers()
{
	var argsdata={};
	var	data={clazz:'com.exam.action.proxy.customer.CustomerProxy',service:'getCustomerList',args:JSON.stringify(argsdata)};
	var success=function(rdata){
		customers_js_ShowAllCustomers(rdata);
	};
	invoke_proxy(data,success);
}

function customers_js_ShowAllCustomers(customers)
{
	var newcontent = "";
	newcontent+=("<table class='OPES_TABLE_ID' cellspacing='0' ><thead><tr><th data-sort-ignore='true' scope=col>修改</th><th scope=col>顺序</th><th data-sort-ignore='true'  scope=col>ID</th><th data-sort-ignore='true' scope=col>客户类型</th><th data-sort-ignore='true' scope=col>客户名称</th><th data-sort-ignore='true' scope=col>联系方式</th><th scope=col>地址</th><th data-sort-ignore='true' scope=col>删除</th></tr></thead>");
	if(customers.length==0)
	{
		newcontent+="<tr>";
		newcontent+=("<td colspan='100%' style='text-align:center'>暂时没有数据</td>");
		newcontent+="</tr>";
	}else
	{
		$.each(customers,function(i){
			newcontent+="<tr>";
			newcontent+="<td><a href='javascript:;'><img onclick=aaaa() width=22px height=22px border=0 src='imgs/edit.png' ></img></a></td>";
			newcontent+=("<td>"+ (i+1) +"</td>");
			newcontent+=("<td>"+ customers[i].cid +"</td>");
			newcontent+=("<td>"+ customers[i].types +"</td>");
			newcontent+=("<td>"+ customers[i].customer_name +"</td>");
			newcontent+=("<td>"+ customers[i].phone +"</td>");
			newcontent+=("<td>"+ customers[i].address +"</td>");
			newcontent+="<td><a href='javascript:;'><img onclick=products_js_DeleteCustomer("+ customers[i].cid  +") width=24px height=24px border=0 src='imgs/cancel_48.png' ></img></a></td>";
			
			newcontent+="</tr>";
		});
	}
	newcontent+="</tbody></table>";
	$('#center_div_id').html(newcontent);
	$('.OPES_TABLE_ID').footable();
}
function products_js_DeleteCustomer(cid)
{
	var callback=function(cid){
		var argsdata={cid:cid};
		var	data={clazz:'com.exam.action.proxy.customer.CustomerProxy',service:'deleteCustomer',args:JSON.stringify(argsdata)};
		var success=function(rdata){
			customers_js_ListCustomers();
		};
		invoke_proxy(data,success);
	};
	open_delete_confirm_dialog(callback,cid);
}
