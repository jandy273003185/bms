<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/template.jsp"%>
<%@page import="com.qifenqian.bms.merchant.product.MerchantProductPath"%>
<%@page import="com.qifenqian.bms.merchant.reported.enums.ChannelCodeType"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>商户分账管理</title>
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


	//新增
	$('.addMerchantSubAccountBtn').click(function(){
		var custId = $('#addMerchantSubAccountModal #custId').val();
	})
</script>	

<body>
	<!-- 商户分账信息 -->
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
							<form action='<c:url value="/merchant/subAccount/list"/>' method="post" id="form">
							<table class="search-table">
								
								<tr>
									<td class="td-left" >客户号：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="custId"  id="custId"  value="${merchantSubAccouontBean.custId }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >外部商户号：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="outMerchantCode"  id="outMerchantCode"  value="${merchantSubAccouontBean.outMerchantCode }"><i class="icon-leaf blue"></i>
										</span>
									</td>
									<td class="td-left" >分账方类型：</td>
									<td class="td-right" >
										<span class="input-icon">
											<input type="text" name="subAccountType"  id="subAccountType"  value="${merchantSubAccouontBean.subAccountType }"><i class="icon-leaf blue"></i>
										</span>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<span class="input-group-btn">																					
										
											<gyzb-admin:function url="/merchant/subAccount/list">
												<button type="submit" class="btn btn-purple btn-sm btn-margin" onclick="$.blockUI();">
													查询
													<i class="icon-search icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
												<button type="button" class="btn btn-purple btn-sm btn-margin clearBtn" >
													清空
													<i class=" icon-move icon-on-right bigger-110"></i>
												</button>
											<gyzb-admin:function url="/merchant/subAccount/add">
												<button class="btn btn-purple btn-sm btn-margin addMerchantSubAccount" data-toggle='modal' data-target="#addMerchantSubAccountModal">
													新增
													<i class="icon-plus-sign icon-on-right bigger-110"></i>
												</button>
											</gyzb-admin:function>
										</span>
									</td>
								</tr>
							</table>
							</form>
							
							<div class="list-table-header">商户分账关系列表</div>
							<div class="table-responsive" >
								<table id="sample-table-2" class="list-table" >
									<thead>
										<tr>
											<th>客户号</th>
											<th>外部商户号</th>
											<th>分账方渠道</th>
											<th>分账方类型</th>
											<th>分账方账号</th>
											<th>分账方全称</th>
											<th>与分账方的关系类型</th>
											<th>微信自定义关系</th>
											<th>外部请求号</th>
											<th>分账比例</th>
											<th>备注</th>
											<th>申请添加状态</th>
											<th>状态</th>
											<th>创建时间</th>
											<th>修改时间</th>											
											<th>操作</th>
										</tr>
									</thead>

									<tbody>

										<c:forEach items="${merchantSubAccouontList}" var="merchantSubAccouont">
											<tr class="merchantSubAccouont">
												<td>${merchantSubAccouont.custId}</td>
												<td>${merchantSubAccouont.outMerchantCode}</td>
												<td>${merchantSubAccouont.channelCode}</td>
												<td>${merchantSubAccouont.subAccountType}</td>																				
												<td>${merchantSubAccouont.account}</td>										
												<td>${merchantSubAccouont.accountName}</td>
												<td>${merchantSubAccouont.relationType}</td>
												<td>${merchantSubAccouont.customRelation}</td>											
												<td>${merchantSubAccouont.outRequestNo}</td>
												<td>${merchantProduct.rate}</td>	
												<td>${merchantProduct.remark}</td>
												<td>${merchantProduct.reportStatus}</td>
												<td>${merchantProduct.status}</td>	
												<td>
												<fmt:formatDate value="${merchantProduct.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td>
												<fmt:formatDate value="${merchantProduct.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>									
												<td>
													<%-- <input type="hidden" name="id" value="${materiel.id }"/>
													<c:if test="${merchantProduct.productStatus !='00'}">
														<gyzbadmin:function url="<%=MerchantProductPath.BASE + MerchantProductPath.EDIT %>">
														<a href="#" class="tooltip-success editMerchantProduct" data-rel="tooltip" data-toggle='modal' data-target="#editMerchantProductModal" title="Edit" >
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
														</gyzbadmin:function>
													</c:if>
													
													<c:if test="${merchantProduct.productStatus !='00'}">
														<a href="#" class="tooltip-success auditMerchantProduct" data-rel="tooltip" data-toggle='modal' data-target="#auditMerchantProductModal" title="Audit" >
															<span class="green">
																<i class="icon-check bigger-120"></i>
															</span>
														</a>
													</c:if>
													
													<gyzbadmin:function url="<%=MerchantProductPath.BASE + MerchantProductPath.DELETE %>">
													<a href="#" class="tooltip-success deleteMerchantProduct" data-rel="tooltip" data-toggle='modal' data-target="#deleteMerchantProductModal" title="Delete" >
														<span class="red">
															<i class="icon-trash bigger-120"></i>
														</span>
													</a>													
													</gyzbadmin:function> --%>
												</td>
											</tr>
										</c:forEach>

										<c:if test="${empty merchantSubAccouontList}">
											<tr><td colspan="16" align="center"><font style="color: red; font-weight: bold;font-size: 15px;">暂无数据</font></td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
							<!-- 分页 -->
							<c:if test="${not empty merchantSubAccouontList}">
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
					<div class="modal fade" id="addMerchantSubAccountModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户分账关系新增</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>	
										<td class="td-left" width="30%">客户号<span style="color:red">*</span></td>
										<td class="td-right" width="70%">	
											<select id="custId" name="custId">
												<option value="">输入客户号查询</option>
												<c:forEach items="${tdMerchantDetailInfoList}" var="bean">
													<option value="${bean.custId}">${bean.custName}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>	
										<td class="td-left" width="30%">分账渠道<span style="color:red">*</span></td>
										<td class="td-right" width="70%">	
											<select name="channelCode" id="channelCode" style="width-90;" data-validation="notnull" data-errMsg="开户银行不能为空" >
												<option value="">--请选择--</option>
												<c:forEach items="<%=ChannelCodeType.values()%>" var="status">
													<option value="${status.code}" <c:if test="${status == queryBean.code}">selected</c:if>>
														${status.name}
													</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>	
										<td class="td-left" width="30%">分账方类型<span style="color:red">*</span></td>
										<td class="td-right" width="70%">	
											<input type="text" id="subAccountType" name="subAccountType" clasS="width-90"/> 
										</td>
									</tr>
									<tr>
										<td class="td-left" >分账方账号</td>
										<td class="td-right" >
											<input type="text" id="account" name="account" clasS="width-90"/> 										
								        </td>
									</tr>
									<tr>
										<td class="td-left" >分账方全称</td>
										<td class="td-right" >
											<input type="text" id="accountName" name="accountName" clasS="width-90"/> 										
								        </td>
									</tr>
									<tr>
										<td class="td-left" >与分账方的关系类型</td>
										<td class="td-right" >
											<input type="text" id="relationType" name="relationType" clasS="width-90"/> 										
								        </td>
									</tr>
									<tr>
										<td class="td-left" >微信自定义关系</td>
										<td class="td-right" >
											<input type="text" id="customRelation" name="customRelation" clasS="width-90"/> 										
								        </td>
									</tr>
									<tr>
										<td class="td-left" >外部请求号</td>
										<td class="td-right" >
											<input type="text" id="outRequestNo" name="outRequestNo" clasS="width-90"/> 										
								        </td>
									</tr>
									<tr>
										<td class="td-left" >分账比例</td>
										<td class="td-right" >
											<input type="text" id="rate" name="rate" clasS="width-90"/> 										
								        </td>
									</tr>
									<tr>
										<td class="td-left" >备注</td>
										<td class="td-right" >
											<input type="text" id="remark" name="remark" clasS="width-90"/> 										
								        </td>
									</tr>
									
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary addMerchantSubAccountBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->			
			
						<div class="modal fade" id="auditMerchantProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户产品审核</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>	
										<td class="td-left" width="30%">商户名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="merchantCode" name="merchantCode" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
									
									<tr>	
										<td class="td-left" width="30%">产品名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="productId" name="productId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
																		
									<tr>
										<td class="td-left" >产品汇率</td>
										<td class="td-right" >
												<input type="text" id="productRate" readonly="readonly" name="productRate" clasS="width-90"/> 										
											
								        </td>
									</tr>
									
									<tr>
										<td class="td-left" >充值费率</td>
										<td class="td-right" >
												<input type="text" id="rechargeRate" readonly="readonly" name="rechargeRate" clasS="width-90"/>
										</td>
									</tr>
																													
									<!-- <tr>
										<td class="td-left" >渠道</td>
										<td class="td-right" >
												<input type="text" id="aisle" name="aisle" clasS="width-90"/>
										</td>
									</tr> -->	
				
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary auditMerchantProductBtn">审核通过</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div>
			
			<div class="modal fade" id="editMerchantProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户产品修改</h4>
					         </div>
					         <div class="modal-body">
					            <table class="modal-input-table">
					            	<tr>	
										<td class="td-left" width="30%">商户名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="merchantCode" name="merchantCode" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
									
									<tr>	
										<td class="td-left" width="30%">产品名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="productId" name="productId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
																		
									<tr>
										<td class="td-left" >产品汇率</td>
										<td class="td-right" >
												<input type="text" id="productRate" name="productRate" clasS="width-90"/> 										
											
								        </td>
									</tr>
									
									<tr>
										<td class="td-left" >充值费率</td>
										<td class="td-right" >
												<input type="text" id="rechargeRate" name="rechargeRate" clasS="width-90"/>
										</td>
									</tr>
																													
									<!-- <tr>
										<td class="td-left" >产品开通状态<span style="color: red">*</span></td>
										<td class="td-right">
												<select id="productStatus" name="productStatus" clasS="width-90"/>
													<option value="">选择产品开通状态</option>
													<option value="00">开通成功</option>
													<option value="09">开通失败</option>
													<option value="99">未开通</option>
												</select>
										</td>
									</tr>	 -->
									<!-- <tr>
										<td class="td-left" >渠道</td>
										<td class="td-right" >
												<input type="text" id="aisle" name="aisle" clasS="width-90"/>
										</td>
									</tr> -->	
				
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            <button type="button" class="btn btn-primary editMerchantProductBtn">提交</button>
					         </div>
					      </div><!-- /.modal-content -->
					     </div>
					</div><!-- /.modal -->		
						
					<div class="modal fade" id="deleteMerchantProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content" style="width: 600px;">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					            <h4 class="modal-title" id="myModalLabel">商户产品信息删除</h4>
					         </div>
					         <div class="modal-body" align="center">
					            <table class="modal-input-table">
					            	<tr>	
										<td class="td-left" width="30%">商户名称<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="merchantCode" name="merchantCode" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
									
									<tr>	
										<td class="td-left" width="30%">产品编号<span style="color:red">*</span></td>
										<td class="td-right" width="70%" >											
											<input type="text" id="productCode" name="productCode" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>
										</td>
									</tr>
																		
									<tr>
										<td class="td-left" >产品编号</td>
										<td class="td-right" >
											<input type="text" id="productId" name="productId" readonly="readonly" style="background-color: #EEEEEE;" clasS="width-90"/>											
								        </td>
									</tr>
							
					            </table>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					            <button type="button" class="btn btn-primary deleteMerchantProductBtn" >确定</button>
					         </div>
					      </div><!-- /.modal-content -->
					 </div>
				</div><!-- /.modal -->
				
				
										
			
				
  </body>
</html>