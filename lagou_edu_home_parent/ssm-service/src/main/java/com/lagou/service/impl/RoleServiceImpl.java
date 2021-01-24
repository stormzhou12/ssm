package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    /**
     * 查询所有角色信息（条件查询）
     * @param role
     * @return
     */
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> roleList = roleMapper.findAllRole(role);
        return roleList;
    }

    /**
     * 根据角色ID查询关联的菜单信息ID
     * @param roleId
     * @return
     */

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> menuIdList = roleMapper.findMenuByRoleId(roleId);
        return menuIdList;
    }

    /**
     * 为角色分配菜单
     * @param roleMenuVo
     */

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        for (int mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            roleMapper.RoleContextMenu(role_menu_relation);
        }
    }

    /**
     * 删除角色
     * @param id
     */

    @Override
    public void deleteRole(int id) {
        roleMapper.deleteRoleContextMenu(id);
        roleMapper.deleteRole(id);

    }

    /**
     * 根据校色id查询资源信息
     * @param roleId
     * @return
     */

    @Override
    public List<Resource> findResourceListByRoleId(Integer roleId) {
       return roleMapper.findResourceListByRoleId(roleId);

    }

    /**
     * 为角色分配资源
     * @param roleResourceVo
     */

    @Override
    public void RoleContextResource(RoleResourceVo roleResourceVo) {
        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());
        for (Integer rid : roleResourceVo.getResourceIdList()) {
            Role_resource_relation role_resource_relation = new Role_resource_relation();
            role_resource_relation.setResourceId(rid);
            role_resource_relation.setRoleId(roleResourceVo.getRoleId());

            Date date = new Date();
            role_resource_relation.setCreatedTime(date);
            role_resource_relation.setUpdatedTime(date);

            role_resource_relation.setCreatedBy("system");
            role_resource_relation.setUpdatedby("system");
            roleMapper.RoleContextResource(role_resource_relation);
        }
    }

}
