
var initialize_ui_js_vars=
{
	taskid:0,
	flowchart_tool_counter:1,
	initialize_ui_js_toolbox_list:[],
	current_dragged_tool_type:null,
	current_selected_tool_id:'',
	current_selected_tool:null,
	flow_object_list:[]
	
};

function initialize_global_variables__________()
{
	var taskid = GetRequest().taskid;
	taskid = parseInt(taskid);
	taskid = isNaN(taskid) ? 0 : taskid;
	initialize_ui_js_vars.taskid=taskid;
	if(taskid==0)
	{
		tips('错误的任务ID！5秒后返回系统主页...');
		var goHome=function(){
			window.location.href='/lattice/';
		};
		window.setTimeout(goHome,5000);
	}
}
function initialize_toolbar___________()
{
	var content="";
	content+="<a class='opes-toolbar' title='点击新建一个实验或测验'><img src='imgs/paper&pencil_48.png' style='height:30px;width:30px;'></img></a>";
	content+="<a class='opes-toolbar'><img src='imgs/toolbar/save5.jpg' style='height:30px;width:30px;'></img></a>";
	content+="<a class='opes-toolbar'><img src='imgs/toolbar/save4.jpg' style='height:30px;width:30px;'></img></a>";
	content+="<a id='initialize_ui_js_toolbar_compile' class='opes-toolbar' title='编译'><img src='imgs/table_48.png' style='height:30px;width:30px;'></img></a>";
	content+="<a id='initialize_ui_js_toolbar_run' class='opes-toolbar' title='运行'><img src='imgs/computer_48.png' style='height:30px;width:30px;'></img></a>";
	content+="<a class='opes-toolbar'><img src='imgs/questionmark_48.png' style='height:30px;width:30px;'></img></a>";
	$('#toolbar').html(content);
	$(".opes-toolbar").button({});
	$(".opes-toolbar").tooltip();
	
	$('#initialize_ui_js_toolbar_compile').click(function() {
		load_js_then_exec_func_util('js/framework/chart_to_code.js','chart_to_code_js_convert_chart_to_code');
	});
	$('#initialize_ui_js_toolbar_run').click(function() {
		load_js_then_exec_func_util('js/framework/page_preview.js','page_preview_js_preview_test');
	});
	
}

function initialize_layout()
{

	var myLayout=$('body').layout({ 
	applyDemoStyles: true,
	closable:true,
	resizable:true

	});
	
}

function initialize_tabs_______()
{
	$("#tabs_div").tabs({
			activate: function( event, ui ) 
			{
				 //alert(ui.newPanel.attr('id'));
				var tabid=ui.newPanel.attr('id');
				if(tabid==='tab_1')
				{
					load_js_then_exec_func_util('js/framework/code_to_chart.js','code_to_chart_js_convert_code_to_chart');
				}else if(tabid==='tab_2')
				{
					load_js_then_exec_func_util('js/framework/chart_to_code.js','chart_to_code_js_convert_chart_to_code');
				}else if(tabid==='tab_3')
				{
					load_js_then_exec_func_util('js/framework/page_preview.js','page_preview_js_preview_test');
				}
			}
	  }
	);
}


