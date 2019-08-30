package com.hdy.myhxc.mapper.ex;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class RoleExSqlProvider {

    /**
     * select UUID, Role_Name from m_role (where Role_name like % roleName %) order by create_Date
     * @param roleName
     * @return
     */
    public String getRole(@Param("roleName") String roleName){
        SQL sql=new SQL();
        sql.SELECT("UUID");
        sql.SELECT("Role_Name");
        sql.FROM("m_role");
        if (roleName!=null && !"".equals(roleName)){
            sql.WHERE("Role_Name like concat('%',#{roleName,jdbcType=VARCHAR},'%')");
        }
        sql.ORDER_BY("Create_Date");
        return sql.toString();
    }
    //a表  M_RoleAuthorityInfo, b表 M_Menu
    //select b.UUID,b.Menu_ID,b.Menu_Name from M_RoleAuthority a left outer join M_Menu b on a.Menu_ID=b.UUID
    //where a.Role_UUID='roleUuid';
    //左外连接  将两个表数据连接  左表中不符合要求的值用null补全
    public String getAuthority(@Param("roleUuid")String roleUuid){
        SQL sql=new SQL();
        sql.SELECT("b.UUID");
        sql.SELECT("b.Menu_ID");
        sql.SELECT("b.Menu_Name");
        sql.FROM("M_RoleAuthority a");
        sql.LEFT_OUTER_JOIN("M_Menu b ON a.Menu_ID=b.UUID");
        sql.WHERE("a.Role_UUID=#{roleUuid,jdbcType=VARCHAR}");
        return sql.toString();
    }
}
