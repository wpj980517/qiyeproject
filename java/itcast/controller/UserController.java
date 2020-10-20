package itcast.controller;

import com.github.pagehelper.PageInfo;
import itcast.domain.Role;
import itcast.domain.UserInfo;
import itcast.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private IUserService service;


    @RequestMapping(path = "/getUser")
    public ModelAndView getUser(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        UserInfo info=service.findByUsername();
        HttpSession session=request.getSession();
        session.setAttribute("usersName", info.getUsername());
        mv.setViewName("main");
        return mv;
    }

    @RequestMapping(path = "/findAll")
    public ModelAndView findAll(@RequestParam(required = true,name = "page",defaultValue = "1") int page,@RequestParam(required = true,name = "size",defaultValue = "4") int size){
        ModelAndView mv=new ModelAndView();
        List<UserInfo> list=service.findAllUser(page, size);
        PageInfo pageInfo=new PageInfo(list);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping(path = "/save")
    public String save(UserInfo userInfo){
        service.saveUser(userInfo);
        return "redirect:findAll";
    }

    @RequestMapping(path = "/findById")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        UserInfo user= service.findById(id);
        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping(path = "/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv=new ModelAndView();
        UserInfo user=service.findById(id);
        //找到这个user没有的全部权限
        List<Role> list=service.findOthersRoles(id);
        mv.addObject("user",user);
        mv.addObject("roleList", list);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping(path = "/addRoleToUser")
    public String addRoleToUser(@RequestParam(required = true,name = "userId") String userId,@RequestParam(required = true,name = "ids") String[] roleIds){
        service.addRoleToUser(userId, roleIds);
        return "redirect:findAll";
    }

}
