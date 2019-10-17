<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.sevenpay.bms.basemanager.merchant.MerchantPath" %>
<%@page import="com.sevenpay.bms.basemanager.merchant.MerchantEnterPath" %>
<%@page import="com.sevenpay.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.sevenpay.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.sevenpay.bms.basemanager.agency.controller.AgentRegisterPath" %>
<% String url = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort(); %>
<link rel="stylesheet" href="/static/css/font-awesome.min.css">
<!-- page specific plugin styles -->
<link rel="stylesheet" href="/static/css/dropzone.css">

<link rel="stylesheet" href="/static/css/ace.min.css">
<link rel="stylesheet" href="/static/css/ace-rtl.min.css">
<link rel="stylesheet" href="/static/css/ace-skins.min.css">
<link rel="stylesheet" href="/static/css/gyzb-admin.css">
<link rel="stylesheet" href="/static/css/bootstrap-datetimepicker.min.css">
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src="/static/js/ace-extra.min.js"></script>
<script src="/static/js/jquery-2.0.3.min.js"></script>
<script src="/static/js/jquery.blockUI.js"></script>
<script src="/static/js/jquery.pagination.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<!-- page specific plugin scripts -->
<script src="/static/js/typeahead-bs2.min.js"></script>
<script src="/static/js/dropzone.min.js"></script>
		
<script src="/static/js/ace-elements.min.js"></script>
<script src="/static/js/ace.min.js"></script>
<script src="/static/js/gyzb-admin.js"></script>
<script src="/static/js/checkRule_source.js"></script>
<script src="/static/js/service.js"></script>
<script src="/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/WdatePicker.js"></script><link href="/static/js/WdatePicker.css" rel="stylesheet" type="text/css">
<script src="/static/js/AutoDatePicker.js"></script><link href="/static/js/AutoDatePicker.css" rel="stylesheet" type="text/css">
<script src="/static/js/jquery-ui.min.js"></script>
<script src="/static/js/jquery.divbox.js"></script>
<script src="/static/js/ajaxfileupload.js"></script>
<script src="/static/js/mobileBUGFix.mini.js"></script>
<script src="/static/js/uploadCompress.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>前台录入管理列表</title>
<meta name="keywords" content="七分钱后台管理系统" />
<meta name="description" content="七分钱后台管理" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
	.headlerPreview{ 
		background-color:#ffbf66; 
		text-align:center; 
		height:30px; 
		font-weight:bold;
	}
