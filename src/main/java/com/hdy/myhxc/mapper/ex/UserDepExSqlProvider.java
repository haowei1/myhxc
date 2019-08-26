package com.hdy.myhxc.mapper.ex;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserDepExSqlProvider {

    public String getListForLevel1(){
        SQL sql=new SQL();
        sql.SELECT("*");
        sql.FROM("M_UserDep");
        sql.WHERE("Dep_Level='1'");
        sql.ORDER_BY("Show_Idx");
        return sql.toString();
    }
    //根据1级id查出2级数据
    public String getListForLevel2(@Param("uuid") String uuid){
        SQL sql=new SQL();
        sql.SELECT("*");
        sql.FROM("M_UserDep");
        sql.WHERE("Dep_Level='2'");
        sql.WHERE("Dep_TopID = #{uuid,jdbcType=VARCHAR}");
        sql.ORDER_BY("Show_Idx");
        return sql.toString();
    }
    //根据2级id查出3级数据
    public String getListForLevel3(@Param("uuid") String uuid){
        SQL sql=new SQL();
        sql.SELECT("*");
        sql.FROM("M_UserDep");
        sql.WHERE("Dep_Level='3'");
        sql.WHERE("Dep_TopID = #{uuid,jdbcType=VARCHAR}");
        sql.ORDER_BY("Show_Idx");
        return sql.toString();
    }
}
