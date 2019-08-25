package com.hdy.myhxc.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author m760384371
 * @date 2019/8/23
 */
public class FormInfo implements Serializable {

    private int page;
    private int limit;
    private int flg;
    private String uuid;
    private String[] uuids;
    private Map<String, String> form = new LinkedHashMap<>();

    @Override
    public String toString() {
        return "FormInfo{" +
                "page=" + page +
                ", limit=" + limit +
                ", flg=" + flg +
                ", uuid='" + uuid + '\'' +
                ", uuids=" + Arrays.toString(uuids) +
                ", form=" + form +
                '}';
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getFlg() {
        return flg;
    }

    public void setFlg(int flg) {
        this.flg = flg;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String[] getUuids() {
        return uuids;
    }

    public void setUuids(String[] uuids) {
        this.uuids = uuids;
    }

    public Map<String, String> getForm() {
        return form;
    }

    public void setForm(Map<String, String> form) {
        this.form = form;
    }
}
