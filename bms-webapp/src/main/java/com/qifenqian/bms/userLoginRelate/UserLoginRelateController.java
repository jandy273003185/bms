package com.qifenqian.bms.userLoginRelate;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.sysuser.bean.SysUser;
import com.qifenqian.bms.basemanager.sysuser.mapper.SysUserMapper;
import com.qifenqian.bms.basemanager.tdsalesmaninf.bean.TdSalesmanInfo;
import com.qifenqian.bms.basemanager.tdsalesmaninf.mapper.TdSalesmanInfoMapper;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.product.bean.MerchantProduct;
import com.qifenqian.bms.merchant.product.bean.Product;
import com.qifenqian.bms.merchant.product.mapper.MerchantProductMapper;
import com.qifenqian.bms.merchant.product.service.MerchantProductService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.userLoginRelate.bean.UserLoginRelate;
import com.qifenqian.bms.userLoginRelate.service.UserLoginRelateService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 商户产品信息控制层
 * 
 * @project qifenqian-bms
 * @fileName MerchantProductController.java
 * @author wuzz
 * @date 2019年11月7日
 * @memo
 */
@Controller
@RequestMapping("/userLoginRelate")
public class UserLoginRelateController {

	private Logger logger = LoggerFactory.getLogger(UserLoginRelateController.class);

	@Autowired
	private UserLoginRelateService userLoginRelateService;
	@Autowired
	private TdCustInfoMapper custInfoMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private TdSalesmanInfoMapper  salesmanInfoMapper;
	/**
	 * 进入用户登陆关联表列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(UserLoginRelate userLoginRelate) {
		logger.info("进入用户登陆关联表列表页面");
		// 返回视图
		ModelAndView mv = new ModelAndView("/userLoginRelate/list");
		userLoginRelate.setIfUnbind("1");
		String startTime = null;//起止最大时间
		String endTime = null;//结束时间
		if (!StringUtils.isBlank(userLoginRelate.getStartTime())) {
			startTime = userLoginRelate.getStartTime().trim();
			userLoginRelate.setStartTime(startTime+" 00:00:00");
		}
		if (!StringUtils.isBlank(userLoginRelate.getEndTime())) {
			endTime = userLoginRelate.getEndTime().trim();
			userLoginRelate.setEndTime(endTime+" 23:59:59");
		}
		List<UserLoginRelate> userLoginRelateList = userLoginRelateService.selectUserLoginRelateListByPage(userLoginRelate);
		
		
		if (userLoginRelateList != null && userLoginRelateList.size()>0) {
			for (int i = 0; i < userLoginRelateList.size(); i++) {
				TdCustInfo custInfo = custInfoMapper.selectById(userLoginRelateList.get(i).getCustId());
				if (custInfo !=null) {
					userLoginRelateList.get(i).setCustName(custInfo.getCustName());
				}
				if ("cust".equals(userLoginRelateList.get(i).getUserType())) {
					SysUser selectUserById = sysUserMapper.selectDeptByUserCode(userLoginRelateList.get(i).getUserId());
					if (selectUserById !=null) {
						userLoginRelateList.get(i).setUserName(selectUserById.getUserName());
					}
				}else if ("salesman".equals(userLoginRelateList.get(i).getUserType())) {
					TdSalesmanInfo selectTdSalesmanInfoById = salesmanInfoMapper.selectTdSalesmanInfoById(userLoginRelateList.get(i).getUserId());
					if (selectTdSalesmanInfoById !=null) {
						userLoginRelateList.get(i).setUserName(selectTdSalesmanInfoById.getUserName());
					}
				}else {
					TdCustInfo custInfo2 = custInfoMapper.selectById(userLoginRelateList.get(i).getUserId());
					if (custInfo2 != null) {
						userLoginRelateList.get(i).setUserName(custInfo2.getCustName());
					}
				}
				
			}
		}
		
		mv.addObject("userLoginRelateList", JSONObject.toJSON(userLoginRelateList));
		
		userLoginRelate.setStartTime(startTime);
		userLoginRelate.setEndTime(endTime);
		mv.addObject("userLoginRelate", userLoginRelate);
		List<TdCustInfo> tdCustInfoList = custInfoMapper.selectByMerchantFlag("3");
		mv.addObject("tdCustInfoList",  JSONObject.toJSON(tdCustInfoList));
	
		// 返回
		return mv;
	}
	
	
	/**
	 * 商户产品新增
	 * 
	 * @param requestMateriel
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String add(UserLoginRelate userLoginRelate) {
		// 请求bean 打印
		logger.info("请求保存MerchantProduct：[{}]", JSONObject.toJSONString(userLoginRelate, true));
		//开通默认09,待审核
		
		JSONObject jsonObject = new JSONObject();
		try {
			UserLoginRelate userLoginRelate2 = new UserLoginRelate();
			userLoginRelate2.setUserId(userLoginRelate.getUserId());
			UserLoginRelate selectUserLoginRelate = userLoginRelateService.selectUserLoginRelateByCode(userLoginRelate2);
			if (selectUserLoginRelate !=null) {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "该商户产品已经存在");
			} else {
				userLoginRelate.setIfUnbind("1");//默认绑定
				userLoginRelate.setCreateTime(new Date());
				userLoginRelate.setUpdateTime(new Date());
				userLoginRelateService.insertUserLoginRelate(userLoginRelate);
				jsonObject.put("result", "SUCCESS");
			}
		} catch (Exception e) {
			logger.error("新增商户产品异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * @param userLoginRelate  删除
	 * @return
	 */
	@RequestMapping("/del")
	@ResponseBody
	public String updateUserLoginRelate(UserLoginRelate userLoginRelate) {
		// 请求bean 打印
		logger.info("请求保存MerchantProduct：[{}]", JSONObject.toJSONString(userLoginRelate, true));
		//开通默认09,待审核
		JSONObject jsonObject = new JSONObject();
		userLoginRelate.setIfUnbind("0");
		try {
			userLoginRelateService.updateUserLoginRelate(userLoginRelate);
			jsonObject.put("result", "SUCCESS");
			} catch (Exception e) {
				logger.error("新增商户产品异常", e);
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", e.getMessage());
			}
			return jsonObject.toJSONString();
	}


}
