package com.lagou.service;

import com.lagou.domain.Resource;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.RoleResourceVo;

import java.util.List;

public interface RoleService {
    /*
     查询角色列表
      */
    public List<Role> findAllRole(Role role);
    /*
    根据角色信息查询关联菜单
     */
    public List<Integer> findMenuByRoleId(Integer roleId);
    /*
    为角色分配菜单
     */
    public void RoleContextMenu(RoleMenuVo roleMenuVo);
    /*
    删除角色
     */
    public void deleteRole(int id);
    /*
    根据角色id查询资源信息
     */
    public List<Resource> findResourceListByRoleId(Integer roleId);
    /*
    为角色分配资源
     */
    public void RoleContextResource(RoleResourceVo roleResourceVo);
}
