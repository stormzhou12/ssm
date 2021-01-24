package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    /*
    多条件课程列表查询
   
     */
    @Autowired
    private CourseService courseService;
    @RequestMapping("/findCourseByConditioin")
    public ResponseResult findCourseByConditioin(@RequestBody CourseVo courseVo){
        List<Course> courseList = courseService.findCourseByConditioin(courseVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseList);
        return responseResult;
    }
    /*
    图片上传接口
     */
    @RequestMapping("/courseUpload")
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
    /*
    新增课程信息及讲师信息
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        if(courseVo.getId()==null){

            courseService.saveCourseOrTeacher(courseVo);
            ResponseResult result = new ResponseResult(true, 200, "新增成功", null);
            return result;
        }else {
            courseService.updateCourseOrTeacher(courseVo);
            ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
            return result;
        }

    }

    /**
     * 根据id获取课程信息
     * */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVo courseVo = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "根据id查询课程信息成功", courseVo);
        return result;

    }

    /**
     * 修改课程状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id,int status){
        courseService.updateCourseStatus(id,status);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "修改状态成功", status);
        return result;
    }

}
