package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;
    /**
     * 分页获取广告列表数据
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVo promotionAdVo){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllAdByPage(promotionAdVo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", pageInfo);
        return result;

    }
    /**
     * 图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目部署路径
        //D:\JavaWebSoft\apache-tomcat-8.5.55\webapps\ssm-web
        String realPath = request.getServletContext().getRealPath("/");
        //3.截取webapps路径
        //D:\JavaWebSoft\apache-tomcat-8.5.55\webapps\
        String webappspath = realPath.substring(0, realPath.indexOf("ssm-web"));
        //4.获取原文件名
        String originalFilename = file.getOriginalFilename();
        //5.新文件名
        String newFileName =System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //6.上传文件
        String uploadPath=webappspath+"upload\\";
        File filePath = new File(uploadPath, newFileName);
        //如果目录不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
        }
        file.transferTo(filePath);
        //7将文件名和路径返回
        Map<String,String> map=new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }
    /**
     *修改广告上下线状态
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id,int status){
        promotionAdService.updatePromotionAdStatus(id,status);
        HashMap<String,Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "广告动态上下线成功", map);

        return result;

    }
}
