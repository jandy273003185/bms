package com.sevenpay.bms.basemanager.merchant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gyzb.platform.web.admin.user.bean.User;
import org.gyzb.platform.web.admin.user.service.UserService;
import org.sevenpay.platform.web.myWorkSpace.WorkSpacePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.sevenpay.bms.basemanager.bank.bean.Bank;
import com.sevenpay.bms.basemanager.bank.mapper.BankMapper;
import com.sevenpay.bms.basemanager.city.service.CityService;
import com.sevenpay.bms.basemanager.merchant.bean.MerchantVo;
import com.sevenpay.bms.basemanager.merchant.service.MerchantWorkFlowAuditService;
import com.sevenpay.bms.basemanager.rule.bean.Rule;
import com.sevenpay.bms.basemanager.rule.mapper.RuleMapper;

@Controller
@RequestMapping(MerchantEnterPath.BASE)
public class MerchantEnterController {
	
	
	private Logger logger = LoggerFactory.getLogger(MerchantEnterController.class);
	
	@Autowired
	private MerchantWorkFlowAuditService merchantWorkFlowAuditService;
	@Autowired
	private BankMapper bankMapper;
	@Autowired
	private RuleMapper ruleMapper;
	@Autowired
	private CityService cityService;
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @param merchantVo
	 * @return
	 */
	@RequestMapping(MerchantEnterPath.LIST)
	public ModelAndView list(MerchantVo merchantVo) {
		Bank bank = new Bank();
//		Rule rule = new Rule();
		ModelAndView mv = new ModelAndView(MerchantEnterPath.BASE + MerchantEnterPath.LIST);
		
		/**
		 * 校验权限
		 */
		merchantWorkFlowAuditService.verifyPermission(mv);
		
//		List<MerchantVo> list = merchantService.selectMerchants(merchantVo);
		
		/**
		 * 装载工作流审核状态
		 */
//		merchantWorkFlowAuditService.loadAuditStatus(list);
		
//		mv.addObject("banklist", bankMapper.selectBanks(bank));
//		mv.addObject("merchantList", JSONObject.toJSON(list));
////		mv.addObject("rulelist", ruleMapper.selectRules02(rule));
//		mv.addObject("provincelist", cityService.selectAllProvince());
//		mv.addObject("queryBean", merchantVo);
		
		return mv;
	}
	
	
	/**
	 * 跳转到相应的页面
	 */
	@RequestMapping(MerchantEnterPath.ADDPAGE)
	public ModelAndView addview(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(MerchantEnterPath.BASE + MerchantEnterPath.ADDPAGE);
		
		Bank bank = new Bank();
		Rule rule = new Rule();
		User user = new User();
		rule.setStatus("VALID");
		mv.addObject("taskId", request.getParameter("taskId"));
		mv.addObject("banklist", bankMapper.selectBanks(bank));
		mv.addObject("rulelist", ruleMapper.selectRules(rule));
		mv.addObject("userlist", userService.getUserList(user));
		mv.addObject("provincelist", cityService.selectAllProvince());
		mv.addObject("provincelist_", cityService.selAllProvince());
		return mv;
		
	}

}
