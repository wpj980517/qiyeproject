package itcast.service.impl;

import com.github.pagehelper.PageHelper;
import itcast.dao.IProductDao;
import itcast.domain.Product;
import itcast.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao dao;

    @Override
    public List<Product> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page, size);
        return dao.findAll();
    }

    @Override
    public void saveProduct(Product product)  {
            dao.saveProduct(product);
    }

}
