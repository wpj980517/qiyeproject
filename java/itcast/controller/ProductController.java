package itcast.controller;

import com.github.pagehelper.PageInfo;
import itcast.domain.Product;
import itcast.service.IProductService;
import itcast.service.impl.ProductServiceImpl;
import itcast.utils.DateStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired()
    private IProductService service;

    //利用@InitBinder来进行类型转换
    @InitBinder
    public  void  initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new DateStringEditor());
    }

    @RequestMapping(path = "/findAllProduct")
    public ModelAndView findAllProduct(@RequestParam(required = true,name = "page",defaultValue = "1") int page,@RequestParam(required = true,name = "size",defaultValue = "2") int size){
        ModelAndView mv=new ModelAndView();
        try {
            System.out.println("find运行");
            List<Product> list=service.findAll(page,size);
            PageInfo info=new PageInfo(list);
            mv.addObject("pageInfo",info);
            mv.setViewName("product-list1");
            return mv;
        } catch (Exception e) {
            throw new RuntimeException("查找失败");
        }

    }
    @RequestMapping( path = "/saveProduct")
    public String saveProduct(Product product){
        System.out.println(product);
            service.saveProduct(product);
            return "redirect:findAllProduct";
    }

}
