package com.c14.dao;

import com.c14.dao.provider.EmployeeDynaProvider;
import com.c14.model.Employee;
import org.apache.ibatis.annotations.*;

import javax.persistence.FetchType;
import java.util.List;
import java.util.Map;

import static com.c14.dao.HrmConstants.EMPLOYEETABLE;

public interface EmployeeDao {

    @SelectProvider(type = EmployeeDynaProvider.class, method = "selectWithParam")
    Integer count(Map<String, Object> params);

    @SelectProvider(type = EmployeeDynaProvider.class, method = "selectWithParam")
    @Results({
        @Result(id=true, column = "id", property = "id"),
            @Result(column = "CARD_ID", property = "cardId"),
            @Result(column = "POST_CODE", property = "postCode"),
            @Result(column = "QQ_NUM", property = "qqNum"),
            @Result(column = "BIRTHDAY", property = "birthday", javaType=java.util.Date.class),
            @Result(column = "CREATE_DATE", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "DEPT_ID", property = "dept", one=@One(select="com.c14.dao.JobDao.selectById",
                    fetchType = org.apache.ibatis.mapping.FetchType.EAGER)),
            @Result(column = "JOB_ID",property = "job", one = @One(select="com.c14.dao.JobDao.selectById",
            fetchType = org.apache.ibatis.mapping.FetchType.EAGER))

    })
    List<Employee> selectByPage(Map<String, Object> params);

    @SelectProvider(type = EmployeeDynaProvider.class, method = "save")
    void save(Employee employee);

    @Delete(" delete from " + EMPLOYEETABLE + " Where id = #{id}")
    void deleteById(Integer id);

    @SelectProvider(type = EmployeeDynaProvider.class, method = "update")
    void update(Employee employee);

    @Select("select * from " + EMPLOYEETABLE + " Where id = #{id}")
    @Results({
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "CARD_ID", property = "cardId"),
            @Result(column = "POST_CODE", property = "postCode"),
            @Result(column = "QQ_NUM", property = "qqNum"),
            @Result(column = "BIRTHDAY", property = "birthday", javaType=java.util.Date.class),
            @Result(column = "CREATE_DATE", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "DEPT_ID", property = "dept", one=@One(select="com.c14.dao.JobDao.selectById",
                    fetchType = org.apache.ibatis.mapping.FetchType.EAGER)),
            @Result(column = "JOB_ID",property = "job", one = @One(select="com.c14.dao.JobDao.selectById",
                    fetchType = org.apache.ibatis.mapping.FetchType.EAGER))
    })
    Employee selectById(Integer id);
}
