var cash_return_js_vars={
		cashReturnList:[]
};

function cash_return_js_ListCashReturns()
{
	var argsdata={};
	var	data={clazz:'com.exam.action.proxy.cashreturn.CashReturnProxy',service:'getCashReturnList',args:JSON.stringify(argsdata)};
	var success=function(rdata){
		cash_return_js_ShowCashReturns(rdata);
		cash_return_js_vars.cashReturnList=rdata;
	};
	invoke_proxy(data,success);
}

function cash_return_js_ShowCashReturns(cashReturns)
{
	var newcontent = "";
	newcontent+=("<table class='OPES_TABLE_ID' cellspacing='0' ><thead><tr><th data-sort-ignore='true' scope=col>查看</th><th scope=col>顺序</th><th data-sort-ignore='true'  scope=col>代理商名字</th><th data-sort-ignore='true' scope=col>联系方式</th><th data-sort-ignore='true' scope=col>联系地址</th><th data-sort-ignore='true' scope=col>销售记录</th><th data-sort-ignore='true' scope=col>需返现金额</th></tr></thead>");
	if(cashReturns.length==0)
	{
		newcontent+="<tr>";
		newcontent+=("<td colspan='100%' style='text-align:center'>暂时没有数据</td>");
		newcontent+="</tr>";
	}else
	{
		$.each(cashReturns,function(i){
			newcontent+="<tr>";
			newcontent+="<td><a href='javascript:;'><img onclick=cash_return_js_openCashReturnsDetailsDialog("+cashReturns[i].agent.cid+") width=22px height=22px border=0 src='imgs/edit.png' ></img></a></td>";
			newcontent+=("<td>"+ (i+1) +"</td>");
			newcontent+=("<td>"+ cashReturns[i].agent.customer_name +"</td>");
			newcontent+=("<td>"+ cashReturns[i].agent.phone +"</td>");
			newcontent+=("<td>"+ cashReturns[i].agent.address +"</td>");
			newcontent+=("<td>"+ cashReturns[i].salesHistory.length +"</td>");
			newcontent+=("<td>"+ cashReturns[i].totalCashReturn +"</td>");
			newcontent+="</tr>";
		});
	}
	newcontent+="</tbody></table>";
	$('#center_div_id').html(newcontent);
	$('.OPES_TABLE_ID').footable();
}

function cash_return_js_openCashReturnsDetailsDialog(agentid)
{
	var dialogid='cash_return_js_openCashReturnsDetailsDialog_dialog_id';
	if($('#'+dialogid).length!=0)
	{
		$('#'+dialogid).dialog('destroy');
		$('#'+dialogid).remove();
	}
	$('body').append("<div id='"+dialogid+"' title='销售记录'>"+
					 "</div>");
	$('#'+dialogid).dialog({
		resizable: false,
		autoOpen: true,
		height: 600,
		width: 900,
		modal: true,
		buttons:[{
			text: "确定",
			click: function() {
				$('#'+dialogid).dialog('close');
			}
		}]
	});
	$('#'+dialogid).dialog('open');
	cash_return_js_showCashReturnsDetailsList(agentid);
}
function cash_return_js_showCashReturnsDetailsList(agentid)
{
	var dialogid='cash_return_js_openCashReturnsDetailsDialog_dialog_id';
	var saleHistoryList=[];
	for(var k=0;k<cash_return_js_vars.cashReturnList.length;k++)
	{
		var cashReturn=cash_return_js_vars.cashReturnList[k];
		if(cashReturn.agent.cid==agentid)
		{
			saleHistoryList=cashReturn.salesHistory;
		}
	}
	
	var newcontent = "";
	newcontent+=("<table class='OPES_TABLE_ID' cellspacing='0' ><thead><tr><th data-sort-ignore='true' scope=col>顺序</th><th scope=col>客户名称</th><th scope=col>电话</th><th scope=col>地址</th><th scope=col>产品名称</th><th scope=col>价格</th><th scope=col>返现设置</th><th scope=col>数量</th><th scope=col>销售日期</th></tr></thead>");
	if(saleHistoryList.length==0)
	{
		newcontent+="<tr>";
		newcontent+=("<td colspan='100%' style='text-align:center'>暂时没有数据</td>");
		newcontent+="</tr>";
	}else
	{
		$.each(saleHistoryList,function(i){
			newcontent+="<tr>";
			newcontent+=("<td>"+ (i+1) +"</td>");
			newcontent+=("<td>"+ saleHistoryList[i].customer.customer_name +"</td>");
			newcontent+=("<td>"+ saleHistoryList[i].customer.phone +"</td>");
			newcontent+=("<td>"+ saleHistoryList[i].customer.address +"</td>");
			newcontent+=("<td>"+ saleHistoryList[i].product.product_name +"</td>");
			newcontent+=("<td>"+ saleHistoryList[i].product.price +"</td>");
			newcontent+=("<td>"+ saleHistoryList[i].product.cash_return +"</td>");
			newcontent+=("<td>"+ saleHistoryList[i].quantity +"</td>");
			newcontent+=("<td>"+ saleHistoryList[i].sale_time +"</td>");
			newcontent+="</tr>";
		});
	}
	newcontent+="</tbody></table>";
	$('#'+dialogid).html(newcontent);
	$('.OPES_TABLE_ID').footable();
}