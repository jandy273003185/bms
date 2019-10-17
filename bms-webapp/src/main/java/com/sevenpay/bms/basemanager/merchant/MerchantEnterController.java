package com.sevenpay.bms.basemanager.merchant;

import com.alibaba.fastjson.JSONObject;
import com.sevenpay.bms.basemanager.bank.bean.Bank;
import com.sevenpay.bms.basemanager.bank.mapper.BankMapper;
import com.sevenpay.bms.basemanager.city.service.CityService;
import com.sevenpay.bms.basemanager.custInfo.bean.TdCustInfo;
import com.sevenpay.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.sevenpay.bms.basemanager.custInfo.service.TdCustInfoService;
import com.sevenpay.bms.basemanager.feerule.service.FeeRuleService;
import com.sevenpay.bms.basemanager.merchant.bean.*;
import com.sevenpay.bms.basemanager.merchant.mapper.ActWorkflowMerchantAuditMapper;
import com.sevenpay.bms.basemanager.merchant.mapper.CustScanMapper;
import com.sevenpay.bms.basemanager.merchant.mapper.MerchantMapper;
import com.sevenpay.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.sevenpay.bms.basemanager.merchant.service.AuditorService;
import com.sevenpay.bms.basemanager.merchant.service.MerchantEnterService;
import com.sevenpay.bms.basemanager.merchant.service.MerchantService;
import com.sevenpay.bms.basemanager.merchant.service.MerchantWorkFlowAuditService;
import com.sevenpay.bms.basemanager.rule.bean.Rule;
import com.sevenpay.bms.basemanager.rule.mapper.RuleMapper;
import com.sevenpay.bms.basemanager.trade.service.TradeBillService;
import com.sevenpay.bms.basemanager.utils.GenSN;
import com.sevenpay.bms.common.util.PropertiesUtil;
import com.sevenpay.bms.expresspay.CommonService;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.transaction.bindbankcard.BindBankCardResponse;
import com.sevenpay.plugin.message.bean.MessageColumnValues;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.gyzb.platform.web.admin.user.service.UserService;
import org.gyzb.platform.web.admin.utils.WebUtils;
import org.sevenpay.platform.utils.BusinessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping(MerchantEnterPath.BASE)
public class MerchantEnterController {
	
	
	private Logger logger = LoggerFactory.getLogger(MerchantEnterController.class);

	@Autowired
	private MerchantService merchantService;

	@Autowired
	private MerchantEnterService merchantEnterService;
	@Autowired
	private BankMapper bankMapper;
	@Autowired
	private RuleMapper ruleMapper;
	@Autowired
	private CityService cityService;
	@Autowired
	private FeeRuleService feeRuleService;
	@Autowired
	private AuditorService auditorService;
	@Autowired
	private UserService userService;
	@Autowired
	private TdLoginUserInfoMapper loginUserInfoMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private MerchantMapper merchantMapper;
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TdCustInfoService tdCustInfoService;

	@Autowired
	private TradeBillService tradeBillService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private ActWorkflowMerchantAuditMapper actWorkflowMerchantAuditMapper;

	@Autowired
	private MerchantWorkFlowAuditService merchantWorkFlowAuditService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private CustScanMapper custScanMapper;

	@Autowired
	private TdCustInfoMapper tdCustInfoMapper;

	/**
	 * 显示商户列表
	 * @param merchantVo
	 * @return
	 */
	@RequestMapping(MerchantEnterPath.LIST)
	public ModelAndView auditList(MerchantVo merchantVo){
		Bank bank = new Bank();
		Rule rule = new Rule();
		ModelAndView mv = new ModelAndView(MerchantEnterPath.BASE + MerchantEnterPath.LIST);
		List<MerchantVo> list = null;
		String userId  = String.valueOf(WebUtils.getUserInfo().getUserId());
		//是否有权限查看所有订单
		boolean isAllList = tdCustInfoService.isAllList(userId);
		if(isAllList){
			//list = merchantService.selectAuditMerchants(merchantVo);
			list = merchantEnterService.selectAuditMerchants(merchantVo);
		}else{
			merchantVo.setUserId(userId);
			merchantVo.setUserName(WebUtils.getUserInfo().getUserName());
			//list = merchantService.selectMyAuditMerchants(merchantVo);
			list = merchantEnterService.selectAuditMerchants(merchantVo);
		}
		mv.addObject("banklist", bankMapper.selectBanks(bank));
		mv.addObject("merchantList", JSONObject.toJSON(list));
		mv.addObject("rulelist", ruleMapper.selectRules02(rule));
		mv.addObject("provincelist", cityService.selectAllProvince());
		mv.addObject("queryBean", merchantVo);
		return mv;
	}

