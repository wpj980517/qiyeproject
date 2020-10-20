package itcast.service;

import itcast.domain.Permission;
import itcast.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAllRole();

    public void saveRole(Role role);

    public Role findById(String id);

    public List<Permission> findOtherPermissions(String id);

    public void addPermissionToRole(String roleId,String[] permissionIds);
}
