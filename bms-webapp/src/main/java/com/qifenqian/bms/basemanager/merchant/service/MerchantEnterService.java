package com.qifenqian.bms.basemanager.merchant.service;

import cn.jpush.api.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.acctsevenbuss.mapper.AcctSevenBussMapper;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.basemanager.bank.service.BankService;
import com.qifenqian.bms.basemanager.city.service.CityService;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.dao.MerchantDao;
import com.qifenqian.bms.basemanager.merchant.mapper.*;
import com.qifenqian.bms.basemanager.rule.mapper.RuleMapper;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.MD5Security;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.upgrade.merchant.bean.MerchantRegisterInfo;
import com.qifenqian.bms.upgrade.merchant.bean.TdAuditRecodeInfo;
import com.qifenqian.bms.upgrade.merchant.dao.MerchantListDao;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.transaction.bindbankcard.BindBankCardResponse;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.commons.codec.binary.Base64;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;
import com.qifenqian.bms.platform.utils.GenSN;
import com.qifenqian.bms.platform.web.myWorkSpace.service.WorkSpaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    @Autowired
    private MerchantListDao merchantListDao;

    ExecutorService smsExecutors = Executors.newFixedThreadPool(10);
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

    /**
     * 审核结果
     * @param result 审核结果，pass通过   noPass不通过
     * @param merchantId 商户编号
     * @param AuditInfo	审核信息
     * @return
     */
    public String updateResultOfAudit(String result, String merchantId, TdAuditRecodeInfo AuditInfo) {
        String applyStatus = null;
        String custStatus = null;
        String userStatus = null;
        String[] tos = null;
        String content = null;
        String subject = null;
        String pwd = GenSN.getRandomNum(6);
        String attachStr = GenSN.getRandomNum(5);
        final IPlugin plugin = commonService.getIPlugin();
        final MessageBean messageBean = new MessageBean();



        //获取custId
        List<MerchantRegisterInfo> list = queryMerchantInfo(merchantId);
        MerchantRegisterInfo merchantInfo = null;
        merchantInfo = list.get(0);
        String custId = merchantInfo.getCustId();
        //获取登陆表信息
        com.qifenqian.bms.basemanager.custInfo.bean.TdLoginUserInfo userInfo = merchantListDao.getUserInfoByCustId(custId);
        String userEmail = userInfo.getEmail();
        String userMobile = userInfo.getMobile();

        //获取商户信息
        TdCustInfo custInfo = merchantListDao.getCustInfoByCustId(custId);
        String custName = custInfo.getCustName();
        //审核不通过
        if("noPass".equals(result)) {
            applyStatus = "99";
            custStatus = "04";
            userStatus = "07";
            if(userEmail==null||("").equals(userEmail)) {
                //审核不通过，手机短信通知
                tos = new String[] { userMobile };
                messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
                messageBean.setSubject("【七分钱支付】商户审核未通过");// 标题
                messageBean.setContent("【七分钱支付】您的商户注册未能通过审核，原因是：" + AuditInfo.getAuditInfo() +"请前往商户网站修改商户信息，如有任何问题，请拨打400-633-0707。");

            }else {
                //审核不通过，邮件通知
                tos = new String[] {userEmail};
                content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
                        + "<div style=\"margin-bottom:10px;\">"
                        + "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
                        + "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
                        + "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + custInfo.getCustName() + "：您好！</h3>"
                        + "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + userInfo.getEmail() + "</b>"
                        + "审核不通过，具体原因为:"+AuditInfo.getAuditInfo()+"您可以通过 "
                        + "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + "登录系统，重新提交资料。" + "</p>"
                        + "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
                        + "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
                        + "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
                        + "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
                        + "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
                        + "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
                messageBean.setContent(content);
                messageBean.setSubject("【七分钱支付】商户审核未通过");// 标题
                messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
            }
            //审核通过
        }else if("pass".equals(result)) {
            applyStatus = "00";
            custStatus = "00";
            userStatus = "00";
            //密码加密
            String loginPwd_02 = MD5Security.getMD5String(custId + pwd + attachStr);
            userInfo.setLoginPwd(loginPwd_02);
            userInfo.setAttachStr(attachStr);
            //将密码以及attachStr字段写入登录表中
            merchantListDao.updateLoginUserInfo(userInfo);
            if(userEmail==null||("").equals(userEmail)) {
                //审核通过，手机短信通知
                tos = new String[] { userMobile };
                messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
                messageBean.setSubject("【七分钱支付】商户审核已通过");// 标题
                messageBean.setContent("【七分钱支付】您的商户注册已通过审核，初始密码为"+pwd+"请您尽快登录系统修改初始密码，如有任何问题，请拨打400-633-0707。");


            }else {
                tos = new String[] { userEmail };
                content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
                        + "<div style=\"margin-bottom:10px;\">"
                        + "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
                        + "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
                        + "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + custInfo.getCustName() + "：您好！</h3>"
                        + "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + userInfo.getEmail() + "</b>"
                        + "已经审核通过，初始密码为"+pwd+",您可以通过 "
                        + "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + " 登录系统。" + "</p>"
                        + "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
                        + "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
                        + "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
                        + "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
                        + "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
                        + "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
                messageBean.setContent(content);
                messageBean.setSubject("七分钱--亲爱的" + custInfo.getCustName() + "，你的七分钱商户账号已经审核通过，欢迎登录！");// 标题
                messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
            }
        }
        String applyId = merchantListDao.getApplyIdByMerchantId(merchantId);
        if(StringUtils.isNotEmpty(applyId)) {
            //服务商内新增的商户，会在TD_MERCHANT_APPLY_INFO中有数据，因此状态要更新到APPLY表中
            merchantListDao.updateStatusForAuditResult(applyStatus, custStatus, userStatus, merchantId);
        }else {
            merchantListDao.updateStatusForAuditResultOld(custStatus, userStatus, merchantId);
        }
        //先删除已有审核信息，避免读取列表时信息重复
        merchantListDao.deleteAuditInfo(AuditInfo);
        merchantListDao.insertAuditInfo(AuditInfo);
        messageBean.setTos(tos);
        messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);


        smsExecutors.execute(new Runnable() {
            @Override
            public void run() {
                logger.debug("发送邮件");
                if (messageBean.getMsgType().equals(MessageColumnValues.MsgType.SMS)) {
                    plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean); // 电话SMS
                } else {
                    plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean); // 邮件EMAIL
                }
            }
        });

        return "success";
    }

    /**
     * 查询商户信息
     * @param merchantCode 商户编号
     * @return
     */
    public List<MerchantRegisterInfo> queryMerchantInfo(String merchantCode){
        return merchantListDao.queryMerchantInfo(merchantCode);
    }
}
