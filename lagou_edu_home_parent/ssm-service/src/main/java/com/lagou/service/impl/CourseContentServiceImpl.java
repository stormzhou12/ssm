package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper courseContentMapper;
    /**
     *
     * 根据课程ID查询章节与课时信息
     * @param courseId
     * @return
     */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {
       return courseContentMapper.findSectionAndLessonByCourseId(courseId);

    }

    /**
     * 回显章节对应的课程信息
     * @param courseId
     * @return
     */
    @Override
    public Course findCourseByCourseId(int courseId) {
        return courseContentMapper.findCourseByCourseId(courseId);
    }

    /**
     * 新增章节信息
     * @param courseSection
     */

    @Override
    public void saveSection(CourseSection courseSection) {
       Date date= new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseContentMapper.saveSection(courseSection);
    }

    /**
     *修改章节信息
     * @param courseSection
     */

    @Override
    public void updateSection(CourseSection courseSection) {
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSection(courseSection);
    }

    /**
     * 修改章节状态
     * @param id
     * @param status
     */

    @Override
    public void updateSectionStatus(int id,int status) {
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSectionStatus(courseSection);
    }

}
