package com.c14.dao;

import com.c14.dao.provider.JobDynaProvider;
import com.c14.model.Job;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.c14.dao.HrmConstants.JOBTABLE;

public interface JobDao {

    @Select("select * from " + JOBTABLE + " Where ID = #{id} ")
    Job selectById(int id);

    @Select("select * from " + JOBTABLE + " ")
    Integer selectAllJob(Map<String, Object> params);

    @SelectProvider(type = JobDynaProvider.class, method = "selectByPage")
    List<Job> selectByPage(Map<String, Object> params);

    @SelectProvider(type = JobDynaProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @Delete(" delete from " + JOBTABLE + " Where id = #{id} ")
    void deleteById(Integer id);

    @SelectProvider(type = JobDynaProvider.class, method = "insertJob")
    void save(Job job);

    @SelectProvider(type = JobDynaProvider.class, method = "updateJob")
    void update(Job job);
}
