package itcast.dao;

import itcast.domain.Members;
import org.apache.ibatis.annotations.Select;

public interface IMembersDao {

    @Select("select * from members where id=#{membersId}")
    Members findById(String membersId);
}
