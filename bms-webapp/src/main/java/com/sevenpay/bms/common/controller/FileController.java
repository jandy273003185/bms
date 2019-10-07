package com.sevenpay.bms.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sevenpay.bms.common.bean.FileInfo;

@Controller
@RequestMapping("/common/files")
public class FileController {

	private static final String PRE_PATH = "/data/nfsshare/merchant/";

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public FileInfo upload(MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				String fileName = file.getOriginalFilename();
				String suffixName = fileName.substring(fileName.lastIndexOf("."));
				File localFile = new File(PRE_PATH + UUID.randomUUID().toString() + suffixName);
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
}
