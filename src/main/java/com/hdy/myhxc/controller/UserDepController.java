package com.hdy.myhxc.controller;

import com.hdy.myhxc.entity.FormInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.model.UserDep;
import com.hdy.myhxc.service.UserDepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author m760384371
 * @date 2019/8/26
 */
@RestController
@RequestMapping("m0002")
public class UserDepController {

    @Autowired
    private UserDepService userDepServiceImpl;

    /**
     * 获取所有部门信息（递归）
     * @param info
     * @return
     */
    @RequestMapping("dep/list")
    public ResponseEntity<ResultData> showDepList(@ModelAttribute FormInfo info){
        return new ResponseEntity<>(userDepServiceImpl.getDepList(info.getPage(), info.getLimit()), HttpStatus.OK);
    }

    /**
     * 编辑部门信息  可更新或新增
     * @param userDep
     * @return
     */
    @RequestMapping("dep/edit")
    public ResponseEntity<ResultData> editDep(@ModelAttribute UserDep userDep){
        ResultData resultData = new ResultData();
        resultData.setData(userDepServiceImpl.editDep(userDep));
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }

    /**
     * 获取当前节点信息
     * @param uuid
     * @return
     */
    @RequestMapping("get/{uuid}")
    public ResponseEntity<ResultData> getDep(@PathVariable String uuid){
        return new ResponseEntity<>(userDepServiceImpl.getDep(uuid), HttpStatus.OK);
    }

    /**
     * 根据删除部门信息
     * @param uuid
     * @return
     */
    @RequestMapping("dep/delete/{uuid}")
    public ResponseEntity<ResultData> delDep(@PathVariable String uuid) {
        ResultData resultData = new ResultData();
        resultData.setData(userDepServiceImpl.delDep(uuid));
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }
}
