<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.seven.micropay.channel.enums.BestBankCode"%>
<%@page import="com.seven.micropay.channel.enums.MerUpdateType"%>
<%@page import="com.sevenpay.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.merchant.merchantReported.MerchantEnterReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.qifenqian.bms.basemanager.agency.controller.AgentRegisterPath" %>
<%@ page import="java.util.*" %>
<%@ include file="/include/template.jsp"%>

<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src='<c:url value="/static/js/jquery.divbox.js"/>'></script>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/up.js"/>'></script>
<link rel="stylesheet" href="<c:url value='/static/css/base.css' />" />
<link rel="stylesheet" href="<c:url value='/static/css/home.css' />" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>进件详情</title>
	<meta name="keywords" content="七分钱后台管理系统" />
	<meta name="description" content="七分钱后台管理" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style type="text/css">
		table tr td{word-wrap:break-word;word-break:break-all;}
	</style>
</head>
<body>
<!-- 用户信息 -->
<%@ include file="/include/top.jsp"%>

<div class="main-container" id="main-container">
	<script type="text/javascript">
		try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>

	<div class="main-container-inner">
		<div class="main-content">
			<!-- 路径 -->
			<%@ include file="/include/path.jsp"%>
			<!-- 主内容 -->
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					<input type="hidden" id="taskCode" name="taskCode" value ="${remark}"/>
					<input type="hidden" id="status" name="status" value="${status}"/>
					<input type="hidden" id="channlCode" name="channlCode" value="KFT_PAY"/>
					<input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
					<input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/>
					<input type="hidden" id="businessPhototemp"/>
					<input type="hidden" id="openPhototemp"/>
					<input type="hidden" id="certAttribute1temp" />
					<input type="hidden" id="certAttribute2temp" />
					<input type="hidden" id="bankCardPhototemp"/>
					<input type="hidden" id="bankCardBackPhototemp"/>
					<input type="hidden" id="settleIdCardtemp"/>
					<input type="hidden" id="cooperatetemp"/>
					
					
					<div id="door_temp"></div>
					<section class="aui-content">
					    <div class="aui-content-up">
					    <form  id="merchantForm"  method="post">
					    <table id="merchant_table" class="list-table">
					    <tbody>
                        	<tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">商户信息</td>
							</tr>
						    <tr>
							</tr>
                            <tr>
								<td class="td-left" width="18%">商户编号：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="merchantCode" name="merchantCode" readonly maxlength=""  value="${custInfo.merchantCode }" style="width:90%">
								</td>
                                <td class="td-left" width="18%">商户简称：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="shortName" name="shortName" readonly placeholder="请输入商户简称" maxlength=""  value="${custInfo.shortName }" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left" width="18%">商户名称：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="custName" name="custName" readonly placeholder="请输入营业执照名称/店名" maxlength=""  value="${custInfo.custName }" style="width:90%">
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
							    <td class="td-left">商户类型：</td>
								<c:choose>
									<c:when test="${merchantVo.custType =='0'}">
										<td class="td-right">个人</td>
									</c:when>
									<c:when test="${merchantVo.custType =='1'}">
										<td class="td-right">企业</td>
									</c:when>
									<c:when test="${merchantVo.custType =='2'}">
										<td class="td-right">个体户</td>
									</c:when>
								</c:choose>
								   <!-- <select  name="merchantProperty" id="merchantProperty"  class="width-90" onchange = "getType();">
										<option value="">--请选择商户类型--</option>
										<option value="1">--个人--</option>
										<option value="2">--企业--</option>
										<option value="3">--个体工商户--</option>
										<option value="4">--事业单位--</option>
									</select>
									<label class="label-tips" id="merchantPropertyLab"></label>	 -->
							</tr>
	                        <%-- <tr>
							    <td class="td-left">商户行业信息：</td>
								<td class="td-right" colspan="3"> 
								<div class="col-xs-2 pd0" style="padding:0;">
								   <select name="industry" id="industry" class="width-90" onchange="getLevel();">
										<option value="">--请选择行业--</option>
											<c:if test="${not empty industryList }">
					                           	<c:forEach items="${industryList }" var="industry">
					                           		<option id="${industry.levelOne}" value="${industry.levelOne}">${industry.levelOne}</option>
					                           	</c:forEach>
				                 		  	</c:if>
									</select>	
								</div>
								<div class="col-xs-2 pd0" style="margin:0 1%;padding:0;" >
	                                <select name="levelTwo" id="levelTwo" class="form-control" onchange="getLevelTwo();" >
	                                    <option value="">--请选择二级类目--</option>
	                                </select>
	                               	<label id="levelTwoLabel" class="label-tips"></label>
	                            </div>
	                            <div class="col-xs-2 pd0" style="margin:0 1%;padding:0;" >
	                                <select name="levelThree" id="levelThree" class="form-control">
	                                    <option value="">--请选择三级类目--</option>
	                                </select>
	                               	<label id="levelThreeLab" class="label-tips"></label>
	                            </div>
								</td>
							</tr> --%>
							<tr>
								<td class="td-left">注册地址：</td>
								<td class="td-right" colspan="3">
									<%-- <div class="col-xs-2 pd0" style="padding:0;">
	                                    <select class="form-control" name="merchantProvince" id="merchantProvince" onchange="getMerchantCity();">
	                                       	<option value="">--请选择省--</option>
		                                    <c:if test="${not empty provinceList }">
		                                        <c:forEach items="${provinceList }" var="merchantProvince">
		                                            <option id="${merchantProvince.provinceName}"
		                                                    value="${merchantProvince.provinceName}">
		                                                ${merchantProvince.provinceName}
		                                            </option>
		                                        </c:forEach>
		                                    </c:if>
		                                </select>
	                                </div>
	                                <div class="col-xs-2 pd0" style="margin:0 1%;padding:0;">
		                                <select class="form-control" name="merchantCity" id="merchantCity"  onchange="getMerchantArea();">
		                                    <option value="" id="cityDef">--请选择市--</option>
		                                </select>
	                                </div>
	                                <div class="col-xs-2 pd0" style="padding:0;">
		                                <select class="form-control" name="merchantArea" id="merchantArea" >
		                                    <option value="" id="areaDef">--请选择区--</option>
		                                </select>
	                                </div>
                                    <div class="col-xs-5 pd0" style="padding:0;margin-left:1%">
	                                    <input type="text" name="cprRegAddr" id="cprRegAddr" readonly placeholder="详细地址"  value="${custInfo.custAdd }" style="width:100%">
	                                </div>
	                                <label class="label-tips" id="countryLab"></label> --%>
	                                <input type="text" name="cprRegAddr" id="cprRegAddr" readonly placeholder="详细地址"  value="${custInfo.custAdd }">
								</td>
							</tr>
							<!-- <tr>
							    <td class="td-left">商户属性：</td>
								<td class="td-right"> 
								   <select  name="merchantAttribute" id="merchantAttribute"  class="width-90" >
										<option value="1">--实体特约商户--</option>
										<option value="2">--网络特约商户--</option>
										<option value="3">--实体兼网络特约商户--</option>
									</select>
									<label class="label-tips" id="merchantAttributeLab"></label>	
								</td>
								<td class="td-left">业务场景说明：</td>
								<td class="td-right"> 
								    <input type="text" id="businessScene" name="businessScene" placeholder="业务场景说明" maxlength=""  value="" style="width:90%">
									<label class="label-tips" id="businessSceneLab"></label>
								</td>
                                
							</tr> -->	
						 	<tr>
								<td class="td-left">营业执照编号：</td>
								<td class="td-right">
									<input type="text" name="businessLicense" id="businessLicense" readonly  placeholder="请输入营业执照"  value="${custInfo.businessLicense }" style="width:90%">
									<label class="label-tips" id="businessLicenseLab"></label>
								</td>
								<!-- <td class="td-left">营业执照有效期：</td>
								<td class="td-right"> 
									<input type="text" id="businessTermEnd" name="businessTermEnd" placeholder="请输入8位营业执照有效截止期"  value="" maxlength="" style="width:90%">
									<label class="label-tips" id="businessLicenseLab"></label>
								</td> -->
							</tr>
							<tr id="businessPhotoType" style = "display:">
								<td class="td-left">营业执照照片：</td>
								<td class="td-right" colspan="3">
									<a data-toggle="modal" class="tooltip-success businessPhotoClick" data-target="#previewImageModal">
										<label id="businessPhotoDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img id="businessPhotoImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;display:none">										  
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input type="hidden" id="businessPhotoImageVal02">  
										<input type="file" name="businessPhoto" id="businessPhoto" onChange="showBusinessPhotoImage(this)">
									 	<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
                            <tr id="doorPhotoType" style = "display:">
								<td class="td-left">门头照照片：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success doorPhotoClick"  data-target="#previewImageModal"  >
										<label id="doorPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="doorPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="doorPhotoPath" name="doorPhotoPath" />
										<input type="hidden" id="doorPhotoImageVal02"  />  
										<input type="file" name="doorPhoto" id="doorPhoto" onchange="showDoorPhotoImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="cooperateType" style = "display:">
								<td class="td-left">合作函：</td>
								<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success cooperateClick"  data-target="#previewImageModal"  >
										<label id="cooperateDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="cooperateImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input  type="hidden" id="cooperatePath" name="cooperatePath" />
										<input type="hidden" id="cooperateImageVal02"  />  
										<input type="file" name="cooperate" id="cooperate" onchange="showCooperateImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td>
							</tr>
							<tr></tr>
                           	<tr>
								<td class="td-left">法人真实姓名：</td>
								<td class="td-right"> 
									<input type="text" id="representativeName" name="representativeName" readonly placeholder="请输入法人真实姓名"  value="${custInfo.representativeName }" maxlength="" style="width:90%">
									<label class="label-tips" id="representativeNameLab"></label>
								</td>
								<td class="td-left">法人身份证号码：</td>
								<td class="td-right"> 
									<input type="text" name="representativeCertNo" id="representativeCertNo" readonly  placeholder="请输入法人身份证号码"  value="${custInfo.representativeCertNo }" style="width:90%">
									<label class="label-tips" id="representativeCertNoLab"></label>
								</td>
							</tr>
                            <tr>
                            	<!-- <td class="td-left">法人证件类型：</td> -->
								<%-- <c:choose>
									<c:when test="${merchantVo.representativeCertType =='00'}">
										<td class="td-right">身份证</td>
									</c:when>
									<c:when test="${merchantVo.representativeCertType =='05'}">
										<td class="td-right">港澳居民往来内地通行证</td>
									</c:when>
									<c:when test="${merchantVo.representativeCertType =='03'}">
										<td class="td-right">军人证</td>
									</c:when>
									<c:when test="${merchantVo.representativeCertType =='04'}">
										<td class="td-right">警察证</td>
									</c:when>
									<c:when test="${merchantVo.representativeCertType =='06'}">
										<td class="td-right">台湾居民来往大陆通行证</td>
									</c:when>
									<c:when test="${merchantVo.representativeCertType =='07'}">
										<td class="td-right">护照</td>
									</c:when>
									<c:when test="${merchantVo.representativeCertType =='98'}">
										<td class="td-right">单位证件</td>
									</c:when>
									<c:when test="${merchantVo.representativeCertType =='06'}">
										<td class="td-right">其他证件</td>
									</c:when>
								</c:choose> --%>
								<!-- <td class="td-right"> 
									<select name="representativeCertType" id="representativeCertType" style="width:90%;"  >
										<option value="">--请选择--</option>
										<option value="00">--身份证--</option>
										<option value="03">--军人证--</option>
										<option value="04">--警察证--</option>
										<option value="05">--港澳居民往来内地通行证--</option>
										<option value="06">--台湾居民来往大陆通行证--</option>
										<option value="07">--护照--</option>
										<option value="98">--单位证件--</option>
										<option value="06">--其他证件--</option>
									</select>
									<label class="label-tips" id="representativeCertTypeLab"></label>
								</td> -->
								<%-- <td class="td-left">手机号码：</td>
								<td class="td-right"> 
									<input type="text" name="mobileNo" id="mobileNo" placeholder="请输入手机号码"  value="${custInfo.mobile }" style="width:90%">
								</td> --%>
								
								<!-- <td class="td-left">身份证有效期：</td>
								<td class="td-right"> 
									<input type="text" id="representativeTermEnd" name="representativeTermEnd" placeholder="请输入8位身份证有效截止期"  value="" maxlength="" style="width:90%">
									<label class="label-tips" id="representativeTermEndLab"></label>
								</td> -->
							</tr>
							<tr>
								<td class="td-left">联系人姓名：</td>
								<td class="td-right"> 
									<input type="text" id="contactName" name="contactName" placeholder="请输入联系人姓名" readonly  value="${custInfo.contactName }" maxlength="" style="width:90%">
									<label class="label-tips" id="contactNameLab"></label>
								</td>
								<td class="td-left">联系人手机号：</td>
								<td class="td-right"> 
									<input type="text" id="contactMobile" name="contactMobile" placeholder="请输入联系人电话" readonly  value="${custInfo.contactMobile }" maxlength="" style="width:90%">
									<label class="label-tips" id="contactMobileLab"></label>
								</td>
							</tr>
							<tr>
								
								<td class="td-left">联系人邮箱：</td>
								<td class="td-right"> 
									<input type="text" id="merchantEmail" name="merchantEmail" placeholder="请输入联系人邮箱" readonly  value="${custInfo.merchantEmail }" maxlength="" style="width:90%">
									<label class="label-tips" id="merchantEmailLab"></label>
								</td>
							</tr>
							<tr id="legalIdCardType" style = "display:">
								<td class="td-left">法人身份证正面：</td>
								<td class="td-right" colspan="3">
									<a data-toggle="modal" class="tooltip-success certAttribute1Click" data-target="#previewImageModal">
									<label id="certAttribute1Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img id="certAttribute1ImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%; display:none">
									</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input  type="hidden" id="certAttribute1FilePath" name="certAttribute1FilePath" />
										<input type="hidden" id="certAttribute1Val02">  
										<input type="file" name="certAttribute1" id="certAttribute1" onChange="showCertAttribute1Image(this)"> 
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
								</td>
							</tr>
							<tr id="legalIdCardBackType" style = "display:">
								<td class="td-left">法人身份证背面：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle="modal" class="tooltip-success certAttribute2Click" data-target="#previewImageModal">
										<label id="certAttribute2Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="certAttribute2ImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%; display:none">
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input  type="hidden" id="certAttribute2FilePath" name="certAttribute2FilePath" />
										<input type="hidden" id="certAttribute2Val02">  
										<input type="file" name="certAttribute2" id="certAttribute2" onChange="showCertAttribute2Image(this)"> 
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="next_id">
								<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
							</tr>
	                        <tr>
								<td class="td-left">结算账户名称：</td>
								<td class="td-right"> 
									<input type="text" id="actNm" name="actNm" maxlength="100" placeholder="请输入结算账户名称" readonly  value="${custInfo.custName }" style="width:90%">
									<label class="label-tips" id="actNmLab"></label>
								</td>
								<td class="td-left">银行卡号：</td>
								<td class="td-right"> 
									<input type="text" id="bankCardNo" name="bankCardNo" maxlength="100" placeholder="请输入银行卡号" readonly  value="${custInfo.compMainAcct }" style="width:90%">
									<label class="label-tips" id="bankCardNoLab"></label>
								</td>
							</tr>
                            <tr>
								<td class="td-left">结算类型：</td>
								<c:choose>
									<c:when test="${merchantVo.custType =='0' || merchantVo.custType =='2'}">
										<td class="td-right">对私</td>
									</c:when>
									<c:when test="${merchantVo.custType =='1'}">
										<td class="td-right">对公</td>
									</c:when>
								</c:choose>
								<!-- <td class="td-right">
									<select name="actType" id="actType" style="width-90" >
										<option value="">--请选择--</option>
										<option value="1">--个人--</option>
										<option value="2">--企业--</option>
									</select> 
									<label class="label-tips" id="actTypeLab"></label>
								</td> -->
								<!-- <td class="td-left">结算银行：</td>
								<td class="td-right"> 
									<select name="settleBankNo" id="settleBankNo" style="width:90%;"  >
										<option value="">--请选择--</option>
										<option value="1041000">--中国银行--</option>
										<option value="3135840">--平安银行--</option>
										<option value="3011000">--交通银行--</option>
										<option value="0025840">--邮政储蓄银行--</option>
										<option value="3031000">--光大银行--</option>
										<option value="3041000">--华夏银行--</option>
										<option value="3021000">--中信银行--</option>
										<option value="3065810">--广发银行--</option>
										<option value="3102900">--浦东发展银行--</option>
										<option value="3135841">--上海银行--</option>
										<option value="1021000">--工商银行--</option>
										<option value="1031000">--农业银行--</option>
										<option value="1051000">--建设银行--</option>
										<option value="3051000">--民生银行--</option>
										<option value="3085840">--招商银行--</option>
										<option value="3091000">--兴业银行--</option>
									</select>
									<label class="label-tips" id="settleBankNoLab"></label>
								</td> -->
							</tr>
							<tr id="openPhotoType" style = "display:">
								<td class="td-left">开户许可证：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle="modal" class="tooltip-success openPhotoClick" data-target="#previewImageModal">
										<label id="openPhotoDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="openPhotoImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;" >
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input type="hidden" id="certAttribute0Val02">  
										<input type="file" name="certAttribute0" id="certAttribute0" onChange="showCertAttribute0Image(this)">
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="bankCardPhotoType" style = "display:">
				                <td class="td-left">银行卡正面照：</td>
		                    	<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success bankCardPhotoClick"  data-target="#previewImageModal"  >
										<label id="bankCardPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="bankCardPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="bankCardPhotoImageVal02"  />  
										<input type="file" name="bankCardPhoto" id="bankCardPhoto" onchange="showBankCardPhotoImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							</tr>
							<tr id="bankCardBackPhotoType" style = "display:">
				                <td class="td-left"> 银行卡反面照： </td>
		                    	<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success bankCardBackPhotoClick"  data-target="#previewImageModal"  >
										<label id="bankCardBackPhotoDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="bankCardBackPhotoImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="bankCardBackPhotoVal02"  />   
										<input type="file" name="bankCardBackPhoto" id="bankCardBackPhoto" onchange="showbankCardBackPhotoImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
					         </tr>
					         <tr id="settleIdCardType" style = "display:">
				                <td class="td-left">手持身份证正面照： </td>
		                    	<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success settleIdCardClick"  data-target="#previewImageModal"  >
										<label id="settleIdCardDiv"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="settleIdCardImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="settleIdCardVal02"  />  
										<input type="file" name="settleIdCard" id="settleIdCard" onchange="showsettleIdCardImage(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							 </tr>
							 <!-- <tr>
							    <td  id="wxsm" value="010101">微信扫码</td>
							    <td >
							        <input type="text" id="wxsmRate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="wxsmAttach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="wxsmType" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>
							<tr>
							    <td id="wxgzh" value="010102">微信公众号/H5</td>
							    <td >
							        <input type="text" id="wxgzhRate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="wxgzhAttach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="wxgzhType" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>
							<tr>
							    <td id="wxapp" value="010103">微信APP</td>
							    <td >
							        <input type="text" id="wxappRate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="wxappAttach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="wxappType" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>
							<tr>
							    <td id="wxsmS0" value="010104">微信扫码—S0</td>
							    <td >
							        <input type="text" id="wxsmS0Rate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="wxsmS0Attach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="wxsmS0Type" style="width:50%;"  >
							    		<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>
							<tr>
							    <td id="wxgzhS0" value="010105">微信公众号/H5—S0</td>
							    <td >
							        <input type="text" id="wxgzhS0Rate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="wxgzhS0Attach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="wxgzhS0Type" style="width:50%;"  >
							    		<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>
							<tr>
							    <td id="wxappS0" value="010106">微信APP—S0</td>
							    <td >
							        <input type="text" id="wxappS0Rate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="wxappS0Attach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="wxappS0Type" style="width:50%;"  >
							    		<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>
							<tr>
							    <td id="zfbsm" value="010201">支付宝扫码</td>
							    <td >
							        <input type="text" id="zfbsmRate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="zfbsmAttach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="zfbsmType" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>
							<tr>
							    <td id="zfbfwc" value="010202">支付宝服务窗</td>
							    <td >
							        <input type="text" id="zfbfwcRate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="zfbfwcAttach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="zfbfwcType" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>		
							
							<tr>
							    <td id="zfbapp" value="010203">支付宝APP</td>
							    <td >
							        <input type="text" id="zfbappRate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="zfbappAttach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="zfbappType" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>
							<tr>
							    <td id="zfbsmS0" value="010204">支付宝扫码—S0</td>
							    <td >
							        <input type="text" id="zfbsmS0Rate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="zfbsmS0Attach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="zfbsmS0Type" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>	
							<tr>
							    <td id="zfbfwcS0" value="010205">支付宝服务窗—S0</td>
							    <td >
							        <input type="text" id="zfbfwcS0Rate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="zfbfwcS0Attach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="zfbfwcS0Type" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>	
							<tr>
							    <td id="zfbappS0" value="010206">支付宝APP—S0</td>
							    <td >
							        <input type="text" id="zfbappS0Rate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="zfbappS0Attach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="zfbappS0Type" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>	
							<tr>
							    <td id="ylsm" value="010301">银联扫码</td>
							    <td >
							        <input type="text" id="ylsmRate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="ylsmAttach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="ylsmType" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>	
							<tr>
							    <td id="dxkj" value="010501">短信快捷</td>
							    <td >
							        <input type="text" id="dxkjRate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="dxkjAttach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="dxkjType" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>	
							<tr>
							    <td id="dxkjS0" value="010503">短信快捷-S0</td>
							    <td >
							        <input type="text" id="dxkjS0Rate"  placeholder="万分比费率"  value="">
							    </td>
							    <td>
							       	<input type="text" id="dxkjS0Attach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="dxkjS0Type" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>	
							<tr>
							    <td id="wyzx" value="010601">网银在线</td>
							    <td >
							        <input type="text" id="wyzxRate"  placeholder="万分比费率"  value="">
							    </td>
							    <td >
							       	<input type="text" id="wyzxAttach" placeholder="附加费率(分)"  value="" style="width:90%"> 
							    </td>
							    <td >
							    	<select id="wyzxType" style="width:50%;"  >
										<option value="">--请选择费用类型--</option>
										<option value="1">--S0--</option>
										<option value="2">--T0--</option>
										<option value="3">--T1--</option>
									</select>
							    </td>
							</tr>		 -->			           
						</tbody>
						</table>
					    </form>
					    </div>
					    <div style="margin:50px 0 0 0;text-align:center">
					    	<!-- <button class="btn btn-primary" id='submitData'  data-toggle='modal' data-target="#submitModal">提交报备</button> --> 
					    	<button type="button"  class="btn btn-default" onclick="exit()">关闭</button> 
					    </div>
					</section>
					</div>
				</div>
			</div><!-- /.modal -->
			<!-- 底部-->
			<%@ include file="/include/bottom.jsp"%>
		</div><!-- /.main-content -->
		<!-- 设置 -->
		<%@ include file="/include/setting.jsp"%>
	</div><!-- /.main-container-inner -->

	<!-- 向上置顶 -->
	<%@ include file="/include/up.jsp"%>
