package itcast.service.impl;

import com.github.pagehelper.PageHelper;
import itcast.dao.IOrdersDao;
import itcast.domain.Orders;
import itcast.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrderService {

    @Autowired
    private IOrdersDao dao;

    @Override
    public List<Orders> findAllOrders(int page ,int size) {
        //使用分页管理pagehelper进行分页，注意，这个必须是在真正实现这个方法的代码之前，否则无效
        PageHelper.startPage(page, size);//pageNum表示哪一页开始，pageSize表示每页的条数
        return dao.findAllOrders();
    }

    @Override
    public Orders findById(String id) {
        return dao.findById(id);
    }

}
