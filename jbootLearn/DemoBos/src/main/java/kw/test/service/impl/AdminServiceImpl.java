package kw.test.service.impl;

import io.jboot.aop.annotation.Bean;
import kw.test.model.response.AdminResponse;
import kw.test.utils.constant.ConstMsg;
import kw.test.utils.exception.MyException;
import kw.test.model.request.AdminLoginRequest;
import kw.test.service.AdminService;
import kw.test.model.admin.Admin;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;
import java.util.List;

@Bean
@Singleton
public class AdminServiceImpl extends JbootServiceBase<Admin> implements AdminService {

    public List<Admin> login(AdminLoginRequest adminLoginRequest) {
        AdminResponse adminResponse = AdminResponse.getInstant();
        List<Admin> admin = DAO.find("Select *from admin where username=? and password=?",adminLoginRequest.getUserName(),adminLoginRequest.getPassword());
        System.out.println(admin);
        return admin;
    }
}