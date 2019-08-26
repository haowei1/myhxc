package com.hdy.myhxc.controller;

import com.hdy.myhxc.entity.FormInfo;
import com.hdy.myhxc.entity.ResultData;
import com.hdy.myhxc.service.UserDepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author m760384371
 * @date 2019/8/26
 */
@Controller
@RequestMapping("m0002")
public class UserDepController {

    @Autowired
    private UserDepService userDepServiceImpl;

    @RequestMapping("dep/list")
    public ResponseEntity<ResultData> showDepList(@ModelAttribute FormInfo info){
        return new ResponseEntity<>(userDepServiceImpl.getDepList(info.getPage(), info.getLimit()), HttpStatus.OK);
    }

}
