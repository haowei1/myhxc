package com.hdy.myhxc.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author m760384371
 * @date 2019/8/23
 */
public class RequestData implements Serializable {

    private Map<String, String> form = new LinkedHashMap<String, String>();


    public Map<String, String> getForm() {
        return form;
    }

    public void setForm(Map<String, String> form) {
        this.form = form;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "form=" + form +
                '}';
    }
}
