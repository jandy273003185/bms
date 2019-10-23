package com.sevenpay.bms.basemanager.merchant.service;

import com.sevenpay.bms.basemanager.acctsevenbuss.mapper.AcctSevenBussMapper;
import com.sevenpay.bms.basemanager.bank.service.BankService;
import com.sevenpay.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.sevenpay.bms.basemanager.merchant.bean.Merchant;
import com.sevenpay.bms.basemanager.merchant.bean.MerchantExport;
import com.sevenpay.bms.basemanager.merchant.bean.MerchantVo;
import com.sevenpay.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.sevenpay.bms.basemanager.merchant.dao.MerchantDao;
import com.sevenpay.bms.basemanager.merchant.mapper.*;
import com.sevenpay.bms.expresspay.CommonService;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.commons.codec.binary.Base64;
import org.sevenpay.platform.web.myWorkSpace.service.WorkSpaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商户注册服务类v2
 */
@Service
public class MerchantEnterService {
    private static Base64 base64 = new Base64();

    @Autowired
    private CommonService commonService;
    @Autowired
    private BankService bankService;
    @Autowired
    SevenpayCoreServiceInterface sevenpayCoreServiceInterface;
    @Autowired
    private TdLoginUserInfoMapper mapper;

    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private MerchantEnterMapper merchantEnterMapper;
    @Autowired
    private CustScanMapper custScanMapper;

    @Autowired
    private MerchantDao dao;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private IdentityService identityService;

    @Autowired
    private BmsProtocolContentMapper bmsProtocolContentMapper;

    @Autowired
    private TdCustInfoMapper tdCustInfoMapper;

    @Autowired
    private WorkSpaceService workSpaceService;

    @Autowired
    private TdLoginUserInfoMapper tdLoginUserInfoMapper;

    @Autowired
    private AcctSevenBussMapper acctSevenBussMapper;

    @Autowired
    private StoreManageMapper storeManageMapper;

    private static final Logger logger = LoggerFactory.getLogger(MerchantService.class);

    /**
     * 注册通过后的商户信息v2
     *
     * @param merchantVo
     * @return
     */
    public List<MerchantVo> selectAuditMerchants(MerchantVo merchantVo) {
        List<MerchantVo> list = dao.auditList2(merchantVo);
        return list;
    }
    
    /**
     * 导出商户信息
     * 
     * @param merchantVo
     * @return
     */
    public List<MerchantExport> proExportMerchantInfo(MerchantVo merchantVo) {
      return merchantEnterMapper.newExportlist(merchantVo);
    }

    public TdLoginUserInfo validateMerchantAccount(String merchantAccount, String custId) {
        TdLoginUserInfo tdLoginUserInfo = null;
        if(merchantAccount.contains("@")){
            //账号为邮箱
            tdLoginUserInfo = tdLoginUserInfoMapper.selectByEmail(merchantAccount, null, null);
        }else {
            tdLoginUserInfo = tdLoginUserInfoMapper.selectByPhoneEnter(merchantAccount);
        }
        return tdLoginUserInfo;
    }
}
