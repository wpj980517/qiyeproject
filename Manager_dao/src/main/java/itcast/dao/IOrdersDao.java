package itcast.dao;

import itcast.domain.Members;
import itcast.domain.Orders;
import itcast.domain.Product;
import itcast.domain.Traveller;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    //查询所有订单
    @Select("select * from orders")
    @Results(
            {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "payTypeStr",column = "payTypeStr"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "itcast.dao.IProductDao.findById"))
            }
    )
    List<Orders> findAllOrders();


    @Select("select * from orders where id=#{id}")
    @Results(
            {
                    @Result(id = true,property = "id",column = "id"),
                    @Result(property = "orderNum",column = "orderNum"),
                    @Result(property = "orderTime",column = "orderTime"),
                    @Result(property = "peopleCount",column = "peopleCount"),
                    @Result(property = "orderDesc",column = "orderDesc"),
                    @Result(property = "payType",column = "payType"),
                    @Result(property = "payTypeStr",column = "payTypeStr"),
                    @Result(property = "orderStatus",column = "orderStatus"),
                    @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "itcast.dao.IProductDao.findById")),
                    @Result(property = "members",column = "membersId",javaType = Members.class,one = @One(select = "itcast.dao.IMembersDao.findById")),
                    @Result(property = "travellers",column = "id",javaType =java.util.List.class,one=@One(select = "itcast.dao.ITravellersDao.findByOrdersId")),

            }
    )
    Orders findById(String id);
}
