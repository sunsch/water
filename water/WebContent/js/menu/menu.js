$(function(){
	var span = '<span></span>';
	$('.system_menu li a').wrapInner(span);
	
	$('.system_menu li').click(function(){
		$(this).addClass('selected')
			   .siblings().removeClass('selected');
	});
});