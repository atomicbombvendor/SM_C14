package com.c14.dao.provider;

import com.c14.model.Notice;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.c14.dao.HrmConstants.NOTICETABLE;

public class NoticeDynaSqlProvider {

    public String selectWithParam(Map<String, Object> params){
        String sql = queryTemplate(params, "*");

        if (params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    public String count(Map<String, Object> params){
        return queryTemplate(params, "count(*)");
    }

    private String queryTemplate(Map<String, Object> params, String select){
        return new SQL(){
            {
                SELECT(select);
                FROM(NOTICETABLE);
                if (params.get("notice") != null){
                    Notice notice = (Notice) params.get("notice");
                    if (notice.getTitle() != null && !notice.getTitle().equals("")){
                        WHERE(" title LIKE CONCAT ('%', #{notice.title}, '%')");
                    }
                    if (notice.getContent() != null && !notice.getContent().isEmpty()){
                        WHERE(" content LIKE CONCAT ('%', #{notice.content},'%')");
                    }
                }
            }
        }.toString();
    }

    public String insertNotice(Notice notice){
        return new SQL(){
            {
                INSERT_INTO(NOTICETABLE);
                if (notice.getTitle() != null && !notice.getTitle().isEmpty()){
                    VALUES("title", "#{title}");
                }
                if (notice.getContent() != null && !notice.getContent().isEmpty()){
                    VALUES("content", "#{content}");
                }
                if (notice.getUser() != null && notice.getUser().getId() != null){
                    VALUES("user_id", "#{user.id}");
                }
            }
        }.toString();
    }

    public String updateNotice(Notice notice){
        return new SQL(){
            {
                UPDATE(NOTICETABLE);
                if (notice.getTitle() != null && !notice.getTitle().isEmpty()){
                    SET(" title = #{title} ");
                }
                if (notice.getContent() != null && !notice.getContent().isEmpty()){
                    SET(" content = #{content} ");
                }
                if (notice.getUser() != null && notice.getUser().getId() != null){
                    SET(" user_id = #{user.id} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
