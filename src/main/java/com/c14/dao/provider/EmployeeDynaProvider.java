package com.c14.dao.provider;

import com.c14.model.Employee;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.c14.dao.HrmConstants.EMPLOYEETABLE;

/**
 * @author ZZ
 */
public class EmployeeDynaProvider {

    public String selectWithParam(Map<String, Object> params){
        String selectAll = "*";

        return duplicateCode(selectAll, params).toString();
    }

    public String count(Map<String, Object> params){
        String countAll = "count(*)";
        return duplicateCode(countAll, params).toString();
    }

    public String insertEmployee(Employee employee){
        return new SQL(){
            {
                INSERT_INTO(EMPLOYEETABLE);
                if (employee.getName() != null){
                    VALUES("name", "#{name}");
                }
                if (employee.getCardId() != null){
                    VALUES("card_id", "#{cardId}");
                }
                if (employee.getAddress() != null){
                    VALUES("address", "#{address}");
                }
                if (employee.getPostCode() != null){
                    VALUES("post_code", "#{postCode}");
                }
                if (employee.getTel() != null){
                    VALUES("tel", "#{tel}");
                }
                if (employee.getPhone() != null){
                    VALUES("phone", "#{phone}");
                }
                if (employee.getQqNum() != null){
                    VALUES("qq_num", "#{qqNum}");
                }
                if (employee.getEmail() != null){
                    VALUES("email", "#{email}");
                }
                if (employee.getSex() != null){
                    VALUES("sex", "#{sex}");
                }
                if (employee.getParty() != null){
                    VALUES("party", "#{party}");
                }
                if (employee.getBirthDay() != null){
                    VALUES("birthday", "#{birthday}");
                }
                if (employee.getRace() != null){
                    VALUES("race", "#{race}");
                }
                if (employee.getEducation() != null){
                    VALUES("education", "#{education}");
                }
                if (employee.getSpeciality() != null){
                    VALUES("speciality", "#{speciality}");
                }
                if (employee.getHobby() != null){
                    VALUES("hobby", "#{hobby}");
                }
                if (employee.getRemake() != null){
                    VALUES("remark", "#{remark}");
                }
                if (employee.getCreateDate() != null){
                    VALUES("create_date", "#{createDate}");
                }
                if (employee.getDept() != null){
                    VALUES("dept_id", "#{dept.id}");
                }
                if (employee.getJob() != null){
                    VALUES("job_id", "#{job.id}");
                }
            }
        }.toString();
    }

    public String updateEmployee(Employee employee){
        return new SQL(){
            {
                UPDATE(EMPLOYEETABLE);
                if (employee.getName() != null){
                    SET("name", "#{name}");
                }
                if (employee.getCardId() != null){
                    SET("card_id", "#{cardId}");
                }
                if (employee.getAddress() != null){
                    SET("address", "#{address}");
                }
                if (employee.getPostCode() != null){
                    SET("post_code", "#{postCode}");
                }
                if (employee.getTel() != null){
                    SET("tel", "#{tel}");
                }
                if (employee.getPhone() != null){
                    SET("phone", "#{phone}");
                }
                if (employee.getQqNum() != null){
                    SET("qq_num", "#{qqNum}");
                }
                if (employee.getEmail() != null){
                    SET("email", "#{email}");
                }
                if (employee.getSex() != null){
                    SET("sex", "#{sex}");
                }
                if (employee.getParty() != null){
                    SET("party", "#{party}");
                }
                if (employee.getBirthDay() != null){
                    SET("birthday", "#{birthday}");
                }
                if (employee.getRace() != null){
                    SET("race", "#{race}");
                }
                if (employee.getEducation() != null){
                    SET("education", "#{education}");
                }
                if (employee.getSpeciality() != null){
                    SET("speciality", "#{speciality}");
                }
                if (employee.getHobby() != null){
                    SET("hobby", "#{hobby}");
                }
                if (employee.getRemake() != null){
                    SET("remark", "#{remark}");
                }
                if (employee.getCreateDate() != null){
                    SET("create_date", "#{createDate}");
                }
                if (employee.getDept() != null){
                    SET("dept_id", "#{dept.id}");
                }
                if (employee.getJob() != null){
                    SET("job_id", "#{job.id}");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }

    private SQL duplicateCode(String selectSql, Map<String, Object> params){
        return new SQL(){
            {
                SELECT(selectSql);
                FROM(com.c14.dao.HrmConstants.EMPLOYEETABLE);
                if (params.get("employee") != null){
                    Employee employee = (Employee) params.get("employee");
                    if (employee.getDept() != null && employee.getDept().getId() != null && employee.getDept().getId() != 0){
                        WHERE(" DEPT_ID = #{employee.dept.id} ");
                    }
                    if (employee.getJob() != null && employee.getJob().getId() != null &&
                            employee.getJob().getId() != 0){
                        WHERE(" JOB_ID = #{employee.job.id} ");
                    }
                    if (employee.getName() != null && !employee.getName().equals("")){
                        WHERE(" NAME LIKE CONCAT('%', #{employee.name} ,'%')");
                    }
                    if (employee.getPhone() != null && !employee.getPhone().equals("")){
                        WHERE(" phone LIKE CONCAT('%', #{employee.phone}, '%')");
                    }
                    if (employee.getCardId() != null && !employee.getCardId().equals("")){
                        WHERE(" card_id LIKE CONCAT('%', #{employee.cardId}, '%')");
                    }
                    if (employee.getSex() != null && employee.getSex() != 0){
                        WHERE(" sex = #{employee.sex}");
                    }
                }
            }
        };
    }
}
