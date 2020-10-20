package itcast.dao;

import itcast.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellersDao {

    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId=#{id})")
    List<Traveller> findByOrdersId(String id);
}
