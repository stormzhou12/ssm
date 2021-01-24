package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    /**
     * 查询课程下的章节与课时信息
     * */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);
    /**
     *
     *回显课程信息
     */
    public Course findCourseByCourseId(int courseId);
    /**
     * 新增章节信息
     */
    public void saveSection(CourseSection courseSection);
    /**
     * 修改章节信息
     */
    public void updateSection(CourseSection courseSection);
    /**
     *修改章节状态
     */
    public void  updateSectionStatus(CourseSection courseSection);
}
