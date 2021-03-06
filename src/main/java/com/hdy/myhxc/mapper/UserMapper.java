package com.hdy.myhxc.mapper;

import com.hdy.myhxc.model.User;
import com.hdy.myhxc.model.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user
     *
     * @mbg.generated Mon Aug 26 14:48:06 CST 2019
     */
    @SelectProvider(type=UserSqlProvider.class, method="countByExample")
    long countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user
     *
     * @mbg.generated Mon Aug 26 14:48:06 CST 2019
     */
    @DeleteProvider(type=UserSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user
     *
     * @mbg.generated Mon Aug 26 14:48:06 CST 2019
     */
    @Delete({
        "delete from m_user",
        "where UUID = #{uuid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user
     *
     * @mbg.generated Mon Aug 26 14:48:06 CST 2019
     */
    @Insert({
        "insert into m_user (UUID, User_Code, ",
        "User_Psd, User_Dep, ",
        "User_DepNM, User_NM, ",
        "User_Gender, User_Tel, ",
        "User_Role, Update_PWDT, ",
        "IsConstructionUnit, IsRoadPatrol, ",
        "IsDesignatedCU, IsEndTask, ",
        "Handheld_Device, JGRegister_ID, ",
        "Create_User, Create_Date, ",
        "Update_User, Update_Date, ",
        "DelFg, Beizhu)",
        "values (#{uuid,jdbcType=VARCHAR}, #{userCode,jdbcType=VARCHAR}, ",
        "#{userPsd,jdbcType=VARCHAR}, #{userDep,jdbcType=VARCHAR}, ",
        "#{userDepnm,jdbcType=VARCHAR}, #{userNm,jdbcType=VARCHAR}, ",
        "#{userGender,jdbcType=VARCHAR}, #{userTel,jdbcType=VARCHAR}, ",
        "#{userRole,jdbcType=VARCHAR}, #{updatePwdt,jdbcType=TIMESTAMP}, ",
        "#{isconstructionunit,jdbcType=VARCHAR}, #{isroadpatrol,jdbcType=VARCHAR}, ",
        "#{isdesignatedcu,jdbcType=VARCHAR}, #{isendtask,jdbcType=VARCHAR}, ",
        "#{handheldDevice,jdbcType=VARCHAR}, #{jgregisterId,jdbcType=VARCHAR}, ",
        "#{createUser,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP}, ",
        "#{updateUser,jdbcType=VARCHAR}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{delfg,jdbcType=VARCHAR}, #{beizhu,jdbcType=VARCHAR})"
    })
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user
     *
     * @mbg.generated Mon Aug 26 14:48:06 CST 2019
     */
    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user
     *
     * @mbg.generated Mon Aug 26 14:48:06 CST 2019
     */
    @SelectProvider(type=UserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="UUID", property="uuid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="User_Code", property="userCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_Psd", property="userPsd", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_Dep", property="userDep", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_DepNM", property="userDepnm", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_NM", property="userNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_Gender", property="userGender", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_Tel", property="userTel", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_Role", property="userRole", jdbcType=JdbcType.VARCHAR),
        @Result(column="Update_PWDT", property="updatePwdt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="IsConstructionUnit", property="isconstructionunit", jdbcType=JdbcType.VARCHAR),
        @Result(column="IsRoadPatrol", property="isroadpatrol", jdbcType=JdbcType.VARCHAR),
        @Result(column="IsDesignatedCU", property="isdesignatedcu", jdbcType=JdbcType.VARCHAR),
        @Result(column="IsEndTask", property="isendtask", jdbcType=JdbcType.VARCHAR),
        @Result(column="Handheld_Device", property="handheldDevice", jdbcType=JdbcType.VARCHAR),
        @Result(column="JGRegister_ID", property="jgregisterId", jdbcType=JdbcType.VARCHAR),
        @Result(column="Create_User", property="createUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="Create_Date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="Update_User", property="updateUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="Update_Date", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="DelFg", property="delfg", jdbcType=JdbcType.VARCHAR),
        @Result(column="Beizhu", property="beizhu", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user
     *
     * @mbg.generated Mon Aug 26 14:48:06 CST 2019
     */
    @Select({
        "select",
        "UUID, User_Code, User_Psd, User_Dep, User_DepNM, User_NM, User_Gender, User_Tel, ",
        "User_Role, Update_PWDT, IsConstructionUnit, IsRoadPatrol, IsDesignatedCU, IsEndTask, ",
        "Handheld_Device, JGRegister_ID, Create_User, Create_Date, Update_User, Update_Date, ",
        "DelFg, Beizhu",
        "from m_user",
        "where UUID = #{uuid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="UUID", property="uuid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="User_Code", property="userCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_Psd", property="userPsd", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_Dep", property="userDep", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_DepNM", property="userDepnm", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_NM", property="userNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_Gender", property="userGender", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_Tel", property="userTel", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_Role", property="userRole", jdbcType=JdbcType.VARCHAR),
        @Result(column="Update_PWDT", property="updatePwdt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="IsConstructionUnit", property="isconstructionunit", jdbcType=JdbcType.VARCHAR),
        @Result(column="IsRoadPatrol", property="isroadpatrol", jdbcType=JdbcType.VARCHAR),
        @Result(column="IsDesignatedCU", property="isdesignatedcu", jdbcType=JdbcType.VARCHAR),
        @Result(column="IsEndTask", property="isendtask", jdbcType=JdbcType.VARCHAR),
        @Result(column="Handheld_Device", property="handheldDevice", jdbcType=JdbcType.VARCHAR),
        @Result(column="JGRegister_ID", property="jgregisterId", jdbcType=JdbcType.VARCHAR),
        @Result(column="Create_User", property="createUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="Create_Date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="Update_User", property="updateUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="Update_Date", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="DelFg", property="delfg", jdbcType=JdbcType.VARCHAR),
        @Result(column="Beizhu", property="beizhu", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user
     *
     * @mbg.generated Mon Aug 26 14:48:06 CST 2019
     */
    @UpdateProvider(type=UserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user
     *
     * @mbg.generated Mon Aug 26 14:48:06 CST 2019
     */
    @UpdateProvider(type=UserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user
     *
     * @mbg.generated Mon Aug 26 14:48:06 CST 2019
     */
    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user
     *
     * @mbg.generated Mon Aug 26 14:48:06 CST 2019
     */
    @Update({
        "update m_user",
        "set User_Code = #{userCode,jdbcType=VARCHAR},",
          "User_Psd = #{userPsd,jdbcType=VARCHAR},",
          "User_Dep = #{userDep,jdbcType=VARCHAR},",
          "User_DepNM = #{userDepnm,jdbcType=VARCHAR},",
          "User_NM = #{userNm,jdbcType=VARCHAR},",
          "User_Gender = #{userGender,jdbcType=VARCHAR},",
          "User_Tel = #{userTel,jdbcType=VARCHAR},",
          "User_Role = #{userRole,jdbcType=VARCHAR},",
          "Update_PWDT = #{updatePwdt,jdbcType=TIMESTAMP},",
          "IsConstructionUnit = #{isconstructionunit,jdbcType=VARCHAR},",
          "IsRoadPatrol = #{isroadpatrol,jdbcType=VARCHAR},",
          "IsDesignatedCU = #{isdesignatedcu,jdbcType=VARCHAR},",
          "IsEndTask = #{isendtask,jdbcType=VARCHAR},",
          "Handheld_Device = #{handheldDevice,jdbcType=VARCHAR},",
          "JGRegister_ID = #{jgregisterId,jdbcType=VARCHAR},",
          "Create_User = #{createUser,jdbcType=VARCHAR},",
          "Create_Date = #{createDate,jdbcType=TIMESTAMP},",
          "Update_User = #{updateUser,jdbcType=VARCHAR},",
          "Update_Date = #{updateDate,jdbcType=TIMESTAMP},",
          "DelFg = #{delfg,jdbcType=VARCHAR},",
          "Beizhu = #{beizhu,jdbcType=VARCHAR}",
        "where UUID = #{uuid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(User record);
}