package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;
import com.lagou.domain.Role_resource_relation;

import java.util.List;

public interface RoleMapper {
    /*
    查询角色列表
     */
    public List<Role> findAllRole(Role role);
    /*
    根据角色id查询关联菜单
     */
    public List<Integer> findMenuByRoleId(Integer roleId);
    /*
    根据roleId清空该角色的菜单信息
     */
    public void deleteRoleContextMenu(Integer roleId);
    /*
    为角色分配菜单
     */
    public void RoleContextMenu(Role_menu_relation role_menu_relation);
    /*
    删除角色
     */
    public void deleteRole(int roleId);
    /*
    获取当前角色拥有的资源信息
     */
    public List<Resource> findResourceListByRoleId(Integer roleId);
    /*
    根据roleId清空该角色的资源信息
     */
    public void deleteRoleContextResource(Integer roleId);
    /*
    为角色分配菜单
     */
    public void RoleContextResource(Role_resource_relation role_resource_relation);
}
