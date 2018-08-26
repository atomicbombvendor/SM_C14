package com.c14.dao.provider;

import com.c14.model.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.c14.dao.HrmConstants.USERTABLE;

public class UserDynaProvider {

    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(USERTABLE);
                if (params.get("user") != null) {
                    User user = (User) params.get("user");
                    if (user.getUserName() != null && !user.getUserName().equals("")) {
                        WHERE(" username LIKE CONCAT ('%', #{user.username}, '%') ");
                    }
                    if (user.getStatus() != null && !user.getStatus().equals("")) {
                        WHERE(" status LIKE CONCAT ('%', #{user.status}, '%')");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }

        return sql;
    }

    public String count(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("COUNT(*)");
                FROM(USERTABLE);
                if (params.get("user") != null) {
                    User user = (User) params.get("user");
                    if (user.getUserName() != null && !user.getUserName().equals("")) {
                        WHERE(" username LIKE CONCAT ('%', #{user.username}, '%') ");
                    }
                    if (user.getStatus() != null && !user.getStatus().equals("")) {
                        WHERE(" status LIKE CONCAT ('%', #{user.status}, '%') ");
                    }
                }
            }
        }.toString();
    }

    public String insertUser(User user){
        return new SQL(){
            {
                INSERT_INTO(USERTABLE);
                if (user.getUserName() != null && !user.getUserName().equals("")){
                    VALUES("username", "#{user.username}");
                }
                if (user.getStatus() != null && !user.getStatus().equals("")){
                    VALUES("status", "#{user.status}");
                }
                if (user.getLoginName() != null && !user.getLoginName().equals("")){
                    VALUES("loginname", "#{user.loginname}");
                }
                if (user.getPassword() != null && !user.getPassword().equals("")){
                    VALUES("password", "#{user.password}");
                }
            }
        }.toString();
    }

    public String updateUser(User user){
        return new SQL(){
            {
                UPDATE(USERTABLE);
                if (user.getUserName() != null){
                    SET(" username = #{user.username} ");
                }
                if (user.getLoginName() != null){
                    SET(" loginname = #{user.loginname} ");
                }
                if (user.getPassword() != null){
                    SET(" password = #{user.password} ");
                }
                if (user.getStatus() != null){
                    SET(" status = #{user.status} ");
                }
                if (user.getCreateDate() != null){
                    SET(" create_date = #{user.createDate} ");
                }
                WHERE(" id = #{id}");
            }
        }.toString();
    }
}
