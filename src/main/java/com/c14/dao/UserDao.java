package com.c14.dao;

import com.c14.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;
import com.c14.dao.provider.UserDynaProvider;

public interface UserDao {

    @Select("select * from " + HrmConstants.USERTABLE + " where loginname = #{loginname} " +
            "and password = #{password}")
    User selectByLoginnameAndPassword(
            @Param("loginName") String loginname,
            @Param("password") String password
    );

    @Select("SELECT * from " + HrmConstants.USERTABLE + " Where ID = #{id}")
    User selectById(Integer id);

    @Delete("Delete from " + HrmConstants.USERTABLE + "Where id = #{id}")
    void deleteById(Integer id);

    @SelectProvider(type = UserDynaProvider.class,  method="updateUser")
    void update(User user);

    @SelectProvider(type= UserDynaProvider.class, method = "count")
    List<User> selectByPage(Map<String, Object> params);

    @SelectProvider(type = UserDynaProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @SelectProvider(type = UserDynaProvider.class, method = "insertUser")
    void save(User user);
}
