<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.seven.micropay.channel.enums.BestBankCode"%>
<%@page import="com.qifenqian.bms.merchant.settle.MerchantSettlePath"%>
<%@page import="com.qifenqian.bms.merchant.merchantReported.MerchantEnterReportedPath"%>
<script src='<c:url value="/static/js/up.js"/>'></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />
<html>
<head>
	<meta charset="utf-8" />
	<title>商户报备列表</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<script type="text/javascript">

	$('.buttonSearch').click(function(){
		
		var form = $('#merchantForm');
		form.submit();
	});
	
	 $(document).ready(function(){
		$('.clearMerchantSearch').click(function(){
	        $('.search-table #merchantCode').val('');
	        $('.search-table #custName').val('');
	        $('.search-table #email').val('');
	        $('.search-table #channelNo').val('');
	        $('.search-table #reportStatus').val('');
	        $('.search-table #startModifyTime').val('');
	        $('.search-table #endModifyTime').val('');
	        /* $('.search-table #filingStatus').val('');
	        $('.search-table #filingAuditStatus').val(''); */
	
	    })
	 })
	 function getWeChatUpdate(obj){
		 
		var merchantCode=$(obj).parent().find('#merchantCode_').val();
		var patchNo = $(obj).parent().find("#patchNo_").val();
		var channlCode =$(obj).parent().find('#channelNo_').val();
		var bestMerchantType = $(obj).parent().find('#bestMerchantType_').val();
		var loginNo = $(obj).parent().find('#loginNo_').val();
		var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
		var url=window.Constants.ContextPath+"<%="/merchant/merchantReported/weChatAppUpgradeMerchantReport"%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&patchNo="+patchNo; 
     	var name="window";                        //网页名称，可为空;
     	var iWidth=1200;                          //弹出窗口的宽度;
     	var iHeight=600;                       //弹出窗口的高度;
     	//获得窗口的垂直位置
     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
     	//获得窗口的水平位置
     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
     	var params='width='+iWidth
            +',height='+iHeight
            +',top='+iTop
            +',left='+iLeft; 
     	/*  $.blockUI();  */
      	winChild =  window.open(url, name,params);
     	
      	window.location.reload();
	 }
	 
	 
	 
	//查看报备信息
	function getInfo(obj){
		var merchantCode=$(obj).parent().find('#merchantCode_').val();
		var patchNo = $(obj).parent().find("#patchNo_").val();
		var channlCode =$(obj).parent().find('#channelNo_').val();
		var bestMerchantType = $(obj).parent().find('#bestMerchantType_').val();
		var loginNo = $(obj).parent().find('#loginNo_').val();
		var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
		
		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.MERCHANTREPORTINFO%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&patchNo="+patchNo; 
     	var name="window";                        //网页名称，可为空;
     	var iWidth=1200;                          //弹出窗口的宽度;
     	var iHeight=600;                       //弹出窗口的高度;
     	//获得窗口的垂直位置
     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
     	//获得窗口的水平位置
     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
     	var params='width='+iWidth
            +',height='+iHeight
            +',top='+iTop
            +',left='+iLeft; 
     	/*  $.blockUI();  */
      	winChild =  window.open(url, name,params);
     	
      	window.location.reload();
		<%-- 
		if("BEST_PAY" == channlCode){
	   	   	
	   		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.BESTPAYMERCHANTINFO%>?merchantCode="+merchantCode+"&channlCode="+channlCode; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	   		 
   	   	}
   	   	if("SUIXING_PAY" == channlCode){
   	   	   	
   	   		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.SUIXINGMERCHANTINFO%>?merchantCode="+merchantCode+"&channlCode="+channlCode; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
   	   	}
		 
		if("YQB" == channlCode){
	   	   	
	   		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.YQBMERCHANTINFO%>?merchantCode="+merchantCode+"&channlCode="+channlCode; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	   	}
		
		if("KFT_PAY" == channlCode){
	   	   	
	   		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.KFTMERCHANTINFO%>?merchantCode="+merchantCode+"&channlCode="+channlCode; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	   	}
		window.location.reload(); --%>
	}
	
	//商户进件状态查询
	function getStatus(obj){
	  	
		var merchantCode=$(obj).parent().find('#merchantCode_').val();
		var patchNo = $(obj).parent().find("#patchNo_").val();
		var channlCode =$(obj).parent().find('#channelNo_').val();
		var bestMerchantType = $(obj).parent().find('#bestMerchantType_').val();
		var loginNo = $(obj).parent().find('#loginNo_').val();
		var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:window.Constants.ContextPath+'<%=MerchantReportedPath.BASE+ MerchantReportedPath.SELECTMERCHANTREPORTSTATUS %>',
			data:
			{
				"merchantCode" 	: merchantCode,
				"patchNo" : patchNo,
				"bestMerchantType" : bestMerchantType,
				"loginNo" : loginNo,
				"channelNo" : channlCode,
				"outMerchantCode" : outMerchantCode
			},
			success:function(data){
				if(data.result=="SUCCESS"){
					if(null == data.message || "" ==data.message){
						alert("查询成功");
						window.location.reload();
					}else{
						if("02" == data.message){
							//进件成功后签约页面
							alert("查询成功");
							window.location.reload();
							
						}else{
							alert(data.message);
							window.location.reload();
						}
					}
				}else{
					if(null == data.message || "" ==data.message){
						alert("查询失败");
					}else{
						alert(data.message);
						window.location.reload();
					}
				}
			}
		});
	}	

	//商户更新进件
	function getUpdate(obj){
		var status ="reported";
		var merchantCode=$(obj).parent().find('#merchantCode_').val();
		var patchNo = $(obj).parent().find("#patchNo_").val();
		var channlCode =$(obj).parent().find('#channelNo_').val();
		var bestMerchantType = $(obj).parent().find('#bestMerchantType_').val();
		var loginNo = $(obj).parent().find('#loginNo_').val();
		var outMerchantCode = $(obj).parent().find('#outMerchantCode_').val();
		var remark = $(obj).parent().find('#remark_').val();
		var reportStatus = $(obj).parent().find('#reportStatus_').val();
		var resultMsg =  $(obj).parent().find('#resultMsg_').val();
		if(null != resultMsg){
			alert("进件失败原因" + resultMsg);
		}
		
		if("BEST_PAY" == channlCode){
	   	   	
	   		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.UPDATEBESTPAYREPORT%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	   		 
   	   	}
   	   	if("SUIXING_PAY" == channlCode){
   	   	   	if(null == outMerchantCode){
   	   	   		
	   	   	  	var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.SUIXINGMERCHANTREPORT%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode; 
		     	var name="window";                        //网页名称，可为空;
		     	var iWidth=1200;                          //弹出窗口的宽度;
		     	var iHeight=600;                       //弹出窗口的高度;
		     	//获得窗口的垂直位置
		     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
		     	//获得窗口的水平位置
		     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
		     	var params='width='+iWidth
		            +',height='+iHeight
		            +',top='+iTop
		            +',left='+iLeft; 
		     	/*  $.blockUI();  */
		      	winChild =  window.open(url, name,params);
   	   	   	}else{
   	   	   		
	   	   	    var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.UPDATESUNXINGREPORT%>?merchantCode="+merchantCode+"&status="+status+"&reportStatus="+reportStatus+"&patchNo="+patchNo+"&remark="+remark+"&channlCode="+channlCode; 
		     	var name="window";                        //网页名称，可为空;
		     	var iWidth=1200;                          //弹出窗口的宽度;
		     	var iHeight=600;                       //弹出窗口的高度;
		     	//获得窗口的垂直位置
		     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
		     	//获得窗口的水平位置
		     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
		     	var params='width='+iWidth
		            +',height='+iHeight
		            +',top='+iTop
		            +',left='+iLeft; 
		     	/*  $.blockUI();  */
		      	winChild =  window.open(url, name,params);
   	   	   	}
   	   		
   	   	}
		if("YQB" == channlCode){
	   	   	
	   		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.UPDATEYQBREPORT%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	   	}
		if("KFT_PAY" == channlCode){
	   	   	
	   		var url=window.Constants.ContextPath+"<%=MerchantEnterReportedPath.BASE+ MerchantEnterReportedPath.UPDATEKFTREPORT%>?merchantCode="+merchantCode+"&status="+status+"&channlCode="+channlCode; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	   	}
		window.location.reload();
		
	}

   	var winChild
   	function bottonSearch() {

   		//判定是否已进件
   		         
   		var merchantCode=$("#merchantCode").val();
   		var channlCode =$("#channlCode").val();
   		var merchantType =$("#merchantType").val();
   		var status ="unReported";
   		if(kong.test($("#merchantCode").val())){
   			$("#merchantCodeLab").text("请填写商户号");
   			return false;
   		}
   		if(kong.test($("#channlCode").val())){
   			$("#channlCodeLabel").text("请选择渠道");
   			return false;
   		}
   		if("BEST_PAY" == channlCode){
   	   		 if("01" == merchantType){
   	   	   		 //翼支付微小型商户页面
	   	   		 var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.BESTPAYMERCHANTREPORTS%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&status="+status; 
		 	     var name="window";                        //网页名称，可为空;
		 	     var iWidth=1200;                          //弹出窗口的宽度;
		 	     var iHeight=600;                       //弹出窗口的高度;
		 	     //获得窗口的垂直位置
		 	     var iTop = (window.screen.availHeight-30-iHeight)/2; 
		 	     //获得窗口的水平位置
		 	     var iLeft = (window.screen.availWidth-10-iWidth)/2;
		 	     var params='width='+iWidth
		 	            +',height='+iHeight
		 	            +',top='+iTop
		 	            +',left='+iLeft; 
		 	     /*  $.blockUI();  */
		 	      winChild =  window.open(url, name,params); 
   	   	   	 }else{
   	   	   	   	//翼支付企业页面
	   	   	    var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.BESTPAYCOMERCHANTREPORTS%>?merchantCode="+merchantCode+"&merchantType="+merchantType+"&status="+status; 
		     	var name="window";                        //网页名称，可为空;
		     	var iWidth=1200;                          //弹出窗口的宽度;
		     	var iHeight=600;                       //弹出窗口的高度;
		     	//获得窗口的垂直位置
		     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
		     	//获得窗口的水平位置
		     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
		     	var params='width='+iWidth
		            +',height='+iHeight
		            +',top='+iTop
		            +',left='+iLeft; 
		     	/*  $.blockUI();  */
		      	winChild =  window.open(url, name,params);

   	   	   	 }
	   		 
   	   	}
   	   	if("SUIXING_PAY" == channlCode){
   	   	   	
   	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.SUIXINGPAYMERCHANTREPORTS%>?merchantCode="+merchantCode+"&status="+status; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
   	   	}
		 
   		if("SUM_PAY" == channlCode){
	   	   	
	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.SUMPAYMERCHANTREPORTS%>?merchantCode="+merchantCode+"&status="+status; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	   	}

		if("YQB" == channlCode){
	   	   	
	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.YQBMERCHANTREPORTS%>?merchantCode="+merchantCode+"&status="+status; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	   	}
	   
    }

	function bottonSearch1(obj) {
		var merchantCode=$(obj).parent().find('#merchantCode_').val();      
  		var channlCode =$(obj).parent().find('#channelNo_').val();
  		var outMerchantCode =$(obj).parent().find('#outMerchantCode_').val();    
  		var status ="unReported";
		if("SUM_PAY" == channlCode){
	   	   	
	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.SUMPAYMERCHANTINFOREPORTS%>?merchantCode="+merchantCode+"&status="+status+"&outMerchantCode="+outMerchantCode; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
	   	}
	}
   	function updateBottonSearch(obj) {
   	   	
   		 var merchantCode=$(obj).parent().find('#merchantCode_').val();      
   		 var channlCode =$(obj).parent().find('#channelNo_').val();
   		 var status ="reported";
   		 if("BEST_PAY" == channlCode){
   	   		 
   			 var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.BESTPAYMERCHANTREPORTS%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&status="+status; 
	   	     var name="window";                        //网页名称，可为空;
	   	     var iWidth=1200;                          //弹出窗口的宽度;
	   	     var iHeight=600;                       //弹出窗口的高度;
	   	     //获得窗口的垂直位置
	   	     var iTop = (window.screen.availHeight-30-iHeight)/2; 
	   	     //获得窗口的水平位置
	   	     var iLeft = (window.screen.availWidth-10-iWidth)/2;
	   	     var params='width='+iWidth
	   	            +',height='+iHeight
	   	            +',top='+iTop
	   	            +',left='+iLeft; 
	   	     /*  $.blockUI();  */
	   	      winChild =  window.open(url, name,params);
   	   	 }	
		 if("SUIXING_PAY" == channlCode){
   	   	   	
   	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.SUIXINGPAYMERCHANTREPORTS%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&status="+status; 
	     	var name="window";                        //网页名称，可为空;
	     	var iWidth=1200;                          //弹出窗口的宽度;
	     	var iHeight=600;                       //弹出窗口的高度;
	     	//获得窗口的垂直位置
	     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	     	//获得窗口的水平位置
	     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
	     	var params='width='+iWidth
	            +',height='+iHeight
	            +',top='+iTop
	            +',left='+iLeft; 
	     	/*  $.blockUI();  */
	      	winChild =  window.open(url, name,params);
   	   	 }
		 if("SUM_PAY" == channlCode){
	   	   	   	
	   	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.SUMPAYMERCHANTREPORTS%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&status="+status; 
		     	var name="window";                        //网页名称，可为空;
		     	var iWidth=1200;                          //弹出窗口的宽度;
		     	var iHeight=600;                       //弹出窗口的高度;
		     	//获得窗口的垂直位置
		     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
		     	//获得窗口的水平位置
		     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
		     	var params='width='+iWidth
		            +',height='+iHeight
		            +',top='+iTop
		            +',left='+iLeft; 
		     	/*  $.blockUI();  */
		      	winChild =  window.open(url, name,params);
	   	  }
		 if("YQB" == channlCode){
	   	   	   	
	   	   		var url=window.Constants.ContextPath+"<%=MerchantReportedPath.BASE+ MerchantReportedPath.YQBMERCHANTREPORTS%>?merchantCode="+merchantCode+"&channlCode="+channlCode+"&status="+status; 
		     	var name="window";                        //网页名称，可为空;
		     	var iWidth=1200;                          //弹出窗口的宽度;
		     	var iHeight=600;                       //弹出窗口的高度;
		     	//获得窗口的垂直位置
		     	var iTop = (window.screen.availHeight-30-iHeight)/2; 
		     	//获得窗口的水平位置
		     	var iLeft = (window.screen.availWidth-10-iWidth)/2;
		     	var params='width='+iWidth
		            +',height='+iHeight
		            +',top='+iTop
		            +',left='+iLeft; 
		     	/*  $.blockUI();  */
		      	winChild =  window.open(url, name,params);
	   	  }
	   
    }
    
  	//翼支付企业类型不同样式不同 
  	function selectChannlCode(){
  		if($("#channlCode").val() =='BEST_PAY'){
  			$("#merchantType1").attr("style","display:");
  			$("#merchantType2").attr("style","display:");
  	  	}else{
  	  		$("#merchantType1").attr("style","display:none");
  	  		$("#merchantType2").attr("style","display:none");
  	  	}
  	}

  	
  	function getContarct(obj) {
  		var merchantCode = $(obj).parents("tr").find("#merchantCode_").val();
		$("#contractMerchantModal").find("#merchantCode0").val(merchantCode);
	}

  	function bottonContract(){
  		var merchantCode=$("#contractMerchantModal").find("#merchantCode0").val();
  		var productCode=$("#contractMerchantModal").find("#productCode").val();
  		var feeFlag=$("#contractMerchantModal").find("#feeFlag").val();
  		var feeMode=$("#contractMerchantModal").find("#feeMode").val();
  		var billingMode=$("#contractMerchantModal").find("#billingMode").val();
  		var billingValue=$("#contractMerchantModal").find("#billingValue").val();
  		var feeBeginAmt=$("#contractMerchantModal").find("#feeBeginAmt").val();
  		var bottomAmt=$("#contractMerchantModal").find("#bottomAmt").val();
  		var ceilingAmt=$("#contractMerchantModal").find("#ceilingAmt").val();
  		var feeStatus=$("#contractMerchantModal").find("#feeStatus").val();
  		var feeCycle=$("#contractMerchantModal").find("#feeCycle").val();
  		var refundFeeFlag=$("#contractMerchantModal").find("#refundFeeFlag").val();
		var offlineAgreementPath=$("#contractMerchantModal").find("#offlineAgreementPath").val();
		var createdBy = $("#contractMerchantModal").find("#createdBy").val();
  		if(kong.test(refundFeeFlag)){
			$.gyzbadmin.alertFailure("退款手续费标识不可为空");
			return;
		}
  		if(kong.test(feeCycle)){
			$.gyzbadmin.alertFailure("金额累计周期不可为空");
			return;
		}
  		if(kong.test(feeStatus)){
			$.gyzbadmin.alertFailure("收费状态不可为空");
			return;
		}
  		if(kong.test(ceilingAmt)){
			$.gyzbadmin.alertFailure("封顶金额不可为空");
			return;
		}
  		if(kong.test(merchantCode)){
			$.gyzbadmin.alertFailure("商户号不可为空");
			return;
		}
  		/* if(kong.test(productCode)){
			$.gyzbadmin.alertFailure("产品编号不可为空");
			return;
		} */
  		if(kong.test(feeFlag)){
			$.gyzbadmin.alertFailure("是否收费标识不可为空");
			return;
		}
  		if(kong.test(feeMode)){
			$.gyzbadmin.alertFailure("收费模式不可为空");
			return;
		}
  		if(kong.test(billingMode)){
			$.gyzbadmin.alertFailure("计费模式不可为空");
			return;
		}
  		if(kong.test(billingValue)){
			$.gyzbadmin.alertFailure("费率值不可为空");
			return;
		}
  		if(kong.test(feeBeginAmt)){
			$.gyzbadmin.alertFailure("起费金额不可为空");
			return;
		}
  		if(kong.test(bottomAmt)){
			$.gyzbadmin.alertFailure("封底金额不可为空");
			return;
		}
		if(billingValue !=0.35){
			if(kong.test(offlineAgreementPath)){
				$.gyzbadmin.alertFailure("文件路径不可为空");
				return;
			}
		}
		if(kong.test(createdBy)){
			$.gyzbadmin.alertFailure("创建人不可为空");
			return;
		}
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.CONTRACTPRODUCT%>?productCode="+productCode+"&offlineAgreementPath="+offlineAgreementPath,
				{
					'merchantCode':merchantCode,
					'feeFlag':feeFlag,
					'feeMode':feeMode,
					'billingMode':billingMode,
					'billingValue':billingValue,
					'feeBeginAmt':feeBeginAmt,
					'bottomAmt': bottomAmt,
					'ceilingAmt' :ceilingAmt,
					'feeStatus' :feeStatus,
					'feeCycle': feeCycle,
					'refundFeeFlag':refundFeeFlag,
					'createdBy' : createdBy
				},
				function(data){
					
					if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("签约成功！",function(){
								$("#contractMerchantModal").modal("hide");
							},function(){
								window.location.reload();
							});
					}else{
							
							window.location.reload();
							$.gyzbadmin.alertFailure("签约失败！"+data.message,function(){
								$("#contractMerchantModal").modal("hide");
							});
					}
				},'json'

				);
  	}

  	function getContarct1(obj) {
  		var merchantCode = $(obj).parents("tr").find("#merchantCode_").val();
  		var custName = $(obj).parents("tr").find("#custName_").val();
  		var outMerchantCode = $(obj).parents("tr").find("#outMerchantCode_").val();
		$("#contractMerchantModal1").find("#merchantCode1").val(merchantCode);
		$("#contractMerchantModal1").find("#custName1").val(custName);
		$("#contractMerchantModal1").find("#outMerchantCode1").val(outMerchantCode);

		//存token
		$.post(window.Constants.ContextPath + '<%=MerchantSettlePath.BASE + MerchantSettlePath.SAVE_TOKEN %>', {
		}, function(data) {
			var token = data.token ;
			$("#_token").val(token);
		}, 'json'
		);
	}

  	function bottonContract1(){
  		var merchantCode = $("#contractMerchantModal1").find("#merchantCode1").val();
  		var custName = $("#contractMerchantModal1").find("#custName1").val();
  		var mcc = $("#contractMerchantModal1").find("#levelThree").val();
  		var wechatMchntType = $("#contractMerchantModal1").find("#levelThreeWx").val();
  		var rate = $("#contractMerchantModal1").find("#rate").val();
  		var mchntType = $("#contractMerchantModal1").find("#levelOneZfb").val();
  		var outMerchantCode = $("#contractMerchantModal1").find("#outMerchantCode1").val();
  		$.blockUI();
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.OPENPRODUCT%>?merchantCode="+merchantCode+"&custName="+custName+"&outMerchantCode="+outMerchantCode,
				{
  				'mchntType' : mchntType,
				'mcc' : mcc,
				'wechatMchntType' : wechatMchntType,
				'rate' : rate,
				'_TOKEN_ID':$("#_token").val()
				},
				function(data){
					$.unblockUI();
					if(data.result=="SUCCESS"){
							$.gyzbadmin.alertSuccess("开通成功！",function(){
								
								$("#contractMerchantModal1").modal("hide");
							},function(){
								window.location.reload();
							});
					}else{
							$.gyzbadmin.alertFailure("开通失败！"+data.message,function(){
								$("#contractMerchantModal1").modal("hide");
							});
					}
				},'json'

				);
  	}

  	/***获取二级类目***/
  	function getLevelTwo(){
  		var one = $("#levelOne").val().trim();
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELZFBPRODUCT %>",
		{
			"one":one
		},
		function(data){
			if(data.result=="SUCCESS"){
				var sumPayMccList = data.sumPayMccList;
				$("#levelTwo").html("");
       			for ( var sumPayMcc in sumPayMccList) {
       				$("#levelTwo").append(
       						"<option value='"+ sumPayMccList[sumPayMcc].levelTwo +"'>"
       								+ sumPayMccList[sumPayMcc].levelTwo + "</option>"); 
       			}
       			getLevelThree();
			}
		},'json'
		);	
  	}

	/***获取三级类目***/
  	function getLevelThree(){
  		var levelOne = $("#levelOne").val().trim();
  		var levelTwo = $("#levelTwo").val().trim();
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELZFBPRODUCTMCC %>",
		{
			"levelOne":levelOne,
			"levelTwo":levelTwo
		},
		function(data){
			if(data.result=="SUCCESS"){
				var sumPayMccList = data.sumPayMccList;
				$("#levelThree").html("");
       			for ( var sumPayMcc in sumPayMccList) {
       				$("#levelThree").append(
       						"<option value='"+ sumPayMccList[sumPayMcc].mcc +"'>"
       								+ sumPayMccList[sumPayMcc].levelThree + "</option>");
       			}
			}
		},'json'
		);	
  	}

  	/***获取WX二级类目***/
  	function getLevelTwoWx(){
  		var one = $("#levelOneWx").val().trim();
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELWXPRODUCT %>",
		{
			"one":one
		},
		function(data){
			if(data.result=="SUCCESS"){
				var sumPayMccList = data.sumPayMccList;
				$("#levelTwoWx").html("");
       			for ( var sumPayMcc in sumPayMccList) {
       				$("#levelTwoWx").append(
       						"<option value='"+ sumPayMccList[sumPayMcc].levelTwo +"'>"
       								+ sumPayMccList[sumPayMcc].levelTwo + "</option>"); 
       			}
       			getLevelThreeWx();
			}
		},'json'
		);	
  	}

	/***获取WX三级类目***/
  	function getLevelThreeWx(){
  		var levelOne = $("#levelOneWx").val().trim();
  		var levelTwo = $("#levelTwoWx").val().trim();
  		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELWXPRODUCTMCC %>",
		{
  			"levelOne":levelOne,
			"levelTwo":levelTwo
		},
		function(data){
			if(data.result=="SUCCESS"){
				var sumPayMccList = data.sumPayMccList;
				$("#levelThreeWx").html("");
       			for ( var sumPayMcc in sumPayMccList) {
       				$("#levelThreeWx").append(
       						"<option value='"+ sumPayMccList[sumPayMcc].wechatMchntType +"'>"
       								+ sumPayMccList[sumPayMcc].levelThree + "</option>");
       			}
			}
		},'json'
		);	
  	}
