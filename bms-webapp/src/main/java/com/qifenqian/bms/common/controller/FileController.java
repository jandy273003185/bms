package com.qifenqian.bms.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.PicturePath;
import com.qifenqian.bms.basemanager.merchant.mapper.CustScanMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.service.MerchantEnterService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.common.bean.FileInfo;
import com.qifenqian.bms.platform.common.utils.DateUtils;

@Controller
@RequestMapping("/common/files")
public class FileController {

	@Autowired
	private CustScanMapper custScanMapper;
	@Autowired
	private MerchantMapper merchantMapper;
	@Autowired
	private MerchantEnterService merchantEnterService;
	
	private static final String PRE_PATH = "/data/nfsshare/upload/picture/";
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	
    @Value("${images.relativePaths}")
    private String relativePaths;
    //存储路径
    @Value("${images.absolutePaths}")
    private String absolutePaths;
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public FileInfo upload(MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				String fileName = file.getOriginalFilename();
				String suffixName = fileName.substring(fileName.lastIndexOf("."));
				File localFile = new File(PRE_PATH + GenSN.getSysTime() + GenSN.getSN() + suffixName);
				file.transferTo(localFile);
				return new FileInfo(localFile.getAbsolutePath());
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * 文件下载， 只支持图片类型的文件
	 * 
	 * @param path
	 * @param httpResponse
	 * @throws IOException
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public void get(String path, HttpServletResponse httpResponse) throws IOException {

		File file = new File(path);

		if (!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream = httpResponse.getOutputStream();
			outputStream.write(errorMessage.getBytes("UTF-8"));
			outputStream.close();
			return;
		}
		
		ImageIO.write(ImageIO.read(file), "JPEG", httpResponse.getOutputStream());

	}
	
	
	@RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    @ResponseBody
    public FileInfo fileUpload(@RequestParam("file")MultipartFile file){

        // 获取文件名后缀名
        String suffix = file.getOriginalFilename();
        String prefix = suffix.substring(suffix.lastIndexOf("."));
        
        String Filename = DateUtils.getDateStr8()+"_"+UUID.randomUUID().toString().replaceAll("-","");
        if (!file.isEmpty()) {//文件不为空
            try {
                //上传路径
                StringBuilder filePath = new StringBuilder("/data/nfsshare/upload/picture").append("/").append(Filename).append(prefix);
                File saveDir = new File(String.valueOf(filePath));
                // 转存文件
                file.transferTo(saveDir);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("imagePath",Filename+prefix);
//              jsonObject.put("uri",uri);
                jsonObject.put("url",new StringBuilder("/pic/").append(Filename).append(prefix));
                return new FileInfo(Filename+prefix);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("上传失败");
            }
        }
        logger.error("上传文件为空");
        return new FileInfo("网络延迟，请重新提交");
        
        
        

    }
	
	/**
	 * 图片路径入库
	 * @param provinceBean
	 * @return
	 */
	@RequestMapping(value = "/getPicPath", method = RequestMethod.POST)
	@ResponseBody
	public String getPicPath(PicturePath  picturePath ,String custId) {
		JSONObject object = new JSONObject();
		CustScan custScan = new CustScan();
		MerchantVo merchantVo = merchantMapper.newFindMerchantInfo(custId);
		PicturePath picturePathOld = merchantEnterService.getPicPath(merchantVo);
		
		custScan.setCustId(custId);
		custScan.setAuthId(merchantVo.getAuthId());
		custScan.setCreateId(merchantVo.getCreateId());
		custScan.setCustId(custId);
		
		try {
			//银行卡照
			if(!StringUtils.isEmpty(picturePath.getBankCardPath())) {
				custScan.setCertifyType("07");
				if(!StringUtils.isEmpty(picturePathOld.getBankCardPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				custScan.setStatus("00");
				custScan.setScanCopyPath(picturePath.getBankCardPath());
				custScanMapper.insertCustScan(custScan);
			}
			//身份证正面照
			if(!StringUtils.isEmpty(picturePath.getIdCardOPath())) {
				custScan.setCertifyType("04");
				
				if(!StringUtils.isEmpty(picturePathOld.getIdCardOPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setStatus("00");
				custScan.setScanCopyPath(picturePath.getIdCardOPath());
				custScanMapper.insertCustScan(custScan);
			}
			//身份证反面照
			if(!StringUtils.isEmpty(picturePath.getIdCardFPath())) {
				custScan.setCertifyType("16");
				
				if(!StringUtils.isEmpty(picturePathOld.getIdCardFPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getIdCardFPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//营业执照
			if(!StringUtils.isEmpty(picturePath.getBussinessPath())) {
				custScan.setCertifyType("02");
				
				if(!StringUtils.isEmpty(picturePathOld.getBussinessPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getBussinessPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//开户许可证
			if(!StringUtils.isEmpty(picturePath.getOpenAccountPath())) {
				custScan.setCertifyType("03");
				
				if(!StringUtils.isEmpty(picturePathOld.getOpenAccountPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getOpenAccountPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//门头照
			if(!StringUtils.isEmpty(picturePath.getDoorPhotoPath())) {
				custScan.setCertifyType("08");
				
				if(!StringUtils.isEmpty(picturePathOld.getDoorPhotoPath())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getDoorPhotoPath());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//结算人身份证正面
			if(!StringUtils.isEmpty(picturePath.getSettleCertAttribute1Path())) {
				custScan.setCertifyType("30");
				
				if(!StringUtils.isEmpty(picturePathOld.getSettleCertAttribute1Path())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getSettleCertAttribute1Path());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			//结算人身份证反面
			if(!StringUtils.isEmpty(picturePath.getSettleCertAttribute2Path())) {
				custScan.setCertifyType("31");
				
				if(!StringUtils.isEmpty(picturePathOld.getSettleCertAttribute2Path())) {
					custScan.setStatus("01");
					custScanMapper.updateCustScan(custScan);
				}
				
				custScan.setScanCopyPath(picturePath.getSettleCertAttribute2Path());
				custScan.setStatus("00");
				custScanMapper.insertCustScan(custScan);
			}
			object.put("result", "SUCCESS");
			object.put("message", "新增图片成功");
		} catch (Exception e) {
			object.put("result", "FAIL");
			object.put("message", "新增图片失败");
		}
		return object.toString();
		
	}
}
