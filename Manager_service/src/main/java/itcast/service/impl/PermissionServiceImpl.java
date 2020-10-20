package itcast.service.impl;

import itcast.dao.IPermissionDao;
import itcast.domain.Permission;
import itcast.service.IPermissionService;
import itcast.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao dao;

    @Override
    public List<Permission> findAllPermission() {
        return dao.findAllPermission();
    }

    @Override
    public void savePermission(Permission permission) {
        dao.savePermission(permission);
    }
}
