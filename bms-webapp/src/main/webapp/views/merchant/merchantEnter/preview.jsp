<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@ page import="java.util.ResourceBundle"%>
<%ResourceBundle res = ResourceBundle.getBundle("uploadFileConfig"); %>
<%@page import="com.sevenpay.bms.basemanager.merchant.MerchantPath" %>
<%@page import="com.sevenpay.bms.basemanager.merchant.MerchantEnterPath" %>
<%@page import="com.sevenpay.bms.basemanager.merchant.StoreManagePath" %>
<%@page import="com.sevenpay.bms.basemanager.merchant.AuditorPath"%>
<%@page import="com.sevenpay.bms.basemanager.merchant.TinyMerchantPath" %>
<%@page import="com.sevenpay.bms.basemanager.agency.controller.AgentRegisterPath" %>
<script src='<c:url value="/static/js/ajaxfileupload.js"/>'></script>
<script src='<c:url value="/static/js/comm.js"/>'></script>
<script src='<c:url value="/static/js/upload.js"/>'></script>
<script src='<c:url value="/static/js/mobileBUGFix.mini.js"/>'></script>
<script src='<c:url value="/static/js/uploadCompress.js"/>'></script>
<script src='<c:url value="/static/js/jquery.qrcode.min.js"/>'></script>
<script src='<c:url value="/static/My97DatePicker/WdatePicker.js"/>'></script>
<script src='<c:url value="/static/js/jquery-ui.min.js"/>'></script>
<script src="<c:url value='/static/js/jquery.combo.select.js'/>"></script>
<html>
<head>
	<meta charset="utf-8" />
	<title>商户录入预览</title>
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

	var custId = $("#custId").val();
	var authId = $("#authId").val();

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


	var cust_url = $("#url").val();
	qrcode(cust_url);
	function qrcode(url){
		$("#code_2").html("");
		$("#code_2").qrcode(url);
		/* $("#code_3").qrcode(url); */
		var mycanvas = $("#code_1").find("canvas")[0];
		var image = mycanvas.toDataURL("image/png");
		$("#code_1").html("<img id='qr_img' download='' src='"+image+"' width='100' height='100'  alt='from canvas'/>");
	}

	function preview(file)
	{
		var prevDiv = document.getElementById('img-' + file.id);
		if (file.files && file.files[0]){
			var reader = new FileReader();
			reader.onload = function(evt){
				prevDiv.innerHTML = '<img  style="width:50%;height:50%;" src="' + evt.target.result + '"   />';
			};
			reader.readAsDataURL(file.files[0]);
		} else{
			prevDiv.innerHTML = '<div style="width:50%;height:50%;" class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\''
					+ file.value + '\'"></div>';
		}
	}
	function check(obj){
		var maxsize = '<%=res.getString("CERTIFY_FILE_MAX_SIZE")%>';
		var fileType = '<%=res.getString("CERTIFY_SUPPORT_FILE_TYPE")%>';
		if(!checkFileSize(maxsize,fileType,obj)){
			var prevDiv = document.getElementById('img-' + obj.id);
			$(prevDiv).html('<a href="javascript:;" >点击上传</a>');
			obj.value='';
			return false;
		}
		preview(obj);
	}
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
						<input type="hidden" id="bankCardPhototemp" />
						<input type="hidden" id="certAttribute1temp">
						<input type="hidden" id="certAttribute2temp">
						<input type="hidden" id="openAccounttemp" />
						<input type="hidden" name="custId" id="custId" value="${merchantVo.custId}">
						<input type="hidden" name="merchantCode" id="merchantCode" value="${merchantVo.merchantCode}">
						<input type="hidden" name="authId" id="authId" value="${merchantVo.authId}">
						<input type="hidden" name="custType" id="custType" value="${merchantVo.custType}">
                    <table id="merchant_table" class="list-table">
					<tbody>
                        <tr>
						    <td colspan="4" class="headlerPreview" style="background:#7ebde1;">商户信息</td>
						</tr>
						<tr></tr>
                        <tr>
						    <td class="td-left">商户账号：</td>
						    <td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.merchantCode }</td>
						</tr>
                        <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1">基本信息</td>
						</tr>
						<tr>
							<td class="td-left" style="color:#666;padding:10px 8px">商户类型：</td>
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
						</tr>
                        <tr>
						    <td class="td-left"  width="18%">商户名称：</td>
							<td class="td-right"  width="32%" style="color:#666;padding:10px 8px">${merchantVo.custName }</td>
							<td class="td-left"  width="18%">商户简称：</td>
							<td class="td-right"  width="32%" style="color:#666;padding:10px 8px">${merchantVo.shortName }</td>
						</tr>
						<tr>
							<td class="td-left">商户邮箱：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.merchantEmail }</td>
							<td class="td-left">客服号码：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.contactPhone }</td>
						</tr>
					    <tr>
							<td class="td-left">商户地址：</td>
							<td class="td-right"  style="color:#666;padding:10px 8px">${merchantVo.custAdd }</td>
						</tr>	
						<tr>
							<td class="td-left">营业执照编号：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.businessLicense }</td>
							<td class="td-left">营业执照有限期：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.businessTermStart } -
								<c:choose>   
			                        <c:when test="${merchantVo.businessTermEnd =='forever'}">  									  
			                            	长期  								  
			                        </c:when>  
			  						<c:otherwise>  								  
			                            ${merchantVo.businessTermEnd }  									  
			                        </c:otherwise>
	                             </c:choose>
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
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.custManager }</td>
							<td class="td-left">所属代理商：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.agentName }</td>
						</tr>
                        <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">法人信息</td></tr>
						<tr>
						</tr>
						<tr>
							<td class="td-left">法人真实姓名：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.representativeName }</td>
							<td class="td-left">法人身份证号码：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.representativeCertNo }</td>
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
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.contactName }</td>
							<td class="td-left">联系人手机号码：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.contactMobile }</td>
						</tr>
						<tr id="next_id">
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">结算信息</td>
						</tr>
						<tr>
							<td class="td-left">银行卡号</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.compMainAcct }</td>
							<td class="td-left">银行类型：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.compAcctBank }</td>
						 </tr>
						 <tr>
							<td class="td-left">开户行：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.branchBank }</td>
							<td class="td-left">开户人：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.bankAcctName }</td>
						 </tr>
                         <tr>
							<td class="td-left">开户省份：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.bankProvinceName }</td>
							<td class="td-left">开户城市：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.bankCityName }</td>
						 </tr>
                         <tr>
							<td class="td-left">网点号：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.cnaps }</td>
							<td class="td-left">结算类型：</td>
							<td class="td-right" style="color:#666;padding:10px 8px">${merchantVo.compMainAcctType }</td>
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
                         <tr>
							<td colspan="4" class="headlerPreview" style="background:#7ebde1;">操作记录</td>
						 </tr>
						 <tr></tr>
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
							<td class="td-right" style="color:#666;padding:10px 8px">
								<fmt:formatDate value="${merchantVo.modifyTime }" pattern="yyyy-MM-dd HH:mm"/>
							</td>
						 </tr>
                         <tr>
							<td class="td-left">审核记录：</td>
							<td class="td-right" style="color:#666;padding:10px 8px"></td>
						 </tr>
                         <tr>
                         	<td colspan="4" class="headlerPreview" style="background:#7ebde1;">付款二维码</td>
						 </tr>
						 <tr>
						 	<td colspan="4">
						 		<div style="display: none; width: 10px;height: 10px;" id="code_1" >
								</div>
							 	<div style="width:300px;height:300px; margin: 10 auto;" id="code_2" >
									<input type="file" name="url" id="url" value=${qrCode }/>
									<img id="showNewRecode" alt="" src="" style="text-align:center;">
							 	</div>
						 	</td>
						 </tr>
					     <tr></tr>
					</tbody>
					</table>
                        <div style="margin:50px 0 0 0;text-align:center">
                        	<button type="button"  class="btn btn-primary" onclick="exit()">关闭</button> 
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

	/********************图片预览***********************/
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


	function exit() {
		if (confirm("您确定要关闭吗？")) {
			window.opener=null;

			window.open("","_self");

			window.close();
		}
	};
</script>
</html>	