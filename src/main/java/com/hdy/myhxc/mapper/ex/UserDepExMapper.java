package com.hdy.myhxc.mapper.ex;

import com.hdy.myhxc.model.ex.UserDepEx;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDepExMapper {
    @SelectProvider(type = UserDepExSqlProvider.class,method = "getListForLevel1")
    @Results({@Result(column = "UUID", property = "uuid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "Dep_Name",property = "depName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Dep_Level",property = "depLevel",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Dep_TopID",property = "depTopid",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Dep_TopName",property = "depTopname",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Show_Idx",property = "showIdx",jdbcType = JdbcType.VARCHAR),
            @Result(column = "UUID",property = "children",many = @Many(select = "com.hdy.myhxc.mapper.ex.UserDepExMapper.getListForLevel2"))

    })
    public List<UserDepEx> getListForLevel1();

    @SelectProvider(type = UserDepExSqlProvider.class,method = "getListForLevel2")
    @Results({@Result(column = "UUID", property = "uuid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "Dep_Name",property = "depName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Dep_Level",property = "depLevel",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Dep_TopID",property = "depTopid",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Dep_TopName",property = "depTopname",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Show_Idx",property = "showIdx",jdbcType = JdbcType.VARCHAR),
            @Result(column = "UUID",property = "children",jdbcType = JdbcType.VARCHAR,many = @Many(select = "com.hdy.myhxc.mapper.ex.UserDepExMapper.getListForLevel3"))
    })
    public List<UserDepEx> getListForLevel2(@Param("uuid") String uuid);

    @SelectProvider(type = UserDepExSqlProvider.class,method = "getListForLevel3")
    @Results({@Result(column = "UUID", property = "uuid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "Dep_Name",property = "depName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Dep_Level",property = "depLevel",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Dep_TopID",property = "depTopid",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Dep_TopName",property = "depTopname",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Show_Idx",property = "showIdx",jdbcType = JdbcType.VARCHAR),
    })
    public List<UserDepEx> getListForLevel3(@Param("uuid") String uuid);

    @Select("select*from m_userdep where Dep_Level=#{Dep_Level}")
    public List<UserDepEx> getListForLevel3ByNothing(@Param("Dep_Level") String depLevel);
}

