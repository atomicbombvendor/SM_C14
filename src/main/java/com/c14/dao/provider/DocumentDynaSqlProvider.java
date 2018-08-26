package com.c14.dao.provider;

import com.c14.model.Document;
import org.apache.ibatis.jdbc.SQL;

import javax.print.Doc;
import java.util.Map;
import java.util.Objects;

import static com.c14.dao.HrmConstants.DOCUMENTTABLE;

/**
 * @author ZZ
 */
public class DocumentDynaSqlProvider {

    public String selecWithParam(Map<String, Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(DOCUMENTTABLE);
                if (params.get("document") != null) {
                    Document document = (Document) params.get("document");
                    if (Objects.nonNull(document.getTitle()) && !document.getTitle().isEmpty()){
                        WHERE (" title LIKE CONCAT ('%',#{document.title},'%') ");
                    }
                }
            }
        }.toString();

        if (Objects.nonNull(params.get("pageModel"))){
            sql += " limit #{pageModel.firstLimitParam}, #{pagemodel.pageSize} ";
        }

        return sql;
    }

    /**
     * 动态查询总数量
     */
    public String count(Map<String, Object> params){
        return new SQL(){
            {
                SELECT("*");
                FROM(DOCUMENTTABLE);
                if (Objects.nonNull(params.get("document"))){
                    Document document = (Document)params.get("document");
                    if (Objects.nonNull(document.getTitle()) && !document.getTitle().isEmpty()){
                        WHERE(" title LIKE CONCAT ('%',#{document.title},'%') ");
                    }
                }
            }
        }.toString();
    }

    /**
     * 动态插入
     */
    public String insertDocument(Document document){
        return new SQL(){
            {
                INSERT_INTO(DOCUMENTTABLE);
                if (Objects.nonNull(document.getTitle()) && !document.getTitle().isEmpty()){
                    VALUES("title", "#{title}");
                }
                if (Objects.nonNull(document.getFileName()) && !document.getFileName().isEmpty()){
                    VALUES("filename", "#{filename}");
                }
                if (Objects.nonNull(document.getRemark()) && !document.getRemark().isEmpty()){
                    VALUES("remark", "#{remark}");
                }
                if (Objects.nonNull(document.getUser()) && document.getUser().getId() != null){
                    VALUES("user_id", "#{user.id}");
                }
            }
        }.toString();
    }

    /**
     * 动态更新
     */
    public String updateDocument(Document document){
        return new SQL(){
            {
                UPDATE(DOCUMENTTABLE);
                if (Objects.nonNull(document.getTitle()) && !document.getTitle().isEmpty()){
                    SET(" title = #{title}");
                }
                if (Objects.nonNull(document.getFileName()) && !document.getFileName().isEmpty()){
                    SET(" filename = #{filename}");
                }
                if (Objects.nonNull(document.getRemark()) && !document.getRemark().isEmpty()){
                    SET(" remark = #{remark}");
                }
                if (Objects.nonNull(document.getUser()) && document.getUser().getId() != null){
                    SET(" user_id = #{user.id}");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }

}