function initialize_left_menu()
{
	var content="";
	content+="<h3>";
	content+="<a href='#'><img src='imgs/start-p1.jpg' style='border:0px;width:24px;height:24px;'></img>送水产品管理</a>";
	content+="</h3>";
	content+="<div class='system_toolbox_class' style='padding-top: 5px; padding-left: 10px;'>";
	content+="<div  id='TOOLBOX_TYPE_DATA1' style='text-align:center;font-size:13px;'><img src='imgs/table_48.png' style='border:0px;width:36px;height:36px;'></img><div></div><a href='javascript:;' onclick='initialize_ui_js_ListProducts();'>产品列表</a></div>";
	content+="<div  id='TOOLBOX_TYPE_TEXT2' style='text-align:center;font-size:13px;'><img src='imgs/computer_48.png' style='border:0px;width:36px;height:36px;'></img><div></div><a href='javascript:;' onclick='initialize_ui_js_AddProducts();'>添加产品</a></div>";
	content+="</div>";
	
	content+="<h3>";
	content+="<a href='#'><img src='imgs/app_48.png' style='border:0px;width:24px;height:24px;'></img>客户关系管理</a>";
	content+="</h3>";
	content+="<div class='system_toolbox_class'  style='padding-top: 5px; padding-left: 10px;'>";
	content+="<div  id='TOOLBOX_TYPE_DATA3' style='text-align:center;font-size:13px;'><img src='imgs/table_48.png' style='border:0px;width:36px;height:36px;'></img><div></div><a href='javascript:;' onclick='initialize_ui_js_ListCustomers();'>客户列表</a></div>";
	content+="<div  id='TOOLBOX_TYPE_TEXT4' style='text-align:center;font-size:13px;'><img src='imgs/computer_48.png' style='border:0px;width:36px;height:36px;'></img><div></div><a href='javascript:;' onclick='initialize_ui_js_AddCustomer();'>添加客户</a></div>";
	content+="</div>";
	content+="<h3>";
	content+="<a href='#'><img src='imgs/hammer_screwdriver.png' style='border:0px;width:20px;height:20px;'></img>送水订单管理</a>";
	content+="</h3>";
	content+="<div class='system_toolbox_class'  style='padding-top: 5px; padding-left: 10px;'>";
	content+="<div  id='TOOLBOX_TYPE_CODE1' style='text-align:center;font-size:13px;'><img src='imgs/paper_content_pencil_48.png' style='border:0px;width:36px;height:36px;'></img><div></div><a href='javascript:;' onclick='initialize_ui_js_ListSalesHistory();'>订单列表</a></div>";
	content+="<div  id='TOOLBOX_TYPE_CODE2' style='text-align:center;font-size:13px;'><img src='imgs/paper_content_pencil_48.png' style='border:0px;width:36px;height:36px;'></img><div></div><a href='javascript:;' onclick='initialize_ui_js_AddSalesHistory();'>添加订单</a></div>";
	content+="</div>";
	content+="<h3>";
	content+="<a href='#'><img src='imgs/clock_48.png' style='border:0px;width:20px;height:20px;'></img>统计账单管理</a>";
	content+="</h3>";
	content+="<div class='system_toolbox_class'  style='padding-top: 5px; padding-left: 10px;'>";
	content+="<div  id='TOOLBOX_TYPE_WAIT2' style='text-align:center;font-size:13px;'><img src='imgs/clock_48.png' style='border:0px;width:36px;height:36px;'></img><div></div><a href='javascript:;' onclick='initialize_ui_js_ListCashReturn();'>返现统计</a></div>";
	content+="</div>";
	
	$( "#accordion" ).html(content);
	//$('.system_toolbox_class div').css( 'cursor', 'pointer' );
	
	$( "#accordion" ).accordion({
				collapsible: true,
				fillSpace: false,
				active: false,
				autoHeight: false,
				navigation: true
			}); 
	
}

function initialize_tooltips()
{
	$(".opes2_tooltip").tooltip({
		show : {
			effect : "slideDown",
			delay : 250
		},
		hide : {
			effect : "slideUp",
			delay : 250
		}
	});
}
function initialize_ui_js_ListProducts()
{
	load_js_then_exec_func_util('js/framework/products.js','products_js_ListProducts');
}
function initialize_ui_js_AddProducts()
{
	load_js_then_exec_func_util('js/framework/products.js','products_js_OpenAddProductsDialog');
}
function initialize_ui_js_ListCustomers()
{
	load_js_then_exec_func_util('js/framework/customers.js','customers_js_ListCustomers');
}
function initialize_ui_js_AddCustomer()
{
	load_js_then_exec_func_util('js/framework/customers.js','customers_js_OpenAddCustomersDialog');
}
function initialize_ui_js_ListSalesHistory()
{
	load_js_then_exec_func_util('js/framework/sales_history.js','sales_history_js_ListSalesHistory');
}
function initialize_ui_js_AddSalesHistory()
{
	load_js_then_exec_func_util('js/framework/sales_history.js','sales_history_js_OpenAddSalesHistoryDialog');
}

function initialize_ui_js_ListCashReturn()
{
	load_js_then_exec_func_util('js/framework/cash_return.js','cash_return_js_ListCashReturns');
}