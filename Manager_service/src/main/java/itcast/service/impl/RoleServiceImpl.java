package itcast.service.impl;

import itcast.dao.IRoleDao;
import itcast.domain.Permission;
import itcast.domain.Role;
import itcast.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao dao;

    @Override
    public List<Role> findAllRole() {
        return dao.findAllRole();
    }

    @Override
    public void saveRole(Role role) {
        dao.saveRole(role);
    }

    @Override
    public Role findById(String id) {
        return dao.findById3(id);
    }

    @Override
    public List<Permission> findOtherPermissions(String id) {
        return dao.findOtherPermissions(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for(String permissionId:permissionIds)
            dao.addPermissionToRole(roleId, permissionId);
    }
}
