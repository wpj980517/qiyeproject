package itcast.dao;

import itcast.domain.Permission;
import itcast.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId=#{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
    })
    public List<Role> findById(String id);

    @Select("select * from role where id in (select roleId from users_role where userId=#{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType =java.util.List.class,many = @Many(select = "itcast.dao.IPermissionDao.findById"))
    })
    public List<Role> findById2(String id);

    @Select("select * from role")
    public List<Role> findAllRole();


    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void saveRole(Role role);

    @Select("select * from role where id =#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType =java.util.List.class,many = @Many(select = "itcast.dao.IPermissionDao.findById"))
    })
    public Role findById3(String id);

    @Select(("select * from permission where id not in (select permissionId from role_permission where roleId=#{id})"))
    public List<Permission> findOtherPermissions(String id);

    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    public void addPermissionToRole(@Param("roleId") String roleId,@Param(("permissionId")) String permissionId);
}
