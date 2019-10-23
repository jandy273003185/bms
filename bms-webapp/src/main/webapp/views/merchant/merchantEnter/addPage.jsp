<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.sevenpay.bms.basemanager.merchant.MerchantPath" %>
<%@page import="com.sevenpay.bms.basemanager.merchant.MerchantEnterPath" %>
<%@page import="com.sevenpay.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.sevenpay.bms.basemanager.agency.controller.AgentRegisterPath" %>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>
<script src='<c:url value="/static/js/upload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/register.js"/>'></script>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户注册新增</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
	table tr td{word-wrap:break-word;word-break:break-all;}
	.uploadImage{ float:left;
			background:url(<%=request.getContextPath() %>/static/images/upload.jpg);
			background-size:120px 100px;
			width:120px;
			height:100px;
			margin: 10 10 10 10;
			}
	</style>
</head>
<script type="text/javascript">
$(function(){

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
		            var imgObj = $('#'+_this.attr('id')+'ImageDiv');
		            imgObj.attr("src", "data:image/jpeg;base64," + base64_string).show();
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
	    	   	        		 if(_this.attr('id')=="certAttribute1"){                    //身份证
	    	       	  				$("#representativeName").val(json.cardName);
	    	       	  				$("#representativeCertNo").val(json.cardId);
	    	       	  			}else if(_this.attr('id')=="businessPhoto"){                //营业执照
	    	       	  				$("#businessLicense").val(json.businessLicense);
	    	       	  				$("#businessTerm").val(json.businessTermStart);
	    	       	  				if("长期"==json.businessTermEnd){
	    	       	  					$("input[value='forever']").click();
	    	       	  				}else{
	    	       	  					$("#businessTermEnd").val(json.businessTermEnd);
	    	       	  				}
	    	       	  				$("#custAdd").val(json.legalAddress);
	    	       	  			}
	    	   				}
	    	   			}
		    	   	});
				}
			});
		}
	);
});
/**开户行城市 **/
function getBankCityList(){

	var provVal = $("#province").val().trim();
	$("#cityDef").siblings().remove();
	if ("" == provVal || provVal.length == 0) {
		return false;
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.GRTCITYLIST %>',
		data:
		{
			"province" 	: provVal,
			"choiceType" : "city"
		},
		success:function(data){
			if(data.result=="SUCCESS"){
				var cityList = data.cityList;
				for ( var city in cityList) {
					$("#bankCityName").append(
						"<option value='"+ cityList[city].cityId +"'>" + cityList[city].cityName + "</option>");
				}
			}else{
			}
		}
	})
}

function getCityList(){

	var provVal = $("#province").val().trim();
	$("#cityDef").siblings().remove();
	$("#areaDef").siblings().remove();
	if ("" == provVal || provVal.length == 0) {
		return false;
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.GRTCITYLIST %>',
		data:
		{
			"province" 	: provVal,
			"choiceType" : "city"
		},
		success:function(data){
			if(data.result=="SUCCESS"){
				var cityList = data.cityList;
				for ( var city in cityList) {
					$("#city").append(
						"<option value='"+ cityList[city].cityId +"'>" + cityList[city].cityName + "</option>");
				}
			}else{
			}
		}
	})
}

function getAreaList(){

	var city = $("#city").val().trim();
	$("#areaDef").siblings().remove();
	if ("" == city || city.length == 0) {
		return false;
	}

	$.ajax({
		type:"POST",
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.GRTCITYLIST %>',
		data:
		{
			"city" 	: city,
			"choiceType" : "area"
		},
		success:function(data){
			if(data.result=="SUCCESS"){
				var areaList = data.areaList;
				for ( var area in areaList) {
					$("#country").append(
						"<option value='"+ areaList[area].areaId +"'>" + areaList[area].areaName + "</option>");
				}
			}else{
			}
		}
	})
}


