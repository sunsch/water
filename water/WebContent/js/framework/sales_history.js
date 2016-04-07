var sales_history_js_vars={
};

function sales_history_js_OpenAddSalesHistoryDialog()
{
	var dialogid='sales_history_js_OpenAddsales_historyDialog_dialog_id';
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
				sales_history_js_AddSalesHistory();
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
	sales_history_js_ShowAddSalesHistoryTable();
}
function sales_history_js_ShowAddSalesHistoryTable()
{
	var dialogid='sales_history_js_OpenAddsales_historyDialog_dialog_id';
	
	var newcontent = "";
	newcontent+=("<table class='OPES_TABLE_ID' id='sales_history_js_ShowAddsales_historyTable_id' data-sort='false' cellspacing='0' ><thead><tr><th scope=col colspan=100%>属性：数据工具: 新建一条数据系列：添加数据</th></tr></thead>");
	
	newcontent+="<tr>";
	newcontent+=("<td>客户名称：</td>");
	newcontent+=("<td><select id='sales_history_js_customer_id' style='width:200px;'></select></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>产品名称：</td>");
	newcontent+=("<td><select id='sales_history_js_product_id' style='width:200px;'></select></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>产品数量：</td>");
	newcontent+=("<td><input type='text' id='sales_history_js_quantity' style='width:200px;' value='' ></input></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>订单时间：</td>");
	newcontent+=("<td><input type='text' id='sales_history_js_sale_time' style='width:200px;' value='' ></input></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>产品备注：</td>");
	newcontent+=("<td><input type='text' id='sales_history_js_comments' style='width:200px;' value='' ></input></td>");
	newcontent+="</tr>";
	
	newcontent+="</table>";
	
	$('#'+dialogid).html(newcontent);
	$('#'+dialogid+' input').css({'height': '16px','width':'280px','padding':'6px 9px'});
	$('#'+dialogid+' select').css({'height': '30px','width':'300px'});
	$('#sales_history_js_ShowAddsales_historyTable_id').footable();
	$('#sales_history_js_ShowAddsales_historyTable_id'+' td').css({'padding': '2px'});
	$("#sales_history_js_sale_time").datepicker({ dateFormat: 'yy-mm-dd' });
	
	sales_history_js_ListAllCommonCustomers();
	sales_history_js_ListAllProducts();
	
}
function sales_history_js_AddSalesHistory()
{
	var sales_history_js_customer_id=$('#sales_history_js_customer_id').val();
	var sales_history_js_product_id=$('#sales_history_js_product_id').val();
	var sales_history_js_quantity=$('#sales_history_js_quantity').val();
	var sales_history_js_sale_time=$('#sales_history_js_sale_time').val();
	var sales_history_js_comments=$('#sales_history_js_comments').val();
	
	var argsdata={customer_id:sales_history_js_customer_id,product_id:sales_history_js_product_id,quantity:sales_history_js_quantity,sale_time:sales_history_js_sale_time,comments:sales_history_js_comments};
	var	data={clazz:'com.exam.action.proxy.saleshistory.SalesHistoryProxy',service:'addSalesHistory',args:JSON.stringify(argsdata)};
	var success=function(rdata){
		sales_history_js_ListSalesHistory();
	};
	invoke_proxy(data,success);
}
function sales_history_js_ListSalesHistory()
{
	var argsdata={};
	var	data={clazz:'com.exam.action.proxy.saleshistory.SalesHistoryProxy',service:'getSalesHistoryList',args:JSON.stringify(argsdata)};
	var success=function(rdata){
		sales_history_js_ShowAllSalesHistory(rdata);
	};
	invoke_proxy(data,success);
}
function sales_history_js_ListAllCommonCustomers()
{
	var argsdata={};
	var	data={clazz:'com.exam.action.proxy.customer.CustomerProxy',service:'getCommonCustomerList',args:JSON.stringify(argsdata)};
	var success=function(rdata){
		var selObj = $("#sales_history_js_customer_id");
		if(rdata.length==0)
		{
			selObj.append("<option selected value=0>暂时没有用户</option>");
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
function sales_history_js_ListAllProducts()
{
	var argsdata={};
	var	data={clazz:'com.exam.action.proxy.product.ProductProxy',service:'getProductList',args:JSON.stringify(argsdata)};
	var success=function(rdata){
		var selObj = $("#sales_history_js_product_id");
		if(rdata.length==0)
		{
			selObj.append("<option selected value=0>暂时没有产品</option>");
		}else
		{
			for(var i=0;i<rdata.length;i++)
			{
				selObj.append("<option value='"+rdata[i].pid+"'>"+rdata[i].product_name+"</option>");
			}
		}
	};
	invoke_proxy(data,success);
}
function sales_history_js_ShowAllSalesHistory(sales_history)
{
	var newcontent = "";
	newcontent+=("<table class='OPES_TABLE_ID' cellspacing='0' ><thead><tr><th data-sort-ignore='true' scope=col>修改</th><th scope=col>顺序</th><th data-sort-ignore='true'  scope=col>ID</th><th data-sort-ignore='true' scope=col>客户名称</th><th data-sort-ignore='true' scope=col>产品名称</th><th data-sort-ignore='true' scope=col>数量</th><th scope=col>订单时间</th><th data-sort-ignore='true' scope=col>删除</th></tr></thead>");
	if(sales_history.length==0)
	{
		newcontent+="<tr>";
		newcontent+=("<td colspan='100%' style='text-align:center'>暂时没有数据</td>");
		newcontent+="</tr>";
	}else
	{
		$.each(sales_history,function(i){
			newcontent+="<tr>";
			newcontent+="<td><a href='javascript:;'><img onclick=toolbox_data_column_js_openAddOrEditColumnDialog() width=22px height=22px border=0 src='imgs/edit.png' ></img></a></td>";
			newcontent+=("<td>"+ (i+1) +"</td>");
			newcontent+=("<td>"+ sales_history[i].sid +"</td>");
			newcontent+=("<td>"+ sales_history[i].customer_id +"</td>");
			newcontent+=("<td>"+ sales_history[i].product_id +"</td>");
			newcontent+=("<td>"+ sales_history[i].quantity +"</td>");
			newcontent+=("<td>"+ sales_history[i].sale_time +"</td>");
			newcontent+="<td><a href='javascript:;'><img onclick=sales_history_js_DeleteSalesHistory("+ sales_history[i].sid  +") width=24px height=24px border=0 src='imgs/cancel_48.png' ></img></a></td>";
			
			newcontent+="</tr>";
		});
	}
	newcontent+="</tbody></table>";
	$('#center_div_id').html(newcontent);
	$('.OPES_TABLE_ID').footable();
}
function sales_history_js_DeleteSalesHistory(sid)
{
	var callback=function(sid){
		var argsdata={sid:sid};
		var	data={clazz:'com.exam.action.proxy.saleshistory.SalesHistoryProxy',service:'deleteSalesHistory',args:JSON.stringify(argsdata)};
		var success=function(rdata){
			sales_history_js_ListSalesHistory();
		};
		invoke_proxy(data,success);
	};
	open_delete_confirm_dialog(callback,sid);
}
