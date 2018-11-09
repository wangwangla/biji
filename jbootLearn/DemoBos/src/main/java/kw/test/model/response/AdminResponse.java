package kw.test.model.response;

import kw.test.model.admin.Admin;

/**
 * auther   kangwang
 */
public class AdminResponse {
    private AdminResponse(){

    }
    private String msg;
    private Admin admin;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public static AdminResponse getInstant(){
        return new AdminResponse();
    }
}
