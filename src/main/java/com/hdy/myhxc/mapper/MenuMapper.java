package com.hdy.myhxc.mapper;

import com.hdy.myhxc.model.Menu;
import com.hdy.myhxc.model.MenuExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_menu
     *
     * @mbg.generated Mon Aug 26 14:48:07 CST 2019
     */
    @SelectProvider(type=MenuSqlProvider.class, method="countByExample")
    long countByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_menu
     *
     * @mbg.generated Mon Aug 26 14:48:07 CST 2019
     */
    @DeleteProvider(type=MenuSqlProvider.class, method="deleteByExample")
    int deleteByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_menu
     *
     * @mbg.generated Mon Aug 26 14:48:07 CST 2019
     */
    @Delete({
        "delete from m_menu",
        "where UUID = #{uuid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_menu
     *
     * @mbg.generated Mon Aug 26 14:48:07 CST 2019
     */
    @Insert({
        "insert into m_menu (UUID, Menu_ID, ",
        "Menu_Name, Menu_URL, ",
        "Parent_ID, Menu_ICO, ",
        "Leavel_ID, SORT, ",
        "Create_User, Create_Date, ",
        "Update_User, Update_Date, ",
        "DelFg, Beizhu)",
        "values (#{uuid,jdbcType=VARCHAR}, #{menuId,jdbcType=VARCHAR}, ",
        "#{menuName,jdbcType=VARCHAR}, #{menuUrl,jdbcType=VARCHAR}, ",
        "#{parentId,jdbcType=VARCHAR}, #{menuIco,jdbcType=VARCHAR}, ",
        "#{leavelId,jdbcType=INTEGER}, #{sort,jdbcType=INTEGER}, ",
        "#{createUser,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP}, ",
        "#{updateUser,jdbcType=VARCHAR}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{delfg,jdbcType=VARCHAR}, #{beizhu,jdbcType=VARCHAR})"
    })
    int insert(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_menu
     *
     * @mbg.generated Mon Aug 26 14:48:07 CST 2019
     */
    @InsertProvider(type=MenuSqlProvider.class, method="insertSelective")
    int insertSelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_menu
     *
     * @mbg.generated Mon Aug 26 14:48:07 CST 2019
     */
    @SelectProvider(type=MenuSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="UUID", property="uuid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="Menu_ID", property="menuId", jdbcType=JdbcType.VARCHAR),
        @Result(column="Menu_Name", property="menuName", jdbcType=JdbcType.VARCHAR),
        @Result(column="Menu_URL", property="menuUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="Parent_ID", property="parentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="Menu_ICO", property="menuIco", jdbcType=JdbcType.VARCHAR),
        @Result(column="Leavel_ID", property="leavelId", jdbcType=JdbcType.INTEGER),
        @Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="Create_User", property="createUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="Create_Date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="Update_User", property="updateUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="Update_Date", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="DelFg", property="delfg", jdbcType=JdbcType.VARCHAR),
        @Result(column="Beizhu", property="beizhu", jdbcType=JdbcType.VARCHAR)
    })
    List<Menu> selectByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_menu
     *
     * @mbg.generated Mon Aug 26 14:48:07 CST 2019
     */
    @Select({
        "select",
        "UUID, Menu_ID, Menu_Name, Menu_URL, Parent_ID, Menu_ICO, Leavel_ID, SORT, Create_User, ",
        "Create_Date, Update_User, Update_Date, DelFg, Beizhu",
        "from m_menu",
        "where UUID = #{uuid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="UUID", property="uuid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="Menu_ID", property="menuId", jdbcType=JdbcType.VARCHAR),
        @Result(column="Menu_Name", property="menuName", jdbcType=JdbcType.VARCHAR),
        @Result(column="Menu_URL", property="menuUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="Parent_ID", property="parentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="Menu_ICO", property="menuIco", jdbcType=JdbcType.VARCHAR),
        @Result(column="Leavel_ID", property="leavelId", jdbcType=JdbcType.INTEGER),
        @Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="Create_User", property="createUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="Create_Date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="Update_User", property="updateUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="Update_Date", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="DelFg", property="delfg", jdbcType=JdbcType.VARCHAR),
        @Result(column="Beizhu", property="beizhu", jdbcType=JdbcType.VARCHAR)
    })
    Menu selectByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_menu
     *
     * @mbg.generated Mon Aug 26 14:48:07 CST 2019
     */
    @UpdateProvider(type=MenuSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_menu
     *
     * @mbg.generated Mon Aug 26 14:48:07 CST 2019
     */
    @UpdateProvider(type=MenuSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_menu
     *
     * @mbg.generated Mon Aug 26 14:48:07 CST 2019
     */
    @UpdateProvider(type=MenuSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_menu
     *
     * @mbg.generated Mon Aug 26 14:48:07 CST 2019
     */
    @Update({
        "update m_menu",
        "set Menu_ID = #{menuId,jdbcType=VARCHAR},",
          "Menu_Name = #{menuName,jdbcType=VARCHAR},",
          "Menu_URL = #{menuUrl,jdbcType=VARCHAR},",
          "Parent_ID = #{parentId,jdbcType=VARCHAR},",
          "Menu_ICO = #{menuIco,jdbcType=VARCHAR},",
          "Leavel_ID = #{leavelId,jdbcType=INTEGER},",
          "SORT = #{sort,jdbcType=INTEGER},",
          "Create_User = #{createUser,jdbcType=VARCHAR},",
          "Create_Date = #{createDate,jdbcType=TIMESTAMP},",
          "Update_User = #{updateUser,jdbcType=VARCHAR},",
          "Update_Date = #{updateDate,jdbcType=TIMESTAMP},",
          "DelFg = #{delfg,jdbcType=VARCHAR},",
          "Beizhu = #{beizhu,jdbcType=VARCHAR}",
        "where UUID = #{uuid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Menu record);
}