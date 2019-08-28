package com.hdy.myhxc.mapper.ex;

import com.hdy.myhxc.model.Menu;
import com.hdy.myhxc.model.ex.RoleEx;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleExMapper {
    @SelectProvider(type = RoleExSqlProvider.class,method = "getRole")
    @Results({@Result(column = "UUID",property = "uuid",id = true,jdbcType = JdbcType.VARCHAR),
            @Result(column = "Role_Name",property = "roleName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "UUID",property = "menuList",many = @Many(select = "com.hdy.myhxc.mapper.ex.RoleExMapper.getAuthority"))
    })
    public List<RoleEx> getRole(@Param("roleName") String roleName);

    @SelectProvider(type = RoleExSqlProvider.class,method = "getAuthority")
    public List<Menu> getAuthority(@Param("roleUuid") String roleUuid);
}