function selCustType(){
	/**商户类型 **/
	//个人
	if($("#custType").val() =='0' ||$("#custType").val() =='2' ){
		$("#bankCardPhoto_").attr("style","display:");
		$("#openAccount_").attr("style","display:none");
	}
	//企业
	if($("#custType").val() =='1'){
		$("#bankCardPhoto_").attr("style","display:none");
		$("#openAccount_").attr("style","display:");
	}
}

//点击长期给营业有效期赋forver
function foreverTime() {

}
function addMerchantBtn(){
	console.log("点击保存")
	var businessTermEnd = "forever";
	/*账号校验*/
	if(isNull($("#merchantAccount")[0])){
		$("#merchantAccountLab").text("请设置商户账户");
		$("#merchantAccount").focus();
		return false;
	}
	/*
	if(!verifyEmailAddress($("#merchantAccount")[0]) || !isMobilePhone($("#merchantAccount")[0])){
		$("#merchantAccountLab").text("账号需用邮箱或者手机号 ");
		$("#merchantAccount").focus();
		return false;
	}  */

	/*邮箱校验*/
	if(isNull($("#merchantEmail")[0])){
		$("#merchantEmailLab").text("请设置邮箱账户");
		$("#merchantEmail").focus();
		return false;
	}

	if(!verifyEmailAddress($("#merchantEmail")[0])){
		$("#merchantEmailLab").text("邮箱格式不对,可使用字母、数字、下划线 ");
		$("#merchantEmail").focus();
		return false;
	}

	/*客服号码校验*/
	if(isNull($("#contactPhone")[0])){
		$("#contactPhoneLab").text("请设置客服号码");
		$("#contactPhone").focus();
		return false;
	}

	if(!isPhoneNo($("#contactPhone")[0])){
		$("#contactPhoneLab").text("客服号码格式不对 ");
		$("#contactPhone").focus();
		return false;
	}

	/*商户地址*/
	if(isNull($("#custAdd")[0])){
		$("#custAddLab").text("请填写地址");
		$("#custAdd").focus();
		return false;
	}

	/*营业执照号*/
	if(isNull($("#businessLicense")[0])){
		$("#businessLicenseLab").text("请填写营业执照注册号");
		$("#businessLicense").focus();
		return false;
	}

	//校验营业执照注册号唯一性
	var businessLicense =$("#businessLicense").val();
	var validateLicense =true ;

	$.ajax({
		async:false,
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantPath.BASE+MerchantPath.VALIDATELICENSE%>',
        data:{businessLicense:businessLicense},
        success:function(data){
        	if(data.result=="FAIL"){
        		$("#businessLicenseLab").text("该营业执照注册号已经被使用");
        		validateLicense = false;
			}else{
				validateLicense = true;
			}
			 }});
	if(!validateLicense){
		return false;
	}

	var merchantAccount =$("#merchantAccount").val();
	var validateLicense =true ;
	//验证商户账户唯一性
	$.ajax({
		async:false,
		dataType:"json",
		url:window.Constants.ContextPath +'<%=MerchantEnterPath.BASE+MerchantEnterPath.VALIDATEMERCHANTACCOUNT%>',
		data:{merchantAccount:merchantAccount},
		success:function(data){
			if(data.result=="FAIL"){
				$("#merchantAccountLab").text("该营业执照注册号已经被使用");
				validateLicense = false;
			}else{
				validateLicense = true;
			}
		}});
	if(!validateLicense){
		return false;
	}

	var custType =$("#custType").val();
	/*个人*/
	if(custType=='0'){
	}
	/*企业*/
	if(custType=='1'){

		/*营业执照有限期 */
		if(isNull($("#businessTermStart")[0])){
			$("#businessTermStartLab").text("请选择日期");
			$("#businessTermStart").focus();
			return false;
		}

		if(isNull($("#businessTermEnd")[0])){
			$("#businessTermEndLab").text("请选择日期");
			$("#businessTermEnd").focus();
			return false;
		}

		/*起始日期判断 */
		var startDate = $("#businessTermStart").val();
		var endDate= $("#businessTermEnd").val();
		if("" != startDate && "" != endDate && startDate > endDate)
		{
			$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
			return false;
		}

		// 校验营业时间
		if(!Register.validateBusinessTerm($("#businessTermStart").val().trim(),$("#businessTermStartLabel"))){
			return false;
		}

		if($("input:radio[name='end']:checked").val()=='sel'){
			if(!Register.validateBusinessTerm($("#businessTermEnd").val().trim(),$("#businessTermStartLabel"))){
				return false;
			}
			businessTermEnd = $("#businessTermEnd").val();
		}

		//营业执照号
		var flag = Register.validateBusinessLicense($("#businessLicense").val().trim(),$("#businessLicenseLab"));

		if(!flag){
			return false;
		}

		if(!checkAttach($("#businessPhoto")[0])){
			$.gyzbadmin.alertFailure("必须提交营业执照扫描件");
			return false;
		}

		if(!checkAttach($("#openAccount")[0])){
			$.gyzbadmin.alertFailure("必须提交开户许可证");
			return false;
		}
	}
	/*个体户*/
	if(custType=='2'){

		/*营业执照有限期 */
		if(isNull($("#businessTermStart")[0])){
			$("#businessTermStartLab").text("请选择日期");
			$("#businessTermStart").focus();
			return false;
		}

		if(isNull($("#businessTermEnd")[0])){
			$("#businessTermEndLab").text("请选择日期");
			$("#businessTermEnd").focus();
			return false;
		}

		/*起始日期判断 */
		var startDate = $("#businessTermStart").val();
		var endDate= $("#businessTermEnd").val();
		if("" != startDate && "" != endDate && startDate > endDate)
		{
			$.gyzbadmin.alertFailure("结束日期不能小于开始日期");
			return false;
		}

		if(checkAttach($("#businessPhoto")[0])){
			if(isNull($("#businessLicense")[0])){
				$("#businessLicenseLab").text("必须填写营业执照注册号");
				return false;
			}

			// 校验营业时间
			if(!Register.validateBusinessTerm($("#businessTermStart").val().trim(), $("#businessTermStartLabel"))) {
				return false;
			}
			if($("input:radio[name='end']:checked").val()=='sel'){
				if(!Register.validateBusinessTerm($("#businessTermEnd").val().trim(),$("#businessTermStartLabel"))) {
					return false;
				}
				businessTermEnd = $("#businessTermEnd").val();
			}

			var flag = Register.validateBusinessLicense($("#businessLicense").val(),$("#businessLicenseLab"));

			if(!flag){
				return false;
			}
		}
		if(!kong.test($("#businessLicense").val().trim())){
			if(!checkAttach($("#businessPhoto")[0])){
				$.gyzbadmin.alertFailure("必须上传营业执照扫描件");
				return false;
			}
		}
	}

	/*法人姓名*/
	if(isNull($("#representativeName")[0])){
		$("#representativeNameLab").text("请填写法人姓名");
		$("#representativeName").focus();
		return false;
	}
	/*法人身份证*/
	if(isNull($("#representativeCertNo")[0])){
		$("#representativeCertNoLab").text("请填写法人身份证");
		$("#representativeCertNo").focus();
		return false;
	}

	/*联系人姓名*/
	if(isNull($("#contactName")[0])){
		$("#contactNameLab").text("请填写联系人姓名");
		$("#contactName").focus();
		return false;
	}
	/*联系人手机号*/
	if(isNull($("#contactMobile")[0])){
		$("#contactMobileLab").text("请填写联系人手机号");
		$("#contactMobile").focus();
		return false;
	}
	if(!isMobilePhone($("#contactMobile")[0])){
		$("#contactMobileLab").text("账号需用手机号 ");
		$("#contactMobile").focus();
		return false;
	}
	/*银行卡号*/
	if(isNull($("#compMainAcct")[0])){
		$("#compMainAcctLab").text("请填写银行卡号");
		$("#compMainAcct").focus();
		return false;
	}

	/*开户银行*/
	var compAcctBank = $("#compAcctBank").val().trim();
	if ("" == compAcctBank || compAcctBank.length == 0) {
		$("#compMainAcctLab").text("请填写开户银行");
		return false;
	}

	/*开户行*/
	if(isNull($("#branchBank")[0])){
		$("#branchBankLab").text("请填写开户行");
		$("#branchBank").focus();
		return false;
	}

	/*开户人 */
	if(isNull($("#bankAcctName")[0])){
		$("#bankAcctNameLab").text("请填写开户人");
		$("#bankAcctName").focus();
		return false;
	}

	/*网点号*/
	if(isNull($("#cnaps")[0])){
		$("#cnapseLab").text("请填写银联号");
		$("#cnaps").focus();
		return false;
	}

	/*结算类型*/
	if(isNull($("#compMainAcctType")[0])){
		$("#compMainAcctTypeLab").text("请填写结算类型");
		$("#compMainAcctType").focus();
		return false;
	}

	// 提交前清空所有错误提示栏

	var merchantAccount = $("#merchantAccount").val().trim();
	var custType = $("#custType").val().trim();
	var custName = $("#custName").val().trim();
	var shortName = $("#shortName").val().trim();
	var merchantEmail = $("#merchantEmail").val().trim();
	var contactPhone =  $("#contactPhone").val().trim();
	var province =  $("#province").val().trim();
	var city = $("#city").val().trim();
	var country = $("#country").val().trim();
	var custAdd = $("#custAdd").val().trim();
	var businessLicense = $("#businessLicense").val().trim();
	var businessTermStart = $("#businessTermStart").val().trim();
	var custManager = $("#custManager").val().trim();
	var agentName = $("#agentName").val().trim();
	var representativeName = $("#representativeName").val().trim();
	var representativeCertNo = $("#representativeCertNo").val().trim();
	var contactName = $("#contactName").val().trim();
	var contactMobile = $("#contactMobile").val().trim();
	var compMainAcct = $("#compMainAcct").val().trim();
	var compAcctBank = $("#compAcctBank").val().trim();
	var branchBank = $("#branchBank").val().trim();
	var bankAcctName = $("#bankAcctName").val().trim();
	var bankProvinceName = $("#bankProvinceName").val().trim();
	var bankCityName = $("#bankCityName").val().trim();
	var cnaps =  $("#cnaps").val().trim();
	var compMainAcctType = $("#compMainAcctType").val().trim();

	$.blockUI();
 	$.ajax({
		type : "POST",
		url : window.Constants.ContextPath +'<%=TinyMerchantPath.BASE + TinyMerchantPath.FILEUPLOAD%>',
		data :{
			businessPhoto  : $('#businessPhototemp').val(),
			certAttribute1 : $('#certAttribute1temp').val(),
			certAttribute2 : $('#certAttribute2temp').val(),
			openAccount    : $('#openAccounttemp').val(),
			bankCardPhoto  : $('#bankCardPhototemp').val()
		},
		dataType : "json",
		success : function(data) {
			if(data.result=='SUCCESS'){
				$.post(window.Constants.ContextPath +'<%=MerchantEnterPath.BASE + MerchantEnterPath.ADD%>',{
        			"custId":                data.custId,						// 回传custId
        			"merchantAccount":       merchantAccount, 					// 商户账号
        			"custType":              custType,							// 商户类型
        			"custName":              custName, 							// 客户姓名
        			"shortName":             shortName,                         // 客户简称
        			"merchantEmail":         merchantEmail,						// 邮箱
        			"contactPhone":          contactPhone,						// 客服电话
        			"province":              province,							// 省份
        			"city":                  city,								// 城市
        			"country" :              country,							// 县区
        			"custAdd" :              custAdd,							// 详细地址
        			"businessLicense":       businessLicense,                   // 营业执照注册号
        			"businessTermStart":     businessTermStart,					// 营业执照有限期
        			"businessTermEnd" :      businessTermEnd,					// 营业执照有限截止期
        			"custManager":           custManager,						// 客户经理
        			"agentName":             agentName,							// 代理商
        			"representativeName":    representativeName,				// 法人姓名
        			"representativeCertNo":  representativeCertNo,				// 法人身份证号
        			"contactName":           contactName,						// 联系人姓名
        			"contactMobile":         contactMobile,						// 联系人电话
        			"compMainAcct":          compMainAcct,						// 银行号
        			"compAcctBank":          compAcctBank,						// 开户行
        			"branchBank":            branchBank,						// 开户支行
        			"bankAcctName":          bankAcctName,						// 开户人
        			"bankProvinceName":      bankProvinceName,					// 开户行省份
        			"bankCityName":          bankCityName,						// 开户行城市
        			"cnaps":                 cnaps,								// 联行号
        			"compMainAcctType":      compMainAcctType					// 结算类型
        		},function(data){
    				if(data.result=="SUCCESS"){
    					$.gyzbadmin.alertSuccess("注册申请成功",null,function(){
    						window.location.href = window.Constants.ContextPath + '<%=MerchantEnterPath.BASE + MerchantEnterPath.LIST %>';
    					});
    				}else {
    					$.gyzbadmin.alertFailure("服务器内部错误，请联系相关技术人员，错误原因是：" + data.message);
    				}
        		},'json')
        	}else{
        		$.gyzbadmin.alertFailure("服务器内部错误，请联系相关技术人员，错误原因是：" + data.message);

        	}
		}
	});

}


