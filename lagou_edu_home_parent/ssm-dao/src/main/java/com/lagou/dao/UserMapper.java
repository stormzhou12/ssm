package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有用户
     */
    public List<User> findAllUserByPage(UserVo userVo);
    /*
    修改用户状态
     */
    public void updateUserStatus(User user);
    /*
    用户登录
     */
    public User login(User user);
    /*
    用户注册，没写完
     */
    public void register(User user);
    /*
    根据用户id清空中间表
     */
    public void deleteUserContextRole(Integer userId);
    /*
    分配角色
     */
    public void userContextRole(User_Role_relation user_role_relation);
    /*
    根据用户id 查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(int id);
    /*
    根据角色id 集合，查询角色所拥有的顶级菜单
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    /*
    查询子菜单
     */
    public List<Menu> findSubMenuByPid(int pid);
    /*
    根据角色id集合，查询角色拥有的资源
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);
}
