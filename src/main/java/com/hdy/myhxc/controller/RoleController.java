package com.hdy.myhxc.controller;

import com.hdy.myhxc.entity.FormInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.model.Role;
import com.hdy.myhxc.model.ex.RoleEx;
import com.hdy.myhxc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author m760384371
 * @date 2019/8/27
 */
@RestController
@RequestMapping("m0009")
public class RoleController {

    @Autowired
    private RoleService roleServiceImpl;

    /**
     * 获取所有用户的对应的权限信息
     * @param info
     * @return
     */
    @RequestMapping("list")
    public ResponseEntity<ResultData> getList(@ModelAttribute FormInfo info) {
        return new ResponseEntity<>(roleServiceImpl.getList(info.getForm().get("roleName"), info.getPage(), info.getLimit()), HttpStatus.OK);
    }

    /**
     * 获取所有权限信息
     * @param uuid
     * @return
     */
    @RequestMapping("role/list/{uuid}")
    public ResponseEntity<ResultData> getRoleList(@PathVariable String uuid) {
        return new ResponseEntity<>(roleServiceImpl.getRoleList(uuid), HttpStatus.OK);
    }

    /**
     * 根据id删除权限信息
     * @param uuid
     * @return
     */
    @RequestMapping("delete/{uuid}")
    public ResponseEntity<ResultData> delRole(@PathVariable String uuid) {
        ResultData resultData = new ResultData();
        resultData.setData(roleServiceImpl.delRole(uuid));
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    /**
     * 根据id进行批量删除
     * @param info
     * @return
     */
    @RequestMapping("list/delete")
    public ResponseEntity<ResultData> delRoles(@ModelAttribute FormInfo info){
        ResultData resultData = new ResultData();
        resultData.setData(roleServiceImpl.delRoles(info.getUuids()));
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    /**
     * 根据id修改角色权限信息
     * @param roleEx
     * @return
     */
    @RequestMapping("add/role")
    public ResponseEntity<ResultData> addRole(@ModelAttribute RoleEx roleEx){
        ResultData resultData = new ResultData();
        resultData.setData(roleServiceImpl.addRole(roleEx.getUuid(), roleEx.getMenuUuid()));
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    /**
     * 编辑角色信息
     * @param role
     * @return
     */
    @RequestMapping("edit")
    public ResponseEntity<ResultData> editRole(Role role) {
        ResultData resultData = new ResultData();
        resultData.setData(roleServiceImpl.editRole(role.getUuid(), role.getRoleName()));
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

}