</style>
</head>
<script type="text/javascript">
$(function(){
	//导出商户数据
	$('.exportBut').click(function(){
		var merchantCode = $('.search-table #merchantCode').val();
		var startCreateTime = $('.search-table #startCreateTime').val();
		var endCreateTime = $('.search-table #endCreateTime').val();
		var auditState = $('.search-table #auditState').val();
		var custName = $('.search-table #custName').val();
		var email = $('.search-table #email').val();
		var merchantState = $('.search-table #merchantState').val();
		
		var src ="<%= request.getContextPath()+ MerchantPath.BASE+MerchantPath.PROEXPORTMERCHANTINFO%>?merchantCode="+
		merchantCode+
		"&startCreateTime="+
		startCreateTime+
		"&endCreateTime="+
		endCreateTime+
		"&auditState="+
		auditState+
		"&custName="+
		custName+
		"&email="+
		email+
		"&merchantState="+
		merchantState;
		$(".exportBut").attr("href",src);
		
	});
	
	$("#merchantDiv").draggable({ addClasses: false}); 
	$("#showImageDiv").draggable({ addClasses: false});  
	$("#updateMerchantClose").click(function(){
		$("#merchantDiv").css({"left":"0px","top":"0px"});
	});
	$("#previewImageModal").click(function(){
		$("#showImageDiv").css({"left":"0px","top":"0px"});
	})
	
	$(".firstAuditNotPassBtn").click(function(){
		$(".modal-backdrop").css("z-index","1042");
		
	})
	$(".messageDefault").click(function(){
		$(".modal-backdrop").css("z-index","1030");
	})
	$("#firstAuditMessageModel .close").click(function(){
		$(".modal-backdrop").css("z-index","1030");
	})
	$(".secondAuditNotPassBtn").click(function(){
		$(".modal-backdrop").css("z-index","1042");
	})
	$("#secondAuditMessageModel .close").click(function(){
		$(".modal-backdrop").css("z-index","1030");
	})	
	
	$("input[type=file]").each(
	function() {
		var _this = $(this);
		_this.localResizeIMG({
			quality : 0.8,
			success : function(result,file) {
				
				var att = pre.substr(pre.lastIndexOf("."));
				//压缩后图片的base64字符串
				var base64_string = result.clearBase64;
				
				$('#'+_this.attr('id')+'temp').val(att+","+base64_string);
				//图片预览
	             var imgObj = $('#'+_this.attr('id')+'Image');
	             imgObj.attr("src", "data:image/jpeg;base64," + base64_string).show(); 
	             
	             var imgObj2 = $('#'+_this.attr('id')+'Image2');
	             imgObj2.attr("src", "data:image/jpeg;base64," + base64_string).show(); 
	             
	             var width = result.width;
	             var height = result.height;
	             var scale =  width/height;
	             if(width >800){
	             width = 800;
	             height = width / scale;
	             }
	             $(".showDiv").width(width+"px");
	             $(".showDiv").height(height+"px");
	             
	           //优图
	             var param = "{str:\""+base64_string+"\",flag:\""+_this.attr('id')+"\"}"
	    		 $.ajax({
	    	   			async:false,
	    	   			type:"POST",
	    	   			contentType:"application/json;charset=utf-8",
	    	   			dataType:"text",
	    	   			url:window.Constants.ContextPath +'<%=AgentRegisterPath.BASE + AgentRegisterPath.YOUTU%>',
	    	   	        data:param,
	    	   	        success:function(data){
	    	   	      		var json = eval('(' + data + ')');
	    	   	        	if(json.result=="SUCCESS"){
	    	   	        		 if(_this.attr('id')=="certAttribute1"){//身份證
	    	       	  				$("#representativeName").val(json.cardName);
	    	       	  				$("#representativeCertNo").val(json.cardId);
	    	       	  			}else if(_this.attr('id')=="businessPhoto"){//营业执照
	    	       	  				$("#businessLicense").val(json.businessLicense);
	    	       	  				//$("#businessTerm").val(json.businessTermStart);
	    	       	  				if("长期"==json.businessTermEnd){
	    	       	  					//$("input[value='forever']").click();
	    	       	  				}else{
	    	       	  					alert(json.businessTermEnd);
	    	       	  				   $("#businessTerm").val(json.businessTermEnd);
	    	       	  					//$("#businessTermEnd").val(json.businessTermEnd);
	    	       	  				} 
	    	       	  				//$("#custAdd").val(json.legalAddress);
	    	       	  				$("#businessRegAddr").val(json.legalAddress);
	    	       	  			
	    	       	  			} 
	    	   				}
	    	   			}
	    	   	});
			}
		});
	});
})	
	
	
	
$(document).ready(function(){
	var resultList= ${merchantList};	
	var merchants=$("tr.merchant");
	$.each(resultList,function(i,value){		
		$.data(merchants[i],"merchant",value);				
	});
	
	$('.clearMerchantSearch').click(function(){
		$('.search-table #merchantCode').val('');
		$('.search-table #custName').val('');
		$('.search-table #email').val('');
		$('.search-table #state').val('');
		$('.search-table #auditState').val('');
		$('.search-table #startCreateTime').val('');
		$('.search-table #endCreateTime').val('');
		/* $('.search-table #filingStatus').val('');
		$('.search-table #filingAuditStatus').val(''); */
		
	}) 
	
	$('.buttonSearch').click(function(){
		var startDate = $("#startCreateTime").val();
		var endDate= $("#endCreateTime").val();
		if("" != startDate && "" != endDate && startDate > endDate) 
		{
			$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
			return false;
		}
		var form = $('#merchantForm');
		form.submit();
	});
});	

