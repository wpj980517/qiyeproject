package itcast.service;

import itcast.domain.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IPermissionService {

    public List<Permission> findAllPermission();

    public void savePermission(Permission permission);
}
