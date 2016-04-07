<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/error/tips.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/error/g.css" />
<title>Rota Framework v1.0 框架</title>

</head>
<body class="All_C_Page ErrorPage">

<div>&nbsp;<br/><br/><br/><br/><br/></div>

<div class="gSysWin_Wp Er_Bck">
			<div class="gSysWin_WpIn">
				
				<div class="gSW_TT_Wp">
					<table class="gSW_TT_tb">
						<tbody><tr>
							<td class="F1Img LftCorner"></td>
							<td class="F2Img MidTxt">系统提示</td>
							<td class="F1Img RigCorner"></td>
						</tr>
					</tbody></table>
				</div>

				<div class="gSW_Con_Wp"> 
					<div class="gSW_Con_WpIn"> 
						<table>
							<tbody><tr>
								<td valign="top" class="SW_Ico_Wp">&nbsp;&nbsp;&nbsp;&nbsp;<img width=40px height=40px src="../image/error/Smiley_Sad.png"></img></td>
								<td class="SW_Con_Wp">
									
									<span class="gSW_mCon">
										<b id="bTitle" class="fnt_Red">会话已过期, 请重新登录系统。</b>
										<div id="dvContent" class="mConIn" style="display: none;"></div>
									
									
									</span>
								
								</td>
							</tr>
						</tbody></table>
					</div>
				</div>

				<div class="gSW_Footer_Wp"> 
					<div class="gSW_Footer_WpIn">
						<div class="HrSub"><hr></div>
						
						<input type="button" hidefocus="true" onmousedown="this.className='Btn BtnHv BtnDw ImpBtn'" onmouseout="this.className='Btn BtnNml ImpBtn'" onmouseover="this.className='Btn BtnHv ImpBtn'" value="返回登录页面" onclick="goback();" class="Btn BtnNml ImpBtn" id="btnLogout">
						
					</div>
				</div>

			</div>
		</div>
<script type="text/javascript">
function goback()
{
	window.location.href="../login/login.htm";
}
</script>
</body>
</html>
