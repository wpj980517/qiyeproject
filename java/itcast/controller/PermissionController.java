package itcast.controller;


import itcast.domain.Permission;
import itcast.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/permission")
public class PermissionController {

    @Autowired
    private IPermissionService service;

    @RequestMapping(path = "/findAllPermission")
    public ModelAndView findAllPermission(){
        ModelAndView mv=new ModelAndView();
        List<Permission> list=service.findAllPermission();
        mv.addObject("permissionList",list);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping(path = "/savePermission")
    public String savePermission(Permission permission){
        service.savePermission(permission);
        return "redirect:findAllPermission";
    }
}
