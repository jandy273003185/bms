<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.seven.micropay.channel.enums.BestBankCode"%>
<%@page import="com.seven.micropay.channel.enums.MerUpdateType"%>
<%@page import="com.seven.micropay.channel.enums.suixinpay.SuixinBankType"%>
<%@page import="com.qifenqian.bms.merchant.reported.MerchantReportedPath"%>
<%@page import="com.qifenqian.bms.basemanager.merchant.AuditorPath"%>
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
	<title>随行付更新进件</title>
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
					<input type="hidden" id="reportStatus" name="reportStatus" value="${reportStatus}"/>
					<input type="hidden" id="taskCode" name="taskCode" value ="${remark}"/>
					<input type="hidden" id="status" name="status" value="${status}"/>
					<input type="hidden" id="patchNo" name="patchNo" value="${patchNo}"/>
					<input type="hidden" id="channlCode" name="channlCode" value="SUIXING_PAY"/>
					<input type="hidden" id="custId" name="custId" value="${custInfo.custId }"/>
					<input type="hidden" id="authId" name="authId" value="${custInfo.authId }"/>
					<input type="hidden" id="shopInteriortemp" />
					<input type="hidden" id="businessPhototemp"/>
					<input type="hidden" id="openPhototemp"/>
					<input type="hidden" id="certAttribute1temp" />
					<input type="hidden" id="certAttribute2temp" />
					<input type="hidden" id="legalCertAttribute1temp" />
					<input type="hidden" id="legalCertAttribute2temp" />
					<input type="hidden" id="settleCertAttribute1temp" />
					<input type="hidden" id="settleCertAttribute2temp" />
					<input type="hidden" id="bankCardPhototemp"/>
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
									<input type="text" id="merchantCode" name="merchantCode" placeholder="请输入商户编号" maxlength=""  value="${custInfo.merchantCode }" style="width:90%">
								</td>
                                <td class="td-left" width="18%">商户简称：</td>
								<td class="td-right" width="32%"> 
									<input type="text" id="custName" name="custName" placeholder="请输入商户简称" maxlength=""  value="${custInfo.custName }" style="width:90%">
								</td>
							</tr>
	                        <tr>
								<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
							</tr>
							<tr>
							    <td class="td-left">商户类型：</td>
								<td class="td-right"> 
								   <select  name="mecTypeFlag" id="mecTypeFlag"  class="width-90" >
										<option value="00">--普通商户--</option>
										<option value="01">--连锁总店--</option>
										<option value="02">--连锁分店--</option>
										<option value="03">--1+n总店--</option>
										<option value="04">--1+n总店--</option>
									</select>
									<label class="label-tips" id="mecTypeFlagLab"></label>	
								</td>
                                <td class="td-left">商户行业信息：</td>
								<td class="td-right"> 
								   <select name="industry" id="industry" class="width-90">
										<option value="">--请选择商户行业--</option>
											<c:if test="${not empty industryList }">
					                           	<c:forEach items="${industryList }" var="industry">
					                           		<option id="${industry.industryCode}" value="${industry.industryCode}">${industry.industryName}</option>
					                           	</c:forEach>
				                 		  	</c:if>
									</select>	
								</td>
							</tr>
	                        <tr>
							    <td class="td-left">是否有证商户：</td>
								<td class="td-right"> 
								    <select  name="suiXingMerchantType" id="suiXingMerchantType" onchange = "getUpdateType();"class="width-90">
										<option value="01">有证商户</option>
										<option value="02">无证商户</option>
									</select>
									<label class="label-tips" id="suiXingMerchantLab"></label>
								</td>
							</tr>
							<tr>
								<td class="td-left">注册地址：</td>
								<td class="td-right" colspan="3">
									<div class="col-xs-2 pd0" style="padding:0;">
	                                    <select class="form-control" name="merchantProvince" id="merchantProvince" onchange="getMerchantCity();">
	                                       	<option value="">--请选择省--</option>
		                                    <c:if test="${not empty merchantProvinceList }">
		                                        <c:forEach items="${merchantProvinceList }" var="merchantProvince">
		                                            <option id="${merchantProvince.areaId}"
		                                                    value="${merchantProvince.areaId}">
		                                                ${merchantProvince.areaName}
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
	                                    <input type="text" name="cprRegAddr" id="cprRegAddr"  placeholder="详细地址"  value="${custInfo.custAdd }" style="width:100%">
	                                </div>
	                                <label class="label-tips" id="countryLab"></label>
								</td>
							</tr>	
						 	<%-- <tr>
								<td class="td-left">营业执照编号：</td>
								<td class="td-right">
									<input type="text" name="cprRegNmCn" id="cprRegNmCn"  placeholder="请输入营业执照"  value="${custInfo.businessLicense }" style="width:90%">
								</td>
								<td class="td-left">营业执照有限期：</td>
								<td class="td-right">
									<input type="text" name="" id="" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> -
                                    <input type="text" name="" id="" style="background:#fff url(/static/My97DatePicker/skin/datePicker.gif) no-repeat right!important;"> <input name="" type="radio" value=""> 长期
								</td>
							</tr> --%>
							<tr id="businessPhotoType" style = "display:">
								
								<td class="td-left">营业执照照片：</td>
								<td class="td-right" colspan="3">
									<a data-toggle="modal" class="tooltip-success businessPhotoClick" data-target="#previewImageModal">
										<label id="businessPhotoDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img id="businessPhotoImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;">										  
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
									<a data-toggle="modal" class="tooltip-success doorPhotoClick" data-target="#previewImageModal">
										<label id="doorPhotoDiv" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px;margin: 10 10 10 10">
										  <img id="doorPhotoImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;">										  
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input type="hidden" id="doorPhotoImageVal02">  
										<input type="file" name="doorPhoto" id="doorPhoto" onChange="showDoorPhotoImage(this)">
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
									<input type="text" id="representativeName" name="representativeName" placeholder="请输入法人真实姓名"  value="${custInfo.representativeName }" maxlength="" style="width:90%">
								</td>
								<td class="td-left">手机号码：</td>
								<td class="td-right"> 
									<input type="text" name="mobileNo" id="mobileNo" placeholder="请输入手机号码"  value="${custInfo.mobile }" style="width:90%">
								</td>
							</tr>
                            <tr>
								<td class="td-left">法人证件类型：</td>
								<td class="td-right"> 
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
								</td>
								<td class="td-left">法人身份证号码：</td>
								<td class="td-right"> 
									<input type="text" name="representativeCertNo" id="representativeCertNo" placeholder="请输入法人身份证号码"  value="${custInfo.representativeCertNo }" style="width:90%">
								</td>
							</tr>
							<tr id="legalIdCardType" style = "display:">
								<td class="td-left">法人身份证正面：</td>
								<td class="td-right" colspan="3">
									<a data-toggle="modal" class="tooltip-success legalCertAttribute1Click" data-target="#previewImageModal">
									<label id="legalCertAttribute1Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
									        <img id="legalCertAttribute1ImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;">
									</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input type="hidden" id="legalCertAttribute1Val02">  
										<input type="file" name="legalCertAttribute1" id="legalCertAttribute1" onChange="showlegalCertAttribute1Image(this)"> 
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
									
								</td>
							</tr>
							<tr id="legalIdCardBackType" style = "display:">
								<td class="td-left">法人身份证背面：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle="modal" class="tooltip-success legalCertAttribute2Click" data-target="#previewImageModal">
										<label id="legalCertAttribute2Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="legalCertAttribute2ImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;">
										</label>
									</a>
									<div class="updateImageDiv" style="float: left; margin-top: 75px; display: block;">
										<input type="hidden" id="legalCertAttribute2Val02">  
										<input type="file" name="legalCertAttribute2" id="legalCertAttribute2" onChange="showlegalCertAttribute2Image(this)"> 
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
									<input type="text" id="actNm" name="actNm" maxlength="100" placeholder="请输入结算账户名称"  value="" style="width:90%">
								</td>
								<td class="td-left">银行卡号：</td>
								<td class="td-right"> 
									<input type="text" id="bankCardNo" name="bankCardNo" maxlength="100" placeholder="请输入银行卡号"  value="${custInfo.compMainAcct }" style="width:90%">
								</td>
							</tr>
							<tr>
								<td class="td-left">开户银行：</td>
								<td class="td-right"> 
									<select name="suiXinBank" id="suiXinBank" style="width-90;">
										<option value="">--请选择--</option>
										<c:forEach items="<%=SuixinBankType.values()%>" var="status">
											<option value="${status}" <c:if test="${status == queryBean.code}">selected</c:if>>
												${status.name}
											</option>
										</c:forEach>
									</select>
								</td>
	                            <td class="td-left">开户支行</td>
								<td class="td-right">
									<input type="text" id="interBankName" name="interBankName" maxlength="100" placeholder="请输入开户支行"  value="${custInfo.branchBANK }" style="width:90%">
									<label class="label-tips" id="interBankNameLabel"></label>
								</td>
							</tr>
                            <tr>
								<td class="td-left">开户省份：</td>
								<td class="td-right"> 
									<select name="bankProvince" id="bankProvince" class="width-90" onchange="getCity();">
	                                    <option value="">--请选择省--</option>
	                                    <c:if test="${not empty provinceList }">
	                                        <c:forEach items="${provinceList }" var="province">
	                                            <option id="${province.provinceId}"
	                                                    value="${province.provinceId}">
	                                                ${province.provinceName}
	                                            </option>
	                                        </c:forEach>
	                                    </c:if>
	                                </select>
	                                <label id="bankProvinceLabel" class="label-tips"></label>
								</td>
								<td class="td-left">开户城市：</td>
								<td class="td-right"> 
									<select name="bankCity" id="bankCity" class="width-90" >
		                                    <option value="">--请选择市--</option>
		                                </select>
		                               	<label id="bankCityLabel" class="label-tips"></label>
								</td>
							</tr>
                            <tr>
								<td class="td-left">结算类型：</td>
								<td class="td-right">
									<select name="actType" id="actType" style="width-90" onchange = "getUpdateType1();" >
										<option value="">--请选择--</option>
										<option value="00">--对公--</option>
										<option value="01">--对私--</option>
									</select> 
								</td>
                                <td class="td-left">结算费率：</td>
							  <td class="td-right"> 
									<input type="text" id="rate" name="rate" placeholder="请输入结算费率"  value="0.38" style="width:90%"> %
							  </td>
							</tr>
                            <tr>
                                <td class="td-left">账户人身份证号码：</td>
							  	<td class="td-right"> 
									<input type="text" id="certifyNo" name="v" placeholder="请输入账户人身份证号码"  value="" style="width:90%">
								</td>
							</tr>
							<tr id="openPhotoType" style = "display:">
								<td class="td-left">开户许可证：</td>
								<td class="td-right" colspan="3"> 
									<a data-toggle="modal" class="tooltip-success certAttribute0Click" data-target="#previewImageModal">
										<label id="certAttribute0Div" style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img id="certAttribute0ImageDiv" onClick="bigImg(this);" style="width: 100%; height: 100%;">
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
							<tr id="settleIdCardType" style = "display:">
				                <td class="td-left"> 结算人身份证正面 </td>
		                    	<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success settleCertAttribute1Click"  data-target="#previewImageModal"  >
										<label id="settleCertAttribute1Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="settleCertAttribute1ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="settleCertAttribute1Val02"  />   
										<input type="file" name="settleCertAttribute1" id="settleCertAttribute1" onchange="showSettleCertAttribute1Image(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
					         </tr>
					         <tr id="settleIdCardBackType" style = "display:">
				                <td class="td-left"> 结算人身份证反面 </td>
		                    	<td class="td-right" colspan="3">
									<a data-toggle='modal' class="tooltip-success settleCertAttribute2Click"  data-target="#previewImageModal"  >
										<label id="settleCertAttribute2Div"style="float:left;background-color:rgb(222, 222, 222); width:120px;height:100px; margin: 10 10 10 10">  
										        <img  id="settleCertAttribute2ImageDiv" onclick="bigImg(this);" style="width:100%;height:100%;display:none" />
										</label>
									</a>
									<div class="updateImageDiv" style="float:left; margin-top:75" >
										<input type="hidden" id="settleCertAttribute2Val02"  />  
										<input type="file" name="settleCertAttribute2" id="settleCertAttribute2" onchange="showSettleCertAttribute2Image(this)"/>
										<span style="color:gray">支持*jpg、*jpeg、*gif、*bmp、*png图片格式</span>
									</div>
								</td>
							 </tr>					           
						</tbody>
						</table>
					    </form>
					    </div>
					    <div style="margin:50px 0 0 0;text-align:center">
					    	<button type="button"  class="btn btn-primary" id='submitData'>提交报备</button> 
					    	<button type="button"  class="btn btn-default" onclick="exit()">关闭</button> 
					    </div>
					    
					    <!-- 
					    <div class="aui-btn-default">
							<button class="aui-btn aui-btn-account" data-toggle='modal' id='submitFile' data-target="#submitModal" style = "display:">
								上传文件
							</button>
							<button class="aui-btn aui-btn-account" data-toggle='modal' id='submitData' data-target="#submitModal" style = "display:none">
								提交审核
							</button>
					    </div> -->
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
        
      	/***获取银行所在地区***/
      	function getCity(){
      		var province = $("#bankProvince").val().trim();
      		
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGCITY %>",
    		{
    			"province":province
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var cityList = data.cityList;
    				$("#bankCity").html("");
           			for ( var city in cityList) {
           				$("#bankCity").append(
           						"<option value='"+ cityList[city].cityId +"'>"
           								+ cityList[city].cityName + "</option>"); 
           			}
    			}else{
    				alert("城市不能为空");
    			}
    		},'json'
    		);	
      	}

      
      	/***获取商户注册所在地区城市***/
      	function getMerchantCity(){
      		var merchantProvinceId = $("#merchantProvince").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGAREA %>",
      	    		{
      	    			"superiorAreaCode":merchantProvinceId
      	    		},
      	    		function(data){
      	    			if(data.result=="SUCCESS"){
      	    				var areaList = data.areaList;
      	    				$("#merchantCity").html("");
      	           			for ( var area in areaList) {
      	           				$("#merchantCity").append(
      	           						"<option value='"+ areaList[area].areaId +"'>"
      	           								+ areaList[area].areaName + "</option>"); 
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
      		var merchantCityId = $("#merchantCity").val().trim();
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGAREA %>",
      	    		{
      	    			"superiorAreaCode":merchantCityId
      	    		},
      	    		function(data){
      	    			if(data.result=="SUCCESS"){
      	    				var areaList = data.areaList;
      	    				$("#merchantArea").html("");
      	           			for ( var area in areaList) {
      	           				$("#merchantArea").append(
      	           						"<option value='"+ areaList[area].areaId +"'>"
      	           								+ areaList[area].areaName + "</option>"); 
      	           			}
      	    			}else{
      	    				alert("市不能为空");
      	    			}
      	    		},'json'
      	    		);	
        }
      	
      	/***获取开户支行***/
      	function getInterBank(){
      		var bank = $("#interBank").val().trim();
      		
      		$.post(window.Constants.ContextPath +"<%=MerchantReportedPath.BASE + MerchantReportedPath.BMSSELINTERBANK %>",
    		{
    			"bank":bank
    		},
    		function(data){
    			if(data.result=="SUCCESS"){
    				var interBankList = data.interBankList;
    				$("#interBank_").html("");
    				$("#interBank_").append("<option value=''>--请选择--</option>");
           			for ( var interBank in interBankList) {
           				$("#interBank_").append(
           						"<option value='"+ interBankList[interBank].bankBranchId +"'>"
           								+ interBankList[interBank].bankBranchName + "</option>");
           			}
    			}
    		},'json'
    		);	
      	}

      	/***获取开户支行***/
      	function setInterBank(){
      		var interBankName = $("#interBank_").find("option:selected").text().trim();
      		var interBank = $("#interBank_").val();
      		$("#InterBankCode").val(interBank);
      		$("#interBankName").val(interBankName);
      	}

      	/** 照片点击预览 **/
      	$('.shopInteriorClick').click(function(){
      		var divObj = document.getElementById("showImageDiv");
      		var imageObj = document.getElementById("showImage");
      		var obj = document.getElementById("shopInteriorClick");
      		return previewImage(divObj,imageObj,obj); 
      	});

      	function getUpdateType(){
			var suiXingMerchantType = $("#suiXingMerchantType").val();
			
			//有证商户
			if("01" == suiXingMerchantType){
				$("#bankCardPhotoType").attr("style","display:");
				$("#idCardType").attr("style","display:none");
				$("#idCardBackType").attr("style","display:none");
				$("#businessPhotoType").attr("style","display:");
				$("#openPhotoType").attr("style","display:");
				$("#legalIdCardType").attr("style","display:");
				$("#legalIdCardBackType").attr("style","display:");
				$("#settleIdCardType").attr("style","display:");
				$("#settleIdCardBackType").attr("style","display:");
				
			}else if("02" == suiXingMerchantType){
				$("#legalIdCardType").attr("style","display:none");
				$("#legalIdCardBackType").attr("style","display:none");
				$("#settleIdCardType").attr("style","display:");
				$("#settleIdCardBackType").attr("style","display:");
				$("#businessPhotoType").attr("style","display:none");
				$("#openPhotoType").attr("style","display:none");
				$("#bankCardPhotoType").attr("style","display:");
				$("#idCardType").attr("style","display:none");
				$("#idCardBackType").attr("style","display:none");
				
			}
			
      	}

      	function getUpdateType1(){
      		var actType = $("#actType").val();
      		//对公
			if("00" == actType){
				$("#legalIdCardType").attr("style","display:");
				$("#legalIdCardBackType").attr("style","display:");
				$("#settleIdCardType").attr("style","display:none");
				$("#settleIdCardBackType").attr("style","display:none");
				$("#openPhotoType").attr("style","display:");
				$("#bankCardPhotoType").attr("style","display:none");
				
			}else if("01" == actType){
				$("#legalIdCardType").attr("style","display:none");
				$("#legalIdCardBackType").attr("style","display:none");
				$("#settleIdCardType").attr("style","display:");
				$("#settleIdCardBackType").attr("style","display:");
				$("#openPhotoType").attr("style","display:none");
				$("#bankCardPhotoType").attr("style","display:");
				
			}

      	}

        //图片预览
        function shopInteriorImage(obj){  
       		var divObj = document.getElementById("shopInteriorDiv");  
       		var imageObj = document.getElementById("shopInteriorImageDiv");  
       		return previewImage(divObj,imageObj,obj);  
        }
        
    	function showBusinessPhotoImage(obj){  
    		 var divObj = document.getElementById("businessPhotoDiv");  
    		 var imageObj = document.getElementById("businessPhotoImageDiv");  
    		 return previewImage(divObj,imageObj,obj);  
    	}

    	function showCertAttribute0Image(obj){  
   		 var divObj = document.getElementById("certAttribute0Div");  
   		 var imageObj = document.getElementById("certAttribute0ImageDiv");  
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
    	function showSettleCertAttribute1Image(obj){  
   		 var divObj = document.getElementById("settleCertAttribute1Div");  
   		 var imageObj = document.getElementById("settleCertAttribute1ImageDiv");  
   		 return previewImage(divObj,imageObj,obj);  
	   	}
	
	   	function showSettleCertAttribute2Image(obj){  
	   		 var divObj = document.getElementById("settleCertAttribute1Div");  
	   		 var imageObj = document.getElementById("settleCertAttribute2ImageDiv");  
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
	    	$("#settleCertAttribute1Div").show();
	    	$("#settleCertAttribute2Div").show();
	    	$("#doorPhotoImageDiv").show();
	    	$("#bankCardPhotoImageDiv").show();
	    	$("#businessPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=02&authId="+authId);
	    	$("#certAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
	    	$("#certAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
	    	$("#doorPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=08&authId="+authId);
	    	$("#bankCardPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=05&authId="+authId);
	    	$("#openPhotoImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=03&authId="+authId);
	    	$("#legalCertAttribute1ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
	    	$("#legalCertAttribute2ImageDiv").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
	    	$("#settleCertAttribute1Div").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=0&authId="+authId);
	    	$("#settleCertAttribute2Div").attr("src","<%=request.getContextPath()+AuditorPath.BASE+ AuditorPath.IMAGE %>?custId="+custId+"&certifyType=04&front=1&authId="+authId);
		}  
    	$("#submitData").click(function(){
        	
   			//渠道
   			var channelNo = $("#channlCode").val();
			//商户号
   			var merchantCode = $("#merchantCode").val();
			
			//客户简称
   			var custName = $("#custName").val();
   			//手机号
   			var mobileNo = $("#mobileNo").val();
   			//（资质类型）
   			var suiXingMerchantType = $("#suiXingMerchantType").val();
   			//mecTypeFlag（商户类型）
   			var mecTypeFlag = $("#mecTypeFlag").val();
   			//cprRegNmCn(注册名)
   			var cprRegNmCn =$("#cprRegNmCn").val(); 
   			//registCode（注册号）
			var registCode = $("#registCode").val();
   			// merchantProvince（注册地区）
   			var merchantProvince = $("#merchantProvince").val();
   			// merchantCity
   			var merchantCity = $("#merchantCity").val();
   			// merchantArea
   			var merchantArea =$("#merchantArea").val();
   			// cprRegAddr（商户详细地址）
   			var cprRegAddr = $("#cprRegAddr").val();
   			//industry（行业信息）
   			var industryCode = $("#industry").val();
   			// representativeName（法人姓名）
   			var representativeName = $("#representativeName").val();
   			// representativeCertType（法人证件类型）
   			var representativeCertType = $("#representativeCertType").val();
   			// representativeCertNo（法人证件号）
   			var representativeCertNo = $("#representativeCertNo").val();
   			//actNm（结算名）
   			var actNm = $("#actNm").val();
   			//actType（结算类型）
   			var actType = $("#actType").val();
   			//certifyNo（账户身份证号）
   			var certifyNo = $("#certifyNo").val();
   			// bankCardNo（结算银行卡号）
   			var bankCardNo = $("#bankCardNo").val();
   			//suiXinBank（开户行）
   			var suiXinBank = $("#suiXinBank").val();
   			// bankProvince（开户行所在地区）
   			var bankProvince = $("#bankProvince").val();
   			// bankCity
   			var bankCity = $("#bankCity").val()
   			// interBankName(开户支行名称)
   			var interBankName = $("#interBankName").val();
   			//rate
   			var rate = $("#rate").val();
			var patchNo = $("#patchNo").val();
   			var reportStatus = $("#reportStatus");
   			var taskCode = $("#taskCode").val();
   			if("" == suiXingMerchantType){
   	    		$("#suiXingMerchantLab").text("资质类型不能为空");
   	    		return false;
   	    	}else{
   	    		$("#suiXingMerchantLab").text('');
   	    	}
   	    	
   			//上传照片
			var imgDoor = [];
			var imgSrc = [];
			var merchantData = new FormData(document.getElementById('merchantForm'));	
			<%-- var mer_upload = document.getElementById('merchantForm');
			mer_upload.action=window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGFILEUPLOAD %>?merchantCode='+merchantCode;
			mer_upload.submit(); --%>
			/* $("#door_temp input[type='hidden']").each(function(){
				if($(this).attr('id').indexOf('Src')>=0){
					imgSrc.push($(this).attr('id')+"="+$(this).val());
				}else{
					if($(this).val()==""){
						imgDoor.push($(this).attr('id')+"="+"无");
					}else{
						imgDoor.push($(this).attr('id')+"="+$(this).val());
					}
				}
			});  */
			$.ajax({
				type : "POST",
				url : window.Constants.ContextPath +'<%=MerchantReportedPath.BASE + MerchantReportedPath.SELSUIXINGFILEUPLOAD %>?patchNo='+patchNo+'&status='+status,
				data :merchantData,
                dataType : "json",
                cache: false,
                processData: false,
                contentType: false,
				success : function(data){
				if(data.result=="SUCCESS"){
					if("" != data.message){
						taskCode = $("#taskCode").val(data.message);
						alert("更新进件成功");
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
					}else{
						alert("上传图片返回码异常");
					}
					
				}else{
					if("" == data.message || null == data.message){
						alert("失败");
					}else{
						alert(data.message);
					}
				}
			},
			error : function(){
				alert("上传失败");
			},
			})
    		
    	});

    	
   	});  
</script>
</body>
</html>