package itcast.service;

import itcast.domain.Role;
import itcast.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    public UserInfo findByUsername();

    public List<UserInfo> findAllUser(int page,int size);

    public void saveUser(UserInfo user);

    public UserInfo findById(String id);

    public List<Role> findOthersRoles(String id);

    public void addRoleToUser(String userId,String[] roleIds);
}
