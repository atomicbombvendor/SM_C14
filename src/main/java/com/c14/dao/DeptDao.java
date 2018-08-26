package com.c14.dao;

import com.c14.dao.provider.DeptDynaProvider;
import com.c14.model.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.c14.dao.HrmConstants.DEPTTABLE;

public interface DeptDao {

    @SelectProvider(type=DeptDynaProvider.class, method = "selectWithParam")
    List<Dept> selectByPage(Map<String, Object> params);

    @SelectProvider(type=DeptDynaProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @SelectProvider(type = DeptDynaProvider.class, method = "selectAllDept")
    List<Dept> selectAllDept();

    @Select("SELECT * FROM " + DEPTTABLE + " Where ID = #{id} ")
    Dept selectById(int id);

    @Delete("DELETE FROM " + DEPTTABLE + " Where ID = #{id} ")
    void deleteById(Integer id);

    @SelectProvider(type = DeptDynaProvider.class, method = "insertDept")
    void save(Dept dept);

    @SelectProvider(type = DeptDynaProvider.class, method = "updateDept")
    void update(Dept dept);


}
