package itcast.dao;

import itcast.domain.Role;
import itcast.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "PASSWORD",column = "PASSWORD"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "STATUS",column = "STATUS"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "itcast.dao.IRoleDao.findById"))
    })
    public UserInfo findByUsername(String username);

    //查询所有
    @Select("select * from users")
    public List<UserInfo> findAllUser();

    //添加用户
    @Insert("insert into users(email,username,PASSWORD,phoneNum,STATUS) values(#{email},#{username},#{PASSWORD},#{phoneNum},#{STATUS})")
    public void saveUser(UserInfo user);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "PASSWORD",column = "PASSWORD"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "STATUS",column = "STATUS"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "itcast.dao.IRoleDao.findById2"))
    })
    public UserInfo findById(String id);


    //找到这个user没有的全部权限
    @Select("select * from role where id not in(select roleId from users_role where userId=#{id})")
    public List<Role> findOthersRoles(String id);


    //为用户添加角色
    @Insert("insert into users_role values(#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);
    //这里两个都是String类型的对象，因此mybatis会不知道使用哪个来匹配，因此需要使用@Param来进行区分
}
