package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 查询所有用户
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo pageInfo = userService.findAllUserByPage(userVo);
        ResponseResult result = new ResponseResult(true, 200, "查询所有用户成功", pageInfo);
        return result;

    }

    /**
     * 修改用户状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id,String status){
        userService.updateUserStatus(id,status);
        HashMap<String,String> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "修改用户状态成功", map);

        return result;

    }

    /**
     * 用户登录
     * @param user
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public ResponseResult longin(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if(user1 !=null){
            //保存用户Id及access_token到session中
            HttpSession session = request.getSession();
            String s = UUID.randomUUID().toString();
            session.setAttribute("access_token",s);
            session.setAttribute("user_id",user1.getId());
            //将查询出来的信息响应给前台
            Map<String, Object> map = new HashMap<>();
            map.put("access_token",s);
            map.put("user_id",user1.getId());
            //将查询出来的user也存到map中
            map.put("user",user1);
           return new ResponseResult(true,1,"登录成功",map);
        }else {
           return new ResponseResult(true,400,"用户名密码错误",null);
        }

    }

    /**
     * 分配角色
     * @param userVo
     * @return
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        return new ResponseResult(true,1,"分配角色成功",null);
    }

    /**
     * 根据用户Id查询关联的角色信息
     * @param id
     * @return
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(int id){
        List<Role> userRelationRoleById = userService.findUserRelationRoleById(id);
       return new ResponseResult(true,200,"根据用户Id查询关联的角色信息成功",userRelationRoleById);
    }

    /**
     *  获取用户拥有的菜单权限
     * @param request
     * @return
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头中的token
        String token = request.getHeader("Authorization");
        //获取session中的access_token
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");
        //判断
        if(token.equals(access_token)){
            int user_id = (Integer) session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(user_id);
            return result;
        }else {
            ResponseResult result = new ResponseResult(false, 400, "获取失败", null);
            return result;
        }
    }
}