/********************图片预览***********************/


/** 点击预览大图 **/
function bigImg(obj){
    /* $('#showImageDiv #showImage').attr("src",obj.src); */
    var realWidth;
	var realHeight
	$('#showImageDiv #showImage').attr("src",obj.src).load(function(){
		realWidth = this.width;
		realHeight = this.height;
		var scale =  realWidth/realHeight;
		if(realWidth >800){
			realWidth = 800;
			realHeight = realWidth / scale;
		}
		$("#imageDiv").css("width",realWidth+"px").css("height",realHeight+"px");
	});
}


/** 营业执照预览 **/
function showBusinessPhotoImage(obj){
	 var divObj = document.getElementById("businessPhotoDiv");
	 var imageObj = document.getElementById("businessPhotoImage");
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;
}
/** 营业执照点击预览 **/
$('.businessPhotoClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("businessPhoto");
	return previewImage(divObj,imageObj,obj);
});
/** 身份证正面预览 **/
function showCertAttribute1Image(obj){
	 var divObj = document.getElementById("certAttribute1Div");
	 var imageObj = document.getElementById("certAttribute1Image");
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;
}
/** 身份证正面点击预览 **/
$('.certAttribute1Click').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("certAttribute1");
	return previewImage(divObj,imageObj,obj);
});
/** 身份证背面预览 **/
function showCertAttribute2Image(obj){
	 var divObj = document.getElementById("certAttribute2Div");
	 var imageObj = document.getElementById("certAttribute2Image");
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;
}
/** 身份证背面点击预览 **/
$('.certAttribute2Click').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("certAttribute2");
	return previewImage(divObj,imageObj,obj);
});

