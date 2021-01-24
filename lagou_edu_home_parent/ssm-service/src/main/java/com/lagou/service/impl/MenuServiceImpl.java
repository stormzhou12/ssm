package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有父子菜单信息
     * @param pid
     * @return
     */
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> subMenuListByPid = menuMapper.findSubMenuListByPid(pid);
        return subMenuListByPid;
    }

    /**
     * 查询菜单列表
     * @return
     */

    @Override
    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    /**
     * 通过id查询菜单信息
     * @param id
     */

    @Override
    public Menu findMenuById(int id) {
        Menu menuById = menuMapper.findMenuById(id);
        return menuById;
    }
}
