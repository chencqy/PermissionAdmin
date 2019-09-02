package com.chen.auth.authorization.oauth;


import com.chen.auth.authorization.entity.RoleEntity;
import com.chen.auth.authorization.entity.UserEntity;
import com.chen.auth.authorization.mapper.RoleEntityMapper;
import com.chen.auth.authorization.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @Author : Chen
 * @Date : Create in 2018/8/23 19:58
 * @Description :
 * @Modify by :
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserEntityMapper userDao;

    @Autowired
    private RoleEntityMapper roleDao;


    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {

        UserEntity userEntity = userDao.getUserByAccountName(accountName);

        if (userEntity == null) {
            throw new UsernameNotFoundException("用户：" + accountName + "不存在");
        }

        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();

        List<RoleEntity> roleEntity = roleDao.getRoleByUserId(userEntity.getId());

        Iterator<RoleEntity> iterator =  roleEntity.iterator();
        while (iterator.hasNext()){
            collection.add(new SimpleGrantedAuthority(iterator.next().getRole()));
        }

        String password = userEntity.getPassword();

        User user = new User(accountName, password, collection);
        return user;
    }
}