/** 开户许可证预览 **/
function showOpenAccountImage(obj){
	 var divObj = document.getElementById("openAccountDiv");
	 var imageObj = document.getElementById("openAccountImage");
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;
}
/** 开户许可证背面点击预览 **/
$('.openAccountClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("openAccount");
	return previewImage(divObj,imageObj,obj);
});

/** 银行卡预览 **/
function showBankCardPhotoImage(obj){
	 var divObj = document.getElementById("bankCardPhotoDiv");
	 var imageObj = document.getElementById("bankCardPhotoImage");
	 var result1 = previewImage(divObj,imageObj,obj);
	 return result1;
}
/** 银行卡点击预览 **/
$('.bankCardPhotoClick').click(function(){
	var divObj = document.getElementById("showImageDiv");
	var imageObj = document.getElementById("showImage");
	var obj = document.getElementById("bankCardPhoto");
	return previewImage(divObj,imageObj,obj);
});


</script>
<body>
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
						<input type="hidden" id="businessPhototemp">
						<input type="hidden" id="certAttribute1temp">
						<input type="hidden" id="certAttribute2temp">
						<input type="hidden" id="openAccounttemp" />
						<input type="hidden" id="bankCardPhototemp" />
                    <table id="merchant_table" class="list-table">
					<tbody>
                        <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">商户信息</td>
						</tr>
						<tr></tr>
                        <tr>
							<td class="td-left">商户账号：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="merchantAccount" name="merchantAccount" placeholder="请输入手机号或邮箱" maxlength="50" style="width:90%">
								<label class="label-tips" id="merchantAccountLab"></label>
							</td>
						</tr>
                        <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
						</tr>
						<tr>
							<td class="td-left">商户类型：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
							   <select name="custType" class="width-90" id="custType" onchange="selCustType();">
									<option value="0">个人</option>
									<option value="1">企业</option>
									<option value="2">个体户</option>
								</select>
							</td>
						</tr>
                        <tr>
						    <td class="td-left" width="18%">商户名称：<span style="color:red;">（必填)</span></td>
							<td class="td-right" width="32%">
								<input type="text" id="custName" name="custName" maxlength="100"  placeholder="请输入商户名称" style="width:90%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="custNameLab"></label>
							</td>
							<td class="td-left" width="18%">商户简称：<span style="color:red;">（必填)</span></td>
							<td class="td-right" width="32%">
							    <input type="text" id="shortName" name="shortName" placeholder="请输入商户简称" style="width:90%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="shortNameLab"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">商户邮箱：<span style="color:red;">非常重要，请填写正确（必填)</span></td>
							<td class="td-right">
								<input type="text" id="merchantEmail" name="merchantEmail" placeholder="请输入商户邮箱" style="width:90%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="merchantEmailLab"></label>
							</td>
							<td class="td-left">客服号码：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="contactPhone" name="contactPhone" placeholder="请输入客服号码" style="width:90%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="contactPhoneLab"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">商户地址：<span style="color:red;">（必填)</span></td>
							<td class="td-right" colspan="3">
								<div class="col-xs-2 pd0" style="padding:0">
                                   <select class="form-control" id="province" onchange="getCityList();">
                                       <c:if test="${not empty provincelist_ }">
                                        	<option value="">--请选择--</option>
						               <c:forEach items="${provincelist_ }" var="prov">
						                   <option value="${prov.provinceId}">${prov.provinceName}</option>
						               </c:forEach>
		               				</c:if>
                                   </select>
                                </div>
                                <div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
                                <select class="form-control" id="city" onchange="getAreaList();">
                                    <option value="" id="cityDef">--请选择--</option>
                                </select>
                                </div>
                                <div class="col-xs-2 pd0" style="padding:0">
                                <select class="form-control" id="country">
                                    <option value="" id="areaDef">--请选择--</option>
                                </select>
                                </div>
                                <div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
                                    <input type="text" id="custAdd" name="custAdd"  placeholder="详细地址" style="width:100%">
                                	<label class="label-tips" id="custAddLab"></label>
                                </div>
							</td>
					    </tr>
						<tr>
							<td class="td-left">营业执照编号：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="businessLicense" name="businessLicense"  placeholder="请输入营业执照" style="width:90%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="businessLicenseLab"></label>
							</td>
							<td class="td-left">营业执照有效期：</td>
							<td class="td-right">
								<input type="text" name="businessTermStart"   id="businessTermStart" readonly="readonly"   onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'businessTermEnd\')}'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
								-
								<input type="radio" checked="checked" name="end" value="sel"/>有期
								<input type="text" name="businessTermEnd"   id="businessTermEnd" readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'businessTermStart\')}'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;">
								<input type="radio" name="end" value="forever" onclick="foreverTime()">长期
								<label id="businessTermStartLabel" class="label-tips"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">营业执照照片<span style="color:red" class="businessPhotoImageSpan"></span></td>
							<td class="td-right">
								<a data-toggle='modal' class="tooltip-success businessPhotoClick"  data-target="#previewImageModal">
									<label id="businessPhotoDiv" class="uploadImage">
										<img  id="businessPhotoImage" style="width:100%;height:100%; display: none" >
									</label>
								</a>
								<div style="float:left;margin-top:75" >
									<input type="file" name="businessPhoto" id="businessPhoto" onChange="showBusinessPhotoImage(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
								</div>
								<label class="label-tips" id="businessPhotoLabel" style="float:left;margin-top:88" ></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">所属业务人员：</td>
							<td class="td-right">
								<input type="text" id="custManager" name="custManager"  placeholder="请输入所属业务人员" style="width:90%">
							</td>
							<td class="td-left">所属代理商：</td>
							<td class="td-right">
								<input type="text" name="agentName" id="agentName" placeholder="请输入所属代理商" style="width:90%">
							</td>
						</tr>
                        <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td>
						</tr>
						<tr></tr>
						<tr>
							<td class="td-left">法人真实姓名：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="representativeName" name="representativeName" placeholder="请输入法人真实姓名" maxlength="50" style="width:90%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="representativeNameLab"></label>
							</td>
							<td class="td-left">法人身份证号码：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" name="representativeCertNo" id="representativeCertNo" placeholder="请输入法人身份证号码" style="width:90%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="representativeCertNoLab"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left" >法人身份证正面<span style="color:red">*</span></td>
							<td class="td-right" >
								<a data-toggle='modal' class="tooltip-success certAttribute1Click"  data-target="#previewImageModal" >
								<label id="certAttribute1Div" class="uploadImage">
								        <img  id="certAttribute1Image" style="width:100%;height:100%;display:none"/>
								</label>
								</a>
								<div style="float:left;margin-top:75" >
									<input type="file" name="certAttribute1" id="certAttribute1" onChange="showCertAttribute1Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
								</div>
								<label class="label-tips" id="certAttribute1Label" style="float:left;margin-top:88"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left" >法人身份证背面<span style="color:red">*</span></td>
							<td class="td-right" >
								<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal" >
									<label id="certAttribute2Div2" class="uploadImage">
									        <img  id="certAttribute2Image" style="width:100%;height:100%;display:none"/>
									</label>
								</a>
								<div style="float:left;margin-top:75" >
								<input type="file" name="certAttribute2" id="certAttribute2" onChange="showCertAttribute2Image(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
								</div>
								<label class="label-tips" id="certAttribute2Label" style="float:left;margin-top:88"></label>
							</td>
						</tr>
                        <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">联系信息</td>
						</tr>
						<tr></tr>
                        <tr>
							<td class="td-left">联系人姓名：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="contactName" name="contactName" placeholder="请输入联系人姓名" maxlength="50" style="width:90%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="contactNameLab"></label>
							</td>
							<td class="td-left">联系人手机号码：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" name="contactMobile" id="contactMobile" placeholder="请输入联系人手机号码" style="width:90%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="contactMobileLab"></label>
							</td>
						</tr>
						<tr id="next_id">
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
						</tr>
						<tr>
							<td class="td-left">银行卡号<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="compMainAcct" name="compMainAcct" maxlength="100" placeholder="请输入银行卡号" style="width:90%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="compMainAcctLab"></label>
							</td>
							<td class="td-left">开户银行：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<select class="width-90" id="compAcctBank" name="compAcctBank">
                                <c:if test="${not empty banklist }">
                                   <option value="">--请选择--</option>
					               <c:forEach items="${banklist }" var="bank">
					                   <option value="${bank.bankCode}">${bank.bankName}</option>
					               </c:forEach>
	               				</c:if>
                                </select>
                                <i class="icon-leaf blue"></i>
								<label class="label-tips" id="compAcctBankLab"></label>
							</td>
						</tr>
						<tr>
							<td class="td-left">开户行：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="branchBank" name="branchBank" placeholder="请输入开户行" style="width:90%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="branchBankLab"></label>
							</td>
							<td class="td-left">开户人：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="bankAcctName" name="bankAcctName" placeholder="请输入开户人" style="width:90%">
								<i class="icon-leaf blue"></i>
								<label class="label-tips" id="bankAcctNameLab"></label>
							</td>
						</tr>
                        <tr>
							<td class="td-left">开户省份：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<select class="width-90" id="bankProvinceName" onchange="getBankCityList();">
	                                   <c:if test="${not empty provincelist_ }">
                                        	<option value="">--请选择--</option>
						               <c:forEach items="${provincelist_ }" var="prov">
						                   <option value="${prov.provinceId}">${prov.provinceName}</option>
						               </c:forEach>
		               				</c:if>
	                               </select>
							</td>
							<td class="td-left">开户城市：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
							   <select class="width-90" id="bankCityName">
                                  <option value="" id="cityDef">--请选择--</option>
                               </select>
							</td>
						</tr>
                        <tr>
							<td class="td-left">网点号：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<input type="text" id="cnaps" name="cnaps" placeholder="请输入网点号" style="width:90%"> <a href="http://www.lianhanghao.com" target="_blank">[查找]</a>
							</td>
							<td class="td-left">结算类型：<span style="color:red;">（必填)</span></td>
							<td class="td-right">
								<select class="width-90" id="compMainAcctType">
                                    <option value="01">对公</option>
                                    <option value="02">对私</option>
                                </select>
							</td>
						</tr>
						<tr id="openAccount_" style="display: none">
							<td class="td-left" >开户许可证</td>
							<td class="td-right" >
								<a data-toggle='modal' class="tooltip-success openAccountClick"  data-target="#previewImageModal" >
									<label id="openAccountDiv" class="uploadImage">
									        <img  id="openAccountImage" style="width:100%;height:100%;display:none"/>
									</label>
								</a>
								<div style="float:left;margin-top:75" >
								<input type="file" name="openAccount" id="openAccount" onChange="showOpenAccountImage(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
								</div>
								<label class="label-tips" id="openAccountLabel" style="float:left;margin-top:88"></label>
							</td>
						</tr>
						<tr id="bankCardPhoto_">
							<td class="td-left" >银行卡照<span style="color:red"></span></td>
							<td class="td-right" >
								<a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal" >
									<label id="bankCardPhotoDiv" class="uploadImage">
									        <img  id="bankCardPhotoImage" style="width:100%;height:100%;display:none"/>
									</label>
								</a>
								<div style="float:left;margin-top:75" >
								<input type="file" name="bankCardPhoto" id="bankCardPhoto" onChange="showBankCardPhotoImage(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
								</div>
								<label class="label-tips" id="bankCardPhotoLabel" style="float:left;margin-top:88"></label>
							</td>
						</tr>
					</tbody>
					</table>
                        <div style="margin:50px 0 0 0;text-align:center">
                        	<button type="button"  class="btn btn-primary addMerchantBtn" onclick="addMerchantBtn()">保存</button>
                        	<a href="<%=request.getContextPath()+MerchantEnterPath.BASE + MerchantEnterPath.LIST%>"  class="btn btn-default" >关闭</a>
                        </div>
					</div>
					</div>

				</div><!-- /.page-content -->
				<!-- 图片预览 -->
				<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				    <div class="modal-dialog showDiv" >
	         			<div id="showImageDiv" style="width:100%;height:100%;">
				           <img id="showImage" style="width:100%;height:100%;">
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

</body>


<script type="text/javascript">
</script>
</html>