	/**
	 * 商户新增
	 *
	 * @return
	 */
	@RequestMapping(MerchantEnterPath.ADDPAGE)
	@ResponseBody
	public String regist(HttpServletRequest request, Merchant merchant) {

		logger.info("商户新增");

		//是否有权限新增
		String userId  = String.valueOf(WebUtils.getUserInfo().getUserId());
		boolean isAddMerchant = tdCustInfoService.isAddMerchant(userId);
        JSONObject object = new JSONObject();
		if (!isAddMerchant){
            logger.error("没有新增权限", userId);
            object.put("result", "fail");
            object.put("message", userId+"没有新增权限");
            return object.toJSONString();
		}

		try {
			// 邮箱
			//String email = request.getParameter("email");
			//String feeCode = request.getParameter("feeCode");

			// 设置商户custId
			String merchantCode = BusinessUtils.getMerchantId(merchant.getBusinessLicense());
			String custId = GenSN.getSN();

			merchant.setMerchantCode(merchantCode);
			merchant.setCustId(custId);
			// 任务ID
			String taskId = request.getParameter("taskId");
			this.saveMerchant(custId, merchant, null);
			// 流程到下一步
			String auditName = request.getParameter("auditName");
			merchantService.startProcess(custId, taskId, auditName);
			object.put("custId", custId);
			object.put("result", "SUCCESS");
			object.put("message", "新增成功");
		} catch (Exception e) {
			logger.error("新增失败", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}

		return object.toJSONString();
	}

    /**
     * 根据id查找商户信息 预览
      */
    @RequestMapping(MerchantEnterPath.PREVIEW)
    @ResponseBody
    public ModelAndView findByIdMerchantInfo(MerchantVo merchantVo) {
        logger.info("查找商户信息");
        JSONObject jsonObject = new JSONObject();
		ModelAndView mv = new ModelAndView(MerchantEnterPath.BASE + MerchantEnterPath.PREVIEW);
		MerchantVo merchant = merchantService.findMerchantInfo(merchantVo.getCustId());
        List<BmsProtocolContent> contents = merchantService.selectContentByCustId(merchantVo.getCustId());

        if (null != contents && contents.size() > 0) {

            jsonObject.put("bmsProtocolContent", contents.get(0));
        }
        //查询商户门头照信息
        //String path = auditorService.findScanPath(merchantVo.getCustId(), "08",merchantVo.getAuthId());
        //获取二维码
		String qrCode = getQrCode(merchantVo);
		mv.addObject("merchantVo", merchant);
		//预览返回的二维码信息
		mv.addObject("qrCode", qrCode);
		//mv.addObject("path", path);
		return mv;
    }

	/**
	 * 跟新
	 * @param merchantVo
	 * @param request
	 * @return
	 */
	@RequestMapping(MerchantEnterPath.UPDATE)
	@ResponseBody
	public String updateMerchantInfo(MerchantVo merchantVo,HttpServletRequest request) {
		logger.info("修改商户信息");
		JSONObject object = new JSONObject();
		String businessType = request.getParameter("businessType");
		String doorPhoto = request.getParameter("doorPhoto");
		String doorFlag = request.getParameter("doorFlag");
		String certAttributeType1 = request.getParameter("certAttributeType1");
		String idCardType_1 = request.getParameter("idCardType_1");
		String idCardType_2 = request.getParameter("idCardType_2");

		Map<String,String> filePath = new HashMap<String, String>();
		filePath.put("businessType", businessType);
		filePath.put("doorPhoto", doorPhoto);
		filePath.put("doorFlag", doorFlag);
		filePath.put("certAttributeType1", certAttributeType1);
		filePath.put("idCardType_1", idCardType_1);
		filePath.put("idCardType_2", idCardType_2);
		try {
			merchantService.updateMerchantEnterAndFeeRule(merchantVo,filePath);
			object.put("result", "SUCCESS");
			object.put("message", "修改商户信息成功");
		} catch (Exception e) {
			logger.error("修改商户信息未成功", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}

    /**
     * 一级审核通过
     */
    @RequestMapping(MerchantEnterPath.AUDIT)
    @ResponseBody
    public String firstPass(String merchantCode,String message,String isPass){
        logger.info("开始商户{}一级审核通过流程",merchantCode);

        JSONObject ob = null;
        try {
            if ("1".equals(isPass)){
                ob = this.pass(merchantCode,message,isPass);
            }else {
                ob = this.unPass(merchantCode,message,isPass);
            }

        } catch (Exception e) {
            logger.error("审核异常",e);
            ob.put("result", "FAILE");
            ob.put("message", e.getMessage());
        }
        return ob.toJSONString();
    }


    private JSONObject pass(String merchantCode, String message, String isPass) {
        /**
         * 启动流程完成任务
         */
        JSONObject ob = new JSONObject();
        logger.info("启动流程完成任务");
        TdCustInfo tdCustInfo = tdCustInfoService.selectByMerchantCode(merchantCode);
        merchantWorkFlowAuditService.startProcessAndCompleteTaskEnter(tdCustInfo.getCustId(),isPass,message);
        ob.put("result", "SUCCESS");

        //二级审核
        //TdCustInfo custInfo = tdCustInfoService.selectById(custId);
        ResponseMessage<BindBankCardResponse> response = merchantService.requestBindBank(tdCustInfo);
        if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {

        }else{
            ob.put("result", "FAILE");
            ob.put("message", "七分钱账户开户失败");
            return ob;
        }
        //merchantWorkFlowAuditService.secondAudit(tdCustInfo.getCustId(),number, true, tdCustInfo.getAuthId(), message, "30", "0","3","notEmpty");
        merchantWorkFlowAuditService.secondAuditEnter(tdCustInfo.getCustId(), true, tdCustInfo.getAuthId(), message, "30", "0","3","notEmpty");

        ob.put("result", "SUCCESS");

        /**
         * 发送邮件
         */

        MerchantVo merchant = merchantMapper.findMerchantInfo(tdCustInfo.getCustId());

        String content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
                + "<div style=\"margin-bottom:10px;\">"
                + "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
                + "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
                + "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + merchant.getCustName() + "：您好！</h3>"
                + "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + merchant.getEmail() + "</b>"
                + "已经审核通过，可以通过 "
                + "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + " 登录系统。" + "</p>"
                + "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
                + "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
                + "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
                + "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
                + "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
                + "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
        String subject = "七分钱--亲爱的" + merchant.getCustName() + "，你的七分钱商户账号已经审核通过，欢迎登录！";
        logger.info("{}发送邮件(审核通过)!",tdCustInfo.getCustId());
        boolean flag = merchantService.sendInfo(merchant.getEmail(), content, subject, MessageColumnValues.MsgType.EMAIL, MessageColumnValues.busType.REGISTER_VERIFY);
        if(flag){
            logger.info("{}审核通过发送邮件成功(审核通过)!",tdCustInfo.getCustId());
        }
        return ob;
    }

    private JSONObject unPass(String merchantCode, String message, String isPass) {
        logger.info("开始商户{}一级审核不通过流程",merchantCode);
        JSONObject ob = new JSONObject();
        TdCustInfo custInfo = tdCustInfoService.selectByMerchantCode(merchantCode);
        merchantWorkFlowAuditService.firstNotPass(custInfo.getCustId(), message, false, "35", custInfo.getAuthId(), "2");//35  实名认证不通过        2：审核不通过
        ob.put("result", "SUCCESS");


        //二级审核不通过
        merchantWorkFlowAuditService.secondAuditEnter(custInfo.getCustId(),false, custInfo.getAuthId(), message, "35", "2",null,"");
        ob.put("result", "SUCCESS");

        /**
         * 发送邮件
         */
        MerchantVo merchant = merchantMapper.findMerchantInfo(custInfo.getCustId());
        String content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
                + "<div style=\"margin-bottom:10px;\">"
                + "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
                + "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
                + "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + merchant.getCustName() + "：您好！</h3>"
                + "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + merchant.getEmail() + "</b>"
                + "审核不通过，可以通过 "
                + "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + "登录系统，重新提交资料。" + "</p>"
                + "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
                + "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
                + "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
                + "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
                + "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
                + "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
        String subject = "七分钱--亲爱的" + merchant.getCustName() + "，你的七分钱商户账号没有审核通过，请重新提交！";
        logger.info("{}发送邮件(二级审核不通过)!",custInfo.getCustId());
        boolean flag = merchantService.sendInfo(merchant.getEmail(), content, subject, MessageColumnValues.MsgType.EMAIL, MessageColumnValues.busType.REGISTER_VERIFY);
        if(flag){
            logger.info("{}发送邮件成功(二级审核不通过)!",custInfo.getCustId());
        }
        return ob;
    }


    /**
	 * 获取二维码
	 * @return
	 */
	private String getQrCode(MerchantVo merchantVo) {
		logger.info("开始查询商户信息===========");
		JSONObject object = new JSONObject();
		Map<String,String> resultMap = new HashMap<String,String>();
		String last_page = "/agent/merchantSuccess.jsp";
		String error_msg = "查询代理商信息有误";
		try {
			//获取商户编号
			TdCustInfo tdCustInfo = tdCustInfoMapper.selectById(merchantVo.getMerchantCode());

			if(null!=merchantVo.getMerchantCode()&&!("".equals(merchantVo.getMerchantCode()))){
				String url = "https://combinedpay.qifenqian.com/pub/merchantqr.do?mid=" +merchantVo.getMerchantCode() +"&sn=" + merchantVo.getMerchantCode();

				object.put("url", url);
				object.put("custId", merchantVo.getCustId());
				//保存
				CustScan custScan = new CustScan();
//					TdCustScanCopy scanCopyIdCard = new TdCustScanCopy();
				custScan.setCustId(merchantVo.getCustId());
				custScan.setCertifyType("re");//二维码

				String imagePath = custScanMapper.findPath(custScan);
//					SevenpayResponse srp3 = BusinessFacadeHelper.handleService("EnterpriseService", "findPath",scanCopyIdCard);
//					String imagePath =(String)srp3.getResult();
				if (imagePath != null) {
					//已经插入
				}else{
					custScan.setCustId(tdCustInfo.getCustId());
					custScan.setCustName(tdCustInfo.getCustName());
					//custScan.setScanCopyPath(fileUploadPath+ File.separator+"re"+File.separator+storeManage.getMchId()+File.separator+"recode.jpg");
					custScan.setCertifyNo(tdCustInfo.getCertifyNo());
					//saveCustScanCopy(custScan);
					custScanMapper.insertCustScan(custScan);
				}

			}else{
				error_msg = "查询代理商信息有误：商户编号为空";
				logger.error("查询代理商信息有误：商户编号为空");
			}


		} catch (Exception e) {
			resultMap.put("last_page", last_page);
			resultMap.put("errMag",error_msg );
			logger.error("查询代理商信息有误==========="+e);
			e.printStackTrace();
		}

		return object.toString();
	}

	/**
	 * 事务处理
	 *
	 * @param custId
	 * @param merchant
	 */
	public void saveMerchant(String custId, Merchant merchant, String paths) {
		merchantService.addMerchant(custId, merchant, paths);
	}


}
