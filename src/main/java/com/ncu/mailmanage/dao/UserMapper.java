package com.ncu.mailmanage.dao;

import com.ncu.mailmanage.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    User findByUsername(String username);

    User findByMailAddress(String mailAddress);

    List<User> listByNotLocked();

    List<User> listByConditionAndNotLocked(@Param("name") String name, @Param("mailAddress") String mailAddress);

    int insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

}