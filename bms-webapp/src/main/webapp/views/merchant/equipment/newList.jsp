<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.product.MerchantProductPath"%>
<%@page import="com.qifenqian.bms.merchant.equipment.MerchantSignPath"%>
<script src="/static/js/bootstrap-select.min.js"></script>
<script src="/static/js/bootstrap-select.js"></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />
<link href="/static/css/bootstrap-select.css" rel="stylesheet">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>商户设备管理</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
		li{list-style-type:none;}
		.displayUl{display:none;}
	</style>
	
</head>
<script type="text/javascript">


jQuery(function($){
			
	// 为每个tr缓存数据
	 var merchantSigns= '<c:out value="${gyzb:toJSONString(materielList)}" escapeXml="false"/>';
	 var merchantSignList=$("tr.merchantSign");
	 $.each($.parseJSON(merchantSigns),function(i,value){		 
	 	$.data(merchantSignList[i],"merchantSign",value);
		}); 

</script>	

<body>
	<!-- 物料信息 -->
	<%@ include file="/include/top.jsp"%>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<div class="main-container-inner">
			<!-- 菜单 -->
			<%@ include file="/include/left.jsp"%>
			
			<div class="main-content">
				<!-- 路径 -->
				<%@ include file="/include/path.jsp"%>
				
				<!-- 主内容 -->
				<div class="page-content">
				
					<div class="row">
						<div class="col-xs-12">
						<!-- 查询条件 -->
							<form action='<c:url value="/merchant/equipment/newList"/>' method="post" id="form">
							<table class="search-table">								
								<tr>
									<td class="td-left" >商户编号：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name=custId  id="custId"  value="${deviceLogina.custId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									
									<td class="td-left" >设备编号：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name=mac  id="mac"  value="${deviceLogina.mac }"><i class="icon-leaf blue"></i>
										</span>
									</td>									
								</tr>

								<tr>
									<td colspan="4" align="center">
										<span class="input-group-btn">																					
										
											<%-- <gyzb-admin:function url="<%=MerchantSignPath.BASE + MerchantSignPath.LIST%>"> --%>
											<gyzb-admin:function url="/merchant/equipment/newList">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
	
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">商户设备信息列表</div>
							<div class="table-responsive" >
								<table id="sample-table-2" class="list-table" >
									<thead>
										<tr>
											<th>商户名称</th>
											<th>商户编号	</th>
											<th style="width:17%">手机MAC</th>
											<th>手机号码</th>
											<th>客户端类型</th>
											<th>登录手机时间</th>
											<th>首次登录服务器时间</th>
											<th>最近一次登录服务器时间</th>
											<th style="width:6%">状态</th>											
										</tr>
									</thead>

									<tbody>

											<c:forEach items="${materielList}" var="deviceLogin">
											<tr class="merchantProduct">
												<td>${deviceLogin.merchantName}</td>
												<td>${deviceLogin.custId}</td>
												<td>${deviceLogin.mac}</td>																				
												<td>${deviceLogin.mobile}</td>										
												<td>${deviceLogin.clientType}</td>		
												<td>${deviceLogin.loginTimeM}</td>	
												<td>
													<fmt:formatDate value="${deviceLogin.loginTimeS}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td>
													<fmt:formatDate value="${deviceLogin.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>												
												<td>
													<c:if test="${deviceLogin.status =='1'}">
														登录
													</c:if>
													<c:if test="${deviceLogin.status =='0'}">
														注销
													</c:if>
												</td>
											</tr>
										</c:forEach>

										<c:if test="${empty materielList}">
											<tr><td colspan="11" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty materielList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div><!-- /.modal -->
				</div><!-- /.page-content -->
				
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->

		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
	
				
  </body>
</html>