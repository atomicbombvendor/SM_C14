package com.c14.dao;

import com.c14.dao.provider.NoticeDynaSqlProvider;
import com.c14.model.Notice;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.c14.dao.HrmConstants.NOTICETABLE;

public interface NoticeDao {

    @SelectProvider(type = NoticeDynaSqlProvider.class, method = "selectByPage")
    @Results (value = {
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "CREATE_DATE", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "USER_ID", property = "user", one=@One(select="com.c14.dao.UserDao.selectById", fetchType = FetchType.EAGER))
    })
    List<Notice> selectByPage(Map<String, Object> params);

    @SelectProvider(type = NoticeDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @Select("SELECT * FROM " + NOTICETABLE + " WHERE ID= #{id}")
    Notice selectById(int id);

    @Delete("DELETE * FROM " + NOTICETABLE+ " WHERE ID = #{id}")
    void deleteById(Integer id);

    @SelectProvider(type = NoticeDynaSqlProvider.class, method = "insertNotice")
    void save(Notice notice);

    @SelectProvider(type = NoticeDynaSqlProvider.class, method = "updateNotice")
    void update(Notice notice);
}
