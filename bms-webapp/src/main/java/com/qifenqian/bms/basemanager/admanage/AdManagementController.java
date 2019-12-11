package com.qifenqian.bms.basemanager.admanage;


import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.app.ad.bean.AdManageBean;
import com.qifenqian.bms.basemanager.admanage.bean.AdManagement;
import com.qifenqian.bms.basemanager.admanage.service.AdManagementService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(AdManagementPath.BASE)
public class AdManagementController {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(AdManagementController.class);

    @Value("${ADMANAGE_FILE_SAVE_PATH}")
    private String updatePath;


    @Autowired
    private AdManagementService adManagementService;

    /**
     * 显示广告信息列表
     *
     * @param queryBean
     * @return
     */
    @RequestMapping(AdManagementPath.LIST)
    public ModelAndView list(AdManagement queryBean) {
        ModelAndView mv = new ModelAndView(AdManagementPath.BASE + AdManagementPath.LIST);
        List<AdManagement> adManagementList = adManagementService.selectAdManagementListBy(queryBean);
        mv.addObject("adList", JSONObject.toJSON(adManagementList));
        mv.addObject("queryBean", queryBean);
        return mv;
    }
    /**
     * 添加APP登录广告页信息
     */
    @RequestMapping(AdManagementPath.ADD)
    @ResponseBody
    public String saveAdManagement(AdManagement adManagement) {
        String result = this.adManagementService.saveAdManagement(adManagement);
        JSONObject json = new JSONObject();
        json.put("result", result);
        return json.toString();
    }

    /**
     * 更新APP登录页广告信息
     */
    @RequestMapping(AdManagementPath.UPDATE)
    @ResponseBody
    public String updateAdManagement(AdManagement adManagement) {
        String result = this.adManagementService.updateAdManagement(adManagement);
        JSONObject json = new JSONObject();
        json.put("result", result);
        return json.toString();
    }

    /**
     * 删除APP登录页广告信息
     *
     * @param adId
     * @return
     */
    @RequestMapping(AdManagementPath.DELETE)
    @ResponseBody
    public String deleteAdManageInfo(String adId) {
        JSONObject json = new JSONObject();
        json.put("result", new HashMap<>());
        return json.toString();
    }


    /**
     * 文件上传
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(AdManagementPath.FILEUPLOAD)
    @ResponseBody
    public String fileUpload(HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        String pathName = "";
        InputStream in = null;
        FileOutputStream out = null;

        try {
            // 使用Apache文件上传组件处理文件上传步骤：
            // 创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            List<FileItem> list = upload.parseRequest(request);

            for (FileItem item : list) {
                String filename = null;
                if (!item.isFormField()) {
                    filename = item.getName();

                    String type = filename.substring(filename.lastIndexOf("."));
                    String[] photoTypes = { ".jpg", ".jpeg", ".gif", ".bmp", ".png" };
                    boolean isType = false;
                    for (int i = 0; i < photoTypes.length; i++) {
                        if (photoTypes[i].equalsIgnoreCase(type)) {
                            isType = true;
                            break;
                        }
                    }
                    if (!isType) {
                        object.put("result", "FAIL");
                        object.put("message", "文件类型不匹配");

                        return object.toJSONString();

                    }
                    filename = filename.substring(filename.lastIndexOf(File.separator) + 1);

                    filename = System.currentTimeMillis() + filename;
                    in = item.getInputStream();


                    pathName = updatePath + File.separator + filename;

                    File saveFile = new File(updatePath);
                    if (!saveFile.exists()) {
                        saveFile.mkdirs();
                    }
                    out = new FileOutputStream(pathName);

                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }

                    item.delete();
                }

            }

            object.put("result", "SUCCESS");
            object.put("message", "上传成功");
            object.put("pathName", pathName);

        } catch (Exception e) {

            object.put("result", "fail");
            object.put("message", e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (out != null) {
                    out.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }

        return object.toJSONString();
    }

    // 读取服务器图片
    @RequestMapping(AdManagementPath.IMAGE)
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imgPath = request.getParameter("imgPath");
        if (imgPath != null) {
            OutputStream os = response.getOutputStream();
            File file = new File(imgPath);
            FileInputStream fips = new FileInputStream(file);
            if (file.exists()) {
                byte[] btImg = readStream(fips);
                os.write(btImg);
                os.flush();
                if (null != fips) {
                    fips.close();
                }
                if (null != os) {
                    os.close();
                }

            }
        }
    }

    /**
     * 读取管道中的流数据
     */
    public byte[] readStream(InputStream inStream) {
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        int data = -1;
        try {
            while ((data = inStream.read()) != -1) {
                bops.write(data);
            }
            return bops.toByteArray();
        } catch (Exception e) {
            return null;
        } finally {
            if (null != bops) {
                try {
                    bops.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