var winChild;
/** 跳转预览页面 **/
function previewMerchantEntry(obj){
	var merchantCode = $(obj).parent().find('input[name="merchantCode"]').val();
	var url=window.Constants.ContextPath+"<%=MerchantEnterPath.BASE + MerchantEnterPath.PREVIEW%>?merchantCode="+merchantCode; 
    var name="newwindow";                        
    var iWidth=1500;                          
    var iHeight=600;                      
    var iTop = (window.screen.availHeight-30-iHeight)/2;
    var iLeft = (window.screen.availWidth-10-iWidth)/2;
    var params='width='+iWidth
           +',height='+iHeight
           +',top='+iTop
           +',left='+iLeft;
  	winChild = window.open(url, name,params);
}
/** 跳转修改页面 **/
function upadteMerchantEntry(obj){
	var merchantCode = $(obj).parent().find('input[name="merchantCode"]').val();
	var url=window.Constants.ContextPath+"<%=MerchantEnterPath.BASE + MerchantEnterPath.UPDATE%>?merchantCode="+merchantCode; 
    var name="newwindow";                        
    var iWidth=1500;                          
    var iHeight=600;                      
    var iTop = (window.screen.availHeight-30-iHeight)/2;
    var iLeft = (window.screen.availWidth-10-iWidth)/2;
    var params='width='+iWidth
           +',height='+iHeight
           +',top='+iTop
           +',left='+iLeft;
  	winChild = window.open(url, name,params);
}
/** 跳转审核页面  **/
function aduitMerchantEntry(obj){
	var merchantCode = $(obj).parent().find('input[name="merchantCode"]').val();
	var url=window.Constants.ContextPath+"<%=MerchantEnterPath.BASE + MerchantEnterPath.AUDIT%>?merchantCode="+merchantCode; 
    var name="newwindow";                        
    var iWidth=1500;                          
    var iHeight=600;                      
    var iTop = (window.screen.availHeight-30-iHeight)/2;
    var iLeft = (window.screen.availWidth-10-iWidth)/2;
    var params='width='+iWidth
           +',height='+iHeight
           +',top='+iTop
           +',left='+iLeft;
  	winChild = window.open(url, name,params);
}
/** 跳转产品页面  **/
function productMerchantEntry(obj){
	var merchantCode = $(obj).parent().find('input[name="merchantCode"]').val();
	var url=window.Constants.ContextPath+"<%=MerchantEnterPath.BASE + MerchantEnterPath.PRODUCT%>?merchantCode="+merchantCode; 
    var name="newwindow";                        
    var iWidth=1500;                          
    var iHeight=600;                      
    var iTop = (window.screen.availHeight-30-iHeight)/2;
    var iLeft = (window.screen.availWidth-10-iWidth)/2;
    var params='width='+iWidth
           +',height='+iHeight
           +',top='+iTop
           +',left='+iLeft;
  	winChild = window.open(url, name,params);
}
</script>
<body  onload="loadMerchant()">
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
						<div id="door_temp">
						</div>
						<!-- 查询条件 -->
						<form  id="merchantEnterForm" action='<c:url value="<%=MerchantEnterPath.BASE + MerchantEnterPath.LIST %>"/>' method="post">
							<table class="search-table">
								<tbody>
								<tr>
									<td class="td-left">商户编号：</td>
									<td class="td-right"> 
										<span class="input-icon">
											<input type="text" name="merchantCode" id="merchantCode" value="">
											<i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left">商户名称：</td>
									<td class="td-right">
                                    <span class="input-icon">
											<input type="text" name="custName" id="custName" value="">
											<i class="icon-leaf blue"></i>
										</span>
									
									</td>
									
									<td class="td-left">邮箱账号：</td>
									<td class="td-right">
                                         <span class="input-icon">
											<input type="text" name="email" id="email" value="">
											<i class="icon-leaf blue"></i>
										</span>								   
										 
									</td>
								</tr>
								<tr>
									<td class="td-left">商户状态：</td>
									<td class="td-right">
                                    <select name="state" id="state">
										  <option value="">请选择 </option>
										  <option value="00"> 有效 </option>
										  <option value="01"> 待审核 </option>
										  <option value="02"> 注销 </option>
										  <option value="03"> 冻结 </option>
										  <option value="04"> 审核不通过 </option>
										 </select>
									    <label class="label-tips" id="businessRegAddrLab"></label> 
									</td>
									<td class="td-left">审核状态：</td>
									<td class="td-right"> 
										<select name="auditState" id="auditState">
										  <option value="">请选择 </option>
										  <option value="0"> 审核通过 </option>
										  <option value="1"> 待审核 </option>
										  <option value="2"> 审核不通过 </option>
										 </select>
									    <label class="label-tips" id="businessRegAddrLab"></label>
									</td>
									<td class="td-left">录入时间：</td>
									<td class="td-right">								   
									<input type="text" name="startCreateTime" id="startCreateTime" readonly value="" onFocus="WdatePicker({skin:&#39;whyGreen&#39;,maxDate:&#39;%y-%M-%d&#39;})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									-
									<input type="text" name="endCreateTime" id="endCreateTime" readonly value="" onFocus="WdatePicker({skin:&#39;whyGreen&#39;,maxDate:&#39;%y-%M-%d&#39;})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									</td>
								</tr>
                                <!-- <tr>
									<td class="td-left">报备状态：</td>
									<td class="td-right"> 
										<select name="filingStatus" id="filingStatus">
										  <option value="">请选择 </option>
										  <option value="00"> 未报备 </option>
										  <option value="01"> 已报备 </option>
										 </select>
									    <label class="label-tips" id="businessRegAddrLab"></label>
									</td>
									<td class="td-left">报备审核状态：</td>
									<td class="td-right"> 
										<select name="filingAuditStatus" id="filingAuditStatus">
										  <option value="">请选择 </option>
										  <option value="00"> 成功 </option>
										  <option value="99"> 失败 </option>
										  <option value="01"> 待审核 </option>
										  <option value="02"> 提交审核 </option>
										 </select>
									    <label class="label-tips" id="businessRegAddrLab"></label>
									</td>
								</tr> -->
									<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">
											<button type="submit" class="btn btn-purple btn-sm btn-margin  buttonSearch">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button>
											<button class="btn btn-purple btn-sm btn-margin clearMerchantSearch">
												清空
											<i class=" icon-move icon-on-right bigger-110"></i>
											</button>
											
										</span>
									</td>
								</tr>
								</tbody>
							</table>
						</form>
							
							<div style="margin:30px 0 10px 0">
								<a href="<%=request.getContextPath()+MerchantEnterPath.BASE + MerchantEnterPath.ADDPAGE%>"  class="tooltip-success transactionLink" >
									<button type="submit" class="btn btn-primary">新增</button>
								</a>
                               <button type="button"  class="btn btn-primary">新增</button>
                               <button type="button"  class="btn btn-primary">重置密码</button>
                               <button type="button"  class="btn btn-primary" disabled="disabled">批量审核资料</button>
                               <button type="button"  class="btn btn-primary" disabled="disabled">批量录入</button>
                               <button type="button"  class="btn btn-primary">导出</button>
                               <button type="button"  class="btn btn-primary">打印二维码</button>
                            </div>
                            
							<div class="list-table-header">商户列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
                                            <th><input name="" type="checkbox" value=""></th>
											<th>商户名称</th>
											<th>商户简称</th>
											<th>商户编号</th>	
											<th>邮箱账号</th>
                                            <th width="12%">录入时间</th>	
											<th>商户状态</th>
											<th>审核状态</th>	
                                            <!-- <th>报备状态</th>
                                            <th>报备审核状态</th> -->
											<th width="10%">审核人</th>									
											<th width="23%">操作</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${merchantList }" var="merchant">
										<tr class="merchant" id="merchant">
											<td><input name="" type="checkbox" value=""></td>
                                            <td>${merchant.custName }</td>
											<td>${merchant.shortName }</td>
											<td>${merchant.merchantCode }</td>
											<td>${merchant.email }</td>
											<td>${merchant.createTime }</td>
											<td>
												<c:choose>   
							                        <c:when test="${merchant.state =='00'}">  									  
							                                                                             有效  								  
							                        </c:when>  
							  						<c:when test="${merchant.state =='01'}">  									  
							                                                                             待审核  								  
							                        </c:when> 
							                        <c:when test="${merchant.state =='02'}">  									  
							                                                                              注销  								  
							                        </c:when>
							                        <c:when test="${merchant.state =='03'}">  									  
							                                                                              冻结  								  
							                        </c:when>
							                        <c:when test="${merchant.state =='04'}">  									  
							                                                                              审核不通过  								  
							                        </c:when>
	                                            </c:choose>
											</td>
											<td>${merchant.auditState }
												<c:choose>   
							                        <c:when test="${merchant.auditState =='0'}">  									  
							                                                                              审核通过  								  
							                        </c:when>  
							  						<c:when test="${merchant.auditState =='1'}">  									  
							                                                                              待审核  								  
							                        </c:when> 
							                        <c:when test="${merchant.auditState =='2'}">  									  
							                                                                               审核不通过  								  
							                        </c:when>
	                                            </c:choose>
	                                        </td>
											<%-- <td>
												<c:choose>   
							                        <c:when test="${merchant.filingStatus =='00'}">  									  
							                                                                             未报备  								  
							                        </c:when>  
							  						<c:when test="${merchant.filingStatus =='01'}">  									  
							                                                                             已报备 								  
							                        </c:when> 
							                        <c:when test="${merchant.state =='02'}">  									  
							                                                                              注销  								  
							                        </c:when>
	                                            </c:choose>
											</td>
											<td>${merchant.filingAuditStatus }
												<c:choose>   
							                        <c:when test="${merchant.filingAuditStatus =='00'}">  									  
							                                                                             成功  								  
							                        </c:when>  
							  						<c:when test="${merchant.filingAuditStatus =='99'}">  									  
							                                                                             失败 								  
							                        </c:when> 
							                        <c:when test="${merchant.filingAuditStatus =='01'}">  									  
							                                                                              待审核  								  
							                        </c:when>
							                        <c:when test="${merchant.filingAuditStatus =='02'}">  									  
							                                                                              提交审核  								  
							                        </c:when>
	                                            </c:choose>
											</td> --%>
											<td>${merchant.aduitUserName }</td>
											<td>
												<input type="hidden" name="merchant" value="${merchant.merchantCode }" />
												<gyzbadmin:function url="<%=MerchantEnterPath.BASE + MerchantEnterPath.PREVIEW %>">
													<a href="#" class="" onclick="previewMerchantEntry(this)" data-rel="tooltip" title="Query" data-toggle='modal'>
														<button type="submit" class="btn btn-purple btn-sm">预览</button>
													</a>
												</gyzbadmin:function>
												<gyzbadmin:function url="<%=MerchantEnterPath.BASE + MerchantEnterPath.UPDATEPAGE %>">
													<a href="#" class="" onclick="upadteMerchantEntry(this)" data-rel="tooltip" title="Query" data-toggle='modal'>
														<button type="submit" class="btn btn-purple btn-sm">商户更新</button>
													</a>
												</gyzbadmin:function>
												<gyzbadmin:function url="<%=MerchantEnterPath.BASE + MerchantEnterPath.AUDITPAGE%>">
													<a href="#" class="" onclick="aduitMerchantEntry(this)" data-rel="tooltip" title="Query" data-toggle='modal'>
														<button type="submit" class="btn btn-purple btn-sm">商户审核</button>
													</a>
												</gyzbadmin:function>
												<!-- 报备链接未建 -->
												<gyzbadmin:function url="<%=MerchantEnterPath.BASE + MerchantEnterPath.PRODUCT %>">
													<a href="#" class="" onclick="productMerchantEntry(this)" data-rel="tooltip" title="Query" data-toggle='modal'>
														<button type="submit" class="btn btn-purple btn-sm">报备</button>
													</a>
												</gyzbadmin:function>
												<gyzbadmin:function url="<%=MerchantEnterPath.BASE + MerchantEnterPath.PRODUCT %>">
													<a href="#" class="" onclick="productMerchantEntry(this)" data-rel="tooltip" title="Query" data-toggle='modal'>
														<button type="submit" class="btn btn-purple btn-sm">产品查看</button>
													</a>
												</gyzbadmin:function>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty merchantList}">
										<tr><td colspan="20" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
									</c:if>
									</tbody>
								</table>
								<!-- 分页 -->
								<c:if test="${not empty merchantList}">
								<%@ include file="/include/page.jsp"%>
								</c:if>
							</div>
						</div>
					</div>
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


	<!-- 图片预览 -->
	<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog showDiv" id="imageDiv" style="width:60%;height:80%;">
	         <div id="showImageDiv" style="width:100%;height:100%;">
	           <img id="showImage" style="width:100%;height:100%;">
	         </div>
	     </div>
	</div>        
</body>

<script type="text/javascript">
</script>
</html>