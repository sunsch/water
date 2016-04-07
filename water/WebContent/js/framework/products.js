var products_js_vars={
};

function products_js_OpenAddProductsDialog()
{
	var dialogid='products_js_OpenAddProductsDialog_dialog_id';
	if($('#'+dialogid).length!=0)
	{
		$('#'+dialogid).dialog('destroy');
		$('#'+dialogid).remove();
	}
	$('body').append("<div id='"+dialogid+"' title='添加产品'>"+
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
				products_js_AddProducts();
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
	products_js_ShowAddProductsTable();
}
function products_js_ShowAddProductsTable()
{
	var dialogid='products_js_OpenAddProductsDialog_dialog_id';
	
	var newcontent = "";
	newcontent+=("<table class='OPES_TABLE_ID' id='products_js_ShowAddProductsTable_id' data-sort='false' cellspacing='0' ><thead><tr><th scope=col colspan=100%>属性：数据工具: 新建一条数据系列：添加数据</th></tr></thead>");
	
	newcontent+="<tr>";
	newcontent+=("<td>产品名称：</td>");
	newcontent+=("<td><input type='text' id='products_js_product_name' style='width:200px;' value='' ></input></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>产品容量：</td>");
	newcontent+=("<td><input type='text' id='products_js_volume' style='width:200px;' value='' ></input></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>产品价格：</td>");
	newcontent+=("<td><input type='text' id='products_js_price' style='width:200px;' value='' ></input></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>返现设置：</td>");
	newcontent+=("<td><input type='text' id='products_js_cash_return' style='width:200px;' value='' ></input></td>");
	newcontent+="</tr>";
	
	newcontent+="<tr>";
	newcontent+=("<td>产品备注：</td>");
	newcontent+=("<td><input type='text' id='products_js_comments' style='width:200px;' value='' ></input></td>");
	newcontent+="</tr>";
	
	newcontent+="</table>";
	
	$('#'+dialogid).html(newcontent);
	$('#'+dialogid+' input').css({'height': '16px','width':'280px','padding':'6px 9px'});
	$('#'+dialogid+' select').css({'height': '30px','width':'300px'});
	$('#products_js_ShowAddProductsTable_id').footable();
	$('#products_js_ShowAddProductsTable_id'+' td').css({'padding': '2px'});
	
	
}
function products_js_AddProducts()
{
	var products_js_product_name=$('#products_js_product_name').val();
	var products_js_volume=$('#products_js_volume').val();
	var products_js_price=$('#products_js_price').val();
	var products_js_comments=$('#products_js_comments').val();
	var products_js_cash_return=$('#products_js_cash_return').val();
	
	var argsdata={cash_return:products_js_cash_return,product_name:products_js_product_name,volume:products_js_volume,price:products_js_price,comments:products_js_comments};
	var	data={clazz:'com.exam.action.proxy.product.ProductProxy',service:'addProduct',args:JSON.stringify(argsdata)};
	var success=function(rdata){
		products_js_ListProducts();
	};
	invoke_proxy(data,success);
}
function products_js_ListProducts()
{
	var content='';
	content+='<div id="products_js_ListProducts_onepage" >';
	content+='</div>';
	content+='<div id="products_js_ListProducts_pagination" class="meneame">';
	content+='</div>';
	$('#center_div_id').html(content);
	products_js_ListProductsAllCount();

}
function products_js_ListProductsAllCount()
{
	var argsdata={};
	var	data={clazz:'com.exam.action.proxy.product.ProductProxy',service:'listProductCount',args:JSON.stringify(argsdata)};
	var success=function(rdata){
		var opt = {callback: products_js_ListProductsOnePage};
		$("#products_js_ListProducts_pagination").mypagination(rdata.count,opt);
	};
	invoke_proxy(data,success);
}
function products_js_ListProductsOnePage(page_index, jq)
{
	var argsdata={page_index:page_index};
	var	data={clazz:'com.exam.action.proxy.product.ProductProxy',service:'listProductOnePage',args:JSON.stringify(argsdata)};
	var success=function(rdata){
		products_js_ShowAllProducts(rdata);
	};
	invoke_proxy(data,success);
}

function products_js_ShowAllProducts(products)
{
	var newcontent = "";
	newcontent+=("<table class='OPES_TABLE_ID' cellspacing='0' ><thead><tr><th data-sort-ignore='true' scope=col>修改</th><th scope=col>顺序</th><th data-sort-ignore='true'  scope=col>ID</th><th data-sort-ignore='true' scope=col>产品名称</th><th data-sort-ignore='true' scope=col>容量</th><th data-sort-ignore='true' scope=col>返现设置</th><th data-sort-ignore='true' scope=col>价格</th><th scope=col>完成状态</th><th data-sort-ignore='true' scope=col>删除</th></tr></thead>");
	if(products.length==0)
	{
		newcontent+="<tr>";
		newcontent+=("<td colspan='100%' style='text-align:center'>暂时没有数据</td>");
		newcontent+="</tr>";
	}else
	{
		$.each(products,function(i){
			newcontent+="<tr>";
			newcontent+="<td><a href='javascript:;'><img onclick=toolbox_data_column_js_openAddOrEditColumnDialog() width=22px height=22px border=0 src='imgs/edit.png' ></img></a></td>";
			newcontent+=("<td>"+ (i+1) +"</td>");
			newcontent+=("<td>"+ products[i].pid +"</td>");
			newcontent+=("<td>"+ products[i].product_name +"</td>");
			newcontent+=("<td>"+ products[i].volume +"</td>");
			newcontent+=("<td>"+ products[i].cash_return +"</td>");
			newcontent+=("<td>"+ products[i].price +"</td>");
			newcontent+="<td><a class='opes2_tooltip' href='javascript:;' title='已完成本条数据' href='javascript:;'><img width=24px height=24px border=0 src='imgs/accepted_48.png' ></img></a></td>";
			newcontent+="<td><a href='javascript:;'><img onclick=products_js_DeleteProduct("+ products[i].pid  +") width=24px height=24px border=0 src='imgs/cancel_48.png' ></img></a></td>";
			
			newcontent+="</tr>";
		});
	}
	newcontent+="</tbody></table>";
	$('#products_js_ListProducts_onepage').html(newcontent);
	$('.OPES_TABLE_ID').footable();
}
function products_js_DeleteProduct(pid)
{
	var callback=function(pid){
		var argsdata={pid:pid};
		var	data={clazz:'com.exam.action.proxy.product.ProductProxy',service:'deleteProduct',args:JSON.stringify(argsdata)};
		var success=function(rdata){
			products_js_ListProducts();
		};
		invoke_proxy(data,success);
	};
	open_delete_confirm_dialog(callback,pid);
}
