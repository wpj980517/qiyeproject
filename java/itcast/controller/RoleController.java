package itcast.controller;

import itcast.dao.IRoleDao;
import itcast.domain.Permission;
import itcast.domain.Role;
import itcast.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/role")
public class RoleController {

    @Autowired
    private IRoleService service;

    @RequestMapping(path = "/findAllRole")
    public ModelAndView findAllRole(){
        ModelAndView mv=new ModelAndView();
        List<Role> list=service.findAllRole();
        mv.addObject("roleList",list);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping(path = "/saveRole")
    public String saveRole(Role role){
        service.saveRole(role);
        return "redirect:findAllRole";
    }

    @RequestMapping(path = "/findById")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        Role role =service.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }
    @RequestMapping(path = "findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(required = true,name = "id") String roleId){
        ModelAndView mv=new ModelAndView();
        Role role=service.findById(roleId);
        List<Permission> list=service.findOtherPermissions(roleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", list);
        mv.setViewName("permission-role-add");
        return mv;
    }

    @RequestMapping(path = "addPermissionToRole")
    public String addPermissionToRole(@RequestParam(required = true,name = "roleId") String roleId,@RequestParam(required = true,name = "ids") String[] permissionIds){

        service.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAllRole";
    }
}
