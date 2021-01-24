package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
     /*
    多条件课程列表查询
     */
    public List<Course> findCourseByConditioin(CourseVo courseVo) {
        List<Course> list = courseMapper.findCourseByConditioin(courseVo);
        return list;
    }


    /*
      添加课程及讲师信息
      */
    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVo);
        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        //添加课程
        courseMapper.saveCourse(course);
        //获取新插入数据的id
        int id = course.getId();
        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);
        //补全信息
        teacher.setCourseId(id);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        //保存讲师信息
        courseMapper.saveTeacher(teacher);
    }


    /**
     * 根据id获取课程信息
     * */
    @Override
    public CourseVo findCourseById(int id) {
        CourseVo courseVo = courseMapper.findCourseById(id);
        return courseVo;
    }
    /**
     * 修改课程信息
     * */
    @Override
    public void updateCourseOrTeacher(CourseVo courseVO) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVO);
        //补全信息
        Date date = new Date();
        course.setUpdateTime(date);
        //更新课程
        courseMapper.updateCourse(course);
        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        //补全信息
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);
        //更新讲师信息
        courseMapper.updateTeacher(teacher);

    }
    /**
     * 修改课程状态
     * */
    @Override
    public void updateCourseStatus(int id, int status) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        courseMapper.updateCourseStatus(course);
    }


}
