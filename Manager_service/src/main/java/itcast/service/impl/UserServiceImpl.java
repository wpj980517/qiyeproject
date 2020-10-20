package itcast.service.impl;

import com.github.pagehelper.PageHelper;
import itcast.dao.IUserDao;
import itcast.domain.Role;
import itcast.domain.UserInfo;
import itcast.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    /*
        使用UserDetails原因是因为spring-security执行时会调用实现这个接口的类，然后我们使用@Service
        来指定调用让spring-security来调用
     */

    @Autowired
    private IUserDao dao;

    private String username;

    @Autowired  //获取密码加密类用来加密
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        this.username=username;
        //先获取到info对象
        UserInfo info=dao.findByUsername(username);
        //然后将其封装到一个实现了UserDetails接口的User实现类里面

//        User user=new User(info.getUsername(), "{noop}"+info.getPASSWORD(),getAuthority(info));

        //这个会把状态也加上
        User user=new User(info.getUsername(), info.getPASSWORD(), info.getSTATUS()==0 ? false : true, true, true, true, getAuthority(info));

        return user;
    }
    private List<SimpleGrantedAuthority> getAuthority(UserInfo userInfo){
        List<SimpleGrantedAuthority> list=new ArrayList<SimpleGrantedAuthority>();

        for(Role role :userInfo.getRoles() ){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        System.out.println(list.size());
        return list;
    }

    @Override
    public UserInfo findByUsername() {
        UserInfo info=dao.findByUsername(username);
        return info;
    }

    @Override
    public List<UserInfo> findAllUser(int page,int size) {
        PageHelper.startPage(page, size);
        return dao.findAllUser();
    }

    @Override
    public void saveUser(UserInfo user) {
        //对密码进行加密
        user.setPASSWORD(bCryptPasswordEncoder.encode(user.getPASSWORD()));
        dao.saveUser(user);
    }

    @Override
    public UserInfo findById(String id) {
        return dao.findById(id);
    }

    @Override
    public List<Role> findOthersRoles(String id) {
        return dao.findOthersRoles(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for(String roleId:roleIds)
            dao.addRoleToUser(userId, roleId);
    }
}
