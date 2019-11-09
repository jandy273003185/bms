package com.qifenqian.bms.merchant.product;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.merchant.product.bean.MerchantProduct;
import com.qifenqian.bms.merchant.product.bean.Product;
import com.qifenqian.bms.merchant.product.mapper.MerchantProductMapper;
import com.qifenqian.bms.merchant.product.service.MerchantProductService;
import java.util.List;
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
@RequestMapping("/merchant/product")
public class MerchantProductController {

	private Logger logger = LoggerFactory.getLogger(MerchantProductController.class);

	@Autowired
	private MerchantProductService merchantProductService;
	
	@Autowired
	private MerchantMapper merchantMapper;
	
	@Autowired
	private MerchantProductMapper merchantProductMapper;

	
	/**
	 * 进入商户设备列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(MerchantProduct merchantProduct) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/merchant/product/list");
		mv.addObject("merchantProductList", JSONObject.toJSON(merchantProductService.selectMaterielListByPage(merchantProduct)));
		mv.addObject("merchantProduct", merchantProduct);
		//商户信息
		List<Merchant> merchantList = merchantMapper.selectMerchant();
		mv.addObject("merchantList", merchantList);
		//产品信息
		List<Product> productList = merchantProductMapper.selectProduct();
		mv.addObject("productList", productList);
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
	public String add(MerchantProduct merchantProduct) {
		// 请求bean 打印
		logger.info("请求保存MerchantProduct：[{}]", JSONObject.toJSONString(merchantProduct, true));
		
		JSONObject jsonObject = new JSONObject();
		try {
			MerchantProduct merPro = merchantProductService.selectMerchantProductByCode(merchantProduct);
			if (merPro !=null) {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "该商户产品已经存在");
			} else {
				merchantProductService.insertMaterielSingle(merchantProduct);
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
	 * 商户产品信息修改
	 * 
	 * @param merchantProduct
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String edit(MerchantProduct merchantProduct) {
		// 请求bean 打印
		logger.info("请求修改merchantProduct：[{}]", JSONObject.toJSONString(merchantProduct, true));

		JSONObject jsonObject = new JSONObject();	
		if (null == merchantProduct) {
			throw new IllegalArgumentException("商户产品对象为空");
		}
		if (StringUtils.isEmpty(merchantProduct.getMerchantCode())) {
			throw new IllegalArgumentException("商户代码不能为空");
		}
		if (StringUtils.isEmpty(merchantProduct.getMachineId())) {
			throw new IllegalArgumentException("产品代码不能为空");
		}
		try {
			//查询需要修改的商户产品信息是否存在
			MerchantProduct merPro1 = merchantProductMapper.selectMerchantProductByCode(merchantProduct);
			if (merPro1 == null) {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "商户产品信息不存在");
			} else {				
				merchantProductService.updateMerchantProduct(merchantProduct);
				jsonObject.put("result", "SUCCESS");					
			}
		} catch (Exception e) {
			logger.error("修改商户产品异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	
	/**
	 * 商户产品删除
	 * 
	 * @param merchantProduct
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(MerchantProduct merchantProduct) {
		logger.info("请求商户产品：[{}]", merchantProduct);

		JSONObject jsonObject = new JSONObject();
		try {
			// 删除
			merchantProductService.deleteMerchantProduct(merchantProduct);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("删除商户产品信息异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	


}
