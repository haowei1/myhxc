package com.hdy.myhxc.mapper.ex;

import com.hdy.myhxc.model.Menu;
import com.hdy.myhxc.model.ex.MenuEx;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author m760384371
 * @date 2019/8/26
 */
@Repository
@Mapper
public interface MenuExMapper {

    /**
     * 查询一级菜单
     * @return
     */
    @SelectProvider(type = MenuExSqlProvider.class, method = "getListForLevel1")
    @Results({@Result(column = "UUID",property = "uuid",jdbcType = JdbcType.VARCHAR,id = true),
            @Result(column = "Menu_ID",property = "menuId",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Menu_Name",property = "menuName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Menu_URL",property = "menuUrl",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Parent_ID",property = "parentId",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Menu_ICO",property = "menuIco",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Leavel_ID",property = "leavelId",jdbcType = JdbcType.VARCHAR),
            @Result(column = "SORT",property = "sort",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Create_User",property = "createUser",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Create_Date",property = "createDate",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Update_User",property = "updateUser",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Update_Date",property = "updateDate",jdbcType = JdbcType.VARCHAR),
            @Result(column = "DelFg",property = "delfg",jdbcType = JdbcType.VARCHAR),
            @Result(column = "UUID",property = "children",many = @Many(select = "com.hdy.myhxc.mapper.ex.MenuExMapper.getListForLevel2")),
    })
    List<MenuEx> getListForLevel1();

    /**
     * 查询二级菜单
     * @param uuid
     * @return
     */
    @SelectProvider(type = MenuExSqlProvider.class, method = "getListForLevel2")
    @Results({@Result(column = "UUID",property = "uuid",jdbcType = JdbcType.VARCHAR,id = true),
            @Result(column = "Menu_ID",property = "menuId",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Menu_Name",property = "menuName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Menu_URL",property = "menuUrl",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Parent_ID",property = "parentId",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Menu_ICO",property = "menuIco",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Leavel_ID",property = "leavelId",jdbcType = JdbcType.VARCHAR),
            @Result(column = "SORT",property = "sort",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Create_User",property = "createUser",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Create_Date",property = "createDate",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Update_User",property = "updateUser",jdbcType = JdbcType.VARCHAR),
            @Result(column = "Update_Date",property = "updateDate",jdbcType = JdbcType.VARCHAR),
            @Result(column = "DelFg",property = "delfg",jdbcType = JdbcType.VARCHAR),
    })
    List<MenuEx> getListForLevel2(@Param("UUID") String uuid);
}
