package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;

    /**
     *
     * 根据课程ID查询章节与课时信息
     * @return
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(int courseId){
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "查询章节和课时成功", list);
        return result;
    }
    /**
     * 回显章节对应的课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(int courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "回想章节对应的课程信息成功", course);
        return result;

    }
    /**
     *
     * 新增章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if(courseSection.getId()==null){
            //新建章节
            courseContentService.saveSection(courseSection);
            ResponseResult result = new ResponseResult(true, 200, "新建章节成功", null);
            return result;
        }else {
            courseContentService.updateSection(courseSection);
            ResponseResult result = new ResponseResult(true, 200, "修改章节成功", null);
            return result;
        }

    }
    /**
     * 修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){
        courseContentService.updateSectionStatus(id,status);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "修改章节状态成功", map);
        return result;
    }

}
