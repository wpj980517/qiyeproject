package itcast.controller;

import com.github.pagehelper.PageInfo;
import itcast.domain.Orders;
import itcast.domain.Role;
import itcast.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping(path = "/orders")
public class OrdersController {

    @Autowired
    private IOrderService service;




    //一次性查询所有订单
    /*
    @RequestMapping(path = "/findAllOrders")
    public ModelAndView findAllOrders(){
        ModelAndView mv =new ModelAndView();
        List<Orders> list= service.findAllOrders();
        mv.addObject("ordersList", list);
        mv.setViewName("orders-list");
        return mv;
    }
    */

    //分页查询所有订单
    @RequestMapping(path ="/findAllOrders" )
//    @Secured("ROLE_USER")   //配置权限，但是这个需要全名
    @RolesAllowed("USER")  //配置权限，只有ROLE_USER权限才能调动此方法,且其可以省略ROLE_前缀
    public ModelAndView findAllOrders(@RequestParam(required = true,name = "page",defaultValue = "1") int page,@RequestParam(required = true,name = "size",defaultValue = "4") int size){
        //需要从请求参数中获取初始页以及每页显示条数
        ModelAndView mv=new ModelAndView();
        List<Orders> list=service.findAllOrders(page,size);
        //利用pagehelper提供的pageInfo来接收数据，并且将其传出去
        PageInfo info=new PageInfo(list);
        mv.addObject("pageInfo", info);
        mv.setViewName("orders-page-list");
        return mv;
    }

    //查询详情
    @RequestMapping(path = "/findById")
//    @PreAuthorize("hasRole('ROLE_USER')")  //表示只有USER_ADMIN才能使用
//    @PreAuthorize("authentication.principal.username=='xiaojie'") //表明只有当username是xiaojie才能使用
    public ModelAndView findById(@RequestParam(required = true,name = "id") String id){
        ModelAndView mv=new ModelAndView();
        Orders orders=service.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return  mv;
    }



}
