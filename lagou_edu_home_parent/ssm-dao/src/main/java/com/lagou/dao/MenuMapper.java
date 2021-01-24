package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {
    /*
    查询所有父子菜单信息
     */
    public List<Menu> findSubMenuListByPid(int pid);
    /*
    查询所有菜单列表
     */
    public List<Menu> findAllMenu();
    /*
    通过id查询菜单信息
     */

    Menu findMenuById(int id);
}
