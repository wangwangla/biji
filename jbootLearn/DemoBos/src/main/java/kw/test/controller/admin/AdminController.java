package kw.test.controller.admin;

import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import kw.test.model.admin.Admin;
import kw.test.model.request.AdminLoginRequest;
import kw.test.model.response.AdminResponse;
import kw.test.service.AdminService;
import kw.test.utils.ReturnValue;
import kw.test.utils.constant.ConstMsg;
import kw.test.utils.exception.MyException;

import javax.inject.Inject;
import java.util.List;

/**
 * auther   kangwang
 * 2018-11-8
 */
@RequestMapping("/admin")
public class AdminController extends JbootController {
    @Inject
    private AdminService adminService;

    public void login(AdminLoginRequest adminLoginRequest) throws MyException {
        String loginName = getPara("uername");
        String password = getPara("password");
        adminLoginRequest.setUserName(loginName);
        adminLoginRequest.setPassword(password);
        ReturnValue returnValue = ReturnValue.build();
        List<Admin> admin = adminService.login(adminLoginRequest);
        if(admin.size()==0)
        {
            returnValue.setMsg(ConstMsg.LOGIN_NOTINFO);
            setAttr("adminInfo",returnValue);
            render("/admin/admin.html");
        }else if(admin.get(0).getPassword().equals(password)){
            returnValue.setData(admin.get(0));
            returnValue.setMsg(ConstMsg.LOGIN_SUCCESS);
            setAttr("adminInfo",returnValue);
            render("/ht_page/kw_hout_index.html");
        }else {
            returnValue.setMsg(ConstMsg.PASSWOD_ERROR);
            setAttr("adminInfo",returnValue);
            render("/admin/admin.html");
        }
    }
}
