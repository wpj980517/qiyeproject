package itcast.service;

import itcast.domain.Product;

import java.util.List;

public interface IProductService {

     List<Product> findAll(int page,int size) throws  Exception;
     void saveProduct(Product product) ;
}
