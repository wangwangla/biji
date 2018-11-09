package kw.test.utils;

import kw.test.model.admin.Admin;

import java.util.List;

/**
 * auther   kangwang
 */
public class ReturnValue<V> {
    private ReturnValue(){

    }
    private String msg;
    private Integer code;
    private List<V> listData;
    private V data;

    public V getData() {
        return data;
    }

    public void setData(V date) {
        this.data = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<V> getListData() {
        return listData;
    }

    public void setListData(List<V> listData) {
        this.listData = listData;
    }
    public static ReturnValue build(){
        return new ReturnValue();
    }
}
