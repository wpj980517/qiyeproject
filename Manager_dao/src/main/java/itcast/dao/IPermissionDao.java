package itcast.dao;

import itcast.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {


    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findById(String id);

    @Select("select * from permission")
    public List<Permission> findAllPermission();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void savePermission(Permission permission);
}
