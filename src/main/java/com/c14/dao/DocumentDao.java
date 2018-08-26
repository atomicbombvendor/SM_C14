package com.c14.dao;

import com.c14.dao.provider.DocumentDynaSqlProvider;
import com.c14.model.Document;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.c14.dao.HrmConstants.DOCUMENTTABLE;

public interface DocumentDao {

    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "selectByPage")
    @Results({
           @Result(id = true, column = "id", property = "user"),
           @Result(column = "CREATE_DATE", property = "createDate", javaType = LocalDate.class),
           @Result(column = "USER_ID", property = "user",
                   one = @One(select = "com.c14.dao.UserDao.selectById", fetchType = FetchType.EAGER))
    })
    List<Document> selectByPage(Map<String, Object> params);

    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "insertDocument")
    Integer count(Map<String, Object> params);

    @Select(" select * from " + DOCUMENTTABLE + " Where ID = #{id}")
    Document selectById(int id);

    @Delete(" delete from " + DOCUMENTTABLE + " Where id = #{id}")
    void deleteById(int id);

    @UpdateProvider(type = DocumentDynaSqlProvider.class, method = "updateDocument")
    void update(Document document);
}