</div><!-- /.main-container -->
</div>

<!-- 图片预览 -->
<div class="modal fade" id="previewImageModal"  aria-hidden="true">
	<div class="modal-dialog showDiv" id="imageDiv" style="width:60%;height:80%;">
    	<div id="showImageDiv" style="width:100%;height:100%;">
        	<img id="showImage" style="width:100%;height:100%;">
        </div>
     </div>
</div>   
<script type="text/javascript">

	function getType(){
		var merchantProperty = $("#merchantProperty").val();
		if("1" == merchantProperty ){
			$("#openPhotoType").attr("style","display:none");
			$("#businessPhotoType").attr("style","display:none");
			$("#bankCardPhotoType").attr("style","display:");
			$("#bankCardBackPhotoType").attr("style","display:");
			$("#settleIdCardType").attr("style","display:");
		}else if("3" == merchantProperty ){
			$("#openPhotoType").attr("style","display:none");
			$("#businessPhotoType").attr("style","display:");
			$("#bankCardPhotoType").attr("style","display:");
			$("#bankCardBackPhotoType").attr("style","display:");
			$("#settleIdCardType").attr("style","display:");
		}else{
			$("#openPhotoType").attr("style","display:");
			$("#businessPhotoType").attr("style","display:");
			$("#bankCardPhotoType").attr("style","display:none");
			$("#bankCardBackPhotoType").attr("style","display:none");
			$("#settleIdCardType").attr("style","display:none");
			
		}
	}

	/***获取行业信息***/
	function getLevel(){
		var levelOne = $("#levelOne").val();
		$.post(window.Constants.ContextPath +"<%=MerchantEnterReportedPath.BASE + MerchantEnterReportedPath.KFTSELINDUSTRYTWO %>",
		{
			"levelOne":levelOne
		},
		function(data){
			if(data.result=="SUCCESS"){
				var kftIndustryTwoList = data.kftIndustryTwoList;
				$("#levelTwo").html("");
	   			for ( var kftIndustry in kftIndustryTwoList) {
	   				$("#levelTwo").append(
	   						"<option value='"+ kftIndustryTwoList[kftIndustry].levelTwo +"'>"
	   								+ kftIndustryTwoList[kftIndustry].levelTwo + "</option>"); 
	   			}
			}
		},'json'
		);	
	}
	
	/***获取行业信息***/
	function getLevelTwo(){
		var levelOne = $("#levelOne").val();
		var levelTwo = $("#levelTwo").val();
		$.post(window.Constants.ContextPath +"<%=MerchantEnterReportedPath.BASE + MerchantEnterReportedPath.KFTSELINDUSTRYTHREE %>",
		{
			"levelOne":levelOne,
			"levelTwo":levelTwo
		},
		function(data){
			if(data.result=="SUCCESS"){
				var kftIndustryThreeList = data.kftIndustryThreeList;
				$("#levelThree").html("");
	   			for ( var kftIndustry in kftIndustryThreeList) {
	   				$("#levelThree").append(
	   						"<option value='"+ kftIndustryThreeList[kftIndustry].levelCode +"'>"
	   								+ kftIndustryThreeList[kftIndustry].levelThree + "</option>"); 
	   			}
			}
		},'json'
		);	
	}
	
	
	/***获取商户注册所在地区城市***/
  	function getMerchantCity(){
  		var provinceName = $("#merchantProvince").val();
  		$.post(window.Constants.ContextPath +"<%=MerchantEnterReportedPath.BASE + MerchantEnterReportedPath.SELKFTCITY %>",
   		{
   			"provinceName":provinceName
   		},
   		function(data){
   			if(data.result=="SUCCESS"){
   				var kftCityList = data.kftCityList;
   				$("#merchantCity").html("");
          			for ( var city in kftCityList) {
          				$("#merchantCity").append(
          						"<option value='"+ kftCityList[city].cityName +"'>"
          								+ kftCityList[city].cityName + "</option>"); 
          			}
          			getMerchantArea();;
   			}else{
   				alert("省份不能为空");
   			}
   		},'json'
   		);	
    }
  	/***获取商户注册所在地区县区***/
  	function getMerchantArea(){
  		var provinceName = $("#merchantProvince").val();
  		var cityName = $("#merchantCity").val();
  		$.post(window.Constants.ContextPath +"<%=MerchantEnterReportedPath.BASE + MerchantEnterReportedPath.SELKFTAREA %>",
  		{
  			"provinceName":provinceName,
  			"cityName":cityName
  			
  		},
  		function(data){
  			if(data.result=="SUCCESS"){
  				var kftAreaList = data.kftAreaList;
  				$("#merchantArea").html("");
         			for ( var area in kftAreaList) {
         				$("#merchantArea").append(
         						"<option value='"+ kftAreaList[area].areaId +"'>"
         								+ kftAreaList[area].areaName + "</option>"); 
         			}
  			}else{
  				alert("市不能为空");
  			}
  		},'json'
  		);	
    }
  	
  	
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
        
   	/** 照片点击预览 **/
   	$('.shopInteriorClick').click(function(){
   		var divObj = document.getElementById("showImageDiv");
   		var imageObj = document.getElementById("showImage");
   		var obj = document.getElementById("shopInteriorClick");
   		return previewImage(divObj,imageObj,obj); 
   	});

    //图片预览
    function shopInteriorImage(obj){  
   		var divObj = document.getElementById("shopInteriorDiv");  
   		var imageObj = document.getElementById("shopInteriorImageDiv");  
   		return previewImage(divObj,imageObj,obj);  
   }
    
    function showCooperateImage(obj){  
		 var divObj = document.getElementById("cooperateDiv");  
		 var imageObj = document.getElementById("cooperateImageDiv");  
		 return previewImage(divObj,imageObj,obj);  
	}
   function showBusinessPhotoImage(obj){  
   		 var divObj = document.getElementById("businessPhotoDiv");  
   		 var imageObj = document.getElementById("businessPhotoImageDiv");  
   		 return previewImage(divObj,imageObj,obj);  
   }

	function showOpenPhotoImage(obj){  
	 	var divObj = document.getElementById("openPhotoDiv");  
	 	var imageObj = document.getElementById("openPhotoImageDiv");  
	 	return previewImage(divObj,imageObj,obj);  
	}

	function showDoorPhotoImage(obj){  
		 var divObj = document.getElementById("doorPhotoDiv");  
		 var imageObj = document.getElementById("doorPhotoImageDiv");  
		 return previewImage(divObj,imageObj,obj);  
	}

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

   	function showLegalCertAttribute1Image(obj){  
  		 var divObj = document.getElementById("legalCertAttribute1Div");  
  		 var imageObj = document.getElementById("legalCertAttribute1ImageDiv");  
  		 return previewImage(divObj,imageObj,obj);  
   	}

   	function showLegalCertAttribute2Image(obj){  
   		 var divObj = document.getElementById("legalCertAttribute2Div");  
   		 var imageObj = document.getElementById("legalCertAttribute2ImageDiv");  
   		 return previewImage(divObj,imageObj,obj);  
   	}

   	function showBankCardPhotoImage(obj){  
  		 var divObj = document.getElementById("bankCardPhotoDiv");  
  		 var imageObj = document.getElementById("bankCardPhotoImageDiv");  
  		 return previewImage(divObj,imageObj,obj);  
  		}
   	function showbankCardBackPhotoImage(obj){  
  		 var divObj = document.getElementById("bankCardBackPhotoDiv");  
  		 var imageObj = document.getElementById("bankCardBackPhotoImageDiv");  
  		 return previewImage(divObj,imageObj,obj);  
   	}

   	function showsettleIdCardImage(obj){  
   		 var divObj = document.getElementById("settleIdCardDiv");  
   		 var imageObj = document.getElementById("settleIdCardImageDiv");  
   		 return previewImage(divObj,imageObj,obj);  
   	}
   	
   	function exit() {
   		if (confirm("您确定要关闭吗？")) {
   			window.opener=null;
   		
   			window.open("","_self");
   		
   			window.close();
   		}
   	};
    	
    /* 校验渠道 */
    $(function(){
    	//判定是新进件还是更新进件
		var status = $("#status").val();
		var custId = $("#custId").val();
		var authId = $("#authId").val();
		if("" ==custId){
			alert(status);
		}else{
	    	$("#openPhotoImageDiv").show();
	    	$("#businessPhotoImageDiv").show();
	    	$("#certAttribute1ImageDiv").show();
	    	$("#certAttribute2ImageDiv").show();
	    	$("#legalCertAttribute1ImageDiv").show();
	    	$("#legalCertAttribute2ImageDiv").show();
	    	$("#bankCardBackPhotoDiv").show();
	    	$("#settleIdCardDiv").show();
	    	$("#doorPhotoImageDiv").show();
	    	$("#bankCardPhotoImageDiv").show();
	    	$("#cooperateImageDiv").show();
	    	
	    	$("#businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=02&authId="+authId);
	    	$("#certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
	    	$("#certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
	    	$("#doorPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=08&authId="+authId);
	    	$("#bankCardPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=05&authId="+authId);
	    	$("#openPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId);
	    	$("#legalCertAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
	    	$("#legalCertAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
	    	$("#bankCardBackPhotoDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=14&authId="+authId);
	    	$("#settleIdCardDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=13&authId="+authId);
	    	$("#cooperateImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=15&authId="+authId);
		} 
    	
		//照片
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
		             
		             var width = result.width;
		             var height = result.height;
		             
		             var scale =  width/height;
			     	 if(width >800){
			     		width = 800;
			     		height = width / scale;
			     	 }
		             $(".showDiv").width(width+"px");
		             $(".showDiv").height(height+"px");
				}
			});
		}
		) 
    	
   	});  
</script>
</body>
</html>