package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.ResourceCategoryService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /**
     * 查询所有角色信息
     * @param role
     * @return
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult result = new ResponseResult(true, 200, "查询角色列表成功", allRole);
        return result;

    }

    /**
     * 查询所有父子菜单信息
     * @return
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuLit",subMenuListByPid);
        ResponseResult result = new ResponseResult(true, 200, "查询所有父子菜单信息成功", map);

        return result;
    }

    /**
     * 根据角色信息查询关联菜单
     * @param roleId
     * @return
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(int roleId){
        List<Integer> menuIdList = roleService.findMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true, 200, "根据角色信息查询关联菜单成功", menuIdList);
        return result;
    }

    /**
     * 为角色分配菜单
     * @param roleMenuVo
     * @return
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.RoleContextMenu(roleMenuVo);
        ResponseResult result = new ResponseResult(true, 200, "为角色分配菜单成功", null);
        return result;
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(int id){
        roleService.deleteRole(id);
        ResponseResult result = new ResponseResult(true, 200, "删除角色成功", null);
        return result;
    }

    /**
     * 根据角色id查询资源信息,可用于分配资源中的回显资源信息
     * @param roleId
     * @return
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){
        List<Resource> resourceList = roleService.findResourceListByRoleId(roleId);
       return new ResponseResult(true,200,"根据角色id查询资源信息成功",resourceList);
    }

    /**
     * 查询所有资源分类及每个资源分类下的资源信息
     * @return
     */
    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllCategoryAndResource")
    public ResponseResult findAllCategoryAndResource(){
        List<ResourceCategory> resourceCategoryList = resourceCategoryService.findSubResourceByCategoryId();
        ResponseResult result = new ResponseResult(true, 200, "响应成功", resourceCategoryList);
        return result;
    }

    /**
     * 为角色分配资源
     * @param roleResourceVo
     * @return
     */
    @RequestMapping("/RoleContextResource")
    public ResponseResult RoleContextResource(@RequestBody RoleResourceVo roleResourceVo){
        roleService.RoleContextResource(roleResourceVo);
        ResponseResult result = new ResponseResult(true, 200, "为角色分配资源成功", null);
        return result;
    }
}
