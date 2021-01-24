package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 查询所有用户
     * @param userVo
     * @return
     */
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        return pageInfo;
    }

    /**
     * 修改用户状态
     * @param id
     * @param status
     */

    @Override
    public void updateUserStatus(int id, String status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());
        userMapper.updateUserStatus(user);
    }

    /**
     * 用户登录
     * @param user
     * @return
     */

    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        if(user1 !=null && Md5.verify(user.getPassword(),"lagou",user1.getPassword())){
            return user1;
        }else {
            return null;
        }

    }



    /**
     * 分配角色
     * @param userVo
     */


    @Override
    public void userContextRole(UserVo userVo) {
        //根据userid清空中间表关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());
        //分配角色
        for (Integer roleid : userVo.getRoleIdList()) {

            User_Role_relation user_role_relation = new User_Role_relation();

            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleid);

            user_role_relation.setCreatedTime(new Date());
            user_role_relation.setUpdatedTime(new Date());

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.userContextRole(user_role_relation);
        }
    }

    /**
     * 根据用户id查询关联的角色信息
     * @param id
     * @return
     */

    @Override
    public List<Role> findUserRelationRoleById(int id) {
        List<Role> roleList = userMapper.findUserRelationRoleById(id);
        return roleList;
    }

    /**
     * 获取用户权限，进行菜单动态展示
     * @param id
     * @return
     */

    @Override
    public ResponseResult getUserPermissions(Integer id) {
        //1.获取当前用户所拥有的角色
        List<Role> rolelist = userMapper.findUserRelationRoleById(id);
        //1.1 获取角色id集合
        List<Integer> list = new ArrayList<>();
        for (Role role : rolelist) {
            list.add(role.getId());
        }
        //2.根据角色id 集合，查询角色所拥有的顶级菜单
        List<Menu> parentMenuList = userMapper.findParentMenuByRoleId(list);
        //3.查询子菜单
        for (Menu menu : parentMenuList) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }
        //4.根据角色id集合，查询角色拥有的资源
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenuList);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限chengg",map);
    }
}
