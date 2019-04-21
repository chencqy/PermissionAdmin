package com.chen.vtg.service.impl;

import com.chen.vtg.entity.PermissionEntity;
import com.chen.vtg.entity.RoleEntity;
import com.chen.vtg.entity.UserEntity;
import com.chen.vtg.mapper.PermissionEntityMapper;
import com.chen.vtg.mapper.RoleEntityMapper;
import com.chen.vtg.mapper.UserEntityMapper;
import com.chen.vtg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MapperTest {


    @Autowired
    UserEntityMapper userDao;

    @Autowired
    RoleEntityMapper roleDao;

    @Autowired
    PermissionEntityMapper permissionDao;

    @Autowired
    UserService userService;

    @Test
    public void getRoleByUserId() {

        String accountName = "admin";
        UserEntity userEntity = userDao.getUserByAccountName(accountName);


        List<RoleEntity> roleEntity = roleDao.getRoleByUserId(userEntity.getId());

        Iterator<RoleEntity> iterator =  roleEntity.iterator();
        while (iterator.hasNext()){
            log.info("role : {}", iterator.next().getRole());
        }
    }

    @Test
    public void addPermission() {
        for (int i = 113; i < 117; i++) {
            LocalDateTime time = LocalDateTime.now();
            int parent = 54;
            String resource = "article:";
            String name = "文章";
            String type = "button";
            PermissionEntity permissionEntity = new PermissionEntity();
            permissionEntity.setId(i);
            permissionEntity.setParent(parent);
            permissionEntity.setResources(resource);
            permissionEntity.setName(name);
            permissionEntity.setType(type);
            permissionEntity.setCreateTime(time);
            permissionDao.insert(permissionEntity);
        }
//        Integer i = new Integer(1);
//        PermissionEntity permissionEntity = permissionDao.selectByPrimaryKey(i);
//        LocalDateTime time = LocalDateTime.now();
//        permissionEntity.setCreateTime(time);
//        permissionDao.updateByPrimaryKey(permissionEntity);
    }

    @Test
    public void getUserInfo(){
        String accountName = "admin";
        log.info("user:{}", userService.getUserInfo(accountName));
    }
}