</script>

<body >
	<!-- 用户信息 -->
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
							<div class="nino" style="display:none; text-align: center; color: #438eb9;">
								<h3 >商户行业类别信息：<span class="showInfo"></span></h3>
							</div>
							<!-- 报备 -->
							<form id ="merchantForm" action='<c:url value="<%=MerchantEnterReportedPath.BASE +MerchantEnterReportedPath.LIST %>"/>' name="fileUpload" enctype="multipart/form-data" method="post" >
							<input type="hidden" id="_token" name="_token"/>
							<table class="search-table" style="margin: 40px 0;">
							<tr>
								<td class="td-left">商户编号：</td>
								<td class="td-right"> 
									<span class="input-icon">
										<input type="text" name="merchantCode" id="merchantCode" value="${queryBean.merchantCode}">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left">商户名称：</td>
								<td class="td-right">
                                   <span class="input-icon">
										<input type="text" name="custName" id="custName" value="${queryBean.custName}">
										<i class="icon-leaf blue"></i>
									</span>
								</td>
								<td class="td-left">邮箱账号：</td>
								<td class="td-right">
                                        <span class="input-icon">
										<input type="text" name="email" id="email" value="${queryBean.email}">
										<i class="icon-leaf blue"></i>
									</span>								   
								</td>
							</tr>
							<tr>
								<td class="td-left">报备渠道：</td>
								<td class="td-right">
                                   <select name="channelNo" id="channelNo">
									  <option value="">请选择报备渠道 </option>
									  <c:if test="${not empty infoList }">
			                            <c:forEach items="${infoList }" var="info">
			                            	<option id="${info.channlId}" value="${info.channlCode}">${info.channlName}</option>
			                            </c:forEach>
	                    		  	  </c:if>
									</select>
								</td>
								<td class="td-left">报备状态：</td>
								<td class="td-right"> 
									<select name="reportStatus" id="reportStatus">
									  <option value="">请选择 </option>
									  	  <option value="00"> 报备成功     </option>
										  <option value="99"> 报备失败     </option>
										  <option value="0">  提交审核中 </option>
										  <option value="1">  审核通过     </option>
										  <option value="2">  审核不通过     </option>
									 </select>
								</td>
								<td class="td-left">报备时间：</td>
								<td class="td-right">								   
									<input type="text" name="startModifyTime" id="startModifyTime" readonly value="" onFocus="WdatePicker({skin:&#39;whyGreen&#39;,maxDate:&#39;%y-%M-%d&#39;})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									-
									<input type="text" name="endModifyTime" id="endModifyTime" readonly value="" onFocus="WdatePicker({skin:&#39;whyGreen&#39;,maxDate:&#39;%y-%M-%d&#39;})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								</td>
							</tr>
							<!-- 
                            <tr>
                            	
								<td class="td-left">报备时间：</td>
								<td class="td-right">								   
									<input type="text" name="startModifyTime" id="startModifyTime" readonly value="" onFocus="WdatePicker({skin:&#39;whyGreen&#39;,maxDate:&#39;%y-%M-%d&#39;})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									-
									<input type="text" name="endModifyTime" id="endModifyTime" readonly value="" onFocus="WdatePicker({skin:&#39;whyGreen&#39;,maxDate:&#39;%y-%M-%d&#39;})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								</td>
                                <td class="td-left">报备成功时间：</td>
								<td class="td-right">								   
									<input type="text" name="startCreateTime" id="startCreateTime" readonly value="" onFocus="WdatePicker({skin:&#39;whyGreen&#39;,maxDate:&#39;%y-%M-%d&#39;})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
									-
									<input type="text" name="endCreateTime" id="endCreateTime" readonly value="" onFocus="WdatePicker({skin:&#39;whyGreen&#39;,maxDate:&#39;%y-%M-%d&#39;})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> 
								</td>
							</tr>
							-->
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
							
							</table>
							</form>
							
							<div class="list-table-header">商户报备数据列表</div>
							<div class="table-responsive">
								<table id="sample-table-2" class="list-table">
									<thead>
										<tr>
											<th width="10%">商户名称</th>
											<th width="10%">商户简称</th>
											<th width="10%">商户编号</th>
											<th width="10%">邮箱账号</th>
											<th width="5%">报备渠道</th>
											<th width="10%">渠道商户号</th>
											<th width="7%">商户报备状态</th>
											<th width="14%">返回信息</th>
											<th width="10%">报备时间</th>
											<th width="14%">操作</th>
										</tr>
									</thead>
									<tbody>
									  <c:forEach items="${reportedList}" var="reported" varStatus="status">
										<tr class="reported" id="reported">
											<td>${reported.custName}</td>
										  	<td>${reported.shortName}</td>
											<td>${reported.merchantCode }</td>
										    <td>${reported.email}</td>
										    <td>
											    <c:if test="${reported.channelNo == 'iCr'}">
											    	华润银行
											    </c:if>
											    <c:if test="${reported.channelNo == 'CR'}">
											    	华润银行
											    </c:if>
											     <c:if test="${reported.channelNo == 'HELIPAY'}">
											    	合利宝
											    </c:if>
											    <c:if test="${reported.channelNo == 'KFT_PAY'}">
											    	快付通
											    </c:if>
											     <c:if test="${reported.channelNo == 'FM_COMBINEDPAY'}">
											    	富民
											    </c:if>
											    <c:if test="${reported.channelNo == 'WX'}">
											    	微信
											    </c:if>
											    <c:if test="${reported.channelNo == 'ALIPAY'}">
											    	支付宝
											    </c:if>
											     <c:if test="${reported.channelNo == 'BEST_PAY'}">
											    	翼支付
											    </c:if>
											     <c:if test="${reported.channelNo == 'SUIXING_PAY'}">
											    	随行付
											    </c:if>
											     <c:if test="${reported.channelNo == 'SUM_PAY'}">
											    	商盟支付
											    </c:if>
											     <c:if test="${reported.channelNo == 'YQB'}">
											    	平安付
											    </c:if>
										    </td>
										    <td>${reported.outMerchantCode}</td>
										    <td>
											    <c:if test="${reported.reportStatus == '00'}">
											    	提交报备成功
											    </c:if>
											    <c:if test="${reported.reportStatus == '10'}">
											    	产品报备成功
											    </c:if>
											    <c:if test="${reported.reportStatus == '99'}">
											    	报备失败
											    </c:if>
											    <c:if test="${reported.reportStatus == '98'}">
											    	产品报备异常
											    </c:if>
											    <c:if test="${reported.reportStatus == '0'}">
											    	待审核
											    </c:if>
											    <c:if test="${reported.reportStatus == '1'}">
											    	审核通过
											    </c:if>
											    <c:if test="${reported.reportStatus == '2'}">
											    	审核失败
											    </c:if>
											    <c:if test="${reported.reportStatus == '3'}">
											    	未认证
											    </c:if>
											    <c:if test="${reported.reportStatus == '4'}">
											    	审核成功但未同意协议
											    </c:if>
											    <c:if test="${reported.reportStatus == '9'}">
											    	子商户被删除
											    </c:if>
											    <c:if test="${reported.reportStatus == '15'}">
											    	资料信息已提交
											    </c:if>
										    </td>
										    <td>
										   		${reported.resultMsg}
										    </td>
										    <td>
										   		${reported.reportTime}
										    </td>
										    <td>
											    <input type="hidden" id="merchantCode_" value="${reported.merchantCode }">
											    <input type="hidden" id="custName_" value="${reported.custName }">
											    <input type="hidden" id="patchNo_" value="${reported.patchNo }">
											    <input type="hidden" id="channelNo_" value="${reported.channelNo }">
											    <input type="hidden" id="bestMerchantType_" value="${reported.bestMerchantType }">
											    <input type="hidden" id="loginNo_" value="${reported.loginNo }">
											    <input type="hidden" id="outMerchantCode_" value="${reported.outMerchantCode }">
	                                            <input type="hidden" id="reportStatus_" value="${reported.reportStatus }">
	                                            <input type="hidden" id="remark_" value="${reported.remark }">
	                                            <input type="hidden" id="resultMsg_" value="${reported.resultMsg }">
	                                            <button type="button"  class="btn btn-primary btn-xs" onclick="getInfo(this);">查看</button>
	                                            
	                                            <c:if test="${reported.reportStatus =='0' ||reported.reportStatus =='00' }">
	                                            	<button type="button"  class="btn btn-primary btn-xs" onclick="getStatus(this);">刷新状态</button>
	                                            </c:if>
	                                            <c:if test="${reported.reportStatus !='0' && reported.reportStatus !='00'  }">
	                                            	<button type="button"  class="btn btn-primary btn-xs" disabled>刷新状态</button>
	                                            </c:if>
	                                            
	                                            <c:if test="${reported.reportStatus =='99' || reported.detailStatus =='E' || reported.detailStatus =='F'}">
	                                            	<button type="button"  class="btn btn-primary btn-xs" onclick ="getUpdate(this);">报备更新</button>
                                            	</c:if>
                                            	<c:if test="${reported.reportStatus !='99' && reported.detailStatus !='E' && reported.detailStatus !='F'}">
	                                            	<button type="button"  class="btn btn-primary btn-xs" disabled>报备更新</button>
                                            	</c:if>
                                            	<button type="button"  class="btn btn-primary btn-xs" onclick ="getWeChatUpdate(this);">微信升级</button>
										    </td>
										</tr>
									   </c:forEach> 
										<c:if test="${empty reportedList}">
											<tr><td colspan="10" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if> 
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							 <c:if test="${not empty reportedList}">
								<%@ include file="/include/page.jsp"%>
							</c:if>
						</div>
					</div>
				</div>
				<!-- 底部-->
				<%@ include file="/include/bottom.jsp"%>
			</div><!-- /.main-content -->
			<!-- 设置 -->
			<%@ include file="/include/setting.jsp"%>
		</div><!-- /.main-container-inner -->
		<!-- 向上置顶 -->
		<%@ include file="/include/up.jsp"%>
	</div><!-- /.main-container -->
	
	<!-- 翼支付企业进件签约-->
	<div class="modal fade" style="z-index: 1040;" id="contractMerchantModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 60%; z-index: 90;">
			<div class="modal-content" style="width: 1000px;" id="merchantDiv">
				<div class="modal-header" style="background-color: 0099CC">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<!-- <button type="button" class="close" data-dismiss="modal" id="contractMerchantClose" aria-hidden="true">&times;</button> -->
					<h4 class="modal-title" id="myModalLabel">进件签约</h4>
				</div>
				<div class="modal-body">
					<table id="sample-table-2" class="list-table">
						<tr>
							<td class="td-left" >商户编号</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="merchantCode0" name="merchantCode0" maxlength="300"  readonly="readonly">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="merchantCodeLab0"></label>
								</span>
							</td>
							<td class="td-left" >产品编号</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="productCode" name="productCode" maxlength="300"  placeholder="请填写产品编号">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="productCodeLab"></label>
								</span>
							</td>
						</tr>
						<tr>
							<td class="td-left" >是否收费</td>
							<td class="td-right" colspan="3">
									<select id="feeFlag">
										<option value="">--请选择--</option>	
										<option value="TRUE">收费</option>	
										<option value="FALSE">不收费</option>	
									</select>
							</td>
							<td class="td-left" >收费模式</td>
							<td class="td-right" colspan="3">
									<select id="feeMode">
										<option value="">--请选择--</option>	
										<option value="SINGLE">单笔计费</option>	
										<option value="ACCUMUL">累计计费</option>	
										<option value="PACKET">包量计费</option>	
									</select>
							</td>
						</tr>
						<tr>
							<td class="td-left" >计费方式</td>
							<td class="td-right" colspan="3">
									<select id="billingMode">
										<option value="">--请选择--</option>	
										<option value="FIXED">固定</option>	
										<option value="RAT">比率</option>	
									</select>
							</td>
							<td class="td-left" >费率值</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="billingValue" name="billingValue" maxlength="300"  placeholder="请填写费率值">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="billingValueLab"></label>
								</span>
							</td>
						</tr>
						<tr>
							<td class="td-left" >起费金额</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="feeBeginAmt" name="feeBeginAmt" maxlength="300"  placeholder="请填写起费金额">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="feeBeginAmtLab"></label>
								</span>
							</td>
							<td class="td-left" >封底金额</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="bottomAmt" name="bottomAmt" maxlength="300"  placeholder="请填写封底金额">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="bottomAmtLab"></label>
								</span>
							</td>
						</tr>
						<tr>
							<td class="td-left" >封顶金额</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="ceilingAmt" name="ceilingAmt" maxlength="300"  placeholder="请填写封顶金额">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="ceilingAmtLab"></label>
								</span>
							</td>
							<td class="td-left" >收费状态</td>
							<td class="td-right" colspan="3"> 
								<select id="feeStatus">
										<option value="">--请选择--</option>	
										<option value="TRUE">收费</option>	
										<option value="FALSE">不收费</option>	
								</select>
							</td>
						</tr>
						<tr>
							<td class="td-left" >金额累计周期</td>
							<td class="td-right" colspan="3">
									<select id="feeCycle">
										<option value="">--请选择--</option>	
										<option value="YEAR">按自然年</option>	
										<option value="MONTH">按自然月</option>
										<option value="WEEK">按自然周</option>
										<option value="DAY">按自然日</option>	
									</select>
							</td>
							<td class="td-left" >退款手续费标识</td>
							<td class="td-right" colspan="3">
									<select id="refundFeeFlag">
										<option value="">--请选择--</option>	
										<option value="TRUE">收手续费</option>	
										<option value="FALSE">不收手续费</option>	
									</select>
							</td>
						</tr>
						<tr>
							<td class="td-left" >文件本地路径</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="offlineAgreementPath" name="offlineAgreementPath" maxlength="300"  placeholder="请填写文件路径">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="offlineAgreementPathLab"></label>
								</span>
							</td>
							<td class="td-left" >创建人</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="createdBy" name="createdBy" maxlength="300"  value="孙浔" readonly="readonly">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="createdByLab"></label>
								</span>
							</td>
						</tr>
					</table>
				</div>
				<div id="hiddenCheck_2" class="modal-footer">
					<button type="button" class="btn btn-primary contractMerchantBtn"  onclick="bottonContract();">确认</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 商盟开通产品-->
	<div class="modal fade" style="z-index: 1040;" id="contractMerchantModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 60%; z-index: 90;">
			<div class="modal-content" style="width: 1000px;" id="merchantDiv">
				<div class="modal-header" style="background-color: 0099CC">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">开通产品</h4>
				</div>
				<div class="modal-body">
					<table id="sample-table-2" class="list-table">
						<tr>
							<td class="td-left" >商户编号</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="merchantCode1" name="merchantCode1" maxlength="300"  readonly="readonly">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="merchantCodeLab1"></label>
								</span>
							</td>
							<td class="td-left" >商户名称</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 300px;"  id="custName1" name="custName1" maxlength="300"  readonly="readonly">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="custNameLab1"></label>
								</span>
							</td>
							
						</tr>
						<tr>
							<td class="td-left" >商盟支付宝MCC</td>
							<td class="td-right" colspan="3">
									<select id="levelOne" onchange="getLevelTwo();">
									<option value="">--请选择一级类目--</option>
                                    <c:if test="${not empty sumpayMccList }">
                                        <c:forEach items="${sumpayMccList }" var="sumpayMcc">
                                            <option id="${sumpayMcc.levelOne}"
                                                    value="${sumpayMcc.levelOne}">
                                                ${sumpayMcc.levelOne}
                                            </option>
                                        </c:forEach>
                                    </c:if>
									</select>
							</td>
							<td class="td-right" colspan="3">
								<select id="levelTwo" onchange="getLevelThree();">
								 <option value="">--请选择二级类目--</option>
								</select>
							</td>
							<td class="td-right" colspan="3">
								<select name="levelThree" id="levelThree">
	                                <option value="">--请选择三级类目--</option>
	                            </select>
							</td>
						</tr>
						<tr>
							<td class="td-left" >商盟微信产品</td>
							<td class="td-right" colspan="3">
									<select id="levelOneWx" onchange="getLevelTwoWx();">
									<option value="">--请选择一级类目--</option>
                                    <c:if test="${not empty sumpayMccWXList }">
                                        <c:forEach items="${sumpayMccWXList }" var="sumpayMcc">
                                            <option id="${sumpayMcc.levelOne}"
                                                    value="${sumpayMcc.levelOne}">
                                                ${sumpayMcc.levelOne}
                                            </option>
                                        </c:forEach>
                                    </c:if>
									</select>
							</td>
							<td class="td-right" colspan="3">
								<select id="levelTwoWx" onchange="getLevelThreeWx();">
								 <option value="">--请选择二级类目--</option>
								</select>
							</td>
							<td class="td-right" colspan="3">
								<select name="levelThreeWx" id="levelThreeWx">
	                                <option value="">--请选择三级类目--</option>
	                            </select>
							</td>
						</tr>
						<tr>
							<td class="td-left" >外部商户号</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 200px;"  id="outMerchantCode1" name="outMerchantCode1" maxlength="300"  readonly="readonly">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="outMerchantCode1Lab"></label>
								</span>
							</td>
							<td class="td-left" >费率</td>
							<td class="td-right" colspan="3"> 
								<span class="input-icon">
									<input type="text" style="width: 200px;"  id="rate" name="rate" maxlength="300"  placeholder="请填写费率">
									<i class="icon-leaf blue"></i>
									<label class="label-tips" id="rateLab"></label>
								</span>
							</td>
						</tr>
						<tr>
						<td class="td-left" >商盟支付宝产品</td>
							<td class="td-right" colspan="3">
									<select id="levelOneZfb" >
									<option value="">--请选择类目--</option>
                                    <c:if test="${not empty sumpayMccZFBList }">
                                        <c:forEach items="${sumpayMccZFBList }" var="sumpayMcc">
                                            <option id="${sumpayMcc.mchntType}"
                                                    value="${sumpayMcc.mchntType}">
                                                ${sumpayMcc.type}
                                            </option>
                                        </c:forEach>
                                    </c:if>
									</select>
							</td>
						</tr>
						
					</table>
				</div>
				<div id="hiddenCheck_2" class="modal-footer">
					<button type="button" class="btn btn-primary contractMerchantBtn"  onclick="bottonContract1();">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

