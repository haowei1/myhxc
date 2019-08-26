package com.hdy.myhxc.service;

import com.hdy.myhxc.entity.ResultData;

/**
 * @author m760384371
 * @date 2019/8/26
 */
public interface UserDepService {


    /**
     * 获取depList
     * @param page
     * @param limit
     * @return
     */
    ResultData getDepList(int page, int limit);

}
