package com.hdy.myhxc.mapper;

import com.hdy.myhxc.model.Role;
import com.hdy.myhxc.model.RoleExample.Criteria;
import com.hdy.myhxc.model.RoleExample.Criterion;
import com.hdy.myhxc.model.RoleExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class RoleSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_role
     *
     * @mbg.generated Fri Aug 23 16:23:24 CST 2019
     */
    public String countByExample(RoleExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("m_role");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_role
     *
     * @mbg.generated Fri Aug 23 16:23:24 CST 2019
     */
    public String deleteByExample(RoleExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("m_role");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_role
     *
     * @mbg.generated Fri Aug 23 16:23:24 CST 2019
     */
    public String insertSelective(Role record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("m_role");
        
        if (record.getUuid() != null) {
            sql.VALUES("UUID", "#{uuid,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleName() != null) {
            sql.VALUES("Role_Name", "#{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateUser() != null) {
            sql.VALUES("Create_User", "#{createUser,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("Create_Date", "#{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUser() != null) {
            sql.VALUES("Update_User", "#{updateUser,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            sql.VALUES("Update_Date", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelfg() != null) {
            sql.VALUES("DelFg", "#{delfg,jdbcType=VARCHAR}");
        }
        
        if (record.getBeizhu() != null) {
            sql.VALUES("Beizhu", "#{beizhu,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_role
     *
     * @mbg.generated Fri Aug 23 16:23:24 CST 2019
     */
    public String selectByExample(RoleExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("UUID");
        } else {
            sql.SELECT("UUID");
        }
        sql.SELECT("Role_Name");
        sql.SELECT("Create_User");
        sql.SELECT("Create_Date");
        sql.SELECT("Update_User");
        sql.SELECT("Update_Date");
        sql.SELECT("DelFg");
        sql.SELECT("Beizhu");
        sql.FROM("m_role");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_role
     *
     * @mbg.generated Fri Aug 23 16:23:24 CST 2019
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        Role record = (Role) parameter.get("record");
        RoleExample example = (RoleExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("m_role");
        
        if (record.getUuid() != null) {
            sql.SET("UUID = #{record.uuid,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleName() != null) {
            sql.SET("Role_Name = #{record.roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateUser() != null) {
            sql.SET("Create_User = #{record.createUser,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("Create_Date = #{record.createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUser() != null) {
            sql.SET("Update_User = #{record.updateUser,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            sql.SET("Update_Date = #{record.updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelfg() != null) {
            sql.SET("DelFg = #{record.delfg,jdbcType=VARCHAR}");
        }
        
        if (record.getBeizhu() != null) {
            sql.SET("Beizhu = #{record.beizhu,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_role
     *
     * @mbg.generated Fri Aug 23 16:23:24 CST 2019
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("m_role");
        
        sql.SET("UUID = #{record.uuid,jdbcType=VARCHAR}");
        sql.SET("Role_Name = #{record.roleName,jdbcType=VARCHAR}");
        sql.SET("Create_User = #{record.createUser,jdbcType=VARCHAR}");
        sql.SET("Create_Date = #{record.createDate,jdbcType=TIMESTAMP}");
        sql.SET("Update_User = #{record.updateUser,jdbcType=VARCHAR}");
        sql.SET("Update_Date = #{record.updateDate,jdbcType=TIMESTAMP}");
        sql.SET("DelFg = #{record.delfg,jdbcType=VARCHAR}");
        sql.SET("Beizhu = #{record.beizhu,jdbcType=VARCHAR}");
        
        RoleExample example = (RoleExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_role
     *
     * @mbg.generated Fri Aug 23 16:23:24 CST 2019
     */
    public String updateByPrimaryKeySelective(Role record) {
        SQL sql = new SQL();
        sql.UPDATE("m_role");
        
        if (record.getRoleName() != null) {
            sql.SET("Role_Name = #{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateUser() != null) {
            sql.SET("Create_User = #{createUser,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("Create_Date = #{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUser() != null) {
            sql.SET("Update_User = #{updateUser,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            sql.SET("Update_Date = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelfg() != null) {
            sql.SET("DelFg = #{delfg,jdbcType=VARCHAR}");
        }
        
        if (record.getBeizhu() != null) {
            sql.SET("Beizhu = #{beizhu,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("UUID = #{uuid,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_role
     *
     * @mbg.generated Fri Aug 23 16:23:24 CST 2019
     */
    protected void applyWhere(SQL sql, RoleExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}