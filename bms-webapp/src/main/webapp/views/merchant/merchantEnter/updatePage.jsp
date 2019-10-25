<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.sevenpay.bms.basemanager.merchant.MerchantPath" %>
<%@page import="com.sevenpay.bms.basemanager.merchant.MerchantEnterPath" %>
<%@page import="com.sevenpay.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.sevenpay.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.sevenpay.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%-- <script src='<c:url value="/static/js/checkRule_source.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script> --%>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>
<script src='<c:url value="/static/js/upload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/register.js"/>'></script>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户录入更新</title>
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
	$(function() {

		if($("#custType").val() =='0' ||$("#custType").val() =='2' ){
			//个人
			$("#bankCardPhoto_").attr("style","display:");
			$("#openAccount_").attr("style","display:none");
		}
		if($("#custType").val() =='1'){
			//企业
			$("#bankCardPhoto_").attr("style","display:none");
			$("#openAccount_").attr("style","display:");
		}

		var custId = $("#custId").val().trim();
		var authId = $("#authId").val().trim();

		$("#updateMerchant #businessPhotoImageDiv").show();
		$("#updateMerchant #bankCardPhotoImageDiv").show();
		$("#updateMerchant #certAttribute1ImageDiv").show();
		$("#updateMerchant #certAttribute2ImageDiv").show();
		$("#updateMerchant #openAccountImageDiv").show();
		$("#updateMerchant #businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=07&authId="+authId);
		$("#updateMerchant #bankCardPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=08&authId="+authId);
		$("#updateMerchant #certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
		$("#updateMerchant #certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
		$("#updateMerchant #openAccountImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId);


		if ("style=\"width:90%\"" == document.getElementById("custManager").value){
			$("#custManager").attr("value","");
		}

		if ("style=\"width:90%\"" == document.getElementById("agentName").value){
			$("#agentName").attr("value","");
		}


	});

	function getBankCityList()
	{
		var provVal = $("#bankProvinceName").val().trim();

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
								"<option value='"+ cityList[city].cityId +"'>"
								+ cityList[city].cityName + "</option>");
					}
				}else{
				}
			}
		})
	}

	function updateMerchantBtn(){

		var businessTermEnd = "forever";
		/*账号校验*/
		/* if(isNull($("#merchantAccount")[0])){
            $("#merchantAccountLab").text("请设置商户账户");
            $("#merchantAccount").focus();
            return false;
        }

        if(!verifyEmailAddress($("#merchantAccount")[0]) ||!isMobilePhone($("#merchantAccount")[0])){
            $("#merchantAccountLab").text("账号需用邮箱或者手机号 ");
            $("#merchantAccount").focus();
            return false;
        } */

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

		var businessLicense =$("#businessLicense").val();
		//校验营业执照注册号唯一性
		<%-- var validateLicense =true ;

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
        } --%>

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
		var custType =$("#custType").val();
		/*个人*/
		if(custType=='0'){
		}
		/*企业*/
		if(custType=='1'){
			// 校验营业时间
			if(!Register.validateBusinessTerm($("#businessTermStart").val().trim(),$("#businessTermStartLabel"))){return false;}
			if($("input:radio[name='end']:checked").val()=='sel'){
				if(!Register.validateBusinessTerm($("#businessTermEnd").val().trim(),$("#businessTermStartLabel"))){return false;}
				businessTermEnd = $("#businessTermEnd").val();
			}
			//营业执照号
			var flag = Register.validateBusinessLicense($("#businessLicense").val().trim(),$("#businessLicenseLab"));

			if(!flag){return false;}

			/*if(!checkAttach($("#businessPhoto")[0])){
				$.gyzbadmin.alertFailure("必须提交营业执照扫描件");
				return false;
			}

			if(!checkAttach($("#openAccount")[0])){
				$.gyzbadmin.alertFailure("必须提交开户许可证");
				return false;
			}*/
		}
		/*个体户*/
		if(custType=='2'){
			if(checkAttach($("#businessPhoto")[0])){
				if(isNull($("#businessLicense")[0])){
					$("#businessLicenseLab").text("必须填写营业执照注册号");
					return false;
				}
				// 校验营业时间
				if(!Register.validateBusinessTerm($("#businessTermStart").val().trim(),$("#businessTermStartLabel"))){return false;}
				if($("input:radio[name='end']:checked").val()=='sel'){
					if(!Register.validateBusinessTerm($("#businessTermEnd").val().trim(),$("#businessTermStartLabel"))){return false;}
					businessTermEnd = $("#businessTermEnd").val();
				}
				var flag = Register.validateBusinessLicense($("#businessLicense").val(),$("#businessLicenseLab"));
				if(!flag){return false;}
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
		var compMainAcct = $("#compMainAcct").val();
		if(!checkBankCardFormat($("#compMainAcct"))){
			$("#compMainAcctLab").text("账号需用银行卡号 ");
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
		Register.clearAllErrorMsgLabel();

		var merchantAccount = $("#merchantAccount").val();
		var custType = $("#custType").val();
		var custName = $("#custName").val();
		var shortName = $("#shortName").val();
		var merchantEmail = $("#merchantEmail").val();
		var contactPhone =  $("#contactPhone").val();
		var province =  $("#province").val();
		var city = $("#city").val();
		var country = $("#country").val();
		var custAdd = $("#custAdd").val();
		var businessLicense = $("#businessLicense").val();
		var businessTermStart = $("#businessTermStart").val();
		var custManager = $("#custManager").val();
		var agentName = $("#agentName").val();
		var representativeName = $("#representativeName").val();
		var representativeCertNo = $("#representativeCertNo").val();
		var contactName = $("#contactName").val();
		var contactMobile = $("#contactMobile").val();
		var compMainAcct = $("#compMainAcct").val();
		var compAcctBank = $("#compAcctBank").val();
		var branchBank = $("#branchBank").val();
		var bankAcctName = $("#bankAcctName").val();
		var bankProvinceName = $("#bankProvinceName").val();
		var bankCityName = $("#bankCityName").val();
		var cnaps =  $("#cnaps").val();
		var compMainAcctType = $("#compMainAcctType").val();
		var custId = $("#custId").val();
		/*alert(custId);*/
		$.blockUI();
		$.ajax({
			type : "POST",
			url : window.Constants.ContextPath +'<%=TinyMerchantPath.BASE + TinyMerchantPath.FILEUPLOAD%>',
			data :{
				businessPhoto : $('#businessPhototemp').val(),
				certAttribute1 : $('#certAttribute1temp').val(),
				certAttribute2 : $('#certAttribute2temp').val(),
				openAccount : $('#openAccounttemp').val(),
				bankCardPhoto : $('#bankCardPhototemp').val()

			},
			dataType : "json",
			success : function(data) {
				if(data.result=='SUCCESS'){
					$.post(window.Constants.ContextPath +'<%=MerchantEnterPath.BASE + MerchantEnterPath.UPDATE%>',{
						"custId":custId,							// 回传custId
						"merchantAccount":merchantAccount, 					// 商户二维码编号
						"custType":custType,
						"custName":custName, 							// 客户姓名
						"shortName":shortName,                       // 客户简称
						"merchantEmail":merchantEmail,
						"contactPhone":contactPhone,
						"province":province,
						"city":city,
						"country" :country,
						"custAdd" : custAdd,
						"businessLicense":businessLicense,             // 营业执照注册号
						"businessTermStart":businessTermStart,
						"businessTermEnd" : businessTermEnd,
						"custManager":custManager,
						"agentName":agentName,
						"representativeName":representativeName,
						"representativeCertNo":representativeCertNo,
						"contactName":contactName,
						"contactMobile":contactMobile,
						"compMainAcct":compMainAcct,
						"compAcctBank":compAcctBank,
						"branchBank":branchBank,
						"bankAcctName" :bankAcctName,
						"bankProvinceName":bankProvinceName,
						"bankCityName":bankCityName,
						"cnaps":cnaps,
						"compMainAcctType":compMainAcctType
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


	//图片预览
	function showBusinessPhotoImage(obj){
		var divObj = document.getElementById("businessPhotoDiv");
		var imageObj = document.getElementById("businessPhotoImageDiv");
		return previewImage(divObj,imageObj,obj);
	}

	function showopenAccountImage(obj){
		var divObj = document.getElementById("openAccountDiv");
		var imageObj = document.getElementById("openAccountImageDiv");
		return previewImage(divObj,imageObj,obj);
	}
	$('.openAccountClick').click(function(){
		var divObj = document.getElementById("showImageDiv");
		var imageObj = document.getElementById("showImage");
		var obj = document.getElementById("openAccount");
		return previewImage(divObj,imageObj,obj);
	});

	function showCertAttribute1Image(obj){
		var divObj = document.getElementById("certAttribute1Div");
		var imageObj = document.getElementById("certAttribute1ImageDiv");
		return previewImage(divObj,imageObj,obj);
	}

	function showCertAttribute2Image(obj){
		var divObj = document.getElementById("certAttribute2Div");
		var imageObj = document.getElementById("certAttribute2ImageDiv");
		return previewImage(divObj,imageObj,obj);
	}

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

	function exit() {
		if (confirm("您确定要关闭吗？")) {
			window.opener=null;

			window.open("","_self");

			window.close();
		}
	};
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
						<input type="hidden" name="custId" id="custId" value="${merchantVo.custId}">
						<input type="hidden" name="merchantCode" id="merchantCode" value="${merchantVo.merchantCode}">
						<input type="hidden" name="authId" id="authId" value="${merchantVo.authId}">
						<input type="hidden" name="custType" id="custType" value="${merchantVo.custType}">
						<table id="merchant_table" class="list-table">
							<tbody>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">商户信息</td></tr>
							<tr>
							</tr>
							<tr>
								<td class="td-left">商户账号：</td>
								<%-- <td type="hidden" class="td-right" style="color:#666;padding:10px 8px" id="custId"></td>
								<td type="hidden" class="td-right" style="color:#666;padding:10px 8px" id="merchantCode">${merchantVo.merchantCode }</td>
								<td type="hidden" class="td-right" style="color:#666;padding:10px 8px" id="authId">${merchantVo.authId }</td> --%>
								<td class="td-right" style="color:#666;padding:10px 8px">
									${merchantVo.email }
								</td>

							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
								<td class="td-left">商户类型：</td>
								<c:choose>
									<c:when test="${merchantVo.custType =='0'}">
										<td>个人</td>
									</c:when>
									<c:when test="${merchantVo.custType =='1'}">
										<td>企业</td>
									</c:when>
									<c:when test="${merchantVo.custType =='2'}">
										<td>个体户</td>
									</c:when>
								</c:choose>

								<%-- <td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.custType }</td> --%>
							</tr>
							<tr>
								<td class="td-left"  width="18%">商户名称：</td>
								<td class="td-right"  width="32%" style="color:#666;padding:10px 8px">
									<input type="text" id="custName" name="custName" maxlength="100"  value=${merchantVo.custName } style="width:90%">
								</td>
								<td class="td-left"  width="18%">商户简称：</td>
								<td class="td-right"  width="32%" style="color:#666;padding:10px 8px">
									<input type="text" id="shortName" name="shortName" value=${merchantVo.shortName } style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">商户邮箱：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="merchantEmail" name="merchantEmail" placeholder="请输入商户邮箱" value=${merchantVo.merchantEmail } style="width:90%">
								</td>
								<td class="td-left">客服号码：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="contactPhone" name="contactPhone" placeholder="请输入客服号码" value =${merchantVo.contactPhone } style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">商户地址：</td>
								<td class="td-right"  style="color:#666;padding:10px 8px">
									<input type="text" id="custAdd" name="custAdd"  placeholder="详细地址" value =${merchantVo.custAdd } style="width:100%">
								</td>
							</tr>
							<tr>
								<td class="td-left">营业执照编号：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="businessLicense" name="businessLicense"  placeholder="请输入营业执照" value=${merchantVo.businessLicense } style="width:90%">
								</td>
								<td class="td-left">营业执照有效期：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">

									<input type="text" id="businessTermStart" name="businessTermStart" value = ${merchantVo.businessTermStart }  onfocus="WdatePicker({minDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
									<label class="label-tips" id="businessTermLabStart"></label>
									-
									<input type="text" id="businessTermEnd" name="businessTermEnd" value =
									<c:choose>
									<c:when test="${merchantVo.businessTermEnd =='forever'}">
											长期
									</c:when>
											<c:otherwise>
												${merchantVo.businessTermEnd }
											</c:otherwise>
									</c:choose>
										   onfocus="WdatePicker({minDate:'%y-%M-%d'})" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important; width:30%"/>
									<label class="label-tips" id="businessTermLabEnd"></label>

									<input type="button" onclick="fun()" value="长期" />
									<script>
										function fun(){
											$("input[name='businessTermEnd']").val("2040-05-25").focus();
											$("#businessTermEnd").attr("value","2040-05-25");
										}
									</script>
								</td>
							</tr>
							<tr>
								<td class="td-left">营业执照扫描件：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success businessPhotoClick" data-target="#previewImageModal" >
										<label id="businessPhotoDiv"  style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
											<img  id="businessPhotoImageDiv" onclick="bigImg(this);"  style="width:100%;height:100%;display:none"  />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75 " >
										<input type="hidden" id="businessPhotoImageVal02"  />
										<input type="file" name="businessPhoto" id="businessPhoto" onchange="showBusinessPhotoImage(this)" />
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>

								</td>
							</tr>
							<tr>
								<td class="td-left">所属业务人员：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="custManager" name="custManager"  placeholder="请输入所属业务人员" value=${merchantVo.custManager } style="width:90%">
								</td>
								<td class="td-left">所属代理商：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" name="agentName" id="agentName" placeholder="请输入所属代理商" value=${merchantVo.agentName } style="width:90%">
								</td>
							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td></tr>
							<tr>
							</tr><tr>
								<td class="td-left">法人真实姓名：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="representativeName" name="representativeName" placeholder="请输入法人真实姓名" value=${merchantVo.representativeName } maxlength="50" style="width:90%">
								</td>
								<td class="td-left">法人身份证号码：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" name="representativeCertNo" id="representativeCertNo" placeholder="请输入法人身份证号码" value=${merchantVo.representativeCertNo } style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left" >身份证图片正面：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success certAttribute1Click"   data-target="#previewImageModal" >
										<label id="certAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
											<img  id="certAttribute1ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="certAttribute1Val02"  />
										<input type="file" name="certAttribute1" id="certAttribute1"  onchange="showCertAttribute1Image(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>

								</td>
							</tr>
							<tr>
								<td class="td-left" >身份证图片背面：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success certAttribute2Click"  data-target="#previewImageModal"  >
										<label id="certAttribute2Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
											<img  id="certAttribute2ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="certAttribute2Val02"  />
										<input type="file" name="certAttribute2" id="certAttribute2" onchange="showCertAttribute2Image(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>

								</td>
							</tr>
							<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">联系信息</td></tr>
							<tr>
							</tr>

							<tr>
								<td class="td-left">联系人姓名：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="contactName" name="contactName" placeholder="请输入联系人姓名" value=${merchantVo.contactName } maxlength="50" style="width:90%">

								</td>
								<td class="td-left">联系人手机号码：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" name="contactMobile" id="contactMobile" placeholder="请输入联系人手机号码" value=${merchantVo.contactMobile } style="width:90%">
								</td>
							</tr>
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
							<tr>
								<td class="td-left">银行卡号</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="compMainAcct" name="compMainAcct" maxlength="100" placeholder="请输入银行卡号" value=${merchantVo.compMainAcct } style="width:90%">
								</td>

								<td class="td-left">银行类型：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<select class="width-90" id="compAcctBank" name="compAcctBank">
										<c:if test="${not empty banklist }">
											<option value=${merchantVo.compAcctBank }>${merchantVo.compAcctBank }</option>
											<c:forEach items="${banklist }" var="bank">
												<option value="${bank.bankCode}">${bank.bankName}</option>
											</c:forEach>
										</c:if>
									</select>
								</td>
							</tr>
							<tr>
								<td class="td-left">开户行：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="branchBank" name="branchBank" placeholder="请输入开户行" value=${merchantVo.branchBank } style="width:90%">
								</td>
								<td class="td-left">开户人：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="bankAcctName" name="bankAcctName" placeholder="请输入开户人" value=${merchantVo.bankAcctName } style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">开户省份：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<select class="width-90" id="bankProvinceName" onchange="getBankCityList();">
										<c:if test="${not empty provincelist_ }">
											<option value=${merchantVo.bankProvinceName }>${merchantVo.bankProvinceName }</option>
											<c:forEach items="${provincelist_ }" var="prov">
												<option value="${prov.provinceId}">${prov.provinceName}</option>
											</c:forEach>
										</c:if>
									</select>
								</td>
								<td class="td-left">开户城市：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<select class="width-90" id="bankCityName">
										<option value="" id="cityDef">${merchantVo.bankCityName }</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="td-left">网点号：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<input type="text" id="cnaps" name="cnaps" placeholder="请输入网点号" value =${merchantVo.cnaps } style="width:90%"> <a href="http://www.lianhanghao.com" target="_blank">[查找]</a>
								</td>
								<td class="td-left">结算类型：</td>
								<td class="td-right" style="color:#666;padding:10px 8px">
									<select class="width-90" id="compMainAcctType">
										<option value="01">对公</option>
										<option value="02">对私</option>
									</select>

								</td>
							</tr>
							<tr id="openAccount_" style="display: none">
								<td class="td-left" >开户许可证：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success openAccountClick"  data-target="#previewImageModal" >
										<label id="openAccountDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">
											<img  id="openAccountImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none"/>
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="openAccountVal02"  />
										<input type="file" name="openAccount" id="openAccount" onchange="showopenAccountImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>

								</td>
							</tr>
							<tr id="bankCardPhoto_">
								<td class="td-left" >银行卡照<span style="color:red"></span></td>
								<td class="td-right" >
									<a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal" >
										<label id="bankCardPhotoDiv" class="uploadImage">
											<img  id="bankCardPhotoImageDiv" style="width:100%;height:100%;display:none"/>
										</label>
									</a>
									<div style="float:left;margin-top:75" >
										<input type="file" name="bankCardPhoto" id="bankCardPhoto" onChange="showBankCardPhotoImage(this)"/> <p> <span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>

									<label class="label-tips" id="bankCardPhotoLabel" style="float:left;margin-top:88"></label>
								</td>
							</tr>
							<%--
                          <tr>
                           <td colspan="4" class="headlerPreview" style="background:#7ebde1;">操作记录</td></tr>
                          <tr>
                           </tr>

                           <tr>
                               <td class="td-left">录入人：</td>
                               <td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.createId }</td>
                               <td class="td-left">录入时间：</td>
                               <td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.createTime }</td>
                           </tr>
                           <tr>
                               <td class="td-left">审核人：</td>
                               <td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.aduitUserName }</td>
                               <td class="td-left">录入时间：</td>
                               <td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.modifyTime }</td>
                           </tr>
                           <tr>
                               <td class="td-left">审核记录：</td>
                               <td class="td-right" style="color:#666;padding:10px 8px"></td>
                           </tr>

                           <tr><td colspan="4" class="headlerPreview" style="background:#7ebde1;">付款二维码</td></tr>
                           <tr></tr>
                                --%>
							</tbody></table>
						<div style="margin:50px 0 0 0;text-align:center">
							<button type="button"  class="btn btn-primary updateMerchantBtn" onclick="updateMerchantBtn()">保存</button>
							<button type="button"  class="btn btn-default" onclick="exit()">关闭</button>
						</div>
					</div>
				</div>
				<!-- 图片预览 -->
				<div class="modal fade" id="previewImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog showDiv" >
						<div id="showImageDiv" style="width:100%;height:100%;">
							<img id="showImage" style="width:100%;height:100%;">
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

</body>


<script type="text/javascript">
</script>
</html>