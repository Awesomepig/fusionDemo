package com.ericsson.fusionany.im.controller;

import com.ericsson.fusionany.im.enmu.MessageType;
import com.ericsson.fusionany.im.service.FileService;
import com.ericsson.fusionany.im.service.IMService;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eric on 16-5-16.
 */
@Controller
public class IMController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public IMService imService;
    @Autowired
    public FileService fileService;

    //===========================local-method=================

    /**
     * way to default index page
     * @return
     */
    @RequestMapping("/im/index")
    public ModelAndView index(){
        return new ModelAndView("/im/index");
    }

    /**
     * add text message
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/im/addText")
    public ModelAndView addText(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("im/index");
        String content = request.getParameter("mesT");
        if (StringUtils.isEmpty(content)){
            mav.addObject("mes","添加内容为空!");
            mav.addObject("success","false");
        }else{
            imService.addMessageText(content);
            mav.addObject("success","true");
        }
        return mav;
    }

    /**
     * add emoji message
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/im/addEmoji")
    public ModelAndView addEmoji(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("im/index");
        String code = request.getParameter("mesE");
        /**
         * test
         */
        System.out.printf(System.getProperty("user.dir"));
//        System.out.println(Class.class.getClass().getResource("/").getPath());
        /**
         * test end
         */
        if (StringUtils.isEmpty(code)){
            mav.addObject("mes","添加表情为空!");
            mav.addObject("success","false");
        }else{
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
            imService.addMessageEmoji(basePath+"/resources/emoji/"+code+".png");
            mav.addObject("success","true");
        }
        return mav;
    }

    /**
     * add file message
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/im/addFile")
    public ModelAndView addFile(HttpServletRequest request, HttpServletResponse response){
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        ModelAndView mav = new ModelAndView("im/index");
        List<MultipartFile> files = null;
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(
                request.getServletContext());
        if (cmr.isMultipart(request)) {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
            Iterator<String> fileNames = mRequest.getFileNames();
            while (fileNames.hasNext()){
                MultipartFile file = mRequest.getFile(fileNames.next());
                String filename = file.getOriginalFilename();
                try {
                    InputStream in = file.getInputStream();
                    File res = fileService.createFile(in,filename);
                    String abPath = res.getAbsolutePath();
                    imService.addMessageFile(filename,basePath+abPath.substring(abPath.lastIndexOf("/resources/file/")));
                    mav.addObject("success","true");
                } catch (IOException e) {
                    logger.error("[IM-addFile] error when get file inputstream,"+e.getMessage());
                }
            }
//            files = ((MultipartRequest) request).getFiles("mesF");
        }else if (files.size()<1){
            mav.addObject("mes","添加附件为空!");
            mav.addObject("success","false");
        }



//        if (files.size()<1){
//            mav.addObject("mes","添加附件为空!");
//            mav.addObject("success","false");
//        }else if(files.size()>1){
//            mav.addObject("mes","添加附件过多!");
//            mav.addObject("success","false");
//        }else{
//            MultipartFile file = files.get(0);
//            String filename = file.getOriginalFilename();
//            try {
//                InputStream in = file.getInputStream();
//                File res = fileService.createFile(in,filename);
//                String abPath = res.getAbsolutePath();
//                imService.addMessageFile(filename,abPath.substring(abPath.lastIndexOf("/resources/file/")));
//                mav.addObject("success","true");
//            } catch (IOException e) {
//                logger.error("[IM-addFile] error when get file inputstream,"+e.getMessage());
//            }
////            imService.addMessageText(content);
//        }
        return mav;
    }

    /**
     * query for message list
     * @param request parameters :
     *                <ul>
     *                <li>startPosition : result's start position from all message list,default 0 </li>
     *                <li>total : the result length,default 10</li>
     *                </ul>
     *                any parameters with Non-numeric characters or negative number will be reset to default value
     * @param response
     * @return json Object
     */
    @RequestMapping("/im/messageList.htm")
    public
    @ResponseBody
    String queryAllMembers(HttpServletRequest request, HttpServletResponse response) {

        MessageType mesType = MessageType.MESSAGE;
        int total = 10;
        int start = 0;

        /**
         * 获取并设置参数
         */
        String startStr = request.getParameter("startPosition");
        if (!StringUtils.isEmpty(startStr) && startStr.matches("/^\\d+$/") ) {
            start = Integer.valueOf(startStr);
        }
        String totalStr = request.getParameter("total");
        if (!StringUtils.isEmpty(totalStr) && totalStr.matches("/^\\d+$/") ) {
            total = Integer.valueOf(totalStr);
        }


        JSONArray res = imService.getMessageList(start, total,false, mesType);
        return res.toString();
    }
    //==========================get-set==========================

    public IMService getImService() {
        return imService;
    }

    public void setImService(IMService imService) {
        this.imService = imService;
    }

    public FileService getFileService() {
        return fileService;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
