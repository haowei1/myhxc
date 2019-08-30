package com.hdy.myhxc.mapper.ex;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author m760384371
 * @date 2019/8/26
 */
public class MenuExSqlProvider {

    /**
     * select * from m_menu where Leavel_ID = 1 order by sort
     * @return
     */
    public String getListForLevel1(){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("m_menu");
        sql.WHERE("Leavel_ID=1");
        sql.ORDER_BY("SORT");
        return sql.toString();
    }

    /**
     * select * from m_menu where Leavel_ID = 2 and where Parent_ID = '父id' order by sort
     * @param uuid
     * @return
     */
    public String getListForLevel2(@Param("UUID")String uuid){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("m_menu");
        sql.WHERE("Leavel_ID=2");
        sql.WHERE("Parent_ID=#{uuid}");
        sql.ORDER_BY("SORT");
        return sql.toString();
    }

